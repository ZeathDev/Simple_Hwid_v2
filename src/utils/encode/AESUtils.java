package utils.encode;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.io.FileOutputStream;
import java.io.FileInputStream;


public class AESUtils {

    private static final String ALGORITHM = "AES";

    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(128);
        SecretKey key = keyGen.generateKey();
        FileOutputStream fileOutputStream = new FileOutputStream("key.aes");
        fileOutputStream.write(key.getEncoded());
        fileOutputStream.close();
        return key;
    }

    public static SecretKey generateKey(String customKey) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(128, new SecureRandom(customKey.getBytes()));
        SecretKey key = keyGen.generateKey();
        FileOutputStream fileOutputStream = new FileOutputStream("key.aes");
        fileOutputStream.write(key.getEncoded());
        fileOutputStream.close();
        return key;
    }

    public static SecretKey generateKeyFromFile() throws Exception {
        FileInputStream fis = new FileInputStream("key.aes");
        byte[] keyBytes = new byte[fis.available()];
        fis.read(keyBytes);
        fis.close();
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
        return secretKey;
    }

    public static byte[] encrypt(String plaintext, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.getEncoder().encode(cipher.doFinal(plaintext.getBytes()));
    }

    public static byte[] decrypt(byte[] cipherText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(Base64.getDecoder().decode(cipherText));
    }

    public static byte[] decrypt(byte[] cipherText) throws Exception {
        FileInputStream fis = new FileInputStream("key.aes");
        byte[] keyBytes = new byte[fis.available()];
        fis.read(keyBytes);
        fis.close();
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(Base64.getDecoder().decode(cipherText));
    }
}
