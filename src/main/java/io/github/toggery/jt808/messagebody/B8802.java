package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x8802 存储多媒体数据检索
 *
 * <p>注：不按时间范围则将起始时间/结束时间都设为000000000000。</p>
 *
 * @author togger
 */
@Data
public class B8802 {

    /** BYTE 多媒体类型，0：图像；1：音频；2：视频；*/
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

}
