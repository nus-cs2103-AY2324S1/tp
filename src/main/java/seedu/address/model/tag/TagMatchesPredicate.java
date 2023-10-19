package seedu.address.model.tag;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.musician.Musician;

/**
 * Tests that a {@code Musician}'s {@code Genre} tag matches any of the keywords given.
 */
public class TagMatchesPredicate implements Predicate<Musician> {
    private final List<String> tagNames;

    public TagMatchesPredicate(List<String> tagNames) {
        this.tagNames = tagNames;
    }

    @Override
    public boolean test(Musician musician) {
        return tagNames.stream()
                .anyMatch(tagToMatch -> musician.getTags().stream().anyMatch(
                        musicianTag -> StringUtil.containsWordIgnoreCase(musicianTag.tagName, tagToMatch)
                ));
    }

    protected List<String> getTagNames() {
        return tagNames;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagMatchesPredicate)) {
            return false;
        }

        TagMatchesPredicate otherNameContainsKeywordsPredicate = (TagMatchesPredicate) other;
        return tagNames.equals(otherNameContainsKeywordsPredicate.tagNames);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("tags", tagNames).toString();
    }
}
