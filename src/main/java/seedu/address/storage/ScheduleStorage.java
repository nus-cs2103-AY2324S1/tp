package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlySchedule;

/**
 * Represents a storage for {@link seedu.address.model.ScheduleList}.
 */
public interface ScheduleStorage {

    /**
     * Returns the file path of the data file of lessons.
     */
    Path getScheduleListFilePath();

    /**
     * Returns Schedule data as a {@link ReadOnlySchedule}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlySchedule> readScheduleList() throws DataLoadingException;

    /**
     * @see #getScheduleListFilePath()
     */
    Optional<ReadOnlySchedule> readScheduleList(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlySchedule} to the storage.
     * @param scheduleList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveScheduleList(ReadOnlySchedule scheduleList) throws IOException;

    /**
     * @see #saveScheduleList(ReadOnlySchedule)
     */
    void saveScheduleList(ReadOnlySchedule scheduleList, Path filePath) throws IOException;

}
