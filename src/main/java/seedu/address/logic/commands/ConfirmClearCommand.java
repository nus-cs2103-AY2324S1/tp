package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.NpcTrack;


/**
 * Clears the address book.
 */
public class ConfirmClearCommand extends Command {
    public static final String COMMAND_WORD = "yes";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears the address book. "
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new NpcTrack());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
