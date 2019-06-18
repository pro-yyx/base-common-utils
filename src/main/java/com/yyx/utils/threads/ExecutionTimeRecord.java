package com.yyx.utils.threads;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ExecutionTimeRecord
 * @Description //用于记录多线程环境下代码在单条线程下的执行时间
 * @Author yinyx2@lenovo.com
 * @Date 2019/6/18 16:08
 **/
public class ExecutionTimeRecord {

    private static ThreadLocal<Map<String,Long>> executionTime = new ThreadLocal<>();

    public static Map<String, Long> getExectionTime() {
        if (executionTime == null || executionTime.get() == null) {
            executionTime.set(new HashMap<>());
        }
        return executionTime.get();
    }

    public static void setExecutionTime(String key,Long timeLong) {
        getExectionTime().putIfAbsent(key, timeLong);
    }

    public static void removeCurThreadExecutionTimeRecord() {
        if (executionTime == null || executionTime.get() == null) {
            return;
        }
        executionTime.remove();
    }
}
