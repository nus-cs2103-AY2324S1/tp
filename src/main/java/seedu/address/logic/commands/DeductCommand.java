package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW_DEDUCT;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Deduction;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Salary;

/**
 * Adds a deduction to the salary of the person identified using it's
 * displayed index or name from the address book.
 */
public class DeductCommand extends Command {

    public static final String COMMAND_WORD = "deduct";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": add deductions to the salary of the person identified "
            + "by the index number or name used in the last person listing.\n"
            + "Parameters: INDEX (must be a positive integer) or NAME, AMOUNT, REASON\n"
            + "Example 1 (index): " + COMMAND_WORD + " 1, 200.00, cpf\n"
            + "Example 2 (name): " + COMMAND_WORD + " /n john, 150.00, absence\n";

    public static final String MESSAGE_ARGUMENTS = "The deductions for the specified person is as below\n%s";

    private final Index index;
    private final NameContainsKeywordsPredicate name;
    private final Deduction deduction;

    /**
     * Creates a DeductCommand to add deductions
     * to the salary of the person specified by the index.
     * @param index The index of the employee to be deleted.
     * @param deduction The deduction.
     */
    public DeductCommand(Index index, Deduction deduction) {
        requireAllNonNull(index);
        this.index = index;
        this.name = null;
        this.deduction = deduction;
    }

    /**
     * Creates a DeductCommand to add deductions
     * to the salary of the person specified by the name.
     * @param name The name of the employee to be deleted.
     * @param deduction The deduction.
     */
    public DeductCommand(NameContainsKeywordsPredicate name, Deduction deduction) {
        requireAllNonNull(name);
        this.name = name;
        this.index = null;
        this.deduction = deduction;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);
        if (index != null) {
            return executeByIndex(model);
        } else {
            return executeByName(model);
        }
    }

    /**
     * Executes the command by index.
     * @param model {@code Model} which the command should operate on.
     * @return A command result in which the deductions of the person is shown.
     * @throws CommandException If the index is invalid.
     */
    private CommandResult executeByIndex(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person employeeToCalculate = lastShownList.get(index.getZeroBased());
        Salary salary = employeeToCalculate.getSalary();
        salary.addDeduction(deduction);

        model.updateFilteredPersonList(person -> person.equals(employeeToCalculate));
        return new CommandResult(String.format(MESSAGE_ARGUMENTS, salary.getDeductionsString()));
    }

    /**
     * Executes the command by name.
     * @param model {@code Model} which the command should operate on.
     * @return A command result in which the deductions of the person is shown.
     * @throws CommandException If the name is invalid.
     */
    private CommandResult executeByName(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        List<Integer> indexes = model.getIndexOfFilteredPersonList(this.name);

        if (indexes.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
        }

        if (indexes.size() == 1) {
            Person employeeToCalculate = lastShownList.get(indexes.get(0) - 1);
            Salary salary = employeeToCalculate.getSalary();
            salary.addDeduction(deduction);
            model.updateFilteredPersonList(this.name);
            return new CommandResult(String.format(MESSAGE_ARGUMENTS, deduction.toString()));
        }

        model.updateFilteredPersonList(this.name);
        return new CommandResult(String.format(MESSAGE_PERSONS_LISTED_OVERVIEW_DEDUCT,
            lastShownList.size()), indexes);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeductCommand)) {
            return false;
        }

        DeductCommand otherDeductCommand = (DeductCommand) other;

        if (index != null && otherDeductCommand.index != null && deduction != null
                && otherDeductCommand.deduction != null) {
            return index.equals(otherDeductCommand.index)
                && deduction.equals(otherDeductCommand.deduction);
        }

        if (name != null && otherDeductCommand.name != null && deduction != null
                && otherDeductCommand.deduction != null) {
            return name.equals(otherDeductCommand.name)
                && deduction.equals(otherDeductCommand.deduction);
        }

        return false;
    }

    @Override
    public String toString() {
        if (index != null) {
            return new ToStringBuilder(this)
                .add("index", index)
                .add("deduction", deduction)
                .toString();
        } else {
            assert name != null;
            return new ToStringBuilder(this)
                .add("name", name)
                .add("deduction", deduction)
                .toString();
        }
    }
}
