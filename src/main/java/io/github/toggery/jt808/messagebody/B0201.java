package io.github.toggery.jt808.messagebody;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * JT/T 消息体 0x0201 位置信息查询应答
 *
 * @author togger
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class B0201 extends B0200 {

    /** WORD 应答流水号 */
    private int replySn;

}
