package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.patients.Patient;

/**
 * Represents a command to search for patients whose names contain specified keywords.
 * The search is case-insensitive, and the matching patients are displayed as a list
 * with index numbers.
 */
public class SearchPatientCommand extends Command {

    public static final String COMMAND_WORD = "search-patient";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all patients whose names contain any of "
        + "the specified keywords (case-insensitive) and displays them as a list.\n"
        + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
        + "Example: " + COMMAND_WORD + " John Tan";

    private final SearchType searchType;
    private long patientID;
    private NameContainsKeywordsPredicate predicate;

    /**
     * An enumeration representing different types of search criteria.
     */
    public enum SearchType {
        BY_ID,
        BY_NAME
    }

    /**
     * Constructs a SearchPatientCommand with the specified predicate.
     *
     * @param predicate The predicate used for searching patients.
     */
    public SearchPatientCommand(NameContainsKeywordsPredicate predicate) {
        this.searchType = SearchType.BY_NAME;
        this.predicate = predicate;
    }

    /**
     * Constructs a SearchPatientCommand to search for a patient by their ID.
     *
     * @param patientID The ID of the patient to search for.
     */
    public SearchPatientCommand(long patientID) {
        this.searchType = SearchType.BY_ID;
        this.patientID = patientID;
    }

    /**
     * Executes the search operation on the provided model.
     *
     * @param model The model containing the patient data.
     * @return A CommandResult containing the search result message.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (searchType == SearchPatientCommand.SearchType.BY_NAME) {

            model.updateFilteredPatientList(predicate);
            return new CommandResult(
                String.format(Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW, model.getFilteredPatientList().size()));

        } else if (searchType == SearchPatientCommand.SearchType.BY_ID) {

            if (patientID != 0) {
                Predicate<Patient> patientIdPredicate = patient -> patient.getId() == patientID;
                model.updateFilteredPatientList(patientIdPredicate);

                if (model.getFilteredPatientList().isEmpty()) {
                    return new CommandResult("No patient found with patient ID " + patientID + ".");
                } else {
                    return new CommandResult("Patient with patient ID " + patientID + " found.");
                }
            } else {
                return new CommandResult("No patient with patient ID 0");
            }
        }
        return new CommandResult("Invalid search");
    }

    /**
     * Checks if this SearchPatientCommand is equal to another object.
     *
     * @param other The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SearchPatientCommand)) {
            return false;
        }

        SearchPatientCommand otherSearchPatientCommand = (SearchPatientCommand) other;

        switch (this.searchType) {
        case BY_NAME:
            return otherSearchPatientCommand.searchType == this.searchType
                && this.predicate.equals(otherSearchPatientCommand.predicate);
        case BY_ID:
            return otherSearchPatientCommand.searchType == this.searchType
                && this.patientID == otherSearchPatientCommand.patientID;
        default:
            return false;
        }
    }

    /**
     * Returns a string representation of this SearchPatientCommand.
     *
     * @return A string containing information about the command.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("predicate", predicate)
            .toString();
    }
}
