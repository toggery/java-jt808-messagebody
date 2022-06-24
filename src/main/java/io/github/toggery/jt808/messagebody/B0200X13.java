package io.github.toggery.jt808.messagebody;

import java.util.StringJoiner;

/**
 * JT/T 消息体：0x0200 位置信息汇报 附加信息 0x13 路段行驶时间不足/过长报警附加信息，见表 30
 *
 * @author togger
 */
public class B0200X13 extends AbstractToStringJoiner {

    /** DWORD 路段 ID */
    private long id;

    /** WORD 路段行驶时间(秒) */
    private int duration;

    /**
     * BYTE 结果
     * <ul>
     *     <li>0：不足；</li>
     *     <li>1：过长；</li>
     * </ul>
     */
    private int result;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("id=" + id)
                .add("duration=" + duration)
                .add("result=" + result)
        ;
    }


    /**
     * 获取路段 ID
     * @return DWORD 路段 ID
     */
    public long getId() {
        return id;
    }

    /**
     * 设置路段 ID
     * @param id DWORD 路段 ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 获取路段行驶时间(秒)
     * @return WORD 路段行驶时间(秒)
     */
    public int getDuration() {
        return duration;
    }

    /**
     * 设置路段行驶时间(秒)
     * @param duration WORD 路段行驶时间(秒)
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * 获取结果
     * <ul>
     *     <li>0：不足；</li>
     *     <li>1：过长；</li>
     * </ul>
     * @return BYTE 结果
     * @see #RESULT_INSUFFICIENT
     * @see #RESULT_OVER_LENGTH
     */
    public int getResult() {
        return result;
    }

    /**
     * 设置结果
     * <ul>
     *     <li>0：不足；</li>
     *     <li>1：过长；</li>
     * </ul>
     * @param result BYTE 结果
     * @see #RESULT_INSUFFICIENT
     * @see #RESULT_OVER_LENGTH
     */
    public void setResult(int result) {
        this.result = result;
    }


    /**
     * 获取是否不足
     * @return 是否不足
     */
    public final boolean isInsufficient() {
        return result == RESULT_INSUFFICIENT;
    }

    /**
     * 获取是否过长
     * @return 是否过长
     */
    public final boolean isOverLength() {
        return result == RESULT_OVER_LENGTH;
    }


    /** 结果：不足 */
    public static final int RESULT_INSUFFICIENT = 0;
    /** 结果：过长 */
    public static final int RESULT_OVER_LENGTH = 1;
}
