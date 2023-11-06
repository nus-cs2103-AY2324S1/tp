package networkbook.commons.util;

import networkbook.commons.exceptions.NullValueException;

/**
 * Represents an object that can be transformed from a JSON.
 * Classes that inherit this interface must implements {@code assertFieldsAreNotNull},
 * to ensure that object fields not supposed to be null are not null.
 */
public interface JsonObject {
    /**
     * Asserts that all fields that are not supposed to be null are not null.
     * To be used in reading json file.
     * @throws NullValueException If there is a null field.
     */
    void assertFieldsAreNotNull() throws NullValueException;
}
