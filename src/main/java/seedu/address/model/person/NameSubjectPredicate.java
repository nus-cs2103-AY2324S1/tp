package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameSubjectPredicate implements Predicate<Person> {
    private final NameContainsKeywordsPredicate name;
    private final SubjectContainsKeywordsPredicate subject;

    public NameSubjectPredicate(NameContainsKeywordsPredicate name, SubjectContainsKeywordsPredicate subject) {
        this.name = name;
        this.subject = subject;
    }

    @Override
    public boolean test(Person person) {
        return name.test(person) && subject.test(person);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameSubjectPredicate)) {
            return false;
        }

        NameSubjectPredicate otherNameSubjectPredicate = (NameSubjectPredicate) other;
        return name.equals(otherNameSubjectPredicate.name) && subject.equals(otherNameSubjectPredicate.subject);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("name", name).add("subject", subject).toString();
    }
}
