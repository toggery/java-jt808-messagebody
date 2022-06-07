package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x8500 车辆控制 // 2019 modify
 *
 * 注意：
 * <ul>
 *     <li>2019之前的版本，使用属性 {@link #flags}</li>
 *     <li>2019及其之后的版本，使用其他属性</li>
 * </ul>
 *
 * @author togger
 */
@Data
public class B8500 {

    /**
     * BYTE 控制标志，仅适用于 2019 之前的版本
     *
     * <ul>
     *     <li>bit0: 0.车门锁闭 1.车门开启</li>
     *     <li>其他: 保留</li>
     * </ul>
     */
    private int flags;

    /** 0x0001 BYTE 车门，0.车门锁闭 1.车门开启 */
    private Integer x0001;

}
