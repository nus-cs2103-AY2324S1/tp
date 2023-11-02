package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.trendresults.TrendCommandResult;
import seedu.address.model.Model;

/**
 * Generates a (Number of Students) ~ (Time) graph for trend analyzing.
 */
public class TrendCommand extends Command {

    public static final String COMMAND_WORD = "trend";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the trend of the data.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_TREND_MESSAGE = "Displayed trend";

    public static final String MESSAGE_SUCCESS = "Trend displayed!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new TrendCommandResult();
    }
}
