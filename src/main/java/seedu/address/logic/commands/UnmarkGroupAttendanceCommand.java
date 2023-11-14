package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Group;
import seedu.address.model.person.Person;

/**
 * Unmarks the attendance of a student on a specific tutorial.
 */
public class UnmarkGroupAttendanceCommand extends Command {
    public static final String COMMAND_WORD = "unmarkGroupAtd";
    public static final String GROUP_ATTENDANCE_UNMARK_SUCCESS = "Group attendance unmarked successfully!";
    public static final String GROUP_ATTENDANCE_UNMARK_FAIL = "Group attendance failed to unmark!";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unmark the attendance based on groups\n"
            + "Parameters: GROUP "
            + "[" + PREFIX_TUTORIAL + "TUTORIAL] \n"
            + "Example: " + COMMAND_WORD + " tut4 t/1 ";
    private final Group group;
    private final Index tut;

    /**
     * Constructs a new UnMarkAttendanceCommand to mark attendance for a student on a specific tutorial.
     *
     * @param group The group to unmark attendance.
     * @param tut The index of the week to unmark attendance on.
     */
    public UnmarkGroupAttendanceCommand(Group group, Index tut) {
        requireNonNull(group);
        requireNonNull(tut);
        this.group = group;
        this.tut = tut;
    }

    /**
     * Executes the UnMarkGroupAttendanceCommand to unmark attendance for all student of a specified group.
     * @param model The model that the command operates on.
     * @return A CommandResult indicating the outcome of the execution.
     * @throws CommandException If there is an error in executing the command.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList().filtered(x -> x.getGroup().equals(this.group));
        if (lastShownList.isEmpty()) {
            throw new CommandException(Messages.INVALID_GROUP);
        }
        for (Person student : lastShownList) {
            if (!student.getAttendance().isMarkedWeek(this.tut.getZeroBased())) {
                return new CommandResult(Messages.MESSAGE_DUPLICATE_UNMARK);
            }
        }

        for (Person student : lastShownList) {
            Attendance studentAtd = student.getAttendance();
            studentAtd.unmarkAttendance(this.tut.getZeroBased());
            model.updatePerson(student);
        }

        return new CommandResult(generateSuccessMessage(lastShownList));
    }

    /**
     * Generates a command execution success message based on whether
     * the attendance is unmarked in the group.
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(List<Person> personList) {
        for (Person person : personList) {
            if (person.getAttendance().isMarkedWeek(this.tut.getZeroBased())) {
                return GROUP_ATTENDANCE_UNMARK_FAIL;
            }
        }
        return GROUP_ATTENDANCE_UNMARK_SUCCESS;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnmarkGroupAttendanceCommand)) {
            return false;
        }

        // state check
        UnmarkGroupAttendanceCommand e = (UnmarkGroupAttendanceCommand) other;
        return group.equals(e.group)
                && tut.equals(e.tut);
    }
}
