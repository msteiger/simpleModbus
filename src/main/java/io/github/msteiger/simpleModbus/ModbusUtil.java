package io.github.msteiger.simpleModbus;

import java.util.zip.CRC32;

public class ModbusUtil {
    public static String polishing(String str, int i) {
        if (TextUtil.isEmpty(str) || i == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < i * 2) {
            sb.insert(0, TabCategory.DEBUG_CATEGORY_CODE);
        }
        return sb.toString();
    }

    public static String sortFromLowToHigh(String str, int i) {
        if (TextUtil.isEmpty(str)) {
            return null;
        }
        String[] split = TextUtil.split(str, i);
        if (TextUtil.isEmpty((Object[]) split)) {
            return null;
        }
        int i2 = 0;
        for (int length = split.length - 1; i2 < length; length--) {
            String str2 = split[length];
            split[length] = split[i2];
            split[i2] = str2;
            i2++;
        }
        StringBuilder sb = new StringBuilder();
        for (String append : split) {
            sb.append(append);
        }
        return sb.toString();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getCRC(byte[] r6) {

//        CRC32 crc = new CRC32();
//        crc.update(r6);
//        int r0 = 0x1045; // (int) crc.getValue();


        // https://github.com/StephanJoubert/home_assistant_solarman/blob/40a782549ee82c70c71ee73f2d17f73b7a1d7be5/custom_components/solarman/solarman.py#L41

        int POLY = 0xA001;

        int crc = 0xFFFF;
        for (byte b : r6) {
            crc ^= b;
            for (int i=0; i<8; i++) {
                crc = (crc >> 1) ^ POLY;
                if ((crc & 0x0001) != 0) {}
                else crc = crc >> 1;
            }
        }

        return Integer.toHexString((char)crc);

        /*
            if (r6 == 0) goto L_0x0030
            int r0 = r6.length
            if (r0 != 0) goto L_0x0006
            goto L_0x0030
        L_0x0006:
            r0 = 65535(0xffff, float:9.1834E-41)
            r1 = 40961(0xa001, float:5.7399E-41)
            r2 = 0
            r3 = 0
        L_0x000e:
            int r4 = r6.length
            if (r3 >= r4) goto L_0x002b
            byte r4 = r6[r3]
            r4 = r4 & 255(0xff, float:3.57E-43)
            r0 = r0 ^ r4
            r4 = 0
        L_0x0017:
            r5 = 8
            if (r4 >= r5) goto L_0x0028
            r5 = r0 & 1
            if (r5 == 0) goto L_0x0023
            int r0 = r0 >> 1
            r0 = r0 ^ r1
            goto L_0x0025
        L_0x0023:
            int r0 = r0 >> 1
        L_0x0025:
            int r4 = r4 + 1
            goto L_0x0017
        L_0x0028:
            int r3 = r3 + 1
            goto L_0x000e
        L_0x002b:
            java.lang.String r6 = java.lang.Integer.toHexString(r0)
            return r6
        L_0x0030:
            java.lang.String r6 = ""
            return r6
        */
//        throw new UnsupportedOperationException("Method not decompiled: com.igen.localmode.invt.util.ModbusUtil.getCRC(byte[]):java.lang.String");
    }

    public static String getChecksum(String str) {
        if (TextUtil.isEmpty(str)) {
            return "";
        }
        int length = str.length();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i + 2;
            String substring = str.substring(i, i3);
            System.out.println(substring);
            i2 += Integer.parseInt(substring, 16);
            i = i3;
        }
        String hexString = Integer.toHexString(i2 % 256);
        if (hexString.length() >= 2) {
            return hexString;
        }
        return TabCategory.DEBUG_CATEGORY_CODE + hexString;
    }
}
