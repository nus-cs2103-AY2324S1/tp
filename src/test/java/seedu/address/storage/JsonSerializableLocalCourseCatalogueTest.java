package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalObjects.getTypicalLocalCourseCatalogue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.LocalCourseCatalogue;

public class JsonSerializableLocalCourseCatalogueTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableLocalCourseCatalogueTest");
    private static final Path TYPICAL_LOCAL_COURSES_FILE =
            TEST_DATA_FOLDER.resolve("typicalLocalCourseCatalogue.json");
    private static final Path INVALID_LOCAL_COURSES_FILE = TEST_DATA_FOLDER.resolve(
            "invalidLocalCourseInCatalogue.json");
    private static final Path DUPLICATE_LOCAL_COURSES_FILE = TEST_DATA_FOLDER.resolve(
            "duplicateLocalCourseInCatalogue.json");

    @Test
    public void toModelType_typicalLocalCoursesFile_success() throws Exception {
        JsonSerializableLocalCourseCatalogue dataFromFile = JsonUtil.readJsonFile(TYPICAL_LOCAL_COURSES_FILE,
                JsonSerializableLocalCourseCatalogue.class).get();
        LocalCourseCatalogue localCourseCatalogueFromFile = dataFromFile.toModelType();
        LocalCourseCatalogue typicalLocalCourseCatalogue = getTypicalLocalCourseCatalogue();
        assertEquals(localCourseCatalogueFromFile, typicalLocalCourseCatalogue);
    }

    @Test
    public void toModelType_invalidLocalCoursesCatalogueFile_throwsIllegalValueException() throws Exception {
        JsonSerializableLocalCourseCatalogue dataFromFile = JsonUtil.readJsonFile(INVALID_LOCAL_COURSES_FILE,
                JsonSerializableLocalCourseCatalogue.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateLocalCourses_throwsIllegalValueException() throws Exception {
        JsonSerializableLocalCourseCatalogue dataFromFile = JsonUtil.readJsonFile(DUPLICATE_LOCAL_COURSES_FILE,
                JsonSerializableLocalCourseCatalogue.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableLocalCourseCatalogue.MESSAGE_DUPLICATE_LOCAL_COURSE,
                dataFromFile::toModelType);
    }
}
