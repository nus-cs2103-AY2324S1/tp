package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.PrescriptionList;
import seedu.address.testutil.TypicalPrescriptions;

public class JsonSerializablePrescriptionListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
        "JsonSerializablePrescriptionListTest");
    private static final Path TYPICAL_PRESCRIPTION_FILE = TEST_DATA_FOLDER.resolve("typicalPrescriptionList.json");
    private static final Path INVALID_PRESCRIPTION_FILE = TEST_DATA_FOLDER.resolve("invalidPrescriptionList.json");
    private static final Path DUPLICATE_PRESCRIPTION_FILE = TEST_DATA_FOLDER.resolve("duplicatePrescriptionList.json");

    @Test
    public void toModelType_typicalPrescriptionFile_success() throws Exception {
        JsonSerializablePrescriptionList dataFromFile = JsonUtil.readJsonFile(TYPICAL_PRESCRIPTION_FILE,
            JsonSerializablePrescriptionList.class).get();
        PrescriptionList prescriptionListFromFile = dataFromFile.toModelType();
        PrescriptionList typicalPrescriptionList = TypicalPrescriptions.getTypicalPrescriptionList();
        assertEquals(prescriptionListFromFile, typicalPrescriptionList);
    }

    @Test
    public void toModelType_invalidPrescriptionFile_throwsIllegalValueException() throws Exception {
        JsonSerializablePrescriptionList dataFromFile = JsonUtil.readJsonFile(INVALID_PRESCRIPTION_FILE,
            JsonSerializablePrescriptionList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePrescription_throwsIllegalValueException() throws Exception {
        JsonSerializablePrescriptionList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PRESCRIPTION_FILE,
            JsonSerializablePrescriptionList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializablePrescriptionList.MESSAGE_DUPLICATE_PRESCRIPTION,
            dataFromFile::toModelType);
    }

}
