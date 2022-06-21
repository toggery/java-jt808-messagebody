package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * JT/T 消息体 0x8606 设置路线 // 2019 modify
 * @author togger
 */
public class B8606 extends AbstractToStringJoiner implements Codec {

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
    private int props;

    /** BCD[6] 起始时间，yyMMddHHmmss，若区域属性 0 位为 0 则没有该字段 */
    private String startTime;

    /** BCD[6] 结束时间，yyMMddHHmmss，若区域属性 0 位为 0 则没有该字段 */
    private String endTime;

    /** STRING 路线名称 */
    private String name;

    /** 路线拐点列表 */
    private final List<Point> points = new ArrayList<>();


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("id=" + id)
                .add(IntUtil.wordHexString("props=", props))
                .add("startTime=" + (startTime == null ? "" : startTime))
                .add("endTime=" + (endTime == null ? "" : endTime))
                .add("name=" + (name == null ? "" : name))
                .add("points=" + points)
        ;
    }

    @Override
    public void encode(int version, ByteBuf buf) {
        Codec.writeDoubleWord(buf, id);
        Codec.writeWord(buf, props);

        if ((props & BIT0_MASK) == BIT0_MASK) {
            Codec.writeBcd(buf, startTime, 6);
            Codec.writeBcd(buf, endTime, 6);
        }

        Codec.writeCountHeadedContent(buf, IntUnit.WORD, points, (b, that) -> {
            int count = 0;
            for (final Point point : that) {
                if (point == null) continue;
                point.encode(version, b);
                count++;
            }
            return count;
        });

        if (version > 0) {
            Codec.writeString(buf, IntUnit.WORD, name);
        }
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        points.clear();
        id = Codec.readDoubleWord(buf);
        props = Codec.readWord(buf);

        if ((props & BIT0_MASK) == BIT0_MASK) {
            startTime = Codec.readBcd(buf, 6, false);
            endTime = Codec.readBcd(buf, 6, false);
        }

        int cnt = Codec.readWord(buf);
        while (cnt-- > 0) {
            final Point point = new Point();
            point.decode(version, buf);
            points.add(point);
        }

        if (version > 0) {
            name = Codec.readString(buf, IntUnit.WORD);
        }
    }


    /**
     * 获取路线 ID
     * @return DWORD 路线 ID
     */
    public long getId() {
        return id;
    }

    /**
     * 设置路线 ID
     * @param id DWORD 路线 ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 获取路线属性
     * <ul>
     *     <li>bit0: 是否启用起始时间与结束时间的判断规则，0.否 1.是；</li>
     *     <li>bit1: 保留；</li>
     *     <li>bit2: 进路线是否报警给驾驶员，0.否 1.是；</li>
     *     <li>bit3: 进路线是否报警给平台，0.否 1.是；</li>
     *     <li>bit4: 出路线是否报警给驾驶员，0.否 1.是；</li>
     *     <li>bit5: 出路线是否报警给平台，0.否 1.是；</li>
     *     <li>其他: 保留</li>
     * </ul>
     * @return WORD 路线属性
     */
    public int getProps() {
        return props;
    }

    /**
     * 设置路线属性
     * <ul>
     *     <li>bit0: 是否启用起始时间与结束时间的判断规则，0.否 1.是；</li>
     *     <li>bit1: 保留；</li>
     *     <li>bit2: 进路线是否报警给驾驶员，0.否 1.是；</li>
     *     <li>bit3: 进路线是否报警给平台，0.否 1.是；</li>
     *     <li>bit4: 出路线是否报警给驾驶员，0.否 1.是；</li>
     *     <li>bit5: 出路线是否报警给平台，0.否 1.是；</li>
     *     <li>其他: 保留</li>
     * </ul>
     * @param props WORD 路线属性
     */
    public void setProps(int props) {
        this.props = props;
    }

    /**
     * 获取起始时间，yyMMddHHmmss
     * @return BCD[6] 起始时间，yyMMddHHmmss，若区域属性 0 位为 0 则没有该字段
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * 设置起始时间，yyMMddHHmmss
     * @param startTime BCD[6] 起始时间，yyMMddHHmmss，若区域属性 0 位为 0 则没有该字段
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结束时间，yyMMddHHmmss
     * @return BCD[6] 结束时间，yyMMddHHmmss，若区域属性 0 位为 0 则没有该字段
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间，yyMMddHHmmss
     * @param endTime BCD[6] 结束时间，yyMMddHHmmss，若区域属性 0 位为 0 则没有该字段
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取路线名称
     * @return STRING 路线名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置路线名称
     * @param name STRING 路线名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取路线拐点列表
     * @return 路线拐点列表
     */
    public List<Point> getPoints() {
        return points;
    }

    /** {@link Point#props} 二进制位 0 掩码 */
    protected static final int BIT0_MASK = 0b1;
    /** {@link Point#props} 二进制位 1 掩码 */
    protected static final int BIT1_MASK = 0b10;

    /**
     * JT/T 消息体 0x8606 路线拐点
     * @author togger
     */
    public static class Point extends AbstractToStringJoiner implements Codec {

        /** DWORD 拐点 ID */
        private long id;

        /** DWORD 路段 ID */
        private long segmentId;

        /** DWORD 拐点纬度，以度为单位的纬度值乘以 10 的 6 次方，精确到百万分之一度 */
        private long latitude;

        /** DWORD 拐点经度，以度为单位的经度值乘以 10 的 6 次方，精确到百万分之一度 */
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
        private int props;

        /** WORD 路段行驶过长阈值，单位为秒（s），若路段属性 0 位为 0 则没有该字段 */
        private int maxTime;

        /** WORD 路段行驶不足阈值，单位为秒（s），若路段属性 0 位 为 0 则没有该字段 */
        private int minTime;

        /** WORD 路段最高速度，单位为 km/h，若区域属性 1 位为 0 则没有该字段 */
        private int maxSpeed;

        /** BYTE 路段超速持续时间，单位为秒（s），若区域属性 1 位为 0 则没有该字段 */
        private int duration;

        /** WORD 路段夜间最高速度，单位为 km/h，若区域属性 1 位为 0 则没有该字段 */
        private int nightMaxSpeed;


        @Override
        protected void toStringJoiner(StringJoiner joiner) {
            joiner
                    .add("id=" + id)
                    .add("segmentId=" + segmentId)
                    .add("latitude=" + latitude)
                    .add("longitude=" + longitude)
                    .add("width=" + width)
                    .add(IntUtil.byteHexString("props=", props))
                    .add("maxTime=" + maxTime)
                    .add("minTime=" + minTime)
                    .add("maxSpeed=" + maxSpeed)
                    .add("duration=" + duration)
                    .add("nightMaxSpeed=" + nightMaxSpeed)
            ;
        }

        @Override
        public void encode(int version, ByteBuf buf) {
            Codec.writeDoubleWord(buf, id);
            Codec.writeDoubleWord(buf, segmentId);
            Codec.writeDoubleWord(buf, latitude);
            Codec.writeDoubleWord(buf, longitude);
            Codec.writeByte(buf, width);
            Codec.writeByte(buf, props);

            if ((props & BIT0_MASK) == BIT0_MASK) {
                Codec.writeWord(buf, maxTime);
                Codec.writeWord(buf, minTime);
            }

            if ((props & BIT1_MASK) == BIT1_MASK) {
                Codec.writeWord(buf, maxSpeed);
                Codec.writeByte(buf, duration);

                if (version > 0) {
                    Codec.writeWord(buf, nightMaxSpeed);
                }
            }
        }

        @Override
        public void decode(int version, ByteBuf buf) {
            id = Codec.readDoubleWord(buf);
            segmentId = Codec.readDoubleWord(buf);
            latitude = Codec.readDoubleWord(buf);
            longitude = Codec.readDoubleWord(buf);
            width = Codec.readByte(buf);
            props = Codec.readByte(buf);

            if ((props & BIT0_MASK) == BIT0_MASK) {
                maxTime = Codec.readWord(buf);
                minTime = Codec.readWord(buf);
            }

            if ((props & BIT1_MASK) == BIT1_MASK) {
                maxSpeed = Codec.readWord(buf);
                duration = Codec.readByte(buf);

                if (version > 0) {
                    nightMaxSpeed = Codec.readWord(buf);
                }
            }
        }


        /**
         * 获取拐点 ID
         * @return DWORD 拐点 ID
         */
        public long getId() {
            return id;
        }

        /**
         * 设置拐点 ID
         * @param id DWORD 拐点 ID
         */
        public void setId(long id) {
            this.id = id;
        }

        /**
         * 获取路段 ID
         * @return DWORD 路段 ID
         */
        public long getSegmentId() {
            return segmentId;
        }

        /**
         * 设置路段 ID
         * @param segmentId DWORD 路段 ID
         */
        public void setSegmentId(long segmentId) {
            this.segmentId = segmentId;
        }

        /**
         * 获取拐点纬度
         * @return DWORD 拐点纬度，以度为单位的纬度值乘以 10 的 6 次方，精确到百万分之一度
         */
        public long getLatitude() {
            return latitude;
        }

        /**
         * 设置拐点纬度
         * @param latitude DWORD 拐点纬度，以度为单位的纬度值乘以 10 的 6 次方，精确到百万分之一度
         */
        public void setLatitude(long latitude) {
            this.latitude = latitude;
        }

        /**
         * 获取拐点经度
         * @return DWORD 拐点经度，以度为单位的经度值乘以 10 的 6 次方，精确到百万分之一度
         */
        public long getLongitude() {
            return longitude;
        }

        /**
         * 设置拐点经度
         * @param longitude DWORD 拐点经度，以度为单位的经度值乘以 10 的 6 次方，精确到百万分之一度
         */
        public void setLongitude(long longitude) {
            this.longitude = longitude;
        }

        /**
         * 获取路段宽度，单位为米（m）
         * @return BYTE 路段宽度，单位为米（m），路段为该拐点到下一拐点
         */
        public int getWidth() {
            return width;
        }

        /**
         * 设置路段宽度
         * @param width BYTE 路段宽度，单位为米（m），路段为该拐点到下一拐点
         */
        public void setWidth(int width) {
            this.width = width;
        }

        /**
         * 获取路段属性
         * <ul>
         *     <li>bit0: 1.行驶时间；</li>
         *     <li>bit1: 1.限速；</li>
         *     <li>bit2: 0.北纬 1.南纬；</li>
         *     <li>bit3: 0.东经 1.西经；</li>
         *     <li>其他: 保留</li>
         * </ul>
         * @return BYTE 路段属性
         */
        public int getProps() {
            return props;
        }

        /**
         * 设置路段属性
         * <ul>
         *     <li>bit0: 1.行驶时间；</li>
         *     <li>bit1: 1.限速；</li>
         *     <li>bit2: 0.北纬 1.南纬；</li>
         *     <li>bit3: 0.东经 1.西经；</li>
         *     <li>其他: 保留</li>
         * </ul>
         * @param props BYTE 路段属性
         */
        public void setProps(int props) {
            this.props = props;
        }

        /**
         * 获取路段行驶过长阈值，单位为秒（s）
         * @return WORD 路段行驶过长阈值，单位为秒（s），若路段属性 0 位为 0 则没有该字段
         */
        public int getMaxTime() {
            return maxTime;
        }

        /**
         * 设置路段行驶过长阈值，单位为秒（s）
         * @param maxTime WORD 路段行驶过长阈值，单位为秒（s），若路段属性 0 位为 0 则没有该字段
         */
        public void setMaxTime(int maxTime) {
            this.maxTime = maxTime;
        }

        /**
         * 获取路段行驶不足阈值，单位为秒（s）
         * @return WORD 路段行驶不足阈值，单位为秒（s），若路段属性 0 位 为 0 则没有该字段
         */
        public int getMinTime() {
            return minTime;
        }

        /**
         * 设置路段行驶不足阈值，单位为秒（s）
         * @param minTime WORD 路段行驶不足阈值，单位为秒（s），若路段属性 0 位 为 0 则没有该字段
         */
        public void setMinTime(int minTime) {
            this.minTime = minTime;
        }

        /**
         * 获取路段最高速度，单位为 km/h
         * @return WORD 路段最高速度，单位为 km/h，若区域属性 1 位为 0 则没有该字段
         */
        public int getMaxSpeed() {
            return maxSpeed;
        }

        /**
         * 设置路段最高速度，单位为 km/h
         * @param maxSpeed WORD 路段最高速度，单位为 km/h，若区域属性 1 位为 0 则没有该字段
         */
        public void setMaxSpeed(int maxSpeed) {
            this.maxSpeed = maxSpeed;
        }

        /**
         * 获取路段超速持续时间，单位为秒（s）
         * @return BYTE 路段超速持续时间，单位为秒（s），若区域属性 1 位为 0 则没有该字段
         */
        public int getDuration() {
            return duration;
        }

        /**
         * 设置路段超速持续时间，单位为秒（s）
         * @param duration BYTE 路段超速持续时间，单位为秒（s），若区域属性 1 位为 0 则没有该字段
         */
        public void setDuration(int duration) {
            this.duration = duration;
        }

        /**
         * 获取路段夜间最高速度，单位为 km/h
         * @return WORD 路段夜间最高速度，单位为 km/h，若区域属性 1 位为 0 则没有该字段
         */
        public int getNightMaxSpeed() {
            return nightMaxSpeed;
        }

        /**
         * 设置路段夜间最高速度，单位为 km/h
         * @param nightMaxSpeed WORD 路段夜间最高速度，单位为 km/h，若区域属性 1 位为 0 则没有该字段
         */
        public void setNightMaxSpeed(int nightMaxSpeed) {
            this.nightMaxSpeed = nightMaxSpeed;
        }

    }

}
