package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW_DEDUCT;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Benefit;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Salary;

/**
 * Adds a benefit to the salary of the person identified using it's
 * displayed index or name from the address book.
 */
public class BenefitCommand extends Command {

    public static final String COMMAND_WORD = "benefit";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": add benefits to the salary of the person identified "
        + "by the index number or name used in the last person listing.\n"
        + "Parameters: INDEX (must be a positive integer) or NAME, AMOUNT, REASON\n"
        + "Example 1 (index): " + COMMAND_WORD + " 1, 3000.00, bonus\n"
        + "Example 2 (name): " + COMMAND_WORD + " /n john, 150.00, transport\n";

    public static final String MESSAGE_ARGUMENTS = "The benefits for the specified person is as below\n%s";

    private final Index index;
    private final NameContainsKeywordsPredicate name;
    private final Benefit benefit;

    /**
     * Creates a BenefitCommand to add benefits
     * to the salary of the person specified by the index.
     * @param index The index of the employee to be deleted.
     * @param benefit The benefit.
     */
    public BenefitCommand(Index index, Benefit benefit) {
        requireAllNonNull(index);
        this.index = index;
        this.name = null;
        this.benefit = benefit;
    }

    /**
     * Creates a BenefitCommand to add benefits
     * to the salary of the person specified by the name.
     * @param name The name of the employee to be deleted.
     * @param benefit The benefit.
     */
    public BenefitCommand(NameContainsKeywordsPredicate name, Benefit benefit) {
        requireAllNonNull(name);
        this.name = name;
        this.index = null;
        this.benefit = benefit;
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
     * Executes the command by index.
     * @param model {@code Model} which the command should operate on.
     * @return The command result in string format.
     * @throws CommandException If the index is invalid.
     */
    private CommandResult executeByIndex(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person employeeToCalculate = lastShownList.get(index.getZeroBased());
        Salary salary = employeeToCalculate.getSalary();
        salary.addBenefit(benefit);

        model.updateFilteredPersonList(person -> person.equals(employeeToCalculate));
        return new CommandResult(String.format(MESSAGE_ARGUMENTS, salary.getBenefitsString()));
    }

    /**
     * Executes the command by name.
     * @param model {@code Model} which the command should operate on.
     * @return The command result in string format.
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
            salary.addBenefit(benefit);
            model.updateFilteredPersonList(this.name);
            return new CommandResult(String.format(MESSAGE_ARGUMENTS, salary.getBenefitsString()));
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

        // instanceof handles nulls
        if (!(other instanceof BenefitCommand)) {
            return false;
        }

        BenefitCommand otherDeductCommand = (BenefitCommand) other;

        if (index != null && otherDeductCommand.index != null && benefit != null
            && otherDeductCommand.benefit != null) {
            return index.equals(otherDeductCommand.index)
                && benefit.equals(otherDeductCommand.benefit);
        }

        if (name != null && otherDeductCommand.name != null && benefit != null
            && otherDeductCommand.benefit != null) {
            return name.equals(otherDeductCommand.name)
                && benefit.equals(otherDeductCommand.benefit);
        }

        return false;
    }

    @Override
    public String toString() {
        if (index != null) {
            return new ToStringBuilder(this)
                .add("index", index)
                .add("benefit", benefit)
                .toString();
        } else {
            assert name != null;
            return new ToStringBuilder(this)
                .add("name", name)
                .add("benefit", benefit)
                .toString();
        }
    }
}
