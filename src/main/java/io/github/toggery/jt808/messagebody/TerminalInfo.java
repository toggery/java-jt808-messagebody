package io.github.toggery.jt808.messagebody;

/**
 * JT/T 消息体 终端信息
 *
 * @author togger
 */
public interface TerminalInfo {

    /**
     * 获取所属组
     *
     * @return 所属组
     */
    String getGroup();

    /**
     * 获取空闲时间
     *
     * @return 空闲时间，单位为秒
     */
    int getIdle();

}
