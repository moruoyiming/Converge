package com.example.dexdiff.custom.diff;


import com.example.dexdiff.custom.PatchOperation;
import com.example.dexdiff.custom.android.Dex;
import com.example.dexdiff.custom.android.StringIdItem;
import com.example.dexdiff.custom.util.BufferUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UTFDataFormatException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.Adler32;

public class DexPatch {
    private PatchFile patchFile;
    private Dex dex;

    public DexPatch(File oldFile, File patch) throws IOException {
        dex = new Dex(oldFile);
        patchFile = new PatchFile(patch);
    }

    public void patch(File newFile) throws IOException, NoSuchAlgorithmException {
        for (PatchOperation patchOperation : patchFile.patchOperations) {
            if (patchOperation.op == PatchOperation.OP_REPLACE) {
                StringIdItem oldItem = dex.string_ids.get(Integer.valueOf(patchOperation.index));
                if (oldItem.utf16_size == patchOperation.item.utf16_size) {
                    dex.data.position(oldItem.string_data_off);
                    BufferUtil.writeUnsignedLeb128(dex.data, patchOperation.item.utf16_size);
                    dex.data.put(BufferUtil.writeMutf8(patchOperation.item.data));
                    dex.data.position(0);
                }
            }
        }
        byte[] sha1 = getSha1(dex.data.array());
        dex.data.position(12);
        dex.data.put(sha1);

        int checkSum = getCheckSum(dex.data.array());
        dex.data.position(8);
        dex.data.putInt(checkSum);

        FileOutputStream fos = new FileOutputStream(newFile);
        fos.write(dex.data.array());
        fos.close();
    }

    public static byte[] getSha1(byte[] input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");

        mDigest.update(input, 32, input.length - 32);

        byte[] newdt = mDigest.digest();
        return newdt;
    }

    private static int getCheckSum(byte[] dexBytes) {
        Adler32 adler = new Adler32();
        adler.update(dexBytes, 12, dexBytes.length - 12);// 从12到文件末尾计算校验码
        long value = adler.getValue();
        int va = (int) value;
        return va;
    }


}
