package seedu.address.model.statistics;

/**
 * Represents the different metrics that can be used to calculate the statistics.
 */
public enum StatisticMetric {

    SCORE("SCORE"),
    MEAN("MEAN"),
    MEDIAN("MEDIAN"),
    PERCENTILE("PERCENTILE");
    private final String metricName;
    StatisticMetric(String metricName) {
        this.metricName = metricName;
    }
    @Override
    public String toString() {
        return this.metricName;
    }
    /**
     * Checks if the provided metric is a valid metric.
     *
     * @param input The string metric to be checked for validity.
     * @return {@code true} if the metric is valid, {@code false} otherwise.
     */
    public static boolean isValidMetric(String input) {
        String inputUpperCase = input.toUpperCase();
        return inputUpperCase.equals("SCORE") || inputUpperCase.equals("MEAN") || inputUpperCase.equals("MEDIAN")
                || inputUpperCase.equals("PERCENTILE");
    }

    /**
     * Checks if a score is needed for the metric.
     * @param input The string metricF to be checked.
     * @return {@code true} if the metric needs a score, {@code false} otherwise.
     */
    public static boolean isScoreRelevant(String input) {
        String inputUpperCase = input.toUpperCase();
        return inputUpperCase.equals("SCORE") || inputUpperCase.equals("PERCENTILE");
    }
}
