package com.youjian.uitl;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shen youjian
 * @date 12/20/2018 8:58 PM
 */
public class RequestUtils {
    public static boolean isJson(HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        return StringUtils.startsWith( accept,"application/json");
    }
}
