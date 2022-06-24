package io.github.toggery.jt808.messagebody;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * JT/T 消息体：0x0704 定位数据批量上传
 *
 * @author togger
 */
public class B0704 extends AbstractToStringJoiner {

    /**
     * BYTE 类型
     * <ul>
     *     <li>0：正常位置批量汇报；</li>
     *     <li>1：盲区补报；</li>
     * </ul>
     */
    private int type;

    /** 位置汇报数据列表 */
    private final List<B0200> locations = new ArrayList<>();


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("type=" + type)
                .add("locations=" + locations)
        ;
    }


    /**
     * 获取类型
     * <ul>
     *     <li>0：正常位置批量汇报；</li>
     *     <li>1：盲区补报；</li>
     * </ul>
     * @return BYTE 类型
     * @see #TYPE_BATCH
     * @see #TYPE_POST
     */
    public int getType() {
        return type;
    }

    /**
     * 设置类型
     * <ul>
     *     <li>0：正常位置批量汇报；</li>
     *     <li>1：盲区补报；</li>
     * </ul>
     * @param type BYTE 类型
     * @see #TYPE_BATCH
     * @see #TYPE_POST
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取位置汇报数据列表
     * @return 位置汇报数据列表
     */
    public List<B0200> getLocations() {
        return locations;
    }


    /**
     * 获取是否为批量汇报
     * @return 是否为批量汇报
     */
    public boolean isBatch() {
        return type == TYPE_BATCH;
    }

    /**
     * 获取是否为补报
     * @return 是否为补报
     */
    public boolean isPost() {
        return type == TYPE_POST;
    }


    /** 类型：正常位置批量汇报 */
    public static final int TYPE_BATCH = 0;
    /** 类型：盲区补报 */
    public static final int TYPE_POST = 1;

}
