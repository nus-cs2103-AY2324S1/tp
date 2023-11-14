package seedu.address.model.person;

/**
 * Represents a Person's next-of-kin phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class NextOfKinPhone extends Phone {
    public static final String MESSAGE_CONSTRAINTS = "Next of Kin's " + Phone.MESSAGE_CONSTRAINTS;
    /**
     * Constructs a {@code NextOfKinPhone}.
     *
     * @param nextOfKinPhone A valid phone number.
     */
    public NextOfKinPhone(String nextOfKinPhone) {
        super(nextOfKinPhone);
    }
}
