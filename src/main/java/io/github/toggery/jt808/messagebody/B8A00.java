package io.github.toggery.jt808.messagebody;

import java.util.StringJoiner;

/**
 * JT/T 消息体：0x8A00 平台 RSA 公钥
 *
 * @author togger
 */
public class B8A00 extends AbstractToStringJoiner {

    /** DWORD RSA 公钥 {e,n} 中的 e */
    private long e;

    /** BYTE[128] RSA 公钥 {e,n} 中的 n */
    private byte[] n;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add(HexUtil.doubleWordString("e=", e))
                .add("n=" + (n == null ? "" : HexUtil.dump(n)))
        ;
    }


    /**
     * 获取 RSA 公钥 {e,n} 中的 e
     * @return DWORD RSA 公钥 {e,n} 中的 e
     */
    public long getE() {
        return e;
    }

    /**
     * 设置 RSA 公钥 {e,n} 中的 e
     * @param e DWORD RSA 公钥 {e,n} 中的 e
     */
    public void setE(long e) {
        this.e = e;
    }

    /**
     * 获取 RSA 公钥 {e,n} 中的 n
     * @return BYTE[128] RSA 公钥 {e,n} 中的 n
     */
    public byte[] getN() {
        return n;
    }

    /**
     * 设置 RSA 公钥 {e,n} 中的 n
     * @param n BYTE[128] RSA 公钥 {e,n} 中的 n
     */
    public void setN(byte[] n) {
        this.n = n;
    }

}