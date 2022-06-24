package io.github.toggery.jt808.messagebody;

import java.util.StringJoiner;

/**
 * JT/T 消息体：0x8801 摄像头立即拍摄命令 // 2019 modify
 *
 * @author togger
 */
public class B8801 extends AbstractToStringJoiner {

    /** BYTE 通道 ID，值大于 0 */
    private int channel;

    /**
     * WORD 拍摄命令
     * <ul>
     *     <li>0：停止拍摄；</li>
     *     <li>0xFFFF：表示录像；</li>
     *     <li>其它表示拍照张数；</li>
     * </ul>
     */
    private int command;

    /** WORD 拍照间隔/录像时间，单位为秒，0 表示按最小间隔拍照或一直录像 */
    private int interval;

    /**
     * BYTE 保存标志
     * <ul>
     *     <li>0：实时上传；</li>
     *     <li>1：保存；</li>
     * </ul>
     */
    private int action;

    /**
     * BYTE 分辨率
     *
     * <ul>
     *     <li>0x01：320*240；</li>
     *     <li>0x02：640*480；</li>
     *     <li>0x03：800*600；</li>
     *     <li>0x04：1024*768；</li>
     *     <li>0x05：176*144 [Qcif]；</li>
     *     <li>0x06：352*288 [Cif]；</li>
     *     <li>0x07：704*288 [HALF D1]；</li>
     *     <li>0x08：704*576 [D1]；</li>
     *     <li>0xFF：最高分辨率 // 2019 new</li>
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


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("channel=" + channel)
                .add("command=" + command)
                .add("interval=" + interval)
                .add("action=" + action)
                .add("resolution=" + resolution)
                .add("quality=" + quality)
                .add("brightness=" + brightness)
                .add("contrast=" + contrast)
                .add("saturation=" + saturation)
                .add("chroma=" + chroma)
        ;
    }


    /**
     * 获取通道 ID，值大于 0
     * @return BYTE 通道 ID，值大于 0
     */
    public int getChannel() {
        return channel;
    }

    /**
     * 设置通道 ID，值大于 0
     * @param channel BYTE 通道 ID，值大于 0
     */
    public void setChannel(int channel) {
        this.channel = channel;
    }

    /**
     * 获取拍摄命令
     * <ul>
     *     <li>0：停止拍摄；</li>
     *     <li>0xFFFF：表示录像；</li>
     *     <li>其它表示拍照张数；</li>
     * </ul>
     * @return WORD 拍摄命令
     * @see #COMMAND_STOP_SHOOT
     * @see #COMMAND_PICTURE_RECORDING
     */
    public int getCommand() {
        return command;
    }

    /**
     * 设置拍摄命令
     * <ul>
     *     <li>0：停止拍摄；</li>
     *     <li>0xFFFF：表示录像；</li>
     *     <li>其它表示拍照张数；</li>
     * </ul>
     * @param command WORD 拍摄命令
     * @see #COMMAND_STOP_SHOOT
     * @see #COMMAND_PICTURE_RECORDING
     */
    public void setCommand(int command) {
        this.command = command;
    }

    /**
     * 获取拍照间隔/录像时间，单位为秒
     * @return WORD 拍照间隔/录像时间，单位为秒，0 表示按最小间隔拍照或一直录像
     */
    public int getInterval() {
        return interval;
    }

    /**
     * 设置拍照间隔/录像时间，单位为秒
     * @param interval WORD 拍照间隔/录像时间，单位为秒，0 表示按最小间隔拍照或一直录像
     */
    public void setInterval(int interval) {
        this.interval = interval;
    }

    /**
     * 获取保存标志
     * <ul>
     *     <li>0：实时上传；</li>
     *     <li>1：保存；</li>
     * </ul>
     * @return BYTE 保存标志
     * @see #ACTION_UPLOAD
     * @see #ACTION_SAVE
     */
    public int getAction() {
        return action;
    }

    /**
     * 设置保存标志
     * <ul>
     *     <li>0：实时上传；</li>
     *     <li>1：保存；</li>
     * </ul>
     * @param action BYTE 保存标志
     * @see #ACTION_UPLOAD
     * @see #ACTION_SAVE
     */
    public void setAction(int action) {
        this.action = action;
    }

    /**
     * 获取分辨率
     * <ul>
     *     <li>0x01：320*240；</li>
     *     <li>0x02：640*480；</li>
     *     <li>0x03：800*600；</li>
     *     <li>0x04：1024*768；</li>
     *     <li>0x05：176*144 [Qcif]；</li>
     *     <li>0x06：352*288 [Cif]；</li>
     *     <li>0x07：704*288 [HALF D1]；</li>
     *     <li>0x08：704*576 [D1]；</li>
     *     <li>0xFF：最高分辨率 // 2019 new</li>
     * </ul>
     * @return BYTE 分辨率
     * @see #RESOLUTION_320X240
     * @see #RESOLUTION_640X480
     * @see #RESOLUTION_800X600
     * @see #RESOLUTION_1024X768
     * @see #RESOLUTION_176X144
     * @see #RESOLUTION_352X288
     * @see #RESOLUTION_704X288
     * @see #RESOLUTION_704X576
     * @see #RESOLUTION_MAX
     */
    public int getResolution() {
        return resolution;
    }

    /**
     * 设置分辨率
     * <ul>
     *     <li>0x01：320*240；</li>
     *     <li>0x02：640*480；</li>
     *     <li>0x03：800*600；</li>
     *     <li>0x04：1024*768；</li>
     *     <li>0x05：176*144 [Qcif]；</li>
     *     <li>0x06：352*288 [Cif]；</li>
     *     <li>0x07：704*288 [HALF D1]；</li>
     *     <li>0x08：704*576 [D1]；</li>
     *     <li>0xFF：最高分辨率 // 2019 new</li>
     * </ul>
     * @param resolution BYTE 分辨率
     * @see #RESOLUTION_320X240
     * @see #RESOLUTION_640X480
     * @see #RESOLUTION_800X600
     * @see #RESOLUTION_1024X768
     * @see #RESOLUTION_176X144
     * @see #RESOLUTION_352X288
     * @see #RESOLUTION_704X288
     * @see #RESOLUTION_704X576
     * @see #RESOLUTION_MAX
     */
    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    /**
     * 获取图像/视频质量，1-10
     * @return BYTE 图像/视频质量，1-10，1 代表质量损失最小，10 表示压缩比最大
     */
    public int getQuality() {
        return quality;
    }

    /**
     * 设置图像/视频质量，1-10
     * @param quality BYTE 图像/视频质量，1-10，1 代表质量损失最小，10 表示压缩比最大
     */
    public void setQuality(int quality) {
        this.quality = quality;
    }

    /**
     * 获取亮度，0~255
     * @return BYTE 亮度，0~255
     */
    public int getBrightness() {
        return brightness;
    }

    /**
     * 设置亮度，0~255
     * @param brightness BYTE 亮度，0~255
     */
    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    /**
     * 获取对比度，0~127
     * @return BYTE 对比度，0~127
     */
    public int getContrast() {
        return contrast;
    }

    /**
     * 设置对比度，0~127
     * @param contrast BYTE 对比度，0~127
     */
    public void setContrast(int contrast) {
        this.contrast = contrast;
    }

    /**
     * 获取饱和度，0~127
     * @return BYTE 饱和度，0~127
     */
    public int getSaturation() {
        return saturation;
    }

    /**
     * 设置饱和度，0~127
     * @param saturation BYTE 饱和度，0~127
     */
    public void setSaturation(int saturation) {
        this.saturation = saturation;
    }

    /**
     * 获取色度，0~255
     * @return BYTE 色度，0~255
     */
    public int getChroma() {
        return chroma;
    }

    /**
     * 设置色度，0~255
     * @param chroma BYTE 色度，0~255
     */
    public void setChroma(int chroma) {
        this.chroma = chroma;
    }


    /** 拍摄命令：停止拍摄 */
    public static final int COMMAND_STOP_SHOOT = 0;
    /** 拍摄命令：录像 */
    public static final int COMMAND_PICTURE_RECORDING = 0xffff;

    /** 保存标志：实时上传 */
    public static final int ACTION_UPLOAD = 0;
    /** 保存标志：保存 */
    public static final int ACTION_SAVE = 1;

    /** 分辨率：320*240 */
    public static final int RESOLUTION_320X240 = 0x01;
    /** 分辨率：640*480 */
    public static final int RESOLUTION_640X480 = 0x02;
    /** 分辨率：800*600 */
    public static final int RESOLUTION_800X600 = 0x03;
    /** 分辨率：1024*768 */
    public static final int RESOLUTION_1024X768 = 0x04;
    /** 分辨率：176*144 [Qcif] */
    public static final int RESOLUTION_176X144 = 0x05;
    /** 分辨率：352*288 [Cif] */
    public static final int RESOLUTION_352X288 = 0x06;
    /** 分辨率：704*288 [HALF D1] */
    public static final int RESOLUTION_704X288 = 0x07;
    /** 分辨率：704*576 [D1] */
    public static final int RESOLUTION_704X576 = 0x08;
    /** 分辨率：最高分辨率 // 2019 new */
    public static final int RESOLUTION_MAX = 0xff;

}
