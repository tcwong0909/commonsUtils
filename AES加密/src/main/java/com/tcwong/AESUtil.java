package com.tcwong;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * Description
 *
 * @author tcwong
 * @date 2020/9/22
 * Since 1.8
 */
public class AESUtil {
    public static String encrypt(String content, String password) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            keyGenerator.init(128, secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] encoded = secretKey.getEncoded();
            SecretKeySpec aes = new SecretKeySpec(encoded, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
            cipher.init(1,secretKey);
            byte[] result = cipher.doFinal(bytes);
            return Base64.encodeBase64String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String content, String password) {
        try {
            byte[] contentBytes = Base64.decodeBase64(content);
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            keyGenerator.init(128,secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] secretKeyEncoded = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyEncoded, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(2,secretKey);
            byte[] doFinal = cipher.doFinal(contentBytes);
            return new String(doFinal, StandardCharsets.UTF_8);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String aaaaa = encrypt("aaaaa", "123456");
        System.out.println(aaaaa);
        String decrypt = decrypt(aaaaa, "123456");
        System.out.println(decrypt);
    }
}
