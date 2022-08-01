package com.mhh.leetcode.study.Service;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.fastjson.JSON;
import com.mhh.leetcode.LeetcodeApplication;
import com.mhh.leetcode.annotation.ConvertType;
import com.mhh.leetcode.annotation.MyType;
import com.mhh.leetcode.solutions.Solution;
import com.mhh.leetcode.study.User1;
import com.mhh.leetcode.study.User2;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = LeetcodeApplication.class)
public class ServiceTest {


    @Test
    public void searchTest() {
        String fileName = "test1.xlsx";
        List<User2> user2List = new ArrayList<User2>();
        try {
            EasyExcel.read(fileName, User2.class, new PageReadListener<User2>(dataList -> {
                for (User2 demoData : dataList) {
                    System.out.println(JSON.toJSONString(demoData));
                    User2 user2 = JSON.parseObject(JSON.toJSONString(demoData), User2.class);
                    System.out.println(user2.getAddress());

                }
            })).sheet("Sheet2").doRead();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    @Test
    public void mhhTest2() {
        // 写法2：
        // 匿名内部类 不用额外写一个DemoDataListener
        String fileName = "test1.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        String msg = null;
        EasyExcel.read(fileName, User2.class, new ReadListener<User2>() {


            List<User2> user2s = new ArrayList<>();

            @Override
            public void invoke(User2 user2, AnalysisContext analysisContext) {
                System.out.println(JSON.toJSONString(user2));
                user2s.add(user2);
            }


            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

                try {
                    check(user2s);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                System.out.println("\n");
                System.out.println(JSON.toJSONString(user2s));
            }

            private void check(List<User2> user2s) throws IllegalAccessException {

                Class clz = User2.class;
                Field[] declaredFields = clz.getDeclaredFields();
                for (User2 user2 : user2s) {
                    for (Field field : declaredFields) {
                        if (field.isAnnotationPresent(ConvertType.class)) {
                            field.setAccessible(true);
                            int i = Arrays.asList(field.getAnnotation(ConvertType.class).oriValues()).indexOf(field.get(user2));
                            if (i == -1) {
                                System.out.println("检查枚举值是否正确");
                            } else {
                                field.set(user2, field.getAnnotation(ConvertType.class).destValues()[i]);
                            }
                        }
                    }
                }


            }
        }).sheet("Sheet2").doRead();


    }

    @Test
    public void Test2() throws IllegalAccessException {
        String fileName = "test1.xlsx";
        List<User2> user2s = EasyExcel.read(fileName).head(User2.class).sheet("Sheet2").doReadSync();

        if(user2s.size()<1){
            System.out.println("请检查是否有标红列未输入");
        }
        Class clz = User2.class;
        Field[] declaredFields = clz.getDeclaredFields();
        for (User2 user2 : user2s) {
            for (Field field : declaredFields) {
                if (field.isAnnotationPresent(ConvertType.class)) {
                    field.setAccessible(true);
                    int i = Arrays.asList(field.getAnnotation(ConvertType.class).oriValues()).indexOf(field.get(user2));
                    if (i == -1) {
                        System.out.println("检查枚举值是否正确");
                    } else {
                        field.set(user2, field.getAnnotation(ConvertType.class).destValues()[i]);
                    }
                }
            }
        }

        System.out.println("\n");
        System.out.println(JSON.toJSONString(user2s));


    }


}



