package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;

import java.util.StringJoiner;

/**
 * JT/T 消息体 位置扩展信息 0x12 进出区域/路线报警附加信息，见表29
 *
 * @author togger
 */
public class B0200_12 extends AbstractToStringJoiner implements Codec {

    /** BYTE 位置类型，1.圆形区域 2.矩形区域 3.多边形区域 4.路线 */
    private int type;

    /** DWORD 区域或路段 ID */
    private long id;

    /** BYTE 方向，0.进 1.出 */
    private int direction;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("type=" + type)
                .add("id=" + id)
                .add("direction=" + direction)
        ;
    }

    @Override
    public void encode(int version, ByteBuf buf) {
        Codec.writeByte(buf, type);
        Codec.writeDoubleWord(buf, id);
        Codec.writeByte(buf, direction);
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        type = Codec.readByte(buf);
        id = Codec.readDoubleWord(buf);
        direction = Codec.readByte(buf);
    }


    /**
     * 获取位置类型
     * @return BYTE 位置类型，1.圆形区域 2.矩形区域 3.多边形区域 4.路线
     * @see #TYPE_CIRCULAR_REGION
     * @see #TYPE_RECTANGULAR_REGION
     * @see #TYPE_POLYGONAL_REGION
     * @see #TYPE_ROUTE
     */
    public int getType() {
        return type;
    }

    /**
     * 设置位置类型
     * @param type BYTE 位置类型，1.圆形区域 2.矩形区域 3.多边形区域 4.路线
     * @see #TYPE_CIRCULAR_REGION
     * @see #TYPE_RECTANGULAR_REGION
     * @see #TYPE_POLYGONAL_REGION
     * @see #TYPE_ROUTE
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取区域或路段 ID
     * @return DWORD 区域或路段 ID
     */
    public long getId() {
        return id;
    }

    /**
     * 设置区域或路段 ID
     * @param id DWORD 区域或路段 ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 获取方向
     * @return BYTE 方向，0.进 1.出
     * @see #DIRECTION_IN
     * @see #DIRECTION_OUT
     */
    public int getDirection() {
        return direction;
    }

    /**
     * 设置方向
     * @param direction BYTE 方向，0.进 1.出
     * @see #DIRECTION_IN
     * @see #DIRECTION_OUT
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }


    /** 位置类型：圆形区域 */
    public static final int TYPE_CIRCULAR_REGION = 1;
    /** 位置类型：矩形区域 */
    public static final int TYPE_RECTANGULAR_REGION = 2;
    /** 位置类型：多边形区域 */
    public static final int TYPE_POLYGONAL_REGION = 3;
    /** 位置类型：路线 */
    public static final int TYPE_ROUTE = 4;

    /** 方向：进 */
    public static final int DIRECTION_IN = 0;
    /** 方向：出 */
    public static final int DIRECTION_OUT = 1;

}
