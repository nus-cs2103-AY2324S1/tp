package transact.model.transaction.info;
import static java.util.Objects.requireNonNull;

/**
 * Represents a description of a transaction in the system.
 * Description can be changed.
 */
public class Description {
    private final String value;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A description of the transaction.
     */
    public Description(String description) {
        requireNonNull(description);
        value = description;
    }

    /**
     * Returns the description string.
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Description)) {
            return false;
        }
        Description otherDescription = (Description) other;
        return value.equals(otherDescription.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

