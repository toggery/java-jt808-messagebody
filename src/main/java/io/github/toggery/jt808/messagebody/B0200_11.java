package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 位置扩展信息 0x11 超速报警附加信息，见表28
 *
 * @author togger
 */
@Data
public class B0200_11 {

    /** BYTE 位置类型，0.无特定位置 1.圆形区域 2.矩形区域 3.多边形区域 4.路段 */
    private int type;

    /** DWORD 区域或路段ID，若位置类型为 0 则无该字段 */
    private long id;

    /** 无特定位置 */
    public static final int NONE = 0;
    /** 圆形区域 */
    public static final int CIRCULAR_REGION = 1;
    /** 矩形区域 */
    public static final int RECTANGULAR_REGION = 2;
    /** 多边形区域 */
    public static final int POLYGONAL_REGION = 3;
    /** 路段 */
    public static final int SEGMENT = 4;

}
