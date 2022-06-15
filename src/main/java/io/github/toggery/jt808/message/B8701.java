package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

import java.util.StringJoiner;

/**
 * JT/T 消息体 0x8701 行驶记录参数下传命令
 *
 * @author togger
 */
public class B8701 extends AbstractToStringJoiner implements Codec {

    /** BYTE 命令字，见 GB/T 19056 中相关要求 */
    private int command;

    /** BYTE[] 数据块，内容格式见 GB/T 19056 中相关内容，包含 GB/T 19056 要求的完整数据包。*/
    private byte[] data;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("command=" + command)
                .add("data=" + (data == null ? "" : ByteBufUtil.hexDump(data)))
        ;
    }

    @Override
    public void encode(int version, ByteBuf buf) {
        Codec.writeByte(buf, command);
        Codec.writeBytes(buf, data);
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        command = Codec.readByte(buf);
        data = Codec.readBytes(buf);
    }


    /**
     * 获取命令字
     * @return BYTE 命令字，见 GB/T 19056 中相关要求
     */
    public int getCommand() {
        return command;
    }

    /**
     * 设置命令字
     * @param command BYTE 命令字，见 GB/T 19056 中相关要求
     */
    public void setCommand(int command) {
        this.command = command;
    }

    /**
     * 获取数据块
     * @return BYTE[] 数据块，内容格式见 GB/T 19056 中相关内容，包含 GB/T 19056 要求的完整数据包
     */
    public byte[] getData() {
        return data;
    }

    /**
     * 设置数据块
     * @param data BYTE[] 数据块，内容格式见 GB/T 19056 中相关内容，包含 GB/T 19056 要求的完整数据包
     */
    public void setData(byte[] data) {
        this.data = data;
    }

}
