package seedu.address.storage.finance;

import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonSerializableFinancesBookTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableFinancesBookTest");
    private static final Path TYPICAL_FINANCES_FILE = TEST_DATA_FOLDER.resolve("typicalFinancesBook.json");
    private static final Path INVALID_FINANCES_FILE = TEST_DATA_FOLDER.resolve("invalidFinancesBook.json");

}
