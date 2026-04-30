import java.util.List;

public class Banker {

    public static long calculateOffer(Game game) {
        List<Double> remaining = game.getRemainingPrizes();
        if (remaining.isEmpty())
            return 0;

        // Get the average of remaining prizes in one line
        double average = remaining.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

        // The banker gets more generous as rounds progress (max 100% of average)
        double fairness = Math.min(game.getCurrentRound() * 0.10, 1.0);

        return Math.round(average * fairness);
    }
}