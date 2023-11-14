package seedu.address.logic.commands.partnercourse;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.messages.Messages;
import seedu.address.model.PartnerCourseCatalogue;
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
import seedu.address.testutil.PartnerCourseBuilder;

/**
 * Unit testing of PartnerCourseAddCommand, with stubs / dependency injection.
 */
public class PartnerCourseAddCommandTest {

    @Test
    public void constructor_nullPartnerCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PartnerCourseAddCommand(null));
    }

    @Test
    public void execute_partnerCourseAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPartnerCourseAdded modelStub = new ModelStubAcceptingPartnerCourseAdded();
        PartnerCourse validPartnerCourse = new PartnerCourseBuilder().build();

        CommandResult commandResult = new PartnerCourseAddCommand(validPartnerCourse).execute(modelStub);

        assertEquals(String.format(PartnerCourseAddCommand.MESSAGE_SUCCESS, Messages.format(validPartnerCourse)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPartnerCourse), modelStub.partnerCoursesAdded);
    }

    @Test
    public void execute_duplicatePartnerCourse_throwsCommandException() {
        PartnerCourse validPartnerCourse = new PartnerCourseBuilder().build();
        PartnerCourseAddCommand partnerCourseAddCommand = new PartnerCourseAddCommand(validPartnerCourse);
        SeplendidModelStub modelStub = new SeplendidModelStubWithPartnerCourse(validPartnerCourse);

        assertThrows(
                CommandException.class,
                PartnerCourseAddCommand.MESSAGE_DUPLICATE_PARTNER_COURSE, (
                ) -> partnerCourseAddCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        PartnerCourse cs1234 = new PartnerCourseBuilder().withPartnerCode("CS1234").build();
        PartnerCourse cs6789 = new PartnerCourseBuilder().withPartnerCode("CS6789").build();
        PartnerCourseAddCommand addCS1234Command = new PartnerCourseAddCommand(cs1234);
        PartnerCourseAddCommand addCS6789Command = new PartnerCourseAddCommand(cs6789);

        // same object -> returns true
        assertTrue(addCS1234Command.equals(addCS1234Command));

        // same values -> returns true
        PartnerCourseAddCommand addCS1234CommandCopy = new PartnerCourseAddCommand(cs1234);
        assertTrue(addCS1234Command.equals(addCS1234CommandCopy));

        // different types -> returns false
        assertFalse(addCS1234Command.equals(1));

        // null -> returns false
        assertFalse(addCS1234Command.equals(null));

        // different local course -> returns false
        assertFalse(addCS1234Command.equals(addCS6789Command));

    }

    /**
     * A default model stub that have all methods failing.
     */
    private class SeplendidModelStub implements SeplendidModel {
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
        public void setPartnerCourseCatalogueFilePath(Path partnerCourseCatalogueFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getPartnerCourseCatalogueFilePath() {
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
        public void setPartnerCourseCatalogue(ReadOnlyPartnerCourseCatalogue partnerCourseCatalogue) {
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
        public void setUniversityCatalogueFilePath(Path universityCatalogueFilePath) {
        }

        public Optional<University> getUniversityIfExists(UniversityName universityName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setNoteCatalogue(ReadOnlyNoteCatalogue noteCatalogue) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void getSearchUniversityIfExists(
                UniversityNameContainsKeywordsPredicate universityNameContainsKeywordsPredicate
        ) {
            throw new AssertionError("This method should not be called.");
        }

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
        public void updateFilteredNoteList(Predicate<Note> predicate) {
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

    /**
     * A Model stub that contains a single partnerCourse.
     */
    private class SeplendidModelStubWithPartnerCourse extends PartnerCourseAddCommandTest.SeplendidModelStub {
        private final PartnerCourse partnerCourse;

        SeplendidModelStubWithPartnerCourse(PartnerCourse pc) {
            requireNonNull(pc);
            partnerCourse = pc;
        }

        @Override
        public boolean hasPartnerCourse(PartnerCourse pc) {
            requireNonNull(pc);
            return partnerCourse.isSamePartnerCourse(pc);
        }

        @Override
        public boolean hasUniversity(University university) {
            requireNonNull(university);
            return partnerCourse.getPartnerUniversity().isSameUniversity(university);
        }
    }

    private class ModelStubAcceptingPartnerCourseAdded extends PartnerCourseAddCommandTest.SeplendidModelStub {
        final ArrayList<PartnerCourse> partnerCoursesAdded = new ArrayList<>();

        @Override
        public boolean hasPartnerCourse(PartnerCourse partnerCourse) {
            requireNonNull(partnerCourse);
            return partnerCoursesAdded.stream().anyMatch(partnerCourse::isSamePartnerCourse);
        }

        @Override
        public void addPartnerCourse(PartnerCourse partnerCourse) {
            requireNonNull(partnerCourse);
            partnerCoursesAdded.add(partnerCourse);
        }

        @Override
        public boolean hasUniversity(University university) {
            requireNonNull(university);
            //assume that the university exists
            return true;
        }

        @Override
        public ReadOnlyPartnerCourseCatalogue getPartnerCourseCatalogue() {
            return new PartnerCourseCatalogue();
        }
    }


}
