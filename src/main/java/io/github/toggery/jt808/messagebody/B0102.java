package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x0102 终端鉴权 // 2019 modify
 *
 * @author togger
 */
@Data
public class B0102 {

    /** STRING 鉴权码 */
    private String token;

    /** BYTE[15] 终端IMEI // 2019 new */
    private String imei;

    /** BYTE[20] 软件版本号 // 2019 new */
    private String version;

}
