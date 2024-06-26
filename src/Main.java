import javax.crypto.SecretKey;

import utils.HwidUtils;
import utils.encode.AESUtils;
import utils.encode.AtbashUtils;

public class Main {

    public static void main(String[] args) {
        String hwid = HwidUtils.getHwid();
        System.out.println("Your HWID: " + hwid);

        try {
            // SecretKey aesKey = AESUtils.generateKey(); // Random Key
            // SecretKey aesKey = AESUtils.generateKey("custom"); // Random Key by custom string
            SecretKey aesKey = AESUtils.generateKeyFromFile(); // If you have key.aes. Please call this!
            byte[] aesHwid = AESUtils.encrypt(hwid, aesKey);

            System.out.println("AES:" + new String(aesHwid));
            System.out.println("DeAES: " + new String(AESUtils.decrypt(aesHwid)));

            String atbashHwid = new String(aesHwid);
            String atbashed = AtbashUtils.encrypt(atbashHwid);

            System.out.println("Atbash: " + atbashed);
            System.out.println("DeAtbash: " + AtbashUtils.decrypt(atbashed));

            System.out.println(
                    "DeEncode: " + new String(AESUtils.decrypt(AtbashUtils.decrypt(atbashed).getBytes(), aesKey)));

            // !!! CRASH TEST !!!
            // HwidUtils.crash();
            // HwidUtils.crash2(); // faster than HwidUtils.crash()
            // HwidUtils.crash3(); // An interesting
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}