package seedu.address.logic.commands;

/**
 * Calculates the requested statistic about the currently displayed list.
 */
public abstract class StatsCommand extends Command {
    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the requested statistic about the currently displayed list.\n"
            + "Parameters: FIELD (which can take the values avail, current or housing).\n"
            + "Examples: \n"
            + COMMAND_WORD + " " + StatsAvailCommand.COMMAND_WORD + " \n"
            + COMMAND_WORD + " " + StatsCurrentCommand.COMMAND_WORD + " \n"
            + COMMAND_WORD + " " + StatsHousingCommand.COMMAND_WORD;

    public float calculatePercentage(int num, int denom) {
        return num / (float) denom * 100;
    }
}
