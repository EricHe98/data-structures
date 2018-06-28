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
	Random r = new Random();
	int pointsThreshold = 40;
	boolean verbose = false;
	int numGamesPlayed;
	
	public BoardGamePlayer(DoublyLinkedList<BoardSpace> gameBoard, int numPlayers) {
		this.gameBoard = gameBoard;
		this.numPlayers = numPlayers;
		playerArray = new Player[numPlayers];
		for (int i = 0; i < numPlayers; i++) {
			playerArray[i] = new Player(this.gameBoard);
		}
		this.turn = 0;
		this.gameOver = false;
		this.numGamesPlayed = 0;
	}
	
	public BoardGamePlayer(DoublyLinkedList<BoardSpace> gameBoard) {
		this(gameBoard, 1);
	}
	
	public void playGame() {
		while (!gameOver) {
			if (verbose) {
				System.out.println("Player " + turn + " is taking a turn.");
			}
			this.takeTurn(turn);
			this.checkIfWon(turn);
			this.incrementTurn();
			if (verbose) {
				this.printBoardState();	
			}
		}
	}
	
	// move player up, send other player back if needed
	public void takeTurn(int playerIndex) {
		boolean sameRow;
		boolean sameCol;
		Player p = playerArray[playerIndex];
		p.incrementMoves();
		int diceRoll = r.nextInt(6) + 1; // roll dice
		if (verbose) {
			System.out.println("Player is currently at row " +
					p.getBoardSpace().getRow() + 
					" and col " + p.getBoardSpace().getCol());
			System.out.println("Dice rolled " + diceRoll);
		}
		p.traverseBoard(diceRoll);
		// update points
		int newPoints = p.getBoardSpace().getPoints();
		p.addToScore(newPoints);
		if (verbose) {
			System.out.println("Adding " + newPoints + " points");
			System.out.println("Player " + playerIndex + " points are now " + p.getScore());
			System.out.println("Player is now at row " +
				p.getBoardSpace().getRow() + 
				" and col " + p.getBoardSpace().getCol());
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
				this.gameOver = true;
				p.incrementWins();
				this.numGamesPlayed++;
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
		// print board points
		System.out.println("Printing board points on right, players in center");
		BoardSpace currentSpace = gameBoard.first();
		boolean sameRow = false;
		boolean sameCol = false;
		for (int i = 0; i< 5; i++) {
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < playerArray.length; k++) {
					sameRow = false;
					sameCol = false;
					if (playerArray[k].getBoardSpace().getRow() == currentSpace.getRow()) {
						sameRow = true;
					}
					if (playerArray[k].getBoardSpace().getCol() == currentSpace.getCol()) {
						sameCol = true;
					}
					if (sameRow && sameCol) {
						System.out.print(String.format("%10s", (char)('A' + k)));
						System.out.print(String.format("%10s", currentSpace.getPoints()));
						break;
					}
				}
				if (!(sameRow && sameCol)) {
					System.out.print(String.format("%20s", currentSpace.getPoints()));
				}
				if (currentSpace.getType().equals("End")) {
					break;
				}
				currentSpace = gameBoard.nextSpace(currentSpace, 1);
			}
			System.out.println("");
			if (currentSpace.getType().equals("End")) {
				break;
			}
		}
	}
	
	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}
	
	public void resetGame() {
		this.gameOver = false;
		this.turn = 0;
		for (int i = 0; i < playerArray.length; i++) {
			Player p = playerArray[i];
			p.setBoardSpace(gameBoard.first());
		}
	}
	
	public void printPlayerStatus() {
		for (int i = 0; i < playerArray.length; i++) {
			System.out.print(String.format("%40s", "Player " + (char)('A' + i) + " average moves/ win rate"));
		}
		System.out.println("");
		for (int i = 0; i < playerArray.length; i++) {
			System.out.print(String.format("%20s", (float)playerArray[i].getMoves() / this.numGamesPlayed));
			System.out.print(String.format("%20s", (float)playerArray[i].getWins() / this.numGamesPlayed));
		}
		System.out.println("");
	}
}