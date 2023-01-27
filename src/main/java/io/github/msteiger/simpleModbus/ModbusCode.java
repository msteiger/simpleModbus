package io.github.msteiger.simpleModbus;

enum Type {
    // filled up with 0xFF to 64 bit so it can be compared as long
    U16(0xFFFFFFFFFFFF0000l),
    S16(0xFFFFFFFFFFFF8000l),
    U32(0xFFFFFFFFFFFFFFFFl),
    S32(0xFFFFFFFF80000000l),
    U64(0xFFFFFFFFFFFFFFFFl),
    S64(0x8000000000000000l),
    STR32(0);

    private long invalid;

    Type(long invalid) {
        this.invalid = invalid;
    }

    public long getInvalid() {
        return invalid;
    }
}


enum Access {
    READ_ONLY,
    READ_WRITE,
    WRITE_ONLY
}

public interface ModbusCode {

    String getName();

    short getRegister();

    short getCount();

    Type getType();

    Format getFormat();

    Access getAccess();

    String getUnit();

    String getDescriptionEnglish();

    String getDescriptionGerman();

}
