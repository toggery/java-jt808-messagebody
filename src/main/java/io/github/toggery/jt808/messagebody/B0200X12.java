package io.github.toggery.jt808.messagebody;

import java.util.StringJoiner;

/**
 * JT/T 消息体 位置扩展信息 0x12 进出区域/路线报警附加信息，见表 29
 *
 * @author togger
 */
public class B0200X12 extends AbstractToStringJoiner {

    /**
     * BYTE 位置类型
     * <ul>
     *     <li>1：圆形区域；</li>
     *     <li>2：矩形区域；</li>
     *     <li>3：多边形区域；</li>
     *     <li>4：路线；</li>
     * </ul>
     */
    private int type;

    /** DWORD 区域或路段 ID */
    private long id;

    /**
     * BYTE 方向
     * <ul>
     *     <li>0：进；</li>
     *     <li>1：出；</li>
     * </ul>
     */
    private int direction;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("type=" + type)
                .add("id=" + id)
                .add("direction=" + direction)
        ;
    }


    /**
     * 获取位置类型
     * <ul>
     *     <li>1：圆形区域；</li>
     *     <li>2：矩形区域；</li>
     *     <li>3：多边形区域；</li>
     *     <li>4：路线；</li>
     * </ul>
     * @return BYTE 位置类型
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
     * <ul>
     *     <li>1：圆形区域；</li>
     *     <li>2：矩形区域；</li>
     *     <li>3：多边形区域；</li>
     *     <li>4：路线；</li>
     * </ul>
     * @param type BYTE 位置类型
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
     * <ul>
     *     <li>0：进；</li>
     *     <li>1：出；</li>
     * </ul>
     * @return BYTE 方向
     * @see #DIRECTION_IN
     * @see #DIRECTION_OUT
     */
    public int getDirection() {
        return direction;
    }

    /**
     * 设置方向
     * <ul>
     *     <li>0：进；</li>
     *     <li>1：出；</li>
     * </ul>
     * @param direction BYTE 方向
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
