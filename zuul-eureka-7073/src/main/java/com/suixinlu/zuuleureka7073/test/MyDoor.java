package com.suixinlu.zuuleureka7073.test;

/**
 * 测试结果显示子类不光要实现抽象类的方法，也要实现父类实现的接口方法
 */
public class MyDoor extends Door {
    @Override
    void open() {

    }

    @Override
    void close() {

    }

    @Override
    public void alarm() {

    }
    public static void main(String[] args) {
        System.out.println("");
        MyDoor myDoor = new MyDoor();
        System.out.println(";result=" + "");
    }
}
