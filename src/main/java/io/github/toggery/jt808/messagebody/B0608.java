package io.github.toggery.jt808.messagebody;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * JT/T 消息体 0x0608 查询区域或线路数据应答 // 2019 new
 *<br><br>
 * ？？？矛盾：协议中集合元素类型为 DWORD，而描述则是区域或路线消息体数据格式 ？？？
 *
 * @author togger
 */
@Data
public class B0608 {

    /** BYTE 类型，1.圆形区域 2.矩形区域 3.多边形区域 4.路线 */
    private int type;

    /** 圆形区域数据集合 */
    private final List<B8600.Region> circles = new ArrayList<>();

    /** 矩形区域数据集合 */
    private final List<B8602.Region> rectangles = new ArrayList<>();

    /** 多边形区域数据集合 */
    private final List<B8604> polygons = new ArrayList<>();

    /** 路线数据集合 */
    private final List<B8606> routes = new ArrayList<>();

}
