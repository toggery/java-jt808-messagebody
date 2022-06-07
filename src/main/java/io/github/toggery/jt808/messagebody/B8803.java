package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x8803 存储多媒体数据上传
 *
 * @author togger
 */
@Data
public class B8803 {

    /** BYTE 多媒体类型，0：图像；1：音频；2：视频；*/
    private int type;

    /** BYTE 通道 ID */
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

    /** BYTE 删除标志，0：保留；1：删除；*/
    private int deleted;

}
