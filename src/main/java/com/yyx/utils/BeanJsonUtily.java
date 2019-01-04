package com.yyx.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Description: fastjson工具类
 * @Auther: yinyuxin
 * @Date: 2018/4/27 10:10
 */
public class BeanJsonUtily {

    private static final Logger LOGGER=LoggerFactory.getLogger(BeanJsonUtily.class);

    private static final SerializeConfig SERIALIZE_CONFIG;

    static {
        SERIALIZE_CONFIG=new SerializeConfig();
        SERIALIZE_CONFIG.put(Date.class,new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
    }

    private static final SerializerFeature[] serializerFeatures={SerializerFeature.DisableCircularReferenceDetect,
            SerializerFeature.WriteMapNullValue
    };

    /**
     * bean对象转成json字符串。空字段会输出为null
     * @author yinyuxin
     * @param obj
     * @return
     */
    public static String obj2String(Object obj){
        try {
            String objString=JSONObject.toJSONString(obj,SERIALIZE_CONFIG,serializerFeatures);
            return objString;
        } catch (Exception e) {
            LOGGER.error("工具类执行失败:{}",ExceptionUtils.getFullStackTrace(e));
            return null;
        }
    }


    /**
     * json字符串转bean对象
     * @author yinyuxin
     * @param jsonString
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> T string2Obj(String jsonString,Class<T> clz){
        try {
            Object object=JSONObject.parseObject(jsonString,clz);
            return (T)object;
        } catch (Exception e) {
            LOGGER.error("工具类执行失败:{}",ExceptionUtils.getFullStackTrace(e));
            return null;
        }
    }

    /**
     * json字符串转List对象
     * @author yinyuxin
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> string2List(String text, Class<T> clazz) {
        try {
            return JSON.parseArray(text, clazz);
        } catch (Exception e) {
            LOGGER.error("工具类执行失败:{}",ExceptionUtils.getFullStackTrace(e));
            return null;
        }
    }

    /**
     * 对象转换
     * @author yinyuxin
     * @param object
     * @param clz
     * @param <T>
     * @return
     */
    public static <T>T  beanConvert(Object object,Class<T> clz){
        if (Objects.isNull(object)){
            return null;
        }
        try {
            return string2Obj(obj2String(object),clz);
        } catch (Exception e) {
            LOGGER.error("工具类执行失败:{}",ExceptionUtils.getFullStackTrace(e));
            return null;
        }
    }

    /**
     * 对象转换（list）
     * @author yinyuxin
     * @param object
     * @param clz
     * @param <T>
     * @return
     */
    public static <T>List<T>  listConvert(Object object,Class<T> clz){
        if (Objects.isNull(object)){
            return null;
        }
        try {
            return string2List(obj2String(object),clz);
        } catch (Exception e) {
            LOGGER.error("工具类执行失败:{}",ExceptionUtils.getFullStackTrace(e));
            return null;
        }
    }
}
