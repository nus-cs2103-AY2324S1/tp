package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.testutil.SerializableClass;

public class JsonUtilTest {
    private static final String TEST_CLASS_JSON = String.format(
        "{"
                + "%n  \"name\" : \"This is a dummy class.\","
                + "%n  \"localDateTimeList\" :"
                + " [ \"-999999999-01-01T00:00:00\", \"+999999999-12-31T23:59:59.999999999\","
                + " \"0001-01-01T01:01:00\" ],"
                + "%n  \"integerToStringMap\" : {"
                + "%n    \"1\" : \"One\","
                + "%n    \"2\" : \"Two\","
                + "%n    \"3\" : \"Three\""
                + "%n  }"
                + "%n}"
    );

    @TempDir
    public static Path tempDir;

    private Path getSerializationPath() {
        return JsonUtilTest.tempDir.resolve("serialization.json");
    }

    @Test
    public void serializeToFile_successfullySaved() throws IOException {
        SerializableClass serializable = new SerializableClass();
        JsonUtil.serializeToFile(this.getSerializationPath(), serializable);

        assertEquals(
            JsonUtilTest.TEST_CLASS_JSON,
            FileUtil.readFromFile(this.getSerializationPath())
        );
    }

    @Test
    public void deserializeFromFile_successfullyRead() throws IOException {
        FileUtil.writeToFile(this.getSerializationPath(), JsonUtilTest.TEST_CLASS_JSON);

        SerializableClass expected = new SerializableClass();
        SerializableClass actual = JsonUtil.deserializeFromFile(
            this.getSerializationPath(),
            SerializableClass.class
        );

        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getList(), actual.getList());
        assertEquals(expected.integerToStringMap, actual.integerToStringMap);
    }
}
