package io.github.toggery.jt808.message;

/**
 * JT/T 终端信息接口
 *
 * @author togger
 */
public interface TerminalInfo {

    /**
     * 获取终端手机号
     *
     * @return 终端手机号
     */
    String getSimNo();

    /**
     * 获取终端型号
     *
     * @return 终端型号
     */
    String getModel();

    /**
     * 获取终端所属组
     *
     * @return 终端所属组
     */
    String getGroup();

    /**
     * 获取终端保持连接的空闲时间，单位为秒
     *
     * @return 终端保持连接的空闲时间，单位为秒，超过此时间后将会强制关闭连接
     */
    int getIdleTime();

}
