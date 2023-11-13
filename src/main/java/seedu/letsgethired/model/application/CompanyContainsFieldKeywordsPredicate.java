package seedu.letsgethired.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_CYCLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_NOTE_INSERT;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.List;
import java.util.function.Predicate;

import javafx.util.Pair;
import seedu.letsgethired.commons.util.ToStringBuilder;
import seedu.letsgethired.logic.parser.Prefix;

/**
 * Tests that a {@code InternApplication}'s {@code Company} matches any of the keywords given.
 */
public class CompanyContainsFieldKeywordsPredicate implements Predicate<InternApplication> {
    private static final String EMPTY_FIELD_VALUE = "";

    private final List<Pair<Prefix, String>> fieldKeywords;

    /**
     * Creates a Predicate for {@code InternAppplication}
     *
     * @param fieldKeywords List of pairs of field and keywords to be checked for
     */
    public CompanyContainsFieldKeywordsPredicate(List<Pair<Prefix, String>> fieldKeywords) {
        requireNonNull(fieldKeywords);
        this.fieldKeywords = fieldKeywords;
    }

    /**
     * Returns whether the keyword has a partial match in the search string.
     *
     * @param searchString String to be searched
     * @param keyword Keyword to be searched for
     * @return whether the keyword has a partial match in the search string
     */
    public boolean partialMatch(String searchString, String keyword) {
        assert searchString != null;
        assert keyword != null;

        int keywordIndex = 0;
        int searchIndex = 0;
        while (keywordIndex < keyword.length() && searchIndex < searchString.length()) {
            if (Character.toLowerCase(keyword.charAt(keywordIndex))
                    == Character.toLowerCase(searchString.charAt(searchIndex))) {
                keywordIndex++;
            }
            searchIndex++;
        }
        return keywordIndex == keyword.length();
    }

    public String getFieldValue(Prefix fieldPrefix, InternApplication internApplication) {
        assert fieldPrefix != null;
        assert internApplication != null;

        if (fieldPrefix.equals(PREFIX_COMPANY)) {
            return internApplication.getCompany().value;
        }
        if (fieldPrefix.equals(PREFIX_CYCLE)) {
            return internApplication.getCycle().value;
        }
        if (fieldPrefix.equals(PREFIX_NOTE_INSERT)) {
            return internApplication.getNotes()
                    .stream()
                    .map(x -> x.value)
                    .reduce("", (x, y) -> x + "/" + y); // We can just append since we are finding a substring
        }
        if (fieldPrefix.equals(PREFIX_STATUS)) {
            return internApplication.getStatus().value;
        }
        if (fieldPrefix.equals(PREFIX_ROLE)) {
            return internApplication.getRole().value;
        }
        if (fieldPrefix.equals(PREFIX_DEADLINE)) {
            return internApplication.getDeadline().value;
        }

        return EMPTY_FIELD_VALUE;
    }

    @Override
    public boolean test(InternApplication internApplication) {
        return fieldKeywords.stream()
                .allMatch(keyword -> partialMatch(getFieldValue(keyword.getKey(), internApplication),
                        keyword.getValue()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CompanyContainsFieldKeywordsPredicate)) {
            return false;
        }

        CompanyContainsFieldKeywordsPredicate otherCompanyContainsFieldKeywordsPredicate =
                (CompanyContainsFieldKeywordsPredicate) other;
        return fieldKeywords.equals(otherCompanyContainsFieldKeywordsPredicate.fieldKeywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", fieldKeywords).toString();
    }
}
