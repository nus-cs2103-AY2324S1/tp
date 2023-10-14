package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Interaction;
import seedu.address.model.person.Interaction.Outcome;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for InteractionCommand.
 */
public class InteractionCommandTest {

    private static final String INTERACTION_NOTE_STUB = "Test note";
    private static final Outcome INTERACTION_OUTCOME_STUB = Outcome.INTERESTED;

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() throws CommandException {
        Person personToAddInteractions = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Set<Interaction> interactions = personToAddInteractions.getInteractions();
        Interaction interactionToAdd = new Interaction(INTERACTION_NOTE_STUB, INTERACTION_OUTCOME_STUB);
        interactions.add(interactionToAdd);

        Person editedPerson = new PersonBuilder(personToAddInteractions).build();
        editedPerson.addInteractions(interactions);

        InteractionCommand interactionCommand = new InteractionCommand(INDEX_FIRST_PERSON, interactionToAdd);

        String expectedMessage = String.format(InteractionCommand.MESSAGE_INTERACTION_SUCCESS, personToAddInteractions);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToAddInteractions, editedPerson);

        assertCommandSuccess(interactionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Interaction interactionToAdd = new Interaction(INTERACTION_NOTE_STUB, INTERACTION_OUTCOME_STUB);
        InteractionCommand interactionCommand = new InteractionCommand(outOfBoundIndex, interactionToAdd);

        assertCommandFailure(interactionCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Interaction interactionToAdd = new Interaction(INTERACTION_NOTE_STUB, INTERACTION_OUTCOME_STUB);
        final InteractionCommand standardCommand = new InteractionCommand(INDEX_FIRST_PERSON, interactionToAdd);

        // same values -> returns true
        InteractionCommand commandWithSameValues = new InteractionCommand(INDEX_FIRST_PERSON, interactionToAdd);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new InteractionCommand(Index.fromOneBased(2), interactionToAdd)));

        // different interaction -> returns false
        assertFalse(standardCommand.equals(new InteractionCommand(INDEX_FIRST_PERSON,
            new Interaction("Different note", Outcome.UNKNOWN))));
    }
}
