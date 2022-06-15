package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * JT/T 消息体 0x0704 定位数据批量上传
 *
 * @author togger
 */
public class B0704 extends AbstractToStringJoiner implements Codec {

    /**
     * BYTE 类型
     * <ul>
     *     <li>0：正常位置批量汇报</li>
     *     <li>1：盲区补报</li>
     * </ul>
     */
    private int type;

    /** 位置汇报数据列表 */
    private final List<B0200> locations = new ArrayList<>();


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("type=" + type)
                .add("locations=" + locations)
        ;
    }

    @Override
    public void encode(int version, ByteBuf buf) {
        locations.removeIf(Objects::isNull);
        Codec.writeWord(buf, locations.size());
        Codec.writeByte(buf, type);
        locations.forEach(i -> Codec.writeLengthHeadedContent(buf, IntUnit.WORD, i, (b, v) -> v.encode(version, b)));
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        locations.clear();

        int cnt = Codec.readWord(buf);
        type = Codec.readByte(buf);
        while (cnt-- > 0) {
            final ByteBuf locationBuf = Codec.readSlice(buf, IntUnit.WORD);
            final B0200 location = new B0200();
            location.decode(version, locationBuf);
            locations.add(location);
        }
    }


    /**
     * 获取类型
     * <ul>
     *     <li>0：正常位置批量汇报</li>
     *     <li>1：盲区补报</li>
     * </ul>
     * @return BYTE 类型
     * @see #TYPE_BATCH
     * @see #TYPE_POST
     */
    public int getType() {
        return type;
    }

    /**
     * 设置类型
     * <ul>
     *     <li>0：正常位置批量汇报</li>
     *     <li>1：盲区补报</li>
     * </ul>
     * @param type BYTE 类型
     * @see #TYPE_BATCH
     * @see #TYPE_POST
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取位置汇报数据列表
     * @return 位置汇报数据列表
     */
    public List<B0200> getLocations() {
        return locations;
    }


    /** 类型：正常位置批量汇报 */
    public static final int TYPE_BATCH = 0;
    /** 类型：盲区补报 */
    public static final int TYPE_POST = 1;

}
