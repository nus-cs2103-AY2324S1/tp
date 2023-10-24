package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.FreeTime;
import seedu.address.model.Model;
import seedu.address.model.person.Name;

import static java.util.Objects.requireNonNull;

public class ListTimePersonCommand extends ListTimeCommand{
    public static final String MESSAGE_LISTTIME_PERSON_SUCCESS = "Listed times of Person: %1$s";
    public static final String MESSAGE_NO_PERSON_WITH_NAME_FOUND = "No person with such name found.\n"
            + "Please provide the person's full name as in the existing contactlist.";
    private final Name personName;

    public ListTimePersonCommand(Name personName) {
        this.personName = personName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasPerson(personName)) {
            throw new CommandException(MESSAGE_NO_PERSON_WITH_NAME_FOUND);
        }
        FreeTime freetime = model.getFreeTimeFromPerson(personName);
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
