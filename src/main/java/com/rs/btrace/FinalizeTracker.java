package com.rs.btrace;

import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;
import java.lang.reflect.Field;

@BTrace public class FinalizeTracker {
  private static Field fdField =
    field("java.io.FileInputStream", "fd");

  @OnTimer(4000) 
  public static void ontimer() {
    runFinalization();     
  }

  @OnMethod(
    clazz="java.io.FileInputStream",
    method="finalize"
  ) 
  public static void onfinalize(@Self Object me) {
    println(concat("finalizing ", str(me)));
    printFields(me);
    printFields(get(fdField, me));
    println("==========");
  }

  @OnMethod(
    clazz="java.io.FileInputStream",
    method="close"
  ) 
  public static void onclose(@Self Object me) {
    println(concat("closing ", str(me)));
    println(concat("thread: ", str(currentThread())));
    printFields(me);
    printFields(get(fdField, me));
    jstack();
    println("=============");
  }
}
