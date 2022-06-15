package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * JT/T 消息体 0x8600 设置圆形区域 // 2019 modify
 * <br>
 * <p><b>注：</b>本条消息协议支持周期时间范围，如要限制每天的8:30-18:00，起始/结束时间设
 * 为：000000083000/000000180000，其他以此类推。</p>
 *
 * @author togger
 */
public class B8600 extends AbstractToStringJoiner implements Codec {

    /**
     * BYTE 属性
     *
     * <ul>
     *     <li>0：更新区域；</li>
     *     <li>1：追加区域；</li>
     *     <li>2：修改区域；</li>
     * </ul>
     */
    private int action;

    /** 区域列表 */
    private final List<Region> regions = new ArrayList<>();


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("action=" + action)
                .add("regions=" + regions)
        ;
    }

    @Override
    public void encode(int version, ByteBuf buf) {
        Codec.writeByte(buf, action);
        Codec.writeCountHeadedContent(buf, IntUnit.BYTE, regions, (b, that) -> {
            int count = 0;
            for (final Region region : that) {
                if (region == null) continue;
                region.encode(version, b);
                count++;
            }
            return count;
        });
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        regions.clear();
        action = Codec.readByte(buf);

        int cnt = Codec.readByte(buf);
        while (cnt-- > 0) {
            final Region region = new Region();
            region.decode(version, buf);
            regions.add(region);
        }
    }


    /**
     * 获取属性
     * <ul>
     *     <li>0：更新区域；</li>
     *     <li>1：追加区域；</li>
     *     <li>2：修改区域；</li>
     * </ul>
     * @return BYTE 属性
     * @see #ACTION_UPDATE
     * @see #ACTION_APPEND
     * @see #ACTION_MODIFY
     */
    public int getAction() {
        return action;
    }

    /**
     * 设置属性
     * <ul>
     *     <li>0：更新区域；</li>
     *     <li>1：追加区域；</li>
     *     <li>2：修改区域；</li>
     * </ul>
     * @param action BYTE 属性
     * @see #ACTION_UPDATE
     * @see #ACTION_APPEND
     * @see #ACTION_MODIFY
     */
    public void setAction(int action) {
        this.action = action;
    }

    /**
     * 获取区域列表
     * @return 区域列表
     */
    public List<Region> getRegions() {
        return regions;
    }


    /** 属性：更新 */
    public static final int ACTION_UPDATE = 0;
    /** 属性：追加 */
    public static final int ACTION_APPEND = 1;
    /** 属性：修改 */
    public static final int ACTION_MODIFY = 2;

    /** {@link Region#flags} 二进制位 0 掩码 */
    protected static final int BIT0_MASK = 0b1;
    /** {@link Region#flags} 二进制位 1 掩码 */
    protected static final int BIT1_MASK = 0b10;


    /**
     * JT/T 消息体 0x8600 圆形区域
     * @author togger
     */
    public static class Region extends AbstractToStringJoiner implements Codec {

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

        /** DWORD 中心点纬度，以度为单位的纬度值乘以 10 的 6 次方，精确到百万分之一度 */
        private long latitude;

        /** DWORD 中心点经度，以度为单位的经度值乘以 10 的 6 次方，精确到百万分之一度 */
        private long longitude;

        /** DWORD 半径，单位为米（m），路段为该拐点到下一拐点 */
        private long radius;

        /** BCD[6] 起始时间，yyMMddHHmmss，若区域属性 0 位为 0 则没有该字段 */
        private String startTime;

        /** BCD[6] 结束时间，yyMMddHHmmss，若区域属性 0 位为 0 则没有该字段 */
        private String endTime;

        /** WORD 最高速度，单位为 km/h，若区域属性 1 位为 0 则没有该字段 */
        private int maxSpeed;

        /** BYTE 超速持续时间，单位为秒（s），若区域属性 1 位为 0 则没有该字段 */
        private int duration;

        /** WORD 夜间最高速度，单位为 km/h，若区域属性 1 位为 0 则没有该字段 */
        private int nightMaxSpeed;

        /** STRING 区域名称 */
        private String name;


        @Override
        protected void toStringJoiner(StringJoiner joiner) {
            joiner
                    .add("id=" + id)
                    .add("flags=" + flags)
                    .add("latitude=" + latitude)
                    .add("longitude=" + longitude)
                    .add("radius=" + radius)
                    .add("startTime=" + (startTime == null ? "" : startTime))
                    .add("endTime=" + (endTime == null ? "" : endTime))
                    .add("maxSpeed=" + maxSpeed)
                    .add("duration=" + duration)
                    .add("nightMaxSpeed=" + nightMaxSpeed)
                    .add("name=" + (name == null ? "" : name))
            ;
        }

        @Override
        public void encode(int version, ByteBuf buf) {
            Codec.writeDoubleWord(buf, id);
            Codec.writeWord(buf, flags);
            Codec.writeDoubleWord(buf, latitude);
            Codec.writeDoubleWord(buf, longitude);
            Codec.writeDoubleWord(buf, radius);

            if ((flags & BIT0_MASK) == BIT0_MASK) {
                Codec.writeBcd(buf, startTime, 6);
                Codec.writeBcd(buf, endTime, 6);
            }

            if ((flags & BIT1_MASK) == BIT1_MASK) {
                Codec.writeWord(buf, maxSpeed);
                Codec.writeByte(buf, duration);

                if (version > 0) {
                    Codec.writeWord(buf, nightMaxSpeed);
                }
            }

            if (version > 0) {
                Codec.writeString(buf, IntUnit.WORD, name);
            }
        }

        @Override
        public void decode(int version, ByteBuf buf) {
            id = Codec.readDoubleWord(buf);
            flags = Codec.readWord(buf);
            latitude = Codec.readDoubleWord(buf);
            longitude = Codec.readDoubleWord(buf);
            radius = Codec.readDoubleWord(buf);

            if ((flags & BIT0_MASK) == BIT0_MASK) {
                startTime = Codec.readBcd(buf,6, false);
                endTime = Codec.readBcd(buf,6, false);
            }

            if ((flags & BIT1_MASK) == BIT1_MASK) {
                maxSpeed = Codec.readWord(buf);
                duration = Codec.readByte(buf);

                if (version > 0) {
                    nightMaxSpeed = Codec.readWord(buf);
                }
            }

            if (version > 0) {
                name = Codec.readString(buf, IntUnit.WORD);
            }
        }


        /**
         * 获取区域 ID
         * @return DWORD 区域 ID
         */
        public long getId() {
            return id;
        }

        /**
         * 设置区域 ID
         * @param id DWORD 区域 ID
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
         * 获取中心点纬度
         * @return DWORD 中心点纬度，以度为单位的纬度值乘以 10 的 6 次方，精确到百万分之一度
         */
        public long getLatitude() {
            return latitude;
        }

        /**
         * 设置中心点纬度
         * @param latitude DWORD 中心点纬度，以度为单位的纬度值乘以 10 的 6 次方，精确到百万分之一度
         */
        public void setLatitude(long latitude) {
            this.latitude = latitude;
        }

        /**
         * 获取中心点经度
         * @return DWORD 中心点经度，以度为单位的经度值乘以 10 的 6 次方，精确到百万分之一度
         */
        public long getLongitude() {
            return longitude;
        }

        /**
         * 设置中心点经度
         * @param longitude DWORD 中心点经度，以度为单位的经度值乘以 10 的 6 次方，精确到百万分之一度
         */
        public void setLongitude(long longitude) {
            this.longitude = longitude;
        }

        /**
         * 获取半径，单位为米（m）
         * @return DWORD 半径，单位为米（m），路段为该拐点到下一拐点
         */
        public long getRadius() {
            return radius;
        }

        /**
         * 设置半径，单位为米（m）
         * @param radius DWORD 半径，单位为米（m），路段为该拐点到下一拐点
         */
        public void setRadius(long radius) {
            this.radius = radius;
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
         * @return WORD 最高速度，单位为 km/h，若区域属性 1 位为 0 则没有该字段
         */
        public int getMaxSpeed() {
            return maxSpeed;
        }

        /**
         * 设置最高速度，单位为 km/h
         * @param maxSpeed WORD 最高速度，单位为 km/h，若区域属性 1 位为 0 则没有该字段
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

    }

}
