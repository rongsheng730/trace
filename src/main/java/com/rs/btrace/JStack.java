package com.rs.btrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.Sys.*;
import static com.sun.btrace.BTraceUtils.Threads.*;

/*
 * A simple sample prints stack traces and exits. This
 * BTrace program mimics the jstack command line tool in JDK.
 */
@BTrace
public class JStack {

    static {
        deadlocks(false);
        jstackAll();
        exit(0);
    }
}
