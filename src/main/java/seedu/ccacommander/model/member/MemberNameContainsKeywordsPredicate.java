package seedu.ccacommander.model.member;

import java.util.List;
import java.util.function.Predicate;

import seedu.ccacommander.commons.util.StringUtil;
import seedu.ccacommander.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Member}'s {@code Name} matches any of the keywords given.
 */
public class MemberNameContainsKeywordsPredicate implements Predicate<Member> {
    private final List<String> keywords;

    public MemberNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Member member) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(member.getName().name, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MemberNameContainsKeywordsPredicate)) {
            return false;
        }

        MemberNameContainsKeywordsPredicate otherMemberNameContainsKeywordsPredicate =
                (MemberNameContainsKeywordsPredicate) other;
        return keywords.equals(otherMemberNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
