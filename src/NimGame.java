import java.util.Scanner;

/**
 * 
 */

/**
 * @author ZIYANG XIE StudentID 870523 9 Apr. 2018-4:35:44 pm
 */
public class NimGame {
	int upperBound;
	int numberOfStones;
	private NimPlayer playerA;
	private NimPlayer playerB;
	String winner;

	public NimGame(int initialStones, int upperBound, NimPlayer playerA, NimPlayer playerB) {
		this.numberOfStones = initialStones;
		this.upperBound = upperBound;
		this.setPlayerA(playerA);
		this.setPlayerB(playerB);
	}

	public int startGame(Scanner scanner) {
		NimGame nimGame = new NimGame(numberOfStones, upperBound, playerA, playerB);
		// record initial number
		System.out.println("Initial stone count: " + nimGame.numberOfStones);
		System.out.println("Maximum stone removal: " + nimGame.upperBound);
		System.out.println("Player 1: " + playerA.getGivenName() + " " + playerA.getFamilyName());
		System.out.println("Player 2: " + playerB.getGivenName() + " " + playerB.getFamilyName() + "\n");
		winner = null;

		// game begin
		while (nimGame.numberOfStones != 0) {
			// player1's turn
			int flag = 0;
			while (flag == 0) {
				printStones(nimGame.numberOfStones);
				System.out.println(playerA.getGivenName() + "'s turn - remove how many?\n");
				flag = playerA.removeStone(scanner, nimGame);
			}
			if (nimGame.numberOfStones == 0) {
				nimGame.winner = playerB.getGivenName() + " " + playerB.getFamilyName();
				break;
			}
			// player2's turn
			flag = 0;
			while (flag == 0) {
				printStones(nimGame.numberOfStones);
				System.out.println(playerB.getGivenName() + "'s turn - remove how many?\n");
				flag = playerA.removeStone(scanner, nimGame);
			}
		}

		// determine the winner
		System.out.printf("Game Over\n");
		if (nimGame.numberOfStones == 0 && nimGame.winner == null) {
			System.out.println(playerA.getGivenName() + " " + playerA.getFamilyName() + " wins!");
			return 1;
		} else {
			System.out.println(nimGame.winner + " wins!");
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
