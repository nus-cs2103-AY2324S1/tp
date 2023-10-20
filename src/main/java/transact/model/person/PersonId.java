package transact.model.person;

import static java.util.Objects.requireNonNull;
import static transact.commons.util.AppUtil.checkArgument;

import java.util.TreeSet;

/**
 * Represents a unique person ID in the system.
 * Guarantees: immutable; is valid as declared in constructors
 */
public class PersonId implements Comparable<PersonId> {

    public static final String MESSAGE_CONSTRAINTS = "Person ID should be a unique number";
    public static final String VALIDATION_REGEX = "^\\d+$";

    private static TreeSet<Integer> usedIds = new TreeSet<>();

    public final Integer value;

    /**
     * Constructs a {@code PersonId}.
     *
     */
    public PersonId() {
        this(usedIds.isEmpty() ? 0 : usedIds.last() + 1);
    }

    /**
     * Constructs a {@code PersonId} with specified {@code id}
     */
    public PersonId(Integer id) {
        requireNonNull(id);
        checkArgument(isValidPersonId(id), MESSAGE_CONSTRAINTS);
        value = id;
        usedIds.add(value);
    }

    /**
     * Constructs a {@code PersonId} with specified {@code id}
     */
    public PersonId(String id) {
        this(parsePersonId(id));
    }

    /**
     * Parse Person ID from string.
     */
    public static Integer parsePersonId(String test) {
        checkArgument(test.matches(VALIDATION_REGEX), MESSAGE_CONSTRAINTS);
        return Integer.parseInt(test);
    }

    /**
     * Returns true if a given integer is a valid Person ID.
     */
    public static boolean isValidPersonId(Integer test) {
        return !usedIds.contains(test);
    }

    /**
     * Free up {@code usedId}, allowing it to be used when Person object is
     * deleted
     *
     * @param usedId
     *            Id that will not be used anymore
     */
    public static void freeUsedPersonIds(Integer usedId) {
        usedIds.remove(usedId);
    }

    public Integer getValue() {
        return value;
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

        // instanceof handles nulls
        if (!(other instanceof PersonId)) {
            return false;
        }

        PersonId otherId = (PersonId) other;
        return value.equals(otherId.value);
    }

    @Override
    public int compareTo(PersonId otherId) {
        return this.value.compareTo(otherId.getValue());
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
