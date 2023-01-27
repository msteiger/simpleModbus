package io.github.msteiger.simpleModbus;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class StringUtils {
    public static long parseUnsignedInt(String str) {
        return parseUnsignedInt(str, 10);
    }

    public static long parseUnsignedInt(String str, int i) {
        if (str == null) {
            return (long) 0;
        }
        int length = str.length();
        if (length <= 0) {
            return (long) 0;
        }
        if (str.charAt(0) == '-') {
            return (long) 0;
        }
        if (length <= 5 || (i == 10 && length <= 9)) {
            try {
                return (long) Integer.parseInt(str, i);
            } catch (NumberFormatException unused) {
                return (long) 0;
            }
        } else {
            try {
                long parseLong = Long.parseLong(str, i);
                return (-4294967296L & parseLong) == 0 ? parseLong : (long) 0;
            } catch (NumberFormatException unused2) {
                return (long) 0;
            }
        }
    }

    public static String formatStr(String str) {
        return formatStr(str, "--");
    }

    public static String formatStr(String str, String str2) {
        if (str2 == null) {
            str2 = "--";
        }
        return (str == null || str.equals("") || str.equals("null") || str.equals("--")) ? str2 : str;
    }

    public static int getIntValue(String str) {
        return getIntValue(str, 0);
    }

    public static int getIntValue(String str, int i) {
        if (str == null || str.equals("")) {
            return i;
        }
        try {
            return Double.valueOf(str).intValue();
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static double getDoubleValue(String str) {
        return getDoubleValue(str, 0.0d);
    }

    public static double getDoubleValue(String str, double d) {
        if (str == null || str.equals("")) {
            return d;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException unused) {
            return d;
        }
    }

    public static long getLongValue(String str) {
        return getLongValue(str, 0);
    }

    public static long getLongValue(String str, long j) {
        if (str == null || str.equals("")) {
            return j;
        }
        try {
            return Double.valueOf(str).longValue();
        } catch (NumberFormatException unused) {
            return j;
        }
    }

    public static float getFloatValue(String str) {
        return getFloatValue(str, 0.0f);
    }

    public static float getFloatValue(String str, float f) {
        if (str == null || str.equals("")) {
            return f;
        }
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException unused) {
            return f;
        }
    }

    public static boolean isEmptyStr(String str) {
        return str == null || str.equals("") || str.equals("null");
    }

    public static int getNumberDecimalDigits(double d) {
        if (d == Math.rint(d)) {
            return 0;
        }
        String valueOf = String.valueOf(d);
        if (valueOf.contains(".")) {
            return Math.max((valueOf.length() - valueOf.indexOf(".")) - 1, 0);
        }
        return 0;
    }

    public static DecimalFormat getNumberDecimalFormat(String str) {
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getPercentInstance();
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        decimalFormat.applyPattern(str);
        return decimalFormat;
    }

    public static String getNumberDecimalFormat(double d) {
        int numberDecimalDigits = getNumberDecimalDigits(d);
        if (numberDecimalDigits == 0) {
            return "#";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("#.");
        for (int i = 0; i < numberDecimalDigits; i++) {
            sb.append("#");
        }
        return sb.toString();
    }

    public static double divide(double d, double d2) {
        if (d2 == 0.0d) {
            return d;
        }
        return BigDecimal.valueOf(d).divide(BigDecimal.valueOf(d2), 12, 1).doubleValue();
    }

    public static double multiply(double d, double d2) {
        BigDecimal bigDecimal = new BigDecimal(d + "");
        return bigDecimal.multiply(new BigDecimal(d2 + "")).setScale(12, 1).doubleValue();
    }

    public static String getNumberDecimalFormat4Keep(double d) {
        int numberDecimalDigits = getNumberDecimalDigits(d);
        if (numberDecimalDigits == 0) {
            return TabCategory.DEBUG_CATEGORY_CODE;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("0.");
        for (int i = 0; i < numberDecimalDigits; i++) {
            sb.append(TabCategory.DEBUG_CATEGORY_CODE);
        }
        return sb.toString();
    }

}
