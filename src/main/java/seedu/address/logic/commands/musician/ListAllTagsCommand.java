package seedu.address.logic.commands.musician;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.tag.Genre;
import seedu.address.model.tag.Instrument;

/**
 * Lists all valid instrument tags and genre tags to the user in the message box.
 */
public class ListAllTagsCommand extends Command {

    public static final String COMMAND_WORD = "tags";

    public static final String MESSAGE_SUCCESS = "Listed all valid instrument tags and genre tags below:\n"
            + "Instrument tags: " + Instrument.VALID_INSTRUMENTS + "\n"
            + "Genre tags: " + Genre.VALID_GENRES;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
