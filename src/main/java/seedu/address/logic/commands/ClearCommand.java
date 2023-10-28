package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "HealthSync has been cleared!";
    public static final String MESSAGE_UNDO_SUCCESS = "Undoing the clearing of HealthSync data.";

    private AddressBook addressBookBeforeClear;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        // Store a copy of the current address book before clearing
        addressBookBeforeClear = new AddressBook(model.getAddressBook());
        model.addToHistory(this);

        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
    @Override
    public CommandResult undo(Model model) {
        requireNonNull(model);
        model.setAddressBook(addressBookBeforeClear);
        return new CommandResult(MESSAGE_UNDO_SUCCESS);
    }
}
