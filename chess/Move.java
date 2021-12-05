package chess;

class Move{
  public Square start;
  public Square landing;

  public Move(Square x, Square y){
    start = x;
    landing = y;
  }

  public Square getStart(){
    return start;
  }

  public Square getLanding(){
    return landing;
  }
}
