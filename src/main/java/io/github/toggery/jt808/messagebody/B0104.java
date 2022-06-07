package io.github.toggery.jt808.messagebody;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * JT/T 消息体 0x0104 查询终端参数应答
 *
 * @author togger
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class B0104 extends B8103 {

    /** WORD 应答流水号 */
    private int replySn;

}
