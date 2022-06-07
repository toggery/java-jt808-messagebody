package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x0A00 终端RSA公钥
 *
 * @author togger
 */
@Data
public class B0A00 {

    /** DWORD RSA 公钥 {e,n} 中的 e */
    private long e;

    /** BYTE[128] RSA 公钥 {e,n} 中的 n */
    private byte[] n;

}
