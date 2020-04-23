package cn.com.dhc.epq.common;

import java.util.HashMap;

public class Constants {
    //请求成功
    public static final int SUCCESS = 0;
    //请求失败
    public static final int FAILURE = 1000;


    public static HashMap<Integer, String> VALUES = new HashMap<>();
   

    static {
        VALUES.put(SUCCESS, "成功");
        VALUES.put(FAILURE, "失败");
    }
}
