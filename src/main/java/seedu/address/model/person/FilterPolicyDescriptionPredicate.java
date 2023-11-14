package seedu.address.model.person;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.policy.Policy;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class FilterPolicyDescriptionPredicate implements Predicate<Person> {
    private final String description;

    public FilterPolicyDescriptionPredicate(String description) {
        this.description = description;
    }

    @Override
    public boolean test(Person person) {
        Set<Policy> policies = person.getPolicies();
        for (Policy policy : policies) {
            if (policy.getDescription().contains(description)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterPolicyDescriptionPredicate)) {
            return false;
        }

        FilterPolicyDescriptionPredicate otherFilterPolicyDescriptionPredicate =
                (FilterPolicyDescriptionPredicate) other;
        return description.equals(otherFilterPolicyDescriptionPredicate.description);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("description", description).toString();
    }
}
