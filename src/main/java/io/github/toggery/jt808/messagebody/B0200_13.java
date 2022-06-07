package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 位置扩展信息 0x13 路段行驶时间不足/过长报警附加信息，见表30
 *
 * @author togger
 */
@Data
public class B0200_13 {

    /** DWORD 路段ID */
    private long id;

    /** WORD 路段行驶时间(秒) */
    private int duration;

    /** BYTE 结果，0.不足 1.过长 */
    private int result;

    /**
     * @return 是否不足
     */
    public final boolean isInsufficient() {
        return result == INSUFFICIENT;
    }

    /**
     * @return 是否过长
     */
    public final boolean isOverLength() {
        return result == OVER_LENGTH;
    }

    /** 不足 */
    public static final int INSUFFICIENT = 0;
    /** 过长 */
    public static final int OVER_LENGTH = 1;
}
