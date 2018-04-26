import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author ZIYANG XIE StudentID 870523 9 Apr. 2018-5:14:53 pm
 */
public class Nimsys {
	// store users in the system
	NimPlayer[] users = new NimPlayer[100];
	// record the index of array users
	int cursor = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// determine to scan next command
		int flag = 1;
		Scanner scanner = new Scanner(System.in);
		Nimsys nimsys = new Nimsys();
		System.out.println("Welcome to Nim");
		printDollar();
		while (flag == 1) {
			String command = scanner.next();
			// if command is exit, flag will be 0.Otherwise, it will be 1.
			flag = nimsys.determineCommand(scanner, command);
		}
		System.out.println();
		 System.exit(0);
	}

	public int determineCommand(Scanner scanner, String command) {
		if (command.equals("addplayer")) {
			String parameter = scanner.next();
			String[] parameters = parameter.split(",");
			addPlayer(parameters[0], parameters[1], parameters[2]);
		} else if (command.equals("removeplayer")) {
			String parameter = scanner.nextLine();
			if (!parameter.equals("")) {
				removePlayer(scanner, parameter.trim());
			} else {
				removePlayer(scanner, null);
			}
		} else if (command.equals("editplayer")) {
			String parameter = scanner.next();
			String[] parameters = parameter.split(",");
			editPlayer(parameters[0], parameters[1], parameters[2]);
		} else if (command.equals("resetstats")) {
			String parameter = scanner.nextLine();
			if (!parameter.equals("")) {
				resetStats(scanner, parameter.trim());
			} else {
				resetStats(scanner, null);
			}
		} else if (command.equals("displayplayer")) {
			String parameter = scanner.nextLine();
			if (!parameter.equals("")) {
				displayPlayer(scanner, parameter.trim());
			} else {
				displayPlayer(scanner, null);
			}
		} else if (command.equals("rankings")) {
			String parameter = scanner.nextLine();
			if (!parameter.equals("")) {
				rankings(parameter.trim());
			} else {
				rankings(null);
			}
		} else if (command.equals("startgame")) {
			String parameter = scanner.next();
			String[] parameters = parameter.split(",");
			startGame(scanner, Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1]), parameters[2],
					parameters[3]);
		} else if (command.equals("exit")) {
			return 0;
		}
		printDollar();
		return 1;
	}

	// out the dollar symbol
	public static void printDollar() {
		System.out.print("\n$");
	}

	// insert a new user in to array users
	public void addPlayer(String userName, String familyName, String givenName) {
		// if the number of users is maximum
		if (cursor == 99) {
			System.out.println("The player already full.");
		} else {
			int flag = checkUserName(users, userName);
			// if flag is 0, it does not exist the same userName in the array
			if (flag == -1) {
				NimPlayer player = new NimPlayer(userName, givenName, familyName);
				users[cursor] = player;
				cursor++;
			} else {
				System.out.println("The player already exists.");
			}
		}
	}

	// remove the user from array users
	public void removePlayer(Scanner scanner, String userName) {
		// if userName is null, it will delete all users.
		if (userName == null) {
			System.out.println("Are you sure you want to remove all players? (y/n)");
			String flag = scanner.next();
			if (flag.equals("y")) {
				for (int i = 0; i < users.length; i++)
					users[i] = null;
				cursor = 0;

			}
		} else {
			int flag = checkUserName(users, userName);
			if (flag == -1) {
				System.out.println("The player does not exist.");
			} else {
				// rearrange the array
				for (int i = flag + 1; i <= cursor; i++) {
					users[i - 1] = users[i];
				}
				cursor--;
			}
		}

	}

	// modify user information
	public void editPlayer(String userName, String familyName, String givenName) {
		int flag = checkUserName(users, userName);
		if (flag != -1) {
			users[flag].setFamilyName(familyName);
			users[flag].setGivenName(givenName);
		} else {
			System.out.println("The player does not exist.");
		}
	}

	public void resetStats(Scanner scanner, String userName) {
		// if userName is null, it will reset all users' statistics.
		if (userName.equals(null)) {
			System.out.println("Are you sure you want to reset all player statistics? (y/n)");
			String flag = scanner.next();
			if (flag.equals("y")) {
				for (int i = 0; i <= cursor; i++) {
					users[i].setNumberOfPlayed(0);
					users[i].setNumberOfWon(0);
				}
			}
		} else {
			int flag = checkUserName(users, userName);
			if (flag != -1) {
				users[flag].setNumberOfPlayed(0);
				users[flag].setNumberOfWon(0);
			} else {
				System.out.println("The player does not exists.");
			}

		}
	}

	public void displayPlayer(Scanner scanner, String userName) {
		// if userName is null, it will display all users.
		if (userName == null) {
			// create a comparator which arrange the userName in alphabetical order
			Comparator<NimPlayer> nameComparator = new Comparator<NimPlayer>() {
				@Override
				public int compare(NimPlayer o1, NimPlayer o2) {
					// TODO Auto-generated method stub
					if (o1 != null && o2 != null)
						return o1.getUserName().compareTo(o2.getUserName());
					else
						return 0;
				}
			};
			Arrays.sort(users, nameComparator);
			// output the all users'information
			for (int i = 0; i <= cursor; i++) {
				if (users[i] != null)
					System.out.println(users[i].getUserName() + "," + users[i].getGivenName() + ","
							+ users[i].getFamilyName() + "," + users[i].getNumberOfPlayed() + " games,"
							+ users[i].getNumberOfWon() + " wins");
			}
		} else {
			int flag = 1;
			// output the certain user information
			for (int i = 0; i <= cursor; i++) {
				if (users[i] != null && userName.equals(users[i].getUserName())) {
					System.out.println(users[i].getUserName() + "," + users[i].getGivenName() + ","
							+ users[i].getFamilyName() + "," + users[i].getNumberOfPlayed() + " games,"
							+ users[i].getNumberOfWon() + " wins");
					flag = 0;
					break;
				}
			}
			if (flag == 1) {
				System.out.println("The player does not exist.");
			}
		}
	}

	public void rankings(String parameter) {
		// create a comparator which arrange the win rate in descending order
		Comparator<NimPlayer> descComparator = new Comparator<NimPlayer>() {
			@Override
			public int compare(NimPlayer o1, NimPlayer o2) {
				if (o1 != null && o2 != null) {
					// both users have played some games
					if (o1.getNumberOfPlayed() != 0 && o2.getNumberOfPlayed() != 0) {
						// arrange won percentage first
						if ((o1.getNumberOfWon() / o1.getNumberOfPlayed()) != (o2.getNumberOfWon()
								/ o2.getNumberOfPlayed())) {
							return o2.getNumberOfWon() / o2.getNumberOfPlayed()
									- o1.getNumberOfWon() / o1.getNumberOfPlayed();
						} else {
							// arrange userName if the won percentages are the same.
							return o1.getUserName().compareTo(o2.getUserName());
						}

					} else if (o1.getNumberOfPlayed() != o2.getNumberOfPlayed()) {
						// list the user who has played games first
						return o2.getNumberOfPlayed() - o1.getNumberOfPlayed();
					} else {
						// neither users has played games, arrange their userName in alphabetical order
						return o1.getUserName().compareTo(o2.getUserName());
					}
				} else
					return 0;
			}
		};

		if (parameter == null || parameter.equals("desc")) {
			Arrays.sort(users, descComparator);
			printRankings(users, parameter);
		} else if (parameter.equals("asc")) {
			// create a comparator which arrange the win rate in ascending order
			Comparator<NimPlayer> ascComparator = new Comparator<NimPlayer>() {
				@Override
				public int compare(NimPlayer o1, NimPlayer o2) {
					if (o1 != null && o2 != null) {
						if (o1.getNumberOfPlayed() != 0 && o2.getNumberOfPlayed() != 0) {
							// arrange won percentage first
							if ((o1.getNumberOfWon() / o1.getNumberOfPlayed()) != (o2.getNumberOfWon()
									/ o2.getNumberOfPlayed())) {
								return o1.getNumberOfWon() / o1.getNumberOfPlayed()
										- o2.getNumberOfWon() / o2.getNumberOfPlayed();
							} else {
								// arrange userName if the won percentages are the same.
								return o1.getUserName().compareTo(o2.getUserName());
							}
						} else if (o1.getNumberOfPlayed() != o2.getNumberOfPlayed()) {
							// arrange user who never play games and the user who played games before
							return o1.getNumberOfPlayed() - o2.getNumberOfPlayed();
						} else {
							// arrange userName if users never played games before
							return o1.getUserName().compareTo(o2.getUserName());
						}
					} else
						return 0;
				}

			};
			Arrays.sort(users, ascComparator);
			printRankings(users, parameter);
		}
	}

	// output the ranking list
	public void printRankings(NimPlayer[] users, String parameter) {
		double wonPercentage = 0;
		for (int i = 0; i <= cursor && i < 10; i++) {
			if (users[i] != null) {
				if (users[i].getNumberOfPlayed() != 0) {
					wonPercentage = users[i].getNumberOfWon() / users[i].getNumberOfPlayed();
				}
				String result = Integer.parseInt(new DecimalFormat("0").format(wonPercentage * 100)) + "%";
				System.out.printf("%-5s" + "| ", result);
				System.out.printf("%02d" + " games | ", users[i].getNumberOfPlayed());
				System.out.println(users[i].getGivenName() + " " + users[i].getFamilyName());
			}
		}
	}

	public void startGame(Scanner scanner, int initialNumber, int upperBound, String userNameA, String userNameB) {
		int playerA = -1;
		int playerB = -1;
		for (int i = 0; i <= cursor; i++) {
			if (users[i] != null && users[i].getUserName().equals(userNameA)) {
				playerA = i;
			} else if (users[i] != null && users[i].getUserName().equals(userNameB)) {
				playerB = i;
			}
		}
		if (playerA != -1 && playerB != -1) {
			NimGame newGame = new NimGame(initialNumber, upperBound, users[playerA], users[playerB]);
			System.out.println();
			int winner = newGame.startGame(scanner);
			// record the initial number of played or number of won
			int temp = 0;
			temp = users[playerA].getNumberOfPlayed();
			users[playerA].setNumberOfPlayed(temp + 1);
			temp = users[playerB].getNumberOfPlayed();
			users[playerB].setNumberOfPlayed(temp + 1);
			if (winner == 1) {
				temp = users[playerA].getNumberOfWon();
				users[playerA].setNumberOfWon(temp + 1);
			} else {
				temp = users[playerB].getNumberOfWon();
				users[playerB].setNumberOfWon(temp + 1);
			}
		} else {
			System.out.println("One of the players does not exist.");
		}
	}

	public static int checkUserName(NimPlayer[] users, String userName) {
		int flag = -1;
		// check the same userName in the array
		for (int i = 0; i < users.length; i++) {
			if (users[i] != null && userName.equals(users[i].getUserName())) {
				flag = i;
				break;
			}
		}
		return flag;
	}
}
