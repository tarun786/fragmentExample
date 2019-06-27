package com.example.common.utils;

import android.text.TextUtils;
import android.util.Patterns;

public class Validator {

    public Validator() {

    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean verifyName(String name)
    {
        name = name.trim();

        if(name.length() <= 0)
        {
            return false;
        }
        
        return name.matches("[a-zA-Z]*");
    }

    public static boolean isNumeric(String strNum) {
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }
}
