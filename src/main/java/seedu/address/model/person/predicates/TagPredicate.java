package seedu.address.model.person.predicates;

import java.util.List;

import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tags} matches any of the keywords given.
 */
public class TagPredicate implements FindCommandPredicate {
    private final List<Tag> tags;

    public TagPredicate(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toFilterString() {
        return "tag: [" + tags.stream().map(tag -> tag.tagName).reduce((x, y) -> x + ", " + y).orElse("") + "]";
    }

    @Override
    public boolean test(Person person) {
        return tags.stream()
                .anyMatch(predicateTag -> person.getTags().stream()
                        .anyMatch(predicateTag::equals));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagPredicate)) {
            return false;
        }

        TagPredicate otherNameContainsKeywordsPredicate = (TagPredicate) other;
        return tags.equals(otherNameContainsKeywordsPredicate.tags);
    }

    @Override
    public String toString() {
        return "tag: " + tags;
    }
}
