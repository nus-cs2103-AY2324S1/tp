package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword)
                        ||
                        StringUtil.containsWordIgnoreCase(person.getNric().nric, keyword)
                        ||
                        StringUtil.containsWordIgnoreCase(person.getPhone().value, keyword)
                        ||
                        person.getAllergies().stream()
                                .anyMatch(hobby -> StringUtil.containsWordIgnoreCase(hobby.allergy, keyword))
                        ||
                        StringUtil.containsWordIgnoreCase(person.getGender().gender, keyword)
                        ||
                        StringUtil.containsWordIgnoreCase(person.getBloodType().bloodType, keyword)
                        ||
                        StringUtil.containsWordIgnoreCase(person.getAge().age.toString(), keyword)
                        ||
                        StringUtil.containsWordIgnoreCase(person.getEmail().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameContainsKeywordsPredicate)) {
            return false;
        }

        NameContainsKeywordsPredicate otherNameContainsKeywordsPredicate = (NameContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
