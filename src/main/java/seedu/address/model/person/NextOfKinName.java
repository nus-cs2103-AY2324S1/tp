package seedu.address.model.person;

/**
 * Represents a Person's next-of-kin name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class NextOfKinName extends Name {
    /**
     * Constructs a {@code NextOfKinName}.
     *
     * @param nokName A valid next-of-kin name.
     */
    public NextOfKinName(String nokName) {
        super(nokName);
    }
}
