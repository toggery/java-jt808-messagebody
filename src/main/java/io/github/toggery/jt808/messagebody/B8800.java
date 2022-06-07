package io.github.toggery.jt808.messagebody;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * JT/T 消息体 0x8800 多媒体数据上传应答
 *
 * @author togger
 */
@Data
public class B8800 {

    /** DWORD 多媒体数据 ID，值大于零 */
    private long id;

    /** WORD 重传包序号列表 */
    private final List<Integer> subpackageSns = new ArrayList<>();

}
