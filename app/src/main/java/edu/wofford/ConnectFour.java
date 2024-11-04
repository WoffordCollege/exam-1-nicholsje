package edu.wofford;


/**
 * This class provides implements a game board for Connect Four.
 * In this game, the RED player always plays the first token.
 * A Connect Four game board has 6 rows and 7 columns:
 * 
 *  | | | | | | | |
 *  | | | | | | | |
 *  | | | | | | | |
 *  | | | |B| | | |
 *  |B| |B|R| | | |
 *  |R|B|B|R| |R|R|
 *  ---------------
 * 
 * This diagram also illustrates how the board should be printed
 * to the screen (via the toString method).
 * 
 * The first player (RED or BLACK) to get four tokens in a row,
 * either horizontally, vertically, or diagonally, wins the game.
 * It is possible for neither player to win (a TIE) if the board
 * is filled with no winning condition. If the game is still
 * ongoing, the result should be NONE.
 */
 
public class ConnectFour {

    public enum Location {EMPTY, RED, BLACK};
    public enum Result {NONE, TIE, BLACKWIN, REDWIN};
    protected Location[][] board;
    protected boolean redTurn;
    
    /**
     * This is a convenience method that you might find useful.
     * It returns the specified column's entries as a string.
     * For instance, suppose that you had the following board:
     * 
     * | | | | | | | |
     * | | | | | | | |
     * | | | | | | | |
     * | |B| | |R| | |
     * | |B|R| |R|R|B|
     * |B|R|B| |R|B|R|
     * ---------------
     * 
     * Calling this function on column 1 would return "RBB". On
     * column 5 it would return "BR". (It starts from the "bottom" 
     * of the column.)
     * 
     * @param column the column to convert
     * @return a string representing that column's contents
     */
    private String getColumnAsString(int column) {
        if(column >= 0 && column < board[0].length) {
            String s = "";
            int row = board.length - 1;
            while(row >= 0 && board[row][column] != Location.EMPTY) {
                s += (board[row][column] == Location.RED)? "R" : "B";
                row--;
            }
            return s;
        }
        else {
            return "";
        }
    }
    
    /**
     * This constructor creates a completely empty board and
     * sets the current player to red.
     */
    public ConnectFour() {
        board = new Location[6][7];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = Location.EMPTY;
            }
        }
        redTurn = true;
    }

    // These two are only needed by the unit tests for verification purposes.
    // If you change them, you will fail the tests, so
    // DO NOT CHANGE THEM.
    public Location getLocation(int row, int col) {
        return board[row][col];
    }
    
    public void setLocation(int row, int col, Location loc) {
        board[row][col] = loc;
    }
    




    

    //===========================================================
    //                          TO DO
    //===========================================================


    /**
     * This method returns the token type on the top of the 
     * specified column. If the column is empty, it should
     * return EMPTY. Otherwise, it should return either
     * RED or BLACK.
     * 
     * @param column the column to check
     * @return the top of the column
     */
    public Location getTopOfColumn(int column) {
        // Question 1
        // TODO
		String r = getColumnAsString(column);
		if (r.equals("")){
			return Location.EMPTY;
		} else if (r.charAt(r.length() - 1) == 'B'){
			return Location.BLACK;
		} else {
			return Location.RED;
		}
    }
    
    /**
     * This method returns the height of the specified column.
     * If the column is empty, this returns a 0. The maximum
     * value that this function can return is 6.
     * 
     * @param column the column to check
     * @return the height of the column
     */
    public int getHeightOfColumn(int column) {
        // Question 2
        // TODO
        String r = getColumnAsString(column);
		if (r.equals("")){
			return 0;
		} else {
			return r.length();
		}
    }
    
    /**
     * This method drops a token into the specified column and 
     * changes the current player (from RED to BLACK and vice versa).
     * If the column is invalid (i.e., not in the range [0, 6]), then
     * the drop should be ignored (so that it would remain the previous
     * player's turn). If the column is full, it should throw a
     * ColumnFullException (but the player should also not lose their turn).
     * 
     * @param column the column for the token
     */
    public void dropToken(int column) {
        // Question 3
        // TODO
		if (column < 7 && column >= 0){
			String r = getColumnAsString(column);
			if (getHeightOfColumn(column) == 6){
				throw new ColumnFullException();
			} else {
				int row = getHeightOfColumn(column);
				if (redTurn){
					redTurn = false;
					board[5-row][column] = Location.RED;
				} else {
					redTurn = true;
					board[5-row][column] = Location.BLACK;
				}
			}
		}
        
    }
    
    /**
     * This method returns the current result of the game. If the
     * game board is entirely full, this result should be TIE. If
     * RED or BLACK have four-in-a-row, the result should be REDWIN
     * or BLACKWIN, respectively. Otherwise, the result should be
     * NONE (meaning the game is not over).
     * 
     * @return the current result of the game
     */
    public Result getResult() {
        // Question 4
        // You don't have to handle all possible winning conditions.
        // You only need to handle whether a player has won vertically
        // (as either RED or BLACK). In all other cases, this should 
        // return Result.NONE.
        // 
        // Note: You may find it convenient to use another method of
        //       this class to determine whether someone has won 
        //       along a column.
        
        // TODO
		for (int i = 0; i < 6; i++){
			int counter = 0;
			Location last = Location.EMPTY;
			for (int j = 5; j > 0; j--){
				if (board[j][i] != Location.EMPTY){
					if (board[j][i] == last){
						counter = counter + 1;
						if (counter == 3){
							if (last == Location.BLACK){
								return Result.BLACKWIN;
							} else {
								return Result.REDWIN;
							}
						}
					} else {
						counter = 0;
						last = board[j][i];
					}
				}
			}
		}
        
        return Result.NONE;
    }
    
    /**
     * This method returns the representation of the board as a string.
     * The following is an example:
     * 
     *  | | | | | | | |
     *  | | | | | | | |
     *  | | | | | | | |
     *  | | | |B| | | |
     *  |B| |B|R| | | |
     *  |R|B|B|R| |R|R|
     *  ---------------
     * 
     * Here, "B" and "R" mark the black and red tokens in each column.
     * 
     * @return a string representing the board
     */
    public String toString() {
        // Question 5
        // TODO
		String r = "|";
        for (int i = 0; i < 6; i++){
			for (int j = 0; j < 7; j++){
				String temp = "";
				if (board[i][j] == Location.BLACK){
					temp = "B";
				} else if (board[i][j] == Location.RED){
					temp = "R";
				} else {
					temp = " ";
				}
				r = r + temp + "|";
			}
			if (i < 5){
				r = r + "\n|";
			} else {
				r = r + "\n---------------";
			}
		}
        return r;
    }


}
