import java.util.Random;

public class RunThisClass {
// This game makes a board of 20 spaces. Player A moves forward
// through the spaces each turn 1-3 spaces. Player A add the value
// of each board space to its score.
	public static void main(String[] args) {
        // initialize board
		DoublyLinkedList<BoardSpace> gameBoard = createBoard();
		
		System.out.println("Playing games with one player");
		// initialize one-player game and results
		BoardGamePlayer onePlayer = new BoardGamePlayer(gameBoard, 1);
		
		// loop 1000 games
		for (int i = 0; i < 1000; i++) {
			onePlayer.playGame();
			if ((i % 100) == 0) {
				onePlayer.printBoardState();
			}
			onePlayer.resetGame();
		}
		onePlayer.printPlayerStatus();
		
		System.out.println("Playing games with two players");
		// two players
		BoardGamePlayer twoPlayers = new BoardGamePlayer(gameBoard, 2);
		// twoPlayers.setVerbose(true);
		for (int i = 0; i < 1000; i++) {
			twoPlayers.playGame();
			if ((i % 100) == 0) {
				twoPlayers.printBoardState();
			}
			twoPlayers.resetGame();
		}
		twoPlayers.printPlayerStatus();
		
		System.out.println("Playing games with three players");
		// three players
		BoardGamePlayer threePlayers = new BoardGamePlayer(gameBoard, 3);
		for (int i = 0; i < 1000; i++) {
			threePlayers.playGame();
			if ((i % 100) == 0) {
				threePlayers.printBoardState();
			}
			threePlayers.resetGame();
		}
		threePlayers.printPlayerStatus();
		
		System.out.println("Playing games with four players");
		// three players
		BoardGamePlayer fourPlayers = new BoardGamePlayer(gameBoard, 4);
		for (int i = 0; i < 1000; i++) {
			fourPlayers.playGame();
			if ((i % 100) == 0) {
				fourPlayers.printBoardState();
			}
			fourPlayers.resetGame();
		}
		fourPlayers.printPlayerStatus();
		
	     
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
		 BoardSpace space = new BoardSpace(4, 4, 0, "End");
		 mylist.addFirst(space);
		 for (int i = 23;  i >= 0; i--) {
			 space = new BoardSpace(i / 5, i % 5, r.nextInt(10) + 1, "Regular");
			 mylist.addFirst(space);
			 // System.out.println("Adding board space of type " + space.getType() + " and points " + space.getPoints() + " to coordinates " + i / 5 + " " + i % 5);
		 }
		 return mylist;
	}
	
	public static void printResults() {
		
	}
	
//	public static void printBoard() {
//		// pretty print the board, its points, the player positions
//	}
//	
//	public static void printResults() {
//		// pretty print results matrix
//	}

}
