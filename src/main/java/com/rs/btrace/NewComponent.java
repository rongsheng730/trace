package com.rs.btrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

import java.awt.Component;

/**
 * 两秒输出一次java.awt.Component初始化的总次数 A BTrace program that can be run against a GUI program. This program prints
 * (monotonic) count of number of java.awt.Components created once every 2 seconds (2000 milliseconds).
 */

@BTrace
public class NewComponent {

    // component count
    private static volatile long count;

    @OnMethod(
        clazz = "java.awt.Component",
        method = "<init>"
    )
    public static void onnew(@Self Component c) {
        // increment counter on constructor entry
        count++;
    }

    /**
     * 两秒打印一次
     */
    @OnTimer(2000)
    public static void print() {
        // print the counter
        println(Strings.strcat("component count = ", str(count)));
    }
}
