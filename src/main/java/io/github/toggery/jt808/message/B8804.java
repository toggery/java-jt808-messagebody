package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;

import java.util.StringJoiner;

/**
 * JT/T 消息体 0x8804 录音开始命令
 *
 * @author togger
 */
public class B8804 extends AbstractToStringJoiner implements Codec {

    /**
     * BYTE 录音命令
     * <ul>
     *     <li>0：停止录音；</li>
     *     <li>0x01：开始录音</li>
     * </ul>
     */
    private int command;

    /** WORD 录音时间，单位为秒（s），0 表示一直录音 */
    private int duration;

    /**
     * BYTE 保存标志
     * <ul>
     *     <li>0：实时上传；</li>
     *     <li>1：保存；</li>
     * </ul>
     */
    private int action;

    /**
     * BYTE 音频采样率
     * <ul>
     *     <li>0：8K；</li>
     *     <li>1：11K；</li>
     *     <li>2：23K；</li>
     *     <li>3：32K；</li>
     *     <li>其他保留</li>
     * </ul>
     */
    private int sampling;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("command=" + command)
                .add("duration=" + duration)
                .add("action=" + action)
                .add("sampling=" + sampling)
        ;
    }

    @Override
    public void encode(int version, ByteBuf buf) {
        Codec.writeByte(buf, command);
        Codec.writeWord(buf, duration);
        Codec.writeByte(buf, action);
        Codec.writeByte(buf, sampling);
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        command = Codec.readByte(buf);
        duration = Codec.readWord(buf);
        action = Codec.readByte(buf);
        sampling = Codec.readByte(buf);
    }


    /**
     * 获取录音命令
     * <ul>
     *     <li>0：停止录音；</li>
     *     <li>0x01：开始录音</li>
     * </ul>
     * @return BYTE 录音命令
     * @see #COMMAND_STOP
     * @see #COMMAND_SART
     */
    public int getCommand() {
        return command;
    }

    /**
     * 设置录音命令
     * <ul>
     *     <li>0：停止录音；</li>
     *     <li>0x01：开始录音</li>
     * </ul>
     * @param command BYTE 录音命令
     * @see #COMMAND_STOP
     * @see #COMMAND_SART
     */
    public void setCommand(int command) {
        this.command = command;
    }

    /**
     * 获取录音时间，单位为秒（s）
     * @return WORD 录音时间，单位为秒（s），0 表示一直录音
     */
    public int getDuration() {
        return duration;
    }

    /**
     * 设置录音时间，单位为秒（s）
     * @param duration WORD 录音时间，单位为秒（s），0 表示一直录音
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * 获取保存标志
     * <ul>
     *     <li>0：实时上传；</li>
     *     <li>1：保存；</li>
     * </ul>
     * @return BYTE 保存标志
     * @see #ACTION_UPLOAD
     * @see #ACTION_SAVE
     */
    public int getAction() {
        return action;
    }

    /**
     * 设置保存标志
     * <ul>
     *     <li>0：实时上传；</li>
     *     <li>1：保存；</li>
     * </ul>
     * @param action BYTE 保存标志
     * @see #ACTION_UPLOAD
     * @see #ACTION_SAVE
     */
    public void setAction(int action) {
        this.action = action;
    }

    /**
     * 获取音频采样率
     * <ul>
     *     <li>0：8K；</li>
     *     <li>1：11K；</li>
     *     <li>2：23K；</li>
     *     <li>3：32K；</li>
     *     <li>其他保留</li>
     * </ul>
     * @return BYTE 音频采样率
     * @see #SAMPLING_8K
     * @see #SAMPLING_11K
     * @see #SAMPLING_23K
     * @see #SAMPLING_32K
     */
    public int getSampling() {
        return sampling;
    }

    /**
     * 设置音频采样率
     * <ul>
     *     <li>0：8K；</li>
     *     <li>1：11K；</li>
     *     <li>2：23K；</li>
     *     <li>3：32K；</li>
     *     <li>其他保留</li>
     * </ul>
     * @param sampling BYTE 音频采样率
     * @see #SAMPLING_8K
     * @see #SAMPLING_11K
     * @see #SAMPLING_23K
     * @see #SAMPLING_32K
     */
    public void setSampling(int sampling) {
        this.sampling = sampling;
    }

    /** 录音命令：停止录音 */
    public static final int COMMAND_STOP = 0;
    /** 录音命令：开始录音 */
    public static final int COMMAND_SART = 0x01;

    /** 保存标志：实时上传 */
    public static final int ACTION_UPLOAD = 0;
    /** 保存标志：保存 */
    public static final int ACTION_SAVE = 1;

    /** 音频采样率：8K */
    public static final int SAMPLING_8K = 0;
    /** 音频采样率：11K */
    public static final int SAMPLING_11K = 1;
    /** 音频采样率：23K */
    public static final int SAMPLING_23K = 2;
    /** 音频采样率：32K */
    public static final int SAMPLING_32K = 3;

}
