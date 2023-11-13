package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} or {@code Group} matches any of the keywords given.
 */
public class PersonNameOrGroupContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    /**
     * Constructs the NameContainsKeywordsPredicate with provided keywords.
     *
     * @param keywords keywords from user input.
     */
    public PersonNameOrGroupContainsKeywordsPredicate(List<String> keywords) {
        assert keywords != null;

        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> (StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword)
                        || person.getGroups().stream().anyMatch(
                                group -> StringUtil.containsWordIgnoreCase(group.groupName, keyword))));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonNameOrGroupContainsKeywordsPredicate)) {
            return false;
        }

        PersonNameOrGroupContainsKeywordsPredicate otherNameOrGroupContainsKeywordsPredicate =
                (PersonNameOrGroupContainsKeywordsPredicate) other;
        return keywords.equals(otherNameOrGroupContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }

}
