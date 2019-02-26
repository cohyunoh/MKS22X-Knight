import java.util.ArrayList;
public class KnightBoard{
  private int[][] data;
  private int[][] optData;
  private int[] moves = {-2,1,-1,2,1,2,2,1,2,-1,1,-2,-1,-2,-2,-1};
  private ArrayList<int[]> outgoingMoves;

  public KnightBoard(int l, int w){
    if(l <= 0 || w <= 0){
      throw new IllegalArgumentException("Not Viable Dimensions for a Board");
    }
    data = new int[l][w];
    createOptData();
  }

  public String printOptData(){
    String ans = "";
    for(int r = 0; r < data.length; r++){
      String line = "";
      for(int c = 0; c < data[r].length; c++){
        line += optData[r][c] + " ";
      }
      line += "\n";
      ans += line;
    }
    return ans;
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
      possibleMoves(startingRow, startingCol);
      move(startingRow, startingCol, 1);
      return solveH(startingRow, startingCol, 2);
    }
  }

  private boolean move(int newR, int newC, int level){
    if (newR >=0 && newC >= 0 && newR < data.length && newC < data[newR].length && data[newR][newC] == 0){
      data[newR][newC] = level;
      possibleMoves(newR,newC);
      updateBoard(true);
      return true;
    }else{
      return false;
    }
  }

  private void updateBoard(boolean mode){
    for(int i = 0; i < outgoingMoves.size(); i++){
      if(mode){
        optData[outgoingMoves.get(i)[0]][outgoingMoves.get(i)[1]] = optData[outgoingMoves.get(i)[0]][outgoingMoves.get(i)[1]] - 1;
      }else{
        optData[outgoingMoves.get(i)[0]][outgoingMoves.get(i)[1]] = optData[outgoingMoves.get(i)[0]][outgoingMoves.get(i)[1]] + 1;
      }

    }
  }

  private boolean retract(int r, int c, int level){
    if (r >=0 && c >= 0 && r < data.length && c < data[r].length && data[r][c] == level){
      data[r][c] = 0;
      possibleMoves(r,c);
      updateBoard(false);
      return true;
    }else{
      return false;
    }
  }

  private boolean solveH(int row ,int col, int level){
    int newR, newC;
    if(level == (Math.pow(data.length, 2))){
      possibleMoves(row,col);
      newR = outgoingMoves.get(0)[0];
      newC = outgoingMoves.get(0)[1];
      move(newR, newC, level);
      return true;
    }else if(level == 1){
      move(row,col,level);
    }else{
      possibleMoves(row,col);
      for(int i = 0; i < outgoingMoves.size(); i++){
        newR = outgoingMoves.get(i)[0];
        newC = outgoingMoves.get(i)[1];
        move(newR, newC, level + 1);
        if(solveH(newR, newC, level + 1)){
          return true;
        }else{
          retract(newR, newC, level + 1);
        }
      }
    }
    return false;
    /*
    if(level == (data.length * data[0].length)){
      return true;
    }
    possibleMoves(row, col);
    System.out.println(printPoss(row,col));
    for(int i = 0; i < outgoingMoves.size(); i++){
      int newR = outgoingMoves.get(i)[0];
      int newC = outgoingMoves.get(i)[1];
      move(newR,newC,level);
      if(solveH(newR, newC, level + 1)){
        return true;
      }else{
        retract(newR, newC, level);
      }

    }
    //System.out.println("false statement 1");
    //System.out.println("level:" + level);
    return false;
    */
  }

    //System.out.println("false statement 2");
    //System.out.println("level:" + level);

  public int countSolutions(int startingRow, int startingCol){
    if(!isEmpty()){
      throw new IllegalStateException("The Board is Already Solved");
    }else if(startingRow < 0 || startingRow >= data.length || startingCol < 0 || startingCol >= data[0].length){
      throw new IllegalArgumentException("The Coordinates Given are Not on the Boundaries of the Board");
    }else{
      possibleMoves(startingRow, startingCol);
      move(startingRow, startingCol, 1);
      return countSolutionsHelper(startingRow, startingCol, 2, 0);
    }
  }

  private int countSolutionsHelper(int row, int col, int level, int solutions){
    int newR, newC;
    if(level > (Math.pow(data.length, 2))){
      solutions ++;
      return solutions;
    }else{
      possibleMoves(row,col);
      for(int i = 0; i < outgoingMoves.size(); i++){
        newR = outgoingMoves.get(i)[0];
        newC = outgoingMoves.get(i)[1];
        move(newR, newC, level + 1);
        if(countSolutionsHelper(newR,newC,level+1,solutions) > solutions){
          solutions = countSolutionsHelper(newR,newC,level+1,solutions);
        }
        retract(newR, newC, level + 1);
      }
    }

    return solutions;
    /*
    if(level > data.length * data[row].length){
      solutions ++;
      return solutions;
    }else{
      possibleMoves(row, col);
      for(int i = 0; i < outgoingMoves.size(); i++){
        int newR = outgoingMoves.get(i)[0];
        int newC = outgoingMoves.get(i)[1];
        if(move(newR,newC,level)){
          if(countSolutionsHelper(newR,newC,level+1,solutions) > solutions){
            solutions = countSolutionsHelper(newR,newC,level+1,solutions);
          }
          retract(newR,newC,level+1);
        }
      }
      return solutions;
    }
    */
  }

  private void clear(){
    for(int r = 0; r < data.length; r++){
      for(int c = 0; c < data[r].length; c++){
        data[r][c] = 0;
      }
    }
  }
  public String printPoss(int row, int col){
    possibleMoves(row, col);
    String ans = "";
    for(int i = 0; i < outgoingMoves.size(); i++){
      ans += "[";
      ans += outgoingMoves.get(i)[0] + " , ";
      ans += outgoingMoves.get(i)[1] + "]";
      ans += " , ";
    }
    return ans;
  }

  private void possibleMoves(int row, int col){
    ArrayList<int[]> list = new ArrayList<int[]>();
    for(int i = 0; i < 8; i++){
      int newR = row + moves[i * 2];
      int newC = col + moves[i * 2 + 1];
      if (newR >=0 && newC >= 0 && newR < data.length && newC < data[newR].length && data[newR][newC] == 0){
        int[] coord = {newR, newC};
        list.add(coord);
      }
    }
    list = organize(list);
    outgoingMoves = list;
  }

  private ArrayList<int[]> organize(ArrayList<int[]> list){
    ArrayList<int[]> newList = new ArrayList<int[]>();
    for(int i = 1; i < list.size(); i++){
      for(int c = i - 1; c >=0; c--){
        int value1 = optData[list.get(i)[0]][list.get(i)[1]];
        int value2 = optData[list.get(c)[0]][list.get(c)[1]];
        if(value2 > value1){
          int[] coord = list.get(i);
          list.remove(i);
          list.add(c, coord);
        }
      }
    }
    return list;
  }

  private void createOptData(){
    optData = new int[data.length][data[0].length];
    for(int r = 0; r < data.length; r++){
      for(int c = 0; c < data[r].length; c++){
        optData[r][c] = 8;
        int greatestR = data.length - 1;
        int greatestC = data[r].length - 1;
        if(r % greatestR == 0 && c % greatestC == 0){
          optData[r][c] = 2;
        }else if((r % greatestR == 0 && c > 1 && c < greatestC - 1) || (c % greatestC == 0 && r > 1 && r < greatestR - 1)){
          optData[r][c] = 4;
        }else if((c == 1 || c == greatestC - 1) && (r==1 || r == greatestR - 1)){
          optData[r][c] = 4;
        }else if(((r > 1 && r < greatestR - 1) && (c == 1 || c == greatestC - 1)) || ((c > 1 && c < greatestC - 1) && (r == 1 || r == greatestR - 1))){
          optData[r][c] = 6;
        }else if((r % greatestR == 0 && c == 1 || c == greatestC - 1) || (c % greatestC == 0 && r == 1 || r == greatestR - 1)){
          optData[r][c] = 3;
        }
      }
    }
  }
}
