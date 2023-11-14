package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REASON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;

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
            + PREFIX_NAME + " STUDENT_NAME | " + PREFIX_ID + " STUDENT_ID "
            + PREFIX_ATTENDANCE + " ATTENDANCE "
            + PREFIX_WEEK + " WEEK_NUMBER "
            + "[" + PREFIX_REASON + " REASON_OF_ABSENCE]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + " Zong Jin " + PREFIX_ATTENDANCE + " 1 "
            + PREFIX_WEEK + " 1\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + " Zong Jin " + PREFIX_ATTENDANCE + " 0 "
            + PREFIX_WEEK + " 2 " + PREFIX_REASON + " Took a MC\n";
    public static final String MESSAGE_SUCCESS = "Attendance marked for person: ";
    public static final String MESSAGE_ABSENT = " is absent for week ";
    public static final String MESSAGE_PRESENT = " is present for week ";
    public static final String MESSAGE_UPDATED_SUCCESS = "Attendance updated for person: ";
    public static final String MESSAGE_PERSON_NOT_FOUND = "Person not found.";
    public static final String MESSAGE_PERSON_NOT_FOUND_MULTIPLE = "Persons not found: ";
    private final List<String> identifiers; // This can be either studentName or studentID
    private final boolean isPresent;
    private final Week week;
    private final String reason;

    /**
     * Constructs a MarkAttendanceCommand to mark the specified student's attendance as absent.
     *
     * @param identifiers The student's name or ID.
     * @param isPresent The attendance status.
     * @param week The week of the attendance.
     * @param reason The reason why the student is absent.
     */
    public MarkAttendanceCommand(List<String> identifiers, boolean isPresent, Week week, String reason) {
        this.identifiers = identifiers;
        this.isPresent = isPresent;
        this.week = week;
        this.reason = reason;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        StringBuilder successMessage = new StringBuilder();
        StringBuilder errorMessage = new StringBuilder();

        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        Attendance attendance;

        for (String identifier : identifiers) {
            Person targetPerson = lastShownList.stream()
                    .filter(person -> person.getName().fullName.equals(identifier) || person.getId().value.equals(
                            identifier))
                    .findFirst()
                    .orElse(null);

            if (targetPerson == null && identifiers.size() == 1) {
                throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
            }

            if (targetPerson == null && identifiers.size() > 1) {
                errorMessage.append(String.format(MESSAGE_PERSON_NOT_FOUND_MULTIPLE + "%s\n", identifier));
                continue;
            }

            Optional<Attendance> existingAttendance = targetPerson.getAttendanceForSpecifiedWeek(week);

            if (existingAttendance.isPresent()) {
                // Modify the existing attendance record
                attendance = existingAttendance.get();
                attendance.setAttendance(isPresent);
                if (isPresent) {
                    successMessage.append(String.format(MESSAGE_UPDATED_SUCCESS + "%s\n%s" + MESSAGE_PRESENT + "%d\n",
                            targetPerson.getName(), targetPerson.getName(), week.getWeekNumber()));
                } else {
                    attendance.setReason(this.reason);
                    successMessage.append(String.format(MESSAGE_UPDATED_SUCCESS + "%s\n%s" + MESSAGE_ABSENT + "%d\n"
                            + "Reason: %s\n", targetPerson.getName(), targetPerson.getName(), week.getWeekNumber(),
                            reason));
                }
            } else {
                // Add a new attendance record for the current week
                if (isPresent) {
                    attendance = new Attendance(week, true, null);
                } else {
                    attendance = new Attendance(week, false, reason);
                }

                targetPerson.addAttendance(attendance);

                if (attendance.isPresent()) {
                    successMessage.append(String.format(MESSAGE_SUCCESS + "%s\n%s" + MESSAGE_PRESENT + "%d\n",
                            targetPerson.getName(), targetPerson.getName(), attendance.getWeek().getWeekNumber()));
                } else {
                    successMessage.append(String.format(MESSAGE_SUCCESS + "%s\n%s" + MESSAGE_ABSENT
                                    + "%d\nReason: %s\n",
                            targetPerson.getName(), targetPerson.getName(), attendance.getWeek().getWeekNumber(),
                            attendance.getReason()));
                }
            }
        }

        if (errorMessage.length() > 0) {
            successMessage.append(errorMessage.toString());
        }

        // TODO: Find a better way to refresh the address book to show marked attendance
        model.setAddressBook(model.getAddressBook());

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
