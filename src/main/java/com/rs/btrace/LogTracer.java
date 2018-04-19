package com.rs.btrace;

import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;
import java.util.logging.*;
import java.lang.reflect.Field;

/**
 * Simple log message tracer class. This class
 * prints all log messages regardless of log Level.
 * Note that we read LogRecord's private "message"
 * field using "field()" and "objectValue()" built-ins.
 */
@BTrace public class LogTracer {
    private static Field msgField = Reflective.field("java.util.logging.LogRecord", "message");

    @OnMethod(
        clazz="+java.util.logging.Logger",
        method="log"
    )
    public static void onLog(@Self Logger self, LogRecord record) {
        println(Reflective.get(msgField, record));
    }
}
