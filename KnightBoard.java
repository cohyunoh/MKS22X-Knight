public class KnightBoard{
  private int[][] data;
  private int[] moves = {-2,1,-1,2,1,2,2,1,2,-1,1,-2,-1,-2,-2,-1};

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

  private boolean move(int newR, int newC, int level){
    if (newR >=0 && newC >= 0 && newR < data.length && newC < data[newR].length && data[newR][newC] == 0){
      data[newR][newC] = level;
      return true;
    }else{
      return false;
    }
  }

  private boolean solveH(int row ,int col, int level){
    if(level > (data.length * data[0].length)){
      return true;
    }else{
      if(move(row,col,level)){
        boolean canMove = false;
        for(int i = 0; i < 8; i+=2){
          if(solveH(row + moves[i], col + moves[i+1], level++)){
            return true;
          }
        }
        return false;
      }else{
        return false;
      }
    }
  }
  /*
  public int countSolutions(int startingRow, int startingCol){
    if(!isEmpty()){
      throw new IllegalStateException("The Board is Already Solved");
    }else if(startingRow < 0 || startingRow >= data.length || startingCol < 0 || startingCol >= data[0].length){
      throw new IllegalArgumentException("The Coordinates Given are Not on the Boundaries of the Board");
    }else{
      return countSolutionsHelper(startingRow, startingCol, 1, 0);
    }
  }
  private int countSolutionsHelper(int row, int col, int level, int solutions){
    if(level > (data.length * data[0].length)){
      solutions += 1;
      return solutions;
    }else if(!canMove(row,col)){
      return solutions;
    }else{
      for(int i = 0; i < 8; i++){
        if(moveKnight(row,col,i,level)){
          if(countSolutionsHelper(currentR, currentC, level + 1, solutions) > solutions){
            solutions ++;
          }
          retractKnight(row,col,i);
        }
      }
    }
    return solutions;
  }
*/
}
