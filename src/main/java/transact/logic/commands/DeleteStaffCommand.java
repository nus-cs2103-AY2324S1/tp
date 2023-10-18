package transact.logic.commands;

import static java.util.Objects.requireNonNull;

import transact.commons.util.ToStringBuilder;
import transact.logic.Messages;
import transact.logic.commands.exceptions.CommandException;
import transact.model.Model;
import transact.model.person.Person;
import transact.model.person.PersonId;
import transact.ui.MainWindow.TabWindow;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteStaffCommand extends Command {

    public static final String COMMAND_WORD = "delstaff";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DELETE_PERSON_FAILURE = "Cannot find person with id: %d";
    private final Integer personId;

    public DeleteStaffCommand(Integer personId) {
        this.personId = personId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        for (PersonId id : model.getAddressBook().getPersonMap().keySet()) {
            if (id.getValue() == personId) {
                Person deletedPerson = model.deletePerson(id);
                return new CommandResult(
                        String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(deletedPerson)),
                        TabWindow.ADDRESSBOOK);
            }
        }
        return new CommandResult(
                String.format(MESSAGE_DELETE_PERSON_FAILURE, personId),
                TabWindow.ADDRESSBOOK);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteStaffCommand)) {
            return false;
        }

        DeleteStaffCommand otherDeleteStaffCommand = (DeleteStaffCommand) other;
        return personId.equals(otherDeleteStaffCommand.personId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("personId", personId)
                .toString();
    }
}
