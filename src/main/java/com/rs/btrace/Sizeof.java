package com.rs.btrace;

import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;

@BTrace public class Sizeof {
    @OnMethod(
        clazz="javax.swing.JComponent",
        method="<init>"
    ) 
    public static void onnew(@Self Object obj) {
        println(Strings.concat("object of: ", Reflective.name(Reflective.classOf(obj))));
        println(Strings.concat("size: ", Strings.str(sizeof(obj))));
    }
}
