package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;

import java.util.StringJoiner;

/**
 * JT/T 消息体 0x0001 终端通用应答
 *
 * @author togger
 */
public class B0001 extends AbstractToStringJoiner implements Codec {

    /** WORD 应答流水号，对应的平台消息流水号 */
    private int replySn;

    /** WORD 应答 ID，对应的平台消息 ID */
    private int replyId;

    /** BYTE 结果，0.成功 1.失败 2.消息有误 3.不支持 */
    private int result;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("replySn=" + replySn)
                .add("replyId=" + replyId)
                .add("result=" + result)
        ;
    }

    @Override
    public void encode(int version, ByteBuf buf) {
        Codec.writeWord(buf, replySn);
        Codec.writeWord(buf, replyId);
        Codec.writeByte(buf, result);
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        replySn = Codec.readWord(buf);
        replyId = Codec.readWord(buf);
        result = Codec.readByte(buf);
    }


    /**
     * 获取应答流水号，对应的平台消息流水号
     * @return WORD 应答流水号，对应的平台消息流水号
     */
    public int getReplySn() {
        return replySn;
    }

    /**
     * 设置应答流水号，对应的平台消息流水号
     * @param replySn WORD 应答流水号，对应的平台消息流水号
     */
    public void setReplySn(int replySn) {
        this.replySn = replySn;
    }

    /**
     * 获取应答 ID，对应的平台消息 ID
     * @return WORD 应答 ID，对应的平台消息 ID
     */
    public int getReplyId() {
        return replyId;
    }

    /**
     * 设置应答 ID，对应的平台消息 ID
     * @param replyId WORD 应答 ID，对应的平台消息 ID
     */
    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    /**
     * 获取结果
     * @return BYTE 结果，0.成功 1.失败 2.消息有误 3.不支持
     * @see #RESULT_SUCCESSFUL
     * @see #RESULT_FAILED
     * @see #RESULT_WRONG
     * @see #RESULT_UNSUPPORTED
     */
    public int getResult() {
        return result;
    }

    /**
     * 设置结果
     * @param result BYTE 结果，0.成功 1.失败 2.消息有误 3.不支持
     * @see #RESULT_SUCCESSFUL
     * @see #RESULT_FAILED
     * @see #RESULT_WRONG
     * @see #RESULT_UNSUPPORTED
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * 获取是否成功
     * @return 是否成功
     */
    public final boolean isSuccessful() {
        return result == RESULT_SUCCESSFUL;
    }

    /** 结果：成功、确认 */
    public static final int RESULT_SUCCESSFUL = 0;
    /** 结果：失败 */
    public static final int RESULT_FAILED = 1;
    /** 结果：消息有误 */
    public static final int RESULT_WRONG = 2;
    /** 结果：不支持 */
    public static final int RESULT_UNSUPPORTED = 3;

}
