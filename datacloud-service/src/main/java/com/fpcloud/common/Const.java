package com.fpcloud.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Const {
    public static final  String  TENANT_ID = "tenantId";
    public static final Map<String,Integer> alphabetNumMap;
    public static final String LOGIN_USER = "loginUser";
    public static final long TRY_USE_DAY = 864000000;
    static {
        Map<String,Integer> aMap = new HashMap<String,Integer>();
        aMap.put("a", 0);aMap.put("b", 1);aMap.put("c", 2);aMap.put("d", 3);aMap.put("e", 4);aMap.put("f", 5);
        aMap.put("g", 6);aMap.put("h", 7);aMap.put("i", 8);aMap.put("j", 9);aMap.put("k", 10);aMap.put("l", 11);
        aMap.put("m", 12);aMap.put("n", 13);aMap.put("o", 14);aMap.put("p", 15);aMap.put("q", 16);aMap.put("r", 17);
        aMap.put("s", 18);aMap.put("t", 19);aMap.put("u", 20);aMap.put("v", 21);aMap.put("w", 22);aMap.put("x", 23);
        aMap.put("y", 24);aMap.put("z", 25);
        alphabetNumMap = Collections.unmodifiableMap(aMap);
    }


}
