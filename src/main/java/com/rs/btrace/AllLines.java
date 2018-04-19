package com.rs.btrace;

import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;

/*
 * This sample prints a line every time any line
 * of code of java.lang.Thread class is reached.
 * The line param may be set to any particular
 * value so that the probe fires only when that line
 * is reached. But, the value -1 means all line numbers.
 */
@BTrace
public class AllLines {
    @OnMethod(
        clazz="java.lang.Thread",
        location=@Location(value=Kind.LINE, line=-1)
    )
    public static void online(@ProbeClassName String pcn, @ProbeMethodName String pmn, int line) {
        print(Strings.strcat(pcn, "."));
        print(Strings.strcat(pmn, ":"));
        println(line);
    } 
}
