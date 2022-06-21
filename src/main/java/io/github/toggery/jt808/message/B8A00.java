package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

import java.util.StringJoiner;

/**
 * JT/T 消息体 0x8A00 平台 RSA 公钥
 *
 * @author togger
 */
public class B8A00 extends AbstractToStringJoiner implements Codec {

    /** DWORD RSA 公钥 {e,n} 中的 e */
    private long e;

    /** BYTE[128] RSA 公钥 {e,n} 中的 n */
    private byte[] n;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add(IntUtil.doubleWordHexString("e=", e))
                .add("n=" + (n == null ? "" : ByteBufUtil.hexDump(n)))
        ;
    }

    @Override
    public void encode(int version, ByteBuf buf) {
        Codec.writeDoubleWord(buf, e);
        Codec.writeBytes(buf, n);
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        e =Codec.readDoubleWord(buf);
        n = Codec.readBytes(buf);
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