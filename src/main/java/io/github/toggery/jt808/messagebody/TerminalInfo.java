package io.github.toggery.jt808.messagebody;

/**
 * JT/T 消息体 终端信息
 *
 * @author togger
 */
public interface TerminalInfo {

    /** @return 所属组 */
    String getGroup();

    /** @return 空闲时间，单位为秒 */
    int getIdle();

}
