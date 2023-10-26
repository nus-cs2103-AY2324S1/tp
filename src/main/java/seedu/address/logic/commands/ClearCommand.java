package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "reset";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    public static final String MESSAGE_USAGE = "Enter 'reset confirm' if you are sure of erasing the Address Book!";
    public static final String MESSAGE_PROMPT = "THIS COMMAND WILL ERASE THE ENTIRE ADDRESS BOOK!\n"
            + "ARE YOU SURE YOU WANT TO DO THIS?\n"
            + "IF YOU ARE SURE ENTER: 'reset confirm'";

    private final String confirmation;

    public ClearCommand(String confirmation) {
        this.confirmation = confirmation;
    }


    @Override
    public CommandResult execute(Model model) {
        if (Objects.equals(this.confirmation, "confirm")) {
            requireNonNull(model);
            model.setAddressBook(new AddressBook());
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_PROMPT);
        }

    }
}
