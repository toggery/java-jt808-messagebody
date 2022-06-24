package io.github.toggery.jt808.messagebody;

import java.util.StringJoiner;

/**
 * JT/T 消息体：0x0303 信息点播/取消 // 2019 del
 *
 * @author togger
 */
public class B0303 extends AbstractToStringJoiner {

    /** BYTE 信息类型 */
    private int type;

    /**
     * BYTE 点播/取消标志
     * <ul>
     *     <li>0：取消；</li>
     *     <li>1：点播；</li>
     * </ul>
     */
    private int action;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("type=" + type)
                .add("action=" + action)
        ;
    }


    /**
     * 获取信息类型
     * @return BYTE 信息类型
     */
    public int getType() {
        return type;
    }

    /**
     * 设置信息类型
     * @param type BYTE 信息类型
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取点播/取消标志
     * <ul>
     *     <li>0：取消；</li>
     *     <li>1：点播；</li>
     * </ul>
     * @return BYTE 点播/取消标志
     * @see #ACTION_CANCEL
     * @see #ACTION_DEMAND
     */
    public int getAction() {
        return action;
    }

    /**
     * 设置点播/取消标志
     * <ul>
     *     <li>0：取消；</li>
     *     <li>1：点播；</li>
     * </ul>
     * @param action BYTE 点播/取消标志
     * @see #ACTION_CANCEL
     * @see #ACTION_DEMAND
     */
    public void setAction(int action) {
        this.action = action;
    }


    /**
     * 获取是否取消
     * @return 是否取消
     */
    public boolean isCancelled() {
        return action == ACTION_CANCEL;
    }

    /**
     * 获取是否点播
     * @return 是否点播
     */
    public boolean isDamanded() {
        return action == ACTION_DEMAND;
    }


    /** 点播/取消标志：取消 */
    public static final int ACTION_CANCEL = 0;
    /** 点播/取消标志：点播 */
    public static final int ACTION_DEMAND = 1;

}
