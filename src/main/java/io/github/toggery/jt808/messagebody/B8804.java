package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x8804 录音开始命令
 *
 * @author togger
 */
@Data
public class B8804 {

    /** BYTE 录音命令，0：停止录音；0x01：开始录音 */
    private int command;

    /** WORD 录音时间，单位为秒（s），0 表示一直录音 */
    private int duration;

    /** BYTE 保存标志，0：实时上传；1：保存 */
    private int action;

    /** BYTE 音频采样率，0：8K；1：11K；2：23K；3：32K；其他保留 */
    private int sampling;

}
