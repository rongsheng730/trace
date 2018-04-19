package com.rs.btrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This sample collects histogram of javax.swing.JComponets created by traced app. But, the histogram is printed only on
 * event (from client).
 */
@BTrace
public class HistoOnEvent {

    private static Map<String, AtomicInteger> histo = newHashMap();

    @OnMethod(
        clazz = "javax.swing.JComponent",
        method = "<init>"
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

    @OnEvent
    public static void print() {
        if (size(histo) != 0) {
            printNumberMap("Component Histogram", histo);
        }
    }
}
