package password;

public class Password implements Encryptable {
    private String encryptedText;

    public void encrypt(String text) {
        encryptedText = new StringBuilder(text).reverse().toString();
    }

    public String decrypt() {
        return new StringBuilder(encryptedText).reverse().toString();
    }

    public String getEncryptedText() {
        return encryptedText;
    }
}
