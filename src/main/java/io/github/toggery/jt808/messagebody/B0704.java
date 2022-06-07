package io.github.toggery.jt808.messagebody;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * JT/T 消息体 0x0704 定位数据批量上传
 *
 * @author togger
 */
@Data
public class B0704 {

    /** BYTE 类型，0：正常位置批量汇报，1：盲区补报 */
    private int type;

    /** 位置汇报数据列表 */
    private final List<B0200> locations = new ArrayList<>();

}
