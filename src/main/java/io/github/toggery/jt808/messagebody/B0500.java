package io.github.toggery.jt808.messagebody;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * JT/T 消息体 0x0500 车辆控制应答（继承的位置信息附加项无效）
 *
 * @author togger
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class B0500 extends B0200 {

    /** WORD 应答流水号，对应的车辆控制消息的流水号 */
    private int replySn;

}
