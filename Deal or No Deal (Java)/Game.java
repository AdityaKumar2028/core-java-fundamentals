import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Game {

    public static final double[] ALL_PRIZES = {
            0.01, 1, 5, 10, 25, 50, 75, 100, 200, 300, 400, 500, 750, 1000,
            5000, 10000, 25000, 50000, 75000, 100000, 200000, 300000,
            400000, 500000, 750000, 1000000
    };

    // Cases opened per round
    private static final int[] ROUND_SIZES = { 6, 5, 4, 3, 2, 1, 1, 1, 1, 1 };

    private double[] caseValues = new double[26];
    private boolean[] caseOpened = new boolean[26];
    private List<Double> remaining;

    private int myCase, currentRound, casesLeft;
    private boolean gameOver;

    public Game() {
        init();
    }

    public void init() {
        myCase = -1;
        gameOver = false;
        currentRound = casesLeft = 0;
        Arrays.fill(caseOpened, false);

        // Populate remaining list and shuffle values into cases
        remaining = Arrays.stream(ALL_PRIZES).boxed().collect(Collectors.toList());
        List<Double> shuffled = new ArrayList<>(remaining);
        Collections.shuffle(shuffled);

        for (int i = 0; i < 26; i++) {
            caseValues[i] = shuffled.get(i);
        }
    }

    public void pickMyCase(int n) {
        myCase = n;
        startNextRound();
    }

    public double openCase(int n) {
        int i = n - 1;
        if (i < 0 || i >= 26 || caseOpened[i] || n == myCase)
            return -1;

        caseOpened[i] = true;
        double prize = caseValues[i];
        remaining.remove(prize);
        casesLeft--;
        return prize;
    }

    public void startNextRound() {
        casesLeft = currentRound < ROUND_SIZES.length ? ROUND_SIZES[currentRound] : 1;
        currentRound++;
    }

    // ── Getters & Helpers ──────────────────────────────────────────────────

    public int getCasesLeftThisRound() {
        return casesLeft;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public int getMyCase() {
        return myCase;
    }

    public double getMyCasePrize() {
        return caseValues[myCase - 1];
    }

    public boolean isCasePicked() {
        return myCase != -1;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isRoundOver() {
        return casesLeft == 0;
    }

    public void setGameOver() {
        gameOver = true;
    }

    public List<Double> getRemainingPrizes() {
        return remaining;
    }

    // Remaining unopened cases (excluding the player's case)
    public int getUnopenedCount() {
        return remaining.size() - 1;
    }

    // Use built-in Java currency formatter
    public static String formatMoney(double amount) {
        if (amount < 1)
            return String.format("$%.2f", amount);
        return NumberFormat.getCurrencyInstance(Locale.US).format(amount).replace(".00", "");
    }
}