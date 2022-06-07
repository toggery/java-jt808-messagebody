package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x8001 平台通用应答
 *
 * @author togger
 */
@Data
public class B8001 {

    /** WORD 应答流水号，对应的终端消息流水号 */
    private int replySn;

    /** WORD 应答 ID，对应的终端消息ID */
    private int replyId;

    /** BYTE 结果，0.成功 1.失败 2.消息有误 3.不支持 4.报警处理确认 */
    private int result;

    /** 终端信息，不参与编码解码 */
    private TerminalInfo terminal;

    /**
     * @return 是否成功
     */
    public final boolean isSuccess() {
        return result == 0;
    }

}
