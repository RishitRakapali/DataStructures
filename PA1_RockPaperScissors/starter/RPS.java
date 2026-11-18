import java.util.Scanner;

public class RPS extends RPSAbstract {
    // Messages for the game.
    protected static final String GAME_NOT_IMPLEMENTED =
            "Game not yet implemented.";
    /**
     * Construct a new instance of RPS with the given possible moves.
     *
     * @param moves all possible moves in the game.
     */
    public RPS(String[] moves) {
        this.possibleMoves = moves;
        this.playerMoves = new String[MAX_GAMES];
        this.cpuMoves = new String[MAX_GAMES];
        this.recentPlayerMoves = new String[MAX_RECENT_WINS];
        this.recentCPUMoves = new String[MAX_RECENT_WINS];
    }

    public static void main(String[] args) {
        // If command line args are provided use those as the possible moves
        String[] moves = new String[args.length];
        if (args.length >= MIN_POSSIBLE_MOVES) {
            System.arraycopy(args, 0, moves, 0, args.length);
        } else {
            moves = RPS.DEFAULT_MOVES;
        }
        // Create new game and scanner
        RPS game = new RPS(moves);
        Scanner in = new Scanner(System.in);

        // While user does not input "q", play game
        while (true) {
            System.out.println(PROMPT_MOVE);

            String cpuMove = game.genCPUMove();
            String playerMove = in.nextLine();

            if (playerMove.equals(QUIT)) {
                break;
            }

            if (!game.isValidMove(playerMove)) {
                System.out.println(INVALID_INPUT);
                continue;
            }

            game.playRPS(playerMove, cpuMove);
        }

        game.displayStats();

        in.close();
    }

    @Override
    public int determineWinner(String playerMove, String cpuMove) {
        if (!isValidMove(playerMove) || !isValidMove(cpuMove)) {
            return INVALID_INPUT_OUTCOME;
        }

        int playerIndex = -1;
        int cpuIndex = -1;

        for (int i = 0; i < possibleMoves.length; i++) {
            if (possibleMoves[i].equals(playerMove)) {
                playerIndex = i;
            }

            if (possibleMoves[i].equals(cpuMove)) {
                cpuIndex = i;
            }
        }

        if (playerIndex == cpuIndex) {
            return TIE_OUTCOME;
        }

        if (playerIndex == 0 &&
                cpuIndex == possibleMoves.length - 1) {
            return PLAYER_WIN_OUTCOME;
        }

        if (cpuIndex == 0 &&
                playerIndex == possibleMoves.length - 1) {
            return CPU_WIN_OUTCOME;
        }

        if (playerIndex == cpuIndex + 1) {
            return PLAYER_WIN_OUTCOME;
        }

        if (cpuIndex == playerIndex + 1) {
            return CPU_WIN_OUTCOME;
        }

        return TIE_OUTCOME;
    }
}
