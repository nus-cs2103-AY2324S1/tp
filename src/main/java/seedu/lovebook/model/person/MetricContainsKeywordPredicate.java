package seedu.lovebook.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.function.Predicate;

import seedu.lovebook.commons.util.StringUtil;
import seedu.lovebook.commons.util.ToStringBuilder;
import seedu.lovebook.logic.parser.Prefix;



/**
 * Tests that a {@code Date}'s {@code Name} matches any of the keywords given.
 */
public class MetricContainsKeywordPredicate implements Predicate<Date> {
    private final String keyword;
    private final Prefix metric;
    /**
     * Constructor that initialises the String keyword and Prefix metric
    */
    public MetricContainsKeywordPredicate(String keyword, Prefix metric) {
        this.keyword = keyword;
        this.metric = metric;
    }

    @Override
    public boolean test(Date date) {
        requireNonNull(metric);
        if (metric.equals(PREFIX_NAME)) {
            return StringUtil.containsWordIgnoreCase(date.getName().fullName, keyword);
        }
        if (metric.equals(PREFIX_AGE)) {
            return StringUtil.containsWordIgnoreCase(date.getAge().value, keyword);
        }
        if (metric.equals(PREFIX_GENDER)) {
            return StringUtil.containsWordIgnoreCase(date.getGender().value, keyword);
        }
        if (metric.equals(PREFIX_HEIGHT)) {
            return StringUtil.containsWordIgnoreCase(date.getHeight().value, keyword);
        }
        return false; // invalid metric
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MetricContainsKeywordPredicate)) {
            return false;
        }

        MetricContainsKeywordPredicate otherMetricContainsKeywordsPredicate = (MetricContainsKeywordPredicate) other;
        return keyword.equals(otherMetricContainsKeywordsPredicate.keyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keyword", keyword).add("prefix", metric)
                .toString();
    }
}
