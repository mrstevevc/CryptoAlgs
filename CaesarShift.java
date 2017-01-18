import java.util.Scanner;
public class CaesarShift
{
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.println("What would you like me to encrypt");
		String message = reader.nextLine();
		String encrypted = encrypt(message,10);
		System.out.println(encrypted);
		String decryptedAgain = decrypt(encrypted,10);
		System.out.println(decryptedAgain);

		//demonstrate using second, combined method
		System.out.println("What would you like me to encrypt");
		String message2 = reader.nextLine();
		String encrypted2 = shiftLetters(message2,5);
		System.out.println(encrypted2);
		String decryptedAgain2 = shiftLetters(message2,-5);
		System.out.println(decryptedAgain2);
	}


	public static String encrypt(String original, int shift)
	{

		String encrypted = "";

		for (int i = 0; i < original.length(); i++) {
			//Check if it is a letter. If it isn't, then we don't have to do anything anyway
			if( (original.charAt(i) >= 'A' && original.charAt(i) <= 'Z') ||
				(original.charAt(i) >= 'a' && original.charAt(i) <= 'z'))
			{
				//Check if there was "wraparound" For example z + 3 should give us c
				//In this case, the way to get this result is by subtracting 23 instead of adding 3
				if (((char)(original.charAt(i) + shift)  > 'Z' &&
						 original.charAt(i) <= 'Z') 
					|| ((char)(original.charAt(i) + shift)  > 'z' 
						&& original.charAt(i) <= 'z'))

				{
					//add the next character which is encrypted to the String
					encrypted = encrypted + (char)(original.charAt(i) + shift - 26);	
				}
				else 
				{
					//add the next character which is encrypted to the String
					encrypted = encrypted + (char)(original.charAt(i) + shift);
				}
			}
			else
			{
				//add the original character to the String
				encrypted = encrypted + original.charAt(i);
			}
		}

		//return the resulting String
		return encrypted;
	}


	public static String decrypt(String original, int shift)
	{
		String decrypted = "";
		for (int i = 0; i < original.length(); i++) {
			if( (original.charAt(i) >= 'A' && original.charAt(i) <= 'Z') ||
				(original.charAt(i) >= 'a' && original.charAt(i) <= 'z'))
			{
				if (((char)(original.charAt(i) - shift)  < 'A' &&
						 original.charAt(i) >= 'A') 
					|| ((char)(original.charAt(i) - shift)  < 'a' 
						&& original.charAt(i) >= 'a'))

				{
					decrypted = decrypted + (char)(original.charAt(i) - shift + 26);	
				}
				else {
					decrypted = decrypted + (char)(original.charAt(i) - shift);
				}
			}
			else
			{
				decrypted = decrypted + original.charAt(i);
			}
		}
		return decrypted;
	}

	public static String shiftLetters(String original, int shift)
	{
		String shifted = "";
		for (int i = 0; i < original.length(); i++)
		{
			if (isLetter(original.charAt(i)))
			{
				shifted = shifted + shift(original.charAt(i), shift);
			}
			else
			{
				shifted = shifted + original.charAt(i);
			}
		}

		return shifted;
	}

	public static boolean isLetter(char letter)
	{
		return (letter >= 'a' && letter <= 'z') || (letter >= 'A' && letter <= 'Z');
	}


	public static char shift(char original, int shift)
	{
		int normalizedShift = shift % 26; 
		char shifted = (char)(original + shift);

		if ((shift > 'Z' && original <= 'Z') || (shift > 'z' && original <= 'z'))
		{
			shifted = (char)(shifted - 26);
		}

		return shifted;
	}

}
