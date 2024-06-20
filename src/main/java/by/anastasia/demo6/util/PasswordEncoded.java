package by.anastasia.demo6.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoded {
    private static MessageDigest messageDigest;

    static {
        try {
            messageDigest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    public String encoded(String password) {
        messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
        byte[] bytesEncoded = messageDigest.digest();
        BigInteger bigInt = new BigInteger(1, bytesEncoded);
        String result = bigInt.toString(16);
        return result;
    }
}
