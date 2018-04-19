package com.rs.btrace;

import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;

/*
 * A simple sample that dumps heap of the target at start and exits.
 * This BTrace program mimics the jmap tool (with -dump option).
 */
@BTrace
public class JMap {
    static {
        String name;
        if (Sys.$length() == 3) {
            name = Sys.$(2);
        } else {
            name = "heap.hprof";
        }
        Sys.Memory.dumpHeap(name);
        println("heap dumped!");
        Sys.exit(0);
    }
}
