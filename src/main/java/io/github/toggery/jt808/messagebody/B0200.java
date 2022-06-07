package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x0200 位置信息汇报 // 2019 modify
 *
 * @author togger
 */
@Data
public class B0200 {

    /** DWORD 报警标志位，定义见表 25 */
    private long alarmBits;

    /** DWORD 状态位，定义见表 24 */
    private long statusBits;

    /** DWORD 纬度，以度为单位的纬度值乘以 10 的 6 次方，精确到百万分之一度 */
    private long latitude;

    /** DWORD 经度，以度为单位的经度值乘以 10 的 6 次方，精确到百万分之一度 */
    private long longitude;

    /** WORD 高程，海拔高度，单位为米（m） */
    private int altitude;

    /** WORD 速度，1/10km/h */
    private int speed;

    /** WORD 方向，0-359，正北为 0，顺时针 */
    private int direction;

    /** BCD[6] 时间，yyMMddHHmmss（GMT+8 时间，本标准中之后涉及的时间均采用此时区） */
    private String time;


    /** 0x01 DWORD 里程，单位为 0.1km，对应车上里程表读数 */
    private Long x01;
    /** 0x02 WORD 油量，单位为 0.1L，对应车上油量表读数 */
    private Integer x02;
    /** 0x03 WORD 行驶记录功能获取的速度，单位为 0.1km/h */
    private Integer x03;
    /** 0x04 WORD 需要人工确认报警事件的 ID，从 1 开始计数 */
    private Integer x04;

    /** 0x05 BYTE[30] 胎压，单位为 Pa，标定轮子的顺序为从车头开始从左到右顺序排列，定长 30 字节，多余的字节为 0xFF，表示无效数据 // 2019 new */
    private byte[] x05;
    /** 0x06 SHORT 车厢温度，单位为摄氏度，取值范围为 -32767 ~ 32767，最高位为1表示负数 // 2019 new */
    private Short x06;

    /** 0x11 超速报警附加信息，见表 28 */
    private B0200_11 x11;
    /** 0x12 进出区域/路线报警附加信息，见表 29 */
    private B0200_12 x12;
    /** 0x13 路段行驶时间不足/过长报警附加信息，见表 30 */
    private B0200_13 x13;

    /** 0x25 DWORD 扩展车辆信号状态位，参数项格式和定义见表 31 */
    private Long x25;

    /** 0x2A WORD IO 状态位，参数项格式和定义见表 32 */
    private Integer x2A;
    /** 0x2B DWORD 模拟量，bit[0~15]，AD0；bit[l6~31]，AD1 */
    private Long x2B;

    /** 0x30 BYTE 无线通信网络信号强度 */
    private Integer x30;
    /** 0x31 BYTE GNSS定位卫星数 */
    private Integer x31;

}
