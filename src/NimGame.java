import java.util.Scanner;

/**
 * 
 */

/**
 * @author ZIYANG XIE StudentID 870523 9 Apr. 2018-4:35:44 pm
 */
public class NimGame {
	private int upperBound;
	private int numberOfStones;
	private NimPlayer playerA;
	private NimPlayer playerB;
	private String winner;

	public NimGame(int initialStones, int upperBound, NimPlayer playerA, NimPlayer playerB) {
		this.numberOfStones = initialStones;
		this.upperBound = upperBound;
		this.setPlayerA(playerA);
		this.setPlayerB(playerB);
	}

	public int startGame(Scanner scanner) {
		// record initial number
		System.out.println("Initial stone count: " + this.numberOfStones);
		System.out.println("Maximum stone removal: " + this.upperBound);
		System.out.println("Player 1: " + playerA.getGivenName() + " " + playerA.getFamilyName());
		System.out.println("Player 2: " + playerB.getGivenName() + " " + playerB.getFamilyName() + "\n");
		winner = null;

		// game begin
		while (this.numberOfStones != 0) {
			// player1's turn
			int flag = 0;
			while (flag == 0) {
				printStones(this.numberOfStones);
				System.out.println(playerA.getGivenName() + "'s turn - remove how many?\n");
				flag = playerA.removeStone(scanner, this);
			}
			if (this.numberOfStones == 0) {
				this.winner = playerB.getGivenName() + " " + playerB.getFamilyName();
				break;
			}
			// player2's turn
			flag = 0;
			while (flag == 0) {
				printStones(this.numberOfStones);
				System.out.println(playerB.getGivenName() + "'s turn - remove how many?\n");
				flag = playerA.removeStone(scanner, this);
			}
		}

		// determine the winner
		System.out.printf("Game Over\n");
		if (this.numberOfStones == 0 && this.winner == null) {
			System.out.println(playerA.getGivenName() + " " + playerA.getFamilyName() + " wins!");
			return 1;
		} else {
			System.out.println(this.winner + " wins!");
			return 2;
		}

	}

	// output the remaining stones
	public void printStones(int numberOfStones) {
		System.out.printf(numberOfStones + " stones left:");
		for (int i = 1; i <= numberOfStones; i++) {
			System.out.printf(" *");
		}
		System.out.printf("\n");
	}

	/**
	 * @return the upperBound
	 */
	public int getUpperBound() {
		return upperBound;
	}

	/**
	 * @param upperBound
	 *            the upperBound to set
	 */
	public void setUpperBound(int upperBound) {
		this.upperBound = upperBound;
	}

	/**
	 * @return the numberOfStones
	 */
	public int getNumberOfStones() {
		return numberOfStones;
	}

	/**
	 * @param numberOfStones
	 *            the numberOfStones to set
	 */
	public void setNumberOfStones(int numberOfStones) {
		this.numberOfStones = numberOfStones;
	}

	/**
	 * @return the winner
	 */
	public String getWinner() {
		return winner;
	}

	/**
	 * @param winner
	 *            the winner to set
	 */
	public void setWinner(String winner) {
		this.winner = winner;
	}

	/**
	 * @return the playerA
	 */
	public NimPlayer getPlayerA() {
		return playerA;
	}

	/**
	 * @param playerA
	 *            the playerA to set
	 */
	public void setPlayerA(NimPlayer playerA) {
		this.playerA = playerA;
	}

	/**
	 * @return the playerB
	 */
	public NimPlayer getPlayerB() {
		return playerB;
	}

	/**
	 * @param playerB
	 *            the playerB to set
	 */
	public void setPlayerB(NimPlayer playerB) {
		this.playerB = playerB;
	}
}
