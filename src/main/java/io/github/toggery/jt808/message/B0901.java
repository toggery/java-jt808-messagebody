package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

import java.util.StringJoiner;

/**
 * JT/T 消息体 0x0901 数据压缩上报
 *
 * @author togger
 */
public class B0901 extends AbstractToStringJoiner implements Codec {

    /** BYTE[] 压缩消息体，GZIP */
    private byte[] data;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("data=" + (data == null ? "" : ByteBufUtil.hexDump(data)))
        ;
    }

    @Override
    public void encode(int version, ByteBuf buf) {
        Codec.writeBytes(buf, IntUnit.DWORD, data);
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        data = Codec.readBytes(buf, IntUnit.DWORD);
    }


    /**
     * 获取压缩消息体，GZIP
     * @return BYTE[] 压缩消息体，GZIP
     */
    public byte[] getData() {
        return data;
    }

    /**
     * 设置压缩消息体，GZIP
     * @param data BYTE[] 压缩消息体，GZIP
     */
    public void setData(byte[] data) {
        this.data = data;
    }

}
