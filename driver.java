public class driver{
  public static void main(String[] args) {
    KnightBoard board = new KnightBoard(5,4);
    System.out.println(board);
    System.out.println(board.countSolutions(0,0));
    System.out.print(board);
  }
}
