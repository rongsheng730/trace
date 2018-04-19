package com.rs.btrace.dtrace;

import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;

/*
 * This sample demonstrates DTrace/BTrace integration.
 * A one-liner D-script is started by BTrace client 
 * because of @DTrace annotation. In this example 
 * on new Java Thread starts, BTrace action raises a
 * DTrace probe. The D-script prints mixed mode stack
 * trace on receiving this probe.
 */
@DTrace("btrace$1:::event / copyinstr(arg0) == \"mstack\" / { jstack(); }")
@BTrace
public class DTraceInline {
    @OnMethod(
        clazz="java.lang.Thread",
        method="start"
    )
    public static void newThread(Thread th) {
        println(Threads.name(th));
        D.probe("mstack", "");
    } 
}
