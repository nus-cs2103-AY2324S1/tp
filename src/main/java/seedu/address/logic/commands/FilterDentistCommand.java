package seedu.address.logic.commands;

import static seedu.address.logic.Messages.MESSAGE_USAGE_FILTER_DENTIST_FAIL;
import static seedu.address.logic.Messages.MESSAGE_USAGE_FILTER_DENTIST_SUCCESS;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.DentistAttributeContainsKeywordsPredicate;
import seedu.address.model.Model;
import seedu.address.model.person.dentist.Dentist;

/**
 * Filters the list of dentists based on specific criteria and updates the filtered list.
 */
public class FilterDentistCommand extends Command {

    public static final String COMMAND_WORD = "filter-dentist";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters the list of dentists by a specific criteria.\n"
        + "Parameters: a/ATTRIBUTE k/KEYWORD\n"
        + "Example: " + COMMAND_WORD + " a/phone k/98667722";

    private static final Set<String> ALLOWED_ATTRIBUTES = Set.of(
        "phone",
        "address",
        "email",
        "specialization",
        "name",
        "tags",
        "experience");

    private final String attribute;
    private final String keywords;

    /**
     * Constructs a FilterPatientCommand to filter a patient based on the provided attribute and keywords.
     *
     * @param attribute The predicate by which the patient should be filtered.
     * @param keywords  A list of keywords to match against the specified attribute.
     */
    public FilterDentistCommand(String attribute, String keywords) {
        this.attribute = attribute;
        this.keywords = keywords;
    }

    @Override
    public CommandResult execute(Model model) {

        Predicate<Dentist> predicate = new DentistAttributeContainsKeywordsPredicate(attribute, keywords);
        model.updateFilteredDentistList(predicate);

        String finalMessage;

        if (model.getFilteredDentistList().isEmpty()) {
            finalMessage = String.format(MESSAGE_USAGE_FILTER_DENTIST_FAIL, attribute, keywords);
        } else {
            finalMessage = String.format(MESSAGE_USAGE_FILTER_DENTIST_SUCCESS, attribute, keywords);
        }
        return new CommandResult(finalMessage);
    }

    /**
     * Checks if this FilterDentistCommand is equal to another object.
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
        if (!(other instanceof FilterDentistCommand)) {
            return false;
        }

        FilterDentistCommand otherFilterDentistCommand = (FilterDentistCommand) other;
        return attribute.equals(otherFilterDentistCommand.attribute)
            && keywords.equals(otherFilterDentistCommand.keywords);
    }

    /**
     * Returns a string representation of this FilterDentistCommand.
     *
     * @return A string containing information about the command.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("attribute", attribute)
            .add("keywords", keywords)
            .toString();
    }

    public static Set<String> getAllowedAttributes() {
        return ALLOWED_ATTRIBUTES;
    }
}
