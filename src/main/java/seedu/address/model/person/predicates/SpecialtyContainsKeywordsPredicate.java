package seedu.address.model.person.predicates;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;
import seedu.address.model.person.Specialist;

import java.util.List;
import java.util.function.Predicate;

public class SpecialtyContainsKeywordsPredicate implements Predicate<Person> {

    private final List<String> keywords;

    public SpecialtyContainsKeywordsPredicate(List<String> keywords) {
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
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(specialist.getSpecialty().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SpecialtyContainsKeywordsPredicate)) {
            return false;
        }

        SpecialtyContainsKeywordsPredicate otherSpecialtyContainsKeywordsPredicate = (SpecialtyContainsKeywordsPredicate) other;
        return keywords.equals(otherSpecialtyContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }

}
