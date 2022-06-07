package io.github.toggery.jt808.messagebody;

import lombok.Data;

/**
 * JT/T 消息体 0x8801 摄像头立即拍摄命令 // 2019 modify
 *
 * @author togger
 */
@Data
public class B8801 {

    /** BYTE 通道 ID，值大于零 */
    private int channel;

    /** WORD 拍摄命令，0 表示停止拍摄；0xFFFF 表示录像；其它表示拍照张数 */
    private int command;

    /** WORD 拍照间隔/录像时间，单位为秒，0 表示按最小间隔拍照或一直录像 */
    private int interval;

    /** BYTE 保存标志，1：保存；0：实时上传 */
    private int action;

    /**
     * BYTE 分辨率
     *
     * <ul>
     *     <li>0x01:320*240；</li>
     *     <li>0x02:640*480；</li>
     *     <li>0x03:800*600；</li>
     *     <li>0x04:1024*768;</li>
     *     <li>0x05:176*144;[Qcif];</li>
     *     <li>0x06:352*288;[Cif];</li>
     *     <li>0x07:704*288;[HALF D1];</li>
     *     <li>0x08:704*576;[D1];</li>
     *     <li>0xFF:最高分辨率 //2019 new</li>
     * </ul>
     */
    private int resolution;

    /** BYTE 图像/视频质量，1-10，1 代表质量损失最小，10 表示压缩比最大 */
    private int quality;

    /** BYTE 亮度，0~255 */
    private int brightness;

    /** BYTE 对比度，0~127 */
    private int contrast;

    /** BYTE 饱和度，0~127 */
    private int saturation;

    /** BYTE 色度，0~255 */
    private int chroma;

}
