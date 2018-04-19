package com.rs.btrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

/**
 * This script demonstrates the possibility to intercept array creations that are about to be executed from the body of
 * a certain method. This is achieved by using the {@linkplain Kind#NEWARRAY} location value.
 */
@BTrace
public class NewArray {

    // component count
    private static volatile long count;

    @OnMethod(
        clazz = "/.*/", // tracking in all classes; can be restricted to specific user classes
        method = "/.*/", // tracking in all methods; can be restricted to specific user methods
        location = @Location(value = Kind.NEWARRAY, clazz = "char")
    )
    public static void onnew(@ProbeClassName String pcn, @ProbeMethodName String pmn, String arrType, int dim) {
        // pcn - allocation place class name
        // pmn - allocation place method name
        // **** following two parameters MUST always be in this order
        // arrType - the actual array type
        // dim - the array dimension

        // increment counter on new array
        count++;
    }

    @OnTimer(2000)
    public static void print() {
        // print the counter
        println(Strings.strcat("char[] count = ", str(count)));
    }
}
