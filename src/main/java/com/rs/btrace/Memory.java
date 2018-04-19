package com.rs.btrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

/**
 * Simple BTrace program that prints memory usage once every 4 seconds. It is possible to modify this to dump heap
 * depending on used memory crossing a threshold or some other such condition. [dumpHeap is a built-in function].
 */
@BTrace
public class Memory {

    @OnTimer(4000)
    public static void printMem() {
        println("Heap:");
        println(Sys.Memory.heapUsage());
        println("Non-Heap:");
        println(Sys.Memory.nonHeapUsage());
    }
}
