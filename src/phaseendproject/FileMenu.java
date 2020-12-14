package phaseendproject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


import customExceptions.DuplicaterFileException;
import customExceptions.FileNotInDirectoryException;
import customExceptions.InvalidInputException;

public class FileMenu {
	public static List<String> fileList = new ArrayList<>();
	
	public void syncLists() {
		List<String> pathnames = new ArrayList<>();
		File f = new File("./");
		pathnames = Arrays.asList(f.list());
		pathnames = pathnames.stream().filter(path -> path.endsWith(".txt")).collect(Collectors.toList());
		fileList.clear();
		fileList.addAll(pathnames);
	}
	
	public void showAllFiles() {
		System.out.println("*******************************************************************");
		syncLists();
		if (fileList.isEmpty())
			System.out.println("The current directory is empty");
		Collections.sort(fileList, String.CASE_INSENSITIVE_ORDER);
		System.out.println("###################################################################");
		if (!fileList.isEmpty()) System.out.println("Your files:");
		fileList.forEach(file -> System.out.println(file));
	}
	
	public void addFile() throws DuplicaterFileException, IOException{
		Scanner scanner = new Scanner(System.in);
		System.out.println("_____________________________________");
		System.out.println("Please enter the name of .txt file you would like to add:");
		
		String file = scanner.nextLine();
		
		String regex = ".*\\.txt$";
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(file);
		boolean found = matcher.matches();
		try {
			if(!found) {
				throw new InvalidInputException("\n\t Your file name must end with .txt");
			}
		}catch(InvalidInputException e) {
			System.err.println(e.getMessage());
		}
		while(!found) {
			System.out.println("\n\tPlease enter another file ending with .txt");
			file = scanner.nextLine();
			matcher = p.matcher(file);
			found = matcher.matches();
			}
		File myFile = new File(file);
		if(myFile.createNewFile()) {
			fileList.add(file);
			System.out.println("\n\tThe file " + file + " has been successfully added" );
		}else {
			throw new DuplicaterFileException("Sorry, This file already exist");
		}
	}
	

	void searchFile()throws FileNotInDirectoryException {
		System.out.println("Enter the name of the file you need to search:\t");
		Scanner scan = new Scanner(System.in);
		String fileName = scan.nextLine();
		
		System.out.println(fileList.contains(fileName) ? "We have this file "+ fileName : "The file doesn't exist!");

	}
	
	
	public void removeFile() throws FileNotInDirectoryException {
		syncLists();
	    System.out.println("_______________________________________");
	    Scanner scan = new Scanner(System.in);
	    System.out.println("Please enter the name of the text file you want to delete");
	    String fileName = scan.nextLine();
	    
	    String regex = ".*\\.txt$";
	    Pattern p = Pattern.compile(regex);
	    Matcher matcher = p.matcher(fileName);
	    boolean exist = matcher.matches();
	    
	    try {
	    	if(!exist) {
	    		throw new InvalidInputException("File name must end with .txt");
	    	}
	    }catch(InvalidInputException e) {
	    	System.err.println(e.getMessage());
	    	while(!exist) {
	    		System.out.println("Please enter a file name ending with .txt");
	    		fileName = scan.nextLine();
	    		matcher = p.matcher(fileName);
	    		exist = matcher.matches();
	    	}
	    }
	    
	    File myFile = new File(fileName);
	    
	    if(myFile.delete()) {
	    	syncLists();
	    	System.out.println(fileName + " was deleted");
	    }else {
	    	throw new FileNotInDirectoryException("The file does not exist");
	    }
	    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		
	}
	
	


}
