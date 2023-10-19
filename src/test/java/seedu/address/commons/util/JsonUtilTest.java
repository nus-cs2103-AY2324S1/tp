package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TestData.JSON_STRING_REPRESENTATION;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.testutil.SerializableTestClass;



/**
 * Tests JSON Read and Write
 */
public class JsonUtilTest {
    @TempDir
    public static Path TEMP_DIR;

    private Path getSerializationPath() {
        return JsonUtilTest.TEMP_DIR.resolve("serialization.json");
    }

    @Test
    public void serializeObjectToJsonFile_noExceptionThrown() throws IOException {
        SerializableTestClass serializableTestClass = new SerializableTestClass();
        serializableTestClass.setTestValues();

        JsonUtil.serializeObjectToJsonFile(this.getSerializationPath(), serializableTestClass);

        assertEquals(FileUtil.readFromFile(this.getSerializationPath()), JSON_STRING_REPRESENTATION);
    }

    @Test
    public void deserializeObjectFromJsonFile_noExceptionThrown() throws IOException {
        FileUtil.writeToFile(this.getSerializationPath(), JSON_STRING_REPRESENTATION);

        SerializableTestClass serializableTestClass = JsonUtil
                .deserializeObjectFromJsonFile(this.getSerializationPath(), SerializableTestClass.class);

        assertEquals(serializableTestClass.getName(), SerializableTestClass.getNameTestValue());
        assertEquals(serializableTestClass.getListOfLocalDateTimes(), SerializableTestClass.getListTestValues());
        assertEquals(serializableTestClass.getMapOfIntegerToString(), SerializableTestClass.getHashMapTestValues());
    }
}
