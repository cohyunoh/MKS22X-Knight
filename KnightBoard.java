public class KnightBoard{
  private int[][] data;
  public KnightBoard(int l, int w){
    if(l <= 0 || w <= 0){
      throw new IllegalArgumentException("Not Viable Dimensions for a Board");
    }
    data = new int[l][w];
  }
  public String toString(){
    String ans = "";
    if((data.length * data[0].length) % 10 == (data.length * data[0].length)){
      for(int r = 0; r < data.length; r++){
        String line = "";
        for(int c = 0; c < data[r].length; c++){
          if(data[r][c]==0){
            line += "_ ";
          }else{
            line += data[r][c] + " ";
          }
        }
        line += "\n";
        ans += line;
      }
    }else{
      for(int r = 0; r < data.length; r++){
        String line = "";
        for(int c = 0; c < data[r].length; c++){
          if(data[r][c]==0){
            line += "__ ";
          }else{
            if(data[r][c] % 10 == data[r][c]){
              line += "_" + data[r][c] + " ";
            }else{
              line += data[r][c] + " ";
            }
          }
        }
        line += "\n";
        ans += line;
      }
    }
    return ans;
  }
  private boolean isEmpty(){
    for(int r = 0; r < data.length; r++){
      for(int c = 0; c < data[r].length; c++){
        if(data[r][c] != 0){
          return false;
        }
      }
    }
    return true;
  }

  public boolean solve(int startingRow, int startingCol){
    if(!isEmpty()){
      throw new IllegalStateException("The Board is Already Solved");
    }else if(startingRow < 0 || startingRow >= data.length || startingCol < 0 || startingCol >= data[0].length){
      throw new IllegalArgumentException("The Coordinates Given are Not on the Boundaries of the Board");
    }else{
      return solveH(startingRow, startingCol, 1);
    }
  }

  private boolean canMove(int r, int c, int direction){
    if(direction == 0){
      return r - 2 >= 0 && c + 1 < data[r].length && data[r - 2][c + 1] == 0;
    }
    if(direction == 1){
      return r - 1 >= 0 && c + 2 < data[r].length && data[r - 1][c + 2] == 0;
    }
    if(direction == 2){
      return r + 1 < data.length && c + 2 < data[r].length && data[r + 1][c + 2] == 0;
    }
    if(direction == 3){
      return r + 2 < board.length && c + 1 < data[r].length && data[r + 2][c + 1] == 0;
    }
    if(direction == 4){
      return r + 2 < board.length && c - 1 >= 0 && data[r + 2][c - 1] == 0;
    }
    if(direction == 5){
      return r + 1 < board.length && c - 2 >= 0 && data[r + 1][c - 2] == 0;
    }
    if(direction == 6){
      return r - 1 >= 0 && c - 2 >= 0 && data[r - 1][c - 2] == 0;
    }
    if(direction == 7){
      return r - 2 >= 0 && c - 1 >= 0 && data[r - 2][c - 1] == 0;
    }
  }
  private boolean canMove(int r, int c){
    for(int i = 0; i < 8; i++){
      if(canMove(r,c,i)){
        return true;
      }
    }
    return false;
  }
  private boolean solveH(int row ,int col, int level){
    if(level == data.length * data[0].length){
      return true;
    }else if{

    }
  }
}
