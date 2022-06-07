package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x0700 行驶记录数据上传（应答）
 *
 * @author togger
 */
@Data
public class B0700 {

    /** WORD 应答流水号，对应的行驶记录数据采集命令消息的流水号 */
    private int replySn;

    /** BYTE 命令字，对应平台发出的命令字 */
    private int command;

    /** BYTE[] 数据块，内容格式见数据块内容格式见 GB/T 19056 中相关内容，包含 GB/T 19056 要求的完整数据包。*/
    private byte[] data;

}
