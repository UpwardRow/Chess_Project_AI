package chess;

import java.util.*;

public class  AIAgent{
  Random rand;

  public AIAgent(){
    rand = new Random();
  }

  public int pieceScoreCalculations(String pieceName){
    int pieceScoreValue = 0;
    if (pieceName.contains("Pawn")){
      pieceScoreValue = 1;
    }else if (pieceName.contains("Knight") || (pieceName.contains("Bishop"))){
      pieceScoreValue = 3;
    }else if (pieceName.contains("Rook")){
      pieceScoreValue = 5;
    }else if (pieceName.contains("Queen")){
      pieceScoreValue = 9;
    }else if (pieceName.contains("King")){
      pieceScoreValue = 20;
    }
    return pieceScoreValue;
  }

  public int squareScoreCalculations(Square squareGiven){
    int squareScoreValue = 0;

    if (squareGiven.getXC() == 0 && squareGiven.getYC() == 0 ||
            squareGiven.getXC() == 7 && squareGiven.getYC() == 0 ||
            squareGiven.getXC() == 0 && squareGiven.getYC() == 7 ||
            squareGiven.getXC() == 7 && squareGiven.getYC() == 7){

      squareScoreValue = 5;

    }else if(squareGiven.getXC() == 1 && squareGiven.getYC() == 0 ||
            squareGiven.getXC() == 6 && squareGiven.getYC() == 0 ||
            squareGiven.getXC() == 0 && squareGiven.getYC() == 1 ||
            squareGiven.getXC() == 7 && squareGiven.getYC() == 1 ||
            squareGiven.getXC() == 0 && squareGiven.getYC() == 6 ||
            squareGiven.getXC() == 7 && squareGiven.getYC() == 6 ||
            squareGiven.getXC() == 1 && squareGiven.getYC() == 7 ||
            squareGiven.getXC() == 6 && squareGiven.getYC() == 7){

      squareScoreValue = 8;

    } else if (((squareGiven.getXC() >= 2) && (squareGiven.getXC() <= 5)) && (squareGiven.getYC() == 0) ||
            (squareGiven.getXC() == 0 && ((squareGiven.getYC() >= 2) && (squareGiven.getYC() <= 5))) ||
            (squareGiven.getXC() == 7 && ((squareGiven.getYC() >= 2) && (squareGiven.getYC() <= 5))) ||
            ((squareGiven.getXC() >= 2 && (squareGiven.getXC() <= 5)) && (squareGiven.getYC() == 7))){

      squareScoreValue = 9;

    }else if(squareGiven.getXC() == 1 && squareGiven.getYC() == 1 ||
            squareGiven.getXC() == 6 && squareGiven.getYC() == 1 ||
            squareGiven.getXC() == 1 && squareGiven.getYC() == 6 ||
            squareGiven.getXC() == 6 && squareGiven.getYC() == 6){

      squareScoreValue = 12;

    } else if (((squareGiven.getXC() >= 2) && (squareGiven.getXC() <= 5)) && (squareGiven.getYC() == 1) ||
            (squareGiven.getXC() == 1 && ((squareGiven.getYC() >= 2) && (squareGiven.getYC() <= 5))) ||
            (squareGiven.getXC() == 6 && ((squareGiven.getYC() >= 2) && (squareGiven.getYC() <= 5))) ||
            ((squareGiven.getXC() >= 2 && (squareGiven.getXC() <= 5)) && (squareGiven.getYC() == 6))){

      squareScoreValue = 14;

    } else if (((squareGiven.getXC() >= 2) && (squareGiven.getXC() <= 5)) && (squareGiven.getYC() == 2) ||
            (squareGiven.getXC() == 2 && ((squareGiven.getYC() >= 2) && (squareGiven.getYC() <= 5))) ||
            (squareGiven.getXC() == 5 && ((squareGiven.getYC() >= 2) && (squareGiven.getYC() <= 5))) ||
            ((squareGiven.getXC() >= 2 && (squareGiven.getXC() <= 5)) && (squareGiven.getYC() == 5)) ||
            ((squareGiven.getXC() >= 3) && (squareGiven.getXC() <= 4)) && squareGiven.getYC() == 3 ||
            ((squareGiven.getXC() >= 3) && (squareGiven.getXC() <= 4) && squareGiven.getYC() == 4)){

      squareScoreValue = 16;

    }
    return squareScoreValue;
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

    int bestScore = 0;
    Move bestMove = null;

    while(!possibilities.isEmpty()) {
        Move tmpMove = (Move)possibilities.pop();

        Square landing = tmpMove.getLanding();

        int scoreOfPiece = pieceScoreCalculations(landing.getName());
        int scoreOfSquare = squareScoreCalculations(landing);
        int totalScore = 0;
        totalScore = scoreOfPiece + scoreOfSquare;

      if(totalScore > bestScore) {
        bestScore = totalScore;
        bestMove = tmpMove;
      }
    }
    return bestMove;
  }
}
