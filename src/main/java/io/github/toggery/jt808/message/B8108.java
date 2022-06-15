package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

import java.util.StringJoiner;

/**
 * JT/T 消息体 0x8108 下发终端升级包
 *
 * @author togger
 */
public class B8108 extends AbstractToStringJoiner implements Codec {

    /**
     * BYTE 升级类型
     * <ul>
     *     <li>0.终端</li>
     *     <li>12.道路运输证IC卡读卡器</li>
     *     <li>52.北斗卫星定位模块</li>
     * </ul>
     */
    private int type;

    /** BYTE[5] 制造商 ID */
    private String maker;

    /** STRING 版本号 */
    private String version;

    /** BYTE[] 升级数据包 */
    private byte[] data;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("type=" + type)
                .add("maker=" + (maker == null ? "" : maker))
                .add("version=" + (version == null ? "" : version))
                .add("data=" + (data == null ? "" : ByteBufUtil.hexDump(data)))
        ;
    }

    @Override
    public void encode(int version, ByteBuf buf) {
        Codec.writeByte(buf, type);
        // 后补 0x00（左对齐）
        Codec.writeChars(buf, maker, -5, PadChar.NUL);
        Codec.writeString(buf, IntUnit.BYTE, this.version);
        Codec.writeBytes(buf, IntUnit.DWORD, data);
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        type = Codec.readByte(buf);
        maker = Codec.readChars(buf, 5);
        this.version = Codec.readString(buf, IntUnit.BYTE);
        data = Codec.readBytes(buf, IntUnit.DWORD);
    }


    /**
     * 获取升级类型
     * <ul>
     *     <li>0.终端</li>
     *     <li>12.道路运输证IC卡读卡器</li>
     *     <li>52.北斗卫星定位模块</li>
     * </ul>
     * @return BYTE 升级类型
     * @see #TYPE_TERMINAL
     * @see #TYPE_RTC_IC_CARD_READER
     * @see #TYPE_BEIDOU_POSITIONING_MODULE
     */
    public int getType() {
        return type;
    }

    /**
     * 设置升级类型
     * <ul>
     *     <li>0.终端</li>
     *     <li>12.道路运输证IC卡读卡器</li>
     *     <li>52.北斗卫星定位模块</li>
     * </ul>
     * @param type BYTE 升级类型
     * @see #TYPE_TERMINAL
     * @see #TYPE_RTC_IC_CARD_READER
     * @see #TYPE_BEIDOU_POSITIONING_MODULE
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取制造商 ID
     * @return BYTE[5] 制造商 ID
     */
    public String getMaker() {
        return maker;
    }

    /**
     * 设置制造商 ID
     * @param maker BYTE[5] 制造商 ID
     */
    public void setMaker(String maker) {
        this.maker = maker;
    }

    /**
     * 获取版本号
     * @return STRING 版本号
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置版本号
     * @param version STRING 版本号
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 获取升级数据包
     * @return BYTE[] 升级数据包
     */
    public byte[] getData() {
        return data;
    }

    /**
     * 设置升级数据包
     * @param data BYTE[] 升级数据包
     */
    public void setData(byte[] data) {
        this.data = data;
    }

    /** 升级类型：终端 */
    public static final int TYPE_TERMINAL = 0;
    /** 升级类型：道路运输证 IC 卡读卡器 */
    public static final int TYPE_RTC_IC_CARD_READER = 12;
    /** 升级类型：北斗卫星定位模块 */
    public static final int TYPE_BEIDOU_POSITIONING_MODULE = 52;

}
