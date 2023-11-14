package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPATION_STATUS;
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
 * Marks the attendance of an existing group.
 */
public class MarkGroupAttendanceCommand extends Command {
    public static final String COMMAND_WORD = "markGroupAtd";
    public static final String ATTENDANCE_MARK_SUCCESS = "Group attendance marked successfully!";
    public static final String ATTENDANCE_MARK_FAIL = "Group attendance failed to mark!";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the attendance of all students in the same group\n"
            + "Parameters: GROUP "
            + "[" + PREFIX_TUTORIAL + "TUTORIAL] "
            + "[" + PREFIX_PARTICIPATION_STATUS + "STATUS] \n"
            + "Example: " + COMMAND_WORD + " tut1 t/1 s/P";
    private final Index tut;
    private final Group group;

    private final String status;

    /**
     * Constructs a new MarkAttendanceCommand to mark attendance for a student on a specific week.
     *
     * @param group The group to mark group attendance for.
     * @param tut The index of the week to mark group attendance on.
     * @param status The status of attendance to mark the entire group with.
     */
    public MarkGroupAttendanceCommand(Group group, Index tut, String status) {
        requireNonNull(group);
        requireNonNull(tut);
        this.group = group;
        this.tut = tut;
        this.status = status;
    }

    /**
     * Executes the MarkAttendanceCommand to mark attendance for a student on a specific tutorial.
     *
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

        boolean allStudentsHaveDuplicateMarkings = lastShownList.stream()
            .allMatch(student -> student.getAttendance().isMarkedWeek(this.tut.getZeroBased()));

        if (allStudentsHaveDuplicateMarkings) {
            return new CommandResult(Messages.MESSAGE_DUPLICATE_MARKINGS);
        }

        for (Person student : lastShownList) {
            Attendance studentAtd = student.getAttendance();
            studentAtd.markAttendance(this.tut.getZeroBased(), status);
            model.updatePerson(student);
        }

        return new CommandResult(generateSuccessMessage(lastShownList));
    }

    /**
     * Generates a command execution success message based on whether
     * the attendance is marked.
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(List<Person> persons) {
        for (Person person : persons) {
            if (!person.getAttendance().isMarkedWeek(this.tut.getZeroBased())) {
                return ATTENDANCE_MARK_FAIL;
            }
        }
        return ATTENDANCE_MARK_SUCCESS;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof MarkGroupAttendanceCommand)) {
            return false;
        }
        MarkGroupAttendanceCommand otherCommand = (MarkGroupAttendanceCommand) other;
        return this.tut.equals(otherCommand.tut)
                && this.group.equals(otherCommand.group);
    }
}
