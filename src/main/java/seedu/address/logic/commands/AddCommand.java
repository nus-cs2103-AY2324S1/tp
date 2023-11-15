package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONFLICTING_DRUGS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOSAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FREQUENCY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TOTAL_STOCK;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.prescription.Prescription;


/**
 * Adds a prescription to the prescription list.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a prescription to the prescription list. \n"
            + "Parameters: "
            + PREFIX_NAME + "medication_name "
            + PREFIX_DOSAGE + "dosage "
            + PREFIX_FREQUENCY + "frequency "
            + PREFIX_START_DATE + "start_date "
            + PREFIX_END_DATE + "end_date "
            + PREFIX_EXPIRY_DATE + "expiry_date "
            + PREFIX_TOTAL_STOCK + "total_stock "
            + PREFIX_CONFLICTING_DRUGS + "conflicting_drugs "
            + PREFIX_NOTE + "note \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Aspirin "
            + PREFIX_DOSAGE + "1 "
            + PREFIX_FREQUENCY + "Daily "
            + PREFIX_START_DATE + "01/08/2023 "
            + PREFIX_END_DATE + "25/12/2023 "
            + PREFIX_EXPIRY_DATE + "01/01/2024 "
            + PREFIX_TOTAL_STOCK + "100 "
            + PREFIX_CONFLICTING_DRUGS + "Paracetamol Amoxicillin "
            + PREFIX_NOTE + "Test note";


    public static final String MESSAGE_SUCCESS = "New prescription added: %1$s.";
    public static final String MESSAGE_DUPLICATE_PRESCRIPTION = "This prescription already "
            + "exists in the prescription list.";
    public static final String MESSAGE_DRUG_CLASH = "There are conflicting drugs in BayMeds. \n";
    public static final String MESSAGE_INVALID_DATES = "Start date cannot be after end date, "
            + "and expiry date cannot be before start or end date.";

    private final Prescription toAdd;

    /**
     * Creates an AddPrescriptionCommand to add the specified {@code Prescription}
     */
    public AddCommand(Prescription prescription) {
        requireNonNull(prescription);
        toAdd = prescription;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPrescription(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PRESCRIPTION);
        }

        if (model.hasDrugClash(toAdd)) {
            model.addPrescription(toAdd);
            return new CommandResult(MESSAGE_DRUG_CLASH
                    + String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
        }
        model.addPrescription(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherAddCommand = (AddCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
