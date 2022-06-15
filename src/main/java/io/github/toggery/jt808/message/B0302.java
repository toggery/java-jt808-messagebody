package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;

import java.util.StringJoiner;

/**
 * JT/T 消息体 0x0302 提问应答 // 2019 del
 *
 * @author togger
 */
public class B0302 extends AbstractToStringJoiner implements Codec {

    /** WORD 应答流水号，对应的提问下发消息的流水号 */
    private int replySn;

    /** BYTE 答案 ID，提问下发中附带的答案 ID */
    private int id;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("replySn=" + replySn)
                .add("id=" + id)
        ;
    }

    @Override
    public void encode(int version, ByteBuf buf) {
        Codec.writeWord(buf, replySn);
        Codec.writeByte(buf, id);
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        replySn = Codec.readWord(buf);
        id = Codec.readByte(buf);
    }


    /**
     * 获取应答流水号，对应的提问下发消息的流水号
     * @return WORD 应答流水号，对应的提问下发消息的流水号
     */
    public int getReplySn() {
        return replySn;
    }

    /**
     * 设置应答流水号，对应的提问下发消息的流水号
     * @param replySn WORD 应答流水号，对应的提问下发消息的流水号
     */
    public void setReplySn(int replySn) {
        this.replySn = replySn;
    }

    /**
     * 获取答案 ID，提问下发中附带的答案 ID
     * @return BYTE 答案 ID，提问下发中附带的答案 ID
     */
    public int getId() {
        return id;
    }

    /**
     * 设置答案 ID，提问下发中附带的答案 ID
     * @param id BYTE 答案 ID，提问下发中附带的答案 ID
     */
    public void setId(int id) {
        this.id = id;
    }

}
