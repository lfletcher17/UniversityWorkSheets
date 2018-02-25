/** This is a user interface which creates an object of Class Chess. 
 *	The user is then asked to enter a series of moves which will move
 *  pieces on the printed chess board accordingly.
 * @version 2017-11-28
 * @author lxf736
 */
import java.util.Scanner;

public class ChessInteractive {
	
	public ChessInteractive() {
			
			Chess chess = new Chess();
			int moveCounter = 1;
			int player = 1;
			Scanner inp = new Scanner(System.in);
			System.out.println("To move a piece, please enter where the piece currently "
					+ "sits (i.e a1) followed by where you would like to move it to (i.e a3) in the following format: a1a3");
			System.out.println("Player " + player + ", please enter your move");
			String move = inp.nextLine();
			if (move.equals("q")) {
				inp.close();
				chess.terminate();
			} else {
				while(true) {
					try{
						chess.makeMove(move);
						moveCounter++;
						if (moveCounter % 2 == 0) {
							player = 2;
						} else {
							player = 1;
						}
						System.out.println("Player " + player + ", please enter your move");
						move = inp.next();
						if (move.equals("q")) {
							inp.close();
							chess.terminate();
						}
					}
					catch (Exception e) {
						System.out.println(chess.toString());
						System.out.println("Please ensure to enter a valid move (i.e a1a3)\n");
						move = inp.next();
						if (move.equals("q")) {
							inp.close();
							chess.terminate();
						}
					}
				}
			}
		}

}
