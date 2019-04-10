package com.cjss.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashService {

    private static final String HASH_ALGORITHM = "SHA-1";

    private MessageDigest messageDigest = null;

    private HashService() {
        try {
            messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert (messageDigest != null);
    }

    public static HashService getInstance() {
        return SingletonHandler.instance;
    }

    public String getHashAsString(String text) {
        byte[] mdbytes = messageDigest.digest(text.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < mdbytes.length; j++) {
            String s = Integer.toHexString(0xff & mdbytes[j]);
            s = (s.length() == 1) ? "0" + s : s;
            sb.append(s);
        }
        return sb.toString();
    }

    private static class SingletonHandler {
        static final HashService instance = new HashService();
    }
}
