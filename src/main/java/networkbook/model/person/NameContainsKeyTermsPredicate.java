package networkbook.model.person;

import java.util.List;
import java.util.function.Predicate;

import networkbook.commons.util.StringUtil;
import networkbook.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the key terms given.
 */
public class NameContainsKeyTermsPredicate implements Predicate<Person> {
    private final List<String> keyTerms;

    public NameContainsKeyTermsPredicate(List<String> keyTerms) {
        this.keyTerms = keyTerms;
    }

    @Override
    public boolean test(Person person) {
        return keyTerms.stream()
                .anyMatch(keyTerm -> StringUtil.containsTermIgnoreCase(person.getName().fullName, keyTerm));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameContainsKeyTermsPredicate)) {
            return false;
        }

        NameContainsKeyTermsPredicate otherNameContainsKeyTermsPredicate = (NameContainsKeyTermsPredicate) other;
        return keyTerms.equals(otherNameContainsKeyTermsPredicate.keyTerms);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("key terms", keyTerms).toString();
    }
}
