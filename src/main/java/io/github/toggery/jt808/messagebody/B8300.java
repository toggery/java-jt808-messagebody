package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x8300 文本信息下发 // 2019 modify
 *
 * @author togger
 */
@Data
public class B8300 {

    /**
     * BYTE 标志
     *
     * <ul>
     *     <li>bit0~1: 01.服务 10.紧急 11.通知 //2019 modify</li>
     *     <li>bit2: 1.终端显示器显示</li>
     *     <li>bit3: 1.终端 TTS 播读</li>
     *     <li>bit4: 1.广告屏显示 //2019 del?</li>
     *     <li>bit5: 0.中心导航信息，1.CAN 故障码信息 //2013 new</li>
     *     <li>其他: 保留</li>
     * </ul>
     */
    private int flags;

    /** BYTE 类型，1.通知 2.服务 // 2019 new */
    private int type;

    /** STRING 文本信息，最长为 1024 字节，经 GBK 编码 */
    private String content;

}
