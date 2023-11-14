package seedu.address.model.customer;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Customer} is same with the given customer.
 */
public class SameCustomerPredicate implements Predicate<Customer> {

    private final Customer customer;

    /**
     * Constructs a {@code SameCustomerPredicate}.
     *
     * @param customer the specified customer
     */
    public SameCustomerPredicate(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean test(Customer targetCustomer) {
        return customer.equals(targetCustomer);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SameCustomerPredicate)) {
            return false;
        }

        SameCustomerPredicate otherSameCustomerPredicate = (SameCustomerPredicate) other;
        return this.customer == otherSameCustomerPredicate.customer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Customer", customer).toString();
    }
}
