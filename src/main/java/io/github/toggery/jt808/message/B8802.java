package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;

import java.util.StringJoiner;

/**
 * JT/T 消息体 0x8802 存储多媒体数据检索
 *
 * <p>注：不按时间范围则将起始时间/结束时间都设为000000000000。</p>
 *
 * @author togger
 */
public class B8802 extends AbstractToStringJoiner implements Codec {

    /**
     * BYTE 多媒体类型
     * <ul>
     *     <li>0：图像；</li>
     *     <li>1：音频；</li>
     *     <li>2：视频；</li>
     * </ul>
     */
    private int type;

    /** BYTE 通道 ID，0 表示检索该媒体类型的所有通道；*/
    private int channel;

    /**
     * BYTE 事件项编码
     * <ul>
     *     <li>0：平台下发指令；</li>
     *     <li>1：定时动作；</li>
     *     <li>2：抢劫报警触发；</li>
     *     <li>3：碰撞侧翻报警触发；</li>
     *     <li>其他保留</li>
     * </ul>
     */
    private int event;

    /** BCD[6] 起始时间，yyMMddHHmmss */
    private String startTime;

    /** BCD[6] 结束时间，yyMMddHHmmss */
    private String endTime;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("type=" + type)
                .add("channel=" + channel)
                .add("event=" + event)
                .add("startTime=" + (startTime == null ? "" : startTime))
                .add("endTime=" + (endTime == null ? "" : endTime))
        ;
    }

    @Override
    public void encode(int version, ByteBuf buf) {
        Codec.writeByte(buf, type);
        Codec.writeByte(buf, channel);
        Codec.writeByte(buf, event);
        Codec.writeBcd(buf, startTime, 6);
        Codec.writeBcd(buf, endTime, 6);
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        type = Codec.readByte(buf);
        channel = Codec.readByte(buf);
        event = Codec.readByte(buf);
        startTime = Codec.readBcd(buf, 6, false);
        endTime = Codec.readBcd(buf, 6, false);
    }


    /**
     * 获取多媒体类型
     * <ul>
     *     <li>0：图像；</li>
     *     <li>1：音频；</li>
     *     <li>2：视频；</li>
     * </ul>
     * @return BYTE 多媒体类型
     * @see #TYPE_IMAGE
     * @see #TYPE_AUDIO
     * @see #TYPE_VIDEO
     */
    public int getType() {
        return type;
    }

    /**
     * 设置多媒体类型
     * <ul>
     *     <li>0：图像；</li>
     *     <li>1：音频；</li>
     *     <li>2：视频；</li>
     * </ul>
     * @param type BYTE 多媒体类型
     * @see #TYPE_IMAGE
     * @see #TYPE_AUDIO
     * @see #TYPE_VIDEO
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取通道 ID
     * @return BYTE 通道 ID，0 表示检索该媒体类型的所有通道
     */
    public int getChannel() {
        return channel;
    }

    /**
     * 设置通道 ID
     * @param channel BYTE 通道 ID，0 表示检索该媒体类型的所有通道
     */
    public void setChannel(int channel) {
        this.channel = channel;
    }

    /**
     * 获取事件项编码
     * <ul>
     *     <li>0：平台下发指令；</li>
     *     <li>1：定时动作；</li>
     *     <li>2：抢劫报警触发；</li>
     *     <li>3：碰撞侧翻报警触发；</li>
     *     <li>其他保留</li>
     * </ul>
     * @return BYTE 事件项编码
     * @see #EVENT_PLATFORM_COMMAND
     * @see #EVENT_SCHEDULED_ACTION
     * @see #EVENT_ALARM_TRIGGERED_BY_ROBBERY
     * @see #EVENT_ALARM_TRIGGERED_BY_SIDE_ROLLING_CRASH
     */
    public int getEvent() {
        return event;
    }

    /**
     * 设置事件项编码
     * <ul>
     *     <li>0：平台下发指令；</li>
     *     <li>1：定时动作；</li>
     *     <li>2：抢劫报警触发；</li>
     *     <li>3：碰撞侧翻报警触发；</li>
     *     <li>其他保留</li>
     * </ul>
     * @param event BYTE 事件项编码
     * @see #EVENT_PLATFORM_COMMAND
     * @see #EVENT_SCHEDULED_ACTION
     * @see #EVENT_ALARM_TRIGGERED_BY_ROBBERY
     * @see #EVENT_ALARM_TRIGGERED_BY_SIDE_ROLLING_CRASH
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

    /** 多媒体类型：图像 */
    public static final int TYPE_IMAGE = 0;
    /** 多媒体类型：音频 */
    public static final int TYPE_AUDIO = 1;
    /** 多媒体类型：视频 */
    public static final int TYPE_VIDEO = 2;

    /** 事件项编码：平台下发指令 */
    public static final int EVENT_PLATFORM_COMMAND = 0;
    /** 事件项编码：定时动作 */
    public static final int EVENT_SCHEDULED_ACTION = 1;
    /** 事件项编码：抢劫报警触发 */
    public static final int EVENT_ALARM_TRIGGERED_BY_ROBBERY = 2;
    /** 事件项编码：碰撞侧翻报警触发 */
    public static final int EVENT_ALARM_TRIGGERED_BY_SIDE_ROLLING_CRASH = 3;

}
