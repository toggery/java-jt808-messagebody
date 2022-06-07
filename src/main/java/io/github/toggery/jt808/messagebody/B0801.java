package io.github.toggery.jt808.messagebody;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * JT/T 消息体 0x0801 多媒体数据上传（继承的位置信息附加项无效） // 2019 modify
 *
 * @author togger
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class B0801 extends B0200 {

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
     *     <li>4：打开车门；//2019 new</li>
     *     <li>5：关闭车门；//2019 new</li>
     *     <li>其他保留</li>
     * </ul>
     */
    private int event;

    /** BYTE 通道 ID */
    private int channel;

    /** BYTE[] 多媒体数据包 */
    private byte[] data;

}
