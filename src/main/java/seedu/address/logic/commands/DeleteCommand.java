package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by specified nric.\n"
            + "Parameters: nric (must be a valid nric belonging to a person in the contact list)\n"
            + "Example: " + COMMAND_WORD + " T0333789H";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Ic targetIc;

    public DeleteCommand(Ic targetIc) {
        this.targetIc = targetIc;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // combine doctor list and patient list
        List<Person> lastShownList = new ArrayList<>();
        lastShownList.addAll(model.getFilteredDoctorList());
        lastShownList.addAll(model.getFilteredPatientList());

        for (Person target : lastShownList) {
            if (targetIc.equals(target.getIc())) {
                model.deletePerson(target);
                return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(target)));
            }
        }
        throw new CommandException(Messages.MESSAGE_PERSON_NOT_FOUND);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetIc.equals(otherDeleteCommand.targetIc);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIc", targetIc)
                .toString();
    }
}
