package seedu.staffsnap.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.staffsnap.commons.core.LogsCenter;
import seedu.staffsnap.commons.exceptions.DataLoadingException;
import seedu.staffsnap.commons.exceptions.IllegalValueException;
import seedu.staffsnap.commons.util.FileUtil;
import seedu.staffsnap.commons.util.JsonUtil;
import seedu.staffsnap.model.ReadOnlyApplicantBook;

/**
 * A class to access ApplicantBook data stored as a json file on the hard disk.
 */
public class JsonApplicantBookStorage implements ApplicantBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonApplicantBookStorage.class);

    private Path filePath;

    public JsonApplicantBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getApplicantBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyApplicantBook> readApplicantBook() throws DataLoadingException {
        return readApplicantBook(filePath);
    }

    /**
     * Similar to {@link #readApplicantBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyApplicantBook> readApplicantBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableApplicantBook> jsonApplicantBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableApplicantBook.class);
        if (!jsonApplicantBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonApplicantBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveApplicantBook(ReadOnlyApplicantBook applicantBook) throws IOException {
        saveApplicantBook(applicantBook, filePath);
    }

    /**
     * Similar to {@link #saveApplicantBook(ReadOnlyApplicantBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveApplicantBook(ReadOnlyApplicantBook applicantBook, Path filePath) throws IOException {
        requireNonNull(applicantBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableApplicantBook(applicantBook), filePath);
    }

}
