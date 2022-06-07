package io.github.toggery.jt808.messagebody;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * JT/T 消息体 0x0805 摄像头立即拍摄命令应答
 *
 * @author togger
 */
@Data
public class B0805 {

    /** WORD 应答流水号，对应平台摄像头立即拍摄命令的消息流水号 */
    private int replySn;

    /** BYTE 结果，0：成功；1：失败；2：通道不支持。元素项（多媒体 ID ）在结果=0 时才有效 */
    private int result;

    /** 多媒体 ID 列表，元素为 DWORD */
    private final List<Long> mediaIds = new ArrayList<>();

    /**
     * @return 是否成功
     */
    public final boolean isSuccess() {
        return result == 0;
    }

}
