package transact.logic.commands;

import static java.util.Objects.requireNonNull;

import transact.model.AddressBook;
import transact.model.Model;
import transact.ui.MainWindow.TabWindow;

/**
 * Clears the address book.
 */
public class ClearStaffCommand extends Command {

    public static final String COMMAND_WORD = "clearstaff";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS, TabWindow.ADDRESSBOOK, false, false, true, false);
    }
}
