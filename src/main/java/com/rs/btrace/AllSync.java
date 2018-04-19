package com.rs.btrace;

import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;

/**
 * This script traces method/block entry into every method of 
 * every class in javax.swing package! Think before using 
 * this script -- this will slow down your app significantly!!
 * Note tha Where.BEFORE is default. For synchronized blocks, BEFORE
 * means before "monitorenter" bytecode. For synchronized methods, we
 * can not have probe point Where.BEFORE. Lock is acquired before entering
 * synchronized method. By making the probe point Where.AFTER for SYNC_ENTER,
 * we probe after monitorenter bytecode or synchronized method entry.
 */
@BTrace public class AllSync {
    @OnMethod(
        clazz="/javax\\.swing\\..*/",
        method="/.*/",
        location=@Location(value=Kind.SYNC_ENTRY, where=Where.AFTER) 
    )
    public static void onSyncEntry(Object obj) {
        println(Strings.strcat("after synchronized entry: ", identityStr(obj)));
    }

    @OnMethod(
        clazz="/javax\\.swing\\..*/",
        method="/.*/",
        location=@Location(Kind.SYNC_EXIT) 
    )
    public static void onSyncExit(Object obj) {
        println(Strings.strcat("before synchronized exit: ", identityStr(obj)));
    }
} 
