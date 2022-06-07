package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x0900 数据上行透传
 *
 * @author togger
 */
@Data
public class B0900 {

    /**
     * BYTE 透传消息类型
     *
     *  <ul>
     *      <li>0x00: GNSS 模块详细定位数据</li>
     *      <li>0x0B: 道路运输证 IC 卡信息。上传消息为 64Byte，下传消息为24Byte。道路运输证 IC 卡认证透传超时时间为 30s。超时后，不重发。</li>
     *      <li>0x41: 串口 1 透传消息</li>
     *      <li>0x42: 串口 2 透传消息</li>
     *      <li>0xF0-0xFF: 用户自定义透传消息</li>
     *  </ul>
     */
    private int type;

    /** BYTE[] 透传消息内容 */
    private byte[] data;

}