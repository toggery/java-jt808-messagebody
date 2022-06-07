package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x0702 驾驶员身份信息采集上报 // 2019 modify
 *
 * @author togger
 */
@Data
public class B0702 {

    /**
     * BYTE 状态
     *
     * <ul>
     *     <li>0x01: 从业资格证 IC 卡插入（驾驶员上班）；</li>
     *     <li>0x02: 从业资格证 IC 卡拔出（驾驶员下班）。</li>
     * </ul>
     */
    private int status;

    /** BCD[6] 插卡/拔卡时间，yyMMddHHmmss 以下字段在状态为 0x01 时才有效并做填充。*/
    private String time;

    /**
     * BYTE IC 卡读取结果
     *
     * <ul>
     *     <li>0x00：IC 卡读卡成功；</li>
     *     <li>0x01：读卡失败，原因为卡片密钥认证未通过；</li>
     *     <li>0x02：读卡失败，原因为卡片已被锁定；</li>
     *     <li>0x03：读卡失败，原因为卡片被拔出；</li>
     *     <li>0x04：读卡失败，原因为数据校验错误。</li>
     *     <li>以下字段在 IC 卡读取结果等于 0x00 时才有效。</li>
     * </ul>
     */
    private int result;

    /** STRING 驾驶员姓名 */
    private String name;

    /** STRING 从业资格证编码，长度 20 位，不足补 0x00。*/
    private String licenseNo;

    /** STRING 发证机构名称 */
    private String authority;

    /** BCD[4] 证件有效期 yyyyMMdd */
    private String validity;

    /** STRING 驾驶员身份证号码，长度 20 位，不足补 0x00 */
    private String idCardNo;

    /**
     * @return 是否成功
     */
    public final boolean isSuccess() {
        return result == 0;
    }

}
