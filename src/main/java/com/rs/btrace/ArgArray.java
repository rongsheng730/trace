package com.rs.btrace;

import com.sun.btrace.annotations.*;
import com.sun.btrace.AnyType;

import static com.sun.btrace.BTraceUtils.*;

/**
 * This sample demonstrates regular expression probe matching and getting input arguments as an array - so that any
 * overload variant can be traced in "one place". This example traces any "readXX" method on any class in java.io
 * package. Probed class, method and arg array is printed in the action.
 */
@BTrace
public class ArgArray {

    @OnMethod(
        clazz = "/java\\.io\\..*/",
        method = "/read.*/"
    )
    public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, AnyType[] args) {
        println(pcn);
        println(pmn);
        printArray(args);
    }
}
