import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class RunThisClass {
	public static void main(String[] args) {		
		Scanner sc = new Scanner(System.in);
		System.out.println("You must choose the input_string dataset to read from");
		System.out.println("Press 1 to select the dataset with bad time input_string.");
		System.out.println("Press 2 to select the dataset with bad day input_string.");
		System.out.println("Press 3 to select the dataset with bad delimiter input_string.");
		System.out.println("Press 4 to select the dataset with no input errors.");
		System.out.print("Select your dataset: ");
		int dataset = sc.nextInt();
		while (dataset != 1 && dataset != 2 && dataset != 3 && dataset != 4) {
			System.out.print("Bad input_string, try again: ");
			dataset = sc.nextInt();
			System.out.println("");
		}
		sc.close();
		String input_string = "";
		File file;
		StringBuilder fileContents = new StringBuilder();
		Scanner fileScanner;
		// code for reading in the files derives from a stackoverflow post
		// https://stackoverflow.com/questions/326390/how-do-i-create-a-java-string-from-the-contents-of-a-file
		
		if (dataset == 1) {
			System.out.println("Reading in dataset with bad time input_string");
//			System.out.println(System.getProperty("user.dir"));
			file = new File("src/BadTimeInput.txt");
			try {
				fileScanner = new Scanner(file);
				while(fileScanner.hasNextLine()) {
					fileContents.append(fileScanner.nextLine());
				}
				fileScanner.close();
				input_string = fileContents.toString();
//				System.out.println(input_string.toString());
			}
			catch(FileNotFoundException e) {
				System.out.println("File not found likely because you are not running as Eclipse project or have directories changed");
				e.printStackTrace();
				System.out.println("Pasting in the dataset and continuing with the program");
				input_string = "(Jon<M[3000]><W[100000]><F[1100]>)(Gregor<M[170000000]><W[2000]><F[1700]>)(Arya<M[1500]><W[2100]><F[1200]>)(Sansa<M[1800]><W[1600]><H[0900]>)";
			}
		}
		else if (dataset == 2) {
			System.out.println("Reading in dataset with bad day input_string");
			file = new File("src/BadDayInput.txt");
			try {
				fileScanner = new Scanner(file);
				while(fileScanner.hasNextLine()) {
					fileContents.append(fileScanner.nextLine());
				}
				fileScanner.close();
				input_string = fileContents.toString();
			}
			catch(FileNotFoundException e) {
				System.out.println("File not found likely because you are not running as Eclipse project or have directories changed");
				e.printStackTrace();
				System.out.println("Pasting in the dataset and continuing with the program");
				input_string = "(Stewie<M[0900]><W[1400]><F[0900]>)(Lois<T[0900][1600]><H[1800]>)(Peter<T[2100]><M[1900]><G[2100]>)(Meg<T[2000]><H[2000]><F[1600]>)(Quagmire<T[2000][1400]><M[1300]>)(Brian<H[0900][1200]><F[2000]>)(Chris<T[0900]<W[2000]><H[1100]>)Joe<T[2100]><M[0900]]><F[1600]>)(Cleveland<T[0900][2100]><F[0900]>)(Bonnie<W1800]><<H[0900]><F[[0900]>)";
			}
		}
		else if (dataset == 3) {
			System.out.println("Reading in dataset with bad delimiter input_string");
			file = new File("src/BadDelimiterInput.txt");
			try {
				fileScanner = new Scanner(file);
				while(fileScanner.hasNextLine()) {
					fileContents.append(fileScanner.nextLine());
				}
				fileScanner.close();
				input_string = fileContents.toString();
			}
			catch(FileNotFoundException e) {
				System.out.println("File not found likely because you are not running as Eclipse project or have directories changed");
				e.printStackTrace();
				System.out.println("Pasting in the dataset and continuing with the program");
				input_string = "(BadDelimiterSoon<M[0900]><T[1400]><H[0900]>)(H<T[0900][1600]><W[1800)>)";
			}
		}
		else if (dataset == 4) {
			System.out.println("Reading in dataset with no errors");
			file = new File("src/GoodInput.txt");
			try {
				fileScanner = new Scanner(file);
				while(fileScanner.hasNextLine()) {
					fileContents.append(fileScanner.nextLine());
				}
				fileScanner.close();
				input_string = fileContents.toString();
			}
			catch(FileNotFoundException e) {
				System.out.println("File not found likely because you are not running as Eclipse project or have directories changed");
				e.printStackTrace();
				System.out.println("Pasting in the dataset and continuing with the program");	
				input_string = "(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Jesus<M[0900]><W[1400]><F[0900]>)(Stewie<M[0900]><W[1400]><F[0900]>)(Stewie<M[0900]><W[1400]><F[0900]>)(Stewie<M[0900]><W[1400]><F[0900]>)(Stewie<M[0900]><W[1400]><F[0900]>)(Stewie<M[0900]><W[1400]><F[0900]>)(Stewie<M[0900]><W[1400]><F[0900]>)(Stewie<M[0900]><W[1400]><F[0900]>)(Stewie<M[0900]><W[1400]><F[0900]>)(Stewie<M[0900]><W[1400]><F[0900]>)(Stewie<M[0900]><W[1400]><F[0900]>)(Stewie<M[0900]><W[1400]><F[0900]>)(Stewie<M[0900]><W[1400]><F[0900]>)(Stewie<M[0900]><W[1400]><F[0900]>)(Stewie<M[0900]><W[1400]><F[0900]>)(Stewie<M[0900]><W[1400]><F[0900]>)(Stewie<M[0900]><W[1400]><F[0900]>)(Stewie<M[0900]><W[1400]><F[0900]>)(Meg<T[2000]><H[2000]><F[1600]>)(Meg<T[2000]><H[2000]><F[1600]>)(Meg<T[2000]><H[2000]><F[1600]>)(Meg<T[2000]><H[2000]><F[1600]>)(Meg<T[2000]><H[2000]><F[1600]>)(Meg<T[2000]><H[2000]><F[1600]>)(Meg<T[2000]><H[2000]><F[1600]>)(Meg<T[2000]><H[2000]><F[1600]>)(Meg<T[2000]><H[2000]><F[1600]>)(Meg<T[2000]><H[2000]><F[1600]>)(Meg<T[2000]><H[2000]><F[1600]>)(Meg<T[2000]><H[2000]><F[1600]>)(Meg<T[2000]><H[2000]><F[1600]>)(Meg<T[2000]><H[2000]><F[1600]>)(Meg<T[2000]><H[2000]><F[1600]>)(Meg<T[2000]><H[2000]><F[1600]>)(Meg<T[2000]><H[2000]><F[1600]>)(Meg<T[2000]><H[2000]><F[1600]>)(Meg<T[2000]><H[2000]><F[1600]>)(Meg<T[2000]><H[2000]><F[1600]>)(Meg<T[2000]><H[2000]><F[1600]>)(Meg<T[2000]><H[2000]><F[1600]>)";
			}
		}
//		input_string = "(Jon<M[3000]><W[100000]><F[1100]>)(Gregor<M[170000000]><W[2000]><F[1700]>)(Arya<M[1500]><W[2100]><F[1200]>)(Sansa<M[1800]><W[1600]><H[0900]>)";
		
		StringBuilder[] studentName = new StringBuilder[100];
		final String OPENING = "(<[";
		final String CLOSING = ")>]";
		final String DAY = "MTWHF";
		final String[] TIME = {"0900", "1000", "1100", "1200", "1300", "1400", "1500", "1600", "1700", "1800", "1900", "2000", "2100"};
		for (int i = 0; i < studentName.length; i++) {
			studentName[i] = new StringBuilder();
		}
		StringBuilder[][][] studentDayHour = new StringBuilder[studentName.length][DAY.length()][TIME.length];
		for (int i = 0; i < studentDayHour.length; i++) {
			for (int j = 0; j < studentDayHour[0].length; j++){
				for (int k = 0; k < studentDayHour[0][0].length; k++) {
					studentDayHour[i][j][k] = new StringBuilder();
				}
			}
		}
		int[][] scheduleArray = new int[DAY.length()][TIME.length];
		int studentIndex = 0;
		int hourIndex = 0;
		LinkedStack<Character> stringChecker = new LinkedStack<Character>();
		
		int openIndex = 0;
		int closeIndex = 0;
		int currentDayIndex = 0;
		boolean verbose = false;
		boolean match = false;
		try {
			for (char c: input_string.toCharArray()) {
				// check open index
				if (OPENING.indexOf(c) != -1) {
					openIndex = OPENING.indexOf(c);
					stringChecker.push(c);
					continue;
				}
				// check close index
				if (CLOSING.indexOf(c) != -1) {
					closeIndex = CLOSING.indexOf(c);
					// check correct close index
					if (closeIndex != -1) {
						if (closeIndex != OPENING.indexOf(stringChecker.pop())) {
							studentIndex++;
							throw new IncorrectInputException("wrong delimiter: " + c);
						}
						else if (closeIndex == 0) {
							studentIndex++;
							hourIndex = 0;
							continue;
						}
						else if (closeIndex == 1) {
							continue;
						}
						else if (closeIndex == 2) {
							hourIndex++;
							continue;
						}
					}
				}
				
				// add to student name
				if (openIndex == 0) {
					studentName[studentIndex].append(c);
					continue;
				}
				// change day index
				if (openIndex == 1) {
					currentDayIndex = DAY.indexOf(c);
					if (currentDayIndex == -1) {
						throw new IncorrectInputException("invalid day input_string: " + c);
					}
					continue;
				}
				// add to hours
				if (openIndex == 2) {
					studentDayHour[studentIndex][currentDayIndex][hourIndex].append(c);
					continue;
				}
			}
		}
		catch(IncorrectInputException e) {
			System.out.println(e.toString());
			System.out.println("Now printing out what data is already processed");
		}
		finally {
			// convert to matrix
			for (int i = 0; i < studentIndex; i++) {
				for (int j = 0; j < DAY.length(); j++) {
					for (int k = 0; k < TIME.length; k++) {
						// skip blanks
						match = false;
						if (studentDayHour[i][j][k].toString().isEmpty()) {
							continue;
						}
						else {
							// match the student's hour input_string to the actual time
							for (int l = 0; l < TIME.length; l++) {
								if (studentDayHour[i][j][k].toString().equals(TIME[l])) {
									if (verbose) {
										System.out.println("Student " + studentName[i].toString() 
												+ " selected day " + DAY.charAt(j) 
												+ " and time " + TIME[l]);
									}
									match = true;
									scheduleArray[j][l]++;
									continue;
								}
							}
							if (!match) {
								// this is only printed out if no match
								System.out.println("Student " + studentName[i].toString() 
										+ " selected day " + DAY.charAt(j) 
										+ " and time " + studentDayHour[i][j][k].toString()
									    + " which did not match any existing time");
								System.out.println("That input has been skipped over.");
								System.out.println("Array will continue to fill out.");
							}
						}
					}
				}
			}
			// print out matrix
			int[] bestOfficeHour = new int[2];
			System.out.print(String.format("%5s", ""));
			for (int i = 0; i < scheduleArray[0].length; i++) {
				System.out.print(String.format("%5s", TIME[i]));
			}
			System.out.println("");
			for (int i = 0; i < scheduleArray.length; i++) {
				System.out.print(String.format("%5s", DAY.charAt(i)));
				for (int j = 0; j < scheduleArray[i].length; j++) {
					System.out.print(String.format("%5s", scheduleArray[i][j]));
					if (scheduleArray[bestOfficeHour[0]][bestOfficeHour[1]] < scheduleArray[i][j]) {
						bestOfficeHour[0] = i;
						bestOfficeHour[1] = j;
					}
				}
				System.out.println("");
			}
			// suggest best time
			System.out.println("The best time for office hours is " + DAY.charAt(bestOfficeHour[0])
			+ " " + TIME[bestOfficeHour[1]]
			+ " with " + scheduleArray[bestOfficeHour[0]][bestOfficeHour[1]] + " available students");
		}
	}
}


