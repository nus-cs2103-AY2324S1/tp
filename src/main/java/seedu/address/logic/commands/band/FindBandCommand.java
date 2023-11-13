package seedu.address.logic.commands.band;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.band.Band;

/**
 * Lists all musicians in a band input by the user.
 */
public class FindBandCommand extends Command {

    public static final String COMMAND_WORD = "findb";

    public static final Object MESSAGE_USAGE = COMMAND_WORD + ": Finds all musicians who are members of "
        + "the band with a specific band name and displays them as a list with index numbers.\n"
        + "Parameters: BAND NAME\n"
        + "Example: " + COMMAND_WORD + " BlackPink";

    public static final String MESSAGE_SUCCESS = "There are %1$d musicians in the band %2$s";
    public static final String MESSAGE_NO_BAND_IN_LIST = "No bands yet." + "\n" + "Please create a band first!";
    private final Predicate<Band> predicate;

    public FindBandCommand(Predicate<Band> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.getFilteredBandList().isEmpty()) {
            throw new CommandException(MESSAGE_NO_BAND_IN_LIST);
        }

        model.updateFilteredBandMusicianList(predicate);
        // If the band exists, filtered band list is guaranteed to have only one band,
        // (because add a band enforce no band with the same name (case-insensitive) is allowed).
        // If filtered band list size > 1 or size == 1 but the band filtered does not pass the predicate,
        // it means that the band name is invalid, exception is thrown.
        if (model.getFilteredBandList().size() > 1 || !predicate.test(model.getFilteredBandList().get(0))) {
            throw new CommandException(Messages.MESSAGE_UNKNOWN_BAND);
        }

        return new CommandResult(
            String.format(MESSAGE_SUCCESS, model.getFilteredMusicianList().size(),
                    model.getFilteredBandList().get(0).getName())
        );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindBandCommand)) {
            return false;
        }

        FindBandCommand otherFindBandCommand = (FindBandCommand) other;
        return predicate.equals(otherFindBandCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
