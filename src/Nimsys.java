import java.util.Scanner;

/**
 * 
 */

/**
 * @author ZIYANG XIE StudentID 870523 9 Apr. 2018-5:14:53 pm
 */
public class Nimsys {

	/**
	 * @param args
	 */
	// store users in the system
	NimPlayer[] user = new NimPlayer[100];
	// the cursor in users
	int cursor = 0;
	Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void addPlayer(String userName, String givenName, String familyName) {
		if (cursor < 100) {
			NimPlayer player = new NimPlayer(userName, givenName, familyName);
			cursor++;
			user[cursor] = player;
		}
	}
}
