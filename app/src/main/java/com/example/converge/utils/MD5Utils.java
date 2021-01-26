package com.example.converge.utils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <pre>
 *     author: chenxian
 *     Date  : 2018/8/6
 *     Description:
 * </pre>
 */
public class MD5Utils {
    public static String getStringMD5(String content) {
        String s = null;

        try {
            MessageDigest digester = MessageDigest.getInstance("MD5");
            byte[] bytes = content.getBytes("UTF-8");
            digester.update(bytes, 0, bytes.length);
            byte[] digest = digester.digest();
            return byteArrayToHexString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }
    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder s = new StringBuilder();
        for (byte b : bytes) {
            int h = ((b >> 4) & 0x0f);
            if (h > 9)
                s.append((char)(h-10+'a'));
            else
                s.append((char)(h+'0'));

            h = (b & 0x0f);
            if (h > 9)
                s.append((char)(h-10+'a'));
            else
                s.append((char)(h+'0'));
        }

        return s.toString();
    }
}
