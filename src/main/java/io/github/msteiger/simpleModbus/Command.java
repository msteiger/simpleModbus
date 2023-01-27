package io.github.msteiger.simpleModbus;

public class Command {
    private String checksum;
    private String controlCode;
    private String dataLength;
    private String end;
    private String loggerSN;
    private String serial;
    private String start;

    public String getDataFrame() {
        return "";
    }

    public String getModbusFrame() {
        return "";
    }

    public void setStart(String str) {
        this.start = str;
    }

    public String getDataLength() {
        return this.dataLength;
    }

    public void setDataLength(String str) {
        this.dataLength = str;
    }

    public String getControlCode() {
        return this.controlCode;
    }

    public void setControlCode(String str) {
        this.controlCode = str;
    }

    public String getSerial() {
        return this.serial;
    }

    public void setSerial(String str) {
        this.serial = str;
    }

    public String getLoggerSN() {
        return this.loggerSN;
    }

    public void setLoggerSN(String str) {
        this.loggerSN = str;
    }

    public void setChecksum(String str) {
        this.checksum = str;
    }

    public void setEnd(String str) {
        this.end = str;
    }

    public String toString() {
        return this.start + this.dataLength + this.controlCode + this.serial + this.loggerSN + getDataFrame() + this.checksum + this.end;
    }
}
