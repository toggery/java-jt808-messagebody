package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x0108 终端升级结果通知
 *
 * @author togger
 */
@Data
public class B0108 {

    /** BYTE 升级类型，0.终端 12.道路运输证IC卡读卡器 52.北斗卫星定位模块 */
    private int type;

    /** BYTE 升级结果，0.成功 1.失败 2.取消 */
    private int result;

    /**
     * @return 是否成功
     */
    public final boolean isSuccess() {
        return result == 0;
    }

}
