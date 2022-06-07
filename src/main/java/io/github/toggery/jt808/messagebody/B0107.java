package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x0107 查询终端属性应答 // 2019 modify
 *
 * @author togger
 */
@Data
public class B0107 {

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
    private String hwVersion;

    /** STRING 终端固件版本号 */
    private String fmVersion;

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

}
