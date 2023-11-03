package seedu.address.logic.commands;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TimeInterval;
import seedu.address.model.group.Group;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

public class AddGroupMeetingTimeCommand extends Command {
    public static final String COMMAND_WORD = "addmeeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add free time to a group.\n"
            + "Parameters: "
            + PREFIX_GROUPTAG + "GROUP "
            + PREFIX_FREETIME + "MEETING_TIME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUPTAG + "CS2103T "
            + PREFIX_FREETIME + "mon 1200 - mon 1400 ;tue 1000 - wed 1600";

    public static final String MESSAGE_NO_GROUP_WITH_NAME_FOUND = "No group with such name found.\n"
            + "Please provide the group's full name as in the existing contact list.";

    public static final String MESSAGE_SUCCESS = "Free time status for: %1$s \n";

    private final Group toAdd;
    private final ArrayList<TimeInterval> toAddFreeTime;

    /**
     * AddGroupFreeTimeCommand constructor.
     * @param toAdd The group object to be added to.
     * @param toAddFreeTime ArrayList of time intervals to be added to group.
     */
    public AddGroupMeetingTimeCommand(Group toAdd, ArrayList<TimeInterval> toAddFreeTime) {
        requireNonNull(toAdd);
        requireNonNull(toAddFreeTime);
        this.toAddFreeTime = toAddFreeTime;
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String status;
        if (model.hasGroup(toAdd)) {
            status = model.addTimeToGroup(toAdd, toAddFreeTime);
        } else {
            throw new CommandException(MESSAGE_NO_GROUP_WITH_NAME_FOUND);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS + status, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddGroupMeetingTimeCommand)) {
            return false;
        }

        AddGroupMeetingTimeCommand otherAddGroupMeetingTimeCommand = (AddGroupMeetingTimeCommand) other;
        return toAdd.equals(otherAddGroupMeetingTimeCommand.toAdd)
                && toAddFreeTime.equals(otherAddGroupMeetingTimeCommand.toAddFreeTime);
    }
}
