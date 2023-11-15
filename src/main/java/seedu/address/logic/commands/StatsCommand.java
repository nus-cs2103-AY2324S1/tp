package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Calculates the requested statistic about the currently displayed list.
 */
public abstract class StatsCommand extends Command {
    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the requested statistic about the currently displayed list.\n"
            + "Examples: \n"
            + COMMAND_WORD + " " + StatsAvailCommand.COMMAND_WORD + " \n"
            + COMMAND_WORD + " " + StatsCurrentCommand.COMMAND_WORD + " \n"
            + COMMAND_WORD + " " + StatsHousingCommand.COMMAND_WORD;

    public static final String MESSAGE_NO_FOSTERERS = "No fosterers to generate statistics from. "
            + "Please add some fosterers!";

    /**
     * Calculates percentage using a numerator and denominator.
     * Denominator must be a non-zero value.
     */
    protected static double calculatePercentage(int num, int denom) {
        assert(denom != 0);
        return num / (double) denom * 100;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
