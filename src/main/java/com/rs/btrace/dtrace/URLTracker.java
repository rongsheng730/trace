package com.rs.btrace.dtrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;
import static com.sun.btrace.BTraceUtils.Strings.*;

import java.net.*;

/*
 * This sample prints every Java URL openURL and
 * openConnection (successful) attempts. In addition,
 * on platforms where DTrace is available, it runs
 * the D-script jurls.d -- which collects a histogram
 * of URL accesses by a btrace:::event probe. From this
 * BTrace program we raise that DTrace probe (dtraceProbe
 * call). Note that it is possible to do similar histogram
 * in BTrace itself (see Histogram.java). But, this sample
 * shows DTrace/BTrace integration as well. On exit, all
 * DTrace aggregates are printed by BTrace (i.e., the ones
 * that are not explicitly printed by DTrace printa call).
 */
@DTraceRef("jurls.d")
@BTrace
public class URLTracker {

    @TLS
    private static URL url;

    @OnMethod(
        clazz = "java.net.URL",
        method = "openConnection"
    )
    public static void openURL(URL self) {
        url = self;
    }

    @OnMethod(
        clazz = "java.net.URL",
        method = "openConnection"
    )
    public static void openURL(URL self, Proxy p) {
        url = self;
    }

    @OnMethod(
        clazz = "java.net.URL",
        method = "openConnection",
        location = @Location(Kind.RETURN)
    )
    public static void openURL() {
        if (url != null) {
            println(Strings.strcat("open ", Strings.str(url)));
            D.probe("java-url-open", Strings.str(url));
            url = null;
        }
    }
}
