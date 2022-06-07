package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x8203 人工确认报警消息
 *
 * @author togger
 */
@Data
public class B8203 {

    /** WORD 报警消息流水号，需人工确认的报警消息流水号，0 表示该报警类型所有消息 */
    private int sn;

    /**
     * DWORD 人工确认报警类型
     *
     * <ul>
     *     <li>bit0: 1.确认紧急报警；</li>
     *     <li>bit3: 1.确认危险预警；</li>
     *     <li>bit20: 1.确认进出区域报警；</li>
     *     <li>bit21: 1.确认进出路线报警；</li>
     *     <li>bit22: 1.确认路段行驶时间不足/过长报警；</li>
     *     <li>bit27: 1.确认车辆非法点火报警；</li>
     *     <li>bit28: 1.确认车辆非法位移报警；</li>
     *     <li>其他: 保留；</li>
     * </ul>
     */
    private long typeBits;

}
