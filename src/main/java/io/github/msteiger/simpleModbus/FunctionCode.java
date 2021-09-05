package io.github.msteiger.simpleModbus;

public enum FunctionCode {

    READ_COILS(0x01),
    WRITE_SINGLE_COIL(0x05),
    WRITE_MULTIPLE_COILS(0x0F),

    READ_INPUT_REGISTER(0x04),
    READ_HOLDING_REGISTER(0x03),
    WRITE_SINGLE_REGISTER(0x06),
    WRITE_MULTIPLE_REGISTERS(0x10),
    READ_WRITE_MULTIPLE_REGISTERS(0x17),
    MASK_WRITE_REGISTER(0x16),
    READ_FIFO_QUEUE(0x18),

    READ_FILE_ACCESS(0x14),
    WRITE_FILE_ACCESS(0x15),

    READ_EXCEPTION_STATUS(0x07),
    READ_DIAGNOSTIC(0x08),
    GET_COM_EVENT_COUNTER(0x0B),
    GET_COM_EVENT_LOG(0x0C),
    REPORT_SERVER_ID(0x11),
    READ_DEVICE_IDENTIFICATION(0x2B),

    ENCAPSULATED_INTERFACE_TRANSPORT(0x2B),
    CANOPEN_GENERAL_REFERENCE(0x2B);


    private final byte code;

    FunctionCode(int code) {
        this.code = (byte) code;
    }

    public byte getCode() {
        return code;
    }
}
