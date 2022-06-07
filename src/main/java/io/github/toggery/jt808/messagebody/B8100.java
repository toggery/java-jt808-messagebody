package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x8100 终端注册应答
 *
 * @author togger
 */
@Data
public class B8100 {

    /** WORD 应答流水号 */
    private int replySn;

    /** BYTE 结果，0.成功 1.车辆已被注册 2.数据库中无该车辆 3.终端已被注册 4.数据库中无该终端 */

    private int result;

    /** STRING 鉴权码，成功后才有该字段 */
    private String token;

    /**
     * @return 是否成功
     */
    public final boolean isSuccess() {
        return result == 0;
    }

}
