package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TimeInterval;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Deletes time from the individual and group.
 */
public class DeleteTimeCommand extends Command{
    public static final String MESSAGE_DELETE_TIME_SUCCESS = "Deleted Time: %1$s";
    public static final String COMMAND_WORD = "deletetime";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes free time from an existing person or group. \n"
            + "For person, parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_FREETIME + "FREE TIME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Alex Yeoh "
            + PREFIX_FREETIME + "mon 1200 - mon 1400 ;tue 1000 - wed 1600 \n"
            + "For group, parameters: "
            + PREFIX_GROUPTAG + "GROUPNAME "
            + PREFIX_FREETIME + "FREE TIME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUPTAG + "CS2103T "
            + PREFIX_FREETIME + "mon 1200 - mon 1400 ;tue 1000 - wed 1600";

    private final ArrayList<TimeInterval> timeIntervalsToDelete;
    private final Name personName;
    private final Group group;

    public DeleteTimeCommand(Group group, ArrayList<TimeInterval> timeIntervalsToDelete) {
        requireNonNull(group);
        requireNonNull(timeIntervalsToDelete);
        this.group = group;
        this.timeIntervalsToDelete = timeIntervalsToDelete;
        this.personName = null;
    }

    public DeleteTimeCommand(Name personName, ArrayList<TimeInterval> timeIntervalsToDelete) {
        requireNonNull(personName);
        requireNonNull(timeIntervalsToDelete);
        this.personName = personName;
        this.timeIntervalsToDelete = timeIntervalsToDelete;
        this.group = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

//        //delete time from group
//        if (toAddPerson == null) {
//
//        } else {
//            //delete time from person
//        }
        if (model.hasPerson(personName)) {
            model.deleteFreeTimeFromPerson(personName, timeIntervalsToDelete);
        } else {
            throw new CommandException("Person does not exists");
        }

        return new CommandResult(String.format(MESSAGE_DELETE_TIME_SUCCESS, personName.fullName));
    }

}
