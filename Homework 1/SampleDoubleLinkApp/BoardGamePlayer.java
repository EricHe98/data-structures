import java.util.Random;

public class BoardGamePlayer {
	// method to play the board game given a board and number of players
	// method to report number of moves for each player, winner
	// method to report board state
	DoublyLinkedList<BoardSpace> gameBoard;
	int numPlayers;
	Player[] playerArray;
	boolean gameOver; // indicates if a game has yet to be played/is in progress
	int turn; // indicates which player's turn it is
	int winner;
	Random r = new Random();
	int pointsThreshold = 40;
	boolean verbose = false;
	
	public BoardGamePlayer(DoublyLinkedList<BoardSpace> gameBoard, int numPlayers) {
		this.gameBoard = gameBoard;
		this.numPlayers = numPlayers;
		playerArray = new Player[numPlayers];
		for (int i = 0; i < numPlayers; i++) {
			playerArray[i] = new Player(this.gameBoard);
		}
		turn = 0;
		gameOver = false;
	}
	
	public BoardGamePlayer(DoublyLinkedList<BoardSpace> gameBoard) {
		this(gameBoard, 1);
	}
	
	public int[] playGame() {
		while (!gameOver) {
			if (verbose) {
				System.out.println("Player " + turn + " is taking a turn.");
			}
			this.takeTurn(turn);
			this.checkIfWon(turn);
			this.incrementTurn();
		}
		int[] gameInfo = this.reportGameInfo();
		return gameInfo;
	}
	
	// move player up, send other player back if needed
	public void takeTurn(int playerIndex) {
		boolean sameRow;
		boolean sameCol;
		Player p = playerArray[playerIndex];
		int diceRoll = r.nextInt(6) + 1; // roll dice
		if (verbose) {
			System.out.println("Dice rolled " + diceRoll);
		}
		p.traverseBoard(diceRoll);
		// update points
		int newPoints = p.getBoardSpace().getPoints();
		p.addToScore(newPoints);
		if (verbose) {
			System.out.println("Adding " + newPoints + " points");
			System.out.println("Player " + playerIndex + " points are now " + p.getScore());
		}
		// deal with collided players
		for (int i = 0; i < numPlayers; i++) {
			// own player does not count as collision
			if (i == playerIndex) {continue;}
			sameRow = (playerArray[i].getBoardSpace().getRow() == playerArray[playerIndex].getBoardSpace().getRow());
			sameCol = (playerArray[i].getBoardSpace().getCol() == playerArray[playerIndex].getBoardSpace().getCol());
			if (sameRow && sameCol) {
				if (verbose) {
					System.out.println("Moving player " + i + 
						"back 3 spaces from row" + playerArray[i].getBoardSpace().getRow() + 
						"and col " + playerArray[i].getBoardSpace().getCol());
				}
				playerArray[i].traverseBoard(-3);
				if (verbose) {
					System.out.println("Player " + i + 
						"is now at row " + playerArray[i].getBoardSpace().getRow() + 
						"and col " + playerArray[i].getBoardSpace().getCol());
				}
			}
		}
	}
	
	// checks to end game, or send player back to start
	public void checkIfWon(int playerIndex) {
		Player p = playerArray[playerIndex];
		if (p.getBoardSpace().getType().equals("End")) {
			if (verbose) {
				System.out.println("Current player has reached the end of the board with " + p.getScore() + " points.");
			}
			if (p.getScore() >= this.pointsThreshold) {
				if (verbose) {
					System.out.println("The game is now over.");
				}
				gameOver = true;
				winner = playerIndex;
			}
			else {
				p.setBoardSpace(this.gameBoard.first());
				if (verbose) {
					System.out.println("Current player has been sent back to row " + p.getBoardSpace().getRow()
						+ " and col " + p.getBoardSpace().getCol());
				}
			}	
		}
	}
	
	public void incrementTurn() {
		if (this.turn < numPlayers - 1) {
			this.turn += 1;
		}
		else {this.turn = 0;}
	}
	
	public void printBoardState() {
		
	}
	
	public int[] reportGameInfo() {
		if (gameOver) {
			int[] gameInfo = new int[2];
			gameInfo[0] = winner;
			gameInfo[1] = playerArray[winner].getMoves();
			return gameInfo;			
		}
		else {
			System.out.println("Game has not been played yet");
			return (new int[2]);
		}
	}
	
	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}
}
