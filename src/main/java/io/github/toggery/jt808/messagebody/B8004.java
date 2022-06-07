package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x8004 查询服务器时间应答 // 2019 new
 *
 * @author togger
 */
@Data
public class B8004 {

    /** BCD[6] 服务器 UTC 时间，形如 yyMMddHHmmss */
    private String time;

}
