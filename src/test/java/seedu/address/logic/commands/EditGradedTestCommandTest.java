package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GT_FINALS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GT_MIDTERMS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GT_PE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GT_RA1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GT_RA2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.gradedtest.GradedTest.DEFAULT_VALUE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.ConsultationListBook;
import seedu.address.model.GradedTestListBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.SessionListBook;
import seedu.address.model.TaskListBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.gradedtest.Finals;
import seedu.address.model.gradedtest.MidTerms;
import seedu.address.model.gradedtest.PracticalExam;
import seedu.address.model.gradedtest.ReadingAssessment1;
import seedu.address.model.gradedtest.ReadingAssessment2;
import seedu.address.model.person.Person;

public class EditGradedTestCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskListBook(),
            new SessionListBook(), new ConsultationListBook(), new GradedTestListBook());

    @Test
    public void execute_editGradedTest_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        List<Person> lastShownList = model.getFilteredPersonList();
        Person personToEdit = lastShownList.get(targetIndex.getZeroBased());

        EditGradedTestCommand editGradedTestCommand = new EditGradedTestCommand(
                targetIndex,
                Optional.of(new ReadingAssessment1(VALID_GT_RA1)),
                Optional.of(new ReadingAssessment2(VALID_GT_RA2)),
                Optional.of(new MidTerms(VALID_GT_MIDTERMS)),
                Optional.of(new Finals(VALID_GT_FINALS)),
                Optional.of(new PracticalExam(VALID_GT_PE)));

        Person editedPerson = editGradedTestCommand.createEditedGradedTestPerson(personToEdit);

        // Define the expected message
        String expectedMessage = String.format(EditGradedTestCommand.MESSAGE_EDIT_GRADEDTEST_SUCCESS,
                Messages.format(editedPerson));

        // Create a new model with the expected changes
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new UserPrefs(), new TaskListBook(), new SessionListBook(),
                new ConsultationListBook(), new GradedTestListBook());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        // Check if the command execution is successful
        assertCommandSuccess(editGradedTestCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editGradedTestPersonNotFound_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);

        // Create EditGradedTestCommand with an index that is out of bounds
        EditGradedTestCommand editGradedTestCommand = new EditGradedTestCommand(
                outOfBoundIndex,
                Optional.of(new ReadingAssessment1("10.0")),
                Optional.of(new ReadingAssessment2("20.0")),
                Optional.of(new MidTerms("70.0")),
                Optional.of(new Finals("100.0")),
                Optional.of(new PracticalExam("100.0")));

        // Check if the command execution fails
        assertCommandFailure(editGradedTestCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_editGradedTestDefaultValues_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        List<Person> lastShownList = model.getFilteredPersonList();
        Person personToEdit = lastShownList.get(targetIndex.getZeroBased());

        // Create EditGradedTestCommand with no assignment values provided (default values should be retained)
        EditGradedTestCommand editGradedTestCommand = new EditGradedTestCommand(
                targetIndex,
                Optional.of(new ReadingAssessment1(DEFAULT_VALUE)),
                Optional.of(new ReadingAssessment2(DEFAULT_VALUE)),
                Optional.of(new MidTerms(DEFAULT_VALUE)),
                Optional.of(new Finals(DEFAULT_VALUE)),
                Optional.of(new PracticalExam(DEFAULT_VALUE)));

        Person editedPerson = editGradedTestCommand.createEditedGradedTestPerson(personToEdit);

        // Define the expected message
        String expectedMessage = String.format(EditGradedTestCommand.MESSAGE_EDIT_GRADEDTEST_SUCCESS,
                Messages.format(editedPerson));

        // Create a new model with the expected changes
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new UserPrefs(), new TaskListBook(), new SessionListBook(),
                new ConsultationListBook(), new GradedTestListBook());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        // Check if the command execution is successful
        assertCommandSuccess(editGradedTestCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void getCommandType() {
        EditGradedTestCommand command = new EditGradedTestCommand(
                INDEX_FIRST_PERSON,
                Optional.of(new ReadingAssessment1(VALID_GT_RA1)),
                Optional.of(new ReadingAssessment2(VALID_GT_RA2)),
                Optional.of(new MidTerms(VALID_GT_MIDTERMS)),
                Optional.of(new Finals(VALID_GT_FINALS)),
                Optional.of(new PracticalExam(VALID_GT_PE)));

        assertEquals(command.getCommandType(), CommandType.EDIT_GRADED_TEST);
    }

    @Test
    public void equals() {
        Index targetIndex = INDEX_FIRST_PERSON;

        EditGradedTestCommand editGradedTestCommandWithRA1 = new EditGradedTestCommand(
                targetIndex,
                Optional.of(new ReadingAssessment1("10.0")),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty());

        EditGradedTestCommand editGradedTestCommandWithRA2 = new EditGradedTestCommand(
                targetIndex,
                Optional.empty(),
                Optional.of(new ReadingAssessment2("20.0")),
                Optional.empty(),
                Optional.empty(),
                Optional.empty());

        EditGradedTestCommand editGradedTestCommandWithMidTerms = new EditGradedTestCommand(
                targetIndex,
                Optional.empty(),
                Optional.empty(),
                Optional.of(new MidTerms("70.0")),
                Optional.empty(),
                Optional.empty());

        EditGradedTestCommand editGradedTestCommandWithFinals = new EditGradedTestCommand(
                targetIndex,
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.of(new Finals("100.0")),
                Optional.empty());

        EditGradedTestCommand editGradedTestCommandWithPE = new EditGradedTestCommand(
                targetIndex,
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.of(new PracticalExam("100.0")));

        EditGradedTestCommand editGradedTestCommandDifferentIndex = new EditGradedTestCommand(
                INDEX_SECOND_PERSON,
                Optional.of(new ReadingAssessment1("10.0")),
                Optional.of(new ReadingAssessment2("20.0")),
                Optional.of(new MidTerms("70.0")),
                Optional.of(new Finals("100.0")),
                Optional.of(new PracticalExam("100.0")));

        EditGradedTestCommand editGradedTestCommandSameValues = new EditGradedTestCommand(
                targetIndex,
                Optional.of(new ReadingAssessment1("10.0")),
                Optional.of(new ReadingAssessment2("20.0")),
                Optional.of(new MidTerms("70.0")),
                Optional.of(new Finals("100.0")),
                Optional.of(new PracticalExam("100.0")));

        // Test for equality
        assertTrue(editGradedTestCommandWithRA1.equals(editGradedTestCommandWithRA1));
        assertTrue(editGradedTestCommandWithRA1.equals(new EditGradedTestCommand(
                targetIndex,
                Optional.of(new ReadingAssessment1("10.0")),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty())));

        assertFalse(editGradedTestCommandWithRA1.equals(null));
        assertFalse(editGradedTestCommandWithRA1.equals(5));

        // Test for inequality
        assertFalse(editGradedTestCommandWithRA1.equals(editGradedTestCommandWithRA2));
        assertFalse(editGradedTestCommandWithRA1.equals(editGradedTestCommandWithMidTerms));
        assertFalse(editGradedTestCommandWithRA1.equals(editGradedTestCommandWithFinals));
        assertFalse(editGradedTestCommandWithRA1.equals(editGradedTestCommandWithPE));
        assertFalse(editGradedTestCommandWithRA1.equals(editGradedTestCommandDifferentIndex));
        assertFalse(editGradedTestCommandWithRA1.equals(editGradedTestCommandSameValues));
    }
}
