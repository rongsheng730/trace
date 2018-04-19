package com.rs.btrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

/**
 * Demonstrates multiple timer probes with different periods to fire.
 */
@BTrace
public class Timers {

    // when starting print the target VM version and start time
    static {
        println(Strings.strcat("vm version ", Sys.VM.vmVersion()));
        println(Strings.strcat("vm starttime ", Strings.str(Sys.VM.vmStartTime())));
    }

    @OnTimer(1000)
    public static void f() {
        println(Strings.strcat("1000 msec: ", Strings.str(Sys.VM.vmUptime())));
    }

    @OnTimer(3000)
    public static void f1() {
        println(Strings.strcat("3000 msec: ", Strings.str(Time.millis())));
    }

}
