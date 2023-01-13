package com.ntt.godzilla.util;

import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;

public class StringUtils {

    public static String getNameByCamelCase(String camelCase) {
        return SplitCamelCaseUtils.getNormalNameByCammelCase(camelCase);
    }
}

class SplitCamelCaseUtils {
    static String PATTERN = "(?<!(^|[A-Z0-9]))(?=[A-Z0-9])|(?<!(^|[^A-Z]))(?=[0-9])|(?<!(^|[^0-9]))(?=[A-Za-z])|(?<!^)(?=[A-Z][a-z])";
    static Pattern SPLIT_CAMEL_CASE = Pattern.compile(PATTERN);

    public static String getNameByCamelCase(String s) {
        return SPLIT_CAMEL_CASE.splitAsStream(s)
                .collect(joining(" ")).replace('.', ' ');
    }

    public static String getNormalNameByCammelCase(String name) {
        return org.apache.commons.lang3.StringUtils.capitalize(getNameByCamelCase(name).toLowerCase());
    }
}
