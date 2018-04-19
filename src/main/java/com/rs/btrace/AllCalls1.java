package com.rs.btrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

/**
 * This script demonstrates the possibility to intercept method calls that are about to be executed from the body of a
 * certain method. This is achieved by using the {@linkplain Kind#CALL} location value.
 */
@BTrace
public class AllCalls1 {

    @OnMethod(
        clazz = "javax.swing.JTextField", method = "/.*/",
        location = @Location(value = Kind.CALL, clazz = "/.*/", method = "/.*/")
    )
    public static void m(@Self Object self,
        @TargetMethodOrField String method,
        @ProbeMethodName String probeMethod) { // all calls to the methods with signature "()"
        println(Strings.strcat(method, Strings.strcat(" in ", probeMethod)));
    }
}
