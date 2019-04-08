package com.suixinlu.zuuleureka7073.test;

/**
 * 接口之间可以互相继承，可以理解为是一种规范的组合，
 * 子类在继承这些接口组合的时候，必须要实现所有的规范
 */
public class MyDoorSecond implements AlramSecond {
    @Override
    public void alarm() {
        //此方法其实是在AlramSecond的父接口声明的
    }
}
