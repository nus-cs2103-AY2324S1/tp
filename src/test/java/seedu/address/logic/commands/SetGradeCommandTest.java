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
 * Contains integration tests (interaction with the Model) and unit tests for SetGradeCommand.
 */
public class SetGradeCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Student editedStudent = new StudentBuilder(TypicalStudents.ALICE)
                .withAssignmentDetails(1, 100)
                .build();

        StudentNumber studentNumber = editedStudent.getStudentNumber();
        SetGradeCommand setGradeCommand = new SetGradeCommand(studentNumber, 1, 100);

        String expectedMessage = String.format(SetGradeCommand.MESSAGE_SUCCESS, editedStudent.getStudentNumber())
                + editedStudent.getClassDetails().displayAssignments();

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(model.getStudent(editedStudent.getStudentNumber()), editedStudent);

        assertCommandSuccess(setGradeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student editedStudent = new StudentBuilder(studentInFilteredList)
                .withAssignmentDetails(1, 50)
                .build();
        SetGradeCommand setGradeCommand = new SetGradeCommand(editedStudent.getStudentNumber(), 1, 50);

        String expectedMessage = String.format(SetGradeCommand.MESSAGE_SUCCESS, editedStudent.getStudentNumber())
                + editedStudent.getClassDetails().displayAssignments();

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.setStudent(model.getStudent(editedStudent.getStudentNumber()), editedStudent);

        assertCommandSuccess(setGradeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_studentDoesNotExist_failure() {
        Student ida = TypicalStudents.IDA;
        assertFalse(model.hasStudent(ida));
        SetGradeCommand setGradeCommand = new SetGradeCommand(ida.getStudentNumber(), 1, 100);

        assertCommandFailure(setGradeCommand, model, Messages.MESSAGE_NONEXISTENT_STUDENT_NUMBER);
    }

    @Test
    public void equals() {
        final SetGradeCommand standardCommand = new SetGradeCommand(
                new StudentNumber(VALID_STUDENT_NUMBER_AMY), 1, 100);

        // same values -> returns true
        SetGradeCommand commandWithSameValues = new SetGradeCommand(
                new StudentNumber(VALID_STUDENT_NUMBER_AMY), 1, 100);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different assignment -> returns false
        assertFalse(standardCommand.equals(new SetGradeCommand(
                new StudentNumber(VALID_STUDENT_NUMBER_AMY), 2, 100)));

        // different grade -> returns false
        assertFalse(standardCommand.equals(new SetGradeCommand(
                new StudentNumber(VALID_STUDENT_NUMBER_AMY), 1, 50)));

        // different student number -> returns false
        assertFalse(standardCommand.equals(new SetGradeCommand(
                new StudentNumber(VALID_STUDENT_NUMBER_BOB), 1, 50)));
    }

    @Test
    public void toStringMethod() {
        SetGradeCommand setGradeCommand = new SetGradeCommand(
                new StudentNumber(VALID_STUDENT_NUMBER_AMY), 1, 50);

        String expected = SetGradeCommand.class.getCanonicalName()
                + "{studentNumber=" + VALID_STUDENT_NUMBER_AMY + ", assignmentNumber=1, grade=50}";
        assertEquals(expected, setGradeCommand.toString());
    }

}
