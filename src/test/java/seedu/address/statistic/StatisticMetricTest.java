package seedu.address.statistic;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.statistics.StatisticMetric;


public class StatisticMetricTest {

    @Test
    public void isValidMetric() {
        // null metric
        assertThrows(NullPointerException.class, () -> StatisticMetric.isValidMetric(null));

        // invalid metrics
        assertFalse(StatisticMetric.isValidMetric("")); // empty string
        assertFalse(StatisticMetric.isValidMetric("notametric")); // random string
        assertFalse(StatisticMetric.isValidMetric("scores")); // additional s

        // valid metrics
        assertTrue(StatisticMetric.isValidMetric("SCORE"));
        assertTrue(StatisticMetric.isValidMetric("mean"));
        assertTrue(StatisticMetric.isValidMetric("MeaN"));
        assertTrue(StatisticMetric.isValidMetric("median"));
        assertTrue(StatisticMetric.isValidMetric("PERCENTILE"));
    }

    @Test
    public void isScoreRelevant() {
        assertThrows(NullPointerException.class, () -> StatisticMetric.isScoreRelevant(null));

        // Score and percentile are relevant
        assertTrue(StatisticMetric.isScoreRelevant("SCORE"));
        assertTrue(StatisticMetric.isScoreRelevant("PERCENTILE"));

        // Mean median are not relevant
        assertFalse(StatisticMetric.isScoreRelevant("MEAN"));
        assertFalse(StatisticMetric.isScoreRelevant("MEDIAN"));
    }

}
