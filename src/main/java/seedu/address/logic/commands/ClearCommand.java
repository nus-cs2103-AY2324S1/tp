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
    public static final String MESSAGE_USAGE = "The 'reset' command will erase the entire Address Book.\n"
            + "Are you sure you want to do this? "
            + "If you are sure, Enter 'reset confirm'.";
    public static final String MESSAGE_PROMPT = "To reset the entire Address Book, "
            + "enter 'reset' followed by 'reset confirm'.";

    private final String confirmation;
    private boolean finalConfirmation = false;

    /**
     * Constructs a ClearCommand with the provided confirmation.
     *
     * @param confirmation The user's confirmation. Must be "confirm" to execute the clear command.
     * @throws NullPointerException if the confirmation is null.
     */
    public ClearCommand(String confirmation) {
        if (confirmation == null) {
            throw new NullPointerException("Confirmation cannot be null.");
        }
        this.confirmation = confirmation;
    }


    @Override
    public CommandResult execute(Model model) {
        if (Objects.equals(this.confirmation, "confirm") && this.finalConfirmation) {
            requireNonNull(model);
            model.setAddressBook(new AddressBook());
            return new CommandResult(MESSAGE_SUCCESS);
        } else if (Objects.equals(this.confirmation, "confirm")) {
            return new CommandResult(MESSAGE_PROMPT);
        } else {
            return new CommandResult(MESSAGE_USAGE, CommandType.CLEAR);
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

    @Override
    public String toString() {
        this.finalConfirmation = true;
        return this.confirmation;
    }
}
