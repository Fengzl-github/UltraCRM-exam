package com.cn.exam.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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


    /**
     * @Author fengzhilong
     * @Desc 获取指定个指定范围内的随机数
     * @Date 2021/1/27 16:45
     * @param max 最大范围值
     * @param resNum 获取几个
     * @return Set
     **/
    public static Set<Integer> getRandom(int max, int resNum) {
        Set<Integer> set = new TreeSet();

        while (set.size() != resNum) {
            int n = (int) (0 + Math.random() * max);
            set.add(n);
        }
        System.out.println(set);

        return set;
    }


    /**
     * @Author fengzhilong
     * @Desc 获取一个指定范围内的随机数
     * @Date 2021/1/27 16:45
     * @param max 最大范围值
     * @return Integer
     **/
    public static Integer getOneRandom(int max) {

        int n = (int) (0 + Math.random() * max);
        System.out.println(n);

        return n;
    }

}
