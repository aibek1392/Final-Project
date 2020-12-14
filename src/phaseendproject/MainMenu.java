package phaseendproject;

import java.io.IOException;
import java.util.*;

import customExceptions.DuplicaterFileException;
import customExceptions.FileNotInDirectoryException;
import customExceptions.InvalidInputException;

public class MainMenu {
	public void showMainMenu() throws InvalidInputException {
		Scanner scan = new Scanner(System.in);
		System.out.println("****************************************************");
		System.out.println("\t Welcome to Company Locker Pvt Ltd...\n");
		System.out.println("\t\t developed by Aibek Ozhorov");
		System.out.println("\t\t contact Email:  aibek.ozhorov@hcl.com");
		System.out.println("****************************************************");
		System.out.println("Would you like to enter the application y/n?");
		System.out.println("\nYour Selection");
		String option = scan.nextLine().toLowerCase();
		try {
			if (option.equals("y")) {
				System.out.println("please enter your choise");
				showSubMenu();
			} else if (option.equals("n")) {
				System.out.println(
						"\n\n****************Thank you for your time visiting Company Locker Pvt Ltd********************************");
			} else {

				throw new InvalidInputException("Invalid input");
			}
		} catch (InvalidInputException e) {
			System.err.println(e.getMessage());
			showMainMenu();
		}
	}

	public void showSubMenu() throws InvalidInputException {
		Scanner scan = new Scanner(System.in);
		int counter = 0;
		while (counter == 0) {
			System.out.println("****************************************************");
			System.out.println("Please press 1 to show all files");
			System.out.println("Please press 2 to add a new file");
			System.out.println("Please press 3 to search the existing file");
			System.out.println("Please press 4 to delete the existing file");

			System.out.println("Please press 5 to exit the program");

			System.out.println("****************************************************");
			System.out.println("\nYour Entry:");
			String choice = scan.nextLine();
			FileMenu myFile = new FileMenu();
			if (choice.equals("1")) {
				myFile.showAllFiles();
			} else if (choice.equals("2")) {
				try {
					myFile.addFile();
				} catch (DuplicaterFileException e) {
					System.out.println(e.getMessage());
				} catch (IOException e) {
					System.out.println("Enter the name of the file");
				}
			} else if(choice.equals("3")){
				try {
					myFile.searchFile();
				} catch (FileNotInDirectoryException e) {
					System.out.println(e.getMessage());
				}
			}
			else if (choice.equals("4")) {
				try {
					myFile.removeFile();
				} catch (FileNotInDirectoryException e) {
					System.out.println(e.getMessage());
				}
			} else if (choice.equals("5")) {
				counter++;
			} else {
				throw new InvalidInputException("Please select a valid option from 1-4");
			}
		}
		try {
			showMainMenu();
		} catch (InvalidInputException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

	}
	
	public void closeApp(){
		System.out.println("Closing app... /nThank you");
	}

	public static void main(String[] args) throws InvalidInputException {
		MainMenu menu = new MainMenu();

		menu.showMainMenu();

		// TODO Auto-generated catch block

	}
}
