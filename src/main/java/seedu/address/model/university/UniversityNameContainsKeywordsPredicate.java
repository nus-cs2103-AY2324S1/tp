package seedu.address.model.university;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code University}'s {@code UniversityName} matches any of the keywords given.
 */
public class UniversityNameContainsKeywordsPredicate implements Predicate<University> {
    private final String keyword;
    public UniversityNameContainsKeywordsPredicate(String keywords) {
        this.keyword = keywords;
    }

    @Override
    public boolean test(University university) {
        return university.getUniversityName().getName().toLowerCase()
                        .contains(keyword.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.address.model.university.UniversityNameContainsKeywordsPredicate)) {
            return false;
        }

        UniversityNameContainsKeywordsPredicate otherNameContainsKeywordsPredicate =
                (UniversityNameContainsKeywordsPredicate) other;
        return keyword.equals(otherNameContainsKeywordsPredicate.keyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keyword", keyword).toString();
    }
}



