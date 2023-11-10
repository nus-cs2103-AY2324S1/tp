package swe.context.model.contact;

import java.util.function.Predicate;
import java.util.Set;

import swe.context.commons.util.ToStringBuilder;
import swe.context.model.tag.Tag;

/**
 * Tests that a {@code Contact}'s Tags matches the tag given in full, case insensitive.
 */
public class ContainsTagPredicate implements Predicate<Contact> {
    private final String keyword;

    public ContainsTagPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Contact contact) {
        Set<Tag> tagSet = contact.getTags();

        for (Tag tag : tagSet) {
            if (tag.toString().equalsIgnoreCase(this.keyword)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ContainsTagPredicate)) {
            return false;
        }

        ContainsTagPredicate otherContainsTagPredicate = (ContainsTagPredicate) other;
        return keyword.equals(otherContainsTagPredicate.keyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keyword", keyword).toString();
    }
}
