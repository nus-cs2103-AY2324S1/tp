package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.PaySlipGenerator;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Generates a payslip for an employee.
 */
public class PayslipCommand extends Command {

    public static final String COMMAND_WORD = "payslip";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Generates a payslip in PDF format for the employee "
            + "identified by the index number used in the displayed employee list. "
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_PAYSLIP_SUCCESS = "Payslip generated for Employee: %1$s";

    private final Index index;

    /**
     * Creates a PayslipCommand to generate a payslip for the specified employee.
     * @param index of the person in the filtered person list to generate a payslip for.
     */
    public PayslipCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToGenerate = lastShownList.get(index.getZeroBased());
        try {
            PaySlipGenerator.generateReport(personToGenerate);
        } catch (Exception e) {
            throw new CommandException("Template file for the payslip is not found");
        }
        return new CommandResult(String.format(MESSAGE_PAYSLIP_SUCCESS, personToGenerate.getName().toString()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PayslipCommand)) {
            return false;
        }

        PayslipCommand otherPayslipCommand = (PayslipCommand) other;
        return index.equals(otherPayslipCommand.index);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("index", index)
            .toString();
    }
}
