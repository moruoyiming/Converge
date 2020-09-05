package com.example.dexdiff.custom;


import com.example.dexdiff.custom.android.StringIdItem;

public class PatchOperation {
    public static final int OP_DEL = 0;
    public static final int OP_ADD = 1;
    public static final int OP_REPLACE = 2;

    public int op;
    public int index;
    public StringIdItem item;

    public PatchOperation(int op, int index, StringIdItem item) {
        this.op = op;
        this.index = index;
        this.item = item;
    }

    @Override
    public String toString() {
        return "PatchOperation{" +
                "op=" + op +
                ", index=" + index +
                ", item=" + item.data +
                '}';
    }
}
