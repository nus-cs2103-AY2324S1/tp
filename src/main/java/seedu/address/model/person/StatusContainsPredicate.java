package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Status} duration within the given start and end.
 */
public class StatusContainsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public StatusContainsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> keyword.isEmpty()
                        || StringUtil.containsWordIgnoreCase(person.getStatus().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StatusContainsPredicate)) {
            return false;
        }

        StatusContainsPredicate otherStatusContainsPredicate = (StatusContainsPredicate) other;
        return keywords.equals(otherStatusContainsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }

}
