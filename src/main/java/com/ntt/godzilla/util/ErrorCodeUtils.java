package com.ntt.godzilla.util;


import com.ntt.godzilla.annotation.ErrorMessage;
import com.ntt.godzilla.constant.ErrorCode;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ErrorCodeUtils {
    private static Map<String, String> ERROR_CODE_MESSAGE_MAP = new HashMap<>();
    private static Map<String, String> ERROR_CODE_NAME_MAP = new HashMap<>();

    static {
        registerErrorCode(ErrorCode.class);
    }

    public static void registerErrorCode(Class clazz) {
        Field[] fields = clazz.getFields();
        int numberOfField = fields.length;

        for (int i = 0; i < numberOfField; ++i) {
            Field f = fields[i];
            if (f.getType() == String.class) {
                String errorMessage = null;
                if (f.isAnnotationPresent(ErrorMessage.class)) {
                    errorMessage = f.getAnnotation(ErrorMessage.class).value();
                }

                try {
                    ERROR_CODE_MESSAGE_MAP.put(f.get(String.class).toString(), errorMessage);
                    ERROR_CODE_NAME_MAP.put(f.getName(), f.get(String.class).toString());
                }
                catch (Exception ex) {
                }
            }
        }
    }

    public static String getErrorMessage(String code) {
        return ERROR_CODE_MESSAGE_MAP.get(code);
    }

    public static String getErrorCodeByName(String name) {
        return ERROR_CODE_NAME_MAP.get(name);
    }

    public static String getErrorCodeByMessage(String message) {
        Iterator iterator = ERROR_CODE_MESSAGE_MAP.keySet().iterator();

        String code;
        do {
            if (!iterator.hasNext()) {
                return null;
            }
            code = (String) iterator.next();
        }
        while (!ERROR_CODE_MESSAGE_MAP.get(code).equalsIgnoreCase(message));

        return code;
    }
}
