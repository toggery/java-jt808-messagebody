package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x8202 临时位置跟踪控制
 *
 * @author togger
 */
@Data
public class B8202 {

    /** WORD 时间间隔，单位为秒（s），0 则停止跟踪。停止跟踪无需带后继字段 */
    private int interval;

    /** DWORD 位置跟踪有效期，单位为秒（s），终端在接收到位置跟踪控制消息后，在有效期截止时间之前，依据消息中的时间间隔发送位置汇报 */
    private long duration;

}
