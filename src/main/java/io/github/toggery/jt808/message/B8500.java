package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * JT/T 消息体 0x8500 车辆控制 // 2019 modify
 *
 * 注意：
 * <ul>
 *     <li>2019之前的版本，使用属性 {@link #command}</li>
 *     <li>
 *         2019及其之后的版本，使用其他属性
 *         <ul>
 *             <li>0x0001：车门；</li>
 *             <li>0x0002~0x8000：为标准修订预留；</li>
 *             <li>0xF001~0xFFFF：为厂家自定义控制类型；</li>
 *         </ul>
 *     </li>
 * </ul>
 *
 * @author togger
 */
public class B8500 extends AbstractToStringJoiner implements Codec {

    /**
     * BYTE 控制指令标志位，仅适用于 2019 之前的版本
     *
     * <ul>
     *     <li>bit0: 0.车门锁闭 1.车门开启</li>
     *     <li>其他: 保留</li>
     * </ul>
     */
    private int command;

    /** 解码时未知的控制参数 */
    private Map<String, String> unknownParams;

    /** 0x0001 BYTE 车门，0.车门锁闭 1.车门开启 */
    private Integer x0001;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add(IntUtil.byteHexString("command=", command))
                .add("unknownParams=" + (unknownParams == null ? "" : unknownParams))
                .add("x0001=" + (x0001 == null ? "" : x0001))
        ;
    }

    /**
     * 编码控制参数
     *
     * @param version 版本号
     * @param fieldEncoder 控制参数编码方法
     */
    protected void encodeParams(int version, CountedFieldEncoder<Integer> fieldEncoder) {
        PARAMS.values().forEach(f -> f.encode(version, fieldEncoder, this));
    }

    /**
     * 解码控制参数
     * @param id 控制参数 ID
     * @param version 版本号
     * @param buf 字节缓冲区
     * @return 是否成功
     */
    protected boolean decodeParam(int id, int version, ByteBuf buf) {
        final FieldCodec<Integer, B8500, ?> param = PARAMS.get(id);
        if (param != null) {
            param.decode(version, buf, this);
            return true;
        }
        return false;
    }

    @Override
    public void encode(int version, ByteBuf buf) {
        if (version > 0) {
            Codec.writeCountHeadedContent(buf, IntUnit.WORD, this, (b, v) -> {
                final CountedFieldEncoder<Integer> encoder = new CountedFieldEncoder<>(b, Codec::writeWord);
                encodeParams(version, encoder);
                return encoder.getCount();
            });
            return;
        }

        Codec.writeByte(buf, command);
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        command = 0;
        unknownParams = null;

        if (version > 0) {
            int cnt = Codec.readWord(buf);
            while (cnt-- > 0) {
                final int id = Codec.readWord(buf);
                if (!decodeParam(id, version, buf)) {
                    putUnknownParam(id, buf);
                    // ！！！没有长度头，无法继续！！！
                    break;
                }
            }
            return;
        }

        command = Codec.readByte(buf);
    }


    /**
     * 获取控制指令标志位，仅适用于 2019 之前的版本
     * @return BYTE 控制指令标志位，仅适用于 2019 之前的版本
     */
    public int getCommand() {
        return command;
    }

    /**
     * 设置控制指令标志位，仅适用于 2019 之前的版本
     * @param command BYTE 控制指令标志位，仅适用于 2019 之前的版本
     */
    public void setCommand(int command) {
        this.command = command;
    }

    /**
     * 获取解码时未知的控制参数
     * @return 解码时未知的控制参数
     */
    public Map<String, String> getUnknownParams() {
        return unknownParams;
    }

    /**
     * 设置解码时未知的控制参数
     * @param unknownParams 解码时未知的控制参数
     */
    public void setUnknownParams(Map<String, String> unknownParams) {
        this.unknownParams = unknownParams;
    }

    /**
     * 获取车门参数
     * @return 0x0001 BYTE 车门，0.车门锁闭 1.车门开启
     */
    public Integer getX0001() {
        return this.x0001;
    }

    /**
     * 设置车门参数
     * @param x0001 0x0001 BYTE 车门，0.车门锁闭 1.车门开启
     */
    public void setX0001(Integer x0001) {
        this.x0001 = x0001;
    }


    private void putUnknownParam(int id, ByteBuf buf) {
        if (unknownParams == null) {
            unknownParams = new LinkedHashMap<>();
        }
        unknownParams.put(IntUtil.wordHexString(id), ByteBufUtil.hexDump(buf));
    }

    private static final Map<Integer, FieldCodec<Integer, B8500, ?>> PARAMS = new LinkedHashMap<>();

    private static <V> void register(FieldCodec<Integer, B8500, V> fieldCodec) {
        PARAMS.put(fieldCodec.getId(), fieldCodec);
    }

    static {
        // 0x0001 车门 BYTE 0.车门锁闭 1.车门开启
        register(FieldCodec.ofByte(0x0001, B8500::getX0001, B8500::setX0001));
    }

}
