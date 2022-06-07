package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x8700 行驶记录数据采集命令
 *
 * @author togger
 */
@Data
public class B8700 {

    /** BYTE 命令字，见 GB/T 19056 中相关要求 */
    private int command;

    /** BYTE[] 数据块，内容格式见 GB/T 19056 中相关内容，包含 GB/T 19056 要求的完整数据包，可为空。*/
    private byte[] data;

}
