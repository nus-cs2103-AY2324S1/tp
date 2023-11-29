package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.dentist.Dentist;

/**
 * Represents a command to search for dentists whose names contain specified keywords.
 * The search is case-insensitive, and the matching dentists are displayed as a list
 * with index numbers.
 */
public class SearchDentistCommand extends Command {

    public static final String COMMAND_WORD = "search-dentist";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all dentists whose names contain any of "
        + "the specified keywords (case-insensitive) and displays them as a list.\n"
        + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
        + "Example: " + COMMAND_WORD + " John Tan";

    private final SearchType searchType;
    private NameContainsKeywordsPredicate predicate = null;
    private long dentistID;

    /**
     * An enumeration representing different types of search criteria.
     */
    public enum SearchType {
        BY_ID,
        BY_NAME
    }

    /**
     * Constructs a SearchDentistCommand to search for dentists by their names using the provided predicate.
     *
     * @param predicate The predicate used for searching dentists by name.
     */
    public SearchDentistCommand(NameContainsKeywordsPredicate predicate) {
        this.searchType = SearchType.BY_NAME;
        this.predicate = predicate;
    }

    /**
     * Constructs a SearchDentistCommand to search for a dentist by their ID.
     *
     * @param dentistID The ID of the dentist to search for.
     */
    public SearchDentistCommand(long dentistID) {
        this.searchType = SearchType.BY_ID;
        this.dentistID = dentistID;
    }

    /**
     * Executes the search operation on the provided model.
     *
     * @param model The model containing the dentist data.
     * @return A CommandResult containing the search result message.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        // Solution adapted and inspired from https://chat.openai.com/share/c95e431a-4406-4a1e-b88f-cbff729f1cca
        // Solution adapted and inspired from https://chat.openai.com/share/d392c3fe-0d09-40ef-8f28-e4e5e6ced48b
        if (searchType == SearchType.BY_NAME) {

            model.updateFilteredDentistList(predicate);
            return new CommandResult(
                String.format(Messages.MESSAGE_DENTISTS_LISTED_OVERVIEW, model.getFilteredDentistList().size()));

        } else if (searchType == SearchType.BY_ID) {

            if (dentistID != 0) {
                Predicate<Dentist> dentistIdPredicate = dentist -> dentist.getId() == dentistID;
                model.updateFilteredDentistList(dentistIdPredicate);

                if (model.getFilteredDentistList().isEmpty()) {
                    return new CommandResult("No dentist found with dentist ID " + dentistID + ".");
                } else {
                    return new CommandResult("Dentist with dentist ID " + dentistID + " found.");
                }
            } else {
                return new CommandResult("No dentist with dentist ID 0");
            }
        }
        return new CommandResult("Invalid search");
    }

    /**
     * Checks if this SearchDentistCommand is equal to another object.
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
        if (!(other instanceof SearchDentistCommand)) {
            return false;
        }

        SearchDentistCommand otherSearchDentistCommand = (SearchDentistCommand) other;

        switch (this.searchType) {
        case BY_NAME:
            return otherSearchDentistCommand.searchType == this.searchType
                && this.predicate.equals(otherSearchDentistCommand.predicate);
        case BY_ID:
            return otherSearchDentistCommand.searchType == this.searchType
                && this.dentistID == otherSearchDentistCommand.dentistID;
        default:
            return false;
        }
    }

    /**
     * Returns a string representation of this SearchDentistCommand.
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
