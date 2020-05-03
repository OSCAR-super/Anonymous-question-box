package com.lyt.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public class CSRFTokenUtil {

    public static String generate(HttpServletRequest request) {

        return UUID.randomUUID().toString();
    }

}