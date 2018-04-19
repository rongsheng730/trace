package com.rs.btrace.dtrace;

import static com.sun.btrace.BTraceUtils.*;
import com.sun.btrace.annotations.*;

/*
 * This sample demonstrates associating a D-script
 * with a BTrace program using @DTraceRef annotation.
 * BTrace client looks for absolute or relative path for
 * the D-script and submits it to kernel *before* submitting
 * BTrace program to BTrace agent.
 */
@DTraceRef("classload.d")
@BTrace public class DTraceRefDemo {
   @OnMethod(
     clazz="java.lang.ClassLoader",
     method="defineClass"
   )
   public static void defineClass() {
       println("user defined loader load start");
   }

   @OnMethod(
     clazz="java.lang.ClassLoader", 
     method="defineClass",
     location=@Location(Kind.RETURN)
   )   
   public static void defineclass(Class cl) {
       println(Strings.strcat("loaded ", Reflective.name(cl)));
       Threads.jstack();
       println("==========================");
   }
}
