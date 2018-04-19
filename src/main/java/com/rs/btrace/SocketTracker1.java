package com.rs.btrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;
import static com.sun.btrace.BTraceUtils.Strings.*;

import java.net.*;
import com.sun.btrace.AnyType;

/**
 * This example tracks all server socket creations and client socket accepts. Unlike SockerTracker.java, this script
 * uses only public API classes and @OnProbe probes - which would be mapped to internal implementation classes by a XML
 * descriptor at BTrace agent. For this sample, XML probe descriptor is "java.net.socket.xml".
 */
@BTrace
public class SocketTracker1 {

    @TLS
    private static int port = -1;
    @TLS
    private static InetAddress inetAddr;
    @TLS
    private static SocketAddress sockAddr;

    @OnMethod(
        clazz = "java.net.ServerSocket",
        method = "<init>"
    )
    public static void onServerSocket(@Self ServerSocket self,
        int p, int backlog, InetAddress bindAddr) {
        port = p;
        inetAddr = bindAddr;
    }

    @OnMethod(
        clazz = "java.net.ServerSocket",
        method = "<init>",
        type = "void (int, int, java.net.InetAddress)",
        location = @Location(Kind.RETURN)
    )
    public static void onSockReturn() {
        if (port != -1) {
            println(Strings.strcat("server socket at ", Strings.str(port)));
            port = -1;
        }
        if (inetAddr != null) {
            println(Strings.strcat("server socket at ", Strings.str(inetAddr)));
            inetAddr = null;
        }
    }

    @OnProbe(
        namespace = "java.net.socket",
        name = "server-socket-creator"
    )
    public static void onSocket(@Return ServerSocket ssock) {
        println(Strings.strcat("server socket at ", Strings.str(ssock)));
    }

    @OnProbe(
        namespace = "java.net.socket",
        name = "bind"
    )
    public static void onBind(@Self Object self, SocketAddress addr, int backlog) {
        sockAddr = addr;
    }

    @OnProbe(
        namespace = "java.net.socket",
        name = "bind-return"
    )
    public static void onBindReturn() {
        if (sockAddr != null) {
            println(Strings.strcat("server socket bind ", Strings.str(sockAddr)));
            sockAddr = null;
        }
    }

    @OnProbe(
        namespace = "java.net.socket",
        name = "accept-return"
    )
    public static void onAcceptReturn(AnyType sock) {
        if (sock != null) {
            println(Strings.strcat("client socket accept ", Strings.str(sock)));
        }
    }
}
