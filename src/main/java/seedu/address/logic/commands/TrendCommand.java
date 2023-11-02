package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.trendresults.TrendCommandResult;
import seedu.address.model.Model;

import java.time.Year;

/**
 * Generates a (Number of Students) ~ (Time) graph for trend analyzing.
 */
public class TrendCommand extends Command {

    public static final String COMMAND_WORD = "trend";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the trend of the data "
            + "of a year. \n"
            + "Parameters: y/[YEAR]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_YEAR + "2020";

    public static final String SHOWING_TREND_MESSAGE = "Displayed trend";

    public static final String MESSAGE_SUCCESS = "Trend displayed!";

    private final Year year;

    /**
     * Constructs a {@code TrendCommand} instance using Year.
     * @param year The year such that the data during this year is to be analyzed.
     */
    public TrendCommand(Year year) {
        this.year = year;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new TrendCommandResult();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TrendCommand)) {
            return false;
        }

        TrendCommand otherTrendCommand = (TrendCommand) other;

        return this.year.equals(otherTrendCommand.year);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("year ", year)
                .toString();
    }
}
