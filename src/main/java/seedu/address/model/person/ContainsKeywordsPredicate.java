package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class ContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;
    private final String identifier;

    /**
     * Constructor a {@code ContainsKeywordsPredicate} with only keywords.
     *
     * @param keywords Keywords to filter the contact list for.
     */
    public ContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
        this.identifier = "name";
    }

    /**
     * Constructor a {@code ContainsKeywordsPredicate} with keywords and specific identifier.
     *
     * @param keywords Keywords to filter the contact list for.
     * @param identifier Identifier to determine whether to filter contact list by name or ID.
     */
    public ContainsKeywordsPredicate(List<String> keywords, String identifier) {
        this.keywords = keywords;
        this.identifier = identifier;
    }

    @Override
    public boolean test(Person person) {
        if (identifier.equals("name")) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
        } else {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getId().value, keyword));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ContainsKeywordsPredicate)) {
            return false;
        }

        ContainsKeywordsPredicate otherNameContainsKeywordsPredicate = (ContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
