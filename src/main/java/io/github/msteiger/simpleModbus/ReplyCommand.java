package io.github.msteiger.simpleModbus;

public class ReplyCommand extends Command {
    private String deliveryTime;
    private String informationFrameType;
    private String offsetTime;
    private String powerOnTime;
    private String statusCode;

    ReplyCommand(String[] strArr) {
        setStart(strArr[0]);
        setDataLength(TextUtil.getContentFromBytes(strArr, 1, 2));
        setControlCode(TextUtil.getContentFromBytes(strArr, 3, 4));
        setSerial(TextUtil.getContentFromBytes(strArr, 5, 6));
        setLoggerSN(TextUtil.getContentFromBytes(strArr, 7, 10));
        setInformationFrameType(strArr);
        setStatusCode(strArr);
        setDeliveryTime(strArr);
        setPowerOnTime(strArr);
        setOffsetTime(strArr);
        setChecksum(strArr[strArr.length - 2]);
        setEnd(strArr[strArr.length - 1]);
    }

    private void setInformationFrameType(String[] strArr) {
        this.informationFrameType = strArr[11];
    }

    public final String getStatusCode() {
        return this.statusCode;
    }

    private void setStatusCode(String[] strArr) {
        this.statusCode = strArr[12];
    }

    private void setDeliveryTime(String[] strArr) {
        this.deliveryTime = TextUtil.getContentFromBytes(strArr, 13, 16);
    }

    private void setPowerOnTime(String[] strArr) {
        this.powerOnTime = TextUtil.getContentFromBytes(strArr, 17, 20);
    }

    private void setOffsetTime(String[] strArr) {
        this.offsetTime = TextUtil.getContentFromBytes(strArr, 21, 24);
    }

    public String getDataFrame() {
        return this.informationFrameType + this.statusCode + this.deliveryTime + this.powerOnTime + this.offsetTime;
    }
}
