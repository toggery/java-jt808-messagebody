package io.github.toggery.jt808.messagebody;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * JT/T 消息体 0x8401 设置电话本
 *
 * @author togger
 */
@Data
public class B8401 {

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

    /** 联系人 */
    @Data
    public final static class Contact {

        /** BYTE 标志，1：呼入；2：呼出；3：呼入/呼出 */
        private int flags;

        /** STRING 电话号码 */
        private String phone;

        /** STRING 联系人 */
        private String name;

    }

}
