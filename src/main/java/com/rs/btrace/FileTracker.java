package com.rs.btrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * This sample prints all files opened for read/write by a Java process. Note that if you pass FileDescriptor to
 * File{Input/Output}Stream or File{Reader/Writer}, that is not tracked by this script.
 */
@BTrace
public class FileTracker {

    @TLS
    private static String name;

    @OnMethod(
        clazz = "java.io.FileInputStream",
        method = "<init>"
    )
    public static void onNewFileInputStream(@Self FileInputStream self, File f) {
        name = Strings.str(f);
    }

    @OnMethod(
        clazz = "java.io.FileInputStream",
        method = "<init>",
        type = "void (java.io.File)",
        location = @Location(Kind.RETURN)
    )
    public static void onNewFileInputStreamReturn() {
        if (name != null) {
            println(Strings.strcat("opened for read ", name));
            name = null;
        }
    }

    @OnMethod(
        clazz = "java.io.FileOutputStream",
        method = "<init>"
    )
    public static void onNewFileOutputStream(@Self FileOutputStream self, File f, boolean b) {
        name = str(f);
    }

    @OnMethod(
        clazz = "java.io.FileOutputStream",
        method = "<init>",
        type = "void (java.io.File, boolean)",
        location = @Location(Kind.RETURN)
    )
    public static void OnNewFileOutputStreamReturn() {
        if (name != null) {
            println(Strings.strcat("opened for write ", name));
            name = null;
        }
    }
}
