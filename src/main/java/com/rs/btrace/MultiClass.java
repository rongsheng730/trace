package com.rs.btrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

/**
 * This BTrace class demonstrates that we can probe into multiple classes and methods by a single probe specification
 * using regular expressions for class and/or method names as given below. In the example, we put probe into all readXXX
 * methods of all InputStream classes.
 */
@BTrace
public class MultiClass {

    @OnMethod(
        clazz = "/java\\.io\\..*Input.*/",
        method = "/read.*/"
    )
    public static void onread(@ProbeClassName String pcn) {
        println(Strings.strcat("read on ", pcn));
    }
}
