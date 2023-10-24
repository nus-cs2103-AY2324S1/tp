package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.FreeTime;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class ListTimePersonCommand extends ListTimeCommand{
    public static final String MESSAGE_LISTTIME_PERSON_SUCCESS = "Listed times of Person: %1$s";
    private final String personName;

    public ListTimePersonCommand(String personName) {
        this.personName = personName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);
        FreeTime freetime = model.getFreeTimeFromPerson(personName);

        return new CommandResult(String.format(MESSAGE_LISTTIME_PERSON_SUCCESS, personName) + freetime);
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
