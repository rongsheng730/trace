package com.rs.btrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

/**
 * This sample demonstrates that you can expose a BTrace class as a JMX MBean. After connecting BTrace to the target
 * application, connect VisualVM or jconsole or any other JMX client to the same application.
 */
@BTrace
public class ThreadCounterBean {

    // @Property makes the count field to be exposed
    // as an attribute of this MBean.
    @Property
    private static long count;

    @OnMethod(
        clazz = "java.lang.Thread",
        method = "start"
    )
    public static void onnewThread(@Self Thread t) {
        count++;
    }

    @OnTimer(2000)
    public static void ontimer() {
        println(count);
    }
}
