package com.rs.btrace;

import static com.sun.btrace.BTraceUtils.*;

import com.sun.btrace.annotations.*;

/**
 * A simple BTrace program that prints a class name and method name whenever a webservice is called and also prints time
 * taken by service method. WebService entry points are annotated javax.jws.WebService and javax.jws.WebMethod. We
 * insert tracing actions into every class and method annotated by these annotations. This way we don't need to know
 * actual webservice implementor class name.
 */
@BTrace
public class WebServiceTracker {

    @OnMethod(
        clazz = "@javax.jws.WebService",
        method = "@javax.jws.WebMethod"
    )
    public static void onWebserviceEntry(@ProbeClassName String pcn, @ProbeMethodName String pmn) {
        print("entering webservice ");
        println(Strings.strcat(Strings.strcat(pcn, "."), pmn));
    }

    @OnMethod(
        clazz = "@javax.jws.WebService",
        method = "@javax.jws.WebMethod",
        location = @Location(Kind.RETURN)
    )
    public static void onWebserviceReturn(@ProbeClassName String pcn, @ProbeMethodName String pmn, @Duration long d) {
        print("leaving web service ");
        println(Strings.strcat(Strings.strcat(pcn, "."), pmn));
        println(Strings.strcat("Time taken (msec) ", Strings.str(d / 1000)));
        println("==========================");
    }

}
