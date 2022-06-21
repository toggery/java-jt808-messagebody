package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;

import java.util.StringJoiner;

/**
 * JT/T 消息体 0x0104 查询终端参数应答
 *
 * @author togger
 */
public class B0104 extends B8103 {

    /** WORD 应答流水号 */
    private int replySn;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add(IntUtil.wordHexString("replySn=", replySn))
        ;
        super.toStringJoiner(joiner);
    }

    @Override
    public void encode(int version, ByteBuf buf) {
        Codec.writeWord(buf, replySn);

        super.encode(version, buf);
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        replySn = Codec.readWord(buf);

        super.decode(version, buf);
    }


    /**
     * 获取应答流水号
     * @return WORD 应答流水号
     */
    public int getReplySn() {
        return replySn;
    }

    /**
     * 设置应答流水号
     * @param replySn WORD 应答流水号
     */
    public void setReplySn(int replySn) {
        this.replySn = replySn;
    }

}
