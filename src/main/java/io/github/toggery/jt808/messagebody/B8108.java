package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x8108 下发终端升级包
 *
 * @author togger
 */
@Data
public class B8108 {

    /** BYTE 升级类型，0.终端 12.道路运输证IC卡读卡器 52.北斗卫星定位模块 */
    private int type;

    /** BYTE[5] 制造商 ID */
    private String maker;

    /** STRING 版本号 */
    private String version;

    /** BYTE[] 升级数据包 */
    private byte[] data;

}
