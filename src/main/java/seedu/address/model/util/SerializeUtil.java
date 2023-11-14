package seedu.address.model.util;


import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ListEntryField;

/**
 * A utility class for serializing and deserializing ListEntryFields.
 */
public class SerializeUtil {
    /**
     * Serializes a ListEntryField into a string.
     */
    public static String serialize(ListEntryField obj) {
        return obj.toString();
    }

    /**
     * Deserializes a string into a ListEntryField.
     */
    public static <T extends ListEntryField> T deserialize(T defaultValue, Of<T> of, String str) throws ParseException {
        return str.equals(defaultValue.toString())
                ? defaultValue
                : of.apply(str);
    }
}
