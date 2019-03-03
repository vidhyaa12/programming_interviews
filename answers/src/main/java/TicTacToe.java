import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

interface MoveMethod {
  public int move();
}

class SimpleMoveStrategy implements MoveMethod {
  private TicTacToe game;

  public SimpleMoveStrategy(TicTacToe t) {
    game = t;
  }

  public int moveOnFirstAvailablePos() {
    for (int i = 0; i < TicTacToe.N; i++) {
      for (int j = 0; j < TicTacToe.N; j++) {
        if (game.board[i][j] == 0)
          return (i * 3 + j + 1);
      }
    }
    return 0;
  }

  public int move() {

    int pos = getBestPositionToMove(game.N - 1, 1); // grab winning position
    if (pos != -1) {
      return pos;
    }

    for (int t = game.N - 1 ; t > 0;t--) {
      pos = getBestPositionToMove(t, 2); // block opponent's winning position

      if (pos == -1) {
        pos = getBestPositionToMove(t - 1, 1);
        if (pos != -1) {
          return pos;
        }
      } else {
        return pos;
      }
    }
    System.out.println("playing random move!");
    return moveOnFirstAvailablePos();
  }

  public int getBestPositionToMove(int numPositionsTakenByPlayer, int playerIdentifier) {

    int[] rowSum = new int[game.N];
    int[] colSum = new int[game.N];
    int[] diagSum = new int[game.N];
    for (int i=0;i<game.N;i++) {
      rowSum[i] = colSum[i] = diagSum[i] = 0;
    }
    for (int row = 0; row < TicTacToe.N; row++) { // row
      for (int col = 0; col < TicTacToe.N; col++) { // col
        if (game.board[row][col] == playerIdentifier) {
          rowSum[row]++;
          colSum[col]++;
          if (row == col) {
            diagSum[0]++;//y=x diagonal
          } else if (row == game.N - col - 1) {
            diagSum[1]++;//y= 3 - x diagonal
          }
        }
      }
    }

    // First look for row/col/diag where there are 2 slots taken by opponent
    for (int i = 0; i < game.N; i++) {
      if (rowSum[i] == numPositionsTakenByPlayer) {
        for (int j = 0; j < game.N; j++) {
          if (game.board[i][j] == 0) {
            return (i * game.N + j + 1);
          }
        }
      }
    }

    for (int col = 0; col < game.N; col++) {
      if (colSum[col] == numPositionsTakenByPlayer) {
        for (int row = 0; row < game.N; row++) {
          if (game.board[row][col] == 0) {
            return (row * game.N + col + 1);
          }
        }
      }
    }

    if (diagSum[0] == numPositionsTakenByPlayer) {
      for (int i = 0; i < game.N; i++) {
        if (game.board[i][i] == 0) {
          return (i * game.N + i + 1);
        }
      }
    }

    if (diagSum[1] == numPositionsTakenByPlayer) {
      for (int i = game.N - 1; i >= 0; i--) {
        if (game.board[i][game.N - i - 1] == 0) {
          return (i * game.N + (game.N - i - 1) + 1);
        }
      }
    }
    return -1;
  }
}

class HumanMove implements MoveMethod {
  private TicTacToe game;

  public HumanMove(TicTacToe t) {
    game = t;
  }

  public int move() {

    String move_str;
    int move_int = 0;
    boolean valid_input = false;
    while (!valid_input) {
      System.out.print("Where to ? ");
      move_str = TicTacToe.getUserInput();
      if (Character.isDigit(move_str.toCharArray()[0])) {
        move_int = Integer.parseInt(move_str);
        if ((move_int <= (TicTacToe.N) * (TicTacToe.N)) &&
            move_int >= 1) {
          valid_input = true;
          break;
        }
      }

      if (!valid_input) {
        System.out.println("Invalid input");
        continue;
      }
    }
    return move_int;
  }

}

class TicTacToe {
  protected static final int N = 5;
  private static final int HSPACE = 20;
  protected int[][] board;
  private static BufferedReader reader =

      new BufferedReader(new InputStreamReader(System.in));

  class Player {
    private String name;
    private int player_type;
    private int player_order;
    private MoveMethod move_strategy;

    public Player(String pname, int type,
                  int order, MoveMethod move_s) {
      name = pname;
      player_type = type;
      player_order = order;
      move_strategy = move_s;
    }

    public String getName() {
      return name;
    }

    public int getPlayerType() {
      return player_type;
    }

    public int getMove() {
      return move_strategy.move();
    }
  }

  private Player player1, player2;

  public Player getplayer1() {
    return player1;
  }

  public Player getplayer2() {
    return player2;
  }

  public static String getPosDescription(int pos) {
    String str = "";
    if (pos == (N * N + 1) / 2) {
      str = "center";
      return str;
    }

    if ((pos - 1) / N == 0) {
      str += "upper ";
    } else if ((pos - 1) / N == 1) {
      str += "middle ";
    } else
      str += "lower ";

    if ((pos - 1) % N == 0)
      str += "left";
    else if ((pos - 1) % N == 1)
      str += "middle";
    else
      str += "right";

    return str;
  }

  protected static String getUserInput() {
    String input = "";
    try {
      input = reader.readLine();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return input;
  }

  public TicTacToe() {
    board = new int[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        board[i][j] = 0;
      }
    }

    System.out.println("Enter player name");
    player1 = new Player(getUserInput(), 2, 0, new HumanMove(this));

    player2 = new Player("", 1, 1, new SimpleMoveStrategy(this));
    System.out.println("\nHuman player " + player1.getName() +
                       " vs Computer Player" + player2.getName() + ":");

  }

  public boolean setMove(int move, int p_type) {
    int x_coord = (move - 1) / N;
    int y_coord = (move - 1) % N;

    if (board[x_coord][y_coord] == 0) {
      board[x_coord][y_coord] = p_type;
      return true;
    } else {
      System.out.println("Invalid move");
      return false;
    }
  }

  private enum WinConfig {
    DRAW, WIN, NONE
  }

  private WinConfig isWinningConfig() {
    WinConfig w = WinConfig.WIN;
    // rows
    for (int i = 0; i < N; i++) {
      boolean isWinningRow = true;
      for (int j = 0; j < N; j++) {
        if ((board[i][0] == 0) || board[i][0] != board[i][j]) {
         isWinningRow = false;
         break;
        }
      }

      if (isWinningRow) {
        return w;
      }
    }

    // columns
    for (int col = 0; col < N; col++) {
      boolean isWinningCol = true;
      for (int row = 0; row < N; row++) {
        if ((board[0][col] == 0) || board[0][col] != board[row][col]) {
          isWinningCol = false;
          break;
        }
      }

      if (isWinningCol) {
        return w;
      }
    }

    // diagonal
    boolean isWiningDiag = true;
    for (int i = 0; i < N; i++) {
      if ((board[0][0] == 0) || board[0][0] != board[i][i]) {
        isWiningDiag = false;
        break;
      }
    }

    if (isWiningDiag) {
        return w;
    }

    boolean isWiningAntiDiag = true;
    for (int i = N - 1; i >= 0; i--) {
      if ((board[N - 1][0] == 0) || board[N - 1][0] != board[i][N - i - 1]) {
        isWiningAntiDiag = false;
        break;
      }
    }

    if (isWiningAntiDiag) {
      return w;
    }

    // draw
    w = WinConfig.DRAW;
    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++) {
        if (board[i][j] == 0)
          w = WinConfig.NONE;
      }
    return w;

  }

  private String getRowString(int row) {
    String s = "";
    for (int i = 0; i < N; i++) {
      switch (board[row][i]) {
        case 0:
          s += " ";
          break;
        case 1:
          s += "O";
          break;
        case 2:
          s += "X";
      }

      if (i != N - 1) {
        s += " | ";
      }
    }

    s += String.format("%" + HSPACE + "s", "");

    for (int i = 0; i < N; i++) {
      s += row * N + i + 1;

      if (i == N - 1) {
        s += "\n";
      } else {
        s += " | ";
      }
    }
    return s;
  }

  public String toString() {
    String s = "";
    // iterate through the rows
    for (int i = 0; i < N; i++) {
      s += getRowString(i);
    }
    return s;
  }

  public static void main(String[] args) {
    System.out.println("Welcome to Tic-Tac-Toe.");
    System.out.println("");

    TicTacToe game = new TicTacToe();
    String move_str;
    int move1 = 0;
    int move2 = 0;
    int player_type = 0;
    WinConfig w = WinConfig.NONE;

    System.out.println("Please make your move selection by entering "
                       + "a number 1-9 corresponding to the movement "
                       + "key on the right.\n");
    System.out.println(game.toString());

    while (game.isWinningConfig() == WinConfig.NONE) {
      do {
        move1 = game.getplayer1().getMove();
      } while (!game.setMove(move1, game.getplayer1().getPlayerType()));


      if ((w = game.isWinningConfig()) == WinConfig.WIN) {
        System.out.println("");
        System.out.println(game.toString());
        System.out.println("You have beaten my poor AI!");
        break;
      } else if (w == WinConfig.DRAW) {
        System.out.println("");
        System.out.println(game.toString());
        System.out.println("Well played. It is a draw!");
        break;
      }

      move2 = game.getplayer2().getMove();
      System.out.println("");
      System.out.println("You have put an X in the " +
                         TicTacToe.getPosDescription(move1) +
                         ". I will put a O in the " +
                         TicTacToe.getPosDescription(move2) + ".");

      game.setMove(move2, game.getplayer2().getPlayerType());

      if (game.isWinningConfig() == WinConfig.WIN) {
        System.out.println("");
        System.out.println(game.toString());
        System.out.println("I won. Thanks for playing.");
        break;
      }
      System.out.println(game.toString());
    }

  }
}