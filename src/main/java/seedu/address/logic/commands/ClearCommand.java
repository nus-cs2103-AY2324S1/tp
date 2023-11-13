package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Employee list has been cleared!";


    @Override
    public CommandResult execute(Model model, String commandText) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        model.addCommandText(commandText);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
