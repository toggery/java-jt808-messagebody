package io.github.toggery.jt808.messagebody;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * JT/T 消息 0x0802 存储多媒体数据检索应答
 *
 * @author togger
 */
@Data
public class B0802 {

    /** WORD 应答流水号，对应的多媒体数据检索消息的流水号 */
    private int replySn;

    /** 多媒体检索列表 */
    private final List<Media> medias = new ArrayList<>();

    /**
     * 多媒体数据（继承的位置信息附加项无效）
     */
    @Data
    @EqualsAndHashCode(callSuper = true)
    @ToString(callSuper = true)
    public final static class Media extends B0200 {

        /** DWORD 多媒体数据 ID，值大于零 */
        private long id;

        /** BYTE 多媒体类型，0：图像；1：音频；2：视频；*/
        private int type;

        /** BYTE 通道 ID，值大于零 */
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

    }
}
