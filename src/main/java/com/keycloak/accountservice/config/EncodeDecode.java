package com.keycloak.accountservice.config;

import org.apache.axis.encoding.Base64;

import java.io.UnsupportedEncodingException;

public class EncodeDecode {


    // Encode
    public static String encodeString(String text)
            throws UnsupportedEncodingException {
        byte[] bytes = text.getBytes("UTF-8");
        String encodeString = Base64.encode(bytes);
        return encodeString;
    }

    // Decode
    public static String decodeString(String encodeText)
            throws UnsupportedEncodingException {
        byte[] decodeBytes = Base64.decode(encodeText);
        String str = new String(decodeBytes, "UTF-8");
        return str;
    }
}
