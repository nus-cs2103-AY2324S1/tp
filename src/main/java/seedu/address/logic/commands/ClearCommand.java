package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Current list has been cleared!";
    public static final String MESSAGE_SUMMARY = "Clear: " + COMMAND_WORD + "\n";


    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.clearSortedFilteredPersonList();
        model.commitAddressBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
