package seedu.lovebook.logic.commands;

import static seedu.lovebook.logic.Messages.MESSAGE_INVALID_PREFIX;
import static seedu.lovebook.logic.Messages.MESSAGE_INVALID_SEQUENCE;
import static seedu.lovebook.logic.Messages.MESSAGE_SORTED;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_HOROSCOPE;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_INCOME;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Comparator;

import seedu.lovebook.commons.util.ToStringBuilder;
import seedu.lovebook.logic.commands.exceptions.CommandException;
import seedu.lovebook.logic.parser.Prefix;
import seedu.lovebook.model.Model;
import seedu.lovebook.model.date.Date;

/**
 * Sorts all dates in LoveBook alphabetically or numerically.
 * @author lynnlow175
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all dates in LoveBook alphabetically "
            + "or numerically based on ONE metric.\n"
            + "Parameters: name/ OR age/ OR height/ OR income/ OR horoscope/ + increasing OR decreasing" + "\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "increasing";
    public static final String SEQUENCE_ASCENDING = "increasing";
    public static final String SEQUENCE_DESCENDING = "decreasing";
    public final Prefix prefix;
    public final String sequence;

    /**
     * Creates a SortCommand to sort the specified {@code Date}
     * @param prefix
     * @param sequence
     */
    public SortCommand(Prefix prefix, String sequence) {
        this.prefix = prefix;
        this.sequence = sequence;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Comparator<Date> c;
        if (sequence.equals(SortCommand.SEQUENCE_ASCENDING)) {
            if (prefix.equals(PREFIX_NAME)) {
                c = Comparator.comparing(Date::getName);
            } else if (prefix.equals(PREFIX_INCOME)) {
                c = Comparator.comparing(Date::getIncome);
            } else if (prefix.equals(PREFIX_AGE)) {
                c = Comparator.comparing(Date::getAge);
            } else if (prefix.equals(PREFIX_HEIGHT)) {
                c = Comparator.comparing(Date::getHeight);
            } else if (prefix.equals(PREFIX_HOROSCOPE)) {
                c = Comparator.comparing(Date::getHoroscope);
            } else {
                throw new CommandException(MESSAGE_INVALID_PREFIX);
            }
        } else if (sequence.equals(SortCommand.SEQUENCE_DESCENDING)) {
            if (prefix.equals(PREFIX_NAME)) {
                c = Comparator.comparing(Date::getName).reversed();
            } else if (prefix.equals(PREFIX_INCOME)) {
                c = Comparator.comparing(Date::getIncome).reversed();
            } else if (prefix.equals(PREFIX_AGE)) {
                c = Comparator.comparing(Date::getAge).reversed();
            } else if (prefix.equals(PREFIX_HEIGHT)) {
                c = Comparator.comparing(Date::getHeight).reversed();
            } else if (prefix.equals(PREFIX_HOROSCOPE)) {
                c = Comparator.comparing(Date::getHoroscope).reversed();
            } else {
                throw new CommandException(MESSAGE_INVALID_PREFIX);
            }
        } else {
            throw new CommandException(MESSAGE_INVALID_SEQUENCE);
        }
        model.updateSortedDateList(c);
        return new CommandResult(MESSAGE_SORTED);
    }

    public Prefix getPrefix() {
        return this.prefix;
    }

    public String getSequence() {
        return this.sequence;
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        SortCommand otherSortCommand = (SortCommand) other;
        return prefix.equals((otherSortCommand.getPrefix())) && sequence.equals(otherSortCommand.getSequence());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("prefix", prefix)
                .add("sequence", sequence)
                .toString();
    }
}
