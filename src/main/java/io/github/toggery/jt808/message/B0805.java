package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * JT/T 消息体 0x0805 摄像头立即拍摄命令应答
 *
 * @author togger
 */
public class B0805 extends AbstractToStringJoiner implements Codec {

    /** WORD 应答流水号，对应平台摄像头立即拍摄命令的消息流水号 */
    private int replySn;

    /**
     * BYTE 结果
     * <ul>
     *     <li>0：成功；</li>
     *     <li>1：失败；</li>
     *     <li>2：通道不支持</li>
     *     <li>元素项（多媒体 ID ）在结果=0 时才有效</li>
     * </ul>
     */
    private int result;

    /** 多媒体 ID 列表，元素为 DWORD */
    private final List<Long> mediaIds = new ArrayList<>();


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("replySn=" + replySn)
                .add("result=" + result)
                .add("mediaIds=" + mediaIds)
        ;
    }

    @Override
    public void encode(int version, ByteBuf buf) {
        Codec.writeWord(buf, replySn);
        Codec.writeByte(buf, result);
        if (result != RESULT_SUCCESSFUL) {
            return;
        }

        Codec.writeCountHeadedContent(buf, IntUnit.WORD, mediaIds, (b, that) -> {
            int count = 0;
            for (final Long mediaId : that) {
                if (mediaId == null) continue;
                Codec.writeDoubleWord(buf, mediaId);
                count++;
            }
            return count;
        });
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        mediaIds.clear();

        replySn = Codec.readWord(buf);
        result = Codec.readByte(buf);
        if (result != RESULT_SUCCESSFUL) {
            return;
        }

        int cnt = Codec.readWord(buf);
        while (cnt-- > 0) {
            mediaIds.add(Codec.readDoubleWord(buf));
        }
    }


    /**
     * 获取应答流水号，对应平台摄像头立即拍摄命令的消息流水号
     * @return WORD 应答流水号，对应平台摄像头立即拍摄命令的消息流水号
     */
    public int getReplySn() {
        return replySn;
    }

    /**
     * 设置应答流水号，对应平台摄像头立即拍摄命令的消息流水号
     * @param replySn WORD 应答流水号，对应平台摄像头立即拍摄命令的消息流水号
     */
    public void setReplySn(int replySn) {
        this.replySn = replySn;
    }

    /**
     * 获取结果
     * <ul>
     *     <li>0：成功；</li>
     *     <li>1：失败；</li>
     *     <li>2：通道不支持</li>
     *     <li>元素项（多媒体 ID ）在结果=0 时才有效</li>
     * </ul>
     * @return BYTE 结果
     * @see #RESULT_SUCCESSFUL
     * @see #RESULT_FAILED
     * @see #RESULT_CHANNEL_UNSUPPORTED
     */
    public int getResult() {
        return result;
    }

    /**
     * 设置结果
     * <ul>
     *     <li>0：成功；</li>
     *     <li>1：失败；</li>
     *     <li>2：通道不支持</li>
     *     <li>元素项（多媒体 ID ）在结果=0 时才有效</li>
     * </ul>
     * @param result BYTE 结果
     * @see #RESULT_SUCCESSFUL
     * @see #RESULT_FAILED
     * @see #RESULT_CHANNEL_UNSUPPORTED
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * 获取多媒体 ID 列表，元素为 DWORD
     * @return 多媒体 ID 列表，元素为 DWORD
     */
    public List<Long> getMediaIds() {
        return mediaIds;
    }

    /**
     * @return 是否成功
     */
    public final boolean isSuccessful() {
        return result == RESULT_SUCCESSFUL;
    }

    /** 结果：成功 */
    public static final int RESULT_SUCCESSFUL = 0;
    /** 结果：失败 */
    public static final int RESULT_FAILED = 1;
    /** 结果：通道不支持 */
    public static final int RESULT_CHANNEL_UNSUPPORTED = 2;

}
