package io.github.msteiger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;

import io.github.msteiger.simpleModbus.DeyeCode;
import io.github.msteiger.simpleModbus.Format;
import io.github.msteiger.simpleModbus.FunctionCode;
import io.github.msteiger.simpleModbus.Modbus;
import io.github.msteiger.simpleModbus.SmaCode;

public class DeyeTest {

    public static void main(String[] args) throws IOException, InterruptedException {

        String ipAddress = "192.168.178.122";
        int port = 8899;



        try (Modbus mb = new Modbus(ipAddress, port)) {
            mb.setUnitIdentifier((int)2209185179L);

            for (DeyeCode code : DeyeCode.values()) {

//                code = SmaCode.OPERATION_OPSTT;

                String text;
                Format fmt = code.getFormat();

                if (fmt == Format.UTF8) {
                    text = mb.readString(FunctionCode.READ_HOLDING_REGISTER, code);
                }
                else if (fmt == Format.REVISION || fmt == Format.FIRMWARE || fmt == Format.IP4) {
                    text = mb.readVersion(FunctionCode.READ_HOLDING_REGISTER, code);
                }
                else if (fmt == Format.FUNCTION_SEC) {
                    text = Arrays.toString(mb.readRaw(FunctionCode.READ_HOLDING_REGISTER, code));
                }
                else if (fmt == Format.ENUM) {
                    text = "LOOKUP[" + mb.readLong(FunctionCode.READ_HOLDING_REGISTER, code) + "]";
                }
                else if (fmt == Format.DATETIME) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withZone(ZoneId.systemDefault());
                    text = formatter.format(mb.readDatetime(FunctionCode.READ_HOLDING_REGISTER, code));
                }
                else if (fmt == Format.RAW) {
                    text = "" + mb.readLong(FunctionCode.READ_HOLDING_REGISTER, code);
                }
                else {
                    double value = mb.readNumber(FunctionCode.READ_HOLDING_REGISTER, code);
                    text = Double.isNaN(value) ? "-" : value + " " + code.getUnit();
                }

                System.out.println(code.getDescriptionGerman() + ": " + text);
                break;
//                Thread.sleep(100);
            }

        }
    }

}
