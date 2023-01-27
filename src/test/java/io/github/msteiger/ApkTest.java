package io.github.msteiger;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import io.github.msteiger.simpleModbus.HexadecimalUtil;
import io.github.msteiger.simpleModbus.StringUtils;
import io.github.msteiger.simpleModbus.TabCategory;
import io.github.msteiger.simpleModbus.TextUtil;
import io.github.msteiger.simpleModbus.ReplyReadCommand;
import io.github.msteiger.simpleModbus.SendCommand;

public class ApkTest {

    public static void main(String[] args) throws IOException {
        String str = "4159913907";
//        int register = 0x2A0;
        int register = 0x006D;
        String sendCommand = new SendCommand.Builder(str, "03", 0x2A0, 0x2A0).build().toString();

        String read = execute(sendCommand);

        String[] split = TextUtil.split(read, 2);
        if (TextUtil.isEmpty((Object[]) split)) {
            // this.callback.requestFail();
        } else {
//            this.callback.requestSuccess(split);
        }


        if (split.length < 34) {
            System.out.println("ERROR");
            return;
        }
        ReplyReadCommand replyReadCommand = new ReplyReadCommand(split);
        if (!"01".equals(replyReadCommand.getStatusCode())) {
            int hexadecimalToDecimalismUnsigned = HexadecimalUtil.hexadecimalToDecimalismUnsigned(replyReadCommand.getValue());
            System.out.println("ERROR: " + hexadecimalToDecimalismUnsigned);
            return;
        }
        String result = parseResult(str, register, replyReadCommand);

        int valueType = 0; // item.getValueType();
        if (valueType == 0) {
            String resultFmt = parsingNormalValue(str);
            System.out.println(resultFmt);
        }
        if (valueType == 1) {
  //          return parsingOptionValue(str);
        }
        if (valueType != 2) {
//            return null;
        }
//        return parsingTimeValue(str);

    }

    private static String parsingNormalValue(String str) {
        int parserRule = 0;// item.getParserRule();
        int ratio = 1; // item.getRatio

        if (parserRule == 0) {
            return HexadecimalUtil.convertHexToString(str);
        }
        if (parserRule != 1) {
            if (parserRule != 2) {
                if (parserRule != 3) {
                    if (parserRule != 4) {
                        return null;
                    }
                }
            }
            return StringUtils.getNumberDecimalFormat(getPattern(ratio)).format(StringUtils.multiply((double) HexadecimalUtil.hexadecimalToDecimalismSigned(str), ratio));
        }
        return StringUtils.getNumberDecimalFormat(getPattern(ratio)).format(StringUtils.multiply((double) HexadecimalUtil.hexadecimalToDecimalismUnsigned(str), ratio));
    }

    private static String parseResult(String str, int registerStart, ReplyReadCommand replyReadCommand) {
            String str2 = null;
            int startAddress = registerStart;
            if (startAddress == 4630 || startAddress == 4631) {
//                String versionValueHex0 = replyReadCommand.getValue();
//                item.setRegister(new Register(4194, 4194));
//                getValue(str, item);
                return null;
            }
            if (startAddress == 4194) {
//                this.versionValueHex1 = replyReadCommand.getValue();
//                str2 = getVersionInfoViewValue();
            } else if (startAddress == 4629) {
//                str2 = getMCUVersion(replyReadCommand.getValue());
            } else {
                return replyReadCommand.getValue();
            }
            return str2;
    }
    private static String getPattern(double d) {
        return d == Math.rint(d) ? TabCategory.DEBUG_CATEGORY_CODE : StringUtils.getNumberDecimalFormat4Keep(d);
    }

    private static String execute(String str) throws IOException {
        String IP = "192.168.178.122";
        int PORT = 8899;
        int TIME_OUT = 5000;

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(IP, PORT), TIME_OUT);

        byte[] hexadecimalToBytes = HexadecimalUtil.hexadecimalToBytes(str);
        if (hexadecimalToBytes != null) {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(hexadecimalToBytes);
            outputStream.flush();
        }

        InputStream inputStream = socket.getInputStream();
        byte[] bArr = new byte[1];
        StringBuilder sb = new StringBuilder();
        while (inputStream.read(bArr) != -1) {
            sb.append(HexadecimalUtil.bytesToHexadecimal(bArr));
            if (inputStream.available() <= 0) {
                break;
            }
        }
        return new String(sb).trim();
    }

}
