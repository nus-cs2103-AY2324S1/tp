package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.band.BandNameContainsKeywordsPredicate;

/**
 * Lists all musicians in a band input by the user.
 */
public class ListBandCommand extends Command {

    public static final String COMMAND_WORD = "list band";

    public static final Object MESSAGE_USAGE = COMMAND_WORD + ": Finds all musicians who are part of "
        + "the specified band (case-insensitive) and displays them as a list with index numbers.\n"
        + "Parameters: BAND_NAME...\n"
        + "Example: " + COMMAND_WORD + " ace jazz";

    private final BandNameContainsKeywordsPredicate predicate;

    public ListBandCommand(BandNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredBandList(predicate);
        model.updateFilteredMusicianListFromBands();
        return new CommandResult(
            String.format(Messages.MESSAGE_MUSICIANS_LISTED_OVERVIEW, model.getFilteredMusicianList().size()));
    }
}
