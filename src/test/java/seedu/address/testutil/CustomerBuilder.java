package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.customer.Budget;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.Email;
import seedu.address.model.customer.Name;
import seedu.address.model.customer.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Customer objects.
 */
public class CustomerBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_BUDGET = "1230000";

    private Name name;
    private Phone phone;
    private Email email;
    private Budget budget;
    private Set<Tag> tags;

    /**
     * Creates a {@code CustomerBuilder} with the default details.
     */
    public CustomerBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        budget = new Budget(DEFAULT_BUDGET);
        tags = new HashSet<>();
    }

    /**
     * Initializes the CustomerBuilder with the data of {@code customerToCopy}.
     */
    public CustomerBuilder(Customer customerToCopy) {
        name = customerToCopy.getName();
        phone = customerToCopy.getPhone();
        email = customerToCopy.getEmail();
        budget = customerToCopy.getBudget();
        tags = new HashSet<>(customerToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Customer} that we are building.
     */
    public CustomerBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Budget} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withBudget(String budget) {
        this.budget = new Budget(budget.trim());
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Customer build() {
        return new Customer(name, phone, email, budget, tags);
    }

}
