package com.example.hotfix.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExecUtils {
    /**
     * 执行控制台命令
     *
     * @throws IOException
     */
    public static void execMethod() throws Exception {
        Process process = Runtime.getRuntime().exec("java -version");
        process.waitFor();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        InputStream inputStream = process.getInputStream();
        int len;
        byte[] buffer = new byte[4096];
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        System.out.println(new String(bos.toByteArray()));
    }
}
