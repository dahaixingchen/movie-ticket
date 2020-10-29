package com.fei.elasticsearch.data;

import jdk.internal.dynalink.beans.StaticClass;
import org.junit.jupiter.api.Test;

/**
 * @ClassName: StaticClassTest
 * @Author chengfei
 * @Date 2020/10/29 10:36
 * @Description: TODO
 **/
public class StaticClassTest {
    public static long OUTER_DATE = System.currentTimeMillis();

    static {
        System.out.println("外部类静态块加载时间：" + System.currentTimeMillis());
    }

    public StaticClassTest() {
        System.out.println("外部类构造函数时间：" + System.currentTimeMillis());
    }

    static class InnerStaticClass {
        public static long INNER_STATIC_DATE = System.currentTimeMillis();

        static {
            System.out.println("静态内部类静态块加载时间：" + System.currentTimeMillis());
        }
    }

    class InnerClass {
        public long INNER_DATE = 0;

        public InnerClass() {
            INNER_DATE = System.currentTimeMillis();
        }
    }

    @Test
    void test() {
        InnerClass innerClass = new InnerClass();
        System.out.println(innerClass.INNER_DATE);
    }

    @Test
    void test1() {
        System.out.println(InnerStaticClass.INNER_STATIC_DATE);
    }

    public static void main(String[] args) {
        System.out.println(InnerStaticClass.INNER_STATIC_DATE);
    }
//    public static void main(String[] args) {
//        InnerClass innerClass = new InnerClass();
//        System.out.println();
//    }

//    public static void main(String[] args) {
//        StaticClassTest outer = new StaticClassTest();
//        System.out.println("外部类静态变量加载时间：" + outer.OUTER_DATE);
//    }
}
