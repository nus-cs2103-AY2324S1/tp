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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IllnessContainsKeywordsPredicate)) {
            return false;
        }

        IllnessContainsKeywordsPredicate otherIllnessContainsKeywordsPredicate = (IllnessContainsKeywordsPredicate)
                other;
        return keywords.equals(otherIllnessContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
