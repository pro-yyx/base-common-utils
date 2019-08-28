package com.yyx.utils.threads;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName test
 * @Description //TODO
 * @Author yinyx2@lenovo.com
 * @Date 2019/6/24 11:57
 **/
public class test {

    public static void main(String[] args) {
        apiReturnInTime();
    }

    private static void apiReturnInTime() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        try {
            int callTimeOut = 5000;
            Callable<String> callable = new Callable<String>() {
                @Override
                public String call() {
                    return getstring();
                }
            };
            Future<String> future = executorService.submit(callable);
            String responseSuccessDataResponse = future
                .get(callTimeOut, TimeUnit.MILLISECONDS);
            System.out.println(responseSuccessDataResponse);;
        } catch (Exception e) {
            throw new RuntimeException("call msa calculatePointToMoney() timeout");
        } finally {
            executorService.shutdown();
        }

    }

    private static String getstring() {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            System.out.println("dddddd");
        }
        return "yyx";
    }

}
