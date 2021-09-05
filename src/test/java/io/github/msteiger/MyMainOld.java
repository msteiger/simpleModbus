//package io.github.msteiger;
//
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.DatagramPacket;
//import java.net.DatagramSocket;
//import java.net.InetAddress;
//import java.net.Socket;
//import java.net.SocketException;
//import java.net.UnknownHostException;
//import java.nio.ByteBuffer;
//import java.util.Arrays;
//
//public class MyMainOld {
//
//
//    public void asd() throws UnknownHostException, IOException, ModbusException {
//        ModbusClient client = null;//new ModbusClient(ipAddress, port);
//        client.Connect();
//
//        for (int i = 0; i < 1; i++) {
//            // GEHT
//            readInputRegisters(42110, 1);
//
//            // ???
//
//            readInputRegisters(40915, 2);
//            readInputRegisters(30775, 2);
//
//
////            int[] regs2 = readInputRegisters(30775, 2);
////            int[] regs3 = readInputRegisters(30776, 2);
//
////            System.out.println("Data: " + Arrays.toString(regs1) + " - " + Arrays.toString(regs2) + " - "
////                    + Arrays.toString(regs3));
//
////            Thread.sleep(1000);
//        }
//    }
//
//    public static int[] readInputRegisters(int startingAddress, int quantity)
//            throws de.re.easymodbus.exceptions.ModbusException, UnknownHostException, SocketException, IOException {
//
//        Socket tcpClientSocket = new Socket();
//
//        String ipAddress = "192.168.178.95";
//        int connectTimeout = 10000;
//        int port = 502;
//        InputStream inStream;
//        DataOutputStream outStream;
//
//        tcpClientSocket = new Socket(ipAddress, port);
//        tcpClientSocket.setSoTimeout(connectTimeout);
//        outStream = new DataOutputStream(tcpClientSocket.getOutputStream());
//        inStream = tcpClientSocket.getInputStream();
//
//
//        byte [] transactionIdentifier = new byte[2];
//        byte [] protocolIdentifier = new byte[2];
//        byte [] length = new byte[2];
//        byte[] crc = new byte[2];
//        byte unitIdentifier = 3;
//        byte functionCode = 0x03;
//        byte [] startingAddressArray = new byte[2];
//        byte [] quantityArray = new byte[2];
//        boolean udpFlag = false;
//        int numberOfBytes = 0;
//
//        if (tcpClientSocket == null)
//            throw new de.re.easymodbus.exceptions.ConnectionException("connection Error");
//        if (startingAddress > 65535 | quantity > 125)
//            throw new IllegalArgumentException("Starting adress must be 0 - 65535; quantity must be 0 - 125");
//        int[] responseArray = new int[quantity];
//        transactionIdentifier = toByteArray(0x0001);
//        protocolIdentifier = toByteArray(0x0000);
//        length = toByteArray(0x0006);
//        startingAddressArray = toByteArray(startingAddress);
//        quantityArray = toByteArray(quantity);
//        byte[] response = new byte[20];
//        byte[] data = new byte[]
//                {
//                    transactionIdentifier[1],
//                    transactionIdentifier[0],
//                    protocolIdentifier[1],
//                    protocolIdentifier[0],
//                    length[1],
//                    length[0],
//                    unitIdentifier,
//                    functionCode,
//                    startingAddressArray[1],
//                    startingAddressArray[0],
//                    quantityArray[1],
//                    quantityArray[0],
//                    crc[0],
//                    crc[1]
//                };
//        if (tcpClientSocket.isConnected() | udpFlag) {
//            if (udpFlag) {
//                InetAddress inetAddress = InetAddress.getByName(ipAddress);
//                DatagramPacket sendPacket = new DatagramPacket(data, data.length, inetAddress, port);
//                DatagramSocket clientSocket = new DatagramSocket();
//                clientSocket.setSoTimeout(500);
//                clientSocket.send(sendPacket);
//                data = new byte[2100];
//                DatagramPacket receivePacket = new DatagramPacket(data, data.length);
//                clientSocket.receive(receivePacket);
//                clientSocket.close();
//                data = receivePacket.getData();
//            } else {
//                outStream.write(data, 0, data.length - 2);
//                numberOfBytes = inStream.read(response, 0, response.length);
//                System.out.println(Arrays.toString(data));
//                System.out.println(Arrays.toString(response));
//            }
//            if (((int) (response[7] & 0xff)) == 0x84 & ((int) response[8]) == 0x01) {
//                throw new de.re.easymodbus.exceptions.FunctionCodeNotSupportedException(
//                        "Function code not supported by master");
//            }
//            if (((int) (response[7] & 0xff)) == 0x84 & ((int) response[8]) == 0x02) {
//                throw new de.re.easymodbus.exceptions.StartingAddressInvalidException(
//                        "Starting adress invalid or starting adress + quantity invalid");
//            }
//            if (((int) (response[7] & 0xff)) == 0x84 & ((int) response[8]) == 0x03) {
//                throw new de.re.easymodbus.exceptions.QuantityInvalidException("Quantity invalid");
//            }
//            if (((int) (response[7] & 0xff)) == 0x84 & ((int) response[8]) == 0x04) {
//                throw new de.re.easymodbus.exceptions.ModbusException("Error reading");
//            }
//        }
//        for (int i = 0; i < quantity && i * 2 < numberOfBytes; i++) {
//            byte[] bytes = new byte[2];
//            bytes[0] = (byte) response[9 + i * 2];
//            bytes[1] = (byte) response[9 + i * 2 + 1];
//            ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
//            responseArray[i] = byteBuffer.getShort();
//        }
//
//        tcpClientSocket.close();
//        return (responseArray);
//    }
//
//    public static byte[] toByteArray(int value)
//    {
//        byte[] result = new byte[2];
//        result[1] = (byte) (value >> 8);
//        result[0] = (byte) (value);
//        return result;
//    }
//
//}
