package io.github.msteiger.simpleModbus;

import static io.github.msteiger.simpleModbus.Access.*;
import static io.github.msteiger.simpleModbus.Format.*;
import static io.github.msteiger.simpleModbus.Type.*;


@SuppressWarnings({ "unused", "javadoc"})
public enum DeyeCode implements ModbusCode {

    PV1_VOLTAGE("PV1.Voltage", 0x006D, 1, S16, FIX1, READ_ONLY, "V", "PV1 Voltage", "PV1 Spannung"),
    PV2_VOLTAGE("PV2.Voltage", 0x006F, 1, S16, FIX1, READ_ONLY, "V", "PV2 Voltage", "PV2 Spannung"),
    PV3_VOLTAGE("PV3.Voltage", 0x0071, 1, S16, FIX1, READ_ONLY, "V", "PV3 Voltage", "PV3 Spannung"),
    PV4_VOLTAGE("PV4.Voltage", 0x0073, 1, S16, FIX1, READ_ONLY, "V", "PV4 Voltage", "PV4 Spannung"),

    PV1_CURRENT("PV1.Current", 0x006E, 1, S16, FIX1, READ_ONLY, "A", "PV1 Current", "PV1 Strom"),
    PV2_CURRENT("PV2.Current", 0x0070, 1, S16, FIX1, READ_ONLY, "A", "PV2 Current", "PV2 Strom"),
    PV3_CURRENT("PV3.Current", 0x0072, 1, S16, FIX1, READ_ONLY, "A", "PV3 Current", "PV3 Strom"),
    PV4_CURRENT("PV4.Current", 0x0074, 1, S16, FIX1, READ_ONLY, "A", "PV4 Current", "PV4 Strom"),

    DAILY_PROD("Daily.Production", 0x003C, 1, S16, FIX1, READ_ONLY, "kWh", "Daily Production", "Tägliche Produktion"),
    TOTAL_PROD("Total.Production", 0x003F, 2, S32, FIX1, READ_ONLY, "kWh", "Total Production", "Gesamte Produktion"),

    AC_VOLTAGE("AC.Voltage", 0x0049, 1, S16, FIX0, READ_ONLY, "V", "AC Voltage", "Wechselspannung"),
    AC_FREQ("AC.Output.Frequency", 0x004F, 1, S16, FIX0, READ_ONLY, "V", "AC Frequency", "AC Frequenz");

    private String nameId;
    private short register;
    private short count;
    private Type type;
    private Format format;
    private Access access;
    private String unit;
    private String descEn;
    private String descDe;

    DeyeCode(String name, int register, int count, Type type, Format format, Access access, String unit, String descEn, String descDe) {
        this.nameId = name;
        this.register = (short)register;
        this.count = (short)count;
        this.type = type;
        this.format = format;
        this.access = access;
        this.unit = unit;
        this.descEn = descEn;
        this.descDe = descDe;
    }

    @Override
    public String getName() {
        return nameId;
    }

    @Override
    public short getRegister() {
        return register;
    }

    @Override
    public short getCount() {
        return count;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public Format getFormat() {
        return format;
    }

    @Override
    public Access getAccess() {
        return access;
    }

    @Override
    public String getUnit() {
        return unit;
    }

    @Override
    public String getDescriptionEnglish() {
        return descEn;
    }

    @Override
    public String getDescriptionGerman() {
        return descDe;
    }
}
