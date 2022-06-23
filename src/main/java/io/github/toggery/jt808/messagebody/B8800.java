package io.github.toggery.jt808.messagebody;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * JT/T 消息体 0x8800 多媒体数据上传应答
 *
 * @author togger
 */
public class B8800 extends AbstractToStringJoiner {

    /** DWORD 多媒体数据 ID，值大于 0 */
    private long id;

    /** WORD 重传包序号列表 */
    private final List<Integer> bodyPacketSns = new ArrayList<>();


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("id=" + id)
                .add("bodyPacketSns=" + bodyPacketSns.stream().map(HexUtil::wordString).collect(Collectors.toList()))
        ;
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
