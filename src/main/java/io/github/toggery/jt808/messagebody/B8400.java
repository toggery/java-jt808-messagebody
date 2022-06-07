package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x8400 电话回拨
 *
 * @author togger
 */
@Data
public class B8400 {

    /** BYTE 标志，0:普通通话；1:监听 */
    private int type;

    /** STRING 电话号码，最长为 20 字节 */
    private String phone;

}
