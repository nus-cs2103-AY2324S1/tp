package seedu.address.logic.commands;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TimeInterval;
import seedu.address.model.group.Group;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

public class AddGroupFreeTimeCommand extends Command {
    public static final String COMMAND_WORD = "addmeeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add free time to a group.\n"
            + "Parameters: "
            + PREFIX_GROUPTAG + "GROUP\n"
            + PREFIX_FREETIME + "FREE TIME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUPTAG + "CS2103T "
            + PREFIX_FREETIME + "mon 1200 - mon 1400 ;tue 1000 - wed 1600";

    public static final String MESSAGE_NO_GROUP_WITH_NAME_FOUND = "No group with such name found.\n"
            + "Please provide the group's full name as in the existing contactlist.";

    public static final String MESSAGE_SUCCESS = "Free time added to: %1$s";

    private final Group toAdd;
    private final ArrayList<TimeInterval> toAddFreeTime;

    /**
     * AddGroupFreeTimeCommand constructor.
     * @param toAdd The group object to be added to.
     * @param toAddFreeTime ArrayList of time intervals to be added to group.
     */
    public AddGroupFreeTimeCommand(Group toAdd, ArrayList<TimeInterval> toAddFreeTime) {
        requireNonNull(toAdd);
        requireNonNull(toAddFreeTime);
        this.toAddFreeTime = toAddFreeTime;
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasGroup(toAdd)) {
            model.addFreeTimeToGroup(toAdd, toAddFreeTime);
        } else {
            throw new CommandException(MESSAGE_NO_GROUP_WITH_NAME_FOUND);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddGroupFreeTimeCommand)) {
            return false;
        }

        AddGroupFreeTimeCommand otherAddGroupFreeTimeCommand = (AddGroupFreeTimeCommand) other;
        return toAdd.equals(otherAddGroupFreeTimeCommand.toAdd)
                && toAddFreeTime.equals(otherAddGroupFreeTimeCommand.toAddFreeTime);
    }
}
