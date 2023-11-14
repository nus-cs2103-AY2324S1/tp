package seedu.address.testutil;

import java.io.IOException;

import seedu.address.commons.util.JsonUtil;

/**
 * A mock version of {@link JsonUtil} used for testing.
 */
public class MockJsonUtil extends JsonUtil {

    /**
     * Overrides the {@code fromJsonString} method of {@link JsonUtil} to always
     * return null.
     * This behavior can be useful to simulate certain test conditions where
     * deserialization fails to produce an object.
     *
     * @param <T>        The type of the object to be deserialized.
     * @param jsonString The JSON string to deserialize.
     * @param classOfT   The class of type T.
     * @return Always returns null irrespective of input parameters.
     * @throws IOException If an I/O error occurs.
     */
    public static <T> T fromJsonString(String jsonString, Class<T> classOfT) throws IOException {
        return null;
    }
}
