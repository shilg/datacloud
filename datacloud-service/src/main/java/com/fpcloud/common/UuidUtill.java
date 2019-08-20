package com.fpcloud.common;

import java.util.UUID;

public class UuidUtill {
    public static String getIdField(){
        UUID uuid= UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr=str.replace("-", "");
        return uuidStr;
    }
}
