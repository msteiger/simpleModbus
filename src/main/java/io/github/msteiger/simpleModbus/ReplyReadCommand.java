package io.github.msteiger.simpleModbus;


public final class ReplyReadCommand extends ReplyCommand {
    private String crc;
    private String functionCode;
    private String slaveAddress;
    private String value;
    private String valueLength;

    public ReplyReadCommand(String[] strArr) {
        super(strArr);
        setSlaveAddress(strArr);
        setFunctionCode(strArr);
        setValueLength(strArr);
        setValue(strArr);
        setCrc(strArr);
    }

    private void setSlaveAddress(String[] strArr) {
        this.slaveAddress = strArr[25];
    }

    private void setFunctionCode(String[] strArr) {
        this.functionCode = strArr[26];
    }

    private void setValueLength(String[] strArr) {
        this.valueLength = strArr[27];
    }

    public String getValue() {
        return this.value;
    }

    private void setValue(String[] strArr) {
        this.value = TextUtil.getContentFromBytes(strArr, 28, (HexadecimalUtil.hexadecimalToDecimalismUnsigned(this.valueLength) + 28) - 1);
    }

    private void setCrc(String[] strArr) {
        this.crc = TextUtil.getContentFromBytes(strArr, strArr.length - 4, strArr.length - 3);
    }

    public String getModbusFrame() {
        return this.slaveAddress + this.functionCode + this.valueLength + this.value + this.crc;
    }

    public String getDataFrame() {
        return super.getDataFrame() + getModbusFrame();
    }
}
