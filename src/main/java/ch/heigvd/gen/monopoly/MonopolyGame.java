package ch.heigvd.gen.monopoly;

import ch.heigvd.gen.monopoly.random.Cup;
import java.util.ArrayList;
import java.util.List;

public class MonopolyGame {

  private static final int DEFAULT_NUMBER_PLAYER = 3;
  private static final int NUMBER_OF_ROUNDS = 20;

  private final Board board;
  private final List<Player> players;
  private final Cup cup;

  private int roundCnt;

  /**
   * Monopoly Game default constructor.
   *
   * <p>If you do not want to specify the number of players, the game will be initialized with the
   * default value</p>
   */
  public MonopolyGame() {
    this(DEFAULT_NUMBER_PLAYER);
  }

  /**
   * Monopoly Game constructor.
   *
   * <p>Will initialize the game</p>
   *
   * @param numOfPlayer The number of players you want in the game
   */
  public MonopolyGame(int numOfPlayer) {
    if (numOfPlayer < 2 || numOfPlayer > 8) {
      throw new IllegalArgumentException("You must provide between 2 and 8 players.");
    }
    this.board = new Board();
    this.players = new ArrayList<>();
    this.cup = new Cup();

    for (int i = 0; i < numOfPlayer; i++) {
      this.players.add(new Player(
              String.format("Player #%d", i + 1),
              this.board.getInitialSquare()
          )
      );
    }
  }

  /**
   * The main entry point of the program.
   *
   * @param args The input arguments.
   */
  public static void main(String[] args) {

    if (args.length != 1) {
      throw new ArrayIndexOutOfBoundsException("Please use 1 argument.");
    }

    int players = Integer.parseInt(args[0]);
    System.out.println(String.format("You're playing Monopoly with %d players !", players));
    MonopolyGame game = new MonopolyGame(players);
    game.playGame();
  }

  /**
   * Launches the game.
   */
  public void playGame() {
    do {
      System.out.println("--- Turn " + (roundCnt + 1) + " ---");
      playRound();
      roundCnt++;
    } while (roundCnt < NUMBER_OF_ROUNDS);
  }

  private void playRound() {
    for (Player p : players) {
      p.takeTurn(cup, board);
    }
  }

}
