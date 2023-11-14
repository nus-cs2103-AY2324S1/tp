package seedu.address.model.finance;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
/**
 * Tests that a {@code Finance}'s {@code Name} matches the input exactly.
 */
public class ClientNameExactMatchPredicate implements Predicate<Finance> {
    private final String clientName;
    public ClientNameExactMatchPredicate(String clientName) {
        this.clientName = clientName;
    }

    public String getClientName() {
        return clientName;
    }

    @Override
    public boolean test(Finance finance) {
        if (finance.getClient() == null) {
            return false;
        }
        return clientName.equals(finance.getClient().getName().toString());
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClientNameExactMatchPredicate)) {
            return false;
        }
        ClientNameExactMatchPredicate otherClientNameExactMatchPredicate =
                (ClientNameExactMatchPredicate) other;
        return clientName.equals(otherClientNameExactMatchPredicate.clientName);
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this).add("clientName", clientName).toString();
    }
}
