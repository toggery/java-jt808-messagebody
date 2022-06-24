package io.github.toggery.jt808.messagebody;

import java.util.ArrayList;

/**
 * JT/T 消息体：0x8601 删除圆形区域 元素类型为 DWORD
 *
 * <p>本条消息中包含的区域数，不超过 125 个，多于 125 个建议用多条消息，0 为删除所有圆形区域</p>
 *
 * @author togger
 */
public class B8601 extends ArrayList<Long> {
}
