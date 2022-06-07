package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x0001 终端通用应答
 *
 * @author togger
 */
@Data
public class B0001 {

    /** WORD 应答流水号，对应的平台消息流水号 */
    private int replySn;

    /** WORD 应答ID，对应的平台消息ID */
    private int replyId;

    /** BYTE 结果，0.成功 1.失败 2.消息有误 3.不支持 */
    private int result;

    /**
     * @return 是否成功
     */
    public final boolean isSuccess() {
        return result == 0;
    }

}
