package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalWellNus.getTypicalWellNus;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.WellNus;
import seedu.address.model.student.Note;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for NoteCommand.
 */
public class NoteCommandTest {

    private static final String NOTE_STUB = "Some note";
    private static final String HUNDRED_CHAR_NOTE_STUB = "Lorem Ipsum is simply dummy text of the printing and "
            + "typesetting industry. Lorem Ipsum has been the industry’s standard";

    private Model model = new ModelManager(getTypicalWellNus(), new UserPrefs());

    // Add note < 100 chars
    @Test
    public void execute_addNoteUnfilteredList_success() {
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(firstStudent).withNote(NOTE_STUB).build();

        NoteCommand noteCommand = new NoteCommand(INDEX_FIRST_STUDENT, new Note(editedStudent.getNote().value));

        String expectedMessage = String.format(NoteCommand.MESSAGE_ADD_NOTE_SUCCESS,
                Messages.format(editedStudent), NOTE_STUB);

        Model expectedModel = new ModelManager(new WellNus(model.getWellNusData()), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);

        assertCommandSuccess(noteCommand, model, expectedMessage, expectedModel);
    }

    // Add note of length between 100 and 500 chars
    @Test
    public void execute_addNoteMoreThanHundredChars_success() {
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(firstStudent).withNote(HUNDRED_CHAR_NOTE_STUB).build();

        NoteCommand noteCommand = new NoteCommand(INDEX_FIRST_STUDENT, new Note(editedStudent.getNote().value));

        String expectedMessage = String.format(NoteCommand.MESSAGE_ADD_NOTE_SUCCESS,
                Messages.format(editedStudent), HUNDRED_CHAR_NOTE_STUB.substring(0, 100).trim()
                        + "... (Double-click on the student card to view the full note)");

        Model expectedModel = new ModelManager(new WellNus(model.getWellNusData()), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);

        assertCommandSuccess(noteCommand, model, expectedMessage, expectedModel);
    }

    // Delete note
    @Test
    public void execute_deleteNoteUnfilteredList_success() {
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(firstStudent).withNote("").build();

        NoteCommand noteCommand = new NoteCommand(INDEX_FIRST_STUDENT,
                new Note(editedStudent.getNote().toString()));

        String expectedMessage = String.format(NoteCommand.MESSAGE_DELETE_NOTE_SUCCESS,
                Messages.format(editedStudent));

        Model expectedModel = new ModelManager(new WellNus(model.getWellNusData()), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);

        assertCommandSuccess(noteCommand, model, expectedMessage, expectedModel);
    }

    // Add note on filtered list
    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(model.getFilteredStudentList().get(INDEX_FIRST_STUDENT
                .getZeroBased())).withNote(NOTE_STUB).build();

        NoteCommand noteCommand = new NoteCommand(INDEX_FIRST_STUDENT, new Note(editedStudent.getNote().value));

        String expectedMessage = String.format(NoteCommand.MESSAGE_ADD_NOTE_SUCCESS,
                Messages.format(editedStudent), NOTE_STUB);

        Model expectedModel = new ModelManager(new WellNus(model.getWellNusData()), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);

        assertCommandSuccess(noteCommand, model, expectedMessage, expectedModel);
    }

    // Invalid index, unfiltered list
    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        NoteCommand noteCommand = new NoteCommand(outOfBoundIndex, new Note(VALID_NOTE_BOB));

        assertCommandFailure(noteCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of wellnus storage
     */
    @Test
    public void execute_invalidStudentIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of wellnus storage list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getWellNusData().getStudentList().size());

        NoteCommand noteCommand = new NoteCommand(outOfBoundIndex, new Note(VALID_NOTE_BOB));

        assertCommandFailure(noteCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final NoteCommand standardCommand = new NoteCommand(INDEX_FIRST_STUDENT,
                new Note(VALID_NOTE_AMY));

        // same values -> returns true
        NoteCommand commandWithSameValues = new NoteCommand(INDEX_FIRST_STUDENT,
                new Note(VALID_NOTE_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new NoteCommand(INDEX_SECOND_STUDENT,
                new Note(VALID_NOTE_AMY))));

        // different note -> returns false
        assertFalse(standardCommand.equals(new NoteCommand(INDEX_FIRST_STUDENT,
                new Note(VALID_NOTE_BOB))));
    }
}
