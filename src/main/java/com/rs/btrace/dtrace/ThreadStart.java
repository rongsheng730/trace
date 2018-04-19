package com.rs.btrace.dtrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

/*
 * This BTrace script inserts a probe into
 * method entry of java.lang.Thread.start() method.
 * At each Thread.start(), it raises a DTrace probe
 * in addition to printing the name of the thread.
 * A D-script like jthread.d may be used to get the
 * associated DTrace probe events.
 */
@BTrace
public class ThreadStart {

    @OnMethod(
        clazz = "java.lang.Thread",
        method = "start"
    )
    public static void onnewThread(@Self Thread t) {
        D.probe("jthreadstart", Threads.name(t));
        println(Strings.strcat("starting ", Threads.name(t)));
    }
}
