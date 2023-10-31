package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.address.testutil.EditEventDescriptorBuilder;
import seedu.address.testutil.MeetingBuilder;


public class EditEventCommandTest {

    Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditEventCommand(null, null));
    }

    @Test
    public void execute_allSpecifiedFieldsUnfilteredList_success() throws Exception {
        Event editedEvent = new MeetingBuilder()
                .withEventDate("2050-10-10")
                .withEventStartTime("1400")
                .withEventEndTime("1500")
                .withPerson("Carl Kurz", "Elle Meyer")
                .withGroups("friends").build();

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(editedEvent).build();
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT, descriptor);

        String expectedMessage = String.format(EditEventCommand.MESSAGE_EDIT_SUCCESS, Messages.formatEvent(editedEvent));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setEvent(model.getEventList().get(0), editedEvent);

        CommandResult result = editEventCommand.execute(model);
        assertEquals(new CommandResult(expectedMessage), result);
        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_someFieldsUnfilteredList_success() throws Exception {
        Event editedEvent = new MeetingBuilder()
                .withEventDate("2050-10-10")
                .withEventStartTime("1400")
                .withEventEndTime("1500").build();

        Event expectedEvent = new MeetingBuilder()
                .withEventDate("2050-10-10")
                .withEventStartTime("1400")
                .withEventEndTime("1500")
                .withPerson(model.getEventList().get(0).getNames())
                .withGroups(model.getEventList().get(0).getGroups()).build();

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(editedEvent).build();
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT, descriptor);

        String expectedMessage = String
                .format(EditEventCommand.MESSAGE_EDIT_SUCCESS, Messages.formatEvent(expectedEvent));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setEvent(model.getEventList().get(0), editedEvent);

        CommandResult result = editEventCommand.execute(model);
        assertEquals(new CommandResult(expectedMessage), result);
        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() throws Exception {
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT, new EditEventDescriptor());
        Event editedEvent = model.getEventList().get(INDEX_FIRST_EVENT.getZeroBased());

        String expectedMessage = String.format(EditEventCommand.MESSAGE_EDIT_SUCCESS, Messages.formatEvent(editedEvent));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        CommandResult result = editEventCommand.execute(model);
        assertEquals(new CommandResult(expectedMessage), result);
        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_personsGroupsOverlap_success() throws Exception {
        Event editedEvent = new MeetingBuilder()
                .withEventDate("2050-10-10")
                .withEventStartTime("1400")
                .withEventEndTime("1500")
                .withPerson("Alice Pauline", "Benson Meier")
                .withGroups("friends").build();

        Event expectedEvent = new MeetingBuilder()
                .withEventDate("2050-10-10")
                .withEventStartTime("1400")
                .withEventEndTime("1500")
                .withPerson(model.getEventList().get(0).getNames())
                .withGroups("friends").build();

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(editedEvent).build();
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT, descriptor);

        String expectedMessage = String
                .format(EditEventCommand.MESSAGE_EDIT_SUCCESS, Messages.formatEvent(expectedEvent));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setEvent(model.getEventList().get(0), expectedEvent);

        CommandResult result = editEventCommand.execute(model);
        assertEquals(new CommandResult(expectedMessage), result);
        assertEquals(expectedModel, model);
    }
}
