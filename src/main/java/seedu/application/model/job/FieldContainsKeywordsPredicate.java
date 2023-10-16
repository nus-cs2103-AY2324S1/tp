package seedu.application.model.job;

import static seedu.application.model.job.Company.COMPANY_FIND_SPECIFIER;
import static seedu.application.model.job.Role.ROLE_FIND_SPECIFIER;

import java.util.List;
import java.util.function.Predicate;

import seedu.application.commons.util.StringUtil;
import seedu.application.commons.util.ToStringBuilder;
import seedu.application.logic.parser.exceptions.ParseException;

/**
 * Tests that a {@code Job}'s field matches any of the keywords given.
 */
public class FieldContainsKeywordsPredicate implements Predicate<Job> {
    private final String specifier;
    private final List<String> keywords;

    /**
     * Constructs a {@code FieldContainsKeywordsPredicate} using the specifier and keywords given.
     * @param specifier String denoting which field to search in
     * @param keywords List of keywords which the user is searching for.
     */
    public FieldContainsKeywordsPredicate(String specifier, List<String> keywords) {
        this.specifier = specifier;
        this.keywords = keywords;
    }

    private String getField(Job job) {
        switch (specifier) {
        case COMPANY_FIND_SPECIFIER:
            return job.getCompany().name;
        case ROLE_FIND_SPECIFIER:
            return job.getRole().description;
        default:
            return null;
        }
    }

    /**
     * Checks if a {@code String} is a valid specifier.
     * @param specifier The user String input.
     * @throws ParseException if the specifer is invalid.
     */
    public static boolean isValidSpecifier(String specifier) {
        return specifier.equals(COMPANY_FIND_SPECIFIER)
                || specifier.equals(ROLE_FIND_SPECIFIER);
    }

    @Override
    public boolean test(Job job) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(getField(job), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FieldContainsKeywordsPredicate)) {
            return false;
        }

        FieldContainsKeywordsPredicate otherFieldContainsKeywordsPredicate = (FieldContainsKeywordsPredicate) other;
        return specifier.equals(otherFieldContainsKeywordsPredicate.specifier)
                && keywords.equals(otherFieldContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
