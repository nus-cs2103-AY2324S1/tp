package seedu.address.model.gradedtest;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code GradedTest}'s {@code Name} matches any of the keywords given.
 */
public class GradedTestNameContainsKeywordsPredicate implements Predicate<GradedTest> {
    private final List<String> keywords;

    public GradedTestNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(GradedTest gradedTest) {
        return keywords.stream().anyMatch(keyword ->
                StringUtil.containsWordIgnoreCase(gradedTest.getRA1().value, keyword)
                        || StringUtil.containsWordIgnoreCase(gradedTest.getRA2().value, keyword)
                        || StringUtil.containsWordIgnoreCase(gradedTest.getMidTerms().value, keyword)
                        || StringUtil.containsWordIgnoreCase(gradedTest.getFinals().value, keyword)
                        || StringUtil.containsWordIgnoreCase(gradedTest.getPracticalExam().value, keyword)
        );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GradedTestNameContainsKeywordsPredicate)) {
            return false;
        }

        GradedTestNameContainsKeywordsPredicate otherNameContainsKeywordsPredicate =
                (GradedTestNameContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("gradedTest keywords", keywords).toString();
    }
}
