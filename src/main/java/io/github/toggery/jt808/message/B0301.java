package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;

import java.util.StringJoiner;

/**
 * JT/T 消息体 0x0301 事件报告 // 2019 del
 *
 * @author togger
 */
public class B0301 extends AbstractToStringJoiner implements Codec {

    /** BYTE 事件 ID */
    private int id;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("id=" + id)
        ;
    }

    /**
     * 获取事件 ID
     * @return BYTE 事件 ID
     */
    public int getId() {
        return id;
    }

    @Override
    public void encode(int version, ByteBuf buf) {
        Codec.writeByte(buf, id);
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        id = Codec.readByte(buf);
    }


    /**
     * 设置事件 ID
     * @param id BYTE 事件 ID
     */
    public void setId(int id) {
        this.id = id;
    }

}
