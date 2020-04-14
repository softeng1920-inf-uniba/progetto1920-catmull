package it.uniba.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * The main class for the project. It must be customized to meet the project
 * assignment specifications.
 *
 * <b>DO NOT RENAME</b>
 */
public final class AppMain {

	/**
	 * Private constructor. Change if needed.
	 */
	private AppMain() {

	}

	/**
	 * * This is the main entry of the application.
	 *
	 * @param args The command-line arguments.
	 * @throws UnsupportedEncodingException
	 */
	public static void main(final String[] args) {
		Controller c = new Controller();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			while (true) {
				String menu = br.readLine();
				switch (menu) {
				case "":
					break;
				default:

				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
