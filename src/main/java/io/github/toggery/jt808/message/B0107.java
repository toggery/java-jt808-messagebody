package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;

import java.util.StringJoiner;

/**
 * JT/T 消息体 0x0107 查询终端属性应答 // 2019 modify
 *
 * @author togger
 */
public class B0107 extends AbstractToStringJoiner implements Codec {

    /**
     * WORD 终端类型
     *
     * <ul>
     *     <li>bit0，0：不适用客运车辆，1：适用客运车辆；</li>
     *     <li>bit1，0：不适用危险品车辆，1：适用危险品车辆；</li>
     *     <li>bit2，0：不适用普通货运车辆，1：适用普通货运车辆；</li>
     *     <li>bit3，0：不适用出租车辆，1：适用出租车辆；</li>
     *     <li>bit6，0：不支持硬盘录像，1：支持硬盘录像；</li>
     *     <li>bit7，0：一体机，1：分体机；</li>
     *     <li>bit8，0：不适用挂车，1：适用挂车；</li>
     * </ul>
     *
     */
    private int type;

    /** BYTE[5] 制造商 ID */
    private String maker;

    /** BYTE[20 / 30] 终端型号 */
    private String model;

    /** BYTE[7 / 30] 终端 ID */
    private String id;

    /** BCD[10] 终端 SIM 卡 ICCID */
    private String simId;

    /** STRING 终端硬件版本号 */
    private String hw;

    /** STRING 终端固件版本号 */
    private String fm;

    /**
     * BYTE GNSS 模块属性
     *
     * <ul>
     *     <li>bit0，0：不支持 GPS 定位，1：支持 GPS 定位；</li>
     *     <li>bit1，0：不支持北斗定位，1：支持北斗定位；</li>
     *     <li>bit2，0：不支持 GLONASS 定位，1：支持 GLONASS 定位；</li>
     *     <li>bit3，0：不支持 Galileo 定位，1：支持 Galileo 定位；</li>
     * </ul>
     */
    private int gnss;

    /**
     * BYTE 通信模块属性
     *
     * <ul>
     *     <li>bit0，0：不支持GPRS通信，1：支持GPRS通信；</li>
     *     <li>bit1，0：不支持CDMA通信，1：支持CDMA通信；</li>
     *     <li>bit2，0：不支持TD-SCDMA通信，1：支持TD-SCDMA通信；</li>
     *     <li>bit3，0：不支持WCDMA通信，1：支持WCDMA通信；</li>
     *     <li>bit4，0：不支持CDMA2000通信，1：支持CDMA2000通信。</li>
     *     <li>bit5，0：不支持TD-LTE通信，1：支持TD-LTE通信；</li>
     *     <li>bit7，0：不支持其他通信方式，1：支持其他通信方式；</li>
     * </ul>
     */
    private int comm;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add(IntUtil.wordHexString("type=", type))
                .add("maker=" + (maker == null ? "" : maker))
                .add("model=" + (model == null ? "" : model))
                .add("id=" + (id == null ? "" : id))
                .add("simId=" + (simId == null ? "" : simId))
                .add("hw=" + (hw == null ? "" : hw))
                .add("fm=" + (fm == null ? "" : fm))
                .add(IntUtil.byteHexString("gnss=", gnss))
                .add(IntUtil.byteHexString("comm=", comm))
        ;
    }

    @Override
    public void encode(int version, ByteBuf buf) {
        Codec.writeWord(buf, type);
        // 后补 0x00 （左对齐）
        Codec.writeChars(buf, maker, -5, PadChar.NUL);
        Codec.writeChars(buf, model, version > 0 ? -30 : -20, PadChar.NUL);
        Codec.writeChars(buf, id, version > 0 ? -30 : -7, PadChar.NUL);
        Codec.writeBcd(buf, simId, 10);
        Codec.writeString(buf, IntUnit.BYTE, hw);
        Codec.writeString(buf, IntUnit.BYTE, fm);
        Codec.writeByte(buf, gnss);
        Codec.writeByte(buf, comm);
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        type = Codec.readWord(buf);
        maker = Codec.readChars(buf, 5);
        model = Codec.readChars(buf, version > 0 ? 30 : 20);
        id = Codec.readChars(buf, version > 0 ? 30 : 7);
        simId = Codec.readBcd(buf, 10, true);
        hw = Codec.readString(buf, IntUnit.BYTE);
        fm = Codec.readString(buf, IntUnit.BYTE);
        gnss = Codec.readByte(buf);
        comm = Codec.readByte(buf);
    }


    /**
     * 获取终端类型
     * <ul>
     *     <li>bit0，0：不适用客运车辆，1：适用客运车辆；</li>
     *     <li>bit1，0：不适用危险品车辆，1：适用危险品车辆；</li>
     *     <li>bit2，0：不适用普通货运车辆，1：适用普通货运车辆；</li>
     *     <li>bit3，0：不适用出租车辆，1：适用出租车辆；</li>
     *     <li>bit6，0：不支持硬盘录像，1：支持硬盘录像；</li>
     *     <li>bit7，0：一体机，1：分体机；</li>
     *     <li>bit8，0：不适用挂车，1：适用挂车；</li>
     * </ul>
     * @return WORD 终端类型
     *
     */
    public int getType() {
        return type;
    }

    /**
     * 设置终端类型
     * <ul>
     *     <li>bit0，0：不适用客运车辆，1：适用客运车辆；</li>
     *     <li>bit1，0：不适用危险品车辆，1：适用危险品车辆；</li>
     *     <li>bit2，0：不适用普通货运车辆，1：适用普通货运车辆；</li>
     *     <li>bit3，0：不适用出租车辆，1：适用出租车辆；</li>
     *     <li>bit6，0：不支持硬盘录像，1：支持硬盘录像；</li>
     *     <li>bit7，0：一体机，1：分体机；</li>
     *     <li>bit8，0：不适用挂车，1：适用挂车；</li>
     * </ul>
     * @param type WORD 终端类型
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
     * 获取终端型号
     * @return BYTE[20 / 30] 终端型号
     */
    public String getModel() {
        return model;
    }

    /**
     * 设置终端型号
     * @param model BYTE[20 / 30] 终端型号
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * 获取终端 ID
     * @return BYTE[7 / 30] 终端 ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置终端 ID
     * @param id BYTE[7 / 30] 终端 ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取终端 SIM 卡 ICCID
     * @return BCD[10] 终端 SIM 卡 ICCID
     */
    public String getSimId() {
        return simId;
    }

    /**
     * 设置终端 SIM 卡 ICCID
     * @param simId BCD[10] 终端 SIM 卡 ICCID
     */
    public void setSimId(String simId) {
        this.simId = simId;
    }

    /**
     * 获取终端硬件版本号
     * @return STRING 终端硬件版本号
     */
    public String getHw() {
        return hw;
    }

    /**
     * 设置终端硬件版本号
     * @param hw STRING 终端硬件版本号
     */
    public void setHw(String hw) {
        this.hw = hw;
    }

    /**
     * 获取终端固件版本号
     * @return STRING 终端固件版本号
     */
    public String getFm() {
        return fm;
    }

    /**
     * 设置终端固件版本号
     * @param fm STRING 终端固件版本号
     */
    public void setFm(String fm) {
        this.fm = fm;
    }

    /**
     * 获取 GNSS 模块属性
     * <ul>
     *     <li>bit0，0：不支持 GPS 定位，1：支持 GPS 定位；</li>
     *     <li>bit1，0：不支持北斗定位，1：支持北斗定位；</li>
     *     <li>bit2，0：不支持 GLONASS 定位，1：支持 GLONASS 定位；</li>
     *     <li>bit3，0：不支持 Galileo 定位，1：支持 Galileo 定位；</li>
     * </ul>
     * @return BYTE GNSS 模块属性
     */
    public int getGnss() {
        return gnss;
    }

    /**
     * 设置 GNSS 模块属性
     * <ul>
     *     <li>bit0，0：不支持 GPS 定位，1：支持 GPS 定位；</li>
     *     <li>bit1，0：不支持北斗定位，1：支持北斗定位；</li>
     *     <li>bit2，0：不支持 GLONASS 定位，1：支持 GLONASS 定位；</li>
     *     <li>bit3，0：不支持 Galileo 定位，1：支持 Galileo 定位；</li>
     * </ul>
     * @param gnss BYTE GNSS 模块属性
     */
    public void setGnss(int gnss) {
        this.gnss = gnss;
    }

    /**
     * 获取通信模块属性
     *
     * <ul>
     *     <li>bit0，0：不支持GPRS通信，1：支持GPRS通信；</li>
     *     <li>bit1，0：不支持CDMA通信，1：支持CDMA通信；</li>
     *     <li>bit2，0：不支持TD-SCDMA通信，1：支持TD-SCDMA通信；</li>
     *     <li>bit3，0：不支持WCDMA通信，1：支持WCDMA通信；</li>
     *     <li>bit4，0：不支持CDMA2000通信，1：支持CDMA2000通信。</li>
     *     <li>bit5，0：不支持TD-LTE通信，1：支持TD-LTE通信；</li>
     *     <li>bit7，0：不支持其他通信方式，1：支持其他通信方式；</li>
     * </ul>
     * @return 通信模块属性
     */
    public int getComm() {
        return comm;
    }

    /**
     * 设置通信模块属性
     *
     * <ul>
     *     <li>bit0，0：不支持GPRS通信，1：支持GPRS通信；</li>
     *     <li>bit1，0：不支持CDMA通信，1：支持CDMA通信；</li>
     *     <li>bit2，0：不支持TD-SCDMA通信，1：支持TD-SCDMA通信；</li>
     *     <li>bit3，0：不支持WCDMA通信，1：支持WCDMA通信；</li>
     *     <li>bit4，0：不支持CDMA2000通信，1：支持CDMA2000通信。</li>
     *     <li>bit5，0：不支持TD-LTE通信，1：支持TD-LTE通信；</li>
     *     <li>bit7，0：不支持其他通信方式，1：支持其他通信方式；</li>
     * </ul>
     * @param comm BYTE 通信模块属性
     */
    public void setComm(int comm) {
        this.comm = comm;
    }

}
