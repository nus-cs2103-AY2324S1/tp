package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyGradedTestList;


/**
 * Represents a storage for {@link seedu.address.model.GradedTestListBook}.
 */
public interface GradedTestListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getGradedTestListFilePath();

    /**
     * Returns GradedTest data as a {@link ReadOnlyGradedTestList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyGradedTestList> readGradedTestList() throws DataLoadingException;

    /**
     * @see #getGradedTestListFilePath()
     */
    Optional<ReadOnlyGradedTestList> readGradedTestList(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyGradedTestList} to the storage.
     * @param gradedTestList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveGradedTestList(ReadOnlyGradedTestList gradedTestList) throws IOException;

    /**
     * @see #saveGradedTestList(ReadOnlyGradedTestList, Path)
     */
    void saveGradedTestList(ReadOnlyGradedTestList gradedTestList, Path filePath) throws IOException;
}
