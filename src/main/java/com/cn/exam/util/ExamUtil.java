package com.cn.exam.util;

import java.util.ArrayList;
import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/1/13 10:54
 *@Desc
 **/
public class ExamUtil {


    /**
     * @Author fengzhilong
     * @Desc 用于多选条件转换成list
     * @Date 2021/1/13 10:58
     * @param tempList
     * @return java.util.List<java.lang.Integer>
     **/
    public static List<Integer> getListOfMultiple(String tempList) {
        List<Integer> list = new ArrayList<>();
        String[] split = tempList.split(",");

        for (String str : split
        ) {
            list.add(Integer.parseInt(str));
        }

        return list;
    }
}
