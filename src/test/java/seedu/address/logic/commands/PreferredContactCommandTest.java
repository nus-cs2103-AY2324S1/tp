package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREFERRED_CONTACT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREFERRED_CONTACT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PreferredContact;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * RemarkCommand.
 */

public class PreferredContactCommandTest {
    private static final String PREFERREDCONTACT_STUB = "";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);

        PreferredContactCommand preferredContactCommand = new PreferredContactCommand(outOfBoundIndex,
                new PreferredContact(VALID_PREFERRED_CONTACT_BOB));

        assertCommandFailure(preferredContactCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        PreferredContactCommand preferredContactCommand = new PreferredContactCommand(
                outOfBoundIndex,
                new PreferredContact(VALID_PREFERRED_CONTACT_BOB));

        assertCommandFailure(preferredContactCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final PreferredContactCommand standardCommand = new PreferredContactCommand(
                INDEX_FIRST_PERSON,
                new PreferredContact(VALID_PREFERRED_CONTACT_AMY));

        // same values -> returns true
        PreferredContactCommand commandWithSameValues = new PreferredContactCommand(INDEX_FIRST_PERSON,
                new PreferredContact(VALID_PREFERRED_CONTACT_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different remark -> returns false
        assertFalse(standardCommand.equals(
                new PreferredContactCommand(
                        INDEX_FIRST_PERSON,
                        new PreferredContact(VALID_PREFERRED_CONTACT_BOB))));

        // different index -> returns false
        assertFalse(standardCommand.equals(
                new PreferredContactCommand(
                        INDEX_SECOND_PERSON,
                        new PreferredContact(VALID_PREFERRED_CONTACT_AMY))));

        // different object type -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
    }
}
