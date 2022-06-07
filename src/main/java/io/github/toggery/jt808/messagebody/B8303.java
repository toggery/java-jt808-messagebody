package io.github.toggery.jt808.messagebody;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * JT/T 消息体 0x8303 信息点播菜单设置 // 2019 del
 *
 * @author togger
 */
@Data
public class B8303 {

    /**
     * BYTE 类型
     *
     * <ul>
     *     <li>0：删除终端全部信息项（该命令后不带后继字节？）；</li>
     *     <li>1：更新菜单；</li>
     *     <li>2：追加菜单；</li>
     *     <li>3：修改菜单；</li>
     * </ul>
     */
    private int type;

    /** 信息项列表 */
    private final List<News> newses = new ArrayList<>();

    /** 信息项 */
    @Data
    public final static class News {

        /** BYTE 信息类型，若终端已有同类型的信息项，则被覆盖 */
        private int type;

        /** STRING 信息名称 */
        private String name;

    }

}
