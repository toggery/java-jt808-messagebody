package io.github.toggery.jt808.messagebody;

/**
 * JT/T 十六进制工具类
 *
 * @author togger
 */
public final class HexUtil {

    private HexUtil() {}

    /**
     * 将整数格式化为十六进制字符串，形如 {@code 0x12}
     * @param value 要格式化为十六进制的整数
     * @return 十六进制字符串
     */
    public static String byteString(int value) {
        return String.format("%#04x", value);
    }

    /**
     * 将整数格式化为十六进制字符串，形如 {@code 0x12}
     * @param prefix 前缀
     * @param value 要格式化为十六进制的整数
     * @return 十六进制字符串
     */
    public static String byteString(String prefix, int value) {
        return String.format("%s%#04x", prefix, value);
    }

    /**
     * 将整数格式化为十六进制字符串，形如 {@code 0x1234}
     * @param value 要格式化为十六进制的整数
     * @return 十六进制字符串
     */
    public static String wordString(int value) {
        return String.format("%#06x", value);
    }
    /**
     * 将整数格式化为十六进制字符串，形如 {@code 0x1234}
     * @param prefix 前缀
     * @param value 要格式化为十六进制的整数
     * @return 十六进制字符串
     */
    public static String wordString(String prefix, int value) {
        return String.format("%s%#06x", prefix, value);
    }

    /**
     * 将整数格式化为十六进制字符串，形如 {@code 0x12345678}
     * @param value 要格式化为十六进制的整数
     * @return 十六进制字符串
     */
    public static String doubleWordString(long value) {
        return String.format("%#010x", value);
    }

    /**
     * 将整数格式化为十六进制字符串，形如 {@code 0x12345678}
     * @param prefix 前缀
     * @param value 要格式化为十六进制的整数
     * @return 十六进制字符串
     */
    public static String doubleWordString(String prefix, long value) {
        return String.format("%s%#010x", prefix, value);
    }


    /**
     * 将字节数组格式化为十六进制字符串，参阅 <a href="https://en.wikipedia.org/wiki/Hex_dump">hex dump</a>
     * @param array 要读取的字节数组
     * @return 十六进制字符串
     */
    public static String dump(byte[] array) {
        return dump(array, 0, array.length);
    }

    /**
     * 将字节数组格式化为十六进制字符串，参阅 <a href="https://en.wikipedia.org/wiki/Hex_dump">hex dump</a>
     * @param array 要读取的字节数组
     * @param fromIndex 字节数组的起始索引
     * @param length 要读取的长度
     * @return 十六进制字符串
     */
    public static String dump(byte[] array, int fromIndex, int length) {
        if (array == null || array.length <= 0 || length <= 0) {
            return "";
        }

        int endIndex = fromIndex + length;
        char[] buf = new char[length << 1];

        int srcIdx = fromIndex;
        int dstIdx = 0;
        for (; srcIdx < endIndex; srcIdx ++, dstIdx += 2) {
            System.arraycopy(
                    HEX_CHAR_TABLE, (array[srcIdx] & 0xFF) << 1,
                    buf, dstIdx, 2);
        }

        return new String(buf);
    }


    private static final char[] HEX_CHAR_TABLE = new char[256 * 4];

    static {
        final char[] digits = "0123456789abcdef".toCharArray();
        for (int i = 0; i < 256; i ++) {
            HEX_CHAR_TABLE[ i << 1     ] = digits[i >>> 4 & 0x0F];
            HEX_CHAR_TABLE[(i << 1) + 1] = digits[i       & 0x0F];
        }
    }

}
