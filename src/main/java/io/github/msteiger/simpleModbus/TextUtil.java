package io.github.msteiger.simpleModbus;

import java.util.List;
import java.util.regex.Pattern;

public final class TextUtil {
    public static boolean isEmpty(Object[] objArr) {
        return objArr == null || objArr.length == 0;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }

    public static String specialCharacterRemoval(String str, String str2) {
        if (isEmpty(str) || isEmpty(str2)) {
            return null;
        }
        return Pattern.compile(str2).matcher(str).replaceAll("").trim();
    }

    public static String[] split(String str, int i) {
        if (isEmpty(str) || str.length() % 2 != 0 || i == 0 || str.length() % i > 0) {
            return null;
        }
        int length = (str.length() / i) - 1;
        StringBuilder sb = new StringBuilder(str);
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 + 1;
            sb.insert((i3 * i) + i2, ",");
            i2 = i3;
        }
        return sb.toString().split(",");
    }

    public static String getContentFromBytes(String[] strArr, int i, int i2) {
        if (i == i2) {
            return strArr[i];
        }
        StringBuilder sb = new StringBuilder();
        while (i < i2 + 1) {
            sb.append(strArr[i]);
            i++;
        }
        return sb.toString();
    }

    public static boolean isNumber(String str) {
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }

    public static boolean verifyIPaddress(String str) {
        return Pattern.compile("^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$").matcher(str).matches();
    }
}
