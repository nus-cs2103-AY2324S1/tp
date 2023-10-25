package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.band.Band;
import seedu.address.model.musician.Musician;

import java.util.function.Predicate;

/**
 * Lists all musicians in a band input by the user.
 */
public class ListBandCommand extends Command {

    public static final String COMMAND_WORD = "listb";

    public static final Object MESSAGE_USAGE = COMMAND_WORD + ": Finds all musicians who are members of "
        + "the band with a specific band name and displays them as a list with index numbers.\n"
        + "Parameters: BAND NAME\n"
        + "Example: " + COMMAND_WORD + " BlackPink";

    private final Predicate<Band> predicate;

    public ListBandCommand(Predicate<Band> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredBandMusicianList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_MUSICIANS_LISTED_OVERVIEW, model.getFilteredMusicianList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListBandCommand)) {
            return false;
        }

        ListBandCommand otherListBandCommand = (ListBandCommand) other;
        return predicate.equals(otherListBandCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
