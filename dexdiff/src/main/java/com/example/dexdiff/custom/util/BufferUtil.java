package com.example.dexdiff.custom.util;

import java.io.UTFDataFormatException;
import java.nio.ByteBuffer;

public class BufferUtil {

    public static byte[] readBytes(ByteBuffer in, int len) {
        byte[] data = new byte[len];
        in.get(data);
        return data;
    }

    public static int readUnsignedLeb128(ByteBuffer in) {
        return Leb128.readUnsignedLeb128(in);
    }

    public static void writeUnsignedLeb128(DexDataBuffer buffer, int value) {
        Leb128.writeUnsignedLeb128(buffer, value);
    }

    public static void writeUnsignedLeb128(ByteBuffer buffer, int value) {
        Leb128.writeUnsignedLeb128(buffer, value);
    }

    public static String readMutf8(ByteBuffer in, int size) throws UTFDataFormatException {
        String data = Mutf8.decode(in, new char[size]);
        return data;
    }

    public static byte[] writeMutf8(String data) throws UTFDataFormatException {
        return Mutf8.encode(data);
    }


}
