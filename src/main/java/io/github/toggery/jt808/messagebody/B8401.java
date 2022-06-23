package io.github.toggery.jt808.messagebody;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * JT/T 消息体 0x8401 设置电话本
 *
 * @author togger
 */
public class B8401 extends AbstractToStringJoiner {

    /**
     * BYTE 类型
     *
     * <ul>
     *     <li>0：删除终端上所有存储的联系人（该命令后不带后继字节？）；</li>
     *     <li>1：表示更新电话本（删除终端中已有全部联系人并追加消息中的联系人）；</li>
     *     <li>2：表示追加电话本；</li>
     *     <li>3：表示修改电话本（以联系人为索引）；</li>
     * </ul>
     */
    private int type;

    /** 联系人列表 */
    private final List<Contact> contacts = new ArrayList<>();


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("type=" + type)
                .add("contacts=" + contacts)
        ;
    }


    /**
     * 获取类型
     * <ul>
     *     <li>0：删除终端上所有存储的联系人（该命令后不带后继字节？）；</li>
     *     <li>1：表示更新电话本（删除终端中已有全部联系人并追加消息中的联系人）；</li>
     *     <li>2：表示追加电话本；</li>
     *     <li>3：表示修改电话本（以联系人为索引）；</li>
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
     *     <li>0：删除终端上所有存储的联系人（该命令后不带后继字节？）；</li>
     *     <li>1：表示更新电话本（删除终端中已有全部联系人并追加消息中的联系人）；</li>
     *     <li>2：表示追加电话本；</li>
     *     <li>3：表示修改电话本（以联系人为索引）；</li>
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
     * 获取联系人列表
     * @return 联系人列表
     */
    public List<Contact> getContacts() {
        return contacts;
    }

    /** 类型：删除终端上所有存储的联系人（该命令后不带后继字节？） */
    public static final int TYPE_DELETE_ALL = 0;
    /** 类型：更新电话本（删除终端中已有全部联系人并追加消息中的联系人） */
    public static final int TYPE_UPDATE = 1;
    /** 类型：追加电话本 */
    public static final int TYPE_APPEND = 2;
    /** 类型：修改电话本（以联系人为索引） */
    public static final int TYPE_MODIFY = 3;


    /**
     * JT/T 消息体 0x8401 联系人
     *
     * @author togger
     */
    public static class Contact extends AbstractToStringJoiner {

        /**
         * BYTE 标志
         * <ul>
         *     <li>1：呼入；</li>
         *     <li>2：呼出；</li>
         *     <li>3：呼入/呼出；</li>
         * </ul>
         */
        private int type;

        /** STRING 电话号码 */
        private String phone;

        /** STRING 联系人 */
        private String name;


        @Override
        protected void toStringJoiner(StringJoiner joiner) {
            joiner
                    .add("type=" + type)
                    .add("phone=" + (phone == null ? "" : phone))
                    .add("name=" + (name == null ? "" : name))
            ;
        }


        /**
         * 获取标志
         * <ul>
         *     <li>1：呼入；</li>
         *     <li>2：呼出；</li>
         *     <li>3：呼入/呼出；</li>
         * </ul>
         * @return BYTE 标志
         * @see #TYPE_CALL_IN
         * @see #TYPE_CALL_OUT
         * @see #TYPE_CALL_IN_OUT
         */
        public int getType() {
            return type;
        }

        /**
         * 设置标志
         * <ul>
         *     <li>1：呼入；</li>
         *     <li>2：呼出；</li>
         *     <li>3：呼入/呼出；</li>
         * </ul>
         * @param type BYTE 标志
         * @see #TYPE_CALL_IN
         * @see #TYPE_CALL_OUT
         * @see #TYPE_CALL_IN_OUT
         */
        public void setType(int type) {
            this.type = type;
        }

        /**
         * 获取电话号码
         * @return STRING 电话号码
         */
        public String getPhone() {
            return phone;
        }

        /**
         * 设置电话号码
         * @param phone STRING 电话号码
         */
        public void setPhone(String phone) {
            this.phone = phone;
        }

        /**
         * 获取联系人
         * @return STRING 联系人
         */
        public String getName() {
            return name;
        }

        /**
         * 设置联系人
         * @param name STRING 联系人
         */
        public void setName(String name) {
            this.name = name;
        }

        /** 标志：呼出 */
        public static final int TYPE_CALL_IN = 1;
        /** 标志：呼出 */
        public static final int TYPE_CALL_OUT = 2;
        /** 标志：呼入/呼出 */
        public static final int TYPE_CALL_IN_OUT = TYPE_CALL_IN | TYPE_CALL_OUT;

    }

}
