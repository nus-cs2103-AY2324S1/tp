package seedu.address.logic.commands.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.NoteCatalogue;
import seedu.address.model.ReadOnlyLocalCourseCatalogue;
import seedu.address.model.ReadOnlyMappingCatalogue;
import seedu.address.model.ReadOnlyNoteCatalogue;
import seedu.address.model.ReadOnlyPartnerCourseCatalogue;
import seedu.address.model.ReadOnlyUniversityCatalogue;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.SeplendidModel;
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

public class NoteListCommandTest {

    @Test
    public void execute_listIsNotFiltered_showsAllNotes() throws CommandException {
        ModelStubWithNote modelStub = new ModelStubWithNote();
        NoteListCommand noteListCommand = new NoteListCommand();

        assertEquals("Listed all notes", noteListCommand.execute(modelStub).getFeedbackToUser());
    }

    @Test
    public void equals() {
        NoteListCommand listCommand = new NoteListCommand();
        NoteListCommand otherListCommand = new NoteListCommand();

        // same object -> returns true
        assertTrue(listCommand.equals(listCommand));

        // same values -> returns true
        assertTrue(listCommand.equals(otherListCommand));

        // different types -> returns false
        assertFalse(listCommand.equals(1));

        // null -> returns false
        assertFalse(listCommand.equals(null));
    }

    /**
     * A default model stub that have all methods failing.
     */
    private abstract class SeplendidModelStub implements SeplendidModel {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        // LocalCourse
        @Override
        public void searchLocalCourses(LocalCourseAttribute attribute,
                                       LocalCourseContainsKeywordsPredicate predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getLocalCourseCatalogueFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLocalCourseCatalogueFilePath(Path localCourseCatalogueFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addLocalCourse(LocalCourse localCourse) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLocalCourseCatalogue(ReadOnlyLocalCourseCatalogue localCourseCatalogue) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyLocalCourseCatalogue getLocalCourseCatalogue() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasLocalCourse(LocalCourse localCourse) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasLocalCourse(LocalCode localCode) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<LocalCourse> getLocalCourseIfExists(LocalCode localCode) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteLocalCourse(LocalCourse localCourse) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLocalCourse(LocalCourse localCourse, LocalCourse editedLocalCourse) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<LocalCourse> getSortedLocalCourseList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedLocalList(Comparator<LocalCourse> localCourseComparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<LocalCourse> getFilteredLocalCourseList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredLocalCourseList(Predicate<LocalCourse> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        // PartnerCourse
        @Override
        public void searchPartnerCourses(PartnerCourseAttribute attribute,
                                         PartnerCourseContainsKeywordsPredicate predicate) {
        }

        @Override
        public Path getPartnerCourseCatalogueFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPartnerCourseCatalogueFilePath(Path partnerCourseCatalogueFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPartnerCourse(PartnerCourse partnerCourse) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPartnerCourse(PartnerCourse target, PartnerCourse editedPartnerCourse) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePartnerCourse(PartnerCourse partnerCourse) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<PartnerCourse> getSortedPartnerCourseList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedPartnerList(Comparator<PartnerCourse> partnerCourseComparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyPartnerCourseCatalogue getPartnerCourseCatalogue() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPartnerCourse(PartnerCourse partnerCourse) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPartnerCourse(PartnerCode partnerCode, UniversityName universityName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<PartnerCourse> getPartnerCourseIfExists(
                PartnerCode partnerCode, UniversityName universityName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPartnerCourseCatalogue(ReadOnlyPartnerCourseCatalogue partnerCourseCatalogue) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<PartnerCourse> getFilteredPartnerCourseList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPartnerCourseList(Predicate<PartnerCourse> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        // University
        @Override
        public Path getUniversityCatalogueFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUniversityCatalogue getUniversityCatalogue() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<University> getUniversityIfExists(UniversityName universityName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setNoteCatalogue(ReadOnlyNoteCatalogue noteCatalogue) {
            throw new AssertionError("This method should not be called.");
        }

        public abstract void updateFilteredNoteList(NoteTagContainsKeywordsPredicate predicate);

        @Override
        public ReadOnlyNoteCatalogue getNoteCatalogue() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasNote(Note note) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Note deleteNote(int noteIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUniversityCatalogueFilePath(Path universityCatalogueFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addUniversity(University university) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUniversity(University target, University editedUniversity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<University> getSortedUniversityList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedUniversityList(Comparator<University> universityComparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void getSearchUniversityIfExists(
                UniversityNameContainsKeywordsPredicate universityNameContainsKeywordsPredicate
        ) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<University> getFilteredUniversityList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasUniversity(University university) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public boolean hasUniversity(UniversityName universityName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredUniversityList(Predicate<University> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUniversityCatalogue(ReadOnlyUniversityCatalogue universityCatalogue) {
            throw new AssertionError("This method should not be called.");
        }

        // Note
        @Override
        public void addNote(Note note) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setNote(Note note, Note editedNote) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void getSearchNoteIfExists(NoteTagContainsKeywordsPredicate notePredicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Note> getFilteredNoteList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getMappingCatalogueFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMappingCatalogue(ReadOnlyMappingCatalogue mappingCatalogue) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyMappingCatalogue getMappingCatalogue() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasMapping(Mapping mapping) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasMapping(LocalCode localCode, UniversityName universityName, PartnerCode partnerCode) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Mapping> getMappingIfExists(LocalCode localCode, UniversityName universityName,
                                                    PartnerCode partnerCode) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasMappingWithLocalCode(LocalCode localCode) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasMappingWithPartnerCodeAndUniversityName(PartnerCode partnerCode,
                                                                  UniversityName universityName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteMapping(Mapping mapping) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMapping(Mapping mapping) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Mapping> getFilteredMappingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredMappingList(Predicate<Mapping> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Mapping> getSortedMappingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedMappingList(Comparator<Mapping> mappingComparator) {
            throw new AssertionError("This method should not be called.");
        }
    }

    private class ModelStubWithNote extends SeplendidModelStub {
        private final NoteCatalogue noteCatalogue = new NoteCatalogue();

        @Override
        public void updateFilteredNoteList(Predicate<Note> predicate) {
            // Do nothing in this stub
        }

        @Override
        public void updateFilteredNoteList(NoteTagContainsKeywordsPredicate predicate) {
            // Do nothing in this stub
        }

        @Override
        public NoteCatalogue getNoteCatalogue() {
            return noteCatalogue;
        }
    }
}
