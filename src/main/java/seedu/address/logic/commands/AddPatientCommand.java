package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TREATMENT;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.patients.Patient;

/**
 * Adds a patient to the address book.
 */
public class AddPatientCommand extends Command {

    public static final String COMMAND_WORD = "add-patient";

    public static final String MESSAGE_USAGE =
        COMMAND_WORD + ": Adds a patient to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_BIRTHDATE + "BIRTHDATE "
            + PREFIX_GENDER + "GENDER "
            + "[" + PREFIX_REMARK + "REMARK] "
            + "[" + PREFIX_TREATMENT + "TREATMENT] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John "
            + PREFIX_PHONE + "90676622 "
            + PREFIX_BIRTHDATE + "06-06-1998 "
            + PREFIX_GENDER + "M "
            + PREFIX_REMARK + "Allergic to Peanuts "
            + PREFIX_TREATMENT + "Braces "
            + PREFIX_EMAIL + "johntan@gmail.com "
            + PREFIX_ADDRESS + "60 Harvey Avenue "
            + PREFIX_TAG + "Urgent";

    public static final String MESSAGE_SUCCESS = "New Patient added: %1$s";
    public static final String MESSAGE_DUPLICATE_PATIENT = "This Patient already exists in ToothTracker";
    public static final String MESSAGE_INVALID_TREATMENT = "This treatment is invalid";

    private final Patient toAdd;

    /**
     * Creates an AddPatientCommand to add the specified {@code Patient}
     */
    public AddPatientCommand(Patient patient) {
        requireNonNull(patient);
        toAdd = patient;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPatient(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PATIENT);
        }

        String treatmentName = toAdd.getTreatmentName().toString();
        if (!model.hasTreatmentName(toAdd.getTreatmentName()) && !treatmentName.equals("NIL")) {
            throw new CommandException(MESSAGE_INVALID_TREATMENT);
        }

        model.addPatient(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddPatientCommand)) {
            return false;
        }

        AddPatientCommand otherAddCommand = (AddPatientCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("toAdd", toAdd)
            .toString();
    }
}
