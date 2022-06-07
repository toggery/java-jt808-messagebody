package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x8805 单条存储多媒体数据检索上传命令
 *
 * @author togger
 */

@Data
public class B8805 {

    /** DWORD 多媒体 ID，值大于零 */
    private long id;

    /** BYTE 删除标志，0：保留；1：删除；*/
    private int deleted;

}
