package com.rs.btrace;

import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;
import java.awt.EventQueue;
import java.awt.AWTEvent;
import java.awt.event.FocusEvent;

/**
 * This simple script traces every AWT focus event in
 * the target process.
 */
@BTrace 
public class AWTEventTracer {
  @OnMethod(
     clazz="java.awt.EventQueue",
     method="dispatchEvent"
  )
  public static void onevent(@Self EventQueue queue, AWTEvent event) {
     if (event instanceof FocusEvent) {
         println(event); 
         println();
     }
  }
}

