package com.yyx.utils;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 集合工具类
 * @Auther: yinyuxin
 * @Date: 2019/1/4 20:55
 */
public class CollectionUtily {

    private static final Logger LOGGER=LoggerFactory.getLogger(CollectionUtily.class);

    /**
     * 内存分页
     * @param list
     * @param currPageNo
     * @param pageSize
     * @param <T>
     * @return
     */
    public static  <T> Map<String, Object> getPagingResultMap(List<T> list, Integer currPageNo, Integer pageSize) {
        Map<String, Object> retMap = new HashMap<>();
        try {
            if (list.isEmpty()) {
                retMap.put("data", Collections.emptyList());
                retMap.put("pageNum", 0);
                retMap.put("pageSize", 0);
                retMap.put("total", 0);
                retMap.put("pages", 0);
                return retMap;
            }
            int totalRowNum = list.size();
            int totalPageNum = (totalRowNum - 1) / pageSize + 1;
            int realPageNo = currPageNo;
            if (currPageNo > totalPageNum) {
                realPageNo = totalPageNum;
            } else if (currPageNo < 1) {
                realPageNo = 1;
            }
            int fromIdx = (realPageNo - 1) * pageSize;
            int toIdx = totalPageNum * pageSize > totalRowNum ? totalRowNum : realPageNo * pageSize;
            List<T> result = list.subList(fromIdx, toIdx);
            retMap.put("data", result);
            retMap.put("pageNum", realPageNo);
            retMap.put("pageSize", result.size());
            retMap.put("total", totalRowNum);
            retMap.put("pages", totalPageNum);
            return retMap;
        } catch (Exception e) {
            LOGGER.error("工具类执行失败:{}",ExceptionUtils.getFullStackTrace(e));
            return null;
        }
    }
}
