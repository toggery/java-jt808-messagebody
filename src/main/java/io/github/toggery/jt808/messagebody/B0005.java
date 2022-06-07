package io.github.toggery.jt808.messagebody;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * JT/T 消息体 0x0005 终端补传分包请求 // 2019 new
 *
 * @author togger
 */
@Data
public class B0005 {

    /** WORD 原始消息流水号，对应要求补传的原始消息第一包的消息流水号 */
    private int originalSn;

    /** WORD 重传包序号列表 */
    private final List<Integer> subpackageSns = new ArrayList<>();

}
