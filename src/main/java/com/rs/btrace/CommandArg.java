package com.rs.btrace;

import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;

/**
 * This BTrace program demonstrates command line
 * arguments. $ method helps in getting command line
 * arguments. In this example, desired thread name is
 * passed from the command line (of the BTrace client).
 */
@BTrace public class CommandArg {
    @OnMethod(
        clazz="java.lang.Thread",
        method="run"
    )
    public static void started() {
        if (Strings.strcmp(Threads.name(Threads.currentThread()), Sys.$(2)) == 0) {
            println(Strings.strcat("started ", Sys.$(2)));
        }
    }
}
