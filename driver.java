public class driver{
  public static void main(String[] args) {
    KnightBoard board = new KnightBoard(4,6);
    System.out.println(board.countSolutions(0,0));
    System.out.println(board);
  }
}
