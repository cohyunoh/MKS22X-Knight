public class KnightBoard{
  private int[][] data;
  private int currentR;
  private int currentC;

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

  private boolean notSolved(){
    for(int r = 0; r < data.length; r++){
      for(int c = 0; c < data[r].length; c++){
        if(data[r][c] == 0){
          return true;
        }
      }
    }
    return false;
  }

  public boolean solve(int startingRow, int startingCol){
    if(!isEmpty()){
      throw new IllegalStateException("The Board is Already Solved");
    }else if(startingRow < 0 || startingRow >= data.length || startingCol < 0 || startingCol >= data[0].length){
      throw new IllegalArgumentException("The Coordinates Given are Not on the Boundaries of the Board");
    }else{
      currentR = startingRow;
      currentC = startingCol;
      data[startingRow][startingCol] = 1;
      return solveH(startingRow, startingCol, 2);
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
      return r + 2 < data.length && c + 1 < data[r].length && data[r + 2][c + 1] == 0;
    }
    if(direction == 4){
      return r + 2 < data.length && c - 1 >= 0 && data[r + 2][c - 1] == 0;
    }
    if(direction == 5){
      return r + 1 < data.length && c - 2 >= 0 && data[r + 1][c - 2] == 0;
    }
    if(direction == 6){
      return r - 1 >= 0 && c - 2 >= 0 && data[r - 1][c - 2] == 0;
    }
    if(direction == 7){
      return r - 2 >= 0 && c - 1 >= 0 && data[r - 2][c - 1] == 0;
    }
    return false;
  }

  private boolean moveKnight(int r, int c, int direction, int level){
    if(direction == 0 && canMove(r,c,direction)){
      data[r-2][c+1] = level;
      currentR -= 2;
      currentC += 1;
      return true;
    }
    if(direction == 1 && canMove(r,c,direction)){
      data[r-1][c+2] = level;
      currentR -= 1;
      currentC += 2;
      return true;
    }
    if(direction == 2 && canMove(r,c,direction)){
      data[r+1][c+2] = level;
      currentR += 1;
      currentC += 2;
      return true;
    }
    if(direction == 3 && canMove(r,c,direction)){
      data[r+2][c+1] = level;
      currentR += 2;
      currentC += 1;
      return true;
    }
    if(direction == 4 && canMove(r,c,direction)){
      data[r+2][c-1] = level;
      currentR += 2;
      currentC -= 1;
      return true;
    }
    if(direction == 5 && canMove(r,c,direction)){
      data[r+1][c-2] = level;
      currentR += 1;
      currentC -= 2;
      return true;
    }
    if(direction == 6 && canMove(r,c,direction)){
      data[r-1][c-2] = level;
      currentR -= 1;
      currentC -= 2;
      return true;
    }
    if(direction == 7 && canMove(r,c,direction)){
      data[r-2][c-1] = level;
      currentR -= 2;
      currentC -= 1;
      return true;
    }
    return false;
  }

  public boolean retractKnight(int r, int c, int direction){
    if(r<data.length && r>=0 && c<data[r].length && c>=0){
      if(direction == 0){
        data[r-2][c+1] = 0;
        currentR += 2;
        currentC -= 1;
        return true;
      }else if(direction == 1){
        data[r-1][c+2] = 0;
        currentR += 1;
        currentC -= 2;
        return true;
      }else if(direction == 2){
        data[r+1][c+2] = 0;
        currentR -= 1;
        currentC -= 2;
        return true;
      }else if(direction == 3){
        data[r+2][c+1] = 0;
        currentR -= 2;
        currentC -= 1;
        return true;
      }else if(direction == 4){
        data[r+2][c-1] = 0;
        currentR -= 2;
        currentC += 1;
        return true;
      }else if(direction == 5){
        data[r+1][c-2] = 0;
        currentR -= 1;
        currentC += 2;
        return true;
      }else if(direction == 6){
        data[r-1][c-2] = 0;
        currentR += 1;
        currentC += 2;
        return true;
      }else if(direction == 7){
        data[r-2][c-1] = 0;
        currentR += 2;
        currentC += 1;
        return true;
      }else{
        return false;
      }
    }else{
      return false;
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
    if(level == (data.length * data[0].length)){
      return true;
    }else if(!canMove(row,col)){
      return false;
    }else{
      for(int i = 0; i < 8; i++){
        if(moveKnight(row,col,i,level)){
          if(solveH(currentR, currentC, level + 1)){
            return true;
          }else{
            retractKnight(row,col,i);
          }
        }
      }
    }
    return false;
  }

  public int countSolutions(int startingRow, int startingCol){
    if(!isEmpty()){
      throw new IllegalStateException("The Board is Already Solved");
    }else if(startingRow < 0 || startingRow >= data.length || startingCol < 0 || startingCol >= data[0].length){
      throw new IllegalArgumentException("The Coordinates Given are Not on the Boundaries of the Board");
    }else{
      currentR = startingRow;
      currentC = startingCol;
      data[startingRow][startingCol] = 1;
      return
  }

}
