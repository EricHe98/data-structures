import java.util.Scanner;

public class RunThisClass {
	public static void main(String[] args) {
		int[][] scheduleArray = new int[6][5];
		Scanner sc = new Scanner(System.in);
		
		//String input = args[0];
		String input = "(Jim<M[2000]><W[1900]><F[1700]>)(Pam<T[1200][2000]><H[2000]>)(Andy<T[2000]><M[1500]><F[1600]>)(Angela<T[1600]><H[2000]><F[1700]>)(Michael<T[1200][2000]><M[1500]>)(Kevin<H[1600][2000]><F[1600]>)(Oscar<T[1600]><W[1200]><H[2000]>)(Toby<T[2000]><M[1500]><F[1700]>)(Dwight<T[2000][1600]><F[1700]>)(Kelly<W[1200]><H[2000]><F[1700]>)";
		StringBuilder[] studentName = new StringBuilder[100];
		for (int i = 0; i < studentName.length; i++) {
			studentName[i] = new StringBuilder();
		}
		StringBuilder[][][] studentDayHour = new StringBuilder[100][5][13];
		for (int i = 0; i < studentDayHour.length; i++) {
			for (int j = 0; j < studentDayHour[0].length; j++){
				for (int k = 0; k < studentDayHour[0][0].length; k++) {
					studentDayHour[i][j][k] = new StringBuilder();
				}
			}
		}
		int studentIndex = 0;
		int hourIndex = 0;
		LinkedStack<Character> stringChecker = new LinkedStack<Character>();
		
		final String OPENING = "(<[";
		final String CLOSING = ")>]";
		final String DAY = "MTWHF";
		final String[] TIME = {"0900", "1000", "1100", "1200", "1300", "1400", "1500", "1600", "1700", "1800", "1900", "2000", "2100"};
		
		int openIndex = 0;
		int closeIndex = 0;
		int currentDayIndex = 0;
		try {
			for (char c: input.toCharArray()) {
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
		}
		finally {
			// convert to matrix
			for (int i = 0; i < studentIndex; i++) {
				for (int j = 0; j < 5; j++) {
					for (int k = 0; k < 6; k++) {
						// skip blanks
						
						if (studentDayHour[i][j][k].toString().isEmpty()) {
							continue;
						}
						else {
							// match the student's hour input to the actual time
							for (int l = 0; l < TIME.length; l++) {
								if (studentDayHour[i][j][k].toString().equals(TIME[l])) {
									System.out.println("Student " + studentName[i].toString() 
											+ " selected day " + DAY.charAt(j) 
											+ " and time " + TIME[l]);
									scheduleArray[j][k]++;
								}
							}
						}
					}
				}
			}
			// print out matrix
			// suggest best time
		}
	}
}


