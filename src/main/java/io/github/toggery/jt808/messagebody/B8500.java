package io.github.toggery.jt808.messagebody;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * JT/T 消息体：0x8500 车辆控制 // 2019 modify
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
public class B8500 extends AbstractToStringJoiner {

    /**
     * BYTE 控制指令标志位，仅适用于 2019 之前的版本
     *
     * <ul>
     *     <li>bit0：0.车门锁闭 1.车门开启；</li>
     *     <li>其他保留</li>
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
                .add(HexUtil.byteString("command=", command))
                .add("unknownParams=" + (unknownParams == null ? "" : unknownParams))
                .add("x0001=" + (x0001 == null ? "" : x0001))
        ;
    }


    /**
     * 获取控制指令标志位，仅适用于 2019 之前的版本
     * <ul>
     *     <li>bit0：0.车门锁闭 1.车门开启；</li>
     *     <li>其他保留</li>
     * </ul>
     * @return BYTE 控制指令标志位，仅适用于 2019 之前的版本
     */
    public int getCommand() {
        return command;
    }

    /**
     * 设置控制指令标志位，仅适用于 2019 之前的版本
     * <ul>
     *     <li>bit0：0.车门锁闭 1.车门开启；</li>
     *     <li>其他保留</li>
     * </ul>
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
     * 添加或更新未知的参数
     * @param id 参数 {@code ID}
     * @param value 十六进制字符串格式的参数值
     */
    public void putUnknownParam(int id, String value) {
        if (unknownParams == null) {
            unknownParams = new LinkedHashMap<>();
        }
        unknownParams.put(HexUtil.wordString(id), value);
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

}
