package io.github.toggery.jt808.messagebody;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * JT/T 消息体 0x8302 提问下发 // 2019 del
 * @author togger
 */
@Data
public class B8302 {

    /**
     * BYTE 标志
     *
     * <ul>
     *     <li>bit0: 紧急</li>
     *     <li>bit3: 终端 TTS 播读</li>
     *     <li>bit4: 广告屏显示</li>
     *     <li>其他: 保留</li>
     * </ul>
     */
    private int flags;

    /** STRING 问题 */
    private String question;

    /** 候选答案列表 */
    private final List<Option> options = new ArrayList<>();

    /** 候选答案 */
    @Data
    public final static class Option {

        /** BYTE 答案 ID */
        private int id;

        /** STRING 答案内容 */
        private String content;

    }

}
