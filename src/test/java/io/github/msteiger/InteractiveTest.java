package io.github.msteiger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;

import io.github.msteiger.simpleModbus.Format;
import io.github.msteiger.simpleModbus.FunctionCode;
import io.github.msteiger.simpleModbus.Modbus;
import io.github.msteiger.simpleModbus.SmaCode;

public class InteractiveTest {

    public static void main(String[] args) throws IOException, InterruptedException {

        // http://sma1930209829/
        String ipAddress = "192.168.178.95";
        int port = 502;



        try (Modbus mb = new Modbus("192.168.178.95")) {
            mb.setUnitIdentifier(3);

            for (SmaCode code : SmaCode.values()) {
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
                else {
                    double value = mb.readNumber(FunctionCode.READ_HOLDING_REGISTER, code);
                    text = Double.isNaN(value) ? "-" : value + " " + code.getUnit();
                }

                System.out.println(code.getDescriptionGerman() + ": " + text);
                Thread.sleep(100);
            }

        }
    }

}
