package seedu.address.commons.util;

import java.io.IOException;

import seedu.address.model.AddressBook;

/**
 * Utility class for parsing JSON data to an AddressBook object.
 */
public class JsonParserUtil {

    /**
     * Parses a JSON string into an AddressBook object.
     *
     * @param jsonString The JSON string to be parsed.
     * @return The parsed AddressBook object.
     * @throws IOException If there is an issue with the I/O operation during parsing.
     */
    public static AddressBook parseJsonToAddressBook(String jsonString) throws IOException {
        try {
            return JsonUtil.fromJsonString(jsonString, AddressBook.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
