package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;

import java.util.StringJoiner;

/**
 * JT/T 消息体 0x8001 平台通用应答
 *
 * @author togger
 */
public class B8001 extends AbstractToStringJoiner implements Codec {

    /** WORD 应答流水号，对应的终端消息流水号 */
    private int replySn;

    /** WORD 应答 ID，对应的终端消息 ID */
    private int replyId;

    /** {@code BYTE} 结果，0.成功 1.失败 2.消息有误 3.不支持 4.报警处理确认 */
    private int result;

    /** 终端信息，不参与编码解码 */
    private TerminalInfo terminal;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("replySn=" + replySn)
                .add("replyId=" + replyId)
                .add("result=" + result)
                .add("terminal=" + (terminal == null ? "" : terminal))
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
     * 获取应答流水号，对应的终端消息流水号
     * @return WORD 应答流水号，对应的终端消息流水号
     */
    public int getReplySn() {
        return replySn;
    }

    /**
     * 设置应答流水号，对应的终端消息流水号
     * @param replySn WORD 应答流水号，对应的终端消息流水号
     */
    public void setReplySn(int replySn) {
        this.replySn = replySn;
    }

    /**
     * 获取应答 ID，对应的终端消息 ID
     * @return WORD 应答 ID，对应的终端消息 ID
     */
    public int getReplyId() {
        return replyId;
    }

    /**
     * 设置应答 ID，对应的终端消息 ID
     * @param replyId WORD 应答 ID，对应的终端消息 ID
     */
    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    /**
     * 获取结果
     * @return {@code BYTE} 结果，0.成功 1.失败 2.消息有误 3.不支持 4.报警处理确认
     * @see #RESULT_SUCCESSFUL
     * @see #RESULT_FAILED
     * @see #RESULT_WRONG
     * @see #RESULT_UNSUPPORTED
     * @see #RESULT_ALARM_ACK
     */
    public int getResult() {
        return result;
    }

    /**
     * 设置结果
     * @param result {@code BYTE} 结果，0.成功 1.失败 2.消息有误 3.不支持 4.报警处理确认
     * @see #RESULT_SUCCESSFUL
     * @see #RESULT_FAILED
     * @see #RESULT_WRONG
     * @see #RESULT_UNSUPPORTED
     * @see #RESULT_ALARM_ACK
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * 获取终端信息，不参与编码解码
     * @return 终端信息，不参与编码解码
     */
    public TerminalInfo getTerminal() {
        return terminal;
    }

    /**
     * 设置终端信息，不参与编码解码
     * @param terminal 终端信息，不参与编码解码
     */
    public void setTerminal(TerminalInfo terminal) {
        this.terminal = terminal;
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
    /** 结果：报警处理确认 */
    public static final int RESULT_ALARM_ACK = 4;

}
