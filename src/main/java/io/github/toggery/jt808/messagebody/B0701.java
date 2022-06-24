package io.github.toggery.jt808.messagebody;

import java.util.StringJoiner;

/**
 * JT/T 消息体：0x0701 电子运单上报
 *
 * @author togger
 */
public class B0701 extends AbstractToStringJoiner {

    /** BYTE[] 电子运单数据包 */
    private byte[] data;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("data=" + (data == null ? "" : HexUtil.dump(data)))
        ;
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
