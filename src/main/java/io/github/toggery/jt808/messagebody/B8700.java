package io.github.toggery.jt808.messagebody;

import java.util.StringJoiner;

/**
 * JT/T 消息体：0x8700 行驶记录数据采集命令
 *
 * @author togger
 */
public class B8700 extends AbstractToStringJoiner {

    /** BYTE 命令字，见 GB/T 19056 中相关要求 */
    private int command;

    /** BYTE[] 数据块，内容格式见 GB/T 19056 中相关内容，包含 GB/T 19056 要求的完整数据包，可为空。*/
    private byte[] data;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add(HexUtil.byteString("command=", command))
                .add("data=" + (data == null ? "" : HexUtil.dump(data)))
        ;
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
     * @return BYTE[] 数据块，内容格式见 GB/T 19056 中相关内容，包含 GB/T 19056 要求的完整数据包，可为空
     */
    public byte[] getData() {
        return data;
    }

    /**
     * 设置数据块
     * @param data BYTE[] 数据块，内容格式见 GB/T 19056 中相关内容，包含 GB/T 19056 要求的完整数据包，可为空
     */
    public void setData(byte[] data) {
        this.data = data;
    }

}
