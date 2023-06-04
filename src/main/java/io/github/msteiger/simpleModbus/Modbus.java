package io.github.msteiger.simpleModbus;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * https://modbus.org/specs.php
 */
public class Modbus implements AutoCloseable {


    private static final Logger log = LoggerFactory.getLogger(Modbus.class);

    public static final int DEFAULT_TIMEOUT = 5000;
    public static final int DEFAULT_PORT = 502;  // both TCP and UDP
    public static final byte DEFAULT_UNIT_IDENTIFIER = -1;

    public static final short PROTOCOL_ID = 0; // always 0 (ModBus protocol ID)

    /**
     * UnitID (1), FunctionCode(1), Register(2), Code(2) add up to 6 bytes
     */
    private static final short REQUEST_LENGTH = 6;

    private final Socket tcpClientSocket = new Socket();
    private final InputStream inputStream;
    private final OutputStream outputStream;

    private byte unitIdentifier = DEFAULT_UNIT_IDENTIFIER;

    private short transactionId = 4433; // a random identifier

    /**
     * Port is {@link #DEFAULT_PORT}<br>
     * Timeout is {@link #DEFAULT_TIMEOUT}
     */
    public Modbus(String ipAddressOrHostname) throws IOException {
        this(ipAddressOrHostname, DEFAULT_PORT);
    }

    /**
     * Default timeout is {@link #DEFAULT_TIMEOUT}
     */
    public Modbus(String ipAddressOrHostname, int port) throws IOException {
        this(ipAddressOrHostname, port, DEFAULT_TIMEOUT);
    }

    public Modbus(String ipAddressOrHostname, int port, int timeout) throws IOException {
        InetSocketAddress endpoint = new InetSocketAddress(ipAddressOrHostname, port);
        tcpClientSocket.setSoTimeout(timeout);
        tcpClientSocket.connect(endpoint, timeout);
        inputStream = tcpClientSocket.getInputStream();
        outputStream = tcpClientSocket.getOutputStream();
        log.debug("Connection to '{}' established ..", ipAddressOrHostname);
    }

    public Modbus(String ipAddressOrHostname, int port, int timeout, int unitIdentifier) throws IOException {
        this(ipAddressOrHostname, port, timeout);
        setUnitIdentifier(unitIdentifier);
    }

    public byte getUnitIdentifier() {
        return unitIdentifier;
    }

    public void setUnitIdentifier(int unitIdentifier) {
        checkByte(unitIdentifier);
        this.unitIdentifier = (byte)unitIdentifier;
    }

    @Override
    public void close() throws IOException {
        if (inputStream != null) {
            inputStream.close();
        }
        if (outputStream != null) {
            outputStream.close();
        }
        tcpClientSocket.close();
        log.debug("Connection closed ..");
    }

    /**
     *
     * @param function
     * @param code
     * @return the value or {@link Double#NaN} if missing
     * @throws IOException
     */
    public double readNumber(FunctionCode function, SmaCode code) throws IOException {

        sendRequest(function, code);
        ByteBuffer response = parseResponse(function, code);

        long value = extractNumber(code, response);

        double formattedNumber = formatNumber(value, code);
        return formattedNumber;
    }

    /**
    * @param function
    * @param code
    * @return the value or {@link Double#NaN} if missing
    * @throws IOException
    */
    public long readLong(FunctionCode function, SmaCode code) throws IOException {

        sendRequest(function, code);
        ByteBuffer response = parseResponse(function, code);

        long value = extractNumber(code, response);

        switch (code.getFormat()) {
        case FIX0:
        case DURATION:
        case ENUM:
        case RAW:
            return value;
        default:
            throw new IllegalArgumentException("Illegal conversion for " + code.getFormat());
        }
    }

    public String readVersion(FunctionCode function, SmaCode code) throws IOException {

        sendRequest(function, code);
        ByteBuffer response = parseResponse(function, code);

        int length = code.getCount() * 2;  // always multiply with factor 2 for Modbus
        byte[] data = new byte[length];

        for (int i = 0; i < length; i++) {
            data[i] = response.get();
        }

        StringBuffer sb = new StringBuffer(length * 2); // some extra space for dots
        for (int i = length - 1; i >= 0; i--) {
            sb.append(data[i]);
            if (i > 0) {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    public String readString(FunctionCode function, SmaCode code) throws IOException {

        byte[] raw = readRaw(function, code);

        Charset charset;
        switch (code.getFormat()) {
        case UTF8:
            charset = StandardCharsets.UTF_8;
            break;
        default:
            throw new IOException("Invalid string charset: " + code.getFormat());
        }

        String text = new String(raw, charset);
        return text.trim(); // cut off trailing whites
    }

    /**
    *
    * @param function
    * @param code
    * @return
    * @throws IOException
    */
    public byte[] readRaw(FunctionCode function, SmaCode code) throws IOException {

        sendRequest(function, code);
        ByteBuffer response = parseResponse(function, code);

        int length = response.remaining();
        byte[] raw = new byte[length];
        response.get(raw);
        return raw;
    }

    /**
    *
    * @param function
    * @param code
    * @return
    * @throws IOException
    */
    public Instant readDatetime(FunctionCode function, SmaCode code) throws IOException {

        sendRequest(function, code);
        ByteBuffer response = parseResponse(function, code);

        long value = extractNumber(code, response);  // seconds since 01-01-1970

        if (code.getFormat() != Format.DATETIME) {
            throw new IOException("Code must be DATETIME, not " + code.getFormat());
        }

        return Instant.ofEpochSecond(value);
    }

    private void sendRequest(FunctionCode function, SmaCode code) throws IOException {
        if (!tcpClientSocket.isConnected()) {
            throw new IOException("Not connected");
        }

        if (code.getCount() < 0 || code.getCount() > 125) {
            throw new IllegalArgumentException("quantity must be 0 - 125");
        }

        transactionId++;    // use a new ID for every new transaction, no matter if it fails or not

        ByteBuffer bb = ByteBuffer.allocate(12);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.putShort(transactionId);
        bb.putShort(PROTOCOL_ID);
        bb.putShort(REQUEST_LENGTH);
        bb.put(unitIdentifier);
        bb.put(function.getCode());
        bb.putShort(code.getRegister());
        bb.putShort(code.getCount());

        log.trace("Request  : {}", Arrays.toString(bb.array()));
        // should look similar to [17, 85, 0, 0, 0, 6, 3, 3, 120, 55, 0, 2]

        outputStream.write(bb.array());
    }

    private ByteBuffer parseResponse(FunctionCode function, SmaCode code) throws IOException {
        byte[] responseArray = new byte[1024];
        int numberOfBytes = inputStream.read(responseArray);

        if (numberOfBytes <= 8) {
            throw new IOException("Response too short: only " + numberOfBytes + " bytes read");
        }

        ByteBuffer response = ByteBuffer.wrap(responseArray, 0, numberOfBytes);

        log.trace("Response : {}", Arrays.toString(Arrays.copyOf(responseArray, numberOfBytes)));
        // should look similar to [17, 85, 0, 0, 0, 7, 3, 3, 4, 0, 0, 2, 86]

        short respTransactionId = response.getShort();

        if (respTransactionId != transactionId) {
            throw new IOException("Invalid transaction ID: should be " + transactionId + ", but was " + respTransactionId);
        }

        short respProtocolId = response.getShort();

        if (respProtocolId != PROTOCOL_ID) {
            throw new IOException("Invalid protocol ID: should be " + PROTOCOL_ID + ", but was " + respProtocolId);
        }

        short respLength = response.getShort();

        if (response.remaining() < respLength) {
            throw new IOException("Invalid response length: " + respLength);
        }

        short respUnit = response.get();

        if (respUnit != unitIdentifier) {
            throw new IOException("Invalid response unit ID: " + respUnit);
        }

        byte respCode = response.get();
        byte errorCode = (byte) (function.getCode() + 0x80);

        byte magicValue = response.get();  // (string) length or exception code

        if (respCode != function.getCode()) {
            if (respCode == errorCode) {
                ExceptionCode exCode = ExceptionCode.getFromCode(magicValue);
                if (exCode == null) {
                    throw new IOException("ModBus error: invalid exception code: " + magicValue);
                } else {
                    throw new IOException("ModBus error: " + exCode);
                }
            }
            throw new IOException("Invalid response code: " + respCode);
        }


        int reqLength = code.getCount() * 2;  // ModBus multiplication factor for input/holding registers

        if (reqLength != magicValue) {
            throw new IOException("Data type length does not match returned data length: " + magicValue);
        }

        if (response.remaining() < reqLength) {
            throw new IOException("Not enough data in buffer to parse as " + code.getType());
        }

        return response;
    }

    private static long extractNumber(SmaCode code, ByteBuffer response) {
        long value;
        switch (code.getType()) {
        case S16:
        case U16:
            value = response.getShort();
            break;
        case S32:
        case U32:
            value = response.getInt();
            break;
        case S64:
        case U64:
            value = response.getLong();
            break;
        default:
            throw new IllegalArgumentException("Illegal data type: " + code.getType());
        }
        return value;
    }

    private static double formatNumber(long value, SmaCode code) {

        if (value == code.getType().getInvalid()) {
            return Double.NaN;
        }

        double factor;
        switch (code.getFormat()) {
        case FIX0:
        case DURATION:
        case RAW:
            factor = 1;
            break;
        case FIX1:
        case TEMP:
            factor = 10;
            break;
        case FIX2:
            factor = 100;
            break;
        case FIX3:
            factor = 1000;
            break;
        case FIX4:
            factor = 10000;
            break;
        default:
            throw new IllegalArgumentException("Illegal conversion for " + code.getFormat());
        }

        return value / factor;
    }

    private static void checkByte(int val) {
        if (val > Byte.MAX_VALUE || val < Byte.MIN_VALUE) {
            throw new IllegalArgumentException("unitIdentifier must be 8-bit");
        }
    }

}
