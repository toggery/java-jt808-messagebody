package io.github.toggery.jt808.messagebody;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * JT/T 消息体：0x0608 查询区域或线路数据应答 // 2019 new
 *<br><br>
 * ？？？矛盾：协议中集合元素类型为 DWORD，而描述则是区域或路线消息体数据格式 ？？？
 *
 * @author togger
 */
public class B0608 extends AbstractToStringJoiner {

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

    /** 圆形区域数据列表 */
    private final List<B8600.Region> circles = new ArrayList<>();

    /** 矩形区域数据列表 */
    private final List<B8602.Region> rectangles = new ArrayList<>();

    /** 多边形区域数据列表 */
    private final List<B8604> polygons = new ArrayList<>();

    /** 路线数据列表 */
    private final List<B8606> routes = new ArrayList<>();


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("type=" + type)
                .add("circles=" + circles)
                .add("rectangles=" + rectangles)
                .add("polygons=" + polygons)
                .add("routes=" + routes)
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
     * 获取圆形区域数据列表
     * @return 圆形区域数据列表
     */
    public List<B8600.Region> getCircles() {
        return circles;
    }

    /**
     * 获取矩形区域数据列表
     * @return 矩形区域数据列表
     */
    public List<B8602.Region> getRectangles() {
        return rectangles;
    }

    /**
     * 获取多边形区域数据列表
     * @return 多边形区域数据列表
     */
    public List<B8604> getPolygons() {
        return polygons;
    }

    /**
     * 获取路线数据列表
     * @return 路线数据列表
     */
    public List<B8606> getRoutes() {
        return routes;
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
