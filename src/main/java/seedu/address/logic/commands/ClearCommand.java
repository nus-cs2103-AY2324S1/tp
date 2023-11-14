package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.ui.ConfirmationPopup;

/**
 * Clears the address book with a confirmation popup.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";

    public static final String MESSAGE_SUCCESS = "All student data has been cleared.";

    public static final String SHOWING_CONFIRMATION_MESSAGE = "Opened confirmation window. "
            + "Please ensure you use the exit command when exiting StudentConnect for successful reset.";

    private boolean isConfirmed = false;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        ConfirmationPopup confirmationPopup = new ConfirmationPopup();
        confirmationPopup.setConfirmationCallback(confirmed -> {
            if (confirmed) {
                this.isConfirmed = true;
                model.setAddressBook(new AddressBook());
            }
        });
        confirmationPopup.show();

        if (isConfirmed) {
            return new CommandResult(SHOWING_CONFIRMATION_MESSAGE, false, false, false, true);
        } else {
            return new CommandResult(SHOWING_CONFIRMATION_MESSAGE, false, false, false, false);
        }
    }

    /**
     * Sets the confirmation status of the popup.
     *
     * @param isConfirmed The confirmation status to be set. True if confirmed, false otherwise.
     */
    public void setConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }
}
