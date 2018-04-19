package com.rs.btrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This sample collects histogram of javax.swing.JComponets created by traced app. The histogram is printed once every 4
 * seconds.
 */
@BTrace
public class Histogram {

    private static Map<String, AtomicInteger> histo = Collections.newHashMap();

    @OnMethod(
        clazz = "javax.swing.JComponent",
        method = "<init>"
    )
    public static void onnewObject(@Self Object obj) {
        String cn = Reflective.name(classOf(obj));
        AtomicInteger ai = Collections.get(histo, cn);
        if (ai == null) {
            ai = Atomic.newAtomicInteger(1);
            Collections.put(histo, cn, ai);
        } else {
            Atomic.incrementAndGet(ai);
        }
    }

    @OnTimer(4000)
    public static void print() {
        if (Collections.size(histo) != 0) {
            printNumberMap("Component Histogram", histo);
        }
    }
}
