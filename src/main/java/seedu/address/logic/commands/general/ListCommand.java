package seedu.address.logic.commands.general;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BANDS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MUSICIANS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all musicians in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all musicians and all bands";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredMusicianList(PREDICATE_SHOW_ALL_MUSICIANS);
        model.updateFilteredBandList(PREDICATE_SHOW_ALL_BANDS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
