package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookManager;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for MergeCommand.
 */
public class MergeCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookManager(), new UserPrefs());

    @Test
    public void execute_mergeTwoPeopleWithNoTags_success() {
        Person primaryStudent = new PersonBuilder(AMY).withTags().build();
        Person secondaryStudent = new PersonBuilder(BOB).withTags().build();
        Person mergedStudent = primaryStudent.mergePersons(secondaryStudent);

        CommandResult expectedResult = new CommandResult(String.format(MergeCommand.MESSAGE_MERGE_PERSON_SUCCESS,
                Messages.format(primaryStudent), Messages.format(secondaryStudent),
                Messages.format(mergedStudent)));
        ModelManager expectedModel = new ModelManager();
        expectedModel.addPerson(mergedStudent);

        ModelManager actualModel = new ModelManager();
        actualModel.addPerson(primaryStudent);
        actualModel.addPerson(secondaryStudent);
        this.model = actualModel;
        MergeCommand mergeCommand = new MergeCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);

        assertCommandSuccess(mergeCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_mergeTwoPeopleWithTags_success() {
        Person primaryStudent = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person secondaryStudent = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person mergedStudent = primaryStudent.mergePersons(secondaryStudent);

        CommandResult expectedResult = new CommandResult(String.format(MergeCommand.MESSAGE_MERGE_PERSON_SUCCESS,
                Messages.format(primaryStudent), Messages.format(secondaryStudent),
                Messages.format(mergedStudent)));
        ModelManager expectedModel = new ModelManager(model.getAddressBookManager(), new UserPrefs());
        expectedModel.setPerson(primaryStudent, mergedStudent);
        expectedModel.deletePerson(secondaryStudent);

        MergeCommand mergeCommand = new MergeCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);

        assertCommandSuccess(mergeCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_mergeInvalidPrimaryPersonIndex_failure() {

        int n = model.getFilteredPersonList().size();
        Index outOfBoundIndex = Index.create(n + 1);

        MergeCommand mergeCommand = new MergeCommand(outOfBoundIndex, INDEX_SECOND_PERSON);

        assertCommandFailure(mergeCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

    }

    @Test
    public void execute_mergeInvalidSecondaryPersonIndex_failure() {

        int n = model.getFilteredPersonList().size();
        Index outOfBoundIndex = Index.create(n + 1);

        MergeCommand mergeCommand = new MergeCommand(INDEX_FIRST_PERSON, outOfBoundIndex);

        assertCommandFailure(mergeCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

    }

    @Test
    public void equals() {
        final MergeCommand standardCommand = new MergeCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);

        // same values -> returns true
        MergeCommand commandWithSameValues = new MergeCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new MergeCommand(INDEX_SECOND_PERSON, INDEX_FIRST_PERSON)));

    }

    @Test
    public void toStringMethod() {
        Index primaryIndex = Index.fromOneBased(1);
        Index secondaryIndex = Index.fromOneBased(2);
        MergeCommand mergeCommand = new MergeCommand(primaryIndex, secondaryIndex);
        String expected = MergeCommand.class.getCanonicalName() + "{primary index=" + primaryIndex
                + ", secondary index=" + secondaryIndex + "}";
        assertEquals(expected, mergeCommand.toString());
    }

}
