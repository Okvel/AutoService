package by.bsuir.spp.autoservice.service.util;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5HashCreator {
    private static final String MD5 = "MD5";

    private static Logger logger = Logger.getLogger(MD5HashCreator.class);

    private MD5HashCreator() {}

    public static String create(String src) {
        String hash = "";
        if (src != null && !src.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(MD5);
                byte[] byteData = messageDigest.digest(src.getBytes());
                for (byte data : byteData) {
                    stringBuilder.append(Integer.toString((data & 0xff) + 0x100, 16).substring(1));
                }
                hash = stringBuilder.toString();
            } catch (NoSuchAlgorithmException ex) {
                logger.error("MD5 hashing error", ex);
            }
        }

        return hash;
    }
}
