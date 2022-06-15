package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;

import java.util.StringJoiner;

/**
 * JT/T 消息体 0x0102 终端鉴权 // 2019 modify
 *
 * @author togger
 */
public class B0102 extends AbstractToStringJoiner implements Codec {

    /** STRING 鉴权码 */
    private String token;

    /** BYTE[15] 终端 IMEI // 2019 new */
    private String imei;

    /** BYTE[20] 软件版本号 // 2019 new */
    private String version;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("token=" + (token == null ? "" : token))
                .add("imei=" + (imei == null ? "" : imei))
                .add("version=" + (version == null ? "" : version))
        ;
    }

    @Override
    public void encode(int version, ByteBuf buf) {
        if (version > 0) {
            Codec.writeString(buf, IntUnit.BYTE, token);
            // 后补 0x00 （左对齐）
            Codec.writeChars(buf, imei, -15, PadChar.NUL);
            Codec.writeChars(buf, this.version, -20, PadChar.NUL);
            return;
        }

        Codec.writeString(buf, token);
    }

    @Override
    public void decode(int version, ByteBuf buf) {
        if (version > 0) {
            token = Codec.readString(buf, IntUnit.BYTE);
            imei = Codec.readChars(buf, 15);
            this.version = Codec.readChars(buf, 20);
            return;
        }

        token = Codec.readString(buf);
        imei = null;
        this.version = null;
    }


    /**
     * 获取鉴权码
     *
     * @return STRING 鉴权码
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置鉴权码
     *
     * @param token STRING 鉴权码
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取终端 IMEI // 2019 new
     *
     * @return  BYTE[15] 终端 IMEI // 2019 new
     */
    public String getImei() {
        return imei;
    }

    /**
     * 设置终端 IMEI // 2019 new
     *
     * @param imei  BYTE[15] 终端 IMEI // 2019 new
     */
    public void setImei(String imei) {
        this.imei = imei;
    }

    /**
     * 获取软件版本号 // 2019 new
     *
     * @return BYTE[20] 软件版本号 // 2019 new
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置软件版本号 // 2019 new
     *
     * @param version BYTE[20] 软件版本号 // 2019 new
     */
    public void setVersion(String version) {
        this.version = version;
    }

}
