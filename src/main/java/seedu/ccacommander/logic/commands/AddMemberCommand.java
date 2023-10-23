package seedu.ccacommander.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_HOURS;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.ccacommander.commons.util.ToStringBuilder;
import seedu.ccacommander.logic.Messages;
import seedu.ccacommander.logic.commands.exceptions.CommandException;
import seedu.ccacommander.model.Model;
import seedu.ccacommander.model.attendance.Attendance;

/**
 * Adds a member to an event in CcaCommander.
 */
public class AddMemberCommand extends Command {

    public static final String COMMAND_WORD = "addMember";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a member to an event in CcaCommander. \n"
            + "Parameters: "
            + PREFIX_MEMBER + "MEMBER_INDEX "
            + PREFIX_EVENT + "LOCATION "
            + PREFIX_HOURS + "HOURS "
            + PREFIX_REMARK + "REMARK \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MEMBER + "1 "
            + PREFIX_EVENT + "1 "
            + PREFIX_HOURS + "0 "
            + PREFIX_REMARK + "Role: Supervisor";


    public static final String MESSAGE_SUCCESS = "Successfully added: %1$s";
    public static final String MESSAGE_DUPLICATE_ATTENDANCE = "This member has already been added to the event. ";

    private final Attendance toAdd;

    /**
     * Creates an CreateEventCommand to add the specified {@code Event}
     */
    public AddMemberCommand(Attendance attendance) {
        requireNonNull(attendance);
        toAdd = attendance;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasAttendance(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ATTENDANCE);
        }

        model.createAttendance(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddMemberCommand)) {
            return false;
        }

        AddMemberCommand otherAddMemberCommand = (AddMemberCommand) other;
        return toAdd.equals(otherAddMemberCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
