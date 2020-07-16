package com.jvm;

/**
 * 静态引用对应的对象实体始终都存在堆空间
 * jdk8
 * -Xms200 -Xmx200 -XX:MetaspaceSize:300m -XX:maxMetaspaceSize=300m -XX:+PrintGCDetails
 */
public class StaticFieldTest {
    private static byte[] arr = new byte[1024*1024*1024];
    public static void main(String[] args) {
        System.out.println(arr.length);
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
