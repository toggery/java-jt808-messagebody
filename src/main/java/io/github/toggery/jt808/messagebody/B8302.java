package io.github.toggery.jt808.messagebody;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * JT/T 消息体 0x8302 提问下发 // 2019 del
 * @author togger
 */
public class B8302 extends AbstractToStringJoiner {

    /**
     * BYTE 标志
     *
     * <ul>
     *     <li>bit0：紧急；</li>
     *     <li>bit3：终端 TTS 播读；</li>
     *     <li>bit4：广告屏显示；</li>
     *     <li>其他保留</li>
     * </ul>
     */
    private int props;

    /** STRING 问题 */
    private String question;

    /** 候选答案列表 */
    private final List<Option> options = new ArrayList<>();


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add(HexUtil.byteString("props=", props))
                .add("question=" + (question == null ? "" : question))
                .add("options=" + options)
        ;
    }


    /**
     * 获取标志
     * <ul>
     *     <li>bit0：紧急；</li>
     *     <li>bit3：终端 TTS 播读；</li>
     *     <li>bit4：广告屏显示；</li>
     *     <li>其他保留</li>
     * </ul>
     * @return BYTE 标志
     */
    public int getProps() {
        return props;
    }

    /**
     * 设置标志
     * <ul>
     *     <li>bit0：紧急；</li>
     *     <li>bit3：终端 TTS 播读；</li>
     *     <li>bit4：广告屏显示；</li>
     *     <li>其他保留</li>
     * </ul>
     * @param props BYTE 标志
     */
    public void setProps(int props) {
        this.props = props;
    }

    /**
     * 获取问题
     * @return STRING 问题
     */
    public String getQuestion() {
        return question;
    }

    /**
     * 设置问题
     * @param question STRING 问题
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * 获取候选答案列表
     * @return 候选答案列表
     */
    public List<Option> getOptions() {
        return options;
    }


    /**
     * JT/T 消息体 0x8302 候选答案
     *
     * @author togger
     */
    public static class Option extends AbstractToStringJoiner {

        /** BYTE 答案 ID */
        private int id;

        /** STRING 答案内容 */
        private String content;


        @Override
        protected void toStringJoiner(StringJoiner joiner) {
            joiner
                    .add("id=" + id)
                    .add("content=" + (content == null ? "" : content))
            ;
        }


        /**
         * 获取答案 ID
         * @return BYTE 答案 ID
         */
        public int getId() {
            return id;
        }

        /**
         * 设置答案 ID
         * @param id BYTE 答案 ID
         */
        public void setId(int id) {
            this.id = id;
        }

        /**
         * 获取答案内容
         * @return STRING 答案内容
         */
        public String getContent() {
            return content;
        }

        /**
         * 设置答案内容
         * @param content STRING 答案内容
         */
        public void setContent(String content) {
            this.content = content;
        }

    }

}
