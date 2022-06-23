package io.github.toggery.jt808.messagebody;

import java.util.StringJoiner;

/**
 * JT/T 消息体 0x8900 数据下行透传
 *
 * @author togger
 */
public class B8900 extends AbstractToStringJoiner {

    /**
     * BYTE 透传消息类型
     *
     *  <ul>
     *      <li>0x00：GNSS 模块详细定位数据；</li>
     *      <li>0x0B：道路运输证 IC 卡信息（上传消息为 64Byte，下传消息为24Byte，道路运输证 IC 卡认证透传超时时间为 30s，超时后不重发）；</li>
     *      <li>0x41：串口 1 透传消息；</li>
     *      <li>0x42：串口 2 透传消息；</li>
     *      <li>0xF0-0xFF：用户自定义透传消息；</li>
     *  </ul>
     */
    private int type;

    /** BYTE[] 透传消息内容 */
    private byte[] data;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add(HexUtil.byteString("type=", type))
                .add("data=" + (data == null ? "" : HexUtil.dump(data)))
        ;
    }


    /**
     * 获取透传消息类型
     *  <ul>
     *      <li>0x00：GNSS 模块详细定位数据；</li>
     *      <li>0x0B：道路运输证 IC 卡信息（上传消息为 64Byte，下传消息为24Byte，道路运输证 IC 卡认证透传超时时间为 30s，超时后不重发）；</li>
     *      <li>0x41：串口 1 透传消息；</li>
     *      <li>0x42：串口 2 透传消息；</li>
     *      <li>0xF0-0xFF：用户自定义透传消息；</li>
     *  </ul>
     * @return BYTE 透传消息类型
     * @see #TYPE_GNSS_DATA
     * @see #TYPE_RTC_IC_CARD_DATA
     * @see #TYPE_COMM1_DATA
     * @see #TYPE_COMM2_DATA
     */
    public int getType() {
        return type;
    }

    /**
     * 设置透传消息类型
     *  <ul>
     *      <li>0x00：GNSS 模块详细定位数据；</li>
     *      <li>0x0B：道路运输证 IC 卡信息（上传消息为 64Byte，下传消息为24Byte，道路运输证 IC 卡认证透传超时时间为 30s，超时后不重发）；</li>
     *      <li>0x41：串口 1 透传消息；</li>
     *      <li>0x42：串口 2 透传消息；</li>
     *      <li>0xF0-0xFF：用户自定义透传消息；</li>
     *  </ul>
     * @param type BYTE 透传消息类型
     * @see #TYPE_GNSS_DATA
     * @see #TYPE_RTC_IC_CARD_DATA
     * @see #TYPE_COMM1_DATA
     * @see #TYPE_COMM2_DATA
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取透传消息内容
     * @return BYTE[] 透传消息内容
     */
    public byte[] getData() {
        return data;
    }

    /**
     * 设置透传消息内容
     * @param data BYTE[] 透传消息内容
     */
    public void setData(byte[] data) {
        this.data = data;
    }


    /** 透传消息类型：GNSS 模块详细定位数据 */
    public static final int TYPE_GNSS_DATA = 0x00;
    /** 透传消息类型：道路运输证 IC 卡信息 */
    public static final int TYPE_RTC_IC_CARD_DATA = 0x0B;
    /** 透传消息类型：串口 1 透传消息 */
    public static final int TYPE_COMM1_DATA = 0x41;
    /** 透传消息类型：串口 2 透传消息 */
    public static final int TYPE_COMM2_DATA = 0x42;

}
