package seedu.address.model;

import java.util.List;

import seedu.address.model.person.Person;

/**
 * Manages the generation and updating of unique identity codes for persons in the address book.
 * This ensures that each person has a unique identity code.
 */
public class IdentityCodeManager {
    private static int currentMaxID = 0;

    /**
     * Provides the next unique identity code.
     *
     * @return The next available identity code.
     */
    public static int getNextIdentityCode() {
        return ++currentMaxID;
    }

    /**
     * Updates the maximum identity code value based on the given list of persons.
     * This ensures that the identity code remains unique as persons are added or removed.
     *
     * @param persons List of persons to determine the current maximum identity code.
     */
    public static void updateMaxID(List<Person> persons) {
        currentMaxID = persons.stream()
                .mapToInt(person -> person.getIdentityCode().getValue())
                .max()
                .orElse(0);
    }
}
