package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x8105 终端控制
 *
 * @author togger
 */
@Data
public class B8105 {

    /**
     * BYTE 命令字
     *
     * <ul>
     *     <li>1.无线升级</li>
     *     <li>2.控制终端连接指定服务器</li>
     *     <li>3.终端关机</li>
     *     <li>4.终端复位</li>
     *     <li>5.终端恢复出厂设置</li>
     *     <li>6.关闭数据通信</li>
     *     <li>7.关闭所有无线通信</li>
     * </ul>
     */
    private int command;

    /** STRING 命令参数 */
    private String param;

}
