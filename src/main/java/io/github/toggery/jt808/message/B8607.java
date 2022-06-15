package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;

/**
 * JT/T 消息体 0x8607 删除路线 元素类型为 DWORD
 *
 * <p>本条消息中包含的路线数，不超过 125 个，多于 125 个建议用多条消息，0 为删除所有路线</p>
 * @author togger
 */
public class B8607 extends ArrayList<Long> implements Codec {

    @Override
    public void encode(int version, ByteBuf buf) {
        Codec.writeCountHeadedContent(buf, IntUnit.BYTE, this, (b, that) -> {
            int count = 0;
            for (final Long id : that) {
                if (id == null) continue;
                Codec.writeDoubleWord(buf, id);
                count++;
            }
            return count;
        });
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        clear();

        int cnt = Codec.readByte(buf);
        while (cnt-- > 0) {
            add(Codec.readDoubleWord(buf));
        }
    }

}
