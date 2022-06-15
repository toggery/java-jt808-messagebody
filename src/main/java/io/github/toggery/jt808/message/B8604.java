package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 *  JT/T 消息体 0x8604 设置多边形区域 // 2019 modify
 *
 * @author togger
 */
public class B8604 extends AbstractToStringJoiner implements Codec {

    /** DWORD 区域 ID */
    private long id;

    /**
     * WORD 区域属性
     *
     * <ul>
     *     <li>bit0: 是否启用起始时间与结束时间的判断规则，0.否 1.是；</li>
     *     <li>bit1: 是否启用最高速度、超速持续时间和夜间最高速度的判断规则，0.否 1.是；</li>
     *     <li>bit2: 进区域是否报警给驾驶员，0.否 1.是；</li>
     *     <li>bit3: 进区域是否报警给平台，0.否 1.是；</li>
     *     <li>bit4: 出区域是否报警给驾驶员，0.否 1.是；</li>
     *     <li>bit5: 出区域是否报警给平台，0.否 1.是；</li>
     *     <li>bit6: 0.北纬 1.南纬；</li>
     *     <li>bit7: 0.东经 1.西经；</li>
     *     <li>bit8: 0.允许开门 1.禁止开门；</li>
     *     <li>bit9~13: 保留</li>
     *     <li>bit14: 0.进区域开启通信模块 1.进区域关闭通信模块；</li>
     *     <li>bit15: 0.进区域不采集 GNSS 详细定位数据 1.进区域采集 GNSS 详细定位数据；</li>
     * </ul>
     */
    private int flags;

    /** BCD[6] 起始时间，yyMMddHHmmss，若区域属性 0 位为 0 则没有该字段 */
    private String startTime;

    /** BCD[6] 结束时间，yyMMddHHmmss，若区域属性 0 位为 0 则没有该字段 */
    private String endTime;

    /** WORD 最高速度，单位为 km/h，若区域属性1位为 0 则没有该字段 */
    private int maxSpeed;

    /** BYTE 超速持续时间，单位为秒（s），若区域属性 1 位为 0 则没有该字段 */
    private int duration;

    /** WORD 夜间最高速度，单位为 km/h，若区域属性 1 位为 0 则没有该字段 */
    private int nightMaxSpeed;

    /** STRING 区域名称 */
    private String name;

    /** 区域顶点列表 */
    private final List<Point> points = new ArrayList<>();


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("id=" + id)
                .add("flags=" + flags)
                .add("startTime=" + (startTime == null ? "" : startTime))
                .add("endTime=" + (endTime == null ? "" : endTime))
                .add("maxSpeed=" + maxSpeed)
                .add("duration=" + duration)
                .add("nightMaxSpeed=" + nightMaxSpeed)
                .add("name=" + (name == null ? "" : name))
                .add("points=" + points)
        ;
    }

    @Override
    public void encode(int version, ByteBuf buf) {
        Codec.writeDoubleWord(buf, id);
        Codec.writeWord(buf, flags);

        if ((flags & BIT0_MASK) == BIT0_MASK) {
            Codec.writeBcd(buf, startTime, 6);
            Codec.writeBcd(buf, endTime, 6);
        }

        final boolean limitSpeed = (flags & BIT1_MASK) == BIT1_MASK;
        if (limitSpeed) {
            Codec.writeWord(buf, maxSpeed);
            Codec.writeByte(buf, duration);
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
            if (limitSpeed) {
                Codec.writeWord(buf, nightMaxSpeed);
            }
            Codec.writeString(buf, IntUnit.WORD, name);
        }
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        points.clear();
        id = Codec.readDoubleWord(buf);
        flags = Codec.readWord(buf);

        if ((flags & BIT0_MASK) == BIT0_MASK) {
            startTime = Codec.readBcd(buf,6, false);
            endTime = Codec.readBcd(buf,6, false);
        }

        final boolean limitSpeed = (flags & BIT1_MASK) == BIT1_MASK;
        if (limitSpeed) {
            maxSpeed = Codec.readWord(buf);
            duration = Codec.readByte(buf);
        }

        int cnt = Codec.readWord(buf);
        while (cnt-- > 0) {
            final Point point = new Point();
            point.decode(version, buf);
            points.add(point);
        }

        if (version > 0) {
            if (limitSpeed) {
                nightMaxSpeed = Codec.readWord(buf);
            }
            name = Codec.readString(buf, IntUnit.WORD);
        }
    }


    /**
     * 获取区域属性
     * @return WORD 区域属性
     */
    public long getId() {
        return id;
    }

    /**
     * 设置区域属性
     * @param id WORD 区域属性
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 获取区域属性
     * <ul>
     *     <li>bit0: 是否启用起始时间与结束时间的判断规则，0.否 1.是；</li>
     *     <li>bit1: 是否启用最高速度、超速持续时间和夜间最高速度的判断规则，0.否 1.是；</li>
     *     <li>bit2: 进区域是否报警给驾驶员，0.否 1.是；</li>
     *     <li>bit3: 进区域是否报警给平台，0.否 1.是；</li>
     *     <li>bit4: 出区域是否报警给驾驶员，0.否 1.是；</li>
     *     <li>bit5: 出区域是否报警给平台，0.否 1.是；</li>
     *     <li>bit6: 0.北纬 1.南纬；</li>
     *     <li>bit7: 0.东经 1.西经；</li>
     *     <li>bit8: 0.允许开门 1.禁止开门；</li>
     *     <li>bit9~13: 保留</li>
     *     <li>bit14: 0.进区域开启通信模块 1.进区域关闭通信模块；</li>
     *     <li>bit15: 0.进区域不采集 GNSS 详细定位数据 1.进区域采集 GNSS 详细定位数据；</li>
     * </ul>
     * @return WORD 区域属性
     */
    public int getFlags() {
        return flags;
    }

    /**
     * 设置区域属性
     * <ul>
     *     <li>bit0: 是否启用起始时间与结束时间的判断规则，0.否 1.是；</li>
     *     <li>bit1: 是否启用最高速度、超速持续时间和夜间最高速度的判断规则，0.否 1.是；</li>
     *     <li>bit2: 进区域是否报警给驾驶员，0.否 1.是；</li>
     *     <li>bit3: 进区域是否报警给平台，0.否 1.是；</li>
     *     <li>bit4: 出区域是否报警给驾驶员，0.否 1.是；</li>
     *     <li>bit5: 出区域是否报警给平台，0.否 1.是；</li>
     *     <li>bit6: 0.北纬 1.南纬；</li>
     *     <li>bit7: 0.东经 1.西经；</li>
     *     <li>bit8: 0.允许开门 1.禁止开门；</li>
     *     <li>bit9~13: 保留</li>
     *     <li>bit14: 0.进区域开启通信模块 1.进区域关闭通信模块；</li>
     *     <li>bit15: 0.进区域不采集 GNSS 详细定位数据 1.进区域采集 GNSS 详细定位数据；</li>
     * </ul>
     * @param flags WORD 区域属性
     */
    public void setFlags(int flags) {
        this.flags = flags;
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
     * 获取最高速度，单位为 km/h
     * @return WORD 最高速度，单位为 km/h，若区域属性1位为 0 则没有该字段
     */
    public int getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * 设置最高速度，单位为 km/h
     * @param maxSpeed WORD 最高速度，单位为 km/h，若区域属性1位为 0 则没有该字段
     */
    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * 获取超速持续时间，单位为秒（s）
     * @return BYTE 超速持续时间，单位为秒（s），若区域属性 1 位为 0 则没有该字段
     */
    public int getDuration() {
        return duration;
    }

    /**
     * 设置超速持续时间，单位为秒（s）
     * @param duration BYTE 超速持续时间，单位为秒（s），若区域属性 1 位为 0 则没有该字段
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * 获取夜间最高速度，单位为 km/h
     * @return WORD 夜间最高速度，单位为 km/h，若区域属性 1 位为 0 则没有该字段
     */
    public int getNightMaxSpeed() {
        return nightMaxSpeed;
    }

    /**
     * 设置夜间最高速度，单位为 km/h
     * @param nightMaxSpeed WORD 夜间最高速度，单位为 km/h，若区域属性 1 位为 0 则没有该字段
     */
    public void setNightMaxSpeed(int nightMaxSpeed) {
        this.nightMaxSpeed = nightMaxSpeed;
    }

    /**
     * 获取区域名称
     * @return STRING 区域名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置区域名称
     * @param name STRING 区域名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取区域顶点列表
     * @return 区域顶点列表
     */
    public List<Point> getPoints() {
        return points;
    }

    /** {@link #flags} 二进制位 0 掩码 */
    protected static final int BIT0_MASK = 0b1;
    /** {@link #flags} 二进制位 1 掩码 */
    protected static final int BIT1_MASK = 0b10;

    /**
     * JT/T 消息体 0x8604 多边形区域顶点
     * @author togger
     */
    public static class Point extends AbstractToStringJoiner implements Codec {

        /** DWORD 顶点纬度，以度为单位的纬度值乘以 10 的 6 次方，精确到百万分之一度 */
        private long latitude;

        /** DWORD 顶点经度，以度为单位的经度值乘以 10 的 6 次方，精确到百万分之一度 */
        private long longitude;

        @Override
        protected void toStringJoiner(StringJoiner joiner) {
            joiner
                    .add("latitude=" + latitude)
                    .add("longitude=" + longitude)
            ;
        }

        @Override
        public void encode(int version, ByteBuf buf) {
            Codec.writeDoubleWord(buf, latitude);
            Codec.writeDoubleWord(buf, longitude);
        }

        @Override
        public void decode(int version, ByteBuf buf) {
            latitude = Codec.readDoubleWord(buf);
            longitude = Codec.readDoubleWord(buf);
        }


        /**
         * 获取顶点纬度
         * @return DWORD 顶点纬度，以度为单位的纬度值乘以 10 的 6 次方，精确到百万分之一度
         */
        public long getLatitude() {
            return latitude;
        }

        /**
         * 设置顶点纬度
         * @param latitude DWORD 顶点纬度，以度为单位的纬度值乘以 10 的 6 次方，精确到百万分之一度
         */
        public void setLatitude(long latitude) {
            this.latitude = latitude;
        }

        /**
         * 获取顶点经度
         * @return DWORD 顶点经度，以度为单位的经度值乘以 10 的 6 次方，精确到百万分之一度
         */
        public long getLongitude() {
            return longitude;
        }

        /**
         * 设置顶点经度
         * @param longitude DWORD 顶点经度，以度为单位的经度值乘以 10 的 6 次方，精确到百万分之一度
         */
        public void setLongitude(long longitude) {
            this.longitude = longitude;
        }

    }

}
