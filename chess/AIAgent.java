package chess;

import java.util.*;

public class  AIAgent{
  Random rand;

  public AIAgent(){
    rand = new Random();
  }


  /*
    Here is the break-down of the possible AI possibilities.

    randomMove --> Get all possible movements for white
               --> Select a random move

    nextBestMove --> Get all possible movements for white
                 --> Create a utility function based on the current move, meaning that I would need to implement a score
                 based on the move that is made
                  --> To implement the functionality I will need to loop through the stack of movements and check if we
                  are taking a piece. If so, we make this movement.


   */

  /*
    The method randomMove takes as input a stack of potential moves that the AI agent
    can make. The agent uses a random number generator to randomly select a move from
    the inputted Stack and returns this to the calling agent.
  */

  public Move randomMove(Stack possibilities){

    int moveID = rand.nextInt(possibilities.size());
    System.out.println("Agent randomly selected move : "+moveID);
    for(int i=1;i < (possibilities.size()-(moveID));i++){
      possibilities.pop();
    }
    Move selectedMove = (Move)possibilities.pop();
    return selectedMove;
  }

  /*
    This method tries to calculate the best move that it could make depending on the pieces that it has. I should
    implement some sort of score system that rates the squares and pieces as different values depending on worth.

    It doesn't matter if this turn will create a consequence of the AI losing its own piece(s) after the move
    because of this turn.

    Pawn: 1 point
    Knight/Bishop: 3 points
    Rook: 5 points
    Queen: 9 points
    King: Value of the game

    Gather all the possible moves possible for a piece, similar to the randomMove method. We then need to apply a
    utility function to find out which move to make
   */

  public Move nextBestMove(Stack possibilities){
    Move selectedMove = new Move();
    return selectedMove;
  }

  /*
    This method is an extension of the method above. It tries to make the best move, while also trying to determine what
    move the human player is trying to make.
   */
  public Move twoLevelsDeep(Stack possibilities){
    Move selectedMove = new Move();
    return selectedMove;
  }
}
