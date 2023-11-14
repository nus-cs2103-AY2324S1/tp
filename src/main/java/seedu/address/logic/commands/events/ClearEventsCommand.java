package seedu.address.logic.commands.events;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.EventsBook;
import seedu.address.model.Model;


/**
 * Clears the events book.
 */
public class ClearEventsCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Events book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setEventsBook(new EventsBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
