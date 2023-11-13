package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_EVENT_OUT_OF_BOUNDS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_TYPICAL_GROUP_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_TYPICAL_PERSON_EVENT;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;
import seedu.address.testutil.EditEventDescriptorBuilder;
import seedu.address.testutil.MeetingBuilder;


public class EditEventCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

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

        String expectedMessage = String
                .format(EditEventCommand.MESSAGE_EDIT_SUCCESS, Messages.formatEvent(editedEvent));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setEvent(model.getFilteredEventList().get(0), editedEvent);

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
                .withPerson(model.getFilteredEventList().get(0).getNames())
                .withGroups(model.getFilteredEventList().get(0).getGroups()).build();

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(editedEvent).build();
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT, descriptor);

        String expectedMessage = String
                .format(EditEventCommand.MESSAGE_EDIT_SUCCESS, Messages.formatEvent(expectedEvent));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setEvent(model.getFilteredEventList().get(0), editedEvent);

        CommandResult result = editEventCommand.execute(model);
        assertEquals(new CommandResult(expectedMessage), result);
        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() throws Exception {
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT, new EditEventDescriptor());
        Event editedEvent = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());

        String expectedMessage = String
                .format(EditEventCommand.MESSAGE_EDIT_SUCCESS, Messages.formatEvent(editedEvent));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        CommandResult result = editEventCommand.execute(model);
        assertEquals(new CommandResult(expectedMessage), result);
        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_invalidIndex() {
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_EVENT_OUT_OF_BOUNDS, new EditEventDescriptor());
        assertThrows(CommandException.class, () -> editEventCommand.execute(model));
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
                .withPerson(model.getFilteredEventList().get(0).getNames())
                .withGroups("friends").build();

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(editedEvent).build();
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT, descriptor);

        String expectedMessage = String
                .format(EditEventCommand.MESSAGE_EDIT_SUCCESS, Messages.formatEvent(expectedEvent));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setEvent(model.getFilteredEventList().get(0), expectedEvent);

        CommandResult result = editEventCommand.execute(model);
        assertEquals(new CommandResult(expectedMessage), result);
        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_assignNames_failure() throws Exception {
        Event editedEvent = new MeetingBuilder()
                .withEventDate("2050-10-10")
                .withEventStartTime("1400")
                .withEventEndTime("1501").build();

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(editedEvent).build();
        descriptor.setPersonNames(new HashSet<>(Set.of(new Name("Alice Pauline incorrect"))));
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_TYPICAL_PERSON_EVENT, descriptor);
        assertThrows(CommandException.class, () -> editEventCommand.execute(model));
    }

    @Test
    public void execute_unassignNames_success() throws Exception {
        Event eventToEdit = new MeetingBuilder()
                .withEventDate("2050-10-10")
                .withEventStartTime("1400")
                .withEventEndTime("1500")
                .withPerson("Alice Pauline", "Benson Meier")
                .withGroups("friends").build();

        // Making sure that the event that we are about to test is consistent
        Model targetModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        targetModel
                .setEvent(targetModel.getFilteredEventList().get(INDEX_TYPICAL_PERSON_EVENT.getZeroBased()),
                        eventToEdit);

        // edited event where we are trying to unassign Alice Pauline
        Event editedEvent = new MeetingBuilder()
                .withEventDate("2050-10-10")
                .withEventStartTime("1400")
                .withEventEndTime("1500")
                .withPerson("Alice Pauline", "Benson Meier")
                .withGroups("friends").build();

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(editedEvent).build();
        descriptor.setUnassignPersons(new HashSet<>(Set.of(new Name("Alice Pauline"))));
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_TYPICAL_PERSON_EVENT, descriptor);

        Event expectedEvent = new MeetingBuilder()
                .withEventDate("2050-10-10")
                .withEventStartTime("1400")
                .withEventEndTime("1500")
                .withPerson("Benson Meier").build();

        Model expectedModel = new ModelManager(new AddressBook(targetModel.getAddressBook()), new UserPrefs());
        expectedModel
                .setEvent(targetModel.getFilteredEventList().get(INDEX_TYPICAL_PERSON_EVENT.getZeroBased()),
                        expectedEvent);

        editEventCommand.execute(targetModel);
        assertEquals(expectedModel, targetModel);
    }

    @Test
    public void execute_unassignNames_failure() throws Exception {
        Event editedEvent = new MeetingBuilder()
                .withEventDate("2050-10-10")
                .withEventStartTime("1400")
                .withEventEndTime("1500").build();

        EditEventDescriptor descriptor1 = new EditEventDescriptorBuilder(editedEvent).build();
        descriptor1.setUnassignPersons(new HashSet<>(Set.of(new Name("Alice Pauline Incorrect"))));
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_TYPICAL_PERSON_EVENT, descriptor1);
        assertThrows(CommandException.class, () -> editEventCommand.execute(model));

        EditEventDescriptor descriptor2 = new EditEventDescriptorBuilder(editedEvent).build();
        descriptor2.setUnassignPersons(new HashSet<>(Set.of(new Name("Carl Kurz"))));
        EditEventCommand editEventCommand2 = new EditEventCommand(INDEX_TYPICAL_PERSON_EVENT, descriptor2);
        assertThrows(CommandException.class, () -> editEventCommand2.execute(model));
    }

    @Test
    public void execute_assignGroups_failure() throws Exception {
        Event editedEvent = new MeetingBuilder()
                .withEventDate("2050-10-10")
                .withEventStartTime("1401")
                .withEventEndTime("1500").build();

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(editedEvent).build();
        descriptor.setGroups(new HashSet<>(Set.of(new Group("friendsIncorrect"))));
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_TYPICAL_GROUP_EVENT, descriptor);
        assertThrows(CommandException.class, () -> editEventCommand.execute(model));
    }

    @Test
    public void execute_unassignGroups_success() throws Exception {
        Event editedEvent = new MeetingBuilder()
                .withEventDate("2050-10-10")
                .withEventStartTime("1400")
                .withEventEndTime("1500").build();

        Event expectedEvent = new MeetingBuilder()
                .withEventDate("2050-10-10")
                .withEventStartTime("1400")
                .withEventEndTime("1500")
                .withGroups("friends").build();

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(editedEvent).build();
        descriptor.setUnassignGroups(new HashSet<>(Set.of(new Group("owesMoney"))));
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_TYPICAL_GROUP_EVENT, descriptor);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setEvent(model.getFilteredEventList()
                .get(INDEX_TYPICAL_GROUP_EVENT.getZeroBased()), expectedEvent);

        CommandResult result = editEventCommand.execute(model);
        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_unassignGroups_failure() throws Exception {
        Event editedEvent = new MeetingBuilder()
                .withEventDate("2050-10-10")
                .withEventStartTime("1400")
                .withEventEndTime("1500").build();

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(editedEvent).build();
        descriptor.setUnassignGroups(new HashSet<>(Set.of(new Group("owesMoneyIncorrect"))));
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_TYPICAL_GROUP_EVENT, descriptor);
        assertThrows(CommandException.class, () -> editEventCommand.execute(model));
    }

    @Test
    public void equals_success() throws ParseException {
        Event editedEvent = new MeetingBuilder()
                .withEventDate("2050-10-10")
                .withEventStartTime("1400")
                .withEventEndTime("1500")
                .withPerson("Alice Pauline", "Benson Meier")
                .withGroups("friends").build();

        EditEventDescriptor descriptor1 = new EditEventDescriptorBuilder(editedEvent).build();
        EditEventDescriptor descriptor2 = new EditEventDescriptorBuilder(editedEvent).build();
        EditEventCommand editEventCommand1 = new EditEventCommand(INDEX_FIRST_EVENT, descriptor1);
        EditEventCommand editEventCommand2 = new EditEventCommand(INDEX_FIRST_EVENT, descriptor2);

        assertTrue(editEventCommand1.equals(editEventCommand2));
        assertTrue(editEventCommand1.equals(editEventCommand1));
        assertFalse(editEventCommand1.equals(descriptor1));
    }

    @Test
    public void equals_failure() throws ParseException {
        Event editedEvent1 = new MeetingBuilder()
                .withEventDate("2050-10-10")
                .withEventStartTime("1400")
                .withEventEndTime("1500")
                .withPerson("Alice Pauline", "Benson Meier")
                .withGroups("friends").build();

        Event editedEvent2 = new MeetingBuilder()
                .withEventDate("2050-10-10")
                .withEventStartTime("1400")
                .withEventEndTime("1501")
                .withPerson("Alice Pauline", "Benson Meier")
                .withGroups("friends").build();

        EditEventDescriptor descriptor1 = new EditEventDescriptorBuilder(editedEvent1).build();
        EditEventDescriptor descriptor2 = new EditEventDescriptorBuilder(editedEvent2).build();
        EditEventCommand editEventCommand1 = new EditEventCommand(INDEX_FIRST_EVENT, descriptor1);
        EditEventCommand editEventCommand2 = new EditEventCommand(INDEX_FIRST_EVENT, descriptor2);

        assertFalse(editEventCommand1.equals(editEventCommand2));
    }

    @Test
    public void descriptor_toString() throws Exception {
        Event editedEvent1 = new MeetingBuilder()
                .withEventDate("2050-10-10")
                .withEventStartTime("1400")
                .withEventEndTime("1500")
                .withPerson("Alice Pauline", "Benson Meier")
                .withGroups("friends").build();
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(editedEvent1).build();
        String expected = EditEventDescriptor.class.getCanonicalName() + "{name=TP MEETING TEST, "
                + "date=2050-10-10, " + "start time=1400, " + "end time=1500, "
                + "assign persons=[Alice Pauline, Benson Meier], " + "unassign persons=null, "
                + "assign groups=[[friends]], " + "unassign groups=null}";
        assertEquals(expected, descriptor.toString());
    }

    @Test
    public void descriptor_isAnyFieldEdited() throws Exception {
        EditEventDescriptor descriptor = new EditEventDescriptor();
        assertFalse(descriptor.isAnyFieldEdited());

        Event editedEvent1 = new MeetingBuilder()
                .withEventDate("2050-10-10")
                .withEventStartTime("1400")
                .withEventEndTime("1500")
                .withPerson("Alice Pauline", "Benson Meier")
                .withGroups("friends").build();
        EditEventDescriptor descriptorEdited = new EditEventDescriptorBuilder(editedEvent1).build();
        assertTrue(descriptorEdited.isAnyFieldEdited());
    }

    @Test
    public void equalEditEventDescriptorTest() {
        EditEventDescriptor descriptor1 = new EditEventDescriptor();
        assertTrue(descriptor1.equals(descriptor1));
    }

    @Test
    public void equalEditEventDescriptorNullTest() {
        EditEventDescriptor descriptor1 = new EditEventDescriptor();
        assertFalse(descriptor1.equals(null));
    }
}
