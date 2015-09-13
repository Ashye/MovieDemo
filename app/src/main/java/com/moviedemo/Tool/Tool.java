package com.moviedemo.Tool;

/**
 * Created by ych on 9/13/15.
 */
public class Tool {

    public static boolean notEmptyString(String str) {
        if (str != null && str.length() >0) {
            return true;
        }else {
            return false;
        }
    }

    public static boolean isEmptyString(String str) {
        return !notEmptyString(str);
    }
}
