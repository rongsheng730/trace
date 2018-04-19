package com.rs.btrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

/**
 * A simple example that demonstrates subtype matching by +foo pattern in "clazz" attribute of @OnMethod annotation.
 */
@BTrace
public class SubtypeTracer {

    @OnMethod(
        clazz = "+java.lang.Runnable",
        method = "run"
    )
    public static void onRun(@ProbeClassName String pcn, @ProbeMethodName String pmn) {
        // on every Runnable.run() method entry print class.method
        print(pcn);
        print('.');
        println(pmn);
    }
}
