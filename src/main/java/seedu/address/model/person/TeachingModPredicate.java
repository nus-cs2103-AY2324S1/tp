package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Mods} matches any of the keywords given.
 */
public class TeachingModPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TeachingModPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> person.getMods().stream()
                        .anyMatch(mod -> StringUtil.containsWordIgnoreCase(mod.toString(), keyword)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TeachingModPredicate)) {
            return false;
        }

        TeachingModPredicate otherNameContainsKeywordsPredicate = (TeachingModPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
