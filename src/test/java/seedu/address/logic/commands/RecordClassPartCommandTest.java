package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentNumber;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TypicalStudents;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RecordClassPartCommand.
 */
public class RecordClassPartCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Student editedStudent = new StudentBuilder(TypicalStudents.ALICE)
                .withClassPartDetails(1, true)
                .build();

        StudentNumber studentNumber = editedStudent.getStudentNumber();
        RecordClassPartCommand recordClassPartCommand = new RecordClassPartCommand(studentNumber, 1, true);

        String expectedMessage = String.format(RecordClassPartCommand.MESSAGE_SUCCESS,
                editedStudent.getStudentNumber())
                + editedStudent.getClassDetails().displayParticipations();

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(model.getStudent(editedStudent.getStudentNumber()), editedStudent);

        assertCommandSuccess(recordClassPartCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student editedStudent = new StudentBuilder(studentInFilteredList)
                .withClassPartDetails(1, true)
                .build();
        RecordClassPartCommand recordClassPartCommand = new RecordClassPartCommand(
                editedStudent.getStudentNumber(), 1, true);

        String expectedMessage = String.format(RecordClassPartCommand.MESSAGE_SUCCESS,
                editedStudent.getStudentNumber())
                + editedStudent.getClassDetails().displayParticipations();

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.setStudent(model.getStudent(editedStudent.getStudentNumber()), editedStudent);

        assertCommandSuccess(recordClassPartCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_studentDoesNotExist_failure() {
        Student ida = TypicalStudents.IDA;
        assertFalse(model.hasStudent(ida));
        RecordClassPartCommand recordClassPartCommand = new RecordClassPartCommand(
                ida.getStudentNumber(), 1, true);

        assertCommandFailure(recordClassPartCommand, model, Messages.MESSAGE_NONEXISTENT_STUDENT_NUMBER);
    }

    @Test
    public void equals() {
        final RecordClassPartCommand standardCommand = new RecordClassPartCommand(
                new StudentNumber(VALID_STUDENT_NUMBER_AMY), 1, true);

        // same values -> returns true
        RecordClassPartCommand commandWithSameValues = new RecordClassPartCommand(
                new StudentNumber(VALID_STUDENT_NUMBER_AMY), 1, true);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different tutorial session -> returns false
        assertFalse(standardCommand.equals(new RecordClassPartCommand(
                new StudentNumber(VALID_STUDENT_NUMBER_AMY), 2, true)));

        // different participation -> returns false
        assertFalse(standardCommand.equals(new RecordClassPartCommand(
                new StudentNumber(VALID_STUDENT_NUMBER_AMY), 1, false)));

        // different student number -> returns false
        assertFalse(standardCommand.equals(new RecordClassPartCommand(
                new StudentNumber(VALID_STUDENT_NUMBER_BOB), 1, true)));
    }

    @Test
    public void toStringMethod() {
        RecordClassPartCommand recordClassPartCommand = new RecordClassPartCommand(
                new StudentNumber(VALID_STUDENT_NUMBER_AMY), 1, true);

        String expected = RecordClassPartCommand.class.getCanonicalName()
                + "{studentNumber=" + VALID_STUDENT_NUMBER_AMY + ", sessionNumber=1, isParticipated=true}";
        assertEquals(expected, recordClassPartCommand.toString());
    }

}
