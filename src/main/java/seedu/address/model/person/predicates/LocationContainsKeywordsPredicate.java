package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;
import seedu.address.model.person.Specialist;

/**
 * Tests that a {@code Person}'s {@code Location} matches any of the keywords given.
 */
public class LocationContainsKeywordsPredicate implements Predicate<Person> {

    private final List<String> keywords;

    public LocationContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        if (!(person instanceof Specialist)) {
            return false;
        }

        // It is safe to type cast Person to Specialist due to the guard clause above.
        Specialist specialist = (Specialist) person;
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(specialist.getLocation().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LocationContainsKeywordsPredicate)) {
            return false;
        }

        LocationContainsKeywordsPredicate otherLocationContainsKeywordsPredicate =
                (LocationContainsKeywordsPredicate) other;
        return keywords.equals(otherLocationContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
