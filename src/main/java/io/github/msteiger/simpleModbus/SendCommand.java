package io.github.msteiger.simpleModbus;

public final class SendCommand extends Command {
    private static final String DELIVERY_TIME = "00000000";
    private static final String FRAME_TYPE = "02";
    private static final String OFFSET_TIME = "00000000";
    private static final String POWER_ON_TIME = "00000000";
    private static final String SENSOR_TYPE = "0000";
    private static final String SLAVE_ADDRESS = "01";
    private String addressSize;
    private String crc;
    private String functionCode;
    private String startAddress;
    private String value;
    private String valueLength;

    private SendCommand(Builder builder) {
        setStart("a5");
        setControlCode("1045");
        setSerial("0000");
        setLoggerSN(builder.loggerSN);
        setFunctionCode(builder.functionCode);
        setStartAddress(builder.startAddress);
        setAddressSize(builder.startAddress, builder.endAddress);
        setValue(builder.value);
        setValueLength();
        setCrc();
        setDataLength((String) null);
        setChecksum((String) null);
        setEnd("15");
    }

    public void setLoggerSN(String str) {
        super.setLoggerSN(ModbusUtil.sortFromLowToHigh(ModbusUtil.polishing(HexadecimalUtil.decToHex_U32(StringUtils.parseUnsignedInt(str)), 4), 2));
    }

    private void setFunctionCode(String str) {
        this.functionCode = str;
    }

    private void setStartAddress(int i) {
        this.startAddress = ModbusUtil.polishing(HexadecimalUtil.decimalismToHexadecimalUnsigned(i), 2);
    }

    private void setAddressSize(int i, int i2) {
        this.addressSize = ModbusUtil.polishing(HexadecimalUtil.decimalismToHexadecimalUnsigned((i2 - i) + 1), 2);
    }

    private void setValueLength() {
        if (!TextUtil.isEmpty(this.value)) {
            this.valueLength = ModbusUtil.polishing(HexadecimalUtil.decimalismToHexadecimalUnsigned(this.value.length() / 2), 1);
        } else {
            this.valueLength = "";
        }
    }

    private void setValue(String str) {
        if (!TextUtil.isEmpty(str)) {
            this.value = str;
        } else {
            this.value = "";
        }
    }

    private void setCrc() {
        String polishing = ModbusUtil.polishing(ModbusUtil.getCRC(HexadecimalUtil.hexadecimalToBytes(getBusiness())), 2);
        this.crc = polishing;
        this.crc = ModbusUtil.sortFromLowToHigh(polishing, 2);
    }

    public void setDataLength(String str) {
        super.setDataLength(ModbusUtil.sortFromLowToHigh(ModbusUtil.polishing(HexadecimalUtil.decimalismToHexadecimalUnsigned(getDataFrame().length() / 2), 2), 2));
    }

    public void setChecksum(String str) {
        super.setChecksum(ModbusUtil.polishing(ModbusUtil.getChecksum(getDataLength() + getControlCode() + getSerial() + getLoggerSN() + getDataFrame()), 1));
    }

    private String getBusiness() {
        return "01" + this.functionCode + this.startAddress + this.addressSize + this.valueLength + this.value;
    }

    public String getModbusFrame() {
        return getBusiness() + this.crc;
    }

    public String getDataFrame() {
        return "020000000000000000000000000000" + getModbusFrame();
    }

    public boolean isRead() {
        return TextUtil.isEmpty(this.value);
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public int endAddress;
        /* access modifiers changed from: private */
        public String functionCode;
        /* access modifiers changed from: private */
        public String loggerSN;
        /* access modifiers changed from: private */
        public int startAddress;
        /* access modifiers changed from: private */
        public String value;

        public Builder(String str, String str2, int i, int i2) {
            this.loggerSN = str;
            this.functionCode = str2;
            this.startAddress = i;
            this.endAddress = i2;
        }

        public Builder value(String str) {
            this.value = str;
            return this;
        }

        public SendCommand build() {
            return new SendCommand(this);
        }
    }
}
