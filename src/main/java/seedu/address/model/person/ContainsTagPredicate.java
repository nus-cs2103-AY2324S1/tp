package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tag} matches the tag given.
 */
public class ContainsTagPredicate implements Predicate<Person> {
    private final Tag tagToCheck;

    public ContainsTagPredicate(Tag tagToCheck) {
        this.tagToCheck = tagToCheck;
    }

    public Tag getTagToCheck() {
        return tagToCheck;
    }

    @Override
    public boolean test(Person person) {
        return person.getTags().contains(tagToCheck);
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

        ContainsTagPredicate otherPersonHasTagPredicate = (ContainsTagPredicate) other;
        return tagToCheck.equals(otherPersonHasTagPredicate.tagToCheck);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("tagToCheck", tagToCheck).toString();
    }
}
