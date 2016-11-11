package com.ways2u.redis;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by huanglong on 16/9/8.
 */
public class ExceptionUtil {
    public static String getTrace(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        throwable.printStackTrace(writer);
        StringBuffer buffer = stringWriter.getBuffer();
        return buffer.toString();
    }
}
