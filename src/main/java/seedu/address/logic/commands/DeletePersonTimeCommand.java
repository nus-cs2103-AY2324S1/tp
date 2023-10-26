package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TimeInterval;
import seedu.address.model.person.Name;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

public class DeletePersonTimeCommand extends DeleteTimeCommand {
    private final ArrayList<TimeInterval> timeIntervalsToDelete;
    private final Name personName;

    public DeletePersonTimeCommand(Name personName, ArrayList<TimeInterval> timeIntervalsToDelete) {
        requireNonNull(personName);
        requireNonNull(timeIntervalsToDelete);
        this.personName = personName;
        this.timeIntervalsToDelete = timeIntervalsToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasPerson(personName)) {
            model.deleteTimeFromPerson(personName, timeIntervalsToDelete);
        } else {
            throw new CommandException("Person does not exists");
        }
        return new CommandResult(String.format(MESSAGE_DELETE_TIME_SUCCESS, personName.fullName));
    }

}
