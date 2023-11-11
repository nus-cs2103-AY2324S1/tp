package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TimeIntervalList;
import seedu.address.model.person.Name;

/**
 * Lists the time of a person
 */
public class ListTimePersonCommand extends ListTimeCommand {
    public static final String MESSAGE_LISTTIME_PERSON_SUCCESS = "Listed times of Person: %1$s";
    public static final String MESSAGE_NO_PERSON_WITH_NAME_FOUND = "No person with such name found.\n"
            + "Please provide the person's full name as in the existing contactlist.";
    private final Name personName;

    /**
     * Constructor of ListTimePersonCommand
     *
     * @param personName Name of person to list time for.
     */
    public ListTimePersonCommand(Name personName) {
        requireNonNull(personName);
        this.personName = personName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasPerson(personName)) {
            throw new CommandException(MESSAGE_NO_PERSON_WITH_NAME_FOUND);
        }
        TimeIntervalList freetime = model.getTimeFromPerson(personName);
        return new CommandResult(String.format(MESSAGE_LISTTIME_PERSON_SUCCESS, personName.fullName) + freetime);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListTimePersonCommand)) {
            return false;
        }

        ListTimePersonCommand otherListTimePersonCommand = (ListTimePersonCommand) other;
        return personName.equals(otherListTimePersonCommand.personName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("person name", personName)
                .toString();
    }
}
