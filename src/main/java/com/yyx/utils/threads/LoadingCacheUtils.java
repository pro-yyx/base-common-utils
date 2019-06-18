package com.yyx.utils.threads;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName LoadingCacheUtils
 * @Description guava loadingCache util
 * @Author yinyx2
 * @Date 2019/5/7 11:08
 **/
public class LoadingCacheUtils {

    private static final LoadingCache<String, String> cacheBuilder = CacheBuilder.newBuilder().maximumSize(2)
        .refreshAfterWrite(2, TimeUnit.MILLISECONDS).removalListener(new RemovalListener<Object, Object>() {
            @Override
            public void onRemoval(RemovalNotification<Object, Object> removalNotification) {
                System.out.println(removalNotification.getKey() + "被移除");
            }
        }).build(new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
                return s;
            }
        });

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(cacheBuilder.get("yyx"));
        Thread.sleep(2000);
        System.out.println(cacheBuilder.get("yyx3"));
    }
}
