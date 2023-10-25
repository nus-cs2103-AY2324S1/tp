package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW_PAYROLL;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Payroll;
import seedu.address.model.person.Person;


/**
 * Calculates the payroll of the person identified using it's
 * displayed index or name from the address book.
 */
public class PayrollCommand extends Command {

    public static final String COMMAND_WORD = "payroll";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": calculates the payroll of the person identified "
            + "by the index number or name used in the last person listing.\n"
            + "Parameter: INDEX (must be a positive integer) or NAME\n"
            + "Example 1 (index): " + COMMAND_WORD + " 1\n"
            + "Example 2 (name): " + COMMAND_WORD + " /n john\n";

    public static final String MESSAGE_ARGUMENTS = "The payroll detail for the specified person is as below\n%s";

    private final Index index;
    private final NameContainsKeywordsPredicate name;

    /**
     * Creates a PayrollCommand to calculate the payroll
     * of the person specified by the index.
     * @param index The index of the employee to be deleted.
     */
    public PayrollCommand(Index index) {
        requireAllNonNull(index);
        this.index = index;
        this.name = null;
    }

    /**
     * Creates a PayrollCommand to calculate the payroll
     * of the person specified by the name.
     * @param name The name of the employee to be deleted.
     */
    public PayrollCommand(NameContainsKeywordsPredicate name) {
        requireAllNonNull(name);
        this.name = name;
        this.index = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (index != null) {
            return executeByIndex(model);
        } else {
            return executeByName(model);
        }
    }

    /**
     * Calculate the payroll of the employee identified by the index number used in the displayed employee list.
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException If the index is invalid.
     */
    public CommandResult executeByIndex(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person employeeToCalculate = lastShownList.get(index.getZeroBased());
        Payroll monthPayroll = new Payroll(employeeToCalculate.getSalary());
        employeeToCalculate.addPayroll(monthPayroll);

        model.updateFilteredPersonList(person -> person.equals(employeeToCalculate));
        return new CommandResult(String.format(MESSAGE_ARGUMENTS,
                monthPayroll.calculatePayrollString()), List.of(index.getOneBased()));
    }

    /**
     * Calculate the payroll of the employee identified by the name used in the displayed employee list.
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException If the index is invalid.
     */
    public CommandResult executeByName(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        List<Integer> indexes = model.getIndexOfFilteredPersonList(this.name);

        if (indexes.size() == 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
        }
        if (indexes.size() == 1) {
            Person employeeToCalculate = lastShownList.get(indexes.get(0) - 1);
            Payroll monthPayroll = new Payroll(employeeToCalculate.getSalary());
            model.updateFilteredPersonList(this.name);
            return new CommandResult(String.format(MESSAGE_ARGUMENTS,
                    monthPayroll.calculatePayrollString()), indexes);
        }

        model.updateFilteredPersonList(this.name);
        return new CommandResult(String.format(MESSAGE_PERSONS_LISTED_OVERVIEW_PAYROLL,
                lastShownList.size()), indexes);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PayrollCommand)) {
            return false;
        }

        PayrollCommand otherPayrollCommand = (PayrollCommand) other;

        if ((this.index != null && otherPayrollCommand.index == null)
                || (this.index == null && otherPayrollCommand.index != null)) {
            return false;
        }

        if ((this.name != null && otherPayrollCommand.name == null)
                || (this.name == null && otherPayrollCommand.name != null)) {
            return false;
        }

        if (this.index != null) {
            return index.equals(otherPayrollCommand.index);
        }

        return this.name.equals(otherPayrollCommand.name);
    }
}
