import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	ArrayList<NimPlayer> users = new ArrayList<>();

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
			flag = nimsys.determineCommand(scanner, command);
		}
		System.out.println();
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

	public static void printDollar() {
		System.out.print("\n$");
	}

	public void addPlayer(String userName, String familyName, String givenName) {
		int flag = 0;
		// check the same userName in the array
		for (int i = 0; i < users.size(); i++) {
			if (userName.equals(users.get(i).getUserName()))
				flag = 1;
		}
		if (flag == 0) {
			NimPlayer player = new NimPlayer(userName, givenName, familyName);
			users.add(player);
		} else {
			System.out.println("The player already exists.");
		}

	}

	public void removePlayer(Scanner scanner, String userName) {
		if (userName == null) {
			System.out.println("Are you sure you want to remove all players? (y/n)");
			String flag = scanner.next();
			if (flag.equals("y")) {
				users.clear();
			}

		} else {
			int flag = 1;
			// check the same userName in the array
			for (int i = 0; i < users.size(); i++) {
				if (userName.equals(users.get(i).getUserName())) {
					users.remove(i);
					flag = 0;
				}
			}
			if (flag == 1) {
				System.out.println("The player does not exist.");
			}
		}

	}

	public void editPlayer(String userName, String familyName, String givenName) {
		int flag = 0;
		int i = 0;
		// check the same userName in the array
		for (i = 0; i < users.size(); i++) {
			if (userName.equals(users.get(i).getUserName())) {
				flag = 1;
				break;
			}
		}
		if (flag == 1) {
			users.get(i).setFamilyName(familyName);
			users.get(i).setGivenName(givenName);
		} else {
			System.out.println("The player does not exist.");
		}
	}

	public void resetStats(Scanner scanner, String userName) {
		if (userName.equals(null)) {
			System.out.println("Are you sure you want to reset all player statistics? (y/n)");
			String flag = scanner.next();
			if (flag.equals("y")) {
				for (int i = 0; i < users.size(); i++) {
					users.get(i).setNumberOfPlayed(0);
					users.get(i).setNumberOfWon(0);
				}
			}

		} else {
			int flag = 0;
			int i;
			// check the same userName in the array
			for (i = 0; i < users.size(); i++) {
				if (userName.equals(users.get(i).getUserName())) {
					flag = 1;
					break;
				}
			}
			if (flag == 0) {
				users.get(i).setNumberOfPlayed(0);
				users.get(i).setNumberOfWon(0);
			} else {
				System.out.println("The player already exists.");
			}

		}
	}

	public void displayPlayer(Scanner scanner, String userName) {
		if (userName == null) {
			Comparator<NimPlayer> nameComparator = new Comparator<NimPlayer>() {
				@Override
				public int compare(NimPlayer o1, NimPlayer o2) {
					// TODO Auto-generated method stub
					return o1.getUserName().compareToIgnoreCase(o2.getUserName());
				}
			};
			Collections.sort(users, nameComparator);
			for (int i = 0; i < users.size(); i++) {
				System.out.println(users.get(i).getUserName() + "," + users.get(i).getGivenName() + ","
						+ users.get(i).getFamilyName() + "," + users.get(i).getNumberOfPlayed() + " games,"
						+ users.get(i).getNumberOfWon() + " wins");
			}
		} else {
			int flag = 1;
			// check the same userName in the array
			for (int i = 0; i < users.size(); i++) {
				if (userName.equals(users.get(i).getUserName())) {
					System.out.println(users.get(i).getUserName() + "," + users.get(i).getGivenName() + ","
							+ users.get(i).getFamilyName() + "," + users.get(i).getNumberOfPlayed() + " games,"
							+ users.get(i).getNumberOfWon() + " wins");
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
		Comparator<NimPlayer> descComparator = new Comparator<NimPlayer>() {
			@Override
			public int compare(NimPlayer o1, NimPlayer o2) {
				// TODO Auto-generated method stub
				if (o1.getNumberOfPlayed() != 0 && o2.getNumberOfPlayed() != 0) {
					// arrange won percentage first
					if ((o1.getNumberOfWon() / o1.getNumberOfPlayed()) != (o2.getNumberOfWon()
							/ o2.getNumberOfPlayed())) {
						return o2.getNumberOfWon() / o2.getNumberOfPlayed()
								- o1.getNumberOfWon() / o1.getNumberOfPlayed();
					} else {
						// arrange userName if the won percentages are the same.
						return o1.getUserName().compareToIgnoreCase(o2.getUserName());
					}
				} else if (o1.getNumberOfPlayed() != o2.getNumberOfPlayed()) {
					// arrange user who never play games and the user who played games before
					return o2.getNumberOfPlayed() - o1.getNumberOfPlayed();
				} else {
					// arrange userName if users never played games before
					return o1.getUserName().compareToIgnoreCase(o2.getUserName());
				}
			}
		};
		if (parameter == null || parameter.equals("desc")) {
			Collections.sort(users, descComparator);
			printRankings(users, parameter);
		} else if (parameter.equals("asc")) {
			Comparator<NimPlayer> ascComparator = new Comparator<NimPlayer>() {
				@Override
				public int compare(NimPlayer o1, NimPlayer o2) {
					// TODO Auto-generated method stub
					if (o1.getNumberOfPlayed() != 0 && o2.getNumberOfPlayed() != 0) {
						// arrange won percentage first
						if ((o1.getNumberOfWon() / o1.getNumberOfPlayed()) != (o2.getNumberOfWon()
								/ o2.getNumberOfPlayed())) {
							return o1.getNumberOfWon() / o1.getNumberOfPlayed()
									- o2.getNumberOfWon() / o2.getNumberOfPlayed();
						} else {
							// arrange userName if the won percentages are the same.
							return o1.getUserName().compareToIgnoreCase(o2.getUserName());
						}
					} else if (o1.getNumberOfPlayed() != o2.getNumberOfPlayed()) {
						// arrange user who never play games and the user who played games before
						return o1.getNumberOfPlayed() - o2.getNumberOfPlayed();
					} else {
						// arrange userName if users never played games before
						return o1.getUserName().compareToIgnoreCase(o2.getUserName());
					}

				}

			};
			Collections.sort(users, ascComparator);
			printRankings(users, parameter);
		}
	}

	public void printRankings(ArrayList<NimPlayer> users, String parameter) {
		double wonPercentage = 0;
		for (int i = 0; i < users.size() && i < 10; i++) {
			if (users.get(i).getNumberOfPlayed() != 0) {
				wonPercentage = users.get(i).getNumberOfWon() / users.get(i).getNumberOfPlayed();
			}
			String result = Integer.parseInt(new DecimalFormat("0").format(wonPercentage * 100)) + "%";
			System.out.printf("%-5s" + "| ", result);
			System.out.printf("%02d" + " games | ", users.get(i).getNumberOfPlayed());
			System.out.println(users.get(i).getGivenName() + " " + users.get(i).getFamilyName());
		}
	}

	public void startGame(Scanner scanner, int initialNumber, int upperBound, String userNameA, String userNameB) {
		int playerA = -1;
		int playerB = -1;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUserName().equals(userNameA)) {
				playerA = i;
			} else if (users.get(i).getUserName().equals(userNameB)) {
				playerB = i;
			}
		}
		if (playerA != -1 && playerB != -1) {
			NimGame newGame = new NimGame(initialNumber, upperBound, users.get(playerA), users.get(playerB));
			System.out.println();
			int winner = newGame.startGame(scanner);
			int temp = 0;
			temp = users.get(playerA).getNumberOfPlayed();
			users.get(playerA).setNumberOfPlayed(temp + 1);
			temp = users.get(playerB).getNumberOfPlayed();
			users.get(playerB).setNumberOfPlayed(temp + 1);
			if (winner == 1) {
				temp = users.get(playerA).getNumberOfWon();
				users.get(playerA).setNumberOfWon(temp + 1);
			} else {
				temp = users.get(playerB).getNumberOfWon();
				users.get(playerB).setNumberOfWon(temp + 1);
			}

		} else {
			System.out.println("One of the players does not exist.");
		}
	}
}
