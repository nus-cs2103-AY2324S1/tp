package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Person;
import seedu.address.model.week.Week;

/**
 * Represents a command to mark the attendance of a student.
 */
public class MarkAttendanceCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks the attendance of a student. "
            + "Parameters: "
            + "/name STUDENTNAME | /id STUDENTID "
            + "/attendance ATTENDANCE "
            + "/week WEEK_NUMBER"
            + "[/reason REASON_OF_ABSENCE] "
            + "Example: " + COMMAND_WORD + " /name Zong Jin /attendance 1 /week 1"
            + "Example: " + COMMAND_WORD + " /name Zong Jin /attendance 0 /week 2 /reason Took a MC";
    public static final String MESSAGE_SUCCESS = "Attendance marked for person: ";
    public static final String MESSAGE_PERSON_NOT_FOUND = "Person not found.";
    private final String identifier; // This can be either studentName or studentID
    private final boolean isPresent;
    private final Week week;

    /**
     * Constructs a MarkAttendanceCommand to mark the specified student's attendance.
     *
     * @param identifier The student's name or ID.
     * @param isPresent The attendance status.
     * @param week The week of the attendance.
     */
    public MarkAttendanceCommand(String identifier, boolean isPresent, Week week) {
        this.identifier = identifier;
        this.isPresent = isPresent;
        this.week = week;
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
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
        }

        // TODO - Possibly implement module inclusion for attendance and modify UG appropriately
        Attendance attendance = new Attendance(week, isPresent);
        targetPerson.addAttendance(attendance);

        return new CommandResult(String.format(MESSAGE_SUCCESS + "%s", targetPerson.getName()));
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
        return identifier.equals(otherMarkAttendanceCommand.identifier)
                && isPresent == otherMarkAttendanceCommand.isPresent
                && week == otherMarkAttendanceCommand.week;
    }
}
