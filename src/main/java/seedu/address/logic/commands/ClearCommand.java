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
    public static final String MESSAGE_USAGE = "This command will erase the entire Address Book\n"
            + "Are you sure you want to do this? "
            + "If you are sure, Enter 'reset confirm'";

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
            return new CommandResult(MESSAGE_USAGE);
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ClearCommand other = (ClearCommand) obj;
        return Objects.equals(this.confirmation, other.confirmation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(confirmation);
    }
}
