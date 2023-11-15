package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PRESCRIPTIONS;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.prescription.Prescription;

/**
 * Lists all prescriptions stored to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all prescriptions stored.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all prescriptions.";

    public static final String MESSAGE_EMPTY_LIST = "No prescriptions found.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredPrescriptionList(PREDICATE_SHOW_ALL_PRESCRIPTIONS);

        ObservableList<Prescription> prescriptionList = model.getFilteredPrescriptionList();
        if (prescriptionList.isEmpty()) {
            return new CommandResult(MESSAGE_EMPTY_LIST);
        } else {
            return new CommandResult(MESSAGE_SUCCESS);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ListCommand;
    }
}
