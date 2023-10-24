package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

public class Balance {

    public final Integer value;

    public Balance(Integer balanceInCents) {
        requireNonNull(balanceInCents);
        value = balanceInCents;
    }

    public String toStringInDollars() {
        if (value < 0) {
            return String.format("You owe them: -$%d.%02d", -value / 100, -value % 100);
        }
        return String.format("They owe you: $%d.%02d", value / 100, value % 100);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Balance)) {
            return false;
        }

        Balance otherBalance = (Balance) other;
        return value.equals(otherBalance.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
