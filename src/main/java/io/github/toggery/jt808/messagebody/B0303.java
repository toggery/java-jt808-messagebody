package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x0303 信息点播/取消 // 2019 del
 *
 * @author togger
 */
@Data
public class B0303 {

    /** BYTE 信息类型 */
    private int type;

    /** BYTE 点播/取消标志，0：取消；1：点播 */
    private int action;

}
