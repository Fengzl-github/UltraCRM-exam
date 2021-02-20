package com.cn.exam.restful.test;

import lombok.Data;

import java.util.ArrayList;

/**
 *@Author fengzhilong
 *@Date 2021/1/13 17:33
 *@Desc
 **/
@Data
public class TestRestful {

    public static void main(String[] args) {
        getTest();
    }

    public static void getTest() {
        ArrayList arrayList = new ArrayList(0);
        arrayList.add("li");
        arrayList.add("hi");
        arrayList.add("fi");
        System.out.println(arrayList);

        Object ji = arrayList.add("ji");
        System.out.println(ji);
        System.out.println(arrayList);
        //
    }


}
