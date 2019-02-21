import java.util.ArrayList;
public class optData{
  private int[][] board;
  public optData(int[][] data){
    int[][] optData = new int[data.length][data[0].length];
    for(int r = 0; r < data.length; r++){
      for(int c = 0; c < data[r].length; c++){
        ArrayList<Integer> list = KnightBoard.possibleMoves(r,c);
        optData[r][c] = list.size();
      }
    }
    board = optData;
  }
}
