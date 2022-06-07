package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 位置扩展信息 0x12 进出区域/路线报警附加信息，见表29
 *
 * @author togger
 */
@Data
public class B0200_12 {

    /** BYTE 位置类型，1.圆形区域 2.矩形区域 3.多边形区域 4.路线 */
    private int type;

    /** DWORD 区域或路段ID */
    private long id;

    /** BYTE 方向，0.进 1.出 */
    private int direction;

    /** 圆形区域 */
    public static final int CIRCULAR_REGION = 1;
    /** 矩形区域 */
    public static final int RECTANGULAR_REGION = 2;
    /** 多边形区域 */
    public static final int POLYGONAL_REGION = 3;
    /** 路线 */
    public static final int ROUTE = 4;
}
