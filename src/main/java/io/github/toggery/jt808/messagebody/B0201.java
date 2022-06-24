package io.github.toggery.jt808.messagebody;

import java.util.StringJoiner;

/**
 * JT/T 消息体：0x0201 位置信息查询应答
 *
 * @author togger
 */
public class B0201 extends B0200 {

    /** WORD 应答流水号 */
    private int replySn;

    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add(HexUtil.wordString("replySn=", replySn))
        ;
        super.toStringJoiner(joiner);
    }


    /**
     * 获取应答流水号
     * @return WORD 应答流水号
     */
    public int getReplySn() {
        return replySn;
    }

    /**
     * 设置应答流水号
     * @param replySn WORD 应答流水号
     */
    public void setReplySn(int replySn) {
        this.replySn = replySn;
    }

}
