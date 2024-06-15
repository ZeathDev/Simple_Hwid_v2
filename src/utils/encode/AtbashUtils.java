package utils.encode;

public class AtbashUtils {

    public static String encrypt(String plaintext) {
        StringBuilder ciphertext = new StringBuilder();

        for (char c : plaintext.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                ciphertext.append((char) ('Z' - (c - 'A')));
            } else if (c >= 'a' && c <= 'z') {
                ciphertext.append((char) ('z' - (c - 'a')));
            } else {
                ciphertext.append(c);
            }
        }

        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext) {
        return encrypt(ciphertext);
    }
}
