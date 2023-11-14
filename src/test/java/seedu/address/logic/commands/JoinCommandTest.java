package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalGroups.GROUP1;
import static seedu.address.testutil.TypicalGroups.GROUP11;
import static seedu.address.testutil.TypicalGroups.GROUP2;
import static seedu.address.testutil.TypicalGroups.GROUP3;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.DANIEL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalGroups;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code JoinCommand}.
 */
public class JoinCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalGroups.getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(TypicalGroups.getTypicalAddressBook(), new UserPrefs());
        model.addPerson(AMY);
        expectedModel.addPerson(AMY);
    }

    @Test
    public void execute_join_success() {
        // person exists, group exists, group is not full, person is not in a group
        JoinCommand command = new JoinCommand(AMY.getEmail(), GROUP3.getNumber());
        String expectedMessage = String.format(JoinCommand.MESSAGE_JOIN_SUCCESS,
                AMY.getName(), GROUP3.getNumber());

        expectedModel.addPersonToGroup(AMY, expectedModel.getGroupWithNumber(GROUP3.getNumber()).get());

        assertCommandSuccess(command, model, expectedMessage, expectedModel, true);
    }

    @Test
    public void execute_personDoesNotExist_throwsCommandException() {
        JoinCommand command = new JoinCommand(BOB.getEmail(), GROUP1.getNumber());
        assertCommandFailure(command, model, JoinCommand.MESSAGE_JOIN_EMAIL_NOT_FOUND);
    }

    @Test
    public void execute_groupDoesNotExist_throwsCommandException() {
        JoinCommand command = new JoinCommand(AMY.getEmail(), 100);
        assertCommandFailure(command, model, JoinCommand.MESSAGE_JOIN_GROUP_NOT_FOUND);
    }

    @Test
    public void execute_personAlreadyInTheGroup_throwsCommandException() {
        JoinCommand command = new JoinCommand(ALICE.getEmail(), GROUP1.getNumber());
        assertCommandFailure(command, model, JoinCommand.MESSAGE_PERSON_ALREADY_IN_GROUP);
    }

    @Test
    public void execute_personInAnotherGroup_throwsCommandException() {
        JoinCommand command = new JoinCommand(DANIEL.getEmail(), GROUP1.getNumber());
        assertCommandFailure(command, model, JoinCommand.MESSAGE_PERSON_IN_ANOTHER_GROUP);
    }

    @Test
    public void execute_fullGroup_throwsCommandException() {
        JoinCommand command = new JoinCommand(AMY.getEmail(), GROUP11.getNumber());
        assertCommandFailure(command, model, JoinCommand.MESSAGE_GROUP_FULL);
    }

    @Test
    public void equals() {
        JoinCommand firstJoinCommand = new JoinCommand(AMY.getEmail(), GROUP1.getNumber());
        JoinCommand secondJoinCommand = new JoinCommand(AMY.getEmail(), GROUP2.getNumber());
        JoinCommand thirdJoinCommand = new JoinCommand(BOB.getEmail(), GROUP1.getNumber());

        // same object -> returns true
        assertTrue(firstJoinCommand.equals(firstJoinCommand));

        // same values -> returns true
        JoinCommand firstJoinCommandCopy = new JoinCommand(AMY.getEmail(), GROUP1.getNumber());
        assertTrue(firstJoinCommand.equals(firstJoinCommandCopy));

        // different types -> returns false
        assertFalse(firstJoinCommand.equals(1));

        // null -> returns false
        assertFalse(firstJoinCommand.equals(null));

        // different email -> returns false
        assertFalse(firstJoinCommand.equals(thirdJoinCommand));

        // different group number -> returns false
        assertFalse(firstJoinCommand.equals(secondJoinCommand));
    }

}
