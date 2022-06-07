package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x0901 数据压缩上报
 *
 * @author togger
 */
@Data
public class B0901 {

    /** BYTE[] 压缩消息体 GZIP */
    private byte[] data;

}
