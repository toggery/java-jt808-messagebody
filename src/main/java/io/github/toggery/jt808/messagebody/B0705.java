package io.github.toggery.jt808.messagebody;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * JT/T 消息体：0x0705 CAN 总线数据上传 // 2019 modify
 *
 * @author togger
 */
public class B0705 extends AbstractToStringJoiner {

    /** BCD[5] CAN 总线数据接收时间，第 1 条 CAN 总线数据的接收时间，HHmmssmsms */
    private String time;

    /** CAN 总线数据列表 */
    private final List<Can> cans = new ArrayList<>();


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("time=" + (time == null ? "" : time))
                .add("cans=" + cans)
        ;
    }


    /**
     * 获取 CAN 总线数据接收时间
     * @return BCD[5] CAN 总线数据接收时间，第 1 条 CAN 总线数据的接收时间，HHmmssmsms
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置 CAN 总线数据接收时间
     * @param time BCD[5] CAN 总线数据接收时间，第 1 条 CAN 总线数据的接收时间，HHmmssmsms
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 获取 CAN 总线数据列表
     * @return CAN 总线数据列表
     */
    public List<Can> getCans() {
        return cans;
    }


    /**
     * JT/T 消息体：0x0705 CAN 总线
     *
     * @author togger
     */
    public static class Can extends AbstractToStringJoiner {

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


        @Override
        protected void toStringJoiner(StringJoiner joiner) {
            joiner
                    .add(HexUtil.doubleWordString("id=", id))
                    .add("data=" + (data == null ? "" : HexUtil.dump(data)))
            ;
        }


        /**
         * 获取 CAN ID
         * <ul>
         *     <li>bit31 表示 CAN 通道号，0：CAN1，1：CAN2；</li>
         *     <li>bit30 表示帧类型，0：标准帧，1：扩展帧；</li>
         *     <li>bit29 表示数据采集方式，0：原始数据，1：采集区间的平均值；</li>
         *     <li>bit28-bit0 表示 CAN 总线 ID。</li>
         * </ul>
         * @return BYTE[4] / DWORD CAN ID
         */
        public long getId() {
            return id;
        }

        /**
         * 设置 CAN ID
         * <ul>
         *     <li>bit31 表示 CAN 通道号，0：CAN1，1：CAN2；</li>
         *     <li>bit30 表示帧类型，0：标准帧，1：扩展帧；</li>
         *     <li>bit29 表示数据采集方式，0：原始数据，1：采集区间的平均值；</li>
         *     <li>bit28-bit0 表示 CAN 总线 ID。</li>
         * </ul>
         * @param id BYTE[4] / DWORD CAN ID
         */
        public void setId(long id) {
            this.id = id;
        }

        /**
         * 获取 CAN 数据
         * @return BYTE[8] CAN 数据
         */
        public byte[] getData() {
            return data;
        }

        /**
         * 设置 CAN 数据
         * @param data BYTE[8] CAN 数据
         */
        public void setData(byte[] data) {
            this.data = data;
        }

    }

}
