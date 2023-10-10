package seedu.address.model.tag;

/**
 * Represents a Mod in the address book.
 */
public class Mod extends Tag {
    public static final String MESSAGE_CONSTRAINTS = "Mod codes should start with 2-3 alphabets, "
            + "followed by 4 numbers, and optionally end with an alphabet.";

    // 2-3 alphabets, followed by 4 digits, and optionally ending with an alphabet
    public static final String VALIDATION_REGEX = "^[A-Za-z]{2,3}\\d{4}[A-Za-z]?$";
    /**
     * Constructs a {@code Mod}.
     *
     * @param modName A valid mod name.
     */
    public Mod(String modName) {
        super(modName.toUpperCase());
    } // Mods can only have uppercase letters

    /**
     * Returns true if a given string is a valid mod name.
     */
    public static boolean isValidModName(String test) {
        return test.matches(VALIDATION_REGEX);
    }
}
