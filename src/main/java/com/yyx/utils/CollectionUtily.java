package com.yyx.utils;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

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
    public static <T> PageInfo<T> getPagingResult(List<T> list, Integer currPageNo, Integer pageSize) {
        PageInfo<T> pageInfo = new PageInfo<>();
        try {
            if (list.isEmpty()) {
                pageInfo.setList(Collections.emptyList());
                pageInfo.setPageNum(currPageNo);
                pageInfo.setPageSize(pageSize);
                pageInfo.setTotal(0);
                pageInfo.setPages(0);
                return pageInfo;
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
            pageInfo.setList(result);
            pageInfo.setPageSize(result.size());
            pageInfo.setPageNum(realPageNo);
            pageInfo.setTotal(totalRowNum);
            pageInfo.setPages(totalPageNum);
            return pageInfo;
        } catch (Exception e) {
            LOGGER.error("工具类执行失败:{}",ExceptionUtils.getFullStackTrace(e));
            return null;
        }
    }
}
