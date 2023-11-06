package networkbook.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import networkbook.commons.exceptions.NullValueException;
import networkbook.testutil.SerializableTestClass;
import networkbook.testutil.TestUtil;

/**
 * Tests JSON Read and Write
 */
public class JsonUtilTest {

    private static final Path SERIALIZATION_FILE = TestUtil.getFilePathInSandboxFolder("serialize.json");
    private static final Path EMPTY_FILE = TestUtil.getFilePathInSandboxFolder("empty.json");

    @Test
    public void serializeObjectToJsonFile_noExceptionThrown() throws IOException {
        SerializableTestClass serializableTestClass = new SerializableTestClass();
        serializableTestClass.setTestValues();

        JsonUtil.serializeObjectToJsonFile(SERIALIZATION_FILE, serializableTestClass);

        assertEquals(FileUtil.readFromFile(SERIALIZATION_FILE), SerializableTestClass.JSON_STRING_REPRESENTATION);
    }

    @Test
    public void readJsonFile_fileWithValidContent_noExceptionThrown() throws Exception {
        FileUtil.writeToFile(SERIALIZATION_FILE, SerializableTestClass.JSON_STRING_REPRESENTATION);

        SerializableTestClass serializableTestClass = JsonUtil
                .readJsonFile(SERIALIZATION_FILE, SerializableTestClass.class).get();

        assertEquals(serializableTestClass.getName(), SerializableTestClass.getNameTestValue());
        assertEquals(serializableTestClass.getListOfLocalDateTimes(), SerializableTestClass.getListTestValues());
        assertEquals(serializableTestClass.getMapOfIntegerToString(), SerializableTestClass.getHashMapTestValues());
    }

    @Test
    public void readJsonFile_failToAssertNull_throwsNullValueException() throws Exception {
        FileUtil.writeToFile(EMPTY_FILE, "{}");
        JsonObject jsonObject = () -> {
            throw new NullValueException();
        };
        assertThrows(NullValueException.class, () -> JsonUtil.readJsonFile(EMPTY_FILE, jsonObject.getClass()).get());
    }
}
