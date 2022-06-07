package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x0701 电子运单上报
 *
 * @author togger
 */
@Data
public class B0701 {

    /** 电子运单内容 BYTE[] 电子运单数据包 */
    private byte[] data;

}
