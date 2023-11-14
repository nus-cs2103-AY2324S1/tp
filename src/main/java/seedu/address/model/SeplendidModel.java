package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.model.localcourse.LocalCourseAttribute;
import seedu.address.model.localcourse.LocalCourseContainsKeywordsPredicate;
import seedu.address.model.mapping.Mapping;
import seedu.address.model.note.Note;
import seedu.address.model.note.NoteTagContainsKeywordsPredicate;
import seedu.address.model.partnercourse.PartnerCode;
import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.model.partnercourse.PartnerCourseAttribute;
import seedu.address.model.partnercourse.PartnerCourseContainsKeywordsPredicate;
import seedu.address.model.university.University;
import seedu.address.model.university.UniversityName;
import seedu.address.model.university.UniversityNameContainsKeywordsPredicate;

/**
 * The API of the SeplendidModel component.
 * Add appropriate methods for respective sub-models.
 */
public interface SeplendidModel {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<LocalCourse> PREDICATE_SHOW_ALL_LOCAL_COURSES = unused -> true;
    Predicate<PartnerCourse> PREDICATE_SHOW_ALL_PARTNER_COURSES = unused -> true;
    Predicate<University> PREDICATE_SHOW_ALL_UNIVERSITIES = unused -> true;
    Predicate<Note> PREDICATE_SHOW_ALL_NOTES = unused -> true;
    Predicate<Mapping> PREDICATE_SHOW_ALL_MAPPINGS = unused -> true;

    //=========== UserPrefs ==================================================================================

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
     * Returns the user prefs' LocalCourseCatalogue file path.
     */
    Path getLocalCourseCatalogueFilePath();

    Path getUniversityCatalogueFilePath();

    /**
     * Sets the user prefs' LocalCourseCatalogue file path.
     */
    void setLocalCourseCatalogueFilePath(Path localCourseCatalogueFilePath);

    //=========== LocalCourseCatalogue ================================================================================

    /**
     * Replaces localcourse list data with the data in {@code localCourseCatalogue}.
     */
    void setLocalCourseCatalogue(ReadOnlyLocalCourseCatalogue localCourseCatalogue);

    /**
     * Returns the LocalCourse list.
     */
    ReadOnlyLocalCourseCatalogue getLocalCourseCatalogue();

    void searchLocalCourses(LocalCourseAttribute attribute,
                            LocalCourseContainsKeywordsPredicate predicate);

    /**
     * Returns true if a local course with the same identity as {@code localCourse} exists in the LocalCourseCatalogue.
     */
    boolean hasLocalCourse(LocalCourse localCourse);

    /**
     * Returns true if a local course with {@code localCode} exists in the LocalCourseCatalogue.
     */
    boolean hasLocalCourse(LocalCode localCode);

    /**
     * Returns a LocalCourse in an Optional if exists, else return empty Optional.
     */
    Optional<LocalCourse> getLocalCourseIfExists(LocalCode localCode);

    /**
     * Deletes the given local course.
     * The local course must exist in the LocalCourseCatalogue.
     */
    void deleteLocalCourse(LocalCourse localCourse) throws CommandException;

    /**
     * Adds the given LocalCourse.
     * {@code localCourse} must not already exist in the LocalCourseCatalogue.
     */
    void addLocalCourse(LocalCourse localCourse);

    /**
     * Replaces the given localCourse {@code target} with {@code editedLocalCourse}.
     * {@code target} must exist in SEPlendid.
     * The localCourse of {@code editedLocalCourse} must not be the same as another
     * existing localCourse in the LocalCourseCatalogue.
     */
    void setLocalCourse(LocalCourse localCourse, LocalCourse editedLocalCourse) throws CommandException;

    //=========== Filtered/SortedLocalCourseList Accessors =============================================================

    ObservableList<LocalCourse> getSortedLocalCourseList();

    void updateSortedLocalList(Comparator<LocalCourse> localCourseComparator);

    /**
     * Returns an unmodifiable view of the filtered local course list
     */
    ObservableList<LocalCourse> getFilteredLocalCourseList();


    /**
     * Updates the filter of the filtered local course list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredLocalCourseList(Predicate<LocalCourse> predicate);
    //=========== PartnerCourseCatalogue ============================================================================

    void searchPartnerCourses(PartnerCourseAttribute attribute,
                              PartnerCourseContainsKeywordsPredicate predicate);


    /**
     * Replaces partnercourse list data with the data in {@code partnerCourseCatalogue}.
     */
    void setPartnerCourseCatalogue(ReadOnlyPartnerCourseCatalogue partnerCourseCatalogue);

    ReadOnlyPartnerCourseCatalogue getPartnerCourseCatalogue();

    /**
     * Returns true if a partner course with the same identity as {@code partnerCourse} exists in the
     * PartnerCourseCatalogue.
     */
    boolean hasPartnerCourse(PartnerCourse partnerCourse);

    /**
     * Returns true if a partner course with {@code partnerCode} exists in the PartnerCourseCatalogue.
     */
    boolean hasPartnerCourse(PartnerCode partnerCode, UniversityName universityName);

    /**
     * Returns a LocalCourse in an Optional if exists, else return empty Optional.
     */
    Optional<PartnerCourse> getPartnerCourseIfExists(PartnerCode partnerCode, UniversityName universityName);

    /**
     * Adds the given PartnerCourse.
     * {@code partnerCourse} must not already exist in the PartnerCourseCatalogue.
     */
    void addPartnerCourse(PartnerCourse partnerCourse);

    void setPartnerCourse(PartnerCourse target, PartnerCourse editedPartnerCourse) throws CommandException;

    /**
     * Deletes the given PartnerCourse.
     *
     * @param partnerCourse must exist in the PartnerCourseCatalogue.
     */
    void deletePartnerCourse(PartnerCourse partnerCourse) throws CommandException;

    public ObservableList<PartnerCourse> getSortedPartnerCourseList();

    public void updateSortedPartnerList(Comparator<PartnerCourse> partnerCourseComparator);

    /**
     * Returns an unmodifiable view of the filtered partner course list
     */
    ObservableList<PartnerCourse> getFilteredPartnerCourseList();

    void updateFilteredPartnerCourseList(Predicate<PartnerCourse> predicate);

    Path getPartnerCourseCatalogueFilePath();

    void setPartnerCourseCatalogueFilePath(Path partnerCourseCatalogueFilePath);

    //=========== UniversityCatalogue ================================================================================

    void setUniversityCatalogueFilePath(Path universityCatalogueFilePath);

    ObservableList<University> getFilteredUniversityList();

    boolean hasUniversity(University university);

    boolean hasUniversity(UniversityName universityName);

    void getSearchUniversityIfExists(UniversityNameContainsKeywordsPredicate universityPredicate);


    void addUniversity(University university);

    void setUniversity(University target, University editedUniversity);

    ObservableList<University> getSortedUniversityList();

    void updateSortedUniversityList(Comparator<University> universityComparator);

    void updateFilteredUniversityList(Predicate<University> predicate);

    void setUniversityCatalogue(ReadOnlyUniversityCatalogue universityCatalogue);

    ReadOnlyUniversityCatalogue getUniversityCatalogue();

    Optional<University> getUniversityIfExists(UniversityName universityName);


    //=========== NoteCatalouge ================================================================================

    /**
     * Replaces note list data with the data in {@code noteCatalogue}.
     */
    void setNoteCatalogue(ReadOnlyNoteCatalogue noteCatalogue);

    /**
     * Returns the Note list.
     */
    ReadOnlyNoteCatalogue getNoteCatalogue();

    /**
     * Returns true if a local course with the same identity as {@code note} exists in the NoteCatalogue.
     */
    boolean hasNote(Note note);

    /**
     * Deletes the given Note.
     * The note must exist in the NoteCatalogue.
     *
     * @return
     */
    Note deleteNote(int noteIndex);

    /**
     * Adds the given Note.
     * {@code note} must not already exist in the NoteCatalogue.
     */
    void addNote(Note note);

    /**
     * Replaces the given note {@code target} with {@code editedNote}.
     * {@code target} must exist in the note catalogue.
     * The note identity of {@code editedNote} must not be the same as another
     * existing note in the NoteCatalogue.
     */
    void setNote(Note note, Note editedNote);

    /**
     * Gets the notes with the specific tags
     *
     * @param notePredicate
     */
    void getSearchNoteIfExists(NoteTagContainsKeywordsPredicate notePredicate);


    //=========== FilteredLocalCourseList Accessors =============================================================

    /**
     * Returns an unmodifiable view of the filtered note list
     */
    ObservableList<Note> getFilteredNoteList();

    /**
     * Updates the filter of the filtered note list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredNoteList(Predicate<Note> predicate);

    //=========== MappingCatalogue =============================================================

    /**
     * Returns the user prefs' MappingCatalogue file path.
     */
    Path getMappingCatalogueFilePath();

    /**
     * Replaces mapping list data with the data in {@code mappingCatalogue}.
     */
    void setMappingCatalogue(ReadOnlyMappingCatalogue mappingCatalogue);

    /**
     * Returns the Mapping list.
     */
    ReadOnlyMappingCatalogue getMappingCatalogue();


    /**
     * Returns true if a mapping with the same identity as {@code mapping} exists in the mappingCatalogue.
     */
    boolean hasMapping(Mapping mapping);

    /**
     * Returns true if a mapping with {@code localCode, universityName, partnerCode} exists in the MappingCatalogue.
     */
    boolean hasMapping(LocalCode localCode, UniversityName universityName, PartnerCode partnerCode);

    /**
     * Returns a Mapping in an Optional if exists, else return empty Optional.
     */
    Optional<Mapping> getMappingIfExists(LocalCode localCode, UniversityName universityName, PartnerCode partnerCode);

    /**
     * Returns true is a mapping with {@code localCode} exists in the MappingCatalogue.
     */
    boolean hasMappingWithLocalCode(LocalCode localCode);

    /**
     * Returns true is a mapping with {@code partnerCode} exists in the MappingCatalogue.
     */
    boolean hasMappingWithPartnerCodeAndUniversityName(PartnerCode partnerCode, UniversityName universityName);

    /**
     * Deletes the given mapping.
     * The mapping must exist in the MappingCatalogue.
     */
    void deleteMapping(Mapping mapping);

    /**
     * Adds the given Mapping.
     * The {@code mapping} must not already exist in the MappingCatalogue.
     */
    void addMapping(Mapping mapping);

    /**
     * Returns the sorted mapping list.
     */
    ObservableList<Mapping> getSortedMappingList();

    /**
     * Sorts the mapping list according to the given comparator.
     *
     * @param mappingComparator This is the given comparator.
     */
    void updateSortedMappingList(Comparator<Mapping> mappingComparator);

    //=========== FilteredMappingList Accessors =============================================================

    /**
     * Returns an unmodifiable view of the filtered mapping list
     */
    ObservableList<Mapping> getFilteredMappingList();

    /**
     * Updates the filter of the filtered mapping list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMappingList(Predicate<Mapping> predicate);
}
