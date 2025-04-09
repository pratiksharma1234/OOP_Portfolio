package password;

public class EncryptionTest {
    public static void main(String[] args) {
        Encryptable secret;

        // Secret (Caesar Cipher)
        secret = new Secret();
        secret.encrypt("hello");
        System.out.println("Encrypted from Secret: " + ((Secret) secret).getEncryptedText());
        System.out.println("Decrypted from Secret: " + secret.decrypt());

        // Password (String Reversal)
        secret = new Password();
        secret.encrypt("hello");
        System.out.println("Encrypted from Password: " + ((Password) secret).getEncryptedText());
        System.out.println("Decrypted from Password: " + secret.decrypt());
    }
}
