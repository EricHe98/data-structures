import java.util.Scanner;


public class Connect4 {
	static final int X =1, O=2;
	static final int NUM_COLUMNS = 4;
	static final int NUM_ROWS = 4;
	static Scanner input = new Scanner(System.in);
	static char[][] board = new int[NUM_COLUMNS][NUM_ROWS];

	public static void main(String[] args){
		
		
		// have 
	}
	
	// return 1 if X wins
	// return -1 if O wins
	// return 0 if tie
	// return 3 if no winner
	public static int checkBoard(){
		// horizontal win check
		int count = 0;
		for (int i = 0; i < NUM_COLUMNS; i++){
			char currentChar = board[i][0];
			for (int j = 0; j < NUM_ROWS; j++){
				if (currentChar == board[i][j]){
					count++;
				}
				else {
					count = 1;
					currentChar == board[i][j];
				}
			}
			if (count == 4){
				if (currentChar == 'X'){
					return 1;
				}
				else (currentChar == 'O'){
					return -1;
				}
			}
		}
		// vertical win check
		count = 0;
		for (int i = 0; i < NUM_COLUMNS; i++){
			char currentChar = board[0][i];
			for (int j = 0; j < NUM_ROWS; j++){
				if (currentChar == board[j][i]){
					count++;
				}
				else {
					count = 1;
					currentChar == board[j][i];
				}
			}
			if (count == 4){
				if (currentChar == 'X'){
					return 1;
				}
				else (currentChar == 'O'){
					return -1;
				}
			}
		}
		// diagonal win check
		currentChar = board[0][0];
		count = 1;
		for (int i = 1; i < NUM_COLUMNS; i++){
			if (currentChar != board[i][i]){
				break;
			}
			count++;
		}
		if (count == 4){
			if (currentChar == 'X'){
				return 1;
			}
			else (currentChar == 'O'){
				return -1;
			}
		}
		// off diagonal win check
		currentChar = board[0][0];
		count = 1;
		for (int i = 1; i < NUM_COLUMNS; i++){
			if (currentChar != board[i][NUM_COLUMNS - i]){
				break;
			}
			count++;
		}
		if (count == 4){
			if (currentChar == 'X'){
				return 1;
			}
			else (currentChar == 'O'){
				return -1;
			}
		}
		// check tie
		for (int i = 0; i < NUM_COLUMNS; i++){
			if (currentChar = null){
				return 0;
			}
		}
		// if at this point nothing has been returned then return 
		return 3;
	}

	// run the checkboard method to see if the game is completed
	// otherwise, play a move and run itself again
	public int Play(char player){
		
	}
}
