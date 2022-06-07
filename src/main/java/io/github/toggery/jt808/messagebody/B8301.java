package io.github.toggery.jt808.messagebody;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * JT/T 消息体 0x8301 事件设置 // 2019 del
 *
 * @author togger
 */
@Data
public class B8301 {

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

    /** 事件 */
    @Data
    public final static class Event {

        /** BYTE 事件 ID，若终端已有同 ID 的事件，则被覆盖 */
        private int id;

        /** STRING 事件内容 */
        private String content;

    }
}
