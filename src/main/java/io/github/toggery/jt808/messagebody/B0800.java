package io.github.toggery.jt808.messagebody;

import java.util.StringJoiner;

/**
 * JT/T 消息体：0x0800 多媒体事件信息上传
 *
 * @author togger
 */
public class B0800 extends AbstractToStringJoiner {

    /** DWORD 多媒体数据 ID，值大于 0 */
    private long id;

    /**
     * BYTE 多媒体类型
     * <ul>
     *     <li>0：图像；</li>
     *     <li>1：音频；</li>
     *     <li>2：视频；</li>
     * </ul>
     */
    private int type;

    /**
     * BYTE 多媒体格式编码
     * <ul>
     *     <li>0：JPEG；</li>
     *     <li>1：TIF；</li>
     *     <li>2：MP3；</li>
     *     <li>3：WAV；</li>
     *     <li>4：WMV；</li>
     *     <li>其他保留</li>
     * </ul>
     */
    private int format;

    /**
     * BYTE 事件项编码
     * <ul>
     *     <li>0：平台下发指令；</li>
     *     <li>1：定时动作；</li>
     *     <li>2：抢劫报警触发；</li>
     *     <li>3：碰撞侧翻报警触发；</li>
     *     <li>4：门开拍照；</li>
     *     <li>5：门关拍照；</li>
     *     <li>6：车门由开变关，时速从小于 20 公里到超过 20 公里；</li>
     *     <li>7：定距拍照；</li>
     *     <li>其他保留</li>
     * </ul>
     */
    private int event;

    /** BYTE 通道 ID */
    private int channel;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("id=" + id)
                .add("type=" + type)
                .add("format=" + format)
                .add("event=" + event)
                .add("channel=" + channel)
        ;
    }


    /**
     * 获取多媒体数据 ID，值大于 0
     * @return DWORD 多媒体数据 ID，值大于 0
     */
    public long getId() {
        return id;
    }

    /**
     * 设置多媒体数据 ID，值大于 0
     * @param id DWORD 多媒体数据 ID，值大于 0
     */
    public void setId(long id) {
        this.id = id;
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
     * 获取多媒体格式编码
     * <ul>
     *     <li>0：JPEG；</li>
     *     <li>1：TIF；</li>
     *     <li>2：MP3；</li>
     *     <li>3：WAV；</li>
     *     <li>4：WMV；</li>
     *     <li>其他保留</li>
     * </ul>
     * @return BYTE 多媒体格式编码
     * @see #FORMAT_JPEG
     * @see #FORMAT_TIF
     * @see #FORMAT_MP3
     * @see #FORMAT_WAV
     * @see #FORMAT_WMV
     */
    public int getFormat() {
        return format;
    }

    /**
     * 设置多媒体格式编码
     * <ul>
     *     <li>0：JPEG；</li>
     *     <li>1：TIF；</li>
     *     <li>2：MP3；</li>
     *     <li>3：WAV；</li>
     *     <li>4：WMV；</li>
     *     <li>其他保留</li>
     * </ul>
     * @param format BYTE 多媒体格式编码
     * @see #FORMAT_JPEG
     * @see #FORMAT_TIF
     * @see #FORMAT_MP3
     * @see #FORMAT_WAV
     * @see #FORMAT_WMV
     */
    public void setFormat(int format) {
        this.format = format;
    }

    /**
     * 获取事件项编码
     * <ul>
     *     <li>0：平台下发指令；</li>
     *     <li>1：定时动作；</li>
     *     <li>2：抢劫报警触发；</li>
     *     <li>3：碰撞侧翻报警触发；</li>
     *     <li>4：门开拍照；</li>
     *     <li>5：门关拍照；</li>
     *     <li>6：车门由开变关，时速从小于 20 公里到超过 20 公里；</li>
     *     <li>7：定距拍照；</li>
     *     <li>其他保留</li>
     * </ul>
     * @return BYTE 事件项编码
     * @see #EVENT_PLATFORM_COMMAND
     * @see #EVENT_SCHEDULED_ACTION
     * @see #EVENT_ALARM_TRIGGERED_BY_ROBBERY
     * @see #EVENT_ALARM_TRIGGERED_BY_SIDE_ROLLING_CRASH
     * @see #EVENT_PHOTOGRAPH_ON_DOOR_OPENED
     * @see #EVENT_PHOTOGRAPH_ON_DOOR_CLOSED
     * @see #EVENT_DOOR_CLOSED
     * @see #EVENT_PHOTOGRAPH_ON_FIXED_DISTANCE
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
     *     <li>4：门开拍照；</li>
     *     <li>5：门关拍照；</li>
     *     <li>6：车门由开变关，时速从小于 20 公里到超过 20 公里；</li>
     *     <li>7：定距拍照；</li>
     *     <li>其他保留</li>
     * </ul>
     * @param event BYTE 事件项编码
     * @see #EVENT_PLATFORM_COMMAND
     * @see #EVENT_SCHEDULED_ACTION
     * @see #EVENT_ALARM_TRIGGERED_BY_ROBBERY
     * @see #EVENT_ALARM_TRIGGERED_BY_SIDE_ROLLING_CRASH
     * @see #EVENT_PHOTOGRAPH_ON_DOOR_OPENED
     * @see #EVENT_PHOTOGRAPH_ON_DOOR_CLOSED
     * @see #EVENT_DOOR_CLOSED
     * @see #EVENT_PHOTOGRAPH_ON_FIXED_DISTANCE
     */
    public void setEvent(int event) {
        this.event = event;
    }

    /**
     * 获取通道 ID
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


    /** 多媒体类型：图像 */
    public static final int TYPE_IMAGE = 0;
    /** 多媒体类型：音频 */
    public static final int TYPE_AUDIO = 1;
    /** 多媒体类型：视频 */
    public static final int TYPE_VIDEO = 2;

    /** 多媒体格式编码：JPEG */
    public static final int FORMAT_JPEG = 0;
    /** 多媒体格式编码：TIF */
    public static final int FORMAT_TIF = 1;
    /** 多媒体格式编码：MP3 */
    public static final int FORMAT_MP3 = 2;
    /** 多媒体格式编码：WAV */
    public static final int FORMAT_WAV = 3;
    /** 多媒体格式编码：WMV */
    public static final int FORMAT_WMV = 4;

    /** 事件项编码：平台下发指令 */
    public static final int EVENT_PLATFORM_COMMAND = 0;
    /** 事件项编码：定时动作 */
    public static final int EVENT_SCHEDULED_ACTION = 1;
    /** 事件项编码：抢劫报警触发 */
    public static final int EVENT_ALARM_TRIGGERED_BY_ROBBERY = 2;
    /** 事件项编码：碰撞侧翻报警触发 */
    public static final int EVENT_ALARM_TRIGGERED_BY_SIDE_ROLLING_CRASH = 3;
    /** 事件项编码：门开拍照 */
    public static final int EVENT_PHOTOGRAPH_ON_DOOR_OPENED = 4;
    /** 事件项编码：门关拍照 */
    public static final int EVENT_PHOTOGRAPH_ON_DOOR_CLOSED = 5;
    /** 事件项编码：车门由开变关，时速从小于 20 公里到超过 20 公里 */
    public static final int EVENT_DOOR_CLOSED = 6;
    /** 事件项编码：定距拍照 */
    public static final int EVENT_PHOTOGRAPH_ON_FIXED_DISTANCE = 7;

}
