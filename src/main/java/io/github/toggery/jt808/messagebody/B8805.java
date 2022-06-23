package io.github.toggery.jt808.messagebody;

import java.util.StringJoiner;

/**
 * JT/T 消息体 0x8805 单条存储多媒体数据检索上传命令
 *
 * @author togger
 */
public class B8805 extends AbstractToStringJoiner {

    /** DWORD 多媒体 ID，值大于 0 */
    private long id;

    /**
     * BYTE 删除标志
     * <ul>
     *     <li>0：保留；</li>
     *     <li>1：删除；</li>
     * </ul>
     */
    private int deleted;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("id=" + id)
                .add("deleted=" + deleted)
        ;
    }


    /**
     * 获取多媒体 ID，值大于 0
     * @return DWORD 多媒体 ID，值大于 0
     */
    public long getId() {
        return id;
    }

    /**
     * 设置多媒体 ID，值大于 0
     * @param id DWORD 多媒体 ID，值大于 0
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 获取删除标志
     * <ul>
     *     <li>0：保留；</li>
     *     <li>1：删除；</li>
     * </ul>
     * @return BYTE 删除标志
     * @see #DELETED_RESERVE
     * @see #DELETED_DELETE
     */
    public int getDeleted() {
        return deleted;
    }

    /**
     * 设置删除标志
     * <ul>
     *     <li>0：保留；</li>
     *     <li>1：删除；</li>
     * </ul>
     * @param deleted BYTE 删除标志
     * @see #DELETED_RESERVE
     * @see #DELETED_DELETE
     */
    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }


    /** 删除标志：保留 */
    public static final int DELETED_RESERVE = 0;
    /** 删除标志：删除 */
    public static final int DELETED_DELETE = 1;

}
