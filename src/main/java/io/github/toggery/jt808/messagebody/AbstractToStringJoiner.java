package io.github.toggery.jt808.messagebody;

import java.util.StringJoiner;

/**
 * JT/T 可文本化字段基类
 * @author togger
 */
public abstract class AbstractToStringJoiner {

    @Override
    public String toString() {
        final StringJoiner joiner = new StringJoiner(", ", getClass().getSimpleName() + "(", ")");
        toStringJoiner(joiner);
        return joiner.toString();
    }

    /**
     * 将各个字段文本化到指定的 {@link StringJoiner} 实例
     * @param joiner {@link StringJoiner} 实例
     */
    protected abstract void toStringJoiner(StringJoiner joiner);
}
