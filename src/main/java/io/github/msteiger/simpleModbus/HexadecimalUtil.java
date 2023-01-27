package io.github.msteiger.simpleModbus;

import java.awt.datatransfer.StringSelection;
import java.nio.charset.StandardCharsets;

public class HexadecimalUtil {
    public static String bytesToHexadecimal(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static byte[] hexadecimalToBytes(String str) {
        byte[] bArr;
        if (TextUtil.isEmpty(str)) {
            return null;
        }
        int length = str.length();
        if (length % 2 == 1) {
            length++;
            bArr = new byte[(length / 2)];
            str = TabCategory.DEBUG_CATEGORY_CODE + str;
        } else {
            bArr = new byte[(length / 2)];
        }
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i + 2;
            bArr[i2] = HexadecimalToByte(str.substring(i, i3));
            i2++;
            i = i3;
        }
        return bArr;
    }

    private static byte HexadecimalToByte(String str) {
        if (TextUtil.isEmpty(str)) {
            return 0;
        }
        return (byte) Integer.parseInt(str, 16);
    }

    public static short hexadecimalToDecimalismSigned(String str) {
        if (TextUtil.isEmpty(str)) {
            return 0;
        }
        return Integer.valueOf(str, 16).shortValue();
    }

    public static int hexadecimalToDecimalismUnsigned(String str) {
        if (TextUtil.isEmpty(str)) {
            return 0;
        }
        return Integer.valueOf(str, 16).intValue();
    }

    public static String decimalismToHexadecimalSigned(short s) {
        return bytesToHexadecimal(new byte[]{(byte) (s >> 8), (byte) (s & 255)});
    }

    public static String decimalismToHexadecimalUnsigned(int i) {
        return Integer.toHexString(i);
    }

    public static String decToHex_U32(long j) {
        return highFillZero(Long.toHexString(j), 8).toUpperCase();
    }

    public static byte[] hexadecimalToBinary(String str) {
        if (TextUtil.isEmpty(str)) {
            return null;
        }
        String upperCase = str.toUpperCase();
        int length = upperCase.length() / 2;
        char[] charArray = upperCase.toCharArray();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (charToByte(charArray[i2 + 1]) | (charToByte(charArray[i2]) << 4));
        }
        return bArr;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static String convertHexToString(String str) {
        return new String(hexadecimalToBytes(str), StandardCharsets.UTF_8);
    }

    public static String convertStringToHex(String str) {
        char[] charArray = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char hexString : charArray) {
            sb.append(Integer.toHexString(hexString));
        }
        return sb.toString();
    }

    private static String highFillZero(String str, int i) {
        int length = str.length();
        if (length >= i) {
            return str;
        }
        int i2 = i - length;
        StringBuilder sb = new StringBuilder(str);
        for (int i3 = 0; i3 < i2; i3++) {
            sb.insert(0, TabCategory.DEBUG_CATEGORY_CODE);
        }
        return sb.toString().toUpperCase();
    }

    public static String reverseHex(String str) {
        String[] splitString = splitString(str);
        if (splitString == null || splitString.length == 0) {
            return null;
        }
        int i = 0;
        for (int length = splitString.length - 1; i < length; length--) {
            String str2 = splitString[length];
            splitString[length] = splitString[i];
            splitString[i] = str2;
            i++;
        }
        StringBuilder sb = new StringBuilder();
        for (String append : splitString) {
            sb.append(append);
        }
        return sb.toString();
    }

    public static String[] splitString(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        if (str.length() % 2 != 0) {
            str = TabCategory.DEBUG_CATEGORY_CODE + str;
        }
        int length = str.length() / 2;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            strArr[i] = str.substring(i2, i2 + 2);
        }
        return strArr;
    }
}
