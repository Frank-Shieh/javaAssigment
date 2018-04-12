
import java.util.Scanner;

/**
 * @author ZIYANG XIE StudentID 870523 8 Mar. 2018-1:24:37 pm
 */
public class NimPlayer {

	private String userName;
	private String givenName;
	private String familyName;
	private int numberOfPlayed;
	private int numberOfWon;

	public NimPlayer(String userName, String givenName, String familyName) {
		this.userName = userName;
		this.givenName = givenName;
		this.familyName = familyName;
		this.numberOfPlayed = 0;
		this.numberOfWon = 0;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}

	/**
	 * @param givenName
	 *            the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	/**
	 * @return the familyName
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * @param familyName
	 *            the familyName to set
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/**
	 * @return the numberOfPlayed
	 */
	public int getNumberOfPlayed() {
		return numberOfPlayed;
	}

	/**
	 * @param numberOfPlayed
	 *            the numberOfPlayed to set
	 */
	public void setNumberOfPlayed(int numberOfPlayed) {
		this.numberOfPlayed = numberOfPlayed;
	}

	/**
	 * @return the numberOfWon
	 */
	public int getNumberOfWon() {
		return numberOfWon;
	}

	/**
	 * @param numberOfWon
	 *            the numberOfWon to set
	 */
	public void setNumberOfWon(int numberOfWon) {
		this.numberOfWon = numberOfWon;
	}

	// get the number to remove
	public int removeStone(Scanner scanner) {
		int numberToRemove = scanner.nextInt();
		return numberToRemove;
	}

}
