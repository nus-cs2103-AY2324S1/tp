package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Person;

/**
 * Represents a command to mark the attendance of a student.
 */
public class MarkAttendanceCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks the attendance of a student. "
            + "Parameters: "
            + "/name STUDENTNAME | /id STUDENTID "
            + "/attendance ATTENDANCE "
            + "Example: " + COMMAND_WORD + " /name Zong Jin /attendance 1";
    private final String identifier; // This can be either studentName or studentID
    private final boolean isPresent;
    private final LocalDate date;

    /**
     * Constructs a MarkAttendanceCommand to mark the specified student's attendance.
     *
     * @param identifier The student's name or ID.
     * @param isPresent The attendance status.
     * @param date The date of the attendance.
     */
    public MarkAttendanceCommand(String identifier, boolean isPresent, LocalDate date) {
        this.identifier = identifier;
        this.isPresent = isPresent;
        this.date = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        Person targetPerson = lastShownList.stream()
                .filter(person -> person.getName().fullName.equals(identifier) || person.getId().value.equals(
                        identifier))
                .findFirst()
                .orElse(null);

        if (targetPerson == null) {
            throw new CommandException("Person not found.");
        }

        // TODO - Possibly implement module inclusion for attendance and modify UG appropriately
        Attendance attendance = new Attendance(date, isPresent, null);
        targetPerson.addAttendance(attendance);

        return new CommandResult(String.format("Attendance marked for person: %s", targetPerson.getName()));
    }
}
