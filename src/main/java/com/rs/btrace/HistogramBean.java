package com.rs.btrace;

import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This sample collects histogram of javax.swing.JComponets
 * created by traced app. The histogram is printed once
 * every 4 seconds. Also, this example exposes the trace class
 * as a JMX bean. After connecting BTrace to the target  
 * application, connect VisualVM or jconsole or any other 
 * JMX client to the same application.
 */
@BTrace public class HistogramBean { 
   // @Property exposes this field as MBean attribute
   @Property
   private static Map<String, AtomicInteger> histo = newHashMap();

    @OnMethod(
        clazz="javax.swing.JComponent",
        method="<init>"
    ) 
    public static void onnewObject(@Self Object obj) {
        String cn = name(classOf(obj));
        AtomicInteger ai = get(histo, cn);
        if (ai == null) {
            ai = newAtomicInteger(1);
            put(histo, cn, ai);
        } else {
            incrementAndGet(ai);
        }     
    }

    @OnTimer(4000) 
    public static void print() {
        if (size(histo) != 0) {
            printNumberMap("Component Histogram", histo);
        }
    }
}
