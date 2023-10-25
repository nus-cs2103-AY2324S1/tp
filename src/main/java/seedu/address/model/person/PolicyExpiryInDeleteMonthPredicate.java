package seedu.address.model.person;

import seedu.address.model.month.DeleteMonth;

/**
 * Tests that a {@code Person}'s {@code Policy Expiry date} is in delete month.
 */
public class PolicyExpiryInDeleteMonthPredicate extends FieldPredicates {
    private final DeleteMonth deleteMonth;

    public PolicyExpiryInDeleteMonthPredicate(DeleteMonth deleteMonth) {
        super(deleteMonth.toString());
        this.deleteMonth = deleteMonth;
    }

    @Override
    public boolean test(Person person) {
        return person.getPolicy().getPolicyExpiryDate().isInMonth(deleteMonth);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PolicyExpiryInDeleteMonthPredicate)) {
            return false;
        }
        PolicyExpiryInDeleteMonthPredicate otherPolicyExpiryInDeleteMonthPredicate =
                (PolicyExpiryInDeleteMonthPredicate) other;
        return keywords.equals(otherPolicyExpiryInDeleteMonthPredicate.keywords);
    }
}
