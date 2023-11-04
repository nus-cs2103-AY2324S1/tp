package networkbook.commons.util;

import networkbook.commons.exceptions.NullValueException;

public interface JsonObject {
    /**
     * Asserts that all fields that are not supposed to be null are not null.
     * To be used in reading json file.
     * @throws NullValueException If there is a null field.
     */
    void assertFieldsAreNotNull() throws NullValueException;
}
