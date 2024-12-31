import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class TripleDesAes {
    private String algorithm;
    private SecretKeySpec secretKey;
    private Cipher cipher;

    public TripleDesAes(String algorithm) {
        this.algorithm = algorithm; // either 3DES or AES in our case
        try {
            // create a Cipher object that will be used to apply the transformation
            cipher = Cipher.getInstance(algorithm + "/ECB/PKCS5Padding");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String encrypt(String plainData, String key) {
        byte[] byteKey = key.getBytes();
        // make sure that the key length is 24 bytes for 3DES and 16 bytes for AES
        byteKey = Arrays.copyOf(byteKey, getKeySize());

        try {
            // generate the key from array of bytes
            secretKey = new SecretKeySpec(byteKey, algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(plainData.getBytes());

            Base64.Encoder encoder = Base64.getEncoder();
            String encryptedText = encoder.encodeToString(encryptedBytes);
            return encryptedText;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public String decrypt(String cipherData, String key) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encryptedBytes = decoder.decode(cipherData);
        byte[] byteKey = key.getBytes();
        // make sure that the key length is 24 bytes for 3DES and 16 bytes for AES
        byteKey = Arrays.copyOf(byteKey, getKeySize());

        try {
            // generate the key from array of bytes
            secretKey = new SecretKeySpec(byteKey, algorithm);
            // create a Cipher object that will be used to apply the transformation
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            return new String(decryptedBytes);
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    private int getKeySize() {
        if (algorithm == "AES")
            return 16; // AES-128
        return 24; // Triple DES
    }
}
