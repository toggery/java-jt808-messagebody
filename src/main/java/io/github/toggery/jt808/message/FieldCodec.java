package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * JT/T 字段编码解码器
 *
 * @param <I> 字段 {@code ID} 的类型
 * @param <T> 字段所属对象的类型
 * @param <V> 字段值的类型
 * @author togger
 */
public final class FieldCodec<I, T, V> {

    /**
     * 实例化一个 {@link FieldCodec}
     * @param id 字段 {@code ID}
     * @param getter 字段值获取方法
     * @param setter 字段值设置方法
     * @param encoderSupplier 字段值编码方法
     * @param decoderSupplier 字段值解码方法
     * @throws NullPointerException 如果任何一个参数对象为 {@code null}
     */
    public FieldCodec(I id, Function<T, V> getter, BiConsumer<T, V> setter,
                      Function<Integer, BiConsumer<ByteBuf, V>> encoderSupplier,
                      Function<Integer, Function<ByteBuf, V>> decoderSupplier) {
        this.id = Objects.requireNonNull(id);
        this.getter = Objects.requireNonNull(getter);
        this.setter = Objects.requireNonNull(setter);
        this.encoderSupplier = Objects.requireNonNull(encoderSupplier);
        this.decoderSupplier = Objects.requireNonNull(decoderSupplier);
    }

    private final I id;
    private final Function<T, V> getter;
    private final BiConsumer<T, V> setter;
    private final Function<Integer, BiConsumer<ByteBuf, V>> encoderSupplier;
    private final Function<Integer, Function<ByteBuf, V>> decoderSupplier;

    /**
     * 获取目标对象上的该字段值，交由累计计数 {@code countedFieldEncoder} 写入字节缓冲区
     *
     * @param version 协议版本号
     * @param countedFieldEncoder 累计计数字段编码器
     * @param target 目标对象
     * @throws NullPointerException 如果参数 {@code countedFieldEncoder/target} 中的任何一个为 {@code null}
     */
    public void encode(int version, CountedFieldEncoder<I> countedFieldEncoder, T target) {
        final BiConsumer<ByteBuf, V> encoder = encoderSupplier.apply(version);
        if (encoder == null) {
            return;
        }

        Objects.requireNonNull(countedFieldEncoder);
        Objects.requireNonNull(target);
        countedFieldEncoder.encode(id, getter.apply(target), encoder);
    }

    /**
     * 从字节缓冲区读取该字段值，写入目标对象
     *
     * @param version 协议版本号
     * @param buf 字节缓冲区
     * @param target 目标对象
     * @throws NullPointerException 如果参数 {@code buf/target} 中的任何一个为 {@code null}
     */
    public void decode(int version, ByteBuf buf, T target) {
        final Function<ByteBuf, V> decoder = decoderSupplier.apply(version);
        if (decoder == null) {
            return;
        }

        Objects.requireNonNull(buf);
        Objects.requireNonNull(target);
        V newer = decoder.apply(buf);
        setter.accept(target, newer);
    }

    /**
     * 清除目标对象上该字段的值（置为 {@code null}）
     *
     * @param target 目标对象
     * @throws NullPointerException 如果参数 {@code target} 为 {@code null}
     */
    public void clear(T target) {
        Objects.requireNonNull(target);
        setter.accept(target, null);
    }

}
