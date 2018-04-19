package com.rs.btrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

/**
 * This script demonstrates the possibility to intercept method calls that are about to be executed from the body of a
 * certain method. This is achieved by using the {@linkplain Kind#CALL} location value.
 */
@BTrace
public class AllCalls2 {

    @OnMethod(
        clazz = "/javax\\.swing\\..*/", method = "/.*/",
        location = @Location(value = Kind.CALL, clazz = "/.*/", method = "/.*/")
    )
    public static void n(@Self Object self, @ProbeClassName String pcm, @ProbeMethodName String pmn,
        @TargetInstance Object instance, @TargetMethodOrField String method,
        String text) { // all calls to the methods with signature "(String)"
        println(Strings.strcat("Context: ", Strings.strcat(pcm, Strings.strcat("#", pmn))));
        print(method);
        print(" ");
        println(text);
    }
} 
