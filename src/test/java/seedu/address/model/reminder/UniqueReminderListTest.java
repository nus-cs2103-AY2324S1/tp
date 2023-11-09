package seedu.address.model.reminder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_ALICE;
import static seedu.address.testutil.TypicalIndexes.INDEX_BENSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.InteractionCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.interaction.Interaction;

public class UniqueReminderListTest {

    private static final String INTERACTION_NOTE_STUB = "Test note";
    private static final Interaction.Outcome INTERACTION_OUTCOME_STUB = Interaction.Outcome.INTERESTED;
    private ModelManager modelManager = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final UniqueReminderList uniqueReminderList = new UniqueReminderList(modelManager);

    @Test
    public void updateReminders_personHasNewInteraction_success() {
        Interaction interactionToAdd = new Interaction(INTERACTION_NOTE_STUB, INTERACTION_OUTCOME_STUB);
        InteractionCommand interactionCommand = new InteractionCommand(INDEX_ALICE, interactionToAdd);
        try {
            interactionCommand.execute(modelManager);
        } catch (CommandException e) {
            fail();
        }
        uniqueReminderList.updateReminders();

        UniqueReminderList expectedUniqueReminderList = new UniqueReminderList(modelManager);
        ALICE.getInteractions().add(interactionToAdd);
        expectedUniqueReminderList.addReminder(new Reminder(ALICE));
        expectedUniqueReminderList.addReminder(new Reminder(BENSON));

        assertEquals(expectedUniqueReminderList, uniqueReminderList);
    }

    @Test
    public void updateReminders_personHasSecondInteraction_success() {
        Interaction interactionToAdd = new Interaction(INTERACTION_NOTE_STUB, INTERACTION_OUTCOME_STUB);
        InteractionCommand interactionCommand = new InteractionCommand(INDEX_BENSON, interactionToAdd);
        try {
            interactionCommand.execute(modelManager);
        } catch (CommandException e) {
            fail();
        }
        uniqueReminderList.updateReminders();

        UniqueReminderList expectedUniqueReminderList = new UniqueReminderList(modelManager);
        BENSON.getInteractions().add(interactionToAdd);
        expectedUniqueReminderList.addReminder(new Reminder(ALICE));
        expectedUniqueReminderList.addReminder(new Reminder(BENSON));

        assertEquals(expectedUniqueReminderList, uniqueReminderList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueReminderList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueReminderList.asUnmodifiableObservableList().toString(), uniqueReminderList.toString());
    }
}
