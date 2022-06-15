package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

import java.util.StringJoiner;

/**
 * JT/T 消息体 0x0701 电子运单上报
 *
 * @author togger
 */
public class B0701 extends AbstractToStringJoiner implements Codec {

    /** BYTE[] 电子运单数据包 */
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
     * 获取电子运单数据包
     * @return BYTE[] 电子运单数据包
     */
    public byte[] getData() {
        return data;
    }

    /**
     * 设置电子运单数据包
     * @param data BYTE[] 电子运单数据包
     */
    public void setData(byte[] data) {
        this.data = data;
    }

}
