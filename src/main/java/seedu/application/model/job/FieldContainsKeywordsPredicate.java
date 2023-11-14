package seedu.application.model.job;

import static seedu.application.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_JOB_TYPE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import seedu.application.commons.util.StringUtil;
import seedu.application.commons.util.ToStringBuilder;
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

    /**
     * Generates a {@code Predicate<Job>} which is used to filter the list as specified the preamble.
     * @param keywords The list of keywords in the preamble.
     * @return the {@code Predicate<Job>} to be passed into the constructor for {@code CombinedPredicate}.
     */
    public static Predicate<Job> getPreamblePredicate(List<String> keywords) {
        Predicate<Job> preamblePredicate = x -> true;
        for (String keyword : keywords) {
            Predicate<Job> allFieldsPredicate = x -> false;
            allFieldsPredicate = allFieldsPredicate
                    .or(new FieldContainsKeywordsPredicate(PREFIX_COMPANY, Collections.singletonList(keyword)))
                    .or(new FieldContainsKeywordsPredicate(PREFIX_ROLE, Collections.singletonList(keyword)))
                    .or(new FieldContainsKeywordsPredicate(PREFIX_STATUS, Collections.singletonList(keyword)))
                    .or(new FieldContainsKeywordsPredicate(PREFIX_INDUSTRY, Collections.singletonList(keyword)))
                    .or(new FieldContainsKeywordsPredicate(PREFIX_DEADLINE, Collections.singletonList(keyword)))
                    .or(new FieldContainsKeywordsPredicate(PREFIX_JOB_TYPE, Collections.singletonList(keyword)));
            preamblePredicate = preamblePredicate.and(allFieldsPredicate);
        }
        return preamblePredicate;
    }

    private String getField(Job job) {
        if (prefix.equals(PREFIX_COMPANY)) {
            return job.getCompany().name;
        }

        if (prefix.equals(PREFIX_ROLE)) {
            return job.getRole().description;
        }

        if (prefix.equals(PREFIX_STATUS)) {
            return job.getStatus().status;
        }

        if (prefix.equals(PREFIX_INDUSTRY)) {
            return job.getIndustry().industry;
        }

        if (prefix.equals(PREFIX_DEADLINE)) {
            return job.getDeadline().deadline;
        }

        if (prefix.equals(PREFIX_JOB_TYPE)) {
            return job.getJobType().jobType;
        }

        return null;
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
