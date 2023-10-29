package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW_MARK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDate;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.AnnualLeave;
import seedu.address.model.person.BankAccount;
import seedu.address.model.person.Email;
import seedu.address.model.person.JoinDate;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Salary;
import seedu.address.model.person.attendance.AttendanceStorage;
import seedu.address.model.person.attendance.AttendanceType;



/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the employee's attendance identified by the index number used in the displayed employee list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_USAGE_FOR_NAME = COMMAND_WORD
            + ": Marks the employee's attendance identified by the name used in the displayed employee list.\n"
            + "Parameters: n/NAME (must be present)\n"
            + "Example: " + COMMAND_WORD + " n/John";
    public static final String MESSAGE_MARK_PERSON_SUCCESS = "Marked Employee: %2$s as %1$s";

    private final Index targetIndex;

    private final NameContainsKeywordsPredicate name;

    private final AttendanceType attendanceType;


    /**
     * The constructor for DeleteCommand to take in name instead of index
     * @param name The name of the employee to be deleted
     */
    public MarkCommand(NameContainsKeywordsPredicate name, AttendanceType attendanceType) {
        this.targetIndex = null;
        this.name = name;
        this.attendanceType = attendanceType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return this.markByName(model);

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        MarkCommand otherMarkCommand = (MarkCommand) other;

        if ((this.targetIndex == null && otherMarkCommand.targetIndex != null)
                || (this.targetIndex != null && otherMarkCommand.targetIndex == null)) {
            return false;
        }

        if ((this.name == null && otherMarkCommand.name != null)
                || (this.name != null && otherMarkCommand.name == null)) {
            return false;
        }

        if (this.targetIndex != null) {
            return targetIndex.equals(otherMarkCommand.targetIndex);
        }
        return this.name.equals(otherMarkCommand.name);
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
     * Marks the attendance of the employee identified by the name used in the displayed employee list.
     * @param model {@code Model} which the command should operate on.
     * @return A command result that contains the message to be displayed to the user.
     * @throws CommandException If the name is invalid.
     */
    public CommandResult markByName(Model model) throws CommandException {
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        List<Person> fullList = model.getFilteredPersonList();
        List<Integer> indexes = model.getIndexOfFilteredPersonList(this.name);
        if (indexes.size() == 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
        }
        if (indexes.size() == 1) {
            Person employeeToMark = fullList.get(indexes.get(0) - 1);
            Person markedEmployee = markEmployee(employeeToMark);

            model.setPerson(employeeToMark, markedEmployee);
            return new CommandResult(String.format(MESSAGE_MARK_PERSON_SUCCESS, attendanceType.toString().toLowerCase(), employeeToMark.getName()));
        }
        return new CommandResult(String.format(MESSAGE_PERSONS_LISTED_OVERVIEW_MARK,
                model.getFilteredPersonList().size()), indexes);
    }

    /**
     * Creates and returns an {@code Employee} with the details of {@code employeeToEdit}
     * edited with {@code editEmployeeDescriptor}.
     */
    private Person markEmployee(Person employeeToMark) {
        assert employeeToMark != null;

        Name name = employeeToMark.getName();
        Phone phone = employeeToMark.getPhone();
        Email email = employeeToMark.getEmail();
        Address address = employeeToMark.getAddress();
        BankAccount bankAccount = employeeToMark.getBankAccount();
        JoinDate joinDate = employeeToMark.getJoinDate();
        Salary salary = employeeToMark.getSalary();
        AnnualLeave annualLeave = employeeToMark.getAnnualLeave();

        AttendanceStorage attendanceStorage = employeeToMark.getAttendanceStorage();

        switch (attendanceType) {
        case ABSENT:
            attendanceStorage.markAbsent(LocalDate.now());
            break;
        case LATE:
            attendanceStorage.markLate(LocalDate.now());
            break;
        case PRESENT:
            attendanceStorage.markPresent(LocalDate.now());

        }

        return new Person(name, phone, email, address, bankAccount, joinDate, salary, annualLeave, attendanceStorage);
    }


}
