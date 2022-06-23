package io.github.toggery.jt808.messagebody;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * JT/T 消息体 0x8301 事件设置 // 2019 del
 *
 * @author togger
 */
public class B8301 extends AbstractToStringJoiner {

    /**
     * BYTE 类型
     *
     * <ul>
     *     <li>0：删除终端现有所有事件，该命令后不带后继字节；</li>
     *     <li>1：更新事件；</li>
     *     <li>2：追加事件；</li>
     *     <li>3：修改事件；</li>
     *     <li>4：删除特定几项事件，之后事件项中无需带事件内容；</li>
     * </ul>
     */
    private int type;

    /** 事件项列表 */
    private final List<Event> events = new ArrayList<>();


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("type=" + type)
                .add("events=" + events)
        ;
    }


    /**
     * 获取类型
     * <ul>
     *     <li>0：删除终端现有所有事件，该命令后不带后继字节；</li>
     *     <li>1：更新事件；</li>
     *     <li>2：追加事件；</li>
     *     <li>3：修改事件；</li>
     *     <li>4：删除特定几项事件，之后事件项中无需带事件内容；</li>
     * </ul>
     * @return BYTE 类型
     * @see #TYPE_DELETE_ALL
     * @see #TYPE_UPDATE
     * @see #TYPE_APPEND
     * @see #TYPE_MODIFY
     * @see #TYPE_DELETE_SOME
     */
    public int getType() {
        return type;
    }

    /**
     * 设置类型
     * <ul>
     *     <li>0：删除终端现有所有事件，该命令后不带后继字节；</li>
     *     <li>1：更新事件；</li>
     *     <li>2：追加事件；</li>
     *     <li>3：修改事件；</li>
     *     <li>4：删除特定几项事件，之后事件项中无需带事件内容；</li>
     * </ul>
     * @param type BYTE 类型
     * @see #TYPE_DELETE_ALL
     * @see #TYPE_UPDATE
     * @see #TYPE_APPEND
     * @see #TYPE_MODIFY
     * @see #TYPE_DELETE_SOME
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取事件项列表
     * @return 事件项列表
     */
    public List<Event> getEvents() {
        return events;
    }


    /** 类型：删除终端现有所有事件，该命令后不带后继字节 */
    public static final int TYPE_DELETE_ALL = 0;
    /** 类型：更新事件 */
    public static final int TYPE_UPDATE = 1;
    /** 类型：追加事件 */
    public static final int TYPE_APPEND = 2;
    /** 类型：修改事件 */
    public static final int TYPE_MODIFY = 3;
    /** 类型：删除特定几项事件，之后事件项中无需带事件内容 */
    public static final int TYPE_DELETE_SOME = 4;


    /**
     * JT/T 消息体 0x8301 事件
     *
     * @author togger
     */
    public static class Event extends AbstractToStringJoiner {

        /** BYTE 事件 ID，若终端已有同 ID 的事件，则被覆盖 */
        private int id;

        /** STRING 事件内容 */
        private String content;


        @Override
        protected void toStringJoiner(StringJoiner joiner) {
            joiner
                    .add("id=" + id)
                    .add("content=" + (content == null ? "" : content))
            ;
        }


        /**
         * 获取事件 ID
         * @return BYTE 事件 ID，若终端已有同 ID 的事件，则被覆盖
         */
        public int getId() {
            return id;
        }

        /**
         * 设置事件 ID
         * @param id BYTE 事件 ID，若终端已有同 ID 的事件，则被覆盖
         */
        public void setId(int id) {
            this.id = id;
        }

        /**
         * 获取事件内容
         * @return STRING 事件内容
         */
        public String getContent() {
            return content;
        }

        /**
         * 设置事件内容
         * @param content STRING 事件内容
         */
        public void setContent(String content) {
            this.content = content;
        }

    }

}
