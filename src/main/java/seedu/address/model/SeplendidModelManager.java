package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.SeplendidLogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.localcourse.LocalCourseDeleteCommand;
import seedu.address.logic.commands.localcourse.LocalCourseUpdateCommand;
import seedu.address.logic.commands.partnercourse.PartnerCourseDeleteCommand;
import seedu.address.logic.commands.partnercourse.PartnerCourseUpdateCommand;
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
 * Represents the in-memory model of SEPlendid.
 */
public class SeplendidModelManager implements SeplendidModel {
    private static final Logger logger = SeplendidLogsCenter.getLogger(SeplendidModelManager.class);

    // Add all data models here
    private final LocalCourseCatalogue localCourseCatalogue;
    private final UserPrefs userPrefs;
    private final FilteredList<LocalCourse> filteredLocalCourseCatalogue;
    private final SortedList<LocalCourse> sortedLocalCourseCatalogue;

    private final UniversityCatalogue universityCatalogue;
    private final FilteredList<University> filteredUniversityCatalogue;
    private final SortedList<University> sortedUniversityCatalogue;

    private final PartnerCourseCatalogue partnerCourseCatalogue;
    private final FilteredList<PartnerCourse> filteredPartnerCourseCatalogue;
    private final SortedList<PartnerCourse> sortedPartnerCourseCatalogue;

    private final NoteCatalogue noteCatalogue;
    private final FilteredList<Note> filteredNoteCatalogue;

    private final MappingCatalogue mappingCatalogue;
    private final FilteredList<Mapping> filteredMappingCatalogue;
    private final SortedList<Mapping> sortedMappingCatalogue;


    /**
     * Initializes a SeplendidModelManager with the given localCourseCatalogue, userPrefs,
     * partnerCourseCatalogue, universityCatalogue, mappingCatalogue, noteCatalogue.
     */
    public SeplendidModelManager(ReadOnlyUserPrefs userPrefs,
                                 ReadOnlyLocalCourseCatalogue localCourseCatalogue,
                                 ReadOnlyPartnerCourseCatalogue partnerCourseCatalogue,
                                 ReadOnlyUniversityCatalogue universityCatalogue,
                                 ReadOnlyMappingCatalogue mappingCatalogue,
                                 ReadOnlyNoteCatalogue noteCatalogue) {
        requireAllNonNull(localCourseCatalogue, userPrefs, partnerCourseCatalogue, universityCatalogue,
                noteCatalogue, mappingCatalogue);

        logger.fine(String.format("Initializing with user prefs: %s,\n"
                        + "local course catalogue: %s, \n"
                        + "partner course catalogue: %s,\n"
                        + "university catalogue: %s,\n"
                        + "mapping catalogue: %s,\n"
                        + "and note catalogue: %s",
                userPrefs,
                localCourseCatalogue,
                partnerCourseCatalogue,
                universityCatalogue,
                mappingCatalogue,
                noteCatalogue));

        this.localCourseCatalogue = new LocalCourseCatalogue(localCourseCatalogue);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredLocalCourseCatalogue = new FilteredList<>(this.localCourseCatalogue.getLocalCourseList());
        sortedLocalCourseCatalogue = new SortedList<>(this.localCourseCatalogue.getLocalCourseList());
        this.partnerCourseCatalogue = new PartnerCourseCatalogue(partnerCourseCatalogue);
        filteredPartnerCourseCatalogue = new FilteredList<>(this.partnerCourseCatalogue.getPartnerCourseList());
        sortedPartnerCourseCatalogue = new SortedList<>(this.partnerCourseCatalogue.getPartnerCourseList());
        this.universityCatalogue = new UniversityCatalogue(universityCatalogue);
        filteredUniversityCatalogue = new FilteredList<>(this.universityCatalogue.getUniversityList());
        sortedUniversityCatalogue = new SortedList<>(this.universityCatalogue.getUniversityList());
        this.noteCatalogue = new NoteCatalogue(noteCatalogue);
        filteredNoteCatalogue = new FilteredList<>(this.noteCatalogue.getNoteList());
        this.mappingCatalogue = new MappingCatalogue(mappingCatalogue);
        filteredMappingCatalogue = new FilteredList<>(this.mappingCatalogue.getMappingList());
        sortedMappingCatalogue = new SortedList<>(this.mappingCatalogue.getMappingList());
    }

    /**
     * Constructs Seplendid Model Manager.
     */
    public SeplendidModelManager() {
        this(
                new UserPrefs(),
                new LocalCourseCatalogue(),
                new PartnerCourseCatalogue(),
                new UniversityCatalogue(),
                new MappingCatalogue(),
                new NoteCatalogue());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    //=========== LocalCourseCatalogue ================================================================================

    @Override
    public Path getLocalCourseCatalogueFilePath() {
        return userPrefs.getLocalCourseCatalogueFilePath();
    }

    @Override
    public Path getUniversityCatalogueFilePath() {
        return userPrefs.getUniversityCatalogueFilePath();
    }

    @Override
    public void setLocalCourseCatalogueFilePath(Path localCourseCatalogueFilePath) {
        requireNonNull(localCourseCatalogueFilePath);
        userPrefs.setLocalCourseCatalogueFilePath(localCourseCatalogueFilePath);
    }

    @Override
    public void setPartnerCourseCatalogueFilePath(Path partnerCourseCatalogueFilePath) {
        requireNonNull(partnerCourseCatalogueFilePath);
        userPrefs.setPartnerCourseCatalogueFilePath(partnerCourseCatalogueFilePath);
    }

    @Override
    public void setUniversityCatalogueFilePath(Path universityCatalogueFilePath) {
        requireNonNull(universityCatalogueFilePath);
        userPrefs.setUniversitiesCatalogueFilePath(universityCatalogueFilePath);
    }

    //=========== LocalCourseCatalogue ================================================================================

    @Override
    public void setLocalCourseCatalogue(ReadOnlyLocalCourseCatalogue localCourseCatalogue) {
        this.localCourseCatalogue.resetData(localCourseCatalogue);
    }

    @Override
    public ReadOnlyLocalCourseCatalogue getLocalCourseCatalogue() {
        return localCourseCatalogue;
    }

    @Override
    public boolean hasLocalCourse(LocalCourse localCourse) {
        requireNonNull(localCourse);
        return localCourseCatalogue.hasLocalCourse(localCourse);
    }

    @Override
    public boolean hasLocalCourse(LocalCode localCode) {
        requireNonNull(localCode);
        Optional<LocalCourse> maybeLocalCourse = getLocalCourseIfExists(localCode);
        return maybeLocalCourse.isPresent();
    }

    @Override
    public Optional<LocalCourse> getLocalCourseIfExists(LocalCode localCode) {
        requireNonNull(localCode);
        return localCourseCatalogue.getLocalCourseIfExists(localCode);
    }

    @Override
    public void deleteLocalCourse(LocalCourse target) throws CommandException {
        // Block delete if it exists in a mapping
        if (mappingCatalogue.hasMappingWithLocalCode(target.getLocalCode())) {
            throw new CommandException(LocalCourseDeleteCommand.MESSAGE_MAPPING_DEPENDENT_ON_LOCAL_COURSE);
        }
        localCourseCatalogue.removeLocalCourse(target);
        updateFilteredLocalCourseList(PREDICATE_SHOW_ALL_LOCAL_COURSES);
    }

    @Override
    public void addLocalCourse(LocalCourse localCourse) {
        localCourseCatalogue.addLocalCourse(localCourse);
        updateFilteredLocalCourseList(PREDICATE_SHOW_ALL_LOCAL_COURSES);
    }

    @Override
    public void setLocalCourse(LocalCourse target, LocalCourse editedLocalCourse) throws CommandException {
        requireAllNonNull(target, editedLocalCourse);

        LocalCourse targetCourseWithEditedCourseCopy = new LocalCourse(
                editedLocalCourse.getLocalCode(),
                target.getLocalName(),
                target.getLocalUnit(),
                target.getLocalDescription()
        );
        // LocalCode is to be modified
        if (!targetCourseWithEditedCourseCopy.equals(target)
                && targetCourseWithEditedCourseCopy.equals(editedLocalCourse)
                && mappingCatalogue.hasMappingWithLocalCode(target.getLocalCode())) {
            throw new CommandException(LocalCourseUpdateCommand.MESSAGE_MAPPING_DEPENDENT_ON_LOCAL_COURSE);
        }

        localCourseCatalogue.setLocalCourse(target, editedLocalCourse);
    }

    @Override
    public ObservableList<LocalCourse> getSortedLocalCourseList() {
        return sortedLocalCourseCatalogue;
    }

    @Override
    public void updateSortedLocalList(Comparator<LocalCourse> localCourseComparator) {
        sortedLocalCourseCatalogue.setComparator(localCourseComparator);
    }

    /**
     * Checks if the local course exists.
     *
     * @param attribute
     * @param predicate
     */
    public void searchLocalCourses(LocalCourseAttribute attribute,
                                   LocalCourseContainsKeywordsPredicate predicate) {
        requireNonNull(attribute);
        filteredLocalCourseCatalogue.setPredicate(predicate);
    }

    //=========== FilteredLocalCourseList Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code LocalCourse} backed by the internal list of
     * {@code versionedLocalCourseCatalogue}
     */
    @Override
    public ObservableList<LocalCourse> getFilteredLocalCourseList() {
        return filteredLocalCourseCatalogue;
    }

    @Override
    public void updateFilteredLocalCourseList(Predicate<LocalCourse> predicate) {
        requireNonNull(predicate);
        filteredLocalCourseCatalogue.setPredicate(predicate);
    }

    //=========== PartnerCourseCatalogue ============================================================================

    /**
     * Checks if the local course exists.
     *
     * @param attribute
     * @param predicate
     */
    public void searchPartnerCourses(PartnerCourseAttribute attribute,
                                     PartnerCourseContainsKeywordsPredicate predicate) {
        requireNonNull(attribute);
        filteredPartnerCourseCatalogue.setPredicate(predicate);
    }

    @Override
    public void setPartnerCourseCatalogue(ReadOnlyPartnerCourseCatalogue partnerCourseCatalogue) {
        this.partnerCourseCatalogue.resetData(partnerCourseCatalogue);
    }

    @Override
    public ReadOnlyPartnerCourseCatalogue getPartnerCourseCatalogue() {
        return partnerCourseCatalogue;
    }

    @Override
    public boolean hasPartnerCourse(PartnerCourse partnerCourse) {
        requireNonNull(partnerCourse);
        return partnerCourseCatalogue.hasPartnerCourse(partnerCourse);
    }

    @Override
    public boolean hasPartnerCourse(PartnerCode partnerCode, UniversityName universityName) {
        requireAllNonNull(partnerCode, universityName);
        Optional<PartnerCourse> maybePartnerCourse = getPartnerCourseIfExists(partnerCode, universityName);
        return maybePartnerCourse.isPresent();
    }

    @Override
    public Optional<PartnerCourse> getPartnerCourseIfExists(PartnerCode partnerCode, UniversityName universityName) {
        requireAllNonNull(partnerCode, universityName);
        return partnerCourseCatalogue.getPartnerCourseIfExists(partnerCode, universityName);
    }

    @Override
    public void addPartnerCourse(PartnerCourse partnerCourse) {
        partnerCourseCatalogue.addPartnerCourse(partnerCourse);
        updateFilteredPartnerCourseList(PREDICATE_SHOW_ALL_PARTNER_COURSES);
    }

    @Override
    public void setPartnerCourse(PartnerCourse target, PartnerCourse editedPartnerCourse) throws CommandException {
        requireAllNonNull(target, editedPartnerCourse);
        PartnerCourse targetCourseWithEditedCourseCopy = new PartnerCourse(
                editedPartnerCourse.getPartnerUniversity(),
                editedPartnerCourse.getPartnerCode(),
                target.getPartnerName(),
                target.getPartnerUnit(),
                target.getPartnerDescription()
        );
        // One of partnercode or university is to be modified
        if (!targetCourseWithEditedCourseCopy.equals(target)
                && targetCourseWithEditedCourseCopy.equals(editedPartnerCourse)
                && mappingCatalogue.hasMappingWithPartnerCodeAndUniversityName(target.getPartnerCode(),
                target.getPartnerUniversity().getUniversityName())) {
            throw new CommandException(PartnerCourseUpdateCommand.MESSAGE_MAPPING_DEPENDENT_ON_PARTNER_COURSE);
        }
        partnerCourseCatalogue.setPartnerCourse(target, editedPartnerCourse);
    }

    @Override
    public void deletePartnerCourse(PartnerCourse partnerCourse) throws CommandException {
        // Block delete if it exists in a mapping
        if (mappingCatalogue.hasMappingWithPartnerCodeAndUniversityName(partnerCourse.getPartnerCode(),
                partnerCourse.getPartnerUniversity().getUniversityName())) {
            throw new CommandException(PartnerCourseDeleteCommand.MESSAGE_MAPPING_DEPENDENT_ON_PARTNER_COURSE);
        }

        partnerCourseCatalogue.removePartnerCourse(partnerCourse);
        updateFilteredPartnerCourseList(PREDICATE_SHOW_ALL_PARTNER_COURSES);
    }

    @Override
    public ObservableList<PartnerCourse> getSortedPartnerCourseList() {

        return sortedPartnerCourseCatalogue;
    }

    @Override
    public void updateSortedPartnerList(Comparator<PartnerCourse> partnerCourseComparator) {
        sortedPartnerCourseCatalogue.setComparator(partnerCourseComparator);
    }

    @Override
    public ObservableList<PartnerCourse> getFilteredPartnerCourseList() {
        return filteredPartnerCourseCatalogue;
    }

    @Override
    public void updateFilteredPartnerCourseList(Predicate<PartnerCourse> predicate) {
        requireNonNull(predicate);
        filteredPartnerCourseCatalogue.setPredicate(predicate);
    }

    @Override
    public Path getPartnerCourseCatalogueFilePath() {
        return userPrefs.getPartnerCourseCatalogueFilePath();
    }


    //=========== UniversityCatalogue ================================================================================

    @Override
    public void setUniversityCatalogue(ReadOnlyUniversityCatalogue universityCatalogue) {
        this.universityCatalogue.resetData(universityCatalogue);
    }

    @Override
    public ReadOnlyUniversityCatalogue getUniversityCatalogue() {
        return universityCatalogue;
    }

    @Override
    public Optional<University> getUniversityIfExists(UniversityName universityName) {
        requireNonNull(universityName);
        return universityCatalogue.getUniversityIfExists(universityName);
    }

    /**
     * Check if there exist the same university in the catalogue.
     *
     * @param university University to be checked.
     * @return Boolean.
     */
    @Override
    public boolean hasUniversity(University university) {
        requireNonNull(university);
        return universityCatalogue.hasUniversity(university);
    }

    /**
     * Check if there exist the same university in the catalogue.
     *
     * @param universityName
     * @return
     */
    public boolean hasUniversity(UniversityName universityName) {
        requireNonNull(universityName);
        Optional<University> maybeUniversity = getUniversityIfExists(universityName);
        return maybeUniversity.isPresent();
    }

    @Override
    public void getSearchUniversityIfExists(UniversityNameContainsKeywordsPredicate universityPredicate) {
        requireNonNull(universityPredicate);
        filteredUniversityCatalogue.setPredicate(universityPredicate);

    }

    @Override
    public void addUniversity(University university) {
        universityCatalogue.addUniversity(university);
        updateFilteredUniversityList(PREDICATE_SHOW_ALL_UNIVERSITIES);
    }

    @Override
    public void setUniversity(University target, University editedUniversity) {
        requireAllNonNull(target, editedUniversity);

        universityCatalogue.setUniversity(target, editedUniversity);
    }

    @Override
    public ObservableList<University> getSortedUniversityList() {

        return sortedUniversityCatalogue;
    }

    @Override
    public void updateSortedUniversityList(Comparator<University> universityComparator) {
        sortedUniversityCatalogue.setComparator(universityComparator);
    }


    //=========== FilteredUniversityList Accessors =============================================================

    @Override
    public void updateFilteredUniversityList(Predicate<University> predicate) {
        requireNonNull(predicate);
        filteredUniversityCatalogue.setPredicate(predicate);
    }

    @Override
    public ObservableList<University> getFilteredUniversityList() {

        return filteredUniversityCatalogue;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SeplendidModelManager)) {
            return false;
        }

        SeplendidModelManager otherSeplendidModelManager = (SeplendidModelManager) other;
        return localCourseCatalogue.equals(otherSeplendidModelManager.localCourseCatalogue)
                && userPrefs.equals(otherSeplendidModelManager.userPrefs)
                && filteredLocalCourseCatalogue.equals(otherSeplendidModelManager.filteredLocalCourseCatalogue)
                && partnerCourseCatalogue.equals(otherSeplendidModelManager.partnerCourseCatalogue)
                && filteredPartnerCourseCatalogue.equals(otherSeplendidModelManager.filteredPartnerCourseCatalogue)
                && noteCatalogue.equals(otherSeplendidModelManager.noteCatalogue)
                && filteredNoteCatalogue.equals(otherSeplendidModelManager.filteredNoteCatalogue);
    }

    //=========== NoteCatalogue ================================================================================

    @Override
    public void setNoteCatalogue(ReadOnlyNoteCatalogue noteCatalogue) {
        this.noteCatalogue.resetData(noteCatalogue);
    }

    @Override
    public ReadOnlyNoteCatalogue getNoteCatalogue() {
        return noteCatalogue;
    }

    @Override
    public boolean hasNote(Note note) {
        requireNonNull(note);
        return noteCatalogue.hasNote(note);
    }

    @Override
    public Note deleteNote(int target) {
        return noteCatalogue.removeNote(target);
    }

    @Override
    public void addNote(Note note) {
        noteCatalogue.addNote(note);
        updateFilteredNoteList(PREDICATE_SHOW_ALL_NOTES);
    }

    @Override
    public void setNote(Note target, Note editedNote) {
        requireAllNonNull(target, editedNote);

        noteCatalogue.setNote(target, editedNote);
    }

    @Override
    public void getSearchNoteIfExists(NoteTagContainsKeywordsPredicate notePredicate) {
        requireNonNull(notePredicate);
        filteredNoteCatalogue.setPredicate(notePredicate);

    }

    //=========== FilteredNoteList Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Note} backed by the internal list of
     * {@code versionedNoteCatalogue}
     */
    @Override
    public ObservableList<Note> getFilteredNoteList() {
        return filteredNoteCatalogue;
    }

    @Override
    public void updateFilteredNoteList(Predicate<Note> predicate) {
        requireNonNull(predicate);
        filteredNoteCatalogue.setPredicate(predicate);
    }

    //============ MappingCatalogue ================================================================================
    @Override
    public Path getMappingCatalogueFilePath() {
        return userPrefs.getMappingCatalogueFilePath();
    }

    /**
     * Replaces mapping list data with the data in {@code mappingCatalogue}.
     */
    @Override
    public void setMappingCatalogue(ReadOnlyMappingCatalogue mappingCatalogue) {
        this.mappingCatalogue.resetData(mappingCatalogue);
    }

    /**
     * Returns the Mapping list.
     */
    @Override
    public ReadOnlyMappingCatalogue getMappingCatalogue() {
        return mappingCatalogue;
    }


    /**
     * Returns true if a mapping with the same identity as {@code mapping} exists in the mappingCatalogue.
     */
    @Override
    public boolean hasMapping(Mapping mapping) {
        requireNonNull(mapping);
        return mappingCatalogue.hasMapping(mapping);
    }

    /**
     * Returns true if a mapping with {@code localCode, universityName, partnerCode} exists in the MappingCatalogue.
     */
    @Override
    public boolean hasMapping(LocalCode localCode, UniversityName universityName, PartnerCode partnerCode) {
        requireAllNonNull(localCode, universityName, partnerCode);
        Optional<Mapping> maybeMapping = getMappingIfExists(localCode, universityName, partnerCode);
        return maybeMapping.isPresent();
    }

    /**
     * Returns a Mapping in an Optional if exists, else return empty Optional.
     */
    @Override
    public Optional<Mapping> getMappingIfExists(LocalCode localCode, UniversityName universityName,
                                                PartnerCode partnerCode) {
        requireAllNonNull(localCode, universityName, partnerCode);
        return mappingCatalogue.getMappingIfExists(localCode, universityName, partnerCode);
    }

    /**
     * Returns true is a mapping with {@code localCode} exists in the MappingCatalogue.
     */
    @Override
    public boolean hasMappingWithLocalCode(LocalCode localCode) {
        requireNonNull(localCode);
        return mappingCatalogue.hasMappingWithLocalCode(localCode);
    }

    /**
     * Returns true is a mapping with {@code partnerCode}  and {@code universityName}
     * exists in the MappingCatalogue.
     */
    @Override
    public boolean hasMappingWithPartnerCodeAndUniversityName(PartnerCode partnerCode,
                                                              UniversityName universityName) {
        requireAllNonNull(partnerCode, universityName);
        return mappingCatalogue.hasMappingWithPartnerCodeAndUniversityName(partnerCode, universityName);
    }

    /**
     * Deletes the given mapping.
     * The mapping must exist in the MappingCatalogue.
     */
    @Override
    public void deleteMapping(Mapping mapping) {
        mappingCatalogue.removeMapping(mapping);
    }


    /**
     * Adds the given Mapping.
     * The {@code mapping} must not already exist in the MappingCatalogue.
     */
    @Override
    public void addMapping(Mapping mapping) {
        mappingCatalogue.addMapping(mapping);
        updateFilteredMappingList(PREDICATE_SHOW_ALL_MAPPINGS);
    }

    @Override
    public ObservableList<Mapping> getSortedMappingList() {
        return sortedMappingCatalogue;
    }

    @Override
    public void updateSortedMappingList(Comparator<Mapping> mappingComparator) {
        sortedMappingCatalogue.setComparator(mappingComparator);
    }


    //=========== FilteredNMappingList Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Mapping} backed by the internal list of
     * {@code versionedMappingCatalogue}
     */
    @Override
    public ObservableList<Mapping> getFilteredMappingList() {
        return filteredMappingCatalogue;
    }


    @Override
    public void updateFilteredMappingList(Predicate<Mapping> predicate) {
        requireNonNull(predicate);
        filteredMappingCatalogue.setPredicate(predicate);
    }

}
