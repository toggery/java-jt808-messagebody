package io.github.toggery.jt808.messagebody;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * JT/T 消息体 0x8606 设置路线 // 2019 modify
 * @author togger
 */
@Data
public class B8606 {

    /** DWORD 路线 ID */
    private long id;

    /**
     * WORD 路线属性
     *
     * <ul>
     *     <li>bit0: 是否启用起始时间与结束时间的判断规则，0.否 1.是；</li>
     *     <li>bit1: 保留；</li>
     *     <li>bit2: 进路线是否报警给驾驶员，0.否 1.是；</li>
     *     <li>bit3: 进路线是否报警给平台，0.否 1.是；</li>
     *     <li>bit4: 出路线是否报警给驾驶员，0.否 1.是；</li>
     *     <li>bit5: 出路线是否报警给平台，0.否 1.是；</li>
     *     <li>其他: 保留</li>
     * </ul>
     */
    private int flags;

    /** BCD[6] 起始时间，yyMMddHHmmss，若区域属性 0 位为 0 则没有该字段 */
    private String startTime;

    /** BCD[6] 结束时间，yyMMddHHmmss，若区域属性 0 位为 0 则没有该字段 */
    private String endTime;

    /** STRING 路线名称 */
    private String name;

    /** 路线拐点列表 */
    private final List<Point> points = new ArrayList<>();

    /** 路段拐点 */
    @Data
    public final static class Point {

        /** DWORD 拐点 ID */
        private long id;

        /** DWORD 路段 ID */
        private long segmentId;

        /** DWORD 拐点纬度，以度为单位的纬度值乘以 10 的 6 次方，精确到百万分之一度 */
        private long latitude;

        /** DWORD 拐点经度，以度为单位的经度值乘以 10 的 6 次方，精确到百万分之一度*/
        private long longitude;

        /** BYTE 路段宽度，单位为米（m），路段为该拐点到下一拐点 */
        private int width;

        /**
         * BYTE 路段属性
         *
         * <ul>
         *     <li>bit0: 1.行驶时间；</li>
         *     <li>bit1: 1.限速；</li>
         *     <li>bit2: 0.北纬 1.南纬；</li>
         *     <li>bit3: 0.东经 1.西经；</li>
         *     <li>其他: 保留</li>
         * </ul>
         */
        private int flags;

        /** WORD 路段行驶过长阈值，单位为秒（s），若路段属性0位为0则没有该字段 */
        private int maxTime;

        /** WORD 路段行驶不足阈值，单位为秒（s），若路段属性0位为0则没有该字段 */
        private int minTime;

        /** WORD 路段最高速度，单位为km/h，若区域属性1位为0则没有该字段 */
        private int topSpeed;

        /** BYTE 路段超速持续时间，单位为秒（s）（类似表述，同前修改），若区域属性1位为0则没有该字段 */
        private int duration;

        /** WORD 路段夜间最高速度，单位为km/h，若区域属性1位为0则没有该字段 */
        private int nightTopSpeed;

    }

}
