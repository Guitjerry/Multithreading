package com.jvm;

public class ClassLoaderTest {
    public static void main(String[] args) {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);//sun.misc.Launcher$AppClassLoader@b4aac2
        //获取上层类加载器 扩展类加载器
        ClassLoader exClassLoader = systemClassLoader.getParent();
        System.out.println(exClassLoader);//sun.misc.Launcher$ExtClassLoader@10e140b

        ClassLoader s = exClassLoader.getParent();
        System.out.println(s);
        //对于用户自定义类来说，默认使用系统加载器加载
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);

    }
}
