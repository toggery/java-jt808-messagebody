package io.github.toggery.jt808.messagebody;

import java.util.StringJoiner;

/**
 * JT/T 消息体：0x8400 电话回拨
 *
 * @author togger
 */
public class B8400 extends AbstractToStringJoiner {

    /**
     * BYTE 标志
     * <ul>
     *     <li>0：普通通话；</li>
     *     <li>1：监听；</li>
     * </ul>
     */
    private int type;

    /** STRING 电话号码，最长为 20 字节 */
    private String phone;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("type=" + type)
                .add("phone=" + (phone == null ? "" : phone))
        ;
    }


    /**
     * 获取标志
     * <ul>
     *     <li>0：普通通话；</li>
     *     <li>1：监听；</li>
     * </ul>
     * @return BYTE 标志
     * @see #TYPE_COMMON
     * @see #TYPE_LISTENING
     */
    public int getType() {
        return type;
    }

    /**
     * 设置标志
     * <ul>
     *     <li>0：普通通话；</li>
     *     <li>1：监听；</li>
     * </ul>
     * @param type BYTE 标志
     * @see #TYPE_COMMON
     * @see #TYPE_LISTENING
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取电话号码，最长为 20 字节
     * @return STRING 电话号码，最长为 20 字节
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话号码，最长为 20 字节
     * @param phone STRING 电话号码，最长为 20 字节
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }


    /**
     * 获取是否为普通通话
     * @return 是否为普通通话
     */
    public boolean isCommon() {
        return type == TYPE_COMMON;
    }

    /**
     * 获取是否为监听
     * @return 是否为监听
     */
    public boolean isListening() {
        return type == TYPE_LISTENING;
    }


    /** 标志：普通通话 */
    public static final int TYPE_COMMON = 0;
    /** 标志：监听 */
    public static final int TYPE_LISTENING = 1;

}
