package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * JT/T 消息体 0x0802 存储多媒体数据检索应答
 *
 * @author togger
 */
public class B0802 extends AbstractToStringJoiner implements Codec {

    /** WORD 应答流水号，对应的多媒体数据检索消息的流水号 */
    private int replySn;

    /** 多媒体检索列表 */
    private final List<Media> medias = new ArrayList<>();


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("replySn=" + replySn)
                .add("medias=" + medias)
        ;
    }

    @Override
    public void encode(int version, ByteBuf buf) {
        Codec.writeWord(buf, replySn);
        Codec.writeCountHeadedContent(buf, IntUnit.WORD, medias, (b, that) -> {
            int count = 0;
            for (final Media media: that) {
                if (media == null) continue;
                media.encode(version, b);
                count++;
            }
            return count;
        });
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        medias.clear();
        replySn = Codec.readWord(buf);
        int cnt = Codec.readWord(buf);
        while (cnt-- > 0) {
            final Media media = new Media();
            media.decode(version, buf);
            medias.add(media);
        }
    }


    /**
     * 获取应答流水号，对应的多媒体数据检索消息的流水号
     * @return WORD 应答流水号，对应的多媒体数据检索消息的流水号
     */
    public int getReplySn() {
        return replySn;
    }

    /**
     * 设置应答流水号，对应的多媒体数据检索消息的流水号
     * @param replySn WORD 应答流水号，对应的多媒体数据检索消息的流水号
     */
    public void setReplySn(int replySn) {
        this.replySn = replySn;
    }

    /**
     * 获取多媒体检索列表
     * @return 多媒体检索列表
     */
    public List<Media> getMedias() {
        return medias;
    }


    /**
     * JT/T 消息体 0x0802 存储多媒体数据检索应答的多媒体数据（位置信息汇报的信息基本数据）
     * @author togger
     */
    public static class Media extends B0200 {

        /** DWORD 多媒体数据 ID，值大于 0 */
        private long id;

        /**
         * BYTE 多媒体类型
         * @see B8802#getType()
         */
        private int type;

        /** BYTE 通道 ID，值大于 0 */
        private int channel;

        /**
         * BYTE 事件项编码
         * @see B8802#getEvent()
         */
        private int event;


        @Override
        protected void toStringJoiner(StringJoiner joiner) {
            joiner
                    .add("id=" + id)
                    .add("type=" + type)
                    .add("channel=" + channel)
                    .add("event=" + event)
            ;
            super.toStringJoiner(joiner);
        }

        @Override
        public void encode(int version, ByteBuf buf) {
            Codec.writeDoubleWord(buf, id);
            Codec.writeByte(buf, type);
            Codec.writeByte(buf, channel);
            Codec.writeByte(buf, event);

            super.encode(version, buf);
        }

        @Override
        public void decode(int version, ByteBuf buf) {
            id = Codec.readDoubleWord(buf);
            type = Codec.readByte(buf);
            channel = Codec.readByte(buf);
            event = Codec.readByte(buf);

            super.decode(version, buf);
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
         * 获取多媒体类型
         * @return BYTE 多媒体类型
         * @see B8802#getType()
         */
        public int getType() {
            return type;
        }

        /**
         * 设置多媒体类型
         * @param type BYTE 多媒体类型
         * @see B8802#getType()
         */
        public void setType(int type) {
            this.type = type;
        }

        /**
         * 获取通道 ID，值大于 0
         * @return BYTE 通道 ID，值大于 0
         */
        public int getChannel() {
            return channel;
        }

        /**
         * 设置通道 ID，值大于 0
         * @param channel BYTE 通道 ID，值大于 0
         */
        public void setChannel(int channel) {
            this.channel = channel;
        }

        /**
         * 获取事件项编码
         * @return BYTE 事件项编码
         * @see B8802#getEvent()
         */
        public int getEvent() {
            return event;
        }

        /**
         * 设置事件项编码
         * @param event BYTE 事件项编码
         * @see B8802#getEvent()
         */
        public void setEvent(int event) {
            this.event = event;
        }

    }

}
