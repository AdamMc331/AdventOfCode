import java.security.*;

public class AdventFive {
	private static final String INPUT = "abc";

	public static void main(String[] args) {
		partOne();
		partTwo();
	}

	private static void partOne() {
		String password = "";

		int i = 0;
		while(password.length() < 8) {
			String message = INPUT + i;
			String hash = hashString(message);

			if(hash.substring(0, 5).equals("00000")) {
				password += hash.charAt(5);
			}

			i++;
		}

		System.out.println("First code is: " + password);
	}

	private static void partTwo() {
		char[] password = new char[] {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};

		int i = 0;
		while(containsSpace(password)) {
			String message = INPUT + i;
			String hash = hashString(message);

			if(hash.substring(0, 5).equals("00000")) {		
				int pos = Character.getNumericValue(hash.charAt(5));
				if(pos < 8) {
					if(password[pos] == ' ') {
						password[pos] = hash.charAt(6);
					}
				}
			}

			i++;
		}

		System.out.println("Second code is: " + new String(password));
	}

	private static boolean containsSpace(char[] password) {
		for(char ch : password) if (ch == ' ') return true;

		return false;
	}

	private static String hashString(String message) {
		try {
			byte[] messageBytes = message.getBytes("UTF-8");
			byte[] md5Hash = MessageDigest.getInstance("MD5").digest(messageBytes);
			StringBuffer sb = new StringBuffer();
			for (byte b : md5Hash) {
				sb.append(String.format("%02x", b & 0xff));
			}
			return sb.toString();
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return "";
		}

	}
}