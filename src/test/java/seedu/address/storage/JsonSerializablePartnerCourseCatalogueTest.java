package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalObjects.getTypicalPartnerCourseCatalogue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.PartnerCourseCatalogue;

public class JsonSerializablePartnerCourseCatalogueTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializablePartnerCourseCatalogueTest");
    private static final Path TYPICAL_PARTNER_COURSES_FILE =
            TEST_DATA_FOLDER.resolve("typicalPartnerCourseCatalogue.json");
    private static final Path INVALID_PARTNER_COURSES_FILE = TEST_DATA_FOLDER.resolve(
            "invalidPartnerCourseInCatalogue.json");
    private static final Path DUPLICATE_PARTNER_COURSES_FILE = TEST_DATA_FOLDER.resolve(
            "duplicatePartnerCourseInCatalogue.json");

    @Test
    public void toModelType_typicalPartnerCoursesFile_success() throws Exception {
        JsonSerializablePartnerCourseCatalogue dataFromFile = JsonUtil.readJsonFile(TYPICAL_PARTNER_COURSES_FILE,
                JsonSerializablePartnerCourseCatalogue.class).get();
        PartnerCourseCatalogue partnerCourseCatalogueFromFile = dataFromFile.toModelType();
        PartnerCourseCatalogue typicalPartnerCourseCatalogue = getTypicalPartnerCourseCatalogue();
        assertEquals(partnerCourseCatalogueFromFile, typicalPartnerCourseCatalogue);
    }

    @Test
    public void toModelType_invalidPartnerCoursesCatalogueFile_throwsIllegalValueException() throws Exception {
        JsonSerializablePartnerCourseCatalogue dataFromFile = JsonUtil.readJsonFile(INVALID_PARTNER_COURSES_FILE,
                JsonSerializablePartnerCourseCatalogue.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePartnerCourses_throwsIllegalValueException() throws Exception {
        JsonSerializablePartnerCourseCatalogue dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PARTNER_COURSES_FILE,
                JsonSerializablePartnerCourseCatalogue.class).get();
        assertThrows(IllegalValueException.class,
                JsonSerializablePartnerCourseCatalogue.MESSAGE_DUPLICATE_PARTNER_COURSE,
                dataFromFile::toModelType);
    }
}
