package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GROUPS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;

/**
 * Removes a person specified by email from a group specified by group number.
 */
public class LeaveCommand extends Command {

    public static final String COMMAND_WORD = "leave";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the person specified by email from the group specified by group number.\n"
            + "Parameters: "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_GROUP + "GROUP NUMBER\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EMAIL + "johnd@u.nus.edu "
            + PREFIX_GROUP + "1";

    public static final String MESSAGE_LEAVE_SUCCESS = "Leave successful! %1$s has left Group %2$s!";
    public static final String MESSAGE_LEAVE_EMAIL_NOT_FOUND = "Person with the provided email not found.";
    public static final String MESSAGE_LEAVE_GROUP_NOT_FOUND = "Group with the provided group number not found.";
    public static final String MESSAGE_NOT_IN_GROUP = "The above student is not a member of the provided group.";

    private final Email targetEmail;
    private final int targetGroupNumber;

    /**
     * Constructs a {@code LeaveCommand} with the specified email and group number to leave a group.
     *
     * @param targetEmail The email of the person to leave the group.
     * @param targetGroupNumber The number of the group to leave.
     */
    public LeaveCommand(Email targetEmail, int targetGroupNumber) {
        this.targetEmail = targetEmail;
        this.targetGroupNumber = targetGroupNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        // Find the person with the provided email
        Person personToLeave = model.getPersonWithEmail(targetEmail)
                .orElseThrow(() -> new CommandException(MESSAGE_LEAVE_EMAIL_NOT_FOUND));

        // Find the group with the provided group number
        Group groupToLeave = model.getGroupWithNumber(targetGroupNumber)
                .orElseThrow(() -> new CommandException(MESSAGE_LEAVE_GROUP_NOT_FOUND));

        if (!groupToLeave.hasMember(personToLeave)) {
            throw new CommandException(MESSAGE_NOT_IN_GROUP);
        }
        // Remove the person from the group
        model.removePersonFromGroup(personToLeave, groupToLeave);

        return new CommandResult(String.format(MESSAGE_LEAVE_SUCCESS,
                personToLeave.getName(), groupToLeave.getNumber()), false, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof LeaveCommand)) {
            return false;
        }

        LeaveCommand otherLeaveCommand = (LeaveCommand) other;
        return targetEmail.equals(otherLeaveCommand.targetEmail)
                && targetGroupNumber == otherLeaveCommand.targetGroupNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetEmail", targetEmail)
                .add("targetGroupNumber", targetGroupNumber)
                .toString();
    }
}
