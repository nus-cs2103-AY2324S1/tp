package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;


/**
 * Tests that a {@code Member}'s {@code Fields} matches any of the keywords given.
 */
public class MemberContainsKeywordsPredicate implements Predicate<Member> {
    private final List<String> keywords;

    public MemberContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Member member) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(member.getName().fullName, keyword)
                        || StringUtil.containsWordIgnoreCase(member.getPhone().value, keyword)
                        || StringUtil.containsWordIgnoreCase(member.getEmail().value, keyword)
                        || StringUtil.containsWordIgnoreCase(member.getTelegram().toString(), keyword)
                        || member.getTags().stream()
                                .anyMatch(tag -> StringUtil.containsWordIgnoreCase(tag.tagName, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MemberContainsKeywordsPredicate)) {
            return false;
        }

        MemberContainsKeywordsPredicate otherMemberContainsKeywordsPredicate = (MemberContainsKeywordsPredicate) other;
        return keywords.equals(otherMemberContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
