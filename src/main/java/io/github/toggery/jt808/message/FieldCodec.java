package io.github.toggery.jt808.message;

import io.netty.buffer.ByteBuf;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

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
     * @param <I> 字段 {@code ID} 的类型
     * @param <T> 字段所属对象的类型
     * @param <V> 字段值的类型
     * @return {@link FieldCodec} 实例
     * @throws NullPointerException 如果任何一个参数对象为 {@code null}
     */
    public static <I, T, V> FieldCodec<I, T, V> of(I id
            , Function<T, V> getter, BiConsumer<T, V> setter
            , Function<Integer, BiConsumer<ByteBuf, V>> encoderSupplier
            , Function<Integer, Function<ByteBuf, V>> decoderSupplier) {
        return new FieldCodec<>(id, getter, setter, encoderSupplier, decoderSupplier);
    }

    /**
     * 实例化一个 {@link FieldCodec}
     * @param id 字段 {@code ID}
     * @param getter 字段值获取方法
     * @param setter 字段值设置方法
     * @param valueCreator 字段值构造方法
     * @param <I> 字段 {@code ID} 的类型
     * @param <T> 字段所属对象的类型
     * @param <V> 字段值的类型
     * @return {@link FieldCodec} 实例
     * @throws NullPointerException 如果任何一个参数对象为 {@code null}
     */
    public static <I, T, V extends Codec> FieldCodec<I, T, V> of(I id
            , Function<T, V> getter, BiConsumer<T, V> setter, Supplier<V> valueCreator) {

        final Function<Integer, BiConsumer<ByteBuf, V>> encoderSupplier = ver -> (b, v) -> v.encode(ver, b);
        final Function<Integer, Function<ByteBuf, V>> decoderSupplier = ver -> b -> {
            final V v = valueCreator.get();
            v.decode(ver, b);
            return v;
        };
        return new FieldCodec<>(id, getter, setter, encoderSupplier, decoderSupplier);
    }

    /**
     * 实例化一个 {@code BYTE} 值类型的 {@link FieldCodec}
     * @param id 字段 {@code ID}
     * @param getter 字段值获取方法
     * @param setter 字段值设置方法
     * @param <ID> 字段 {@code ID} 的类型
     * @param <T> 字段所属对象的类型
     * @return {@link FieldCodec} 实例
     * @throws NullPointerException 如果任何一个参数对象为 {@code null}
     */
    public static <ID, T> FieldCodec<ID, T, Integer> ofByte(ID id
            , Function<T, Integer> getter, BiConsumer<T, Integer> setter) {
        return new FieldCodec<>(id, getter, setter, ver -> Codec::writeByte, ver -> Codec::readByte);
    }

    /**
     * 实例化一个 {@code WORD} 值类型的 {@link FieldCodec}
     * @param id 字段 {@code ID}
     * @param getter 字段值获取方法
     * @param setter 字段值设置方法
     * @param <ID> 字段 {@code ID} 的类型
     * @param <T> 字段所属对象的类型
     * @return {@link FieldCodec} 实例
     * @throws NullPointerException 如果任何一个参数对象为 {@code null}
     */
    public static <ID, T> FieldCodec<ID, T, Integer> ofWord(ID id
            , Function<T, Integer> getter, BiConsumer<T, Integer> setter) {
        return new FieldCodec<>(id, getter, setter, ver -> Codec::writeWord, ver -> Codec::readWord);
    }

    /**
     * 实例化一个 {@code Short} 值类型的 {@link FieldCodec}
     * @param id 字段 {@code ID}
     * @param getter 字段值获取方法
     * @param setter 字段值设置方法
     * @param <ID> 字段 {@code ID} 的类型
     * @param <T> 字段所属对象的类型
     * @return {@link FieldCodec} 实例
     * @throws NullPointerException 如果任何一个参数对象为 {@code null}
     */
    public static <ID, T> FieldCodec<ID, T, Short> ofShort(ID id
            , Function<T, Short> getter, BiConsumer<T, Short> setter) {
        return new FieldCodec<>(id, getter, setter, ver -> Codec::writeShort, ver -> Codec::readShort);
    }

    /**
     * 实例化一个 {@code DWORD} 值类型的 {@link FieldCodec}
     * @param id 字段 {@code ID}
     * @param getter 字段值获取方法
     * @param setter 字段值设置方法
     * @param <ID> 字段 {@code ID} 的类型
     * @param <T> 字段所属对象的类型
     * @return {@link FieldCodec} 实例
     * @throws NullPointerException 如果任何一个参数对象为 {@code null}
     */
    public static <ID, T> FieldCodec<ID, T, Long> ofDoubleWord(ID id
            , Function<T, Long> getter, BiConsumer<T, Long> setter) {
        return new FieldCodec<>(id, getter, setter, ver -> Codec::writeDoubleWord, ver -> Codec::readDoubleWord);
    }

    /**
     * 实例化一个 {@code Long} 值类型的 {@link FieldCodec}
     * @param id 字段 {@code ID}
     * @param getter 字段值获取方法
     * @param setter 字段值设置方法
     * @param <ID> 字段 {@code ID} 的类型
     * @param <T> 字段所属对象的类型
     * @return {@link FieldCodec} 实例
     * @throws NullPointerException 如果任何一个参数对象为 {@code null}
     */
    public static <ID, T> FieldCodec<ID, T, Long> ofLong(ID id
            , Function<T, Long> getter, BiConsumer<T, Long> setter) {
        return new FieldCodec<>(id, getter, setter, ver -> Codec::writeLong, ver -> Codec::readLong);
    }

    /**
     * 实例化一个 {@code STRING} 值类型的 {@link FieldCodec}
     * @param id 字段 {@code ID}
     * @param getter 字段值获取方法
     * @param setter 字段值设置方法
     * @param <ID> 字段 {@code ID} 的类型
     * @param <T> 字段所属对象的类型
     * @return {@link FieldCodec} 实例
     * @throws NullPointerException 如果任何一个参数对象为 {@code null}
     */
    public static <ID, T> FieldCodec<ID, T, String> ofString(ID id
            , Function<T, String> getter, BiConsumer<T, String> setter) {
        return new FieldCodec<>(id, getter, setter, ver -> Codec::writeString, ver -> Codec::readString);
    }

    /**
     * 实例化一个 {@link FieldCodec}
     * @param id 字段 {@code ID}
     * @param getter 字段值获取方法
     * @param setter 字段值设置方法
     * @param encoderSupplier 字段值编码方法
     * @param decoderSupplier 字段值解码方法
     * @throws NullPointerException 如果任何一个参数对象为 {@code null}
     */
    public FieldCodec(I id, Function<T, V> getter, BiConsumer<T, V> setter
            , Function<Integer, BiConsumer<ByteBuf, V>> encoderSupplier
            , Function<Integer, Function<ByteBuf, V>> decoderSupplier) {
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
     * 获取字段 {@code ID}
     * @return 字段 {@code ID}
     */
    public I getId() {
        return id;
    }

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

}
