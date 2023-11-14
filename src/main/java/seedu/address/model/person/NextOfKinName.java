package seedu.address.model.person;

/**
 * Represents a Person's next-of-kin name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class NextOfKinName extends Name {
    public static final String MESSAGE_CONSTRAINTS = "Next of Kin's " + Name.MESSAGE_CONSTRAINTS;
    /**
     * Constructs a {@code NextOfKinName}.
     *
     * @param nextOfKinName A valid next-of-kin name.
     */
    public NextOfKinName(String nextOfKinName) {
        super(nextOfKinName);
    }
}
