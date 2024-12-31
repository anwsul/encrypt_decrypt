import java.util.Arrays;
import java.util.Base64;

public class OneTimePad {

    public static String encrypt(String plainData, String key) {
        byte[] byteData = plainData.getBytes();
        // repeat the key to match the length of the plain data
        byte[] byteKey = Arrays.copyOf(key.getBytes(), plainData.length());
        for (int i = key.length(); i < plainData.length(); i++) {
            byteKey[i] = (byte) key.charAt(i % key.length());
        }

        byte[] encryptedBytes = new byte[plainData.length()];
        for (int i = 0; i < plainData.length(); i++) {
            encryptedBytes[i] = (byte) (byteData[i] ^ byteKey[i]);
        }

        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedText = encoder.encodeToString(encryptedBytes);
        return encryptedText;
    }

    public static String decrypt(String cipherData, String key) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encryptedBytes = decoder.decode(cipherData);

        // repeat the key to match the length of the encryptedBytes
        byte[] byteKey = Arrays.copyOf(key.getBytes(), encryptedBytes.length);
        for (int i = key.length(); i < byteKey.length; i++) {
            byteKey[i] = (byte) key.charAt(i % key.length());
        }

        byte[] decryptedBytes = new byte[encryptedBytes.length];
        for (int i = 0; i < encryptedBytes.length; i++) {
            decryptedBytes[i] = (byte) (encryptedBytes[i] ^ byteKey[i]);
        }

        return new String(decryptedBytes);
    }
}
