package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

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
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks the attendance of a student.\n"
            + "Parameters: "
            + "/name STUDENTNAME | /id STUDENTID "
            + "/attendance ATTENDANCE "
            + "/week WEEK_NUMBER"
            + "[/reason REASON_OF_ABSENCE]\n"
            + "Example: " + COMMAND_WORD + " /name Zong Jin /attendance 1 /week 1\n"
            + "Example: " + COMMAND_WORD + " /name Zong Jin /attendance 0 /week 2 /reason Took a MC\n";
    public static final String MESSAGE_SUCCESS = "Attendance marked for person: ";
    public static final String MESSAGE_ABSENT = " is absent for week ";
    public static final String MESSAGE_PRESENT = " is present for week ";
    public static final String MESSAGE_UPDATED_SUCCESS = "Attendance updated for person: ";
    public static final String MESSAGE_PERSON_NOT_FOUND = "Person not found.";
    private final List<String> identifiers; // This can be either studentName or studentID
    private final boolean isPresent;
    private final Week week;
    private final String reason;

    /**
     * Constructs a MarkAttendanceCommand to mark the specified student's attendance as present.
     *
     * @param identifiers The list of student's name or ID.
     * @param isPresent The attendance status.
     * @param week The week of the attendance.
     */

    public MarkAttendanceCommand(List<String> identifiers, boolean isPresent, Week week) {
        this.identifiers = identifiers;
        this.isPresent = isPresent;
        this.week = week;
        this.reason = null;
    }

    /**
     * Constructs a MarkAttendanceCommand to mark the specified student's attendance as absent.
     *
     * @param identifier The student's name or ID.
     * @param isPresent The attendance status.
     * @param week The week of the attendance.
     * @param reason The reason why the student is absent.
     */
    public MarkAttendanceCommand(String identifier, boolean isPresent, Week week, String reason) {
        this.identifier = identifier;
        this.isPresent = isPresent;
        this.week = week;
        this.reason = reason;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        StringBuilder successMessage = new StringBuilder();

        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        Attendance attendance;

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
                if (isPresent) {
                    attendance = new Attendance(week, true);
                } else {
                    attendance = new Attendance(week, false, reason);
                }
                targetPerson.addAttendance(newAttendance);
              
                if (attendance.isPresent()) {
                    successMessage.append(String.format(MESSAGE_SUCCESS + "%s\n" + "%s" + MESSAGE_PRESENT + "%d",
                            targetPerson.getName(), targetPerson.getName(), attendance.getWeek().getWeekNumber()));
                } else {
                    successMessage.append(String.format(MESSAGE_SUCCESS + "%s\n" + "%s" + MESSAGE_ABSENT + "%d\nReason: %s",
                            targetPerson.getName(), targetPerson.getName(), attendance.getWeek().getWeekNumber(),
                            attendance.getReason()));
                }
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
                && week.equals(otherMarkAttendanceCommand.week);
    }
}
