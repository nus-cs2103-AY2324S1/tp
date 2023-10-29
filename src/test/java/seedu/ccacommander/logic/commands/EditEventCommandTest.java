package seedu.ccacommander.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ccacommander.logic.commands.CommandTestUtil.DESC_AURORA;
import static seedu.ccacommander.logic.commands.CommandTestUtil.DESC_BOXING;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_LOCATION_AURORA;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_NAME_AURORA;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_TAG_AURORA;
import static seedu.ccacommander.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.ccacommander.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ccacommander.logic.commands.CommandTestUtil.showEventAtIndex;
import static seedu.ccacommander.testutil.TypicalCcaCommander.getTypicalCcaCommander;
import static seedu.ccacommander.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.ccacommander.testutil.TypicalIndexes.INDEX_SECOND_EVENT;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.ccacommander.commons.core.index.Index;
import seedu.ccacommander.logic.Messages;
import seedu.ccacommander.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.ccacommander.model.CcaCommander;
import seedu.ccacommander.model.Model;
import seedu.ccacommander.model.ModelManager;
import seedu.ccacommander.model.UserPrefs;
import seedu.ccacommander.model.enrolment.Enrolment;
import seedu.ccacommander.model.event.Event;
import seedu.ccacommander.model.shared.Name;
import seedu.ccacommander.testutil.EditEventDescriptorBuilder;
import seedu.ccacommander.testutil.EventBuilder;



/**
 * Contains integration tests (interaction with the Model) and unit tests for EditEventCommand.
 */
public class EditEventCommandTest {

    private Model model = new ModelManager(getTypicalCcaCommander(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Event editedEvent = new EventBuilder().build();
        EditEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder(editedEvent).build();
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT, descriptor);

        String commitMessage = String.format(EditEventCommand.MESSAGE_COMMIT, editedEvent.getName());
        String expectedMessage = String.format(EditEventCommand.MESSAGE_EDIT_EVENT_SUCCESS,
                Messages.format(editedEvent));

        Model expectedModel = new ModelManager(new CcaCommander(model.getCcaCommander()), new UserPrefs());
        expectedModel.setEvent(model.getFilteredEventList().get(0), editedEvent);
        expectedModel.commit(commitMessage);

        Name prevName = model.getFilteredEventList().get(0).getName();
        Name newName = editedEvent.getName();
        // If event's name is edited, the corresponding enrolment objects are edited also
        if (!prevName.equals(newName)) {
            List<Enrolment> enrolmentList = model.getFilteredEnrolmentList();
            for (Enrolment enrolment: enrolmentList) {
                if (enrolment.getEventName().equals(prevName)) {
                    Enrolment editedEnrolment = new Enrolment(enrolment.getMemberName(), newName,
                            enrolment.getHours(), enrolment.getRemark());
                    expectedModel.setEnrolment(enrolment, editedEnrolment);
                }
            }
        }
        assertCommandSuccess(editEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastEvent = Index.fromOneBased(model.getFilteredEventList().size());
        Event lastEvent = model.getFilteredEventList().get(indexLastEvent.getZeroBased());

        EventBuilder eventInList = new EventBuilder(lastEvent);
        Event editedEvent = eventInList.withName(VALID_NAME_AURORA).withLocation(VALID_LOCATION_AURORA)
                .withTags(VALID_TAG_AURORA).build();

        EditEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_AURORA)
                .withLocation(VALID_LOCATION_AURORA).withTags(VALID_TAG_AURORA).build();
        EditEventCommand editEventCommand = new EditEventCommand(indexLastEvent, descriptor);

        String commitMessage = String.format(EditEventCommand.MESSAGE_COMMIT, editedEvent.getName());
        String expectedMessage = String.format(EditEventCommand.MESSAGE_EDIT_EVENT_SUCCESS,
                Messages.format(editedEvent));

        Model expectedModel = new ModelManager(new CcaCommander(model.getCcaCommander()), new UserPrefs());
        expectedModel.setEvent(lastEvent, editedEvent);

        Name prevName = lastEvent.getName();
        Name newName = editedEvent.getName();
        // If event's name is edited, the corresponding enrolment objects are edited also
        if (!prevName.equals(newName)) {
            List<Enrolment> enrolmentList = model.getFilteredEnrolmentList();
            for (Enrolment enrolment: enrolmentList) {
                if (enrolment.getEventName().equals(prevName)) {
                    Enrolment editedEnrolment = new Enrolment(enrolment.getMemberName(), newName,
                            enrolment.getHours(), enrolment.getRemark());
                    expectedModel.setEnrolment(enrolment, editedEnrolment);
                }
            }
        }
        expectedModel.commit(commitMessage);

        assertCommandSuccess(editEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT,
                new EditEventCommand.EditEventDescriptor());
        Event editedEvent = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());

        String commitMessage = String.format(EditEventCommand.MESSAGE_COMMIT, editedEvent.getName());
        String expectedMessage = String.format(EditEventCommand.MESSAGE_EDIT_EVENT_SUCCESS,
                Messages.format(editedEvent));

        Model expectedModel = new ModelManager(new CcaCommander(model.getCcaCommander()), new UserPrefs());
        expectedModel.commit(commitMessage);

        assertCommandSuccess(editEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        Event eventInFilteredList = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        Event editedEvent = new EventBuilder(eventInFilteredList).withName(VALID_NAME_AURORA).build();
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT,
                new EditEventDescriptorBuilder().withName(VALID_NAME_AURORA).build());

        String commitMessage = String.format(EditEventCommand.MESSAGE_COMMIT, editedEvent.getName());
        String expectedMessage = String.format(EditEventCommand.MESSAGE_EDIT_EVENT_SUCCESS,
                Messages.format(editedEvent));

        Model expectedModel = new ModelManager(new CcaCommander(model.getCcaCommander()), new UserPrefs());
        expectedModel.setEvent(model.getFilteredEventList().get(0), editedEvent);
        expectedModel.commit(commitMessage);

        assertCommandSuccess(editEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateEventUnfilteredList_failure() {
        Event firstEvent = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        EditEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder(firstEvent).build();
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_SECOND_EVENT, descriptor);

        assertCommandFailure(editEventCommand, model, EditEventCommand.MESSAGE_DUPLICATE_EVENT);
    }

    @Test
    public void execute_duplicateEventFilteredList_failure() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        // edit event in filtered list into a duplicate in CcaCommander
        Event eventInList = model.getCcaCommander().getEventList().get(INDEX_SECOND_EVENT.getZeroBased());
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT,
                new EditEventDescriptorBuilder(eventInList).build());

        assertCommandFailure(editEventCommand, model, EditEventCommand.MESSAGE_DUPLICATE_EVENT);
    }

    @Test
    public void execute_invalidEventIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_AURORA).build();
        EditEventCommand editEventCommand = new EditEventCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of CcaCommander
     */
    @Test
    public void execute_invalidEventIndexFilteredList_failure() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);
        Index outOfBoundIndex = INDEX_SECOND_EVENT;
        // ensures that outOfBoundIndex is still in bounds of CcaCommander list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getCcaCommander().getEventList().size());

        EditEventCommand editEventCommand = new EditEventCommand(outOfBoundIndex,
                new EditEventDescriptorBuilder().withName(VALID_NAME_AURORA).build());

        assertCommandFailure(editEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditEventCommand standardCommand = new EditEventCommand(INDEX_FIRST_EVENT, DESC_AURORA);

        // same values -> returns true
        EditEventDescriptor copyDescriptor = new EditEventDescriptor(DESC_AURORA);
        EditEventCommand commandWithSameValues = new EditEventCommand(INDEX_FIRST_EVENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditEventCommand(INDEX_SECOND_EVENT, DESC_AURORA)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditEventCommand(INDEX_FIRST_EVENT, DESC_BOXING)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditEventDescriptor editEventDescriptor = new EditEventCommand.EditEventDescriptor();
        EditEventCommand editEventCommand = new EditEventCommand(index, editEventDescriptor);
        String expected = EditEventCommand.class.getCanonicalName() + "{index=" + index + ", editEventDescriptor="
                + editEventDescriptor + "}";
        assertEquals(expected, editEventCommand.toString());
    }

}
