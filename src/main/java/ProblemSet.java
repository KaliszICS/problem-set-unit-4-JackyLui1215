/**
	* Problem set: Unit 4
	* Author: Jacky Lui
	* Date Created: April 16, 2026
	* Date Last Modified: April 21, 2026
	*/

import java.util.Random;
import java.util.Scanner;

public class ProblemSet {

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("----------------------------------------");
		System.out.println(" | Welcome to High-Low guessing Game! |");
		System.out.println("---------------------------------------- \n");

		int rounds = 0;

		while (rounds <= 0 ) { //loops until a valid number of rounds is entered
			System.out.print("Input a number of rounds to play: ");
			if (!scanner.hasNextInt()) {
				System.out.println("Error. Value must be a number.");
				scanner.nextLine();
			}
			else {
				rounds = scanner.nextInt();
				scanner.nextLine();

				if (rounds <= 0) {
					System.out.println("Error. Value must be a number greater than 0.");
				}
			}
		}

		String maxOrMinNum;
		String firstNum;
		String secondNum;
		boolean valid = false;

		System.out.println("\nWhat range would you like to play between? \n");
		do { //loops until a valid ranged is entered
			System.out.print("Maximum Value and Minimum Value (#-#): ");
			maxOrMinNum=scanner.nextLine();
			maxOrMinNum = maxOrMinNum.replace(" ", "");
			int dash = maxOrMinNum.indexOf("-", 1);
			if (dash != -1) {
				firstNum = maxOrMinNum.substring(0, dash); //seperates the first number
				secondNum = maxOrMinNum.substring((dash + 1)); //seperates the second number
			}
			else {
				firstNum = "";
				secondNum = "";
			}
			valid = (intChecker(firstNum) && intChecker(secondNum)); //checks if range is valid.
			if (!valid) {
				System.out.println("Error. Invalid range input.");
			}
			if (valid) {
				int num1 = Integer.parseInt(firstNum);
				int num2 = Integer.parseInt(secondNum);

				int maxTemp = Math.max(num1, num2);
				int minTemp = Math.min(num1, num2);

				if (maxTemp - minTemp + 1 < 3) {
					valid = false;
					System.out.println("Error. Range must include at least 3 numbers.");
				}
			}
		} while (!valid);
		int num1 = Integer.parseInt(firstNum);
		int num2 = Integer.parseInt(secondNum);
		int max = Math.max(num1, num2);
		int min = Math.min(num1, num2);
		int sum = max + min;
		int evenLow = sum / 2;
		int evenHigh = (sum + 1) / 2;
		int countRounds = 0;
		int guessNumber;
		int score = 0;
		do {
			guessNumber = randomNumberGenerator(min, max); //generates the random number

			System.out.println("\nRound " + (countRounds + 1) + "\n"); //prints the rounds
			System.out.println(menu(max, min, evenLow, evenHigh)); //prompts user with the menu
			int option = -1;
			while (option < 1 || option > 3 ) { //ensures option chose is between 1-3
				System.out.print("Choose an option (1-3): ");
				if (scanner.hasNextInt()) {
					option = scanner.nextInt();
					scanner.nextLine();
				}
				if (option < 1 || option > 3) {
					System.out.println("Choose a valid option. \n");
					option = -1;
				}
			}
			scanner.nextLine();
			boolean correct = false;
			if (option == 1) { //High
				if (guessNumber > evenHigh) {
					score++;
					correct = true;
				}
			}
			else if (option == 2) { //Low
				if (guessNumber < evenLow) {
					score++;
					correct = true;
				}
			}
			else if (option == 3) { //Even
				if (guessNumber == evenLow || guessNumber == evenHigh) {
					score++;
					correct = true;
				}
			}
			if (!correct) {
				System.out.println("\nIncorrect. " + "The number was " + guessNumber + ".");
			}
			else {
				System.out.println("\nCorrect. " + "The number was " + guessNumber + ".");
			}
			System.out.println("Current score: " + score);
			countRounds++;
		} while (countRounds < rounds);
		System.out.println("Total score: " + score);
		if (score >= (rounds + 1) / 2) {
			System.out.println("Congratulations you got " + score + " out of " + rounds + " rounds right!"); //won the game
		}
		else {
			System.out.println("Better luck next time. " + "You got " + score + " out of " + rounds + ". You failed to guess half the rounds correctly."); //lost the game
		}
		scanner.close();
	}


	public static String menu (int high, int low, int evenLow, int evenHigh) {  //promopt for the game
		if (evenLow == evenHigh) {
			return "Please select:\n" + "1. High " + "(" + (evenLow + 1) +" to " + high + ")\n" + "2. Low " + "(" + low + " to " + (evenLow - 1) + ")\n" + "3. Even " + "(" + evenLow + ")";
		}
		else {
			return "Please select:\n" + "1. High " + "(" + (evenHigh + 1) +" to " + high + ")\n" + "2. Low " + "(" + low + " to " + (evenLow - 1) + ")\n" + "3. Even " + "(" + evenLow + "," + evenHigh + ")";
		}
	}

	public static int randomNumberGenerator (int min, int max) { //Generates a random number based on the range chosen
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}

	public static boolean intChecker(String number) { //validates if a integer is actually an integer
		if (number.length() == 0) {
			return false;
		}
		int i = 0;
		if(number.charAt(0) == '-') {
			i=1;
		}
		for (; i < number.length(); i++) {
			if (!(number.charAt(i) >= 48 && number.charAt(i) <= 57)) { //ASCII values of 0-9
				return false;
			}
		}
		return true;
	}
}