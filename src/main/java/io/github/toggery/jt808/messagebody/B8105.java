package io.github.toggery.jt808.messagebody;

import java.util.StringJoiner;

/**
 * JT/T 消息体 0x8105 终端控制
 *
 * @author togger
 */
public class B8105 extends AbstractToStringJoiner {

    /**
     * BYTE 命令字
     *
     * <ul>
     *     <li>1：无线升级；</li>
     *     <li>2：控制终端连接指定服务器；</li>
     *     <li>3：终端关机；</li>
     *     <li>4：终端复位；</li>
     *     <li>5：终端恢复出厂设置；</li>
     *     <li>6：关闭数据通信；</li>
     *     <li>7：关闭所有无线通信；</li>
     * </ul>
     */
    private int command;

    /** STRING 命令参数 */
    private String param;

    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("command=" + command)
                .add("param=" + (param == null ? "" : param))
        ;
    }


    /**
     * 获取命令字
     * <ul>
     *     <li>1：无线升级；</li>
     *     <li>2：控制终端连接指定服务器；</li>
     *     <li>3：终端关机；</li>
     *     <li>4：终端复位；</li>
     *     <li>5：终端恢复出厂设置；</li>
     *     <li>6：关闭数据通信；</li>
     *     <li>7：关闭所有无线通信；</li>
     * </ul>
     * @return BYTE 命令字
     * @see #COMMAND_WIRELESS_FIRMWARE_UPGRAGE
     * @see #COMMAND_CONNECT_TO_SERVER
     * @see #COMMAND_SHUTDOWN
     * @see #COMMAND_RESET
     * @see #COMMAND_FACTORY_RESET
     * @see #COMMAND_CLOSE_DATA_COMMNUNICATION
     * @see #COMMAND_CLOSE_WIRELESS_COMMNUNICATION
     */
    public int getCommand() {
        return command;
    }

    /**
     * 设置命令字
     * <ul>
     *     <li>1：无线升级；</li>
     *     <li>2：控制终端连接指定服务器；</li>
     *     <li>3：终端关机；</li>
     *     <li>4：终端复位；</li>
     *     <li>5：终端恢复出厂设置；</li>
     *     <li>6：关闭数据通信；</li>
     *     <li>7：关闭所有无线通信；</li>
     * </ul>
     * @param command BYTE 命令字
     * @see #COMMAND_WIRELESS_FIRMWARE_UPGRAGE
     * @see #COMMAND_CONNECT_TO_SERVER
     * @see #COMMAND_SHUTDOWN
     * @see #COMMAND_RESET
     * @see #COMMAND_FACTORY_RESET
     * @see #COMMAND_CLOSE_DATA_COMMNUNICATION
     * @see #COMMAND_CLOSE_WIRELESS_COMMNUNICATION
     */
    public void setCommand(int command) {
        this.command = command;
    }

    /**
     * 获取命令参数
     * @return STRING 命令参数
     */
    public String getParam() {
        return param;
    }

    /**
     * 设置命令参数
     * @param param STRING 命令参数
     */
    public void setParam(String param) {
        this.param = param;
    }

    /** 命令字：无线升级 */
    public static final int COMMAND_WIRELESS_FIRMWARE_UPGRAGE = 1;
    /** 命令字：控制终端连接指定服务器 */
    public static final int COMMAND_CONNECT_TO_SERVER = 2;
    /** 命令字：终端关机 */
    public static final int COMMAND_SHUTDOWN = 3;
    /** 命令字：终端复位 */
    public static final int COMMAND_RESET = 4;
    /** 命令字：终端恢复出厂设置 */
    public static final int COMMAND_FACTORY_RESET = 5;
    /** 命令字：关闭数据通信 */
    public static final int COMMAND_CLOSE_DATA_COMMNUNICATION = 6;
    /** 命令字：关闭所有无线通信 */
    public static final int COMMAND_CLOSE_WIRELESS_COMMNUNICATION = 7;

}
