import java.util.Random;

public class CallDoublyLinkedList {
// This game makes a board of 20 spaces. Player A moves forward
// through the spaces each turn 1-3 spaces. Player A add the value
// of each board space to its score.
	public static void main(String[] args) {
        // initialize board
		DoublyLinkedList<BoardSpace> gameBoard = createBoard();
		
		// initialize game
		BoardGamePlayer playerAOnly = new BoardGamePlayer(gameBoard, 1);
		playerAOnly.setVerbose(true);
		playerAOnly.playGame();
		
		// output results matrix
	     
//	     int playerAscore = 0;
//	     int playerAmoves = 0;
//	     System.out.println(mylist.toString());
//	     while (!space.type.equals("End")) {
//	    	 playerAmoves++;
//	    	 playerAscore+= space.pointvalue;
//	    	 System.out.println(playerAscore + "-" + playerAmoves);
//	    	 int jumpdistance = r.nextInt(6)+1;
//	    	 space = mylist.nextSpace(space, jumpdistance);
//	     }
//	     System.out.println("Final Stats: " + playerAscore + "-" + playerAmoves);

	}
	
	public static DoublyLinkedList<BoardSpace> createBoard(){
		Random r = new Random();
		 DoublyLinkedList<BoardSpace> mylist = new DoublyLinkedList<BoardSpace>();
		 BoardSpace space = new BoardSpace("End");
		 mylist.addFirst(space);
		 for (int i = 24;  i >= 0; i--) {
			 space = new BoardSpace(i / 5, i % 5, r.nextInt(10) + 1, "Regular");
			 mylist.addFirst(space);
		 }
		 return mylist;
	}
	
//	public static void printBoard() {
//		// pretty print the board, its points, the player positions
//	}
//	
//	public static void printResults() {
//		// pretty print results matrix
//	}

}
