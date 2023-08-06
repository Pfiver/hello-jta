package net.patrickpfeifer.trxsem.util;

import jakarta.transaction.Status;

import java.lang.reflect.Field;

public class TrxStatusUtil {

    public static String getStatusName(int status) {

        try {
            for (Field f : Status.class.getDeclaredFields()) {
                if (status == (int) f.get(null)) {
                    return f.getName();
                }
            }
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        throw new IllegalStateException("undefined transaction status: " + status);
    }
}
