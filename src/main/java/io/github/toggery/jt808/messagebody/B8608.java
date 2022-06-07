package io.github.toggery.jt808.messagebody;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * JT/T 消息体 0x8608 查询区域或线路数据 元素类型为 DWORD //2019 new
 *
 * @author togger
 */
@Data
public class B8608 {

    /** BYTE 类型，1.圆形区域 2.矩形区域 3.多边形区域 4.路线 */
    private int type;

    /** 区域或线路数据ID列表 */
    private final List<Long> ids = new ArrayList<>();

}
