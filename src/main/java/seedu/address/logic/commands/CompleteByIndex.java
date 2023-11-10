package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Completes and removes appointments from person in address book specified by index.
 */
public class CompleteByIndex extends CompleteCommand {
    public final Index index;

    public CompleteByIndex(Index index) {
        this.index = index;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        if (personToEdit.hasNullAppointment()) {
            throw new CommandException(MESSAGE_PERSON_NO_APPOINTMENT);
        }

        Person personWithoutAppointment = personToEdit.clearAppointment();

        model.setPerson(personToEdit, personWithoutAppointment);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(MESSAGE_COMPLETE_SUCCESS);
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles null
        if (!(other instanceof CompleteByIndex)) {
            return false;
        }

        CompleteByIndex otherCommand = (CompleteByIndex) other;
        return this.index.equals(otherCommand.index);
    }
}
