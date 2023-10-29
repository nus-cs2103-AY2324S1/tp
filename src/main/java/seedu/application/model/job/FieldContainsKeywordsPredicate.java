package seedu.application.model.job;

import java.util.List;
import java.util.function.Predicate;

import seedu.application.commons.util.StringUtil;
import seedu.application.commons.util.ToStringBuilder;
import seedu.application.logic.parser.CliSyntax;
import seedu.application.logic.parser.Prefix;

/**
 * Tests that a {@code Job}'s field matches any of the keywords given.
 */
public class FieldContainsKeywordsPredicate implements Predicate<Job> {
    private final Prefix prefix;
    private final List<String> keywords;

    /**
     * Constructs a {@code FieldContainsKeywordsPredicate} using the prefix and keywords given.
     * @param prefix Prefix denoting which field to search in.
     * @param keywords List of keywords which the user is searching for.
     */
    public FieldContainsKeywordsPredicate(Prefix prefix, List<String> keywords) {
        this.prefix = prefix;
        this.keywords = keywords;
    }

    private String getField(Job job) {
        if (prefix.equals(CliSyntax.PREFIX_COMPANY)) {
            return job.getCompany().name;
        }

        if (prefix.equals(CliSyntax.PREFIX_ROLE)) {
            return job.getRole().description;
        }

        if (prefix.equals(CliSyntax.PREFIX_STATUS)) {
            return job.getStatus().status;
        }

        if (prefix.equals(CliSyntax.PREFIX_INDUSTRY)) {
            return job.getIndustry().industry;
        }

        if (prefix.equals(CliSyntax.PREFIX_DEADLINE)) {
            return job.getDeadline().deadline;
        }

        if (prefix.equals(CliSyntax.PREFIX_JOB_TYPE)) {
            return job.getJobType().jobType;
        }

        return null;
    }

    /**
     * Checks if a {@code Prefix} is a valid prefix in the context of the Find command.
     * @param prefix The prefix specified by the user.
     */
    public static boolean isValidPrefix(Prefix prefix) {
        return prefix.equals(CliSyntax.PREFIX_COMPANY)
                || prefix.equals(CliSyntax.PREFIX_ROLE)
                || prefix.equals(CliSyntax.PREFIX_STATUS)
                || prefix.equals(CliSyntax.PREFIX_INDUSTRY)
                || prefix.equals(CliSyntax.PREFIX_DEADLINE)
                || prefix.equals(CliSyntax.PREFIX_JOB_TYPE);
    }

    @Override
    public boolean test(Job job) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(getField(job), keyword));
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
        return prefix.equals(otherFieldContainsKeywordsPredicate.prefix)
                && keywords.equals(otherFieldContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
