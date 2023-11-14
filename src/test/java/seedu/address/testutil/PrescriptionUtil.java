package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DOSAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FREQUENCY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TOTAL_STOCK;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.prescription.Prescription;

/**
 * A utility class for Prescription.
 */
public class PrescriptionUtil {

    /**
     * Returns an add command string for adding the {@code prescription}.
     */
    public static String getAddPrescriptionCommand(Prescription prescription) {
        return AddCommand.COMMAND_WORD + " " + getPrescriptionDetails(prescription);
    }

    public static String getEditPrescriptionCommand(Prescription prescription) {
        return EditCommand.COMMAND_WORD + " 1 " + getPrescriptionDetails(prescription);
    }

    /**
     * Returns the part of command string for the given {@code prescription}'s details.
     */
    public static String getPrescriptionDetails(Prescription prescription) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + prescription.getName().toString() + " ");
        sb.append(PREFIX_DOSAGE + prescription.getDosage().get().toString() + " ");
        sb.append(PREFIX_FREQUENCY + prescription.getFrequency().get().toString() + " ");
        sb.append(PREFIX_START_DATE + prescription.getStartDate().toString() + " ");
        sb.append(PREFIX_END_DATE + prescription.getEndDate().get().toString() + " ");
        sb.append(PREFIX_EXPIRY_DATE + prescription.getExpiryDate().get().toString() + " ");
        sb.append(PREFIX_TOTAL_STOCK + prescription.getTotalStock().get().toString() + " ");
        sb.append(PREFIX_NOTE + prescription.getNote().get().toString() + " ");
        return sb.toString();
    }

}
