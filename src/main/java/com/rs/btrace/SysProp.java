package com.rs.btrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

/**
 * This BTrace script demonstrates that it is okay to trace bootstrap classes and call the same inside the trace
 * actions. In this example, we insert a probe into System.getProperty() method and call System.getProperty [through
 * property() built-in function] without getting into infinite recursion. A thread local flag is used by BTrace to avoid
 * infinite recursion here.
 */
@BTrace
public class SysProp {

    @OnMethod(
        clazz = "java.lang.System",
        method = "getProperty"
    )
    public static void onGetProperty(String name) {
        println(name);
        // call property safely here.
        println(Sys.Env.property(name));
    }
}	
