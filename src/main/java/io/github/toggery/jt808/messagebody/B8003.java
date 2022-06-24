package io.github.toggery.jt808.messagebody;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * JT/T 消息体：0x8003 服务器补传分包请求
 *
 * @author togger
 */
public class B8003 extends AbstractToStringJoiner {

    /** WORD 原始消息流水号，对应要求补传的原始消息第一包的消息流水号 */
    private int originalSn;

    /** WORD 重传包序号列表 */
    private final List<Integer> bodyPacketSns = new ArrayList<>();


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add(HexUtil.wordString("originalSn=", originalSn))
                .add("bodyPacketSns=" + bodyPacketSns.stream().map(HexUtil::wordString).collect(Collectors.toList()))
        ;
    }


    /**
     * 获取原始消息流水号，对应要求补传的原始消息第一包的消息流水号
     *
     * @return WORD 原始消息流水号，对应要求补传的原始消息第一包的消息流水号
     */
    public int getOriginalSn() {
        return originalSn;
    }

    /**
     * 设置原始消息流水号，对应要求补传的原始消息第一包的消息流水号
     *
     * @param originalSn WORD 原原始消息流水号，对应要求补传的原始消息第一包的消息流水号
     */
    public void setOriginalSn(int originalSn) {
        this.originalSn = originalSn;
    }

    /**
     * 获取重传包序号列表
     *
     * @return WORD 重传包序号列表
     */
    public List<Integer> getBodyPacketSns() {
        return bodyPacketSns;
    }

}
