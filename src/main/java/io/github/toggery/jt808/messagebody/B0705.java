package io.github.toggery.jt808.messagebody;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * JT/T 消息体 0x0705 CAN总线数据上传 // 2019 modify
 *
 * @author togger
 */
@Data
public class B0705 {

    /** BCD[5] CAN 总线数据接收时间，第 1 条 CAN 总线数据的接收时间，hh-mm-ss-msms */
    private String time;

    /** CAN 总线数据列表 */
    private final List<Can> cans = new ArrayList<>();

    /** CAN 总线 */
    @Data
    public final static class Can {

        /**
         * BYTE[4] / DWORD CAN ID
         *
         * <ul>
         *     <li>bit31 表示 CAN 通道号，0：CAN1，1：CAN2；</li>
         *     <li>bit30 表示帧类型，0：标准帧，1：扩展帧；</li>
         *     <li>bit29 表示数据采集方式，0：原始数据，1：采集区间的平均值；</li>
         *     <li>bit28-bit0 表示 CAN 总线 ID。</li>
         * </ul>
         */
        private long id;

        /** BYTE[8] CAN 数据 */
        private byte[] data;

    }

}
