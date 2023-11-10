package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatsCommandTest {

    @Test
    public void calculatePercentage_demomZero() {
        assertThrows(AssertionError.class, () -> {
            StatsCommand.calculatePercentage(1, 0);
        });
    }

    @Test
    public void calculatePercentage() {

        assertEquals(0.0, StatsCommand.calculatePercentage(0, 10));
        assertEquals(3 / (double) 10 * 100, StatsCommand.calculatePercentage(3, 10));
        assertEquals(10 / (double) 3 * 100, StatsCommand.calculatePercentage(10, 3));
        assertEquals(-9 / (double) 10 * 100, StatsCommand.calculatePercentage(-9, 10));
        assertEquals(-9 / (double) -10 * 100, StatsCommand.calculatePercentage(-9, -10));
    }
}
