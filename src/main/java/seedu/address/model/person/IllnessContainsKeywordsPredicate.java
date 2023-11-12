package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Illness} matches any of the keywords given.
 */
public class IllnessContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public IllnessContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Tests if a person's illnesses contain any of the specified keywords.
     *
     * @param person The person to test.
     * @return {@code true} if the person's illnesses contain any of the keywords, {@code false} otherwise.
     */
    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(tag -> {
                    return person.getTags().toString().toLowerCase().contains(tag.toLowerCase());
                });
    }

    private boolean areStringListsEqualCaseInsensitive(List<String> keywords1, List<String> keywords2) {
        // Check if both lists have the same size
        if (keywords1.size() != keywords2.size()) {
            return false;
        }

        // Iterate through the elements and compare them in a case-insensitive manner
        for (int i = 0; i < keywords1.size(); i++) {
            String str1 = keywords1.get(i);
            String str2 = keywords2.get(i);

            // Use equalsIgnoreCase for case-insensitive comparison
            if (!str1.equalsIgnoreCase(str2)) {
                return false;
            }
        }

        // If all elements are equal, return true
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IllnessContainsKeywordsPredicate)) {
            return false;
        }

        IllnessContainsKeywordsPredicate otherIllnessContainsKeywordsPredicate =
                (IllnessContainsKeywordsPredicate) other;
        return areStringListsEqualCaseInsensitive(keywords, otherIllnessContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
