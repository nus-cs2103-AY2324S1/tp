package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GROUPS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;

/**
 * Deletes a student identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student identified by the email address.\n"
            + "Parameters: EMAIL\n"
            + "Example: " + COMMAND_WORD + " alexyeoh@u.nus.edu";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Student deleted Successfully! Deleted Student: %1$s";
    public static final String MESSAGE_DELETE_EMAIL_NOT_FOUND = "Student with the provided email not found.";
    private final Email targetEmail;

    public DeleteCommand(Email targetEmail) {
        this.targetEmail = targetEmail;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        requireNonNull(model);

        // Find the person with the provided email
        Optional<Person> personToDelete = model.getPersonWithEmail(targetEmail);

        if (personToDelete.isEmpty()) {
            throw new CommandException(MESSAGE_DELETE_EMAIL_NOT_FOUND);
        }

        // Check if the person is in any groups
        for (Group group : model.getAddressBook().getGroupList()) {
            if (group.hasMember(personToDelete.get())) {
                // If the person is in a group, remove them from the group
                model.removePersonFromGroup(personToDelete.get(), group);
            }
        }

        // Delete the person from the model
        model.deletePerson(personToDelete.get());

        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete.get())));
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
        return targetEmail.equals(otherDeleteCommand.targetEmail);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetEmail", targetEmail)
                .toString();
    }
}
