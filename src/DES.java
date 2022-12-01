import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class DES {

    private byte[] inputText;
    private String key;
    private SecretKey secretKey;

    public DES(byte[] inputText, String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.inputText = inputText;
        this.key = key;
        this.secretKey = keyGen("DES",64,key);
    }

    public SecretKey keyGen(String op, int keySize, String key) throws NoSuchAlgorithmException, InvalidKeySpecException {

        byte[] keyBytes = key.getBytes();
        byte[] saltKey = new byte[keySize/8];
        saltKey = Arrays.copyOfRange(keyBytes,0,8);

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(key.toCharArray(), saltKey, 65536, keySize);

        SecretKey secretKey = new SecretKeySpec(factory.generateSecret(spec).getEncoded(),op);
        return secretKey;
    }

    public byte[] encrypt(String op) throws Exception {

        Cipher cipher = Cipher.getInstance((op+"/ECB/PKCS5Padding"));

        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] cipherText = cipher.doFinal(inputText);

        return cipherText;
    }

    public byte[] decrypt(String op) throws Exception {

        Cipher cipher = Cipher.getInstance((op+"/ECB/PKCS5Padding"));

        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] plainText = cipher.doFinal(inputText);

        return plainText;
    }




    public byte[] getInputText() {
        return inputText;
    }

    public void setInputText(byte[] inputText) {
        this.inputText = inputText;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

}
