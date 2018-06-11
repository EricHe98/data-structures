
public class Player {
	// contains a pointer to the game board
	// player position on the game board,
	// player score,
	// number of moves
	private DoublyLinkedList<BoardSpace> gameBoard;
	private BoardSpace space;
	private int score;
	private int moves;
	
	public Player(DoublyLinkedList<BoardSpace> gameBoard, BoardSpace space, int score, int moves) {
		this.gameBoard = gameBoard;
		this.space = space;
		this.score = score;
		this.moves = moves;
	}
	
	public Player(DoublyLinkedList<BoardSpace> gameBoard) {
		this(gameBoard, gameBoard.first(), 0, 0);
	}
	
	public void setBoardSpace(BoardSpace space) {
		this.space = space;
	}
	
	public void traverseBoard(int n) {
		this.space = this.gameBoard.nextSpace(this.space, n);
	}
	
	public BoardSpace getBoardSpace() {
		return this.space;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void addToScore(int points) {
		this.score += points;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void setMoves(int moves) {
		this.moves = moves;
	}
	
	public void incrementMoves() {
		this.moves += 1;
	}
	
	public int getMoves() {
		return this.moves;
	}
}
