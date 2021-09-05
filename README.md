# simpleModbus

A simple and type-safe library implementation of the modbus TCP specification. 
It does not cover all aspects, but basic communication works and is reliable.
The focus of this implementation is on (type-)safety and adequate error handling.

```java
   try (Modbus mb = new Modbus("192.168.178.95")) {
       mb.setUnitIdentifier(3);

       mb.read(...)
```

The implementation was tested with an SMA Sunny Boy.
