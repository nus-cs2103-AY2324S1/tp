package seedu.address.model.finance;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.person.Person;

/**
 * Represents a commission from a client.
 */
public class Commission extends Finance {

    /**
     * Constructs a Commission object with the specified amount, client, and description.
     *
     * @param amount      The amount of the commission.
     * @param client      The client or payee associated with the commission.
     * @param description A description of the commission.
     * @param timeDue     The time due of the commission.
     */
    public Commission(Amount amount, Person client, Description description, TimeDue timeDue) {
        requireAllNonNull(amount, description);
        this.amount = amount;
        this.client = client;
        this.description = description;
        this.timeDue = timeDue;
    }

    /**
     * Checks if both commissions have the same parameters.
     * This defines a weaker notion of equality between two commissions.
     *
     * @param commission The commission to compare with.
     * @return {@code true} if the commissions are equivalent, {@code false} otherwise.
     */
    public boolean isSameCommission(Commission commission) {
        boolean isSameClient;
        if (client == null) {
            isSameClient = commission.getClient() == null;
        } else {
            isSameClient = client.equals(commission.getClient());
        }

        return amount.equals(commission.getAmount())
                && isSameClient
                && description.equals(commission.getDescription())
                && timeDue.equals(commission.getTimeDue());
    }
}
