package com.rs.btrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

import com.sun.btrace.annotations.Export;

/**
 * This sample creates a jvmstat counter and increments it everytime Thread.start() is called. This thread count may be
 * accessed from outside the process. The @Export annotated fields are mapped to jvmstat counters. The counter name is
 * "btrace." + <className> + "." + <fieldName>
 */
@BTrace
public class ThreadCounter {

    // create a jvmstat counter using @Export
    @Export
    private static long count;

    @OnMethod(
        clazz = "java.lang.Thread",
        method = "start"
    )
    public static void onnewThread(@Self Thread t) {
        // updating counter is easy. Just assign to
        // the static field!
        count++;
    }

    @OnTimer(2000)
    public static void ontimer() {
        // we can access counter as "count" as well
        // as from jvmstat counter directly.
        println(count);
        // or equivalently ...
        println(Counters.perfLong("btrace.com.rs.btrace.ThreadCounter.count"));
    }
}
