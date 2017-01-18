import java.util.Scanner;

final class VigenereSquare {

    /**
     * A 2D char array representing the shifted alphabets.
     */
    public static final char[][] SQUARE = fillSquare();

    private static final int LETTERS_IN_ALPHABET = 26;
    private static final int ASCII_RANGE = 256;

    private VigenereSquare() {}

    /**
     * Fill square with shifted alphabets in ASCII positions:
     *  'a' = 97 .. 'z' = 122
     * @return initialised char[][]
     */
    private static char[][] fillSquare() {
        char[][] square = new char[ASCII_RANGE][ASCII_RANGE];
        int start = 'a';
        int end = start + (LETTERS_IN_ALPHABET - 1);
        int index = start;
        for (int i = start; i <= end; i++) {
            for (int j = start; j <= end; j++) {
                //Check index position if beyond the range of the alphabet
                //reset index position to start.
                if (index > end) {
                    index = start;
                }
                square[i][j] = (char) index;
                index++;
            }
            index = i + 1;
        }
        return square;
    }
}


class CipherKey {

    /**
     * CipherKey String value.
     */
    public final String KEY;

    public CipherKey(String text, String keyword) {
        KEY = createKey(text, keyword);
    }

    /**
     * Creates a key string of the same length of the text based on
     * the keyword.
     * @param text to be encrypted
     * @param keyword the chosen keyword
     * @return the key string
     */
    private String createKey(final String text, final String keyword) {
        StringBuilder key = new StringBuilder();
        for (int i = 0, keywordIndex = 0; i < text.length(); i++,
                keywordIndex++) {
            if (keywordIndex >= keyword.length()) {
                keywordIndex = 0;
            }
            key.append(keyword.charAt(keywordIndex));
        }
        return key.toString();
    }
}


final class VigenereCipherEncrypter {

    private VigenereCipherEncrypter() {}

    /**
     * Encrypt the message using the provided CipherKey and VigenereSquare.
     * @param message to be encrypted
     * @param key used to encrypt message
     * @return encrypted message string
     */
    public static String encrypt(final String message, final CipherKey key) {
        StringBuilder cipher = new StringBuilder();
        String k = key.KEY;
        char[][] square = VigenereSquare.SQUARE;
        for (int i = 0; i < k.length(); i++) {
            //Use the integer values of the key and message char at postion i
            //to determine which character to use from the VigenereSquare and
            //append it to the cipher text.
            cipher.append(square[k.charAt(i)][message.charAt(i)]);
        }
        return cipher.toString();
    }
}


final class VigenereCipherDecrypter {

    private VigenereCipherDecrypter() {}

    /**
     * Decrypt the cipher text using the provided CipherKey and
     * VigenereSquare.
     * @param cipher text.
     * @param key used to decrypt the cipher text.
     * @return decrypted message.
     */
    public static String decrypt(final String cipher, final CipherKey key) {
        StringBuilder message = new StringBuilder();
        String k = key.KEY;
        char[][] square = VigenereSquare.SQUARE;
        for (int i = 0; i < k.length(); i++) {
            int rowIndex = k.charAt(i);
            char[] row = square[rowIndex];
            int colIndex = new String(row).indexOf(cipher.charAt(i));
            message.append((char) colIndex);
        }
        return message.toString();
    }
}


public class Vigenere {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter message to encrypt (a-z characters only): ");
        String message = in.nextLine();

        System.out.println("Enter the keyword: ");
        String keyword = in.nextLine();

        CipherKey cipherKey = new CipherKey(message, keyword);

        String cipherText = VigenereCipherEncrypter.encrypt(message, cipherKey);
        System.out.println("Encrypted message: " + cipherText);

        String decryptedMessage = VigenereCipherDecrypter.decrypt(cipherText,
                cipherKey);
        System.out.println("Decrypted message: " + decryptedMessage);
    }
}
