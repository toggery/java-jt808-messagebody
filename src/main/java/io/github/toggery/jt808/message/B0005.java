package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * JT/T 消息体 0x0005 终端补传分包请求 // 2019 new
 *
 * @author togger
 */
public class B0005 extends AbstractToStringJoiner implements Codec {

    /** WORD 原始消息流水号，对应要求补传的原始消息第一包的消息流水号 */
    private int originalSn;

    /** WORD 重传包序号列表 */
    private final List<Integer> bodyPacketSns = new ArrayList<>();


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("originalSn=" + originalSn)
                .add("bodyPacketSns=" + bodyPacketSns)
        ;
    }

    @Override
    public void encode(int version, ByteBuf buf) {
        Codec.writeWord(buf, originalSn);

        bodyPacketSns.removeIf(Objects::isNull);
        if (version > 0) {
            Codec.writeWord(buf, bodyPacketSns.size());
        } else {
            Codec.writeByte(buf, bodyPacketSns.size());
        }
        bodyPacketSns.stream().sorted().forEach(sn -> Codec.writeWord(buf, sn));
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        originalSn = Codec.readWord(buf);

        bodyPacketSns.clear();
        int cnt;
        if (version > 0) {
            cnt = Codec.readWord(buf);
        } else {
            cnt = Codec.readByte(buf);
        }
        while (cnt-- > 0) {
            bodyPacketSns.add(Codec.readWord(buf));
        }
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
     * @param originalSn WORD 原始消息流水号，对应要求补传的原始消息第一包的消息流水号
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
