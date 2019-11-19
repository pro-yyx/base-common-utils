package com.yyx.utils;

import com.github.pagehelper.PageInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 集合工具类
 * @Auther: yinyuxin
 * @Date: 2019/1/4 20:55
 */
public class CollectionUtily{

    private static final Logger LOGGER=LoggerFactory.getLogger(CollectionUtily.class);

    /**
     * 内存分页
     * @Author yinyuxin
     * @param list
     * @param currPageNo
     * @param pageSize
     * @param <T>
     * @return
     */
    public static <T> PageInfo<T> getPagingResultByMemory(List<T> list, Integer currPageNo, Integer pageSize) {
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

    /**
     * 两个集合求交集
     * @Author yinyuxin
     * @param listA
     * @param listB
     * @param <T>
     * @return
     */
    private static  <T> List<T> calculateIntersection(List<T> listA, List<T> listB) {
        try {
            List<T> commonList = new ArrayList<>();
            if (CollectionUtils.isEmpty(listA) || CollectionUtils.isEmpty(listB)) {
                return commonList;
            }
            Set<T> tempSet = new HashSet<>();
            tempSet.addAll(listA);
            for (T b : listB) {
                if (tempSet.contains(b)) {
                    commonList.add(b);
                }
            }
            return commonList;
        } catch (Exception e) {
            LOGGER.error("工具类执行失败:{}",ExceptionUtils.getFullStackTrace(e));
            return null;
        }
    }

    /**
     * List removeAll，解决原生removeAll方法大数据量下效率感人情况
     * @Author yinyuxin
     * @Param [sourceList, removeList]
     * @return java.util.List<T>
     **/
    private static  <T> List<T> listRemoveAll(List<T> sourceList, List<T> removeList) {
        try {
            if (CollectionUtils.isEmpty(sourceList)) {
                return null;
            }
            if (CollectionUtils.isEmpty(removeList)) {
                return sourceList;
            }
            LinkedList<T>  tempList = new LinkedList(sourceList);
            HashSet<T> tempSet = new HashSet(removeList);
            Iterator iter=tempList.iterator();
            while(iter.hasNext()){
                if (tempSet.contains(iter.next())) {
                    iter.remove();
                }
            }
            return tempList;
        } catch (Exception e) {
            LOGGER.error("工具类执行失败:{}",ExceptionUtils.getFullStackTrace(e));
            return null;
        }
    }

}
