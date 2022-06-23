package io.github.toggery.jt808.messagebody;

import java.util.StringJoiner;

/**
 * JT/T 消息体 0x8100 终端注册应答
 *
 * @author togger
 */
public class B8100 extends AbstractToStringJoiner {

    /** WORD 应答流水号 */
    private int replySn;

    /**
     * BYTE 结果
     * <ul>
     *     <li>0：成功；</li>
     *     <li>1：车辆已被注册；</li>
     *     <li>2：数据库中无该车辆；</li>
     *     <li>3：终端已被注册；</li>
     *     <li>4：数据库中无该终端；</li>
     * </ul>
     */
    private int result;

    /** STRING 鉴权码，成功后才有该字段 */
    private String token;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add(HexUtil.wordString("replySn=", replySn))
                .add("result=" + result)
                .add("token=" + (token == null ? "" : token))
        ;
    }


    /**
     * 获取应答流水号
     * @return WORD 应答流水号
     */
    public int getReplySn() {
        return replySn;
    }

    /**
     * 设置应答流水号
     * @param replySn WORD 应答流水号
     */
    public void setReplySn(int replySn) {
        this.replySn = replySn;
    }

    /**
     * 获取结果
     * <ul>
     *     <li>0：成功；</li>
     *     <li>1：车辆已被注册；</li>
     *     <li>2：数据库中无该车辆；</li>
     *     <li>3：终端已被注册；</li>
     *     <li>4：数据库中无该终端；</li>
     * </ul>
     * @return BYTE 结果
     * @see #RESULT_SUCCESSFUL
     * @see #RESULT_VEHICLE_REGISTERED
     * @see #RESULT_VEHICLE_NOT_FOUND
     * @see #RESULT_TERMINAL_REGISTERED
     * @see #RESULT_TERMINAL_NOT_FOUND
     */
    public int getResult() {
        return result;
    }

    /**
     * 设置结果
     * <ul>
     *     <li>0：成功；</li>
     *     <li>1：车辆已被注册；</li>
     *     <li>2：数据库中无该车辆；</li>
     *     <li>3：终端已被注册；</li>
     *     <li>4：数据库中无该终端；</li>
     * </ul>
     * @param result BYTE 结果
     * @see #RESULT_SUCCESSFUL
     * @see #RESULT_VEHICLE_REGISTERED
     * @see #RESULT_VEHICLE_NOT_FOUND
     * @see #RESULT_TERMINAL_REGISTERED
     * @see #RESULT_TERMINAL_NOT_FOUND
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * 获取鉴权码，成功后才有该字段
     * @return STRING 鉴权码，成功后才有该字段
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置鉴权码，成功后才有该字段
     * @param token STRING 鉴权码，成功后才有该字段
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return 是否成功
     */
    public final boolean isSuccessful() {
        return result == RESULT_SUCCESSFUL;
    }

    /** 结果：成功 */
    public static final int RESULT_SUCCESSFUL = 0;
    /** 结果：车辆已被注册 */
    public static final int RESULT_VEHICLE_REGISTERED = 1;
    /** 结果：数据库中无该车辆 */
    public static final int RESULT_VEHICLE_NOT_FOUND = 2;
    /** 结果：终端已被注册 */
    public static final int RESULT_TERMINAL_REGISTERED = 3;
    /** 结果：数据库中无该终端 */
    public static final int RESULT_TERMINAL_NOT_FOUND = 4;

}
