package io.github.msteiger.simpleModbus;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * https://modbus.org/specs.php
 */
public class Modbus implements AutoCloseable {

    public static final int DEFAULT_TIMEOUT = 5000;
    public static final int DEFAULT_PORT = 502;  // both TCP and UDP
    public static final byte DEFAULT_UNIT_IDENTIFIER = -1;

    private final Socket tcpClientSocket = new Socket();
    private final InputStream inputStream;
    private final OutputStream outputStream;

    private byte unitIdentifier = DEFAULT_UNIT_IDENTIFIER;

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
    }

    public double read(FunctionCode function, SmaCode code) throws IOException {

        if (!tcpClientSocket.isConnected()) {
            throw new IOException("Not connected");
        }

        if (code.getCount() < 0 || code.getCount() > 125) {
            throw new IllegalArgumentException("quantity must be 0 - 125");
        }

        ByteBuffer bb = ByteBuffer.allocate(12);
        bb.order(ByteOrder.BIG_ENDIAN);
        short transactionId = 12345;
        short protocolId = 0;
        short length = 6;
        bb.putShort(transactionId);
        bb.putShort(protocolId);
        bb.putShort(length);
        bb.put(unitIdentifier);
        bb.put(function.getCode());
        bb.putShort(code.getRegister());
        bb.putShort(code.getCount());

//        System.out.println(Arrays.toString(bb.array()));
//      [0, 1, 0, 0, 0, 6, 3, 3, 120, 55, 0, 2]

        outputStream.write(bb.array());

        byte[] responseArray = new byte[1024];
        int numberOfBytes = inputStream.read(responseArray);

        if (numberOfBytes <= 8) {
            throw new IOException("Response too short: only " + numberOfBytes + " bytes read");
        }

        ByteBuffer response = ByteBuffer.wrap(responseArray, 0, numberOfBytes);

//        System.out.println(Arrays.toString(response.array()));
//      [0, 1, 0, 0, 0, 7, 3, 3, 4, 0, 0, 2, 86, 0, 0, 0, 0, 0, 0, 0]

        short respTransactionId = response.getShort();

        if (respTransactionId != transactionId) {
            throw new IOException("Invalid transaction ID: should be " + transactionId + ", but was " + respTransactionId);
        }

        short respProtocolId = response.getShort();

        if (respProtocolId != protocolId) {
            throw new IOException("Invalid protocol ID: should be " + protocolId + ", but was " + respProtocolId);
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

        // TODO: clarify the meaning of this value in the happy flow
        byte magicValue = response.get();

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


        int reqLength = code.getType().getLength();

        if (reqLength != magicValue) {
            throw new IOException("Data type length does not match returned data length: " + magicValue);
        }

        if (response.remaining() < reqLength) {
            throw new IOException("Not enough data in buffer to parse as " + code.getType());
        }

        long value = readNumber(code, response);

        double formattedNumber = formatNumber(value, code);
        return formattedNumber;
    }

    private static long readNumber(SmaCode code, ByteBuffer response) {
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
            factor = 1;
            break;
        case FIX1:
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
