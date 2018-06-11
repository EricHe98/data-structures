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
    
    public BoardSpace(String type) {
    	this.type = type;
    }
}

