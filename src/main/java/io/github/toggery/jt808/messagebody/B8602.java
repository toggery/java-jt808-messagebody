package io.github.toggery.jt808.messagebody;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * JT/T 消息体：0x8602 设置矩形区域 // 2019 modify
 *
 * @author togger
 */
public class B8602 extends AbstractToStringJoiner {

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


    /**
     * JT/T 消息体：0x8602 矩形区域
     *
     * @author togger
     */
    public static class Region extends AbstractToStringJoiner {

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
        private int props;

        /** DWORD 左上点纬度，以度为单位的纬度值乘以 10 的 6 次方，精确到百万分之一度 */
        private long latitudeTopLeft;

        /** DWORD 左上点经度，以度为单位的经度值乘以 10 的 6 次方，精确到百万分之一度 */
        private long longitudeTopLeft;

        /** DWORD 右下点纬度，以度为单位的纬度值乘以 10 的 6 次方，精确到百万分之一度 */
        private long latitudeBottomRight;

        /** DWORD 右下点经度，以度为单位的经度值乘以 10 的 6 次方，精确到百万分之一度 */
        private long longitudeBottomRight;

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
                    .add(HexUtil.wordString("props=", props))
                    .add("latitudeTopLeft=" + latitudeTopLeft)
                    .add("longitudeTopLeft=" + longitudeTopLeft)
                    .add("latitudeBottomRight=" + latitudeBottomRight)
                    .add("longitudeBottomRight=" + longitudeBottomRight)
                    .add("startTime=" + (startTime == null ? "" : startTime))
                    .add("endTime=" + (endTime == null ? "" : endTime))
                    .add("maxSpeed=" + maxSpeed)
                    .add("duration=" + duration)
                    .add("nightMaxSpeed=" + nightMaxSpeed)
                    .add("name=" + (name == null ? "" : name))
            ;
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
        public int getProps() {
            return props;
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
         * @param props WORD 区域属性
         */
        public void setProps(int props) {
            this.props = props;
        }

        /**
         * 获取左上点纬度
         * @return DWORD 左上点纬度，以度为单位的纬度值乘以 10 的 6 次方，精确到百万分之一度
         */
        public long getLatitudeTopLeft() {
            return latitudeTopLeft;
        }

        /**
         * 设置左上点纬度
         * @param latitudeTopLeft DWORD 左上点纬度，以度为单位的纬度值乘以 10 的 6 次方，精确到百万分之一度
         */
        public void setLatitudeTopLeft(long latitudeTopLeft) {
            this.latitudeTopLeft = latitudeTopLeft;
        }

        /**
         * 获取左上点经度
         * @return DWORD 左上点经度，以度为单位的经度值乘以 10 的 6 次方，精确到百万分之一度
         */
        public long getLongitudeTopLeft() {
            return longitudeTopLeft;
        }

        /**
         * 设置左上点经度
         * @param longitudeTopLeft DWORD 左上点经度，以度为单位的经度值乘以 10 的 6 次方，精确到百万分之一度
         */
        public void setLongitudeTopLeft(long longitudeTopLeft) {
            this.longitudeTopLeft = longitudeTopLeft;
        }

        /**
         * 获取右下点纬度
         * @return DWORD 右下点纬度，以度为单位的纬度值乘以 10 的 6 次方，精确到百万分之一度
         */
        public long getLatitudeBottomRight() {
            return latitudeBottomRight;
        }

        /**
         * 设置右下点纬度
         * @param latitudeBottomRight DWORD 右下点纬度，以度为单位的纬度值乘以 10 的 6 次方，精确到百万分之一度
         */
        public void setLatitudeBottomRight(long latitudeBottomRight) {
            this.latitudeBottomRight = latitudeBottomRight;
        }

        /**
         * 获取右下点经度
         * @return DWORD 右下点经度，以度为单位的经度值乘以 10 的 6 次方，精确到百万分之一度
         */
        public long getLongitudeBottomRight() {
            return longitudeBottomRight;
        }

        /**
         * 设置右下点经度
         * @param longitudeBottomRight DWORD 右下点经度，以度为单位的经度值乘以 10 的 6 次方，精确到百万分之一度
         */
        public void setLongitudeBottomRight(long longitudeBottomRight) {
            this.longitudeBottomRight = longitudeBottomRight;
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
