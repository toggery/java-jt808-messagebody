package io.github.toggery.jt808.messagebody;

import java.util.StringJoiner;

/**
 * JT/T 消息体 0x8202 临时位置跟踪控制
 *
 * @author togger
 */
public class B8202 extends AbstractToStringJoiner {

    /** WORD 时间间隔，单位为秒（s），0 则停止跟踪。停止跟踪无需带后继字段 */
    private int interval;

    /** DWORD 位置跟踪有效期，单位为秒（s），终端在接收到位置跟踪控制消息后，在有效期截止时间之前，依据消息中的时间间隔发送位置汇报 */
    private long duration;


    @Override
    protected void toStringJoiner(StringJoiner joiner) {
        joiner
                .add("interval=" + interval)
                .add("duration=" + duration)
        ;
    }


    /**
     * 获取时间间隔，单位为秒（s）
     * @return WORD 时间间隔，单位为秒（s），0 则停止跟踪。停止跟踪无需带后继字段
     */
    public int getInterval() {
        return interval;
    }

    /**
     * 设置时间间隔，单位为秒（s）
     * @param interval WORD 时间间隔，单位为秒（s），0 则停止跟踪。停止跟踪无需带后继字段
     */
    public void setInterval(int interval) {
        this.interval = interval;
    }

    /**
     * 获取位置跟踪有效期，单位为秒（s）
     * @return DWORD 位置跟踪有效期，单位为秒（s），终端在接收到位置跟踪控制消息后，在有效期截止时间之前，依据消息中的时间间隔发送位置汇报
     */
    public long getDuration() {
        return duration;
    }

    /**
     * 设置位置跟踪有效期，单位为秒（s）
     * @param duration DWORD 位置跟踪有效期，单位为秒（s），终端在接收到位置跟踪控制消息后，在有效期截止时间之前，依据消息中的时间间隔发送位置汇报
     */
    public void setDuration(long duration) {
        this.duration = duration;
    }

}
