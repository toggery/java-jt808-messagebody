package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.util.internal.PlatformDependent;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * JT/T 消息体 0x8500 车辆控制 // 2019 modify
 *
 * 注意：
 * <ul>
 *     <li>2019之前的版本，使用属性 {@link #flags}</li>
 *     <li>2019及其之后的版本，使用其他属性</li>
 * </ul>
 *
 * @author togger
 */
public class B8500 extends AbstractToStringJoiner implements Codec {

    /**
     * BYTE 控制标志，仅适用于 2019 之前的版本
     *
     * <ul>
     *     <li>bit0: 0.车门锁闭 1.车门开启</li>
     *     <li>其他: 保留</li>
     * </ul>
     */
    private int flags;

    /** 解码时未知的控制参数 */
    private Map<String, String> unknownParams;

    /** 0x0001 BYTE 车门，0.车门锁闭 1.车门开启 */
    private Integer x0001;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("flags=" + flags)
                .add("unknownParams=" + (unknownParams == null ? "" : unknownParams))
                .add("x0001=" + (x0001 == null ? "" : x0001))
        ;
    }

    /**
     * 编码控制参数
     *
     * @param version 版本号
     * @param fieldEncoder 控制参数编码方法
     * @param target 要编码的对象
     */
    protected void encodeParams(int version, CountedFieldEncoder<Integer> fieldEncoder, B8500 target) {
        Params.CODECS.values().forEach(f -> f.encode(version, fieldEncoder, target));
    }

    /**
     * 清除控制参数
     * @param target 要清除其上控制参数的对象
     */
    protected void clearParams(B8500 target) {
        Params.CODECS.values().forEach(f -> f.clear(target));
    }

    /**
     * 解码控制参数
     * @param id 控制参数 ID
     * @param version 版本号
     * @param buf 字节缓冲区
     * @param target 要解码的对象
     * @return 是否成功
     */
    protected boolean decodeParam(int id, int version, ByteBuf buf, B8500 target) {
        final FieldCodec<Integer, B8500, ?> param = Params.CODECS.get(id);
        if (param != null) {
            param.decode(version, buf, target);
            return true;
        }
        return false;
    }

    @Override
    public void encode(int version, ByteBuf buf) {
        if (version > 0) {
            Codec.writeCountHeadedContent(buf, IntUnit.WORD, this, (b, that) -> {
                final CountedFieldEncoder<Integer> encoder = new CountedFieldEncoder<>(b, Codec::writeWord);
                encodeParams(version, encoder, that);
                return encoder.getCount();
            });
            return;
        }

        Codec.writeByte(buf, flags);
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        if (unknownParams != null) {
            unknownParams.clear();
        }
        flags = 0;
        clearParams(this);

        if (version > 0) {
            int cnt = Codec.readWord(buf);
            while (cnt-- > 0) {
                final int id = Codec.readWord(buf);
                if (!decodeParam(id, version, buf, this)) {
                    putUnknownParam(id, buf);
                    // ！！！没有长度头，无法继续！！！
                    break;
                }
            }
            return;
        }

        flags = Codec.readByte(buf);
    }


    /**
     * 获取控制标志，仅适用于 2019 之前的版本
     * @return BYTE 控制标志，仅适用于 2019 之前的版本
     */
    public int getFlags() {
        return flags;
    }

    /**
     * 设置控制标志，仅适用于 2019 之前的版本
     * @param flags BYTE 控制标志，仅适用于 2019 之前的版本
     */
    public void setFlags(int flags) {
        this.flags = flags;
    }

    /**
     * 获取解码时未知的控制参数
     * @return 解码时未知的控制参数
     */
    public Map<String, String> getUnknownParams() {
        return unknownParams;
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
            unknownParams = new HashMap<>();
        }
        unknownParams.put(String.format("%#06x", id), ByteBufUtil.hexDump(buf));
    }

    private final static class Params {

        private final static Map<Integer, FieldCodec<Integer, B8500, ?>> CODECS = PlatformDependent.newConcurrentHashMap();

        static {
            // 0x0001 车门 BYTE 0.车门锁闭 1.车门开启
            register(0x0001, B8500::getX0001, B8500::setX0001, ver -> Codec::writeByte, ver -> Codec::readByte);
         }

        private static <V> void register(int id, Function<B8500, V> getter, BiConsumer<B8500, V> setter,
                                         Function<Integer, BiConsumer<ByteBuf, V>> encoderSupplier,
                                         Function<Integer, Function<ByteBuf, V>> decoderSupplier) {
            CODECS.put(id, new FieldCodec<>(id, getter, setter, encoderSupplier, decoderSupplier));
        }

        private static <V extends Codec> void register(int id,
                                                       Function<B8500, V> getter, BiConsumer<B8500, V> setter,
                                                       Supplier<V> valueCreator) {
            Objects.requireNonNull(valueCreator);
            final Function<Integer, BiConsumer<ByteBuf, V>> encoderSupplier = ver -> (b, v) -> v.encode(ver, b);
            final Function<Integer, Function<ByteBuf, V>> decoderSupplier = ver -> b -> {
                final V v = valueCreator.get();
                v.decode(ver, b);
                return v;
            };
            CODECS.put(id, new FieldCodec<>(id, getter, setter, encoderSupplier, decoderSupplier));
        }

    }


}
