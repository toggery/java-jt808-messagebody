package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x0302 提问应答 // 2019 del
 *
 * @author togger
 */
@Data
public class B0302 {

    /** WORD 应答流水号，对应的提问下发消息的流水号 */
    private int replySn;

    /** BYTE 答案 ID，提问下发中附带的答案 ID */
    private int id;

}
