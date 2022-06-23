package io.github.toggery.jt808.messagebody;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * JT/T 消息体 0x8103 设置终端参数 // 2019 modify
 *
 * <br><br>
 * <ul>
 *     <li>0x0111-0x01FF BYTE[8] 用于其他 CAN 总线 ID 单独采集设置</li>
 *     <li>0xF000-0xFFFF 用户自定义</li>
 * </ul>
 * @author togger
 */
public class B8103 extends AbstractToStringJoiner {

    /** 解码时未知的终端参数 */
    private Map<String, String> unknownParams;

    /** 0x0001 DWORD 终端心跳发送间隔，单位为秒（s） */
    private Long x0001;
    /** 0x0002 DWORD TCP 消息应答超时时间，单位为秒（s） */
    private Long x0002;
    /** 0x0003 DWORD TCP 消息重传次数 */
    private Long x0003;
    /** 0x0004 DWORD UDP 消息应答超时时间，单位为秒（s） */
    private Long x0004;
    /** 0x0005 DWORD UDP 消息重传次数 */
    private Long x0005;
    /** 0x0006 DWORD SMS 消息应答超时时间，单位为秒（s） */
    private Long x0006;
    /** 0x0007 DWORD SMS 消息重传次数 */
    private Long x0007;

    /** 0x0010 STRING 主服务器 APN，无线通信拨号访问点，若网络制式为 CDMA，则该处为 PPP 拨号号码 */
    private String x0010;
    /** 0x0011 STRING 主服务器无线通信拨号用户名 */
    private String x0011;
    /** 0x0012 STRING 主服务器无线通信拨号密码 */
    private String x0012;
    /** 0x0013 STRING 主服务器地址，IP 或域名(2019 版以冒号分割主机和端口，多个服务器使用分号分隔) */
    private String x0013;
    /** 0x0014 STRING 备份服务器 APN，无线通信拨号访问点 */
    private String x0014;
    /** 0x0015 STRING 备份服务器无线通信拨号用户名 */
    private String x0015;
    /** 0x0016 STRING 备份服务器无线通信拨号密码 */
    private String x0016;
    /** 0x0017 STRING 备份服务器地址、IP 或域名(2019 版以冒号分割主机和端口，多个服务器使用分号分隔) */
    private String x0017;

    /** 0x0018 DWORD 服务器 TCP 端口 // 2019 del */
    private Long x0018;
    /** 0x0019 DWORD 服务器 UDP 端口 // 2019 del */
    private Long x0019;

    /** 0x001A STRING 道路运输证 IC 卡认证主服务器 IP 地址或域名 */
    private String x001A;
    /** 0x001B DWORD 道路运输证 IC 卡认证主服务器 TCP 端口 */
    private Long x001B;
    /** 0x001C DWORD 道路运输证 IC 卡认证主服务器 UDP 端口 */
    private Long x001C;
    /** 0x001D STRING 道路运输证 IC 卡认证备份服务器 IP 地址或域名，端口同主服务器 */
    private String x001D;

    /** 0x0020 DWORD 位置汇报策略，0：定时汇报；1：定距汇报；2：定时和定距汇报 */
    private Long x0020;
    /** 0x0021 DWORD 位置汇报方案，0：根据 ACC 状态；1：根据登录状态和 ACC 状态，先判断登录状态，若登录再根据 ACC 状态 */
    private Long x0021;
    /** 0x0022 DWORD 驾驶员未登录汇报时间间隔，单位为秒（s），值大于 0 */
    private Long x0022;
    /** 0x0023 STRING 从服务器 APN，该值为空时，终端应使用主服务器相同配置 // 2019 new */
    private String x0023;
    /** 0x0024 STRING 从服务器无线通信拨号用户名，该值为空时，终端应使用主服务器相同配置 // 2019 new */
    private String x0024;
    /** 0x0025 STRING 从服务器无线通信拨号密码，该值为空时，终端应使用主服务器相同配置 // 2019 new */
    private String x0025;
    /** 0x0026 STRING 从服务器备份地址、IP 或域名，主机和端口用冒号分割，多个服务器使用分号分割 // 2019 new */
    private String x0026;

    /** 0x0027 DWORD 休眠时汇报时间间隔，单位为秒（s），值大于 0 */
    private Long x0027;
    /** 0x0028 DWORD 紧急报警时汇报时间间隔，单位为秒（s），值大于 0 */
    private Long x0028;
    /** 0x0029 DWORD 缺省时间汇报间隔，单位为秒（s），值大于 0 */
    private Long x0029;

    /** 0x002C DWORD 缺省距离汇报间隔，单位为米（m），值大于 0 */
    private Long x002C;
    /** 0x002D DWORD 驾驶员未登录汇报距离间隔，单位为米（m），值大于 0 */
    private Long x002D;
    /** 0x002E DWORD 休眠时汇报距离间隔，单位为米（m），值大于 0 */
    private Long x002E;
    /** 0x002F DWORD 紧急报警时汇报距离间隔，单位为米（m），值大于 0 */
    private Long x002F;
    /** 0x0030 DWORD 拐点补传角度，值小于 180 */
    private Long x0030;
    /** 0x0031 WORD 电子围栏半径（非法位移阈值），单位为米（m） */
    private Integer x0031;

    /** 0x0032 BYTE[4] 违规行驶时段范围，精确到分。BYTE1：违规行驶开始时间的小时部分；BYTE2：违规行驶开始时间的分钟部分；BYTE3：违规行驶结束时间的小时部分；BYTE4：违规行驶结束时间的分钟部分。示例：0xl6320AIE，表示当天晚上10点50分到第二天早上10点30 分属于违规行驶时段 // 2019 new */
    private byte[] x0032;

    /** 0x0040 STRING 监控平台电话号码 */
    private String x0040;
    /** 0x0041 STRING 复位电话号码，可采用此电话号码拨打终端电话让终端复位 */
    private String x0041;
    /** 0x0042 STRING 恢复出厂设置电话号码，可采用此电话号码拨打终端电话让终端恢复出厂设置 */
    private String x0042;
    /** 0x0043 STRING 监控平台 SMS 电话号码 */
    private String x0043;
    /** 0x0044 STRING 接收终端 SMS 文本报警号码 */
    private String x0044;
    /** 0x0045 DWORD 终端电话接听策略，0：自动接听；1：ACC ON 时自动接听，OFF 时手动接听 */
    private Long x0045;
    /** 0x0046 DWORD 每次最长通话时间，单位为秒（s），0 为不允许通话，0xFFFFFFF 为不限制 */
    private Long x0046;
    /** 0x0047 DWORD 当月最长通话时间，单位为秒（s），0 为不允许通话，0xFFFFFFF 为不限制 */
    private Long x0047;
    /** 0x0048 STRING 监听电话号码 */
    private String x0048;
    /** 0x0049 STRING 监管平台特权短信号码 */
    private String x0049;

    /** 0x0050 DWORD 报警屏蔽字，与位置信息汇报消息中的报警标志相对应，相应位为 1 则相应报警被屏蔽 */
    private Long x0050;
    /** 0x0051 DWORD 报警发送文本 SMS 开关，与位置信息汇报消息中的报警标志相对应，相应位为 1 则相应报警时发送文本 SMS */
    private Long x0051;
    /** 0x0052 DWORD 报警拍摄开关，与位置信息汇报消息中的报警标志相对应，相应位为 1 则相应报警时摄像头拍摄 */
    private Long x0052;
    /** 0x0053 DWORD 报警拍摄存储标志，与位置信息汇报消息中的报警标志相对应，相应位为 1 则对相应报警时拍的照片进行存储，否则实时上传 */
    private Long x0053;
    /** 0x0054 DWORD 关键标志，与位置信息汇报消息中的报警标志相对应，相应位为 1 则对相应报警为关键报警 */
    private Long x0054;
    /** 0x0055 DWORD 最高速度，单位为千米每小时（km/h） */
    private Long x0055;
    /** 0x0056 DWORD 超速持续时间，单位为秒（s） */
    private Long x0056;
    /** 0x0057 DWORD 连续驾驶时间门限，单位为秒（s） */
    private Long x0057;
    /** 0x0058 DWORD 当天累计驾驶时间门限，单位为秒（s） */
    private Long x0058;
    /** 0x0059 DWORD 最小休息时间，单位为秒（s） */
    private Long x0059;
    /** 0x005A DWORD 最长停车时间，单位为秒（s） */
    private Long x005A;

    /** 0x005B WORD 超速预警差值，单位为 1/10 千米每小时（1/10km/h） */
    private Integer x005B;
    /** 0x005C WORD 疲劳驾驶预警差值，单位为秒（s），值大于 0 */
    private Integer x005C;
    /** 0x005D WORD 碰撞报警参数，b7-b0 为碰撞时间，单位为毫秒（ms），b15-b8为碰撞加速度，单位为 0.1g，设置范围为 0~79，默认为 10 */
    private Integer x005D;
    /** 0x005E WORD 侧翻报警参数，侧翻角度，单位 1 度，默认为 30 度 */
    private Integer x005E;

    /** 0x0064 DWORD 定时拍照控制，参数项格式和定义见表 14 */
    private Long x0064;
    /** 0x0065 DWORD 定距拍照控制，参数项格式和定义见表 15 */
    private Long x0065;

    /** 0×0070 DWORD 图像/视频质量，设置范围为 1～10，1 表示最优质量 */
    private Long x0070;
    /** 0x0071 DWORD 亮度，设置范围为 0~255 */
    private Long x0071;
    /** 0x0072 DWORD 对比度，设置范围为 0～127 */
    private Long x0072;
    /** 0x0073 DWORD 饱和度，设置范围为 0~127 */
    private Long x0073;
    /** 0x0074 DWORD 色度，设置范围为 0~255 */
    private Long x0074;

    /** 0x0080 DWORD 车辆里程表读数，单位：1/10km */
    private Long x0080;
    /** 0x0081 WORD 车辆所在的省域 ID */
    private Integer x0081;
    /** 0x0082 WORD 车辆所在的市域 ID */
    private Integer x0082;
    /** 0x0083 STRING 公安交通管理部门颁发的机动车号牌 */
    private String x0083;
    /** 0x0084 BYTE 车牌颜色，0.未上车牌 1.蓝色 2.黄色 3.黑色 4.白色 5.绿色 9.其他 */
    private Integer x0084;

    /** 0x0090 BYTE GNSS 定位模式，bit0，0：禁用GPS定位，1；启用GPS定位；bit1，0：禁用北斗定位，1：启用北斗定位；bit2，0：禁用 GLONASS 定位，1：启用 GLONASS 定位；bit3，0：禁用 Galileo 定位，1：启用 Galileo 定位 */
    private Integer x0090;
    /** 0x0091 BYTE GNSS 波特率，0x00：4800；0x01：9600；0x02：19200；0x03：38400；0x04：57600；05：15200 */
    private Integer x0091;
    /** 0x0092 BYTE GNSS 模块详细定位数据输出频率，0x00：500ms；0x01；1000ms（默认值）：0x02：2000ms；0x03：3000ms；0x04：4000ms */
    private Integer x0092;
    /** 0x0093 DWORD GNSS 模块详细定位数据采集频率，单位为秒（s），默认为 1 */
    private Long x0093;
    /** 0x0094 BYTE GNSS 模块详细定位数据上传方式，0x00：本地存储，不上传（默认值）；0x01：按时间间隔上传；0x02：按距离间隔上传；0x0B：按累计时间上传，达到传输时间后自动停止上传；0x0C：按累计距离上传，达到距离后自动停止上传；0x0D：按累计条数上传，达到上传条数后自动停止上传 */
    private Integer x0094;
    /** 0x0095 DWORD GNSS 模块详细定位数据上传参数，上传方式为 0x01 时，单位为秒（s）；上传方式为 0x02 时，单位为米（m）；上传方式为 0x0B 时，单位为秒（s）；上传方式为 0x0C 时，单位为米（m）；上传方式为 0x0D 时，单位为条 */
    private Long x0095;

    /** 0x0100 DWORD CAN 总线通道 1 采集时间间隔，单位为毫秒（ms），0 表示不采集 */
    private Long x0100;
    /** 0×0101 WORD CAN 总线通道 1 上传时间间隔，单位为秒（s），0 表示不上传 */
    private Integer x0101;
    /** 0x0102 DWORD CAN 总线通道 2 采集时间间隔，单位为毫秒（ms），0 表示不采集 */
    private Long x0102;
    /** 0x0103 WORD CAN 总线通道 2 上传时间间隔，单位为秒（s），0 表示不上传 */
    private Integer x0103;

    /** 0x0110 BYTE[8] CAN 总线 ID 单独采集参数，bit63-bit32 表示此 ID 采集时间间隔（ms），0 表示不采集；bit31 表示 CAN 通道号，0：CAN1，1：CAN2；bit30 表示帧类型，0：标准帧，1∶扩展帧；bit29 表示数据采集方式，0：原始数据，1：采集区间的计算值；bit28-bit0 表示 CAN 总线 ID */
    private byte[] x0110;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("unknownParams=" + (unknownParams == null ? "" : unknownParams))
                .add("x0001=" + (x0001 == null ? "" : x0001))
                .add("x0002=" + (x0002 == null ? "" : x0002))
                .add("x0003=" + (x0003 == null ? "" : x0003))
                .add("x0004=" + (x0004 == null ? "" : x0004))
                .add("x0005=" + (x0005 == null ? "" : x0005))
                .add("x0006=" + (x0006 == null ? "" : x0006))
                .add("x0007=" + (x0007 == null ? "" : x0007))
                .add("x0010=" + (x0010 == null ? "" : x0010))
                .add("x0011=" + (x0011 == null ? "" : x0011))
                .add("x0012=" + (x0012 == null ? "" : x0012))
                .add("x0013=" + (x0013 == null ? "" : x0013))
                .add("x0014=" + (x0014 == null ? "" : x0014))
                .add("x0015=" + (x0015 == null ? "" : x0015))
                .add("x0016=" + (x0016 == null ? "" : x0016))
                .add("x0017=" + (x0017 == null ? "" : x0017))
                .add("x0018=" + (x0018 == null ? "" : x0018))
                .add("x0019=" + (x0019 == null ? "" : x0019))
                .add("x001A=" + (x001A == null ? "" : x001A))
                .add("x001B=" + (x001B == null ? "" : x001B))
                .add("x001C=" + (x001C == null ? "" : x001C))
                .add("x001D=" + (x001D == null ? "" : x001D))
                .add("x0020=" + (x0020 == null ? "" : x0020))
                .add("x0021=" + (x0021 == null ? "" : x0021))
                .add("x0022=" + (x0022 == null ? "" : x0022))
                .add("x0023=" + (x0023 == null ? "" : x0023))
                .add("x0024=" + (x0024 == null ? "" : x0024))
                .add("x0025=" + (x0025 == null ? "" : x0025))
                .add("x0026=" + (x0026 == null ? "" : x0026))
                .add("x0027=" + (x0027 == null ? "" : x0027))
                .add("x0028=" + (x0028 == null ? "" : x0028))
                .add("x0029=" + (x0029 == null ? "" : x0029))
                .add("x002C=" + (x002C == null ? "" : x002C))
                .add("x002D=" + (x002D == null ? "" : x002D))
                .add("x002E=" + (x002E == null ? "" : x002E))
                .add("x002F=" + (x002F == null ? "" : x002F))
                .add("x0030=" + (x0030 == null ? "" : x0030))
                .add("x0031=" + (x0031 == null ? "" : x0031))
                .add("x0032=" + (x0032 == null ? "" : HexUtil.dump(x0032)))
                .add("x0040=" + (x0040 == null ? "" : x0040))
                .add("x0041=" + (x0041 == null ? "" : x0041))
                .add("x0042=" + (x0042 == null ? "" : x0042))
                .add("x0043=" + (x0043 == null ? "" : x0043))
                .add("x0044=" + (x0044 == null ? "" : x0044))
                .add("x0045=" + (x0045 == null ? "" : x0045))
                .add("x0046=" + (x0046 == null ? "" : x0046))
                .add("x0047=" + (x0047 == null ? "" : x0047))
                .add("x0048=" + (x0048 == null ? "" : x0048))
                .add("x0049=" + (x0049 == null ? "" : x0049))
                .add("x0050=" + (x0050 == null ? "" : HexUtil.doubleWordString(x0050)))
                .add("x0051=" + (x0051 == null ? "" : HexUtil.doubleWordString(x0051)))
                .add("x0052=" + (x0052 == null ? "" : HexUtil.doubleWordString(x0052)))
                .add("x0053=" + (x0053 == null ? "" : HexUtil.doubleWordString(x0053)))
                .add("x0054=" + (x0054 == null ? "" : HexUtil.doubleWordString(x0054)))
                .add("x0055=" + (x0055 == null ? "" : x0055))
                .add("x0056=" + (x0056 == null ? "" : x0056))
                .add("x0057=" + (x0057 == null ? "" : x0057))
                .add("x0058=" + (x0058 == null ? "" : x0058))
                .add("x0059=" + (x0059 == null ? "" : x0059))
                .add("x005A=" + (x005A == null ? "" : x005A))
                .add("x005B=" + (x005B == null ? "" : x005B))
                .add("x005C=" + (x005C == null ? "" : x005C))
                .add("x005D=" + (x005D == null ? "" : HexUtil.wordString(x005D)))
                .add("x005E=" + (x005E == null ? "" : x005E))
                .add("x0064=" + (x0064 == null ? "" : x0064))
                .add("x0065=" + (x0065 == null ? "" : x0065))
                .add("x0070=" + (x0070 == null ? "" : x0070))
                .add("x0071=" + (x0071 == null ? "" : x0071))
                .add("x0072=" + (x0072 == null ? "" : x0072))
                .add("x0073=" + (x0073 == null ? "" : x0073))
                .add("x0074=" + (x0074 == null ? "" : x0074))
                .add("x0080=" + (x0080 == null ? "" : x0080))
                .add("x0081=" + (x0081 == null ? "" : x0081))
                .add("x0082=" + (x0082 == null ? "" : x0082))
                .add("x0083=" + (x0083 == null ? "" : x0083))
                .add("x0084=" + (x0084 == null ? "" : x0084))
                .add("x0090=" + (x0090 == null ? "" : HexUtil.byteString(x0090)))
                .add("x0091=" + (x0091 == null ? "" : HexUtil.byteString(x0091)))
                .add("x0092=" + (x0092 == null ? "" : HexUtil.byteString(x0092)))
                .add("x0093=" + (x0093 == null ? "" : x0093))
                .add("x0094=" + (x0094 == null ? "" : HexUtil.byteString(x0094)))
                .add("x0095=" + (x0095 == null ? "" : x0095))
                .add("x0100=" + (x0100 == null ? "" : x0100))
                .add("x0101=" + (x0101 == null ? "" : x0101))
                .add("x0102=" + (x0102 == null ? "" : x0102))
                .add("x0103=" + (x0103 == null ? "" : x0103))
                .add("x0110=" + (x0110 == null ? "" : HexUtil.dump(x0110)))
        ;
    }


    /**
     * 获取解码时未知的终端参数
     * @return 解码时未知的终端参数
     */
    public Map<String, String> getUnknownParams() { return unknownParams; }

    /**
     * 设置解码时未知的终端参数
     * @param unknownParams 解码时未知的终端参数
     */
    public void setUnknownParams(Map<String, String> unknownParams) {
        this.unknownParams = unknownParams;
    }


    /**
     * 添加或更新未知的参数
     * @param id 参数 {@code ID}
     * @param value 十六进制字符串格式的参数值
     */
    public void putUnknownParam(long id, String value) {
        if (unknownParams == null) {
            unknownParams = new LinkedHashMap<>();
        }
        unknownParams.put(HexUtil.wordString((int) id), value);
    }

    /**
     * 获取终端心跳发送间隔，单位为秒（s）
     * @return 0x0001 DWORD 终端心跳发送间隔，单位为秒（s）
     */
    public Long getX0001() {
        return x0001;
    }

    /**
     * 设置终端心跳发送间隔，单位为秒（s）
     * @param x0001 0x0001 DWORD 终端心跳发送间隔，单位为秒（s）
     */
    public void setX0001(Long x0001) {
        this.x0001 = x0001;
    }

    /**
     * 获取 TCP 消息应答超时时间，单位为秒（s）
     * @return 0x0002 DWORD TCP 消息应答超时时间，单位为秒（s）
     */
    public Long getX0002() {
        return x0002;
    }

    /**
     * 设置 TCP 消息应答超时时间，单位为秒（s）
     * @param x0002 0x0002 DWORD TCP 消息应答超时时间，单位为秒（s）
     */
    public void setX0002(Long x0002) {
        this.x0002 = x0002;
    }

    /**
     * 获取 TCP 消息重传次数
     * @return 0x0003 DWORD TCP 消息重传次数
     */
    public Long getX0003() {
        return x0003;
    }

    /**
     * 设置 TCP 消息重传次数
     * @param x0003 0x0003 DWORD TCP 消息重传次数
     */
    public void setX0003(Long x0003) {
        this.x0003 = x0003;
    }

    /**
     * 获取 UDP 消息应答超时时间，单位为秒（s）
     * @return 0x0004 DWORD UDP 消息应答超时时间，单位为秒（s）
     */
    public Long getX0004() {
        return x0004;
    }

    /**
     * 设置 UDP 消息应答超时时间，单位为秒（s）
     * @param x0004 0x0004 DWORD UDP 消息应答超时时间，单位为秒（s）
     */
    public void setX0004(Long x0004) {
        this.x0004 = x0004;
    }

    /**
     * 获取 UDP 消息重传次数
     * @return 0x0005 DWORD UDP 消息重传次数
     */
    public Long getX0005() {
        return x0005;
    }

    /**
     * 设置 UDP 消息重传次数
     * @param x0005 0x0005 DWORD UDP 消息重传次数
     */
    public void setX0005(Long x0005) {
        this.x0005 = x0005;
    }

    /**
     * 获取 SMS 消息应答超时时间，单位为秒（s）
     * @return 0x0006 DWORD SMS 消息应答超时时间，单位为秒（s）
     */
    public Long getX0006() {
        return x0006;
    }

    /**
     * 设置 SMS 消息应答超时时间，单位为秒（s）
     * @param x0006 0x0006 DWORD SMS 消息应答超时时间，单位为秒（s）
     */
    public void setX0006(Long x0006) {
        this.x0006 = x0006;
    }

    /**
     * 获取 SMS 消息重传次数
     * @return 0x0007 DWORD SMS 消息重传次数
     */
    public Long getX0007() {
        return x0007;
    }

    /**
     * 设置 SMS 消息重传次数
     * @param x0007 0x0007 DWORD SMS 消息重传次数
     */
    public void setX0007(Long x0007) {
        this.x0007 = x0007;
    }

    /**
     * 获取主服务器 APN，无线通信拨号访问点
     * @return 0x0010 STRING 主服务器 APN，无线通信拨号访问点，若网络制式为 CDMA，则该处为 PPP 拨号号码
     */
    public String getX0010() {
        return x0010;
    }

    /**
     * 设置主服务器 APN，无线通信拨号访问点
     * @param x0010 0x0010 STRING 主服务器 APN，无线通信拨号访问点，若网络制式为 CDMA，则该处为 PPP 拨号号码
     */
    public void setX0010(String x0010) {
        this.x0010 = x0010;
    }

    /**
     * 获取主服务器无线通信拨号用户名
     * @return 0x0011 STRING 主服务器无线通信拨号用户名
     */
    public String getX0011() {
        return x0011;
    }

    /**
     * 设置主服务器无线通信拨号用户名
     * @param x0011 0x0011 STRING 主服务器无线通信拨号用户名
     */
    public void setX0011(String x0011) {
        this.x0011 = x0011;
    }

    /**
     * 获取主服务器无线通信拨号密码
     * @return 0x0012 STRING 主服务器无线通信拨号密码
     */
    public String getX0012() {
        return x0012;
    }

    /**
     * 设置主服务器无线通信拨号密码
     * @param x0012 0x0012 STRING 主服务器无线通信拨号密码
     */
    public void setX0012(String x0012) {
        this.x0012 = x0012;
    }

    /**
     * 获取主服务器地址，IP 或域名(2019 版以冒号分割主机和端口，多个服务器使用分号分隔)
     * @return 0x0013 STRING 主服务器地址，IP 或域名(2019 版以冒号分割主机和端口，多个服务器使用分号分隔)
     */
    public String getX0013() {
        return x0013;
    }

    /**
     * 设置主服务器地址，IP 或域名(2019 版以冒号分割主机和端口，多个服务器使用分号分隔)
     * @param x0013 0x0013 STRING 主服务器地址，IP 或域名(2019 版以冒号分割主机和端口，多个服务器使用分号分隔)
     */
    public void setX0013(String x0013) {
        this.x0013 = x0013;
    }

    /**
     * 获取备份服务器 APN，无线通信拨号访问点
     * @return 0x0014 STRING 备份服务器 APN，无线通信拨号访问点
     */
    public String getX0014() {
        return x0014;
    }

    /**
     * 设置备份服务器 APN，无线通信拨号访问点
     * @param x0014 0x0014 STRING 备份服务器 APN，无线通信拨号访问点
     */
    public void setX0014(String x0014) {
        this.x0014 = x0014;
    }

    /**
     * 获取备份服务器无线通信拨号用户名
     * @return 0x0015 STRING 备份服务器无线通信拨号用户名
     */
    public String getX0015() {
        return x0015;
    }

    /**
     * 设置备份服务器无线通信拨号用户名
     * @param x0015 0x0015 STRING 备份服务器无线通信拨号用户名
     */
    public void setX0015(String x0015) {
        this.x0015 = x0015;
    }

    /**
     * 获取备份服务器无线通信拨号密码
     * @return 0x0016 STRING 备份服务器无线通信拨号密码
     */
    public String getX0016() {
        return x0016;
    }

    /**
     * 设置备份服务器无线通信拨号密码
     * @param x0016 0x0016 STRING 备份服务器无线通信拨号密码
     */
    public void setX0016(String x0016) {
        this.x0016 = x0016;
    }

    /**
     * 获取备份服务器地址、IP 或域名
     * @return 0x0017 STRING 备份服务器地址、IP 或域名(2019 版以冒号分割主机和端口，多个服务器使用分号分隔)
     */
    public String getX0017() {
        return x0017;
    }

    /**
     * 设置备份服务器地址、IP 或域名
     * @param x0017 0x0017 STRING 备份服务器地址、IP 或域名(2019 版以冒号分割主机和端口，多个服务器使用分号分隔)
     */
    public void setX0017(String x0017) {
        this.x0017 = x0017;
    }

    /**
     * 获取服务器 TCP 端口 // 2019 del
     * @return 0x0018 DWORD 服务器 TCP 端口 // 2019 del
     */
    public Long getX0018() {
        return x0018;
    }

    /**
     * 设置服务器 TCP 端口 // 2019 del
     * @param x0018 0x0018 DWORD 服务器 TCP 端口 // 2019 del
     */
    public void setX0018(Long x0018) {
        this.x0018 = x0018;
    }

    /**
     * 获取服务器 UDP 端口 // 2019 del
     * @return 0x0019 DWORD 服务器 UDP 端口 // 2019 del
     */
    public Long getX0019() {
        return x0019;
    }

    /**
     * 设置服务器 UDP 端口 // 2019 del
     * @param x0019 0x0019 DWORD 服务器 UDP 端口 // 2019 del
     */
    public void setX0019(Long x0019) {
        this.x0019 = x0019;
    }

    /**
     * 获取道路运输证 IC 卡认证主服务器 IP 地址或域名
     * @return 0x001A STRING 道路运输证 IC 卡认证主服务器 IP 地址或域名
     */
    public String getX001A() {
        return x001A;
    }

    /**
     * 设置道路运输证 IC 卡认证主服务器 IP 地址或域名
     * @param x001A 0x001A STRING 道路运输证 IC 卡认证主服务器 IP 地址或域名
     */
    public void setX001A(String x001A) {
        this.x001A = x001A;
    }

    /**
     * 获取道路运输证 IC 卡认证主服务器 TCP 端口
     * @return 0x001B DWORD 道路运输证 IC 卡认证主服务器 TCP 端口
     */
    public Long getX001B() {
        return x001B;
    }

    /**
     * 设置道路运输证 IC 卡认证主服务器 TCP 端口
     * @param x001B 0x001B DWORD 道路运输证 IC 卡认证主服务器 TCP 端口
     */
    public void setX001B(Long x001B) {
        this.x001B = x001B;
    }

    /**
     * 获取道路运输证 IC 卡认证主服务器 UDP 端口
     * @return 0x001C DWORD 道路运输证 IC 卡认证主服务器 UDP 端口
     */
    public Long getX001C() {
        return x001C;
    }

    /**
     * 设置道路运输证 IC 卡认证主服务器 UDP 端口
     * @param x001C 0x001C DWORD 道路运输证 IC 卡认证主服务器 UDP 端口
     */
    public void setX001C(Long x001C) {
        this.x001C = x001C;
    }

    /**
     * 获取道路运输证 IC 卡认证备份服务器 IP 地址或域名，端口同主服务器
     * @return 0x001D STRING 道路运输证 IC 卡认证备份服务器 IP 地址或域名，端口同主服务器
     */
    public String getX001D() {
        return x001D;
    }

    /**
     * 设置道路运输证 IC 卡认证备份服务器 IP 地址或域名，端口同主服务器
     * @param x001D 0x001D STRING 道路运输证 IC 卡认证备份服务器 IP 地址或域名，端口同主服务器
     */
    public void setX001D(String x001D) {
        this.x001D = x001D;
    }

    /**
     * 获取位置汇报策略
     * @return 0x0020 DWORD 位置汇报策略，0：定时汇报；1：定距汇报；2：定时和定距汇报
     */
    public Long getX0020() {
        return x0020;
    }

    /**
     * 设置位置汇报策略
     * @param x0020 0x0020 DWORD 位置汇报策略，0：定时汇报；1：定距汇报；2：定时和定距汇报
     */
    public void setX0020(Long x0020) {
        this.x0020 = x0020;
    }

    /**
     * 获取位置汇报方案
     * @return 0x0021 DWORD 位置汇报方案，0：根据 ACC 状态；1：根据登录状态和 ACC 状态，先判断登录状态，若登录再根据 ACC 状态
     */
    public Long getX0021() {
        return x0021;
    }

    /**
     * 设置位置汇报方案
     * @param x0021 0x0021 DWORD 位置汇报方案，0：根据 ACC 状态；1：根据登录状态和 ACC 状态，先判断登录状态，若登录再根据 ACC 状态
     */
    public void setX0021(Long x0021) {
        this.x0021 = x0021;
    }

    /**
     * 获取驾驶员未登录汇报时间间隔，单位为秒（s），值大于 0
     * @return 0x0022 DWORD 驾驶员未登录汇报时间间隔，单位为秒（s），值大于 0
     */
    public Long getX0022() {
        return x0022;
    }

    /**
     * 设置驾驶员未登录汇报时间间隔，单位为秒（s），值大于 0
     * @param x0022 0x0022 DWORD 驾驶员未登录汇报时间间隔，单位为秒（s），值大于 0
     */
    public void setX0022(Long x0022) {
        this.x0022 = x0022;
    }

    /**
     * 获取从服务器 APN // 2019 new
     * @return 0x0023 STRING 从服务器 APN，该值为空时，终端应使用主服务器相同配置 // 2019 new
     */
    public String getX0023() {
        return x0023;
    }

    /**
     * 设置从服务器 APN // 2019 new
     * @param x0023 0x0023 STRING 从服务器 APN，该值为空时，终端应使用主服务器相同配置 // 2019 new
     */
    public void setX0023(String x0023) {
        this.x0023 = x0023;
    }

    /**
     * 获取从服务器无线通信拨号用户名 // 2019 new
     * @return 0x0024 STRING 从服务器无线通信拨号用户名，该值为空时，终端应使用主服务器相同配置 // 2019 new
     */
    public String getX0024() {
        return x0024;
    }

    /**
     * 设置从服务器无线通信拨号用户名 // 2019 new
     * @param x0024 0x0024 STRING 从服务器无线通信拨号用户名，该值为空时，终端应使用主服务器相同配置 // 2019 new
     */
    public void setX0024(String x0024) {
        this.x0024 = x0024;
    }

    /**
     * 获取从服务器无线通信拨号密码 // 2019 new
     * @return 0x0025 STRING 从服务器无线通信拨号密码，该值为空时，终端应使用主服务器相同配置 // 2019 new
     */
    public String getX0025() {
        return x0025;
    }

    /**
     * 设置从服务器无线通信拨号密码 // 2019 new
     * @param x0025 0x0025 STRING 从服务器无线通信拨号密码，该值为空时，终端应使用主服务器相同配置 // 2019 new
     */
    public void setX0025(String x0025) {
        this.x0025 = x0025;
    }

    /**
     * 获取从服务器备份地址、IP 或域名 // 2019 new
     * @return 0x0026 STRING 从服务器备份地址、IP 或域名，主机和端口用冒号分割，多个服务器使用分号分割 // 2019 new
     */
    public String getX0026() {
        return x0026;
    }

    /**
     * 设置从服务器备份地址、IP 或域名 // 2019 new
     * @param x0026 0x0026 STRING 从服务器备份地址、IP 或域名，主机和端口用冒号分割，多个服务器使用分号分割 // 2019 new
     */
    public void setX0026(String x0026) {
        this.x0026 = x0026;
    }

    /**
     * 获取休眠时汇报时间间隔，单位为秒（s），值大于 0
     * @return 0x0027 DWORD 休眠时汇报时间间隔，单位为秒（s），值大于 0
     */
    public Long getX0027() {
        return x0027;
    }

    /**
     * 设置休眠时汇报时间间隔，单位为秒（s），值大于 0
     * @param x0027 0x0027 DWORD 休眠时汇报时间间隔，单位为秒（s），值大于 0
     */
    public void setX0027(Long x0027) {
        this.x0027 = x0027;
    }

    /**
     * 获取紧急报警时汇报时间间隔，单位为秒（s），值大于 0
     * @return 0x0028 DWORD 紧急报警时汇报时间间隔，单位为秒（s），值大于 0
     */
    public Long getX0028() {
        return x0028;
    }

    /**
     * 设置紧急报警时汇报时间间隔，单位为秒（s），值大于 0
     * @param x0028 0x0028 DWORD 紧急报警时汇报时间间隔，单位为秒（s），值大于 0
     */
    public void setX0028(Long x0028) {
        this.x0028 = x0028;
    }

    /**
     * 获取缺省时间汇报间隔，单位为秒（s），值大于 0
     * @return 0x0029 DWORD 缺省时间汇报间隔，单位为秒（s），值大于 0
     */
    public Long getX0029() {
        return x0029;
    }

    /**
     * 设置缺省时间汇报间隔，单位为秒（s），值大于 0
     * @param x0029 0x0029 DWORD 缺省时间汇报间隔，单位为秒（s），值大于 0
     */
    public void setX0029(Long x0029) {
        this.x0029 = x0029;
    }

    /**
     * 获取缺省距离汇报间隔，单位为米（m），值大于 0
     * @return 0x002C DWORD 缺省距离汇报间隔，单位为米（m），值大于 0
     */
    public Long getX002C() {
        return x002C;
    }

    /**
     * 设置缺省距离汇报间隔，单位为米（m），值大于 0
     * @param x002C 0x002C DWORD 缺省距离汇报间隔，单位为米（m），值大于 0
     */
    public void setX002C(Long x002C) {
        this.x002C = x002C;
    }

    /**
     * 获取驾驶员未登录汇报距离间隔，单位为米（m），值大于 0
     * @return 0x002D DWORD 驾驶员未登录汇报距离间隔，单位为米（m），值大于 0
     */
    public Long getX002D() {
        return x002D;
    }

    /**
     * 设置驾驶员未登录汇报距离间隔，单位为米（m），值大于 0
     * @param x002D 0x002D DWORD 驾驶员未登录汇报距离间隔，单位为米（m），值大于 0
     */
    public void setX002D(Long x002D) {
        this.x002D = x002D;
    }

    /**
     * 获取休眠时汇报距离间隔，单位为米（m），值大于 0
     * @return 0x002E DWORD 休眠时汇报距离间隔，单位为米（m），值大于 0
     */
    public Long getX002E() {
        return x002E;
    }

    /**
     * 设置休眠时汇报距离间隔，单位为米（m），值大于 0
     * @param x002E 0x002E DWORD 休眠时汇报距离间隔，单位为米（m），值大于 0
     */
    public void setX002E(Long x002E) {
        this.x002E = x002E;
    }

    /**
     * 获取紧急报警时汇报距离间隔，单位为米（m），值大于 0
     * @return 0x002F DWORD 紧急报警时汇报距离间隔，单位为米（m），值大于 0
     */
    public Long getX002F() {
        return x002F;
    }

    /**
     * 设置紧急报警时汇报距离间隔，单位为米（m），值大于 0
     * @param x002F 0x002F DWORD 紧急报警时汇报距离间隔，单位为米（m），值大于 0
     */
    public void setX002F(Long x002F) {
        this.x002F = x002F;
    }

    /**
     * 获取拐点补传角度，值小于 180
     * @return 0x0030 DWORD 拐点补传角度，值小于 180
     */
    public Long getX0030() {
        return x0030;
    }

    /**
     * 设置拐点补传角度，值小于 180
     * @param x0030 0x0030 DWORD 拐点补传角度，值小于 180
     */
    public void setX0030(Long x0030) {
        this.x0030 = x0030;
    }

    /**
     * 获取电子围栏半径（非法位移阈值），单位为米（m）
     * @return 0x0031 WORD 电子围栏半径（非法位移阈值），单位为米（m）
     */
    public Integer getX0031() {
        return x0031;
    }

    /**
     * 设置电子围栏半径（非法位移阈值），单位为米（m）
     * @param x0031 0x0031 WORD 电子围栏半径（非法位移阈值），单位为米（m）
     */
    public void setX0031(Integer x0031) {
        this.x0031 = x0031;
    }

    /**
     * 获取违规行驶时段范围，精确到分 // 2019 new
     * @return 0x0032 BYTE[4] 违规行驶时段范围，精确到分。BYTE1：违规行驶开始时间的小时部分；BYTE2：违规行驶开始时间的分钟部分；BYTE3：违规行驶结束时间的小时部分；BYTE4：违规行驶结束时间的分钟部分。示例：0xl6320AIE，表示当天晚上10点50分到第二天早上10点30 分属于违规行驶时段 // 2019 new
     */
    public byte[] getX0032() {
        return x0032;
    }

    /**
     * 设置违规行驶时段范围，精确到分 // 2019 new
     * @param x0032 0x0032 BYTE[4] 违规行驶时段范围，精确到分。BYTE1：违规行驶开始时间的小时部分；BYTE2：违规行驶开始时间的分钟部分；BYTE3：违规行驶结束时间的小时部分；BYTE4：违规行驶结束时间的分钟部分。示例：0xl6320AIE，表示当天晚上10点50分到第二天早上10点30 分属于违规行驶时段 // 2019 new
     */
    public void setX0032(byte[] x0032) {
        this.x0032 = x0032;
    }

    /**
     * 获取监控平台电话号码
     * @return 0x0040 STRING 监控平台电话号码
     */
    public String getX0040() {
        return x0040;
    }

    /**
     * 设置监控平台电话号码
     * @param x0040 0x0040 STRING 监控平台电话号码
     */
    public void setX0040(String x0040) {
        this.x0040 = x0040;
    }

    /**
     * 获取复位电话号码，可采用此电话号码拨打终端电话让终端复位
     * @return 0x0041 STRING 复位电话号码，可采用此电话号码拨打终端电话让终端复位
     */
    public String getX0041() {
        return x0041;
    }

    /**
     * 设置复位电话号码，可采用此电话号码拨打终端电话让终端复位
     * @param x0041 0x0041 STRING 复位电话号码，可采用此电话号码拨打终端电话让终端复位
     */
    public void setX0041(String x0041) {
        this.x0041 = x0041;
    }

    /**
     * 获取恢复出厂设置电话号码，可采用此电话号码拨打终端电话让终端恢复出厂设置
     * @return 0x0042 STRING 恢复出厂设置电话号码，可采用此电话号码拨打终端电话让终端恢复出厂设置
     */
    public String getX0042() {
        return x0042;
    }

    /**
     * 设置恢复出厂设置电话号码，可采用此电话号码拨打终端电话让终端恢复出厂设置
     * @param x0042 0x0042 STRING 恢复出厂设置电话号码，可采用此电话号码拨打终端电话让终端恢复出厂设置
     */
    public void setX0042(String x0042) {
        this.x0042 = x0042;
    }

    /**
     * 获取监控平台 SMS 电话号码
     * @return 0x0043 STRING 监控平台 SMS 电话号码
     */
    public String getX0043() {
        return x0043;
    }

    /**
     * 设置监控平台 SMS 电话号码
     * @param x0043 0x0043 STRING 监控平台 SMS 电话号码
     */
    public void setX0043(String x0043) {
        this.x0043 = x0043;
    }

    /**
     * 获取接收终端 SMS 文本报警号码
     * @return 0x0044 STRING 接收终端 SMS 文本报警号码
     */
    public String getX0044() {
        return x0044;
    }

    /**
     * 设置接收终端 SMS 文本报警号码
     * @param x0044 0x0044 STRING 接收终端 SMS 文本报警号码
     */
    public void setX0044(String x0044) {
        this.x0044 = x0044;
    }

    /**
     * 获取终端电话接听策略
     * @return 0x0045 DWORD 终端电话接听策略，0：自动接听；1：ACC ON 时自动接听，OFF 时手动接听
     */
    public Long getX0045() {
        return x0045;
    }

    /**
     * 设置终端电话接听策略
     * @param x0045 0x0045 DWORD 终端电话接听策略，0：自动接听；1：ACC ON 时自动接听，OFF 时手动接听
     */
    public void setX0045(Long x0045) {
        this.x0045 = x0045;
    }

    /**
     * 获取每次最长通话时间，单位为秒（s）
     * @return 0x0046 DWORD 每次最长通话时间，单位为秒（s），0 为不允许通话，0xFFFFFFF 为不限制
     */
    public Long getX0046() {
        return x0046;
    }

    /**
     * 设置每次最长通话时间，单位为秒（s）
     * @param x0046 0x0046 DWORD 每次最长通话时间，单位为秒（s），0 为不允许通话，0xFFFFFFF 为不限制
     */
    public void setX0046(Long x0046) {
        this.x0046 = x0046;
    }

    /**
     * 获取当月最长通话时间，单位为秒（s）
     * @return 0x0047 DWORD 当月最长通话时间，单位为秒（s），0 为不允许通话，0xFFFFFFF 为不限制
     */
    public Long getX0047() {
        return x0047;
    }

    /**
     * 设置当月最长通话时间，单位为秒（s）
     * @param x0047 0x0047 DWORD 当月最长通话时间，单位为秒（s），0 为不允许通话，0xFFFFFFF 为不限制
     */
    public void setX0047(Long x0047) {
        this.x0047 = x0047;
    }

    /**
     * 获取监听电话号码
     * @return 0x0048 STRING 监听电话号码
     */
    public String getX0048() {
        return x0048;
    }

    /**
     * 设置监听电话号码
     * @param x0048 0x0048 STRING 监听电话号码
     */
    public void setX0048(String x0048) {
        this.x0048 = x0048;
    }

    /**
     * 获取监管平台特权短信号码
     * @return 0x0049 STRING 监管平台特权短信号码
     */
    public String getX0049() {
        return x0049;
    }

    /**
     * 设置监管平台特权短信号码
     * @param x0049 0x0049 STRING 监管平台特权短信号码
     */
    public void setX0049(String x0049) {
        this.x0049 = x0049;
    }

    /**
     * 获取报警屏蔽字
     * @return 0x0050 DWORD 报警屏蔽字，与位置信息汇报消息中的报警标志相对应，相应位为 1 则相应报警被屏蔽
     */
    public Long getX0050() {
        return x0050;
    }

    /**
     * 设置报警屏蔽字
     * @param x0050 0x0050 DWORD 报警屏蔽字，与位置信息汇报消息中的报警标志相对应，相应位为 1 则相应报警被屏蔽
     */
    public void setX0050(Long x0050) {
        this.x0050 = x0050;
    }

    /**
     * 获取报警发送文本 SMS 开关
     * @return 0x0051 DWORD 报警发送文本 SMS 开关，与位置信息汇报消息中的报警标志相对应，相应位为 1 则相应报警时发送文本 SMS
     */
    public Long getX0051() {
        return x0051;
    }

    /**
     * 设置报警发送文本 SMS 开关
     * @param x0051 0x0051 DWORD 报警发送文本 SMS 开关，与位置信息汇报消息中的报警标志相对应，相应位为 1 则相应报警时发送文本 SMS
     */
    public void setX0051(Long x0051) {
        this.x0051 = x0051;
    }

    /**
     * 获取报警拍摄开关
     * @return 0x0052 DWORD 报警拍摄开关，与位置信息汇报消息中的报警标志相对应，相应位为 1 则相应报警时摄像头拍摄
     */
    public Long getX0052() {
        return x0052;
    }

    /**
     * 设置报警拍摄开关
     * @param x0052 0x0052 DWORD 报警拍摄开关，与位置信息汇报消息中的报警标志相对应，相应位为 1 则相应报警时摄像头拍摄
     */
    public void setX0052(Long x0052) {
        this.x0052 = x0052;
    }

    /**
     * 获取报警拍摄存储标志
     * @return 0x0053 DWORD 报警拍摄存储标志，与位置信息汇报消息中的报警标志相对应，相应位为 1 则对相应报警时拍的照片进行存储，否则实时上传
     */
    public Long getX0053() {
        return x0053;
    }

    /**
     * 设置报警拍摄存储标志
     * @param x0053 0x0053 DWORD 报警拍摄存储标志，与位置信息汇报消息中的报警标志相对应，相应位为 1 则对相应报警时拍的照片进行存储，否则实时上传
     */
    public void setX0053(Long x0053) {
        this.x0053 = x0053;
    }

    /**
     * 获取关键标志
     * @return 0x0054 DWORD 关键标志，与位置信息汇报消息中的报警标志相对应，相应位为 1 则对相应报警为关键报警
     */
    public Long getX0054() {
        return x0054;
    }

    /**
     * 设置关键标志
     * @param x0054 0x0054 DWORD 关键标志，与位置信息汇报消息中的报警标志相对应，相应位为 1 则对相应报警为关键报警
     */
    public void setX0054(Long x0054) {
        this.x0054 = x0054;
    }

    /**
     * 获取最高速度，单位为千米每小时（km/h）
     * @return 0x0055 DWORD 最高速度，单位为千米每小时（km/h）
     */
    public Long getX0055() {
        return x0055;
    }

    /**
     * 设置单位为千米每小时（km/h）
     * @param x0055 0x0055 DWORD 最高速度，单位为千米每小时（km/h）
     */
    public void setX0055(Long x0055) {
        this.x0055 = x0055;
    }

    /**
     * 获取超速持续时间，单位为秒（s）
     * @return 0x0056 DWORD 超速持续时间，单位为秒（s）
     */
    public Long getX0056() {
        return x0056;
    }

    /**
     * 设置超速持续时间，单位为秒（s）
     * @param x0056 0x0056 DWORD 超速持续时间，单位为秒（s）
     */
    public void setX0056(Long x0056) {
        this.x0056 = x0056;
    }

    /**
     * 获取连续驾驶时间门限，单位为秒（s）
     * @return 0x0057 DWORD 连续驾驶时间门限，单位为秒（s）
     */
    public Long getX0057() {
        return x0057;
    }

    /**
     * 设置连续驾驶时间门限，单位为秒（s）
     * @param x0057 0x0057 DWORD 连续驾驶时间门限，单位为秒（s）
     */
    public void setX0057(Long x0057) {
        this.x0057 = x0057;
    }

    /**
     * 获取当天累计驾驶时间门限，单位为秒（s）
     * @return 0x0058 DWORD 当天累计驾驶时间门限，单位为秒（s）
     */
    public Long getX0058() {
        return x0058;
    }

    /**
     * 设置当天累计驾驶时间门限，单位为秒（s）
     * @param x0058 0x0058 DWORD 当天累计驾驶时间门限，单位为秒（s）
     */
    public void setX0058(Long x0058) {
        this.x0058 = x0058;
    }

    /**
     * 获取最小休息时间，单位为秒（s）
     * @return 0x0059 DWORD 最小休息时间，单位为秒（s）
     */
    public Long getX0059() {
        return x0059;
    }

    /**
     * 设置最小休息时间，单位为秒（s）
     * @param x0059 0x0059 DWORD 最小休息时间，单位为秒（s）
     */
    public void setX0059(Long x0059) {
        this.x0059 = x0059;
    }

    /**
     * 获取最长停车时间，单位为秒（s）
     * @return 0x005A DWORD 最长停车时间，单位为秒（s）
     */
    public Long getX005A() {
        return x005A;
    }

    /**
     * 设置最长停车时间，单位为秒（s）
     * @param x005A 0x005A DWORD 最长停车时间，单位为秒（s）
     */
    public void setX005A(Long x005A) {
        this.x005A = x005A;
    }

    /**
     * 获取超速预警差值，单位为 1/10 千米每小时（1/10km/h）
     * @return 0x005B WORD 超速预警差值，单位为 1/10 千米每小时（1/10km/h）
     */
    public Integer getX005B() {
        return x005B;
    }

    /**
     * 设置 0x005B WORD 超速预警差值，单位为 1/10 千米每小时（1/10km/h）
     * @param x005B 0x005B WORD 超速预警差值，单位为 1/10 千米每小时（1/10km/h）
     */
    public void setX005B(Integer x005B) {
        this.x005B = x005B;
    }

    /**
     * 获取疲劳驾驶预警差值，单位为秒（s），值大于 0
     * @return 0x005C WORD 疲劳驾驶预警差值，单位为秒（s），值大于 0
     */
    public Integer getX005C() {
        return x005C;
    }

    /**
     * 设置疲劳驾驶预警差值，单位为秒（s），值大于 0
     * @param x005C 0x005C WORD 疲劳驾驶预警差值，单位为秒（s），值大于 0
     */
    public void setX005C(Integer x005C) {
        this.x005C = x005C;
    }

    /**
     * 获取碰撞报警参数
     * @return 0x005D WORD 碰撞报警参数，b7-b0 为碰撞时间，单位为毫秒（ms），b15-b8为碰撞加速度，单位为 0.1g，设置范围为 0~79，默认为 10
     */
    public Integer getX005D() {
        return x005D;
    }

    /**
     * 设置碰撞报警参数
     * @param x005D 0x005D WORD 碰撞报警参数，b7-b0 为碰撞时间，单位为毫秒（ms），b15-b8为碰撞加速度，单位为 0.1g，设置范围为 0~79，默认为 10
     */
    public void setX005D(Integer x005D) {
        this.x005D = x005D;
    }

    /**
     * 获取侧翻报警参数
     * @return 0x005E WORD 侧翻报警参数，侧翻角度，单位 1 度，默认为 30 度
     */
    public Integer getX005E() {
        return x005E;
    }

    /**
     * 设置侧翻报警参数
     * @param x005E 0x005E WORD 侧翻报警参数，侧翻角度，单位 1 度，默认为 30 度
     */
    public void setX005E(Integer x005E) {
        this.x005E = x005E;
    }

    /**
     * 获取定时拍照控制
     * @return 0x0064 DWORD 定时拍照控制，参数项格式和定义见表 14
     */
    public Long getX0064() {
        return x0064;
    }

    /**
     * 设置定时拍照控制
     * @param x0064 0x0064 DWORD 定时拍照控制，参数项格式和定义见表 14
     */
    public void setX0064(Long x0064) {
        this.x0064 = x0064;
    }

    /**
     * 获取定距拍照控制
     * @return 0x0065 DWORD 定距拍照控制，参数项格式和定义见表 15
     */
    public Long getX0065() {
        return x0065;
    }

    /**
     * 设置定距拍照控制
     * @param x0065 0x0065 DWORD 定距拍照控制，参数项格式和定义见表 15
     */
    public void setX0065(Long x0065) {
        this.x0065 = x0065;
    }

    /**
     * 获取图像/视频质量
     * @return 0×0070 DWORD 图像/视频质量，设置范围为 1～10，1 表示最优质量
     */
    public Long getX0070() {
        return x0070;
    }

    /**
     * 设置图像/视频质量
     * @param x0070 0×0070 DWORD 图像/视频质量，设置范围为 1～10，1 表示最优质量
     */
    public void setX0070(Long x0070) {
        this.x0070 = x0070;
    }

    /**
     * 获取亮度
     * @return 0x0071 DWORD 亮度，设置范围为 0~255
     */
    public Long getX0071() {
        return x0071;
    }

    /**
     * 设置亮度
     * @param x0071 0x0071 DWORD 亮度，设置范围为 0~255
     */
    public void setX0071(Long x0071) {
        this.x0071 = x0071;
    }

    /**
     * 获取对比度
     * @return 0x0072 DWORD 对比度，设置范围为 0～127
     */
    public Long getX0072() {
        return x0072;
    }

    /**
     * 设置对比度
     * @param x0072 0x0072 DWORD 对比度，设置范围为 0～127
     */
    public void setX0072(Long x0072) {
        this.x0072 = x0072;
    }

    /**
     * 获取饱和度
     * @return 0x0073 DWORD 饱和度，设置范围为 0~127
     */
    public Long getX0073() {
        return x0073;
    }

    /**
     * 设置饱和度
     * @param x0073 0x0073 DWORD 饱和度，设置范围为 0~127
     */
    public void setX0073(Long x0073) {
        this.x0073 = x0073;
    }

    /**
     * 获取色度
     * @return 0x0074 DWORD 色度，设置范围为 0~255
     */
    public Long getX0074() {
        return x0074;
    }

    /**
     * 设置色度
     * @param x0074 0x0074 DWORD 色度，设置范围为 0~255
     */
    public void setX0074(Long x0074) {
        this.x0074 = x0074;
    }

    /**
     * 获取车辆里程表读数，单位：1/10km
     * @return 0x0080 DWORD 车辆里程表读数，单位：1/10km
     */
    public Long getX0080() {
        return x0080;
    }

    /**
     * 设置车辆里程表读数，单位：1/10km
     * @param x0080 0x0080 DWORD 车辆里程表读数，单位：1/10km
     */
    public void setX0080(Long x0080) {
        this.x0080 = x0080;
    }

    /**
     * 获取车辆所在的省域 ID
     * @return 0x0081 WORD 车辆所在的省域 ID
     */
    public Integer getX0081() {
        return x0081;
    }

    /**
     * 设置车辆所在的省域 ID
     * @param x0081 0x0081 WORD 车辆所在的省域 ID
     */
    public void setX0081(Integer x0081) {
        this.x0081 = x0081;
    }

    /**
     * 获取车辆所在的市域 ID
     * @return 0x0082 WORD 车辆所在的市域 ID
     */
    public Integer getX0082() {
        return x0082;
    }

    /**
     * 设置车辆所在的市域 ID
     * @param x0082 0x0082 WORD 车辆所在的市域 ID
     */
    public void setX0082(Integer x0082) {
        this.x0082 = x0082;
    }

    /**
     * 获取公安交通管理部门颁发的机动车号牌
     * @return 0x0083 STRING 公安交通管理部门颁发的机动车号牌
     */
    public String getX0083() {
        return x0083;
    }

    /**
     * 设置公安交通管理部门颁发的机动车号牌
     * @param x0083 0x0083 STRING 公安交通管理部门颁发的机动车号牌
     */
    public void setX0083(String x0083) {
        this.x0083 = x0083;
    }

    /**
     * 获取车牌颜色
     * @return 0x0084 BYTE 车牌颜色，0.未上车牌 1.蓝色 2.黄色 3.黑色 4.白色 5.绿色 9.其他
     */
    public Integer getX0084() {
        return x0084;
    }

    /**
     * 设置车牌颜色
     * @param x0084 0x0084 BYTE 车牌颜色，0.未上车牌 1.蓝色 2.黄色 3.黑色 4.白色 5.绿色 9.其他
     */
    public void setX0084(Integer x0084) {
        this.x0084 = x0084;
    }

    /**
     * 获取定位模式
     * @return 0x0090 BYTE GNSS 定位模式，bit0，0：禁用GPS定位，1；启用GPS定位；bit1，0：禁用北斗定位，1：启用北斗定位；bit2，0：禁用 GLONASS 定位，1：启用 GLONASS 定位；bit3，0：禁用 Galileo 定位，1：启用 Galileo 定位
     */
    public Integer getX0090() {
        return x0090;
    }

    /**
     * 设置 GNSS 定位模式
     * @param x0090 0x0090 BYTE GNSS 定位模式，bit0，0：禁用GPS定位，1；启用GPS定位；bit1，0：禁用北斗定位，1：启用北斗定位；bit2，0：禁用 GLONASS 定位，1：启用 GLONASS 定位；bit3，0：禁用 Galileo 定位，1：启用 Galileo 定位
     */
    public void setX0090(Integer x0090) {
        this.x0090 = x0090;
    }

    /**
     * 获取 GNSS 波特率
     * @return 0x0091 BYTE GNSS 波特率，0x00：4800；0x01：9600；0x02：19200；0x03：38400；0x04：57600；05：15200
     */
    public Integer getX0091() {
        return x0091;
    }

    /**
     * 设置 GNSS 波特率
     * @param x0091 0x0091 BYTE GNSS 波特率，0x00：4800；0x01：9600；0x02：19200；0x03：38400；0x04：57600；05：15200
     */
    public void setX0091(Integer x0091) {
        this.x0091 = x0091;
    }

    /**
     * 获取 GNSS 模块详细定位数据输出频率
     * @return 0x0092 BYTE GNSS 模块详细定位数据输出频率，0x00：500ms；0x01；1000ms（默认值）：0x02：2000ms；0x03：3000ms；Ox04：4000ms
     */
    public Integer getX0092() {
        return x0092;
    }

    /**
     * 设置 GNSS 模块详细定位数据输出频率
     * @param x0092 0x0092 BYTE GNSS 模块详细定位数据输出频率，0x00：500ms；0x01；1000ms（默认值）：0x02：2000ms；0x03：3000ms；Ox04：4000ms
     */
    public void setX0092(Integer x0092) {
        this.x0092 = x0092;
    }

    /**
     * 获取 GNSS 模块详细定位数据采集频率，单位为秒（s）
     * @return 0x0093 DWORD GNSS 模块详细定位数据采集频率，单位为秒（s），默认为 1
     */
    public Long getX0093() {
        return x0093;
    }

    /**
     * 设置 GNSS 模块详细定位数据采集频率，单位为秒（s）
     * @param x0093 0x0093 DWORD GNSS 模块详细定位数据采集频率，单位为秒（s），默认为 1
     */
    public void setX0093(Long x0093) {
        this.x0093 = x0093;
    }

    /**
     * 获取 GNSS 模块详细定位数据上传方式
     * @return 0x0094 BYTE GNSS 模块详细定位数据上传方式，0x00：本地存储，不上传（默认值）；0x01：按时间间隔上传；0x02：按距离间隔上传；0xOB：按累计时间上传，达到传输时间后自动停止上传；0xOC：按累计距离上传，达到距离后自动停止上传；0x0D：按累计条数上传，达到上传条数后自动停止上传
     */
    public Integer getX0094() {
        return x0094;
    }

    /**
     * 设置 GNSS 模块详细定位数据上传方式
     * @param x0094 0x0094 BYTE GNSS 模块详细定位数据上传方式，0x00：本地存储，不上传（默认值）；0x01：按时间间隔上传；0x02：按距离间隔上传；0xOB：按累计时间上传，达到传输时间后自动停止上传；0xOC：按累计距离上传，达到距离后自动停止上传；0x0D：按累计条数上传，达到上传条数后自动停止上传
     */
    public void setX0094(Integer x0094) {
        this.x0094 = x0094;
    }

    /**
     * 获取 GNSS 模块详细定位数据上传参数
     * @return 0x0095 DWORD GNSS 模块详细定位数据上传参数，上传方式为 0x01 时，单位为秒（s）；上传方式为 0x02 时，单位为米（m）；上传方式为 0xOB 时，单位为秒（s）；上传方式为 0x0C 时，单位为米（m）；上传方式为 0xOD 时，单位为条
     */
    public Long getX0095() {
        return x0095;
    }

    /**
     * 设置 GNSS 模块详细定位数据上传参数
     * @param x0095 0x0095 DWORD GNSS 模块详细定位数据上传参数，上传方式为 0x01 时，单位为秒（s）；上传方式为 0x02 时，单位为米（m）；上传方式为 0xOB 时，单位为秒（s）；上传方式为 0x0C 时，单位为米（m）；上传方式为 0xOD 时，单位为条
     */
    public void setX0095(Long x0095) {
        this.x0095 = x0095;
    }

    /**
     * 获取 CAN 总线通道 1 采集时间间隔，单位为毫秒（ms）
     * @return 0x0100 DWORD CAN 总线通道 1 采集时间间隔，单位为毫秒（ms），0 表示不采集
     */
    public Long getX0100() {
        return x0100;
    }

    /**
     * 设置 CAN 总线通道 1 采集时间间隔，单位为毫秒（ms）
     * @param x0100 0x0100 DWORD CAN 总线通道 1 采集时间间隔，单位为毫秒（ms），0 表示不采集
     */
    public void setX0100(Long x0100) {
        this.x0100 = x0100;
    }

    /**
     * 获取 CAN 总线通道 1 上传时间间隔，单位为秒（s）
     * @return 0×0101 WORD CAN 总线通道 1 上传时间间隔，单位为秒（s），0 表示不上传
     */
    public Integer getX0101() {
        return x0101;
    }

    /**
     * 设置 CAN 总线通道 1 上传时间间隔，单位为秒（s）
     * @param x0101 0×0101 WORD CAN 总线通道 1 上传时间间隔，单位为秒（s），0 表示不上传
     */
    public void setX0101(Integer x0101) {
        this.x0101 = x0101;
    }

    /**
     * 获取 CAN 总线通道 2 采集时间间隔，单位为毫秒（ms）
     * @return 0x0102 DWORD CAN 总线通道 2 采集时间间隔，单位为毫秒（ms），0 表示不采集
     */
    public Long getX0102() {
        return x0102;
    }

    /**
     * 设置 CAN 总线通道 2 采集时间间隔，单位为毫秒（ms）
     * @param x0102 0x0102 DWORD CAN 总线通道 2 采集时间间隔，单位为毫秒（ms），0 表示不采集
     */
    public void setX0102(Long x0102) {
        this.x0102 = x0102;
    }

    /**
     * 获取 CAN 总线通道 2 上传时间间隔，单位为秒（s）
     * @return 0x0103 WORD CAN 总线通道 2 上传时间间隔，单位为秒（s），0 表示不上传
     */
    public Integer getX0103() {
        return x0103;
    }

    /**
     * 设置 CAN 总线通道 2 上传时间间隔，单位为秒（s）
     * @param x0103 0x0103 WORD CAN 总线通道 2 上传时间间隔，单位为秒（s），0 表示不上传
     */
    public void setX0103(Integer x0103) {
        this.x0103 = x0103;
    }

    /**
     * 获取 CAN 总线 ID 单独采集参数
     * @return 0x0110 BYTE[8] CAN 总线 ID 单独采集参数，bit63-bit32 表示此 ID 采集时间间隔（ms），0 表示不采集；bit31 表示 CAN 通道号，0：CAN1，1：CAN2；bit30 表示帧类型，0：标准帧，1∶扩展帧；bit29 表示数据采集方式，0：原始数据，1：采集区间的计算值；bit28-bit0 表示 CAN 总线 ID
     */
    public byte[] getX0110() {
        return x0110;
    }

    /**
     * 设置 CAN 总线 ID 单独采集参数
     * @param x0110 0x0110 BYTE[8] CAN 总线 ID 单独采集参数，bit63-bit32 表示此 ID 采集时间间隔（ms），0 表示不采集；bit31 表示 CAN 通道号，0：CAN1，1：CAN2；bit30 表示帧类型，0：标准帧，1∶扩展帧；bit29 表示数据采集方式，0：原始数据，1：采集区间的计算值；bit28-bit0 表示 CAN 总线 ID
     */
    public void setX0110(byte[] x0110) {
        this.x0110 = x0110;
    }

}
