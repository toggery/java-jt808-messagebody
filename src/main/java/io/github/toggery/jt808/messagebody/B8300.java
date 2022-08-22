package io.github.toggery.jt808.messagebody;

import java.util.StringJoiner;

/**
 * JT/T 消息体：0x8300 文本信息下发 // 2019 modify
 *
 * @author togger
 */
public class B8300 extends AbstractToStringJoiner {

    /**
     * BYTE 标志
     *
     * <ul>
     *     <li>bit0~bit1： 01.服务 10.紧急 11.通知；// 2019 modify</li>
     *     <li>bit2：1.终端显示器显示；</li>
     *     <li>bit3：1.终端 TTS 播读；</li>
     *     <li>bit4：1.广告屏显示；// 2019 del?</li>
     *     <li>bit5：0.中心导航信息，1.CAN 故障码信息；// 2013 new</li>
     *     <li>其他保留</li>
     * </ul>
     */
    private int props;

    /**
     * BYTE 类型 // 2019 new
     * <ul>
     *     <li>1：通知；</li>
     *     <li>2：服务；</li>
     * </ul>
     */
    private int type;

    /** STRING 文本信息，最长为 1024 字节，经 GBK 编码 */
    private String content;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add(HexUtil.byteString("props=", props))
                .add("type=" + type)
                .add("content=" + (content == null ? "" : content))
        ;
    }


    /**
     * 获取标志
     * <ul>
     *     <li>bit0~bit1： 01.服务 10.紧急 11.通知；// 2019 modify</li>
     *     <li>bit2：1.终端显示器显示；</li>
     *     <li>bit3：1.终端 TTS 播读；</li>
     *     <li>bit4：1.广告屏显示；// 2019 del?</li>
     *     <li>bit5：0.中心导航信息，1.CAN 故障码信息；// 2013 new</li>
     *     <li>其他保留</li>
     * </ul>
     * @return BYTE 标志
     */
    public int getProps() {
        return props;
    }

    /**
     * 设置标志
     * <ul>
     *     <li>bit0~bit1： 01.服务 10.紧急 11.通知；// 2019 modify</li>
     *     <li>bit2：1.终端显示器显示；</li>
     *     <li>bit3：1.终端 TTS 播读；</li>
     *     <li>bit4：1.广告屏显示；// 2019 del?</li>
     *     <li>bit5：0.中心导航信息，1.CAN 故障码信息；// 2013 new</li>
     *     <li>其他保留</li>
     * </ul>
     * @param props BYTE 标志
     */
    public void setProps(int props) {
        this.props = props;
    }

    /**
     * 获取类型 // 2019 new
     * <ul>
     *     <li>1：通知；</li>
     *     <li>2：服务；</li>
     * </ul>
     * @return BYTE 类型 // 2019 new
     * @see #TYPE_NOTIFICATION
     * @see #TYPE_SERVICE
     */
    public int getType() {
        return type;
    }

    /**
     * 设置类型 // 2019 new
     * <ul>
     *     <li>1：通知；</li>
     *     <li>2：服务；</li>
     * </ul>
     * @param type BYTE 类型 // 2019 new
     * @see #TYPE_NOTIFICATION
     * @see #TYPE_SERVICE
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取文本信息，最长为 1024 字节
     * @return STRING 文本信息，最长为 1024 字节，经 GBK 编码
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置文本信息，最长为 1024 字节
     * @param content STRING 文本信息，最长为 1024 字节，经 GBK 编码
     */
    public void setContent(String content) {
        this.content = content;
    }


    /** 类型：通知 */
    public static final int TYPE_NOTIFICATION = 1;
    /** 类型：服务 */
    public static final int TYPE_SERVICE = 2;

}
