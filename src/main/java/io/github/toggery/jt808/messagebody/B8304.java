package io.github.toggery.jt808.messagebody;

import java.util.StringJoiner;

/**
 * JT/T 消息体 0x8304 信息服务 // 2019 del
 *
 * @author togger
 */
public class B8304 extends AbstractToStringJoiner {

    /**
     * BYTE 信息类型
     * @see B8303.News#getType()
     */
    private int type;

    /** STRING 信息内容 */
    private String content;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("type=" + type)
                .add("content=" + (content == null ? "" : content))
        ;
    }


    /**
     * 获取信息类型
     * @return BYTE 信息类型
     * @see B8303.News#getType()
     */
    public int getType() {
        return type;
    }

    /**
     * 设置信息类型
     * @param type BYTE 信息类型
     * @see B8303.News#getType()
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取信息内容
     * @return STRING 信息内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置信息内容
     * @param content STRING 信息内容
     */
    public void setContent(String content) {
        this.content = content;
    }

}
