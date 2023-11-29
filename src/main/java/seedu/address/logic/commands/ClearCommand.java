package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "ToothTracker has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        AddressBook newAb = new AddressBook();
        newAb.setPatientId(1);
        newAb.setDentistId(1);
        newAb.setAppointmentId(1);
        model.setAddressBook(newAb);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
