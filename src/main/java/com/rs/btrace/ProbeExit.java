package com.rs.btrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

/**
 * This program demonstrates OnExit probe. When some BTrace action method calls "exit(int)" built-in function, method
 * annotated by @OnExit (if found) is called. In this method, BTrace script print summary information of tracing and/or
 * do clean-up.
 */

@BTrace
public class ProbeExit {

    private static volatile int i;

    // @OnExit is called when some BTrace method
    // calls exit(int) method
    @OnExit
    public static void onexit(int code) {
        println("BTrace program exits!");
    }

    // We just put @OnTimer probe and exit BTrace
    // program when the count reaches 5.

    @OnTimer(1000)
    public static void ontime() {
        println("hello");
        i++;
        if (i == 5) {
            // note that this exits the BTrace client
            // and not the traced program (which would
            // be a destructive action!).
            Sys.exit(0);
        }
    }
}
