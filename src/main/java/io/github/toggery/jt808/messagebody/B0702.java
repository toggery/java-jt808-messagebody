package io.github.toggery.jt808.messagebody;

import java.util.StringJoiner;

/**
 * JT/T 消息体 0x0702 驾驶员身份信息采集上报 // 2019 modify
 *
 * @author togger
 */
public class B0702 extends AbstractToStringJoiner {

    /**
     * BYTE 状态
     *
     * <ul>
     *     <li>0x01：从业资格证 IC 卡插入（驾驶员上班）；</li>
     *     <li>0x02：从业资格证 IC 卡拔出（驾驶员下班）；</li>
     * </ul>
     */
    private int status;

    /** BCD[6] 插卡/拔卡时间，yyMMddHHmmss，以下字段在状态为 0x01 时才有效并做填充。*/
    private String time;

    /**
     * BYTE IC 卡读取结果
     *
     * <ul>
     *     <li>0x00：IC 卡读卡成功；</li>
     *     <li>0x01：读卡失败，原因为卡片密钥认证未通过；</li>
     *     <li>0x02：读卡失败，原因为卡片已被锁定；</li>
     *     <li>0x03：读卡失败，原因为卡片被拔出；</li>
     *     <li>0x04：读卡失败，原因为数据校验错误；</li>
     *     <li>以下字段在 IC 卡读取结果等于 0x00 时才有效。</li>
     * </ul>
     */
    private int result;

    /** STRING 驾驶员姓名 */
    private String name;

    /** STRING 从业资格证编码，最长 20 位，不足补 0x00。*/
    private String licenseNo;

    /** STRING 发证机构名称 */
    private String authority;

    /** BCD[4] 证件有效期，yyyyMMdd */
    private String validity;

    /** STRING 驾驶员身份证号码，最长 20 位，不足补 0x00 */
    private String idCardNo;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add(HexUtil.byteString("status=", status))
                .add("time=" + (time == null ? "" : time))
                .add("result=" + result)
                .add("name=" + (name == null ? "" : name))
                .add("licenseNo=" + (licenseNo == null ? "" : licenseNo))
                .add("authority=" + (authority == null ? "" : authority))
                .add("validity=" + (validity == null ? "" : validity))
                .add("idCardNo=" + (idCardNo == null ? "" : idCardNo))
        ;
    }


    /**
     * 获取状态
     * <ul>
     *     <li>0x01：从业资格证 IC 卡插入（驾驶员上班）；</li>
     *     <li>0x02：从业资格证 IC 卡拔出（驾驶员下班）；</li>
     * </ul>
     * @return BYTE 状态
     * @see #STATUS_IC_CARD_INSERTED
     * @see #STATUS_IC_CARD_DRAWN_OUT
     */
    public int getStatus() {
        return status;
    }

    /**
     * 设置状态
     * <ul>
     *     <li>0x01：从业资格证 IC 卡插入（驾驶员上班）；</li>
     *     <li>0x02：从业资格证 IC 卡拔出（驾驶员下班）；</li>
     * </ul>
     * @param status BYTE 状态
     * @see #STATUS_IC_CARD_INSERTED
     * @see #STATUS_IC_CARD_DRAWN_OUT
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * 获取插卡/拔卡时间，yyMMddHHmmss
     * @return BCD[6] 插卡/拔卡时间，yyMMddHHmmss，以下字段在状态为 0x01 时才有效并做填充
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置插卡/拔卡时间，yyMMddHHmmss
     * @param time BCD[6] 插卡/拔卡时间，yyMMddHHmmss，以下字段在状态为 0x01 时才有效并做填充
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 获取 IC 卡读取结果
     * <ul>
     *     <li>0x00：IC 卡读卡成功；</li>
     *     <li>0x01：读卡失败，原因为卡片密钥认证未通过；</li>
     *     <li>0x02：读卡失败，原因为卡片已被锁定；</li>
     *     <li>0x03：读卡失败，原因为卡片被拔出；</li>
     *     <li>0x04：读卡失败，原因为数据校验错误；</li>
     *     <li>以下字段在 IC 卡读取结果等于 0x00 时才有效。</li>
     * </ul>
     * @return BYTE IC 卡读取结果
     * @see #RESULT_SUCCESSFUL
     * @see #RESULT_FAILED_BY_KEY_UNAUTH
     * @see #RESULT_FAILED_BY_CARD_LOCKED
     * @see #RESULT_FAILED_BY_CARD_OUT
     * @see #RESULT_FAILED_BY_VERIFY_ERROR
     */
    public int getResult() {
        return result;
    }

    /**
     * 设置 IC 卡读取结果
     * <ul>
     *     <li>0x00：IC 卡读卡成功；</li>
     *     <li>0x01：读卡失败，原因为卡片密钥认证未通过；</li>
     *     <li>0x02：读卡失败，原因为卡片已被锁定；</li>
     *     <li>0x03：读卡失败，原因为卡片被拔出；</li>
     *     <li>0x04：读卡失败，原因为数据校验错误；</li>
     *     <li>以下字段在 IC 卡读取结果等于 0x00 时才有效。</li>
     * </ul>
     * @param result BYTE IC 卡读取结果
     * @see #RESULT_SUCCESSFUL
     * @see #RESULT_FAILED_BY_KEY_UNAUTH
     * @see #RESULT_FAILED_BY_CARD_LOCKED
     * @see #RESULT_FAILED_BY_CARD_OUT
     * @see #RESULT_FAILED_BY_VERIFY_ERROR
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * 获取驾驶员姓名
     * @return STRING 驾驶员姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置驾驶员姓名
     * @param name STRING 驾驶员姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取从业资格证编码
     * @return STRING 从业资格证编码，最长 20 位
     */
    public String getLicenseNo() {
        return licenseNo;
    }

    /**
     * 设置从业资格证编码
     * @param licenseNo STRING 从业资格证编码，最长 20 位
     */
    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    /**
     * 获取发证机构名称
     * @return STRING 发证机构名称
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * 设置发证机构名称
     * @param authority STRING 发证机构名称
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    /**
     * 获取证件有效期，yyyyMMdd
     * @return BCD[4] 证件有效期，yyyyMMdd
     */
    public String getValidity() {
        return validity;
    }

    /**
     * 设置证件有效期，yyyyMMdd
     * @param validity BCD[4] 证件有效期，yyyyMMdd
     */
    public void setValidity(String validity) {
        this.validity = validity;
    }

    /**
     * 获取驾驶员身份证号码
     * @return STRING 驾驶员身份证号码，最长 20 位
     */
    public String getIdCardNo() {
        return idCardNo;
    }

    /**
     * 设置驾驶员身份证号码
     * @param idCardNo STRING 驾驶员身份证号码，最长 20 位
     */
    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    /**
     * 获取是否成功
     * @return 是否成功
     */
    public final boolean isSuccessful() {
        return result == RESULT_SUCCESSFUL;
    }


    /** 状态：从业资格证 IC 卡插入（驾驶员上班） */
    public static final int STATUS_IC_CARD_INSERTED = 0x01;
    /** 状态：从业资格证 IC 卡拔出（驾驶员下班） */
    public static final int STATUS_IC_CARD_DRAWN_OUT = 0x02;

    /** IC 卡读取结果：IC 卡读卡成功 */
    public static final int RESULT_SUCCESSFUL = 0x00;
    /** IC 卡读取结果：读卡失败，原因为卡片密钥认证未通过 */
    public static final int RESULT_FAILED_BY_KEY_UNAUTH = 0x01;
    /** IC 卡读取结果：读卡失败，原因为卡片已被锁定 */
    public static final int RESULT_FAILED_BY_CARD_LOCKED = 0x02;
    /** IC 卡读取结果：读卡失败，原因为卡片被拔出 */
    public static final int RESULT_FAILED_BY_CARD_OUT = 0x03;
    /** IC 卡读取结果：读卡失败，原因为数据校验错误 */
    public static final int RESULT_FAILED_BY_VERIFY_ERROR = 0x04;

}
