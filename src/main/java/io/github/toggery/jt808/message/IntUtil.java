package io.github.toggery.jt808.message;

/**
 * JT/T 整数工具类
 *
 * @author togger
 */
public final class IntUtil {

    private IntUtil() {}

    /**
     * 将整数格式化为十六进制字符串，形如 {@code 0x12}
     * @param value 要格式化为十六进制的整数
     * @return 十六进制字符串
     */
    public static String byteHexString(int value) {
        return String.format("%#04x", value);
    }

    /**
     * 将整数格式化为十六进制字符串，形如 {@code 0x12}
     * @param prefix 前缀
     * @param value 要格式化为十六进制的整数
     * @return 十六进制字符串
     */
    public static String byteHexString(String prefix, int value) {
        return String.format("%s%#04x", prefix, value);
    }

    /**
     * 将整数格式化为十六进制字符串，形如 {@code 0x1234}
     * @param value 要格式化为十六进制的整数
     * @return 十六进制字符串
     */
    public static String wordHexString(int value) {
        return String.format("%#06x", value);
    }
    /**
     * 将整数格式化为十六进制字符串，形如 {@code 0x1234}
     * @param prefix 前缀
     * @param value 要格式化为十六进制的整数
     * @return 十六进制字符串
     */
    public static String wordHexString(String prefix, int value) {
        return String.format("%s%#06x", prefix, value);
    }

    /**
     * 将整数格式化为十六进制字符串，形如 {@code 0x12345678}
     * @param value 要格式化为十六进制的整数
     * @return 十六进制字符串
     */
    public static String doubleWordHexString(long value) {
        return String.format("%#010x", value);
    }

    /**
     * 将整数格式化为十六进制字符串，形如 {@code 0x12345678}
     * @param prefix 前缀
     * @param value 要格式化为十六进制的整数
     * @return 十六进制字符串
     */
    public static String doubleWordHexString(String prefix, long value) {
        return String.format("%s%#010x", prefix, value);
    }

}
