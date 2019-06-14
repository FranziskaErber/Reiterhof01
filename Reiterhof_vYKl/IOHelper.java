package oop.horsematching;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Helper class for in and output
 */
public class IOHelper {

	/**
	 * Manages in- and output with the console
	 */
	public static class Console {

		/**
		 * Interrupts the program and waits for an input from the user
		 *
		 * @return given user input
		 */
		public static String getText() {

			Scanner textScanner = new Scanner(System.in);
			return textScanner.nextLine();
		}

		public static String getFilePath() {

			return getFilePath("Error: The given path doesn't point to a valid file.");
		}

		/**
		 * Interrupts the program and waits for a valid file path to be entered
		 * @param noPathError the error printed when the user doesn't enter a valid file path
		 * @return the file path as String
		 */
		public static String getFilePath(String noPathError) {

			String path = getText();
			while (!new File(path).isFile()) {
				Console.sendText(noPathError);
				path = Console.getText();
			}
			return path;
		}

		/**
		 * Interrupts the program and waits for a number input from the user. Only returns after a number is entered.
		 * @return the number entered
		 */
		public static long getNumber() {
			return getNumber(0, 0, null, null);
		}

		/**
		 * Interrupts the program and waits for a number input from the user. Only returns after a number in the given range is entered.
		 * @param min minimum number the user can enter
		 * @param max maximum number the user can enter
		 * @return the entered number
		 */
		public static long getNumber(long min, long max) {
			return getNumber(min, max, null, null);
		}

		/**
		 * Interrupts the program and waits for a number input from the user. Only returns after a number in the given range is entered.
		 * @param min minimum number the user can enter
		 * @param max maximum number the user can enter
		 * @param noNumberError error printed to the user if he enters a non-number
		 * @param outOfRangeError error printed to the user if his number is out of range. Can include up to two %d for min and max
		 * @return the entered number
		 */
		public static long getNumber(long min, long max, String noNumberError, String outOfRangeError) {

			Scanner intScanner = new Scanner(System.in);
			boolean inputAccepted = false;
			long nextLong = 0;
			while (!inputAccepted) {
				try {
					nextLong = Long.parseLong(intScanner.nextLine());
					if (max==0) {
						inputAccepted = true;
					} else {
						if (nextLong<min || nextLong>max) {
							if (outOfRangeError!=null && !outOfRangeError.isEmpty()) {
								sendFormatText(outOfRangeError, min, max);
							} else {
								sendFormatText("Number has to be greater than %d and less than %d.", min, max);
							}
						} else {
							inputAccepted = true;
						}
					}
				} catch (NumberFormatException noNumberException) {
					if (noNumberError!=null && !noNumberError.isEmpty()) {
						sendText(noNumberError);
					} else {
						sendText("Input has to be an integer number.");
					}
				}
			}
			return nextLong;
		}

		/**
		 * @param text prints a given text to the console
		 */
		public static void sendText(String text) {

			System.out.println(text);
		}
		
		/**
		 * @param character prints a given char to the console
		 */
		public static void sendChar(char character) {

			System.out.print(character);
		}

		/**
		 * prints a given formatted text with arguments to the console
		 * @param text formatted text
		 * @param args arguments to be inserted
		 */
		public static void sendFormatText(String text, Object... args) {

			System.out.printf(text+"\n", args);
		}

	}

}