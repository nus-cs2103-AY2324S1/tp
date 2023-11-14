package seedu.address.model.note;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Note}'s {@code Tag} matches any of the keywords given.
 */
public class NoteTagContainsKeywordsPredicate implements Predicate<Note> {
    private final String keyword;
    public NoteTagContainsKeywordsPredicate(String keywords) {
        this.keyword = keywords;
    }

    @Override
    public boolean test(Note note) {
        return note.getTags().toString().toLowerCase()
                .contains(keyword.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.address.model.note.NoteTagContainsKeywordsPredicate)) {
            return false;
        }

        NoteTagContainsKeywordsPredicate otherTagContainsKeywordsPredicate =
                (NoteTagContainsKeywordsPredicate) other;
        return keyword.equals(otherTagContainsKeywordsPredicate.keyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keyword", keyword).toString();
    }
}
