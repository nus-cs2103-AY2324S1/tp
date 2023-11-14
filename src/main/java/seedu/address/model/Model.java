package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.band.Band;
import seedu.address.model.musician.Musician;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Musician> PREDICATE_SHOW_ALL_MUSICIANS = unused -> true;
    Predicate<Band> PREDICATE_SHOW_ALL_BANDS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a musician with the same identity as {@code musician} exists in the address book.
     */
    boolean hasMusician(Musician musician);

    /**
     * Returns true if a musician with the same phone or email as {@code musician} exists in the address book.
     */
    boolean hasDuplicateInfo(Musician toExclude, Musician musician);

    /**
     * Deletes the given musician.
     * The musician must exist in the address book.
     */
    void deleteMusician(Musician target);

    /**
     * Adds the given musician.
     * {@code musician} must not already exist in the address book.
     */
    void addMusician(Musician musician);

    /**
     * Replaces the given musician {@code target} with {@code editedMusician}.
     * {@code target} must exist in the address book.
     * The musician identity of {@code editedMusician} must not be the same as another existing musician
     * in the address book.
     */
    void setMusician(Musician target, Musician editedMusician);

    /** Returns an unmodifiable view of the filtered musician list */
    ObservableList<Musician> getFilteredMusicianList();

    /**
     * Updates the filter of the filtered musician list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMusicianList(Predicate<Musician> predicate);

    /** Returns an unmodifiable view of the filtered band list */
    ObservableList<Band> getFilteredBandList();

    void updateFilteredBandList(Predicate<Band> predicate);

    /**
     * Updates the filter of the filtered band list to filter by the given {@code predicate}.
     * Updates the musician list to reflect the members of the filtered band.
     * An abstraction for FindBandCommand which requires updating both panels simultaneously.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredBandMusicianList(Predicate<Band> predicate);

    /**
     * Returns true if a band with the same identity as {@code band} exists in the address book.
     */
    boolean hasBand(Band band);

    /**
     * Adds the given band.
     * {@code band} must not already exist in the address book.
     */
    void addBand(Band band);

    /**
     * Deletes the given band.
     * The band must exist in the address book.
     */
    void deleteBand(Band bandToDelete);

    /**
     * Replaces the given band {@code target} with {@code editedBand}.
     * {@code target} must exist in the address book.
     * The band identity of {@code editedBand} must not be the same as another existing band
     * in the address book.
     */
    void setBand(Band target, Band editedBand);

    /**
     * Returns true if a band already contains the musician.
     * {@code musician} must not already exist in the band.
     */
    boolean hasMusicianInBand(int addInto, int toAdd);

    /**
     * Adds the given musician into the band.
     * {@code musician} must not already exist in the band.
     */
    void addMusicianToBand(int addInto, int toAdd);

    void removeMusicianFromBand(int bandIndex, int musicianIndex);
}
