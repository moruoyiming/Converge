package com.example.dexdiff.custom.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class DexDataBuffer {

    public static final int DEFAULT_BUFFER_SIZE = 512;
    private ByteBuffer data;
    private int dataBound;

    public DexDataBuffer() {
        this.data = ByteBuffer.allocate(DEFAULT_BUFFER_SIZE);
        this.data.order(ByteOrder.LITTLE_ENDIAN);
        this.dataBound = this.data.position();
        this.data.limit(this.data.capacity());
    }

    //扩容
    private void ensureBufferSize(int bytes) {
        if (this.data.position() + bytes > this.data.limit()) {
            byte[] array = this.data.array();
            byte[] newArray = new byte[array.length + bytes + (array.length >> 1)];
            System.arraycopy(array, 0, newArray, 0, this.data.position());
            int lastPos = this.data.position();
            this.data = ByteBuffer.wrap(newArray);
            this.data.order(ByteOrder.LITTLE_ENDIAN);
            this.data.position(lastPos);
            this.data.limit(this.data.capacity());
        }
    }


    public void write(byte[] bytes) {
        ensureBufferSize(bytes.length);
        this.data.put(bytes);
        if (this.data.position() > this.dataBound) {
            this.dataBound = this.data.position();
        }
    }


    public void writeShort(short i) {
        ensureBufferSize(2);
        data.putShort(i);
        if (this.data.position() > this.dataBound) {
            this.dataBound = this.data.position();
        }
    }

    public void writeInt(int i) {
        ensureBufferSize(4);
        this.data.putInt(i);
        if (this.data.position() > this.dataBound) {
            this.dataBound = this.data.position();
        }
    }


    public byte[] array() {
        byte[] result = new byte[this.dataBound];
        byte[] dataArray = this.data.array();
        System.arraycopy(dataArray, 0, result, 0, this.dataBound);
        return result;
    }

    public void writeByte(int b) {
        ensureBufferSize(1);
        data.put((byte) b);
        if (this.data.position() > this.dataBound) {
            this.dataBound = this.data.position();
        }
    }

    public int writeUnsignedLeb128(int value) {
        int remaining = value >>> 7;
        int bytesWritten = 0;
        while (remaining != 0) {
            writeByte((byte) ((value & 0x7f) | 0x80));
            ++bytesWritten;
            value = remaining;
            remaining >>>= 7;
        }

        writeByte((byte) (value & 0x7f));
        ++bytesWritten;
        return bytesWritten;
    }

}
