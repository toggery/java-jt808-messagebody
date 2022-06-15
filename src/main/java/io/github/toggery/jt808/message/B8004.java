package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;

import java.util.StringJoiner;

/**
 * JT/T 消息体 0x8004 查询服务器时间应答 // 2019 new
 *
 * @author togger
 */
public class B8004 extends AbstractToStringJoiner implements Codec {

    /** BCD[6] 服务器 UTC 时间，形如 yyMMddHHmmss */
    private String time;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("time=" + (time == null ? "" : time))
        ;
    }

    @Override
    public void encode(int version, ByteBuf buf) {
        Codec.writeBcd(buf, time, 6);
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        time = Codec.readBcd(buf, 6, false);
    }


    /**
     * 获取服务器 UTC 时间，形如 yyMMddHHmmss
     * @return BCD[6] 服务器 UTC 时间，形如 yyMMddHHmmss
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置服务器 UTC 时间，形如 yyMMddHHmmss
     * @param time BCD[6] 服务器 UTC 时间，形如 yyMMddHHmmss
     */
    public void setTime(String time) {
        this.time = time;
    }

}
