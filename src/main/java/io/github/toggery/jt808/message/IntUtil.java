package io.github.toggery.jt808.message;

/**
 * JT/T 整数工具类
 *
 * @author togger
 */
public final class IntUtil {

    private IntUtil() {}

    /**
     * 将整数格式化为形如 {@code 0x12} 形式的十六进制字符串
     * @param value 要格式化为十六进制的整数
     * @return 十六进制字符串
     */
    public static final String byteHexString(int value) {
        return String.format("%#04x", value);
    }

    /**
     * 将整数格式化为形如 {@code 0x12} 形式的十六进制字符串
     * @param prefix 前缀
     * @param value 要格式化为十六进制的整数
     * @return 十六进制字符串
     */
    public static final String byteHexString(String prefix, int value) {
        return String.format("%s%#04x", prefix, value);
    }

    /**
     * 将整数格式化为形如 {@code 0x1234} 形式的十六进制字符串
     * @param value 要格式化为十六进制的整数
     * @return 十六进制字符串
     */
    public static final String wordHexString(int value) {
        return String.format("%#06x", value);
    }
    /**
     * 将整数格式化为形如 {@code 0x1234} 形式的十六进制字符串
     * @param prefix 前缀
     * @param value 要格式化为十六进制的整数
     * @return 十六进制字符串
     */
    public static final String wordHexString(String prefix, int value) {
        return String.format("%s%#06x", prefix, value);
    }

    /**
     * 将整数格式化为形如 {@code 0x12345678} 形式的十六进制字符串
     * @param value 要格式化为十六进制的整数
     * @return 十六进制字符串
     */
    public static final String doubleWordHexString(long value) {
        return String.format("%#10x", value);
    }

    /**
     * 将整数格式化为形如 {@code 0x12345678} 形式的十六进制字符串
     * @param prefix 前缀
     * @param value 要格式化为十六进制的整数
     * @return 十六进制字符串
     */
    public static final String doubleWordHexString(String prefix, long value) {
        return String.format("%s%#10x", prefix, value);
    }

}
