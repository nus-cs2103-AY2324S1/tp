package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class PayrollCommand extends Command{

    public static final String COMMAND_WORD = "payroll";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": calculates the payroll of the person identified "
            + "by the index number or name used in the last person listing.\n"
            + "Parameter: INDEX (must be a positive integer) or NAME\n"
            + "Example 1 (index): " + COMMAND_WORD + " 1\n"
            + "Example 2 (name): " + COMMAND_WORD + " /n john\n";

    public static final String MESSAGE_ARGUMENTS = "The payroll for the following person is";

    private final Index index;
    private final NameContainsKeywordsPredicate name;

    public PayrollCommand(Index index) {
        requireAllNonNull(index);
        this.index = index;
        this.name = null;
    }

    public PayrollCommand(NameContainsKeywordsPredicate name) {
        requireAllNonNull(name);
        this.name = name;
        this.index = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (index != null) {
            return executeByIndex();
        } else {
            return executeByName();
        }
    }

    public CommandResult executeByIndex(Model model) {
        
    }

    public CommandResult executeByName(Model model) {

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
