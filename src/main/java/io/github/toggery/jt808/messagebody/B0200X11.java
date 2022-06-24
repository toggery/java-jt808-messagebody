package io.github.toggery.jt808.messagebody;

import java.util.StringJoiner;

/**
 * JT/T 消息体：0x0200 位置信息汇报 附加信息 0x11 超速报警附加信息，见表 28
 *
 * @author togger
 */
public class B0200X11 extends AbstractToStringJoiner {

    /**
     * BYTE 位置类型
     * <ul>
     *     <li>0：无特定位置；</li>
     *     <li>1：圆形区域；</li>
     *     <li>2：矩形区域；</li>
     *     <li>3：多边形区域；</li>
     *     <li>4：路段；</li>
     * </ul>
     */
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


    /**
     * 获取位置类型
     * <ul>
     *     <li>0：无特定位置；</li>
     *     <li>1：圆形区域；</li>
     *     <li>2：矩形区域；</li>
     *     <li>3：多边形区域；</li>
     *     <li>4：路段；</li>
     * </ul>
     * @return BYTE 位置类型
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
     * <ul>
     *     <li>0：无特定位置；</li>
     *     <li>1：圆形区域；</li>
     *     <li>2：矩形区域；</li>
     *     <li>3：多边形区域；</li>
     *     <li>4：路段；</li>
     * </ul>
     * @see #TYPE_NONE
     * @see #TYPE_CIRCULAR_REGION
     * @see #TYPE_RECTANGULAR_REGION
     * @see #TYPE_POLYGONAL_REGION
     * @see #TYPE_SEGMENT
     * @param type BYTE 位置类型
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


    /**
     * 获取是否为无特定位置
     * @return 是否为无特定位置
     */
    public boolean isNone() {
        return type == TYPE_NONE;
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
