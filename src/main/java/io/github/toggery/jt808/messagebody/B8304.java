package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x8304 信息服务 // 2019 del
 *
 * @author togger
 */
@Data
public class B8304 {

    /** BYTE 信息类型 */
    private int type;

    /** STRING 信息内容 */
    private String content;

}
