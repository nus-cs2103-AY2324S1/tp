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
import seedu.address.model.person.Person;

/**
 * Marks the attendance of an existing student in the taa.
 */
public class MarkAttendanceCommand extends Command {
    public static final String COMMAND_WORD = "markAtd";
    public static final String ATTENDANCE_MARK_SUCCESS = "Attendance marked successfully!";
    public static final String ATTENDANCE_MARK_FAIL = "Attendance failed to mark!";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the attendance of the student identified\n"
            + "by the index number used in the displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TUTORIAL + "TUTORIAL] "
            + "[" + PREFIX_PARTICIPATION_STATUS + "STATUS] \n"
            + "Example: " + COMMAND_WORD + " 1 t/1 s/P";
    private final Index index;
    private final Index tut;
    private final String status;
    /**
     * Constructs a new MarkAttendanceCommand to mark attendance for a student on a specific week.
     *
     * @param index The index of the student to mark attendance for.
     * @param tut The index of the week to mark attendance on.
     * @param status The status to mark the week's attendance with.
     */
    public MarkAttendanceCommand(Index index, Index tut, String status) {
        requireNonNull(index);
        requireNonNull(tut);
        this.index = index;
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
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person studentToEdit = lastShownList.get(index.getZeroBased());
        Attendance studentAtd = studentToEdit.getAttendance();
        if (studentAtd.isMarkedWeek(this.tut.getZeroBased())) {
            return new CommandResult(Messages.MESSAGE_DUPLICATE_MARKINGS);
        }
        studentAtd.markAttendance(this.tut.getZeroBased(), this.status);

        model.updatePerson(studentToEdit);
        return new CommandResult(generateSuccessMessage(studentToEdit));
    }

    /**
     * Generates a command execution success message based on whether
     * the attendance is marked.
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = personToEdit.getAttendance().isMarkedWeek(this.tut.getZeroBased())
                ? ATTENDANCE_MARK_SUCCESS
                : ATTENDANCE_MARK_FAIL;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkAttendanceCommand)) {
            return false;
        }

        MarkAttendanceCommand otherMarkAttendanceCommand = (MarkAttendanceCommand) other;
        return index.equals(otherMarkAttendanceCommand.index) && tut.equals(otherMarkAttendanceCommand.tut);
    }
}
