package password;

public class Secret implements Encryptable {
    private String encryptedText;

    public void encrypt(String text) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : text.toCharArray()) {
            encrypted.append((char) (c + 3)); // Caesar cipher
        }
        encryptedText = encrypted.toString();
    }

    public String decrypt() {
        StringBuilder decrypted = new StringBuilder();
        for (char c : encryptedText.toCharArray()) {
            decrypted.append((char) (c - 3));
        }
        return decrypted.toString();
    }

    public String getEncryptedText() {
        return encryptedText;
    }
}
