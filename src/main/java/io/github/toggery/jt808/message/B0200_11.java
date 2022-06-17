package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;

import java.util.StringJoiner;

/**
 * JT/T 消息体 位置扩展信息 0x11 超速报警附加信息，见表 28
 *
 * @author togger
 */
public class B0200_11 extends AbstractToStringJoiner implements Codec {

    /** BYTE 位置类型，0.无特定位置 1.圆形区域 2.矩形区域 3.多边形区域 4.路段 */
    private int type;

    /** DWORD 区域或路段 ID，若位置类型为 0 则无该字段 */
    private long id;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("type=" + type)
                .add("id=" + id)
        ;
    }

    @Override
    public void encode(int version, ByteBuf buf) {
        Codec.writeByte(buf, type);
        if (type != 0) {
            Codec.writeDoubleWord(buf, id);
        }
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        type = Codec.readByte(buf);
        if (type != 0) {
            id = Codec.readDoubleWord(buf);
        } else {
            id = 0;
        }
    }


    /**
     * 获取位置类型
     * @return BYTE 位置类型，0.无特定位置 1.圆形区域 2.矩形区域 3.多边形区域 4.路段
     * @see #TYPE_NONE
     * @see #TYPE_CIRCULAR_REGION
     * @see #TYPE_RECTANGULAR_REGION
     * @see #TYPE_POLYGONAL_REGION
     * @see #TYPE_SEGMENT
     */
    public int getType() {
        return type;
    }

    /**
     * 设置位置类型
     * @see #TYPE_NONE
     * @see #TYPE_CIRCULAR_REGION
     * @see #TYPE_RECTANGULAR_REGION
     * @see #TYPE_POLYGONAL_REGION
     * @see #TYPE_SEGMENT
     * @param type BYTE 位置类型，0.无特定位置 1.圆形区域 2.矩形区域 3.多边形区域 4.路段
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取区域或路段 ID
     * @return DWORD 区域或路段 ID，若位置类型为 0 则无该字段
     */
    public long getId() {
        return id;
    }

    /**
     * 设置区域或路段 ID
     * @param id DWORD 区域或路段 ID，若位置类型为 0 则无该字段
     */
    public void setId(long id) {
        this.id = id;
    }


    /** 位置类型：无特定位置 */
    public static final int TYPE_NONE = 0;
    /** 位置类型：圆形区域 */
    public static final int TYPE_CIRCULAR_REGION = 1;
    /** 位置类型：矩形区域 */
    public static final int TYPE_RECTANGULAR_REGION = 2;
    /** 位置类型：多边形区域 */
    public static final int TYPE_POLYGONAL_REGION = 3;
    /** 位置类型：路段 */
    public static final int TYPE_SEGMENT = 4;

}
