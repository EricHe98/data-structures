public class Connect4 {
	static int X_wins = 0, O_wins = 0, ties = 0;
	static int NUM_COLUMNS = 3;
	static int NUM_ROWS = 3;
	static int NUM_TO_WIN = 3;
	static long numRecursiveCalls = 0;
	static boolean verbose = false;


	public static void main(String[] args){
		char[][] board = new char[NUM_ROWS][NUM_COLUMNS];
		char firstPlayer = 'X';
		char secondPlayer = 'O';
		
		// test making one of the first possible moves for X
//		X_wins = 0;
//		O_wins = 0;
//		numRecursiveCalls = 0;
//		board = new char[NUM_COLUMNS][NUM_ROWS];
//		// place at the bottom of the board
//		board[NUM_COLUMNS - 1][0] = firstPlayer;
//		printBoard(board);
//		Play(board, secondPlayer);
//		System.out.println("Net wins: " + (X_wins - O_wins));
//		System.out.println("X-wins:  "+ X_wins +" O-Wins:" + O_wins);
//		System.out.println("Recursion calls: " + numRecursiveCalls);
		System.out.println("Running output for a 3x3 Connect4 board");
		// make one each of the first possible moves for X
		for (int i = 0; i < NUM_COLUMNS; i++) {
			X_wins = 0;
			O_wins = 0;
			numRecursiveCalls = 0;
			board = new char[NUM_COLUMNS][NUM_ROWS];
			// place at the bottom of the board
			board[NUM_COLUMNS - 1][i] = firstPlayer;
			Play(board, secondPlayer);
			System.out.println("Net wins for column " + i + ":" + (X_wins - O_wins));
			System.out.println("Number of recursion calls: " + numRecursiveCalls);
			System.out.println("X-wins:  "+ X_wins +" O-Wins:" + O_wins);
		}
		System.out.println("Now running output for a 4x4 Connect4 board");
		NUM_COLUMNS = 4;
		NUM_ROWS = 4;
		NUM_TO_WIN = 4;
		board = new char[NUM_ROWS][NUM_COLUMNS];
		for (int i = 0; i < NUM_COLUMNS; i++) {
			X_wins = 0;
			O_wins = 0;
			numRecursiveCalls = 0;
			board = new char[NUM_COLUMNS][NUM_ROWS];
			// place at the bottom of the board
			board[NUM_COLUMNS - 1][i] = firstPlayer;
			Play(board, secondPlayer);
			System.out.println("Net wins for column " + i + ":" + (X_wins - O_wins));
			System.out.println("Number of recursion calls: " + numRecursiveCalls);
			System.out.println("X-wins:  "+ X_wins +" O-Wins:" + O_wins);
		}
	}
	
	// return 1 if X wins
	// return -1 if O wins
	// return 0 if tie
	// return 3 if no winner
	public static int checkBoard(char[][] inlist , char currentPlayer){
		// swap to other player
		char nextPlayer;
		int winner;
		if (currentPlayer == 'X') {
			nextPlayer = 'O';
			winner = -1;
		}
		else {
			nextPlayer = 'X';
			winner = 1;
		}
		
		// check horizontal win
		for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
			int colcnt = 0;
			for (int j=0; j<NUM_COLUMNS; j++) {
				if (inlist[i][j] == nextPlayer) {
					colcnt++;
					if (colcnt == NUM_TO_WIN)  { return winner;}	 
				}  else {
					colcnt =0;
				}
			}
		}
		// check vertical win
		for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
			int colcnt = 0;
			for (int j=0; j<NUM_COLUMNS; j++) {
				if (inlist[j][i] == nextPlayer) {
					colcnt++;
					if (colcnt == NUM_TO_WIN)  { return winner;}	 
				}  else {
					colcnt =0;
				}
			}
		}
		// check left diagonal
		int colcnt = 0;
		for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
			if (inlist[i][i] == nextPlayer) {
				colcnt++;
				if (colcnt == NUM_TO_WIN)  {return winner;}	 
			}  else {
				colcnt =0;
			}
		}
		//check off-diagonal
		colcnt = 0;
		for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
			if (inlist[NUM_COLUMNS-1-i][i] == nextPlayer) {
				colcnt++;
				if (colcnt == NUM_TO_WIN)  { return winner;}	 
			}  else {
				colcnt =0;
			}
		}
		if (isFull(inlist)) {  return 0; 
		} else {
			return 3;
		}
	}
//	public static int checkBoard(char[][] board){
//		// horizontal win check
//		int count = 0;
//		for (int i = 0; i < NUM_COLUMNS; i++){
//			char currentChar = board[i][0];
//			for (int j = 0; j < NUM_ROWS; j++){
//				if (verbose) {
//					System.out.println("Current space to horizontal win check is row " + i + " and col " + j + " with value " + board[i][j]);
//				}
//				if (currentChar == board[i][j] && ((currentChar == 'X') || (currentChar == 'O'))){
//					count++;
//				}
//				else {
//					count = 1;
//					currentChar = board[i][j];
//				}
//				if (count == NUM_TO_WIN){
//					if (currentChar == 'X'){
//						if (verbose) {
//							System.out.println("Triggered horizontal win check win for X at row index " + i);
//						}
//						return 1;
//					}
//					else {
//						if (verbose) {
//							System.out.println("Triggered horizontal win check win for O at row index " + i);
//						}
//						return -1;
//					}
//				}
//			}
//		}
//		// vertical win check
//		count = 0;
//		for (int i = 0; i < NUM_COLUMNS; i++){
//			char currentChar = board[0][i];
//			for (int j = 0; j < NUM_ROWS; j++){
//				if (currentChar == board[j][i] && ((currentChar == 'X') || (currentChar == 'O'))){
//					count++;
//				}
//				else {
//					count = 1;
//					currentChar = board[j][i];
//				}
//				if (count == NUM_TO_WIN){
//					if (currentChar == 'X'){
//						if (verbose) {
//							System.out.println("Triggered vertical win check win for X");
//						}
//						return 1;
//					}
//					else {
//						if (verbose) {
//							System.out.println("Triggered vertical win check win for O at col index " + i);
//						}
//						return -1;
//					}
//				}
//			}			
//		}
//		// diagonal win check
//		char currentChar = board[0][0];
//		count = 1;
//		for (int i = 1; i < NUM_COLUMNS; i++){
//			if (verbose) {
//				System.out.println("Current space to diagonal win check is " + i + " with value " + board[i][i]);
//			}
//			if (currentChar != board[i][i] || ((currentChar != 'X') || (currentChar != 'O'))){
//				break;
//			}
//			else {
//				count++;
//			}
//			if (count == NUM_TO_WIN){
//				if (currentChar == 'X'){
//					if (verbose) {
//						System.out.println("Triggered left diagonal win check win for X");
//					}
//					return 1;
//				}
//				else {
//					if (verbose) {
//						System.out.println("Triggered left diagonal win check win for O");
//					}
//					return -1;
//				}
//			}
//		}
//			
//		// off diagonal win check
//		currentChar = board[0][0];
//		count = 1;
//		for (int i = 1; i < NUM_COLUMNS; i++){
//			if (currentChar != board[i][NUM_COLUMNS - i] || ((currentChar != 'X') || (currentChar != 'O'))){
//				break;
//			}
//			count++;
//			if (count == NUM_TO_WIN){
//				if (currentChar == 'X'){
//					if (verbose) {
//						System.out.println("Triggered off diagonal win check win for X");
//					}
//					return 1;
//				}
//				else {
//					if (verbose) {
//						System.out.println("Triggered off diagonal win check win for O");
//					}
//					return -1;
//				}
//			}
//		}
//			
//		// check tie
//		if (isFull(board)) {
//			return 0;
//		}
//		// if at this point nothing has been returned then return 3
//		// to keep playing
//		return 3;
//	}

	// run the checkboard method to see if the game is completed
	// otherwise, play a move and run itself again
	public static int Play(char[][] board, char currentPlayer){
		numRecursiveCalls++;
		int gameStatus = checkBoard(board, currentPlayer);
//		if ((numRecursiveCalls % 100) == 0) {
//			System.out.println("Recursive call count is " + numRecursiveCalls);
//		}
		// perform actions in case game is over
		if (gameStatus < 3){
			if (gameStatus == 0){
				return 0;
			}
			else if (gameStatus == 1){
				X_wins++;
				return 1;
			}
			else {
				O_wins++;
				return -1;
			}
		}

		// for each space that can be the next move
		// make a copy of the board
		// then make a move on that space
		// start from the bottom of the matrix and go up the columns
		// to find the first valid move for each column
		for (int col = 0; col < NUM_COLUMNS; col++){
			for (int row = NUM_ROWS - 1; row >= 0; row--){
				if ((board[row][col] == 'O') || (board[row][col] == 'X')){
					if (verbose) {
						System.out.println("col " + col + " and row " + row + " are occupied.");
					}
					continue;
				}
				else {
					// clone board to prevent broadcasting
					if (verbose) {
						System.out.println("Cloning board with next move at row " + row + " and col " + col + " with current player " + currentPlayer);
					}
					char[][] newBoard = new char[NUM_ROWS][NUM_COLUMNS];
					for (int i = 0; i < NUM_ROWS; i++){
						for (int j = 0; j < NUM_COLUMNS; j++){
							newBoard[i][j] = board[i][j];
						}
					}
					newBoard[row][col] = currentPlayer;
					char nextPlayer;
					if (currentPlayer == 'X'){
						nextPlayer = 'O';
					}
					else {
						nextPlayer = 'X';
					}
					Play(newBoard, nextPlayer);
					break;
				}
			}
		}
		// indicate that the game is not over in this call
		gameStatus = 0;
		return gameStatus;
	}
	public static void printBoard(char[][] board) {
		for (int i =0; i<board.length; i++) {
			for (int j =0; j<board.length; j++) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	public static boolean isFull(char[][] board){
		boolean empty = true;
		for (int i = 0 ; i<NUM_COLUMNS ; i++ ) {
			for (int i2 = 0 ; i2<NUM_COLUMNS ; i2++ ) {
				if (board[i][i2] ==0   ) { empty = false; break;} 
			}
		}
		return empty;
	}
}
