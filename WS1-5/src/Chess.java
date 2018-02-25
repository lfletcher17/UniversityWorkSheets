/**
 *  A class Chess for a CustomerBase which contains a chessBoard. Chess consists of the 
 *  following field variable:
 *  chessBoard - the squares of a chessBoard containing playing pieces or blank spaces.
 *  The method initialiseBoard() sets the initial values for all squares of the chessBoard
 *  The method makeMove() takes the ID of a space to move from and a space to move to (i.e.a1a3
 *  will move the piece at a1 to a3) and processes the move accordingly on the chessBoard.
 *  The method terminate() exits the application.
 *  The method toString() is used to output a visual representation of the chessBoard.
 * @author lxf736
 * @version 2017-11-27
 */
public class Chess {
	
	private String [] [] chessBoard;
	
	/**
	 * Calls initialiseBoard which sets the initial values for chessBoard
	 */
	public Chess() {
		initialiseBoard();
	}
	
	/**
	 * initialises the chessBoard to the initial values required for a game of Chess
	 */
	public void initialiseBoard() {
		chessBoard = new String [8] [8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i==1 || i==6) {
					chessBoard[i][j] = "  pawn  |";
					if (i==6) {
						chessBoard[i][j] = chessBoard[i][j].toUpperCase();
					}
				} else if (i==0 || i==7) {
					if (j==0 || j==7) {
						chessBoard[i][j] = "  rook  |";
						if (i==7) {
							chessBoard[i][j] = chessBoard[i][j].toUpperCase();
						}
					} else if (j==1 || j==6) {
						chessBoard[i][j] = " knight |";
						if (i==7) {
							chessBoard[i][j] = chessBoard[i][j].toUpperCase();
						}
					} else if (j==2 || j==5) {
						chessBoard[i][j] = " bishop |";
						if (i==7) {
							chessBoard[i][j] = chessBoard[i][j].toUpperCase();
						}
					} else if (j==3) {
						chessBoard[i][j] = " queen  |";
						if (i==7) {
							chessBoard[i][j] = chessBoard[i][j].toUpperCase();
						}
					} else {
						chessBoard[i][j] = "  king  |";
						if (i==7) {
							chessBoard[i][j] = chessBoard[i][j].toUpperCase();
						}
					}
				} else {
					chessBoard [i][j] = "        |";
				}
			}
		}
		
		System.out.println(this.toString());
		
	}
	
	/**
	 * moves a piece on the chessBoard by taking the current position of a piece and the desired 
	 * position of that piece (i.e. to move from a1 to a3 enter "a1a3")
	 * @param newMove is the the current position of a piece and the desired 
	 * position of that piece as a String 
	 */
	public void makeMove(String newMove) {
			
		String temp = "";
		int osi = 0;
		int nsi = 0;
			
		char oldSquarej = newMove.charAt(0);
		int osj = oldSquarej - 97;
			
		char oldSquarei = newMove.charAt(1);
		if ((oldSquarei - 56) >= -7 && (oldSquarei - 56) <= 0) {
			osi = Math.abs(oldSquarei - 56);
		} else {
			throw new IllegalArgumentException();
		}
			
		char newSquarej = newMove.charAt(2);
		int nsj = newSquarej - 97;
			
		char newSquarei = newMove.charAt(3);
		if ((newSquarei - 56) >= -7 && (newSquarei-56) <=0 ) {
			nsi = Math.abs(newSquarei - 56);
		} else {
			throw new IllegalArgumentException();
		}
		
		temp = chessBoard[osi][osj];
		chessBoard[osi][osj] = "        |";
		chessBoard[nsi][nsj] = temp;
		System.out.println(this.toString());
			
	}
	
	/**
	 * this method terminates the application
	 */
	public void terminate() {
		System.out.println("Application Closed - Thanks for playing!");
		System.exit(0);
	}
	
	/** 
     *  toString defines how to print Chess - importantly this is used to produce a visual
     *  representation of chessBoard
     *  @return  the print type of Chess
     */
	public String toString () {
		String result = "        a        b        c        d        e        f        g        h\n" ;
		for (int i=0; i<8; i++) {
			result = result + "   +--------+--------+--------+--------+--------+--------+--------+--------+\n";
			result = result + (8-i) + " | ";
			for (int j=0; j<8; j++) {
				result = result + chessBoard[i][j];
			}
			result = result + + (8-i) + "\n" ;
		}
		return result + "   +--------+--------+--------+--------+--------+--------+--------+--------+\n";
	}

}
