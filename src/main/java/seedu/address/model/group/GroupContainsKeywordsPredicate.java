package seedu.address.model.group;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Group}'s {@code Number} matches any of the keywords given.
 */
public class GroupContainsKeywordsPredicate implements Predicate<Group> {
    private final List<String> keywords;

    public GroupContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Group group) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(String.valueOf(new Integer(group.getNumber())), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GroupContainsKeywordsPredicate)) {
            return false;
        }

        GroupContainsKeywordsPredicate otherGroupContainsKeywordsPredicate = (GroupContainsKeywordsPredicate) other;
        return keywords.equals(otherGroupContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
