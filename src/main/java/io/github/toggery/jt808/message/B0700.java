package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

import java.util.StringJoiner;

/**
 * JT/T 消息体 0x0700 行驶记录数据上传（应答）
 *
 * @author togger
 */
public class B0700 extends AbstractToStringJoiner implements Codec {

    /** WORD 应答流水号，对应的行驶记录数据采集命令消息的流水号 */
    private int replySn;

    /** BYTE 命令字，对应平台发出的命令字 */
    private int command;

    /** BYTE[] 数据块，内容格式见 GB/T 19056 中相关内容，包含 GB/T 19056 要求的完整数据包。*/
    private byte[] data;

    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("replySn=" + replySn)
                .add("command=" + command)
                .add("data=" + (data == null ? "" : ByteBufUtil.hexDump(data)))
        ;
    }

    @Override
    public void encode(int version, ByteBuf buf) {
        Codec.writeWord(buf, replySn);
        Codec.writeByte(buf, command);
        Codec.writeBytes(buf, data);
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        replySn = Codec.readWord(buf);
        command = Codec.readByte(buf);
        data = Codec.readBytes(buf);
    }


    /**
     * 获取应答流水号，对应的行驶记录数据采集命令消息的流水号
     * @return WORD 应答流水号，对应的行驶记录数据采集命令消息的流水号
     */
    public int getReplySn() {
        return replySn;
    }

    /**
     * 设置应答流水号，对应的行驶记录数据采集命令消息的流水号
     * @param replySn WORD 应答流水号，对应的行驶记录数据采集命令消息的流水号
     */
    public void setReplySn(int replySn) {
        this.replySn = replySn;
    }

    /**
     * 获取命令字，对应平台发出的命令字
     * @return BYTE 命令字，对应平台发出的命令字
     */
    public int getCommand() {
        return command;
    }

    /**
     * 设置命令字，对应平台发出的命令字
     * @param command BYTE 命令字，对应平台发出的命令字
     */
    public void setCommand(int command) {
        this.command = command;
    }

    /**
     * 获取数据块
     * @return 数据块，内容格式见 GB/T 19056 中相关内容，包含 GB/T 19056 要求的完整数据包
     */
    public byte[] getData() {
        return data;
    }

    /**
     * 设置数据块
     * @param data 数据块，内容格式见 GB/T 19056 中相关内容，包含 GB/T 19056 要求的完整数据包
     */
    public void setData(byte[] data) {
        this.data = data;
    }

}
