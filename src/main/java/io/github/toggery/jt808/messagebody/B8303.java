package io.github.toggery.jt808.messagebody;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * JT/T 消息体：0x8303 信息点播菜单设置 // 2019 del
 *
 * @author togger
 */
public class B8303 extends AbstractToStringJoiner {

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


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("type=" + type)
                .add("newses=" + newses)
        ;
    }


    /**
     * 获取类型
     * <ul>
     *     <li>0：删除终端全部信息项（该命令后不带后继字节？）；</li>
     *     <li>1：更新菜单；</li>
     *     <li>2：追加菜单；</li>
     *     <li>3：修改菜单；</li>
     * </ul>
     * @return BYTE 类型
     * @see #TYPE_DELETE_ALL
     * @see #TYPE_UPDATE
     * @see #TYPE_APPEND
     * @see #TYPE_MODIFY
     */
    public int getType() {
        return type;
    }

    /**
     * 设置类型
     * <ul>
     *     <li>0：删除终端全部信息项（该命令后不带后继字节？）；</li>
     *     <li>1：更新菜单；</li>
     *     <li>2：追加菜单；</li>
     *     <li>3：修改菜单；</li>
     * </ul>
     * @param type BYTE 类型
     * @see #TYPE_DELETE_ALL
     * @see #TYPE_UPDATE
     * @see #TYPE_APPEND
     * @see #TYPE_MODIFY
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取信息项列表
     * @return 信息项列表
     */
    public List<News> getNewses() {
        return newses;
    }


    /** 类型：删除终端全部信息项（该命令后不带后继字节？） */
    public static final int TYPE_DELETE_ALL = 0;
    /** 类型：更新菜单 */
    public static final int TYPE_UPDATE = 1;
    /** 类型：追加菜单 */
    public static final int TYPE_APPEND = 2;
    /** 类型：修改菜单 */
    public static final int TYPE_MODIFY = 3;


    /**
     * JT/T 消息体：0x8303 信息项
     *
     * @author togger
     */
    public static class News extends AbstractToStringJoiner {

        /** BYTE 信息类型，若终端已有同类型的信息项，则被覆盖 */
        private int type;

        /** STRING 信息名称 */
        private String name;


        @Override
        protected void toStringJoiner(StringJoiner joiner) {
            joiner
                    .add("type=" + type)
                    .add("name=" + (name == null ? "" : name))
            ;
        }


        /**
         * 获取信息类型
         * @return BYTE 信息类型，若终端已有同类型的信息项，则被覆盖
         */
        public int getType() {
            return type;
        }

        /**
         * 设置信息类型
         * @param type BYTE 信息类型，若终端已有同类型的信息项，则被覆盖
         */
        public void setType(int type) {
            this.type = type;
        }

        /**
         * 获取信息名称
         * @return STRING 信息名称
         */
        public String getName() {
            return name;
        }

        /**
         * 设置信息名称
         * @param name STRING 信息名称
         */
        public void setName(String name) {
            this.name = name;
        }

    }

}
