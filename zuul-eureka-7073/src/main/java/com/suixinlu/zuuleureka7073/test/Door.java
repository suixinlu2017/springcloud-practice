package com.suixinlu.zuuleureka7073.test;

/**
 * 可以声明实现接口，但是不需要写实现，等于是给自己加了个规范，
 * 以后子类就没得选择，必须要实现这些接口才行，属于一种强制性的规范，
 * 没有子类单独去声明那么多自由度
 */
public abstract class Door implements Alram {
    abstract void open();
    abstract void close();
}
