package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    public static final String MESSAGE_SUCCESS = "Attendance marked for person: ";
    public static final String MESSAGE_UPDATED_SUCCESS = "Attendance updated for person: ";
    public static final String MESSAGE_PERSON_NOT_FOUND = "Person not found.";
    private final List<String> identifiers; // This can be either studentName or studentID
    private final boolean isPresent;
    private final LocalDate date;

    /**
     * Constructs a MarkAttendanceCommand to mark the specified student's attendance.
     *
     * @param identifiers The list of student's name or ID.
     * @param isPresent The attendance status.
     * @param date The date of the attendance.
     */
    public MarkAttendanceCommand(List<String> identifiers, boolean isPresent, LocalDate date) {
        this.identifiers = identifiers;
        this.isPresent = isPresent;
        this.date = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        StringBuilder successMessage = new StringBuilder();

        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        for (String identifier : identifiers) {
            Person targetPerson = lastShownList.stream()
                    .filter(person -> person.getName().fullName.equals(identifier) || person.getId().value.equals(
                            identifier))
                    .findFirst()
                    .orElse(null);

            if (targetPerson == null) {
                throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
            }


            Optional<Attendance> existingAttendance = targetPerson.getAttendanceForCurrentWeek();

            if (existingAttendance.isPresent()) {
                // Modify the existing attendance record
                Attendance attendance = existingAttendance.get();
                attendance.setAttendance(isPresent);
                successMessage.append(String.format(MESSAGE_UPDATED_SUCCESS + "%s\n", targetPerson.getName()));

            } else {
                // Add a new attendance record for the current week
                Attendance newAttendance = new Attendance(date, isPresent);
                targetPerson.addAttendance(newAttendance);
                successMessage.append(String.format(MESSAGE_SUCCESS + "%s\n", targetPerson.getName()));
            }
        }

        return new CommandResult(successMessage.toString());
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
        return identifiers.equals(otherMarkAttendanceCommand.identifiers)
                && isPresent == otherMarkAttendanceCommand.isPresent
                && date.equals(otherMarkAttendanceCommand.date);
    }
}
