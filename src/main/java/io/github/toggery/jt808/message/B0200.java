package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * JT/T 消息体 0x0200 位置信息汇报 // 2019 modify
 *
 * @author togger
 */
public class B0200 extends AbstractToStringJoiner implements Codec {

    /** 实例化一个新的 {@link B0200}，不排除附加信息（编码解码） */
    public B0200() {
        this(false);
    }

    /**
     * 实例化一个新的 {@link B0200}
     * @param excludeExtras 是否排除附加信息（编码解码）
     */
    protected B0200(boolean excludeExtras) {
        this.excludeExtras = excludeExtras;
    }


    /** DWORD 报警标志位，定义见表 25 */
    private long alarmBits;

    /** DWORD 状态位，定义见表 24 */
    private long statusBits;

    /** DWORD 纬度，以度为单位的纬度值乘以 10 的 6 次方，精确到百万分之一度 */
    private long latitude;

    /** DWORD 经度，以度为单位的经度值乘以 10 的 6 次方，精确到百万分之一度 */
    private long longitude;

    /** WORD 高程，海拔高度，单位为米（m） */
    private int altitude;

    /** WORD 速度，1/10km/h */
    private int speed;

    /** WORD 方向，0-359，正北为 0，顺时针 */
    private int direction;

    /** BCD[6] 时间，yyMMddHHmmss（GMT+8 时间，本标准中之后涉及的时间均采用此时区） */
    private String time;


    /** 是否排除附加信息（编码解码） */
    private boolean excludeExtras;

    /** 未知的附加信息 */
    private Map<String, String> unknownExtras;

    /** 0x01 DWORD 里程，单位为 0.1 km，对应车上里程表读数 */
    private Long x01;
    /** 0x02 WORD 油量，单位为 0.1 L，对应车上油量表读数 */
    private Integer x02;
    /** 0x03 WORD 行驶记录功能获取的速度，单位为 0.1km/h */
    private Integer x03;
    /** 0x04 WORD 需要人工确认报警事件的 ID，从 1 开始计数 */
    private Integer x04;

    /** 0x05 BYTE[30] 胎压，单位为 Pa，标定轮子的顺序为从车头开始从左到右顺序排列，定长 30 字节，多余的字节为 0xFF，表示无效数据 // 2019 new */
    private byte[] x05;
    /** 0x06 SHORT 车厢温度，单位为摄氏度，取值范围为 -32767 ~ 32767，最高位为1表示负数 // 2019 new */
    private Short x06;

    /** 0x11 超速报警附加信息，见表 28 */
    private B0200_11 x11;
    /** 0x12 进出区域/路线报警附加信息，见表 29 */
    private B0200_12 x12;
    /** 0x13 路段行驶时间不足/过长报警附加信息，见表 30 */
    private B0200_13 x13;

    /** 0x25 DWORD 扩展车辆信号状态位，参数项格式和定义见表 31 */
    private Long x25;

    /** 0x2A WORD IO 状态位，参数项格式和定义见表 32 */
    private Integer x2A;
    /** 0x2B DWORD 模拟量，bit[0~15]，AD0；bit[l6~31]，AD1 */
    private Long x2B;

    /** 0x30 BYTE 无线通信网络信号强度 */
    private Integer x30;
    /** 0x31 BYTE GNSS 定位卫星数 */
    private Integer x31;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add(IntUtil.doubleWordHexString("alarmBits=", alarmBits))
                .add(IntUtil.doubleWordHexString("statusBits=", statusBits))
                .add("latitude=" + latitude)
                .add("longitude=" + longitude)
                .add("altitude=" + altitude)
                .add("speed=" + speed)
                .add("direction=" + direction)
                .add("time=" + (time == null ? "" : time))
                .add("excludeExtras=" + excludeExtras)
        ;
        if (excludeExtras) return;

        joiner
                .add("unknownExtras=" + (unknownExtras == null ? "" : unknownExtras))
                .add("x01=" + (x01 == null ? "" : x01))
                .add("x02=" + (x02 == null ? "" : x02))
                .add("x03=" + (x03 == null ? "" : x03))
                .add("x04=" + (x04 == null ? "" : x04))
                .add("x05=" + (x05 == null ? "" : ByteBufUtil.hexDump(x05)))
                .add("x06=" + (x06 == null ? "" : x06))
                .add("x11=" + (x11 == null ? "" : x11))
                .add("x12=" + (x12 == null ? "" : x12))
                .add("x13=" + (x13 == null ? "" : x13))
                .add("x25=" + (x25 == null ? "" : IntUtil.doubleWordHexString(x25)))
                .add("x2A=" + (x2A == null ? "" : IntUtil.wordHexString(x2A)))
                .add("x2B=" + (x2B == null ? "" : IntUtil.doubleWordHexString(x2B)))
                .add("x30=" + (x30 == null ? "" : x30))
                .add("x31=" + (x31 == null ? "" : x31))
        ;
    }

    /**
     * 编码附加信息
     *
     * @param version 版本号
     * @param fieldEncoder 附加信息编码方法
     */
    protected void encodeParams(int version, CountedFieldEncoder<Integer> fieldEncoder) {
        Extras.CODECS.values().forEach(f -> f.encode(version, fieldEncoder, this));
    }

    /** 清除附加信息 */
    protected void clearParams() {
        Extras.CODECS.values().forEach(f -> f.clear(this));
    }

    /**
     * 解码附加信息
     * @param id 附加信息 ID
     * @param version 版本号
     * @param buf 字节缓冲区
     * @return 是否成功
     */
    protected boolean decodeParam(int id, int version, ByteBuf buf) {
        final FieldCodec<Integer, B0200, ?> param = Extras.CODECS.get(id);
        if (param != null) {
            param.decode(version, buf, this);
            return true;
        }
        return false;
    }

    @Override
    public void encode(int version, ByteBuf buf) {
        Codec.writeDoubleWord(buf, alarmBits);
        Codec.writeDoubleWord(buf, statusBits);
        Codec.writeDoubleWord(buf, latitude);
        Codec.writeDoubleWord(buf, longitude);
        Codec.writeWord(buf, altitude);
        Codec.writeWord(buf, speed);
        Codec.writeWord(buf, direction);
        Codec.writeBcd(buf, time, 6);

        if (excludeExtras) return;

        final CountedFieldEncoder<Integer> encoder =
                new CountedLengthHeadedFieldEncoder<>(buf, Codec::writeByte, IntUnit.BYTE);
        encodeParams(version, encoder);
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        alarmBits = Codec.readDoubleWord(buf);
        statusBits = Codec.readDoubleWord(buf);
        latitude = Codec.readDoubleWord(buf);
        longitude = Codec.readDoubleWord(buf);
        altitude = Codec.readWord(buf);
        speed = Codec.readWord(buf);
        direction = Codec.readWord(buf);
        time = Codec.readBcd(buf, 6, false);

        if (excludeExtras) return;
        unknownExtras = null;
        clearParams();

        while (buf.isReadable()) {
            final int id = Codec.readByte(buf);
            final ByteBuf fieldBuf = Codec.readSlice(buf, IntUnit.BYTE);
            if (!decodeParam(id, version, fieldBuf)) {
                putUnknownExtra(id, fieldBuf);
            }
        }
    }


    /**
     * 获取报警标志位
     * @return DWORD 报警标志位，定义见表 25
     */
    public long getAlarmBits() {
        return alarmBits;
    }

    /**
     * 设置报警标志位
     * @param alarmBits DWORD 报警标志位，定义见表 25
     */
    public void setAlarmBits(long alarmBits) {
        this.alarmBits = alarmBits;
    }

    /**
     * 获取状态位
     * @return DWORD 状态位，定义见表 24
     */
    public long getStatusBits() {
        return statusBits;
    }

    /**
     * 设置状态位
     * @param statusBits DWORD 状态位，定义见表 24
     */
    public void setStatusBits(long statusBits) {
        this.statusBits = statusBits;
    }

    /**
     * 获取纬度
     * @return DWORD 纬度，以度为单位的纬度值乘以 10 的 6 次方，精确到百万分之一度
     */
    public long getLatitude() {
        return latitude;
    }

    /**
     * 设置纬度
     * @param latitude DWORD 纬度，以度为单位的纬度值乘以 10 的 6 次方，精确到百万分之一度
     */
    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取经度
     * @return DWORD 经度，以度为单位的经度值乘以 10 的 6 次方，精确到百万分之一度
     */
    public long getLongitude() {
        return longitude;
    }

    /**
     * 设置经度
     * @param longitude DWORD 经度，以度为单位的经度值乘以 10 的 6 次方，精确到百万分之一度
     */
    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取高程，海拔高度，单位为米（m）
     * @return WORD 高程，海拔高度，单位为米（m）
     */
    public int getAltitude() {
        return altitude;
    }

    /**
     * 设置高程，海拔高度，单位为米（m）
     * @param altitude WORD 高程，海拔高度，单位为米（m）
     */
    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    /**
     * 获取速度，1/10km/h
     * @return WORD 速度，1/10km/h
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * 设置速度，1/10km/h
     * @param speed WORD 速度，1/10km/h
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * 获取方向
     * @return WORD 方向，0-359，正北为 0，顺时针
     */
    public int getDirection() {
        return direction;
    }

    /**
     * 设置方向
     * @param direction WORD 方向，0-359，正北为 0，顺时针
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * 获取时间
     * @return BCD[6] 时间，yyMMddHHmmss（GMT+8 时间，本标准中之后涉及的时间均采用此时区）
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置时间
     * @param time BCD[6] 时间，yyMMddHHmmss（GMT+8 时间，本标准中之后涉及的时间均采用此时区）
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 获取是否排除附加信息（编码解码）
     * @return 是否排除附加信息（编码解码）
     */
    public boolean getExcludeExtras() {
        return excludeExtras;
    }

    /**
     * 设置是否排除附加信息（编码解码）
     * @param excludeExtras 是否排除附加信息（编码解码）
     */
    public void setExcludeExtras(boolean excludeExtras) {
        this.excludeExtras = excludeExtras;
    }

    /**
     * 获取解码时未知的附加信息
     * @return 解码时未知的附加信息
     */
    public Map<String, String> getUnknownExtras() {
        return unknownExtras;
    }

    /**
     * 设置解码时未知的附加信息
     * @param unknownExtras 解码时未知的附加信息
     */
    public void setUnknownExtras(Map<String, String> unknownExtras) {
        this.unknownExtras = unknownExtras;
    }

    /**
     * 获取里程，单位为 0.1 km，对应车上里程表读数
     * @return 0x01 DWORD 里程，单位为 0.1 km，对应车上里程表读数
     */
    public Long getX01() {
        return x01;
    }

    /**
     * 设置里程，单位为 0.1 km，对应车上里程表读数
     * @param x01 0x01 DWORD 里程，单位为 0.1 km，对应车上里程表读数
     */
    public void setX01(Long x01) {
        this.x01 = x01;
    }

    /**
     * 获取油量，单位为 0.1L，对应车上油量表读数
     * @return 0x02 WORD 油量，单位为 0.1L，对应车上油量表读数
     */
    public Integer getX02() {
        return x02;
    }

    /**
     * 设置油量，单位为 0.1 L，对应车上油量表读数
     * @param x02 0x02 WORD 油量，单位为 0.1 L，对应车上油量表读数
     */
    public void setX02(Integer x02) {
        this.x02 = x02;
    }

    /**
     * 获取行驶记录功能获取的速度，单位为 0.1km/h
     * @return 0x03 WORD 行驶记录功能获取的速度，单位为 0.1km/h
     */
    public Integer getX03() {
        return x03;
    }

    /**
     * 设置行驶记录功能获取的速度，单位为 0.1km/h
     * @param x03 0x03 WORD 行驶记录功能获取的速度，单位为 0.1km/h
     */
    public void setX03(Integer x03) {
        this.x03 = x03;
    }

    /**
     * 获取需要人工确认报警事件的 ID，从 1 开始计数
     * @return 0x04 WORD 需要人工确认报警事件的 ID，从 1 开始计数
     */
    public Integer getX04() {
        return x04;
    }

    /**
     * 设置需要人工确认报警事件的 ID，从 1 开始计数
     * @param x04 0x04 WORD 需要人工确认报警事件的 ID，从 1 开始计数
     */
    public void setX04(Integer x04) {
        this.x04 = x04;
    }

    /**
     * 获取胎压，单位为 Pa // 2019 new
     * @return 0x05 BYTE[30] 胎压，单位为 Pa，标定轮子的顺序为从车头开始从左到右顺序排列，定长 30 字节，多余的字节为 0xFF，表示无效数据 // 2019 new
     */
    public byte[] getX05() {
        return x05;
    }

    /**
     * 设置胎压，单位为 Pa // 2019 new
     * @param x05 0x05 BYTE[30] 胎压，单位为 Pa，标定轮子的顺序为从车头开始从左到右顺序排列，定长 30 字节，多余的字节为 0xFF，表示无效数据 // 2019 new
     */
    public void setX05(byte[] x05) {
        this.x05 = x05;
    }

    /**
     * 获取车厢温度，单位为摄氏度 // 2019 new
     * @return 0x06 SHORT 车厢温度，单位为摄氏度，取值范围为 -32767 ~ 32767，最高位为1表示负数 // 2019 new
     */
    public Short getX06() {
        return x06;
    }

    /**
     * 设置车厢温度，单位为摄氏度 // 2019 new
     * @param x06 0x06 SHORT 车厢温度，单位为摄氏度，取值范围为 -32767 ~ 32767，最高位为1表示负数 // 2019 new
     */
    public void setX06(Short x06) {
        this.x06 = x06;
    }

    /**
     * 获取超速报警
     * @return 0x11 超速报警附加信息，见表 28
     */
    public B0200_11 getX11() {
        return x11;
    }

    /**
     * 设置超速报警
     * @param x11 0x11 超速报警附加信息，见表 28
     */
    public void setX11(B0200_11 x11) {
        this.x11 = x11;
    }

    /**
     * 获取进出区域/路线报警
     * @return 0x12 进出区域/路线报警附加信息，见表 29
     */
    public B0200_12 getX12() {
        return x12;
    }

    /**
     * 设置进出区域/路线报警
     * @param x12 0x12 进出区域/路线报警附加信息，见表 29
     */
    public void setX12(B0200_12 x12) {
        this.x12 = x12;
    }

    /**
     * 获取路段行驶时间不足/过长报警
     * @return 0x13 路段行驶时间不足/过长报警附加信息，见表 30
     */
    public B0200_13 getX13() {
        return x13;
    }

    /**
     * 设置路段行驶时间不足/过长报警
     * @param x13 0x13 路段行驶时间不足/过长报警附加信息，见表 30
     */
    public void setX13(B0200_13 x13) {
        this.x13 = x13;
    }

    /**
     * 获取扩展车辆信号状态位
     * @return 0x25 DWORD 扩展车辆信号状态位，参数项格式和定义见表 31
     */
    public Long getX25() {
        return x25;
    }

    /**
     * 设置扩展车辆信号状态位
     * @param x25 0x25 DWORD 扩展车辆信号状态位，参数项格式和定义见表 31
     */
    public void setX25(Long x25) {
        this.x25 = x25;
    }

    /**
     * 获取 IO 状态位
     * @return 0x2A WORD IO 状态位，参数项格式和定义见表 32
     */
    public Integer getX2A() {
        return x2A;
    }

    /**
     * 设置 IO 状态位
     * @param x2A 0x2A WORD IO 状态位，参数项格式和定义见表 32
     */
    public void setX2A(Integer x2A) {
        this.x2A = x2A;
    }

    /**
     * 获取模拟量
     * @return 0x2B DWORD 模拟量，bit[0~15]，AD0；bit[l6~31]，AD1
     */
    public Long getX2B() {
        return x2B;
    }

    /**
     * 设置模拟量
     * @param x2B 0x2B DWORD 模拟量，bit[0~15]，AD0；bit[l6~31]，AD1
     */
    public void setX2B(Long x2B) {
        this.x2B = x2B;
    }

    /**
     * 获取无线通信网络信号强度
     * @return 0x30 BYTE 无线通信网络信号强度
     */
    public Integer getX30() {
        return x30;
    }

    /**
     * 设置无线通信网络信号强度
     * @param x30 0x30 BYTE 无线通信网络信号强度
     */
    public void setX30(Integer x30) {
        this.x30 = x30;
    }

    /**
     * 获取 GNSS 定位卫星数
     * @return 0x31 BYTE GNSS 定位卫星数
     */
    public Integer getX31() {
        return x31;
    }

    /**
     * 设置 GNSS 定位卫星数
     * @param x31 BYTE GNSS 定位卫星数
     */
    public void setX31(Integer x31) {
        this.x31 = x31;
    }


    private void putUnknownExtra(int id, ByteBuf buf) {
        if (unknownExtras == null) {
            unknownExtras = new LinkedHashMap<>();
        }
        unknownExtras.put(IntUtil.byteHexString(id), ByteBufUtil.hexDump(buf));
    }

    private final static class Extras {

        private final static Map<Integer, FieldCodec<Integer, B0200, ?>> CODECS = new LinkedHashMap<>();

        static {

            // 0x01 DWORD 里程，单位为 0.1km，对应车上里程表读数
            register(0x01, B0200::getX01, B0200::setX01, ver -> Codec::writeDoubleWord, ver -> Codec::readDoubleWord);
            // 0x02 WORD 油量，单位为 0.1L，对应车上油量表读数
            register(0x02, B0200::getX02, B0200::setX02, ver -> Codec::writeWord, ver -> Codec::readWord);
            // 0x03 WORD 行驶记录功能获取的速度，0.1km/h
            register(0x03, B0200::getX03, B0200::setX03, ver -> Codec::writeWord, ver -> Codec::readWord);
            // 0x04 WORD 需要人工确认报警事件的 ID，从 1 开始计数
            register(0x04, B0200::getX04, B0200::setX04, ver -> Codec::writeWord, ver -> Codec::readWord);

            // 0x05 BYTE[30] 胎压，单位为 Pa，标定轮子的顺序为从车头开始从左到右顺序排列，定长 30 字节，多余的字节为 0xFF，表示无效数据 // 2019 new
            register(0x05, B0200::getX05, B0200::setX05,
                    ver -> ver > 0 ? (b, v) -> Codec.writeBytes(b, v, -30, PadChar.NUL) : null,
                    ver -> ver > 0 ? b -> Codec.readBytes(b, 30, PadChar.NUL) : null
            );
            // 0x06 SHORT 车厢温度，单位为摄氏度，取值范围为 -32767 ~ 32767，最高位为 1 表示负数 // 2019 new
            register(0x06, B0200::getX06, B0200::setX06,
                    ver -> ver > 0 ? Codec::writeShort : null,
                    ver -> ver > 0 ? Codec::readShort : null
            );

            // 0x11 OBJECT 超速报警附加信息见表 28
            register(0x11, B0200::getX11, B0200::setX11, B0200_11::new);
            // 0x12 OBJECT 进出区域/路线报警附加信息见表 29
            register(0x12, B0200::getX12, B0200::setX12, B0200_12::new);
            // 0x13 OBJECT 路段行驶时间不足/过长报警附加信息见表 30
            register(0x13, B0200::getX13, B0200::setX13, B0200_13::new);

            // 0x25 DWORD 扩展车辆信号状态位，参数项格式和定义见表 31
            register(0x25, B0200::getX25, B0200::setX25, ver -> Codec::writeDoubleWord, ver -> Codec::readDoubleWord);

            // 0x2A WORD I0 状态位，参数项格式和定义见表 32
            register(0x2A, B0200::getX2A, B0200::setX2A, ver -> Codec::writeWord, ver -> Codec::readWord);
            // 0x2B DWORD 模拟量，bit[0~15]，AD0;bit[l6~31]，AD1
            register(0x2B, B0200::getX2B, B0200::setX2B, ver -> Codec::writeDoubleWord, ver -> Codec::readDoubleWord);

            // 0x30 BYTE 无线通信网络信号强度
            register(0x30, B0200::getX30, B0200::setX30, ver -> Codec::writeByte, ver -> Codec::readByte);
            // 0x31 BYTE GNSS定位卫星数
            register(0x31, B0200::getX31, B0200::setX31, ver -> Codec::writeByte, ver -> Codec::readByte);

        }

        private static <V> void register(int id, Function<B0200, V> getter, BiConsumer<B0200, V> setter,
                                         Function<Integer, BiConsumer<ByteBuf, V>> encoderSupplier,
                                         Function<Integer, Function<ByteBuf, V>> decoderSupplier) {
            CODECS.put(id, new FieldCodec<>(id, getter, setter, encoderSupplier, decoderSupplier));
        }

        private static <V extends Codec> void register(int id,
                                                       Function<B0200, V> getter, BiConsumer<B0200, V> setter,
                                                       Supplier<V> valueCreator) {
            Objects.requireNonNull(valueCreator);
            final Function<Integer, BiConsumer<ByteBuf, V>> encoderSupplier = ver -> (b, v) -> v.encode(ver, b);
            final Function<Integer, Function<ByteBuf, V>> decoderSupplier = ver -> b -> {
                final V v = valueCreator.get();
                v.decode(ver, b);
                return v;
            };
            CODECS.put(id, new FieldCodec<>(id, getter, setter, encoderSupplier, decoderSupplier));
        }

    }

}
