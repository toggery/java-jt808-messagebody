package io.github.toggery.jt808.messagebody;

import java.util.StringJoiner;

/**
 * JT/T 消息体 终端注册 // 2019 modify
 *
 * @author togger
 */
public class B0100 extends AbstractToStringJoiner {

    /** WORD 省域 ID */
    private int province;

    /** WORD 市县域 ID */
    private int city;

    /** BYTE[5 / 11] 制造商 ID */
    private String maker;

    /** BYTE[20 / 30] 终端型号 */
    private String model;

    /** BYTE[7 / 30] 终端 ID */
    private String id;

    /**
     * BYTE 车牌颜色
     * <ul>
     *     <li>0：未上车牌；</li>
     *     <li>1：蓝色；</li>
     *     <li>2：黄色；</li>
     *     <li>3：黑色；</li>
     *     <li>4：白色；</li>
     *     <li>5：绿色；</li>
     *     <li>9：其他；</li>
     * </ul>
     */
    private int plateColor;

    /** STRING 车牌号码 */
    private String plateNo;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("province=" + province)
                .add("city=" + city)
                .add("maker=" + (maker == null ? "" : maker))
                .add("model=" + (model == null ? "" : model))
                .add("id=" + (id == null ? "" : id))
                .add("plateColor=" + plateColor)
                .add("plateNo=" + (plateNo == null ? "" : plateNo))
        ;
    }


    /**
     * 获取省域 ID
     *
     * @return WORD 省域 ID
     */
    public int getProvince() {
        return province;
    }

    /**
     * 设置省域 ID
     *
     * @param province WORD 省域 ID
     */
    public void setProvince(int province) {
        this.province = province;
    }

    /**
     * 获取市县域 ID
     *
     * @return WORD 市县域 ID
     */
    public int getCity() {
        return city;
    }

    /**
     * 设置市县域 ID
     *
     * @param city WORD 市县域 ID
     */
    public void setCity(int city) {
        this.city = city;
    }

    /**
     * 获取制造商 ID
     *
     * @return BYTE[5 / 11] 制造商 ID
     */
    public String getMaker() {
        return maker;
    }

    /**
     * 设置制造商 ID
     *
     * @param maker BYTE[5 / 11] 制造商 ID
     */
    public void setMaker(String maker) {
        this.maker = maker;
    }

    /**
     * 获取终端型号
     *
     * @return BYTE[20 / 30] 终端型号
     */
    public String getModel() {
        return model;
    }

    /**
     * 设置终端型号
     *
     * @param model {@code BYTE[20 /30 ]} 终端型号
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * 获取终端 ID
     *
     * @return BYTE[7 / 30] 终端 ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置终端 ID
     *
     * @param id BYTE[7 / 30] 终端 ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取车牌颜色
     * <ul>
     *     <li>0：未上车牌；</li>
     *     <li>1：蓝色；</li>
     *     <li>2：黄色；</li>
     *     <li>3：黑色；</li>
     *     <li>4：白色；</li>
     *     <li>5：绿色；</li>
     *     <li>9：其他；</li>
     * </ul>
     * @return BYTE 车牌颜色
     * @see #PLATE_COLOR_NONE
     * @see #PLATE_COLOR_BLUE
     * @see #PLATE_COLOR_YELLOW
     * @see #PLATE_COLOR_BLACK
     * @see #PLATE_COLOR_WHITE
     * @see #PLATE_COLOR_GREEN
     * @see #PLATE_COLOR_OTHER
     */
    public int getPlateColor() {
        return plateColor;
    }

    /**
     * 设置车牌颜色
     * <ul>
     *     <li>0：未上车牌；</li>
     *     <li>1：蓝色；</li>
     *     <li>2：黄色；</li>
     *     <li>3：黑色；</li>
     *     <li>4：白色；</li>
     *     <li>5：绿色；</li>
     *     <li>9：其他；</li>
     * </ul>
     * @param plateColor BYTE
     * @see #PLATE_COLOR_NONE
     * @see #PLATE_COLOR_BLUE
     * @see #PLATE_COLOR_YELLOW
     * @see #PLATE_COLOR_BLACK
     * @see #PLATE_COLOR_WHITE
     * @see #PLATE_COLOR_GREEN
     * @see #PLATE_COLOR_OTHER
     */
    public void setPlateColor(int plateColor) {
        this.plateColor = plateColor;
    }

    /**
     * 获取车牌号码
     *
     * @return STRING 车牌号码
     */
    public String getPlateNo() {
        return plateNo;
    }

    /**
     * 设置车牌号码
     *
     * @param plateNo STRING 车牌号码
     */
    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }


    /** 车牌颜色：未上车牌 */
    public static final int PLATE_COLOR_NONE = 0;
    /** 车牌颜色：蓝色 */
    public static final int PLATE_COLOR_BLUE = 1;
    /** 车牌颜色：黄色 */
    public static final int PLATE_COLOR_YELLOW = 2;
    /** 车牌颜色：黑色 */
    public static final int PLATE_COLOR_BLACK = 3;
    /** 车牌颜色：白色 */
    public static final int PLATE_COLOR_WHITE = 4;
    /** 车牌颜色：绿色 */
    public static final int PLATE_COLOR_GREEN = 5;
    /** 车牌颜色：其他 */
    public static final int PLATE_COLOR_OTHER = 9;

}
