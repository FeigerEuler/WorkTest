package com.mhh.leetcode.annotation;

public enum MyType {
    /*
    1.public static final可以省略不写
    2.枚举对象不能像自定义枚举类对象时new
    3.枚举名1(值1,值2),枚举名2(值1,值2),枚举名3(值1,值2);
    4.不需要生成toString,因为使用enum关键字定义的枚举类继承了java.lang.Enum
    5.在Enum中重写了继承自Object的toString()直接打印的就是枚举名
     */
    //1.通过枚举名传形参
    HAMBURG("汉堡","又大又扁"),
    BEEF("牛肉","肉质柔软"),
    MUTTON("羊肉 ","烹调的香味");
    //2.私有化常量类型
    private  final String FOOD_NAME;
    private  final String FOOD_DESE;
    //3.提供枚举形参构造器
    MyType(String FOOD_NAME, String FOOD_DESE) {
        this.FOOD_NAME = FOOD_NAME;
        this.FOOD_DESE = FOOD_DESE;
    }
    //4.提供get方法
    public String getFOOD_NAME() {
        return FOOD_NAME;
    }
    public String getFOOD_DESE() {
        return FOOD_DESE;
    }
}
