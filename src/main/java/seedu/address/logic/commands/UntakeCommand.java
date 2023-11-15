package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOSAGE;
import static seedu.address.model.prescription.Prescription.COMPLETED_PREDICATE;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.prescription.ConsumptionCount;
import seedu.address.model.prescription.Dosage;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.SameNamePredicate;
import seedu.address.model.prescription.Stock;

/**
 * Represents a command to untake a specified number of doses of a prescription.
 */
public class UntakeCommand extends Command {
    public static final String COMMAND_WORD = "untake";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Untakes a specified number of doses of a prescription.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_DOSAGE + "number_of_doses (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_DOSAGE + "2";

    public static final String MESSAGE_SUCCESS = "Doses untaken from: %1$s";
    public static final String MESSAGE_INSUFFICIENT_CONSUMPTION = "Cannot untake more than what you have consumed.";

    private final Index targetIndex;
    private final int dosesToUntake;

    /**
     * Creates an UntakePrescriptionCommand to untake the specified number of doses from a prescription.
     *
     * @param targetIndex      The index of the prescription in the prescription list.
     * @param dosesToUntake      Number of doses to untake.
     */
    public UntakeCommand(Index targetIndex, Dosage dosesToUntake) {
        this.targetIndex = targetIndex;
        this.dosesToUntake = Integer.parseInt(dosesToUntake.toString());
    }

    /**
     * Executes the UntakePrescriptionCommand to untake the specified doses from the prescription.
     *
     * @param model The model of the prescription list.
     * @return A CommandResult with the result of the execution.
     * @throws CommandException If there are errors in executing the command.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Prescription> lastShownList = model.getFilteredPrescriptionList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PRESCRIPTION_DISPLAYED_INDEX);
        }

        Prescription prescription = lastShownList.get(targetIndex.getZeroBased());
        Optional<Stock> totalStock = prescription.getTotalStock();
        ConsumptionCount consumptionCount = prescription.getConsumptionCount();

        if (Integer.parseInt(prescription.getConsumptionCount().getConsumptionCount()) - dosesToUntake < 0) {
            throw new CommandException(MESSAGE_INSUFFICIENT_CONSUMPTION);
        }

        executeUntake(totalStock, consumptionCount);

        prescription.setIsCompleted(COMPLETED_PREDICATE.test(prescription));

        Predicate<Prescription> isSameName = new SameNamePredicate(prescription.getName());
        model.updateFilteredPrescriptionList(isSameName);

        return new CommandResult(String.format(MESSAGE_SUCCESS, prescription.getName()));
    }

    /**
     * Changes the stock and consumption count of the prescription being untaken.
     *
     * @param totalStock The stock of the prescription.
     * @param consumptionCount The consumption count of the prescription.
     */
    public void executeUntake(Optional<Stock> totalStock, ConsumptionCount consumptionCount) {
        if (totalStock.isPresent()) {
            totalStock.get().incrementCount(dosesToUntake);
        }
        consumptionCount.decrementCount(dosesToUntake);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof UntakeCommand)) {
            return false;
        }

        UntakeCommand otherCommand = (UntakeCommand) other;
        return targetIndex.equals(otherCommand.targetIndex)
                && dosesToUntake == otherCommand.dosesToUntake;
    }
}
