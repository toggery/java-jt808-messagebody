package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x0800 多媒体事件信息上传
 *
 * @author togger
 */
@Data
public class B0800 {

    /** DWORD 多媒体数据 ID，值大于零 */
    private long id;

    /** BYTE 多媒体类型，0：图像；1：音频；2：视频；*/
    private int type;

    /** BYTE 多媒体格式编码，0：JPEG；1：TIF；2：MP3；3：WAV；4：WMV；其他保留 */
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
     *     <li>6：车门由开变关，时速从小于20公里到超过20公里；</li>
     *     <li>7：定距拍照；</li>
     *     <li>其他保留</li>
     * </ul>
     */
    private int event;

    /** BYTE 通道 ID */
    private int channel;

}
