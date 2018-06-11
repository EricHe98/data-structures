import java.util.Random;

public class GameBoard {
	public class BoardSpace {
	    int row;
	    int col;
	    int points;
	    String type;
	    // "R" for regular  "E" for end node of the Board
	    public BoardSpace(int row, int col, int points, String type) {
	    	this.row = row;
	    	this.col = col;
	    	this.points = points;
	    	this.type = type;
	    }
	    public int getRow() {
	    	return row;
	    }
	    public int getCol() {
	    	return col;
	    }
	    public int getPoints() {
	    	return points;
	    }
	    public String getType() {
	    	return type;
	    }
	}
	public static DoublyLinkedList<BoardSpace> createBoard(){
		Random r = new Random();
		 DoublyLinkedList<BoardSpace> mylist = new DoublyLinkedList<BoardSpace>();
		 BoardSpace space = new BoardSpace();
		 space.type = "End";
		 mylist.addFirst(space);
		 for (int i = 0;  i<20; i++) {
			 space = new BoardSpace(i / 5, i % 5, r.nextInt(10) + 1, "Regular");
			 mylist.addFirst(space);
		 }
		 return mylist;
	}
}
