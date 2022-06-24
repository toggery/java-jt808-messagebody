package io.github.toggery.jt808.messagebody;

import java.util.StringJoiner;

/**
 * JT/T 消息体：0x8803 存储多媒体数据上传
 *
 * @author togger
 */
public class B8803 extends AbstractToStringJoiner {

    /**
     * BYTE 多媒体类型
     * @see B8802#getType()
     */
    private int type;

    /** BYTE 通道 ID */
    private int channel;

    /**
     * BYTE 事件项编码
     * @see B8802#getEvent()
     */
    private int event;

    /** BCD[6] 起始时间，yyMMddHHmmss */
    private String startTime;

    /** BCD[6] 结束时间，yyMMddHHmmss */
    private String endTime;

    /**
     * BYTE 删除标志
     * <ul>
     *     <li>0：保留；</li>
     *     <li>1：删除；</li>
     * </ul>
     */
    private int deleted;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("type=" + type)
                .add("channel=" + channel)
                .add("event=" + event)
                .add("startTime=" + (startTime == null ? "" : startTime))
                .add("endTime=" + (endTime == null ? "" : endTime))
                .add("deleted=" + deleted)
        ;
    }


    /**
     * 获取多媒体类型
     * @see B8802#getType()
     * @return BYTE 多媒体类型
     */
    public int getType() {
        return type;
    }

    /**
     * 设置多媒体类型
     * @see B8802#getType()
     * @param type BYTE 多媒体类型
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return BYTE 通道 ID
     */
    public int getChannel() {
        return channel;
    }

    /**
     * 设置通道 ID
     * @param channel BYTE 通道 ID
     */
    public void setChannel(int channel) {
        this.channel = channel;
    }

    /**
     * 获取事件项编码
     * @return BYTE 事件项编码
     * @see B8802#getEvent()
     */
    public int getEvent() {
        return event;
    }

    /**
     * 设置事件项编码
     * @param event BYTE 事件项编码
     * @see B8802#getEvent()
     */
    public void setEvent(int event) {
        this.event = event;
    }

    /**
     * 获取起始时间，yyMMddHHmmss
     * @return BCD[6] 起始时间，yyMMddHHmmss
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * 设置起始时间，yyMMddHHmmss
     * @param startTime BCD[6] 起始时间，yyMMddHHmmss
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结束时间，yyMMddHHmmss
     * @return BCD[6] 结束时间，yyMMddHHmmss
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间，yyMMddHHmmss
     * @param endTime BCD[6] 结束时间，yyMMddHHmmss
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取删除标志
     * <ul>
     *     <li>0：保留；</li>
     *     <li>1：删除；</li>
     * </ul>
     * @return BYTE 删除标志
     * @see #DELETED_RESERVE
     * @see #DELETED_DELETE
     */
    public int getDeleted() {
        return deleted;
    }

    /**
     * 设置删除标志
     * <ul>
     *     <li>0：保留；</li>
     *     <li>1：删除；</li>
     * </ul>
     * @param deleted BYTE 删除标志
     * @see #DELETED_RESERVE
     * @see #DELETED_DELETE
     */
    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }


    /**
     * 获取是否为删除
     * @return 是否为删除
     */
    public boolean isDeleted() {
        return deleted == DELETED_DELETE;
    }


    /** 删除标志：保留 */
    public static final int DELETED_RESERVE = 0;
    /** 删除标志：删除 */
    public static final int DELETED_DELETE = 1;

}
