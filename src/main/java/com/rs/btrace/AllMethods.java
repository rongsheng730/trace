package com.rs.btrace;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;
import com.sun.btrace.annotations.ProbeMethodName;

/**
 * 可以用表达式，批量定义需要监控的类与方法。正则表达式需要写在两个 "/" 中间。可能会非常慢，建议范围还是窄些。
 * This script traces method entry into every method of every class in com.rs package!
 * Think before using this script -- this will slow down your app significantly!!
 */
@BTrace
public class AllMethods {

    /**
     *
     * @param probeClass 匹配到的类名
     * @param probeMethod 匹配到的方法名
     */
    @OnMethod(
        clazz="/com\\.rs\\..*/",
        method="/.*/"
    )
    public static void m(@ProbeClassName String probeClass, @ProbeMethodName String probeMethod) {
        BTraceUtils.println("entered " + probeClass + "." + probeMethod);
    }
} 
