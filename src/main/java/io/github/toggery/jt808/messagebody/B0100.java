package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 终端注册 // 2019 modify
 *
 * @author togger
 */
@Data
public class B0100 {

    /** WORD 省域ID */
    private int province;

    /** WORD 市县域ID */
    private int city;

    /** BYTE[5 / 11] 制造商ID */
    private String maker;

    /** BYTE[20 / 30] 终端型号 */
    private String model;

    /** BYTE[7 / 30] 终端ID */
    private String id;

    /** BYTE 车牌颜色，0.未上车牌 1.蓝色 2.黄色 3.黑色 4.白色 5.绿色 9.其他 */
    private int plateColor;

    /** STRING 车牌号码 */
    private String plateNo;

}
