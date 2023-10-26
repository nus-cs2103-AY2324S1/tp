package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Person;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Represents command to view full detailed attendance record of a student.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows student's attendance record in detail.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_NO_ATTENDANCE_RECORD = " has no attendance records.";
    public static final String MESSAGE_HEADER = "'s attendance records\n";

    private final Index targetIndex;

    /**
     * @param targetIndex Index number used in the displayed person list of the target
     */
    public ViewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        StringBuilder attendanceRecordString = new StringBuilder();
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex == null || targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToView = lastShownList.get(targetIndex.getZeroBased());
        List<Attendance> personAttendanceRecord = personToView.getAttendanceRecords();

        if (personAttendanceRecord.isEmpty()) {
            return new CommandResult(personToView.getName() + MESSAGE_NO_ATTENDANCE_RECORD);
        }

        attendanceRecordString.append(personToView.getName() + MESSAGE_HEADER);

        for (Attendance record : personAttendanceRecord) {
            String attendanceStatus = record.isPresent()
                    ? "Present"
                    : "Absent - " + record.getReason();
            attendanceRecordString.append("Week " + record.getWeek() + " : " + attendanceStatus + "\n");
        }

        return new CommandResult(attendanceRecordString.toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewCommand)) {
            return false;
        }

        ViewCommand otherViewCommand = (ViewCommand) other;
        return otherViewCommand.targetIndex.equals(this.targetIndex);
    }
}
