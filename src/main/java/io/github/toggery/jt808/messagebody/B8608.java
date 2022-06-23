package io.github.toggery.jt808.messagebody;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * JT/T 消息体 0x8608 查询区域或线路数据 元素类型为 DWORD // 2019 new
 *
 * @author togger
 */
public class B8608 extends AbstractToStringJoiner {

    /**
     * BYTE 类型
     * <ul>
     *     <li>1：圆形区域；</li>
     *     <li>2：矩形区域；</li>
     *     <li>3：多边形区域；</li>
     *     <li>4：路线；</li>
     * </ul>
     */
    private int type;

    /** 区域或线路数据 ID 列表 */
    private final List<Long> ids = new ArrayList<>();


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("type=" + type)
                .add("ids=" + ids)
        ;
    }


    /**
     * 获取类型
     * <ul>
     *     <li>1：圆形区域；</li>
     *     <li>2：矩形区域；</li>
     *     <li>3：多边形区域；</li>
     *     <li>4：路线；</li>
     * </ul>
     * @return BYTE 类型
     * @see #TYPE_CIRCLE
     * @see #TYPE_RECTANGLE
     * @see #TYPE_POLYGON
     * @see #TYPE_ROUTE
     */
    public int getType() {
        return type;
    }

    /**
     * 设置类型
     * <ul>
     *     <li>1：圆形区域；</li>
     *     <li>2：矩形区域；</li>
     *     <li>3：多边形区域；</li>
     *     <li>4：路线；</li>
     * </ul>
     * @param type BYTE 类型
     * @see #TYPE_CIRCLE
     * @see #TYPE_RECTANGLE
     * @see #TYPE_POLYGON
     * @see #TYPE_ROUTE
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取区域或线路数据 ID 列表
     * @return 区域或线路数据 ID 列表
     */
    public List<Long> getIds() {
        return ids;
    }

    /** 类型：圆形区域 */
    public static final int TYPE_CIRCLE = 1;
    /** 类型：矩形区域 */
    public static final int TYPE_RECTANGLE = 2;
    /** 类型：多边形区域 */
    public static final int TYPE_POLYGON = 3;
    /** 类型：路线 */
    public static final int TYPE_ROUTE = 4;

}
