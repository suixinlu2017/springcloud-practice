package com.suixinlu.zuuleureka7073.test;

/**
 * 做一个接口，抽象类和实现类的测试
 * 接口和抽象类都是public，否则就没有意义，本身就不实现功能，而是定义规范，不公开别的类没法实现
 *
 * 接口的方法的修饰符默认是public abstract;也只能是public abstract（不能违背public abstract）
 * 你可以不写修饰符；你可以只写public；你可以只写abstract；但是你不能写成private,也不能有方法体。
 *
 * 接口的变量（其实是常量，因为用final修饰）的修饰符默认是public static final; 也只能是public static final。
 * 同方法一样，要么不写或者少写修饰符（因为默认修饰符），如果写就不要违背public static final
 */
public interface Alram {
    String STRING = "接口变量";
    void alarm();
}
