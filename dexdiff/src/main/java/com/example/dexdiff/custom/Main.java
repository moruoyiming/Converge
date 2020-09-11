package com.example.dexdiff.custom;

import com.example.dexdiff.custom.diff.DexDiff;
import com.example.dexdiff.custom.diff.DexPatch;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {




        DexDiff dexDiff = new DexDiff(new File("F:\\Lance\\DexDiff\\DexDiff\\diff\\src\\main\\java\\old.dex"),
                new File("F:\\Lance\\DexDiff\\DexDiff\\diff\\src\\main\\java\\new.dex"));
        dexDiff.diff(new File("F:\\Lance\\DexDiff\\DexDiff\\diff\\src\\main\\java\\patch.dex"));

        DexPatch dexPatch = new DexPatch(new File("F:\\Lance\\DexDiff\\DexDiff\\diff\\src\\main\\java\\old.dex"),
                new File("F:\\Lance\\DexDiff\\DexDiff\\diff\\src\\main\\java\\patch.dex"));
        dexPatch.patch(new File("F:\\Lance\\DexDiff\\DexDiff\\diff\\src\\main\\java\\new2.dex"));

    }
}
