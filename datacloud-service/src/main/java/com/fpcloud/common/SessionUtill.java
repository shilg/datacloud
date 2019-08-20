package com.fpcloud.common;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;

public class SessionUtill {
    public static String getSessionStringAttr(HttpServletRequest request, String attr){
        Object obj = request.getSession().getAttribute(attr);
        if(obj!=null){
            return obj.toString();
        }
        return null;
    }
}
