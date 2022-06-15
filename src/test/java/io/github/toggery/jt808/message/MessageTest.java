package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.util.DefaultAttributeMap;
import io.netty.util.ReferenceCountUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author togger
 */
class MessageTest {

    @Test
    void encode() {
        final B8001 body = new B8001();
        body.setReplySn(1);
        body.setReplyId(2);
        body.setResult(3);
        final Message<B8001> msg = Message.of(0x8001, body);
        msg.setSimNo("18912345678");
        msg.setVersion(1);
        final List<ByteBuf> buffs = new ArrayList<>();
        try {
            Message.encode(msg, UnpooledByteBufAllocator.DEFAULT::buffer, buffs::add, new DefaultAttributeMap());
            assertTrue(buffs.size() > 0, "encode should produce at least one instance of ByteBuf");
        } finally {
            buffs.forEach(b -> {
                System.out.println(ByteBufUtil.hexDump(b));
                ReferenceCountUtil.release(b);
            });
        }
    }

    @Test
    void decode() {
        final Map<Integer, MessageMetadata> messageMetadataMap = MessageMetadata.outbounds();
        final byte[] bytes = ByteBufUtil.decodeHexDump("7e80014005010000000001891234567800000001000203457e");
        final ByteBuf buf = UnpooledByteBufAllocator.DEFAULT.buffer(bytes.length);
        buf.writeBytes(bytes);
        final Message<?> message = Message.decode(buf, messageMetadataMap, new DefaultAttributeMap());
        assertNotNull(message, "decode should return one nonnull instance of Message");
        System.out.println(message);
    }

}