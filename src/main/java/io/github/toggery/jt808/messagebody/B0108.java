package io.github.toggery.jt808.messagebody;

import java.util.StringJoiner;

/**
 * JT/T 消息体 0x0108 终端升级结果通知
 *
 * @author togger
 */
public class B0108 extends AbstractToStringJoiner {

    /** BYTE 升级类型，源自 {@link B8108#getType()} */
    private int type;

    /**
     * BYTE 升级结果
     * <ul>
     *     <li>0：成功；</li>
     *     <li>1：失败；</li>
     *     <li>2：取消；</li>
     * </ul>
     */
    private int result;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("type=" + type)
                .add("result=" + result)
        ;
    }


    /**
     * 获取升级类型，源自 {@link B8108#getType()}
     * @return BYTE 升级类型
     * @see B8108#getType()
     * @see B8108#TYPE_TERMINAL
     * @see B8108#TYPE_RTC_IC_CARD_READER
     * @see B8108#TYPE_BEIDOU_POSITIONING_MODULE
     */
    public int getType() {
        return type;
    }

    /**
     * 设置升级类型
     * @param type BYTE 升级类型，源自 {@link B8108#getType()}
     * @see B8108#TYPE_TERMINAL
     * @see B8108#TYPE_RTC_IC_CARD_READER
     * @see B8108#TYPE_BEIDOU_POSITIONING_MODULE
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取升级结果
     * <ul>
     *     <li>0：成功；</li>
     *     <li>1：失败；</li>
     *     <li>2：取消；</li>
     * </ul>
     * @return BYTE 升级结果
     * @see #RESULT_SUCCESSFUL
     * @see #RESULT_FAILED
     * @see #RESULT_CANCELLED
     */
    public int getResult() {
        return result;
    }

    /**
     * 设置升级结果
     * <ul>
     *     <li>0：成功；</li>
     *     <li>1：失败；</li>
     *     <li>2：取消；</li>
     * </ul>
     * @param result BYTE
     * @see #RESULT_SUCCESSFUL
     * @see #RESULT_FAILED
     * @see #RESULT_CANCELLED
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * 获取是否成功
     * @return 是否成功
     */
    public final boolean isSuccessful() {
        return result == RESULT_SUCCESSFUL;
    }


    /** 升级结果：成功 */
    public static final int RESULT_SUCCESSFUL = 0;
    /** 升级结果：失败 */
    public static final int RESULT_FAILED = 1;
    /** 升级结果：已取消 */
    public static final int RESULT_CANCELLED = 2;

}
