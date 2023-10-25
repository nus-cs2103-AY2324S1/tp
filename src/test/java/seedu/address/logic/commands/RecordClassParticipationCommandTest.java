package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
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
public class RecordClassParticipationCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Student editedStudent = new StudentBuilder(TypicalStudents.ALICE)
                .withClassPartDetails(1, true)
                .build();

        StudentNumber studentNumber = editedStudent.getStudentNumber();
        RecordClassParticipationCommand recordClassParticipationCommand = new RecordClassParticipationCommand(studentNumber, 1, true);

        String expectedMessage = String.format(RecordClassParticipationCommand.MESSAGE_SUCCESS,
                editedStudent.getStudentNumber())
                + editedStudent.getClassDetails().displayParticipations();

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(model.getStudent(editedStudent.getStudentNumber()), editedStudent);

        assertCommandSuccess(recordClassParticipationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(studentInFilteredList)
                .withClassPartDetails(1, true)
                .build();
        RecordClassParticipationCommand recordClassParticipationCommand = new RecordClassParticipationCommand(
                editedStudent.getStudentNumber(), 1, true);

        String expectedMessage = String.format(RecordClassParticipationCommand.MESSAGE_SUCCESS,
                editedStudent.getStudentNumber())
                + editedStudent.getClassDetails().displayParticipations();

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showStudentAtIndex(expectedModel, INDEX_FIRST_STUDENT);
        expectedModel.setStudent(model.getStudent(editedStudent.getStudentNumber()), editedStudent);

        assertCommandSuccess(recordClassParticipationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_studentDoesNotExist_failure() {
        Student ida = TypicalStudents.IDA;
        assertFalse(model.hasStudent(ida));
        RecordClassParticipationCommand recordClassParticipationCommand = new RecordClassParticipationCommand(
                ida.getStudentNumber(), 1, true);

        assertCommandFailure(recordClassParticipationCommand, model, Messages.MESSAGE_NONEXISTENT_STUDENT_NUMBER);
    }

    @Test
    public void equals() {
        final RecordClassParticipationCommand standardCommand = new RecordClassParticipationCommand(
                new StudentNumber(VALID_STUDENT_NUMBER_AMY), 1, true);

        // same values -> returns true
        RecordClassParticipationCommand commandWithSameValues = new RecordClassParticipationCommand(
                new StudentNumber(VALID_STUDENT_NUMBER_AMY), 1, true);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different tutorial session -> returns false
        assertFalse(standardCommand.equals(new RecordClassParticipationCommand(
                new StudentNumber(VALID_STUDENT_NUMBER_AMY), 2, true)));

        // different participation -> returns false
        assertFalse(standardCommand.equals(new RecordClassParticipationCommand(
                new StudentNumber(VALID_STUDENT_NUMBER_AMY), 1, false)));

        // different student number -> returns false
        assertFalse(standardCommand.equals(new RecordClassParticipationCommand(
                new StudentNumber(VALID_STUDENT_NUMBER_BOB), 1, true)));
    }

    @Test
    public void toStringMethod() {
        RecordClassParticipationCommand recordClassParticipationCommand = new RecordClassParticipationCommand(
                new StudentNumber(VALID_STUDENT_NUMBER_AMY), 1, true);

        String expected = RecordClassParticipationCommand.class.getCanonicalName()
                + "{studentNumber=" + VALID_STUDENT_NUMBER_AMY + ", sessionNumber=1, isParticipated=true}";
        assertEquals(expected, recordClassParticipationCommand.toString());
    }

}
