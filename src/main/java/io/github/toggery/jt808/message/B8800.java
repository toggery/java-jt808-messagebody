package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * JT/T 消息体 0x8800 多媒体数据上传应答
 *
 * @author togger
 */
public class B8800 extends AbstractToStringJoiner implements Codec {

    /** DWORD 多媒体数据 ID，值大于 0 */
    private long id;

    /** WORD 重传包序号列表 */
    private final List<Integer> bodyPacketSns = new ArrayList<>();


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("id=" + id)
                .add("bodyPacketSns=" + bodyPacketSns.stream().map(IntUtil::wordHexString).collect(Collectors.toList()))
        ;
    }

    @Override
    public void encode(int version, ByteBuf buf) {
        Codec.writeDoubleWord(buf, id);
        bodyPacketSns.removeIf(Objects::isNull);
        if (bodyPacketSns.size() <= 0) {
            return;
        }

        Codec.writeByte(buf, bodyPacketSns.size());
        bodyPacketSns.forEach(i -> Codec.writeWord(buf, i));
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        bodyPacketSns.clear();
        id = Codec.readDoubleWord(buf);

        if (buf.isReadable()) {
            int cnt = Codec.readByte(buf);
            while (cnt-- > 0) {
                bodyPacketSns.add(Codec.readWord(buf));
            }
        }
    }


    /**
     * 获取多媒体数据 ID，值大于 0
     * @return DWORD 多媒体数据 ID，值大于 0
     */
    public long getId() {
        return id;
    }

    /**
     * 设置多媒体数据 ID，值大于 0
     * @param id DWORD 多媒体数据 ID，值大于 0
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 设置重传包序号列表
     * @return WORD 重传包序号列表
     */
    public List<Integer> getBodyPacketSns() {
        return bodyPacketSns;
    }

}
