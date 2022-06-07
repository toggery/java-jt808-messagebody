package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x8103 设置终端参数 //2019 modify
 *
 * <br><br>
 * <ul>
 *     <li>0x0111-0x01FF BYTE[8] 用于其他 CAN 总线 ID 单独采集设置</li>
 *     <li>0xF000-0xFFFF 用户自定义</li>
 * </ul>
 * @author togger
 */
@Data
public class B8103 {

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

    /** 0x0010 STRING 主服务器 APN，无线通信拨号访问点。若网络制式为 CDMA，则该处为 PPP 拨号号码 */
    private String x0010;
    /** 0x0011 STRING 主服务器无线通信拨号用户名 */
    private String x0011;
    /** 0x0012 STRING 主服务器无线通信拨号密码 */
    private String x0012;
    /** 0x0013 STRING 主服务器地址,IP 或域名(2019 版以冒号分割主机和端口,多个服务器使用分号分隔) */
    private String x0013;
    /** 0x0014 STRING 备份服务器 APN，无线通信拨号访问点 */
    private String x0014;
    /** 0x0015 STRING 备份服务器无线通信拨号用户名 */
    private String x0015;
    /** 0x0016 STRING 备份服务器无线通信拨号密码 */
    private String x0016;
    /** 0x0017 STRING 备份服务器地址，IP 或域名(2019 版以冒号分割主机和端口,多个服务器使用分号分隔) */
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
    /** 0x0022 DWORD 驾驶员未登录汇报时间间隔，单位为秒（s），值大于0 */
    private Long x0022;
    /** 0x0023 STRING 从服务器 APN。该值为空时，终端应使用主服务器相同配置 // 2019 new */
    private String x0023;
    /** 0x0024 STRING 从服务器无线通信拨号用户名。该值为空时，终端应使用主服务器相同配置 // 2019 new */
    private String x0024;
    /** 0x0025 STRING 从服务器无线通信拨号密码。该值为空时，终端应使用主服务器相同配置 // 2019 new */
    private String x0025;
    /** 0x0026 STRING 从服务器备份地址、IP或域名，主机和端口用冒号分割，多个服务器使用分号分割 // 2019 new */
    private String x0026;

    /** 0x0027 DWORD 休眠时汇报时间间隔，单位为秒（s），值大于0 */
    private Long x0027;
    /** 0x0028 DWORD 紧急报警时汇报时间间隔，单位为秒（s），值大于0 */
    private Long x0028;
    /** 0x0029 DWORD 缺省时间汇报间隔，单位为秒（s），值大于0 */
    private Long x0029;

    /** 0x002C DWORD 缺省距离汇报间隔，单位为米（m），值大于0 */
    private Long x002C;
    /** 0x002D DWORD 驾驶员未登录汇报距离间隔，单位为米（m），值大于0 */
    private Long x002D;
    /** 0x002E DWORD 休眠时汇报距离间隔，单位为米（m），值大于0 */
    private Long x002E;
    /** 0x002F DWORD 紧急报警时汇报距离间隔，单位为米（m），值大于0 */
    private Long x002F;
    /** 0x0030 DWORD 拐点补传角度，值小于180 */
    private Long x0030;
    /** 0x0031 WORD 电子围栏半径（非法位移阈值），单位为米（m） */
    private Integer x0031;

    /** 0x0032 BYTE[4] 违规行驶时段范围，精确到分。BYTE1;违规行驶开始时间的小时部分;BYTE2∶违规行驶开始时间的分钟部分;BYTE3∶违规行驶结束时间的小时部分;BYTE4∶违规行驶结束时间的分钟部分。示例∶0xl6320AIE，表示当天晚上10点50分到第二天早上10点30 分属于违规行驶时段 // 2019 new */
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
    /** 0x0045 DWORD 终端电话接听策略。0∶自动接听；1∶ACC ON时自动接听，OFF时手动接听 */
    private Long x0045;
    /** 0x0046 DWORD 每次最长通话时间，单位为秒（s），0为不允许通话，0xFFFFFFF 为不限制 */
    private Long x0046;
    /** 0x0047 DWORD 当月最长通话时间，单位为秒（s），0为不允许通话，0xFFFFFFF为不限制 */
    private Long x0047;
    /** 0x0048 STRING 监听电话号码 */
    private String x0048;
    /** 0x0049 STRING 监管平台特权短信号码 */
    private String x0049;

    /** 0x0050 DWORD 报警屏蔽字，与位置信息汇报消息中的报警标志相对应，相应位为1则相应报警被屏蔽 */
    private Long x0050;
    /** 0x0051 DWORD 报警发送文本 SMS开关，与位置信息汇报消息中的报警标志相对应，相应位为1则相应报警时发送文本 SMS */
    private Long x0051;
    /** 0x0052 DWORD 报警拍摄开关，与位置信息汇报消息中的报警标志相对应，相应位为1则相应报警时摄像头拍摄 */
    private Long x0052;
    /** 0x0053 DWORD 报警拍摄存储标志，与位置信息汇报消息中的报警标志相对应，相应位为1则对相应报警时拍的照片进行存储，否则实时上传 */
    private Long x0053;
    /** 0x0054 DWORD 关键标志，与位置信息汇报消息中的报警标志相对应，相应位为1则对相应报警为关键报警 */
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

    /** 0x005B WORD 超速预警差值，单位为1/10千米每小时（1/10km/h） */
    private Integer x005B;
    /** 0x005C WORD 疲劳驾驶预警差值，单位为秒（s），值大于零 */
    private Integer x005C;
    /** 0x005D WORD 碰撞报警参数设置：b7-b0为碰撞时间，单位为毫秒（ms）；b15-b8为碰撞加速度，单位为0.1g；设置范围为0~79，默认为10。 */
    private Integer x005D;
    /** 0x005E WORD 侧翻报警参数设置：侧翻角度，单位 1 度，默认为 30 度。 */
    private Integer x005E;

    /** 0x0064 DWORD 定时拍照控制，参数项格式和定义见表 14 */
    private Long x0064;
    /** 0x0065 DWORD 定距拍照控制，参数项格式和定义见表 15 */
    private Long x0065;

    /** 0×0070 DWORD 图像/视频质量，设置范围为1～10，1表示最优质量 */
    private Long x0070;
    /** 0x0071 DWORD 亮度，设置范围为0~255 */
    private Long x0071;
    /** 0x0072 DWORD 对比度，设置范围为0～127 */
    private Long x0072;
    /** 0x0073 DWORD 饱和度，设置范围为0~127 */
    private Long x0073;
    /** 0x0074 DWORD 色度，设置范围为0~255 */
    private Long x0074;

    /** 0x0080 DWORD 车辆里程表读数，单位∶1/10km */
    private Long x0080;
    /** 0x0081 WORD 车辆所在的省域ID */
    private Integer x0081;
    /** 0x0082 WORD 车辆所在的市域ID */
    private Integer x0082;
    /** 0x0083 STRING 公安交通管理部门颁发的机动车号牌 */
    private String x0083;
    /** 0x0084 BYTE 车牌颜色，0.未上车牌 1.蓝色 2.黄色 3.黑色 4.白色 5.绿色 9.其他 */
    private Integer x0084;

    /** 0x0090 BYTE GNSS定位模式，定义如下∶ bit0，0∶禁用GPS定位，1;启用GPS定位;bit1，0禁用北斗定位，1∶启用北斗定位;bit2，0;禁用GLONASS定位，1∶启用GLONASS 定位;bit3，0∶禁用Galileo定位，1∶启用Galileo 定位 */
    private Integer x0090;
    /** 0x0091 BYTE GNSS 波特率，定义如下∶0x00:4800;0x01:9600;0x02:19200;0x03:38400;0x04:57600;05:15200 */
    private Integer x0091;
    /** 0x0092 BYTE GNSS 模块详细定位数据输出频率，定义如下∶0x00∶500ms;0x01∶1000ms（默认值）;0x02∶2000ms;0x03∶3000ms;Ox04:4000ms */
    private Integer x0092;
    /** 0x0093 DWORD GNSS模块详细定位数据采集频率，单位为秒（s），默认为1 */
    private Long x0093;
    /** 0x0094 BYTE GNSS 模块详细定位数据上传方式∶0x00，本地存储，不上传（默认值）;0x01，按时间间隔上传;0x02，按距离间隔上传;0xOB，按累计时间上传，达到传输时间后自动停止上传;0xOC，按累计距离上传，达到距离后自动停止上传;0x0D，按累计条数上传，达到上传条数后自动停止上传 */
    private Integer x0094;
    /** 0x0095 DWORD GNSS模块详细定位数据上传设置∶上传方式为0x01时，单位为秒（s）;上传方式为0x02时，单位为米（m）;上传方式为0xOB时，单位为秒（s）;上传方式为0x0C时，单位为米（m）;上传方式为0xOD时，单位为条 */
    private Long x0095;

    /** 0x0100 DWORD CAN总线通道1采集时间间隔，单位为毫秒（ms），0表示不采集 */
    private Long x0100;
    /** 0×0101 WORD CAN 总线通道1上传时间间隔，单位为秒（s），0表示不上传 */
    private Integer x0101;
    /** 0x0102 DWORD CAN总线通道2采集时间间隔，单位为毫秒（ms），0表示不采集 */
    private Long x0102;
    /** 0x0103 WORD CAN 总线通道2上传时间间隔，单位为秒（s），0表示不上传 */
    private Integer x0103;

    /** 0x0110 BYTE[8] CAN 总线ID单独采集设置∶bit63-bit32表示此ID采集时间间隔（ms），0表示不采集;bit31表示CAN通道号，0∶CAN1，，1∶CAN2;bit30表示帧类型，0∶标准帧，1∶扩展帧;bit29表示数据采集方式，0∶原始数据，1;采集区间的计算值;bit28-bit0表示 CAN 总线ID */
    private byte[] x0110;

}
