package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW_ATTENDANCE_REPORT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;


/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class AttendanceCommand extends Command {

    public static final String COMMAND_WORD = "attendance";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Reports the employee's attendance identified by the index number "
            + "used in the displayed employee list.\n"
            + "Parameters: INDEX (must be a positive integer) or /n NAME (must be present)\n"
            + "Example: " + COMMAND_WORD + " 1\n"
            + "Example: " + COMMAND_WORD + " /n John";

    public static final String MESSAGE_REPORT_ATTENDANCE = "%1$s's attendance:\n"
            + "\tDays on leave: %2$s\n"
            + "\tDays absent: %3$s\n"
            + "\tDays late: %4$s\n";


    private final Index targetIndex;

    private final NameContainsKeywordsPredicate name;

    /**
     * The constructor for AttendanceCommand to take in name instead of index
     * @param name The name of the employee to view
     */
    public AttendanceCommand(NameContainsKeywordsPredicate name) {
        this.targetIndex = null;
        requireNonNull(name);
        this.name = name;
    }

    /**
     * The constructor for AttendanceCommand to take in name instead of index
     * @param index The index of the employee to view
     */
    public AttendanceCommand(Index index) {
        requireNonNull(index);
        this.targetIndex = index;
        this.name = null;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (this.targetIndex == null && this.name != null) {
            return this.reportByName(model);
        } else {
            return this.reportByIndex(model);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AttendanceCommand)) {
            return false;
        }

        AttendanceCommand otherAttendanceCommand = (AttendanceCommand) other;

        if ((this.targetIndex == null && otherAttendanceCommand.targetIndex != null)
                || (this.targetIndex != null && otherAttendanceCommand.targetIndex == null)) {
            return false;
        }

        if ((this.name == null && otherAttendanceCommand.name != null)
                || (this.name != null && otherAttendanceCommand.name == null)) {
            return false;
        }

        if (this.targetIndex != null) {
            return targetIndex.equals(otherAttendanceCommand.targetIndex);
        }
        return this.name.equals(otherAttendanceCommand.name);
    }

    @Override
    public String toString() {
        if (name != null) {
            return new ToStringBuilder(this)
                    .add("targetName", name)
                    .toString();
        }
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }

    /**
     * Reports the attendance of the employee identified by the name used in the displayed employee list.
     * @param model {@code Model} which the command should operate on.
     * @return A command result that contains the message to be displayed to the user.
     * @throws CommandException If the name is invalid.
     */
    public CommandResult reportByName(Model model) throws CommandException {
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        List<Person> fullList = model.getFilteredPersonList();
        List<Integer> indexes = model.getIndexOfFilteredPersonList(this.name);
        if (indexes.size() == 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
        }
        if (indexes.size() == 1) {
            Person employeeToReport = fullList.get(indexes.get(0) - 1);
            int[] attendanceValues = employeeToReport
                    .getAttendanceStorage()
                    .getAttendanceReport(employeeToReport.getJoinDate(),
                            employeeToReport.getAnnualLeave().getTotalLeaveTaken());
            return new CommandResult(String.format(MESSAGE_REPORT_ATTENDANCE,
                    employeeToReport.getName(),
                    attendanceValues[0],
                    attendanceValues[1],
                    attendanceValues[2]));
        }
        model.updateFilteredPersonList(this.name);
        return new CommandResult(String.format(MESSAGE_PERSONS_LISTED_OVERVIEW_ATTENDANCE_REPORT,
                model.getFilteredPersonList().size()));
    }

    /**
     * Reports the attendance of the employee identified by the name used in the displayed employee list.
     * @param model {@code Model} which the command should operate on.
     * @return A command result that contains the message to be displayed to the user.
     * @throws CommandException If the name is invalid.
     */
    public CommandResult reportByIndex(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person employeeToReport = lastShownList.get(targetIndex.getZeroBased());
        int[] attendanceValues = employeeToReport
                .getAttendanceStorage()
                .getAttendanceReport(employeeToReport.getJoinDate(),
                        employeeToReport.getAnnualLeave().getTotalLeaveTaken());
        return new CommandResult(String.format(MESSAGE_REPORT_ATTENDANCE,
                employeeToReport.getName(),
                attendanceValues[0],
                attendanceValues[1],
                attendanceValues[2]));
    }
}
