package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    /**
     * Keyword to trigger the clear command.
     */
    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "All persons have been cleared!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clear all of persons\n"
            + "Example: " + COMMAND_WORD;


    /**
     * Executes the ClearCommand to clear the address book in the model.
     *
     * @param model The current model containing the address book data.
     * @return The result of the command execution.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
