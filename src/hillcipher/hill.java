package HillCipher;

import java.util.*;

public class hill {

	final public static int MOD = 26;
	final public static String pad = "X";
	public int n;
	public int[][] key;

	public hill(int[][] theKey) {
		key = theKey;
		n = key.length;
	}

	public String encrypt(String plain) {

		// Pad until plain text is multiple of key size.
		while (plain.length()%n != 0) plain = plain+pad;

		// Put together the encryption of each block and return.
		String res = "";
		for (int i=0; i<plain.length(); i+=n)
			res = res + encryptBlock(plain.substring(i,i+n));
		return res;
	}

	// Pre-condition: length of block is length of key.
	private String encryptBlock(String block) {

		char[] res = new char[n];
		for (int row=0; row<n; row++) {
			int sum = 0;
			for (int col=0; col<n; col++)
				sum += (key[row][col]*(block.charAt(col)-'A'));
			res[row] = (char)('A'+(sum%MOD));
		}

		// Answer as a string.
		return new String(res);
	}


	public static void main(String[] args) {

		// Sets up a test of encrypting using the hill cipher
		// from a file with the following format:
		// line 1: integer n, indiciating n x n matrix size
		// lines 2..n+1: n integers each, space-separated indicating the key
		// line n+2: the message, all uppercase, no spaces.

		// Read in key.
		Scanner stdin = new Scanner(System.in);
		int n = stdin.nextInt();
		int[][] key = new int[n][n];
		for (int i=0; i<n; i++)
			for (int j=0; j<n; j++)
				key[i][j] = stdin.nextInt();

		// Make object, encrypt and output result.
		hill encrypter = new hill(key);
		System.out.println(encrypter.encrypt(stdin.next()));
	}
}