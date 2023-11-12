package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.getTypicalCalendar;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INVALID_INDEX;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDescription;
import seedu.address.model.event.EventPeriod;
import seedu.address.model.person.Person;
import seedu.address.model.task.TaskManager;
import seedu.address.testutil.EditEventDescriptorBuilder;

public class EditContactEventCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalCalendar(), new TaskManager(),
            new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        int personIdx = 0;
        Person editedPerson = model.getFilteredPersonList().get(personIdx);
        EventDescription expectedEventDescription = new EventDescription("Eat Tacos");
        EditContactEventCommand.EditEventDescriptor descriptor = new EditContactEventCommand.EditEventDescriptor();
        descriptor.setEventDescription(expectedEventDescription);
        String startTime = "2023-10-10 10:00";
        String endTime = "2023-10-10 12:00";
        descriptor.setStart(startTime);
        descriptor.setEnd(endTime);
        ArrayList<Index> indexArrayList = new ArrayList<>();
        indexArrayList.add(INDEX_FIRST_PERSON);
        indexArrayList.add(INDEX_FIRST_EVENT);
        EditContactEventCommand editContactEventCommand = new EditContactEventCommand(indexArrayList, descriptor);
        EventPeriod newEventPeriod = new EventPeriod(startTime, endTime);

        Event editEvent = new Event(expectedEventDescription, newEventPeriod);
        editedPerson.getCalendar().getEventList().set(personIdx, editEvent);
        String expectedMessage = String.format(EditContactEventCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                Messages.formatCalendar(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                model.getCalendar(), model.getTaskManager(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(personIdx), editedPerson);

        assertCommandSuccess(editContactEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void editEventDescriptor_success() {
        EditContactEventCommand.EditEventDescriptor editEventDescriptor =
                new EditContactEventCommand.EditEventDescriptor();
        String startDateTimeString = "2023-10-10 10:00";
        String endDateTimeString = "2023-10-10 12:00";
        String desc = "Eat taco";
        editEventDescriptor.setStart(startDateTimeString);
        editEventDescriptor.setEnd(endDateTimeString);
        editEventDescriptor.setEventDescription(new EventDescription(desc));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeString, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeString, formatter);

        assertEquals(editEventDescriptor.getStart().get(), startDateTime);
        assertEquals(editEventDescriptor.getEnd().get(), endDateTime);
        assertEquals(editEventDescriptor.getEventDescription().get().getDescription(), desc);
    }

    @Test
    public void execute_eventIndexOutOfBoundsEvent_failure() {
        EditContactEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder().build();
        ArrayList<Index> indexArrayList = new ArrayList<>();
        indexArrayList.add(INDEX_FIRST_PERSON);
        indexArrayList.add(INVALID_INDEX);
        EditContactEventCommand editContactEventCommand = new EditContactEventCommand(indexArrayList, descriptor);

        assertCommandFailure(editContactEventCommand, model, EditContactEventCommand.INVALID_EVENT_INDEX);
    }

    @Test
    public void execute_personIndexOutOfBoundsEvent_failure() {
        EditContactEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder().build();
        ArrayList<Index> indexArrayList = new ArrayList<>();
        indexArrayList.add(INVALID_INDEX);
        indexArrayList.add(INDEX_FIRST_EVENT);
        EditContactEventCommand editContactEventCommand = new EditContactEventCommand(indexArrayList, descriptor);

        assertCommandFailure(editContactEventCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_editEventDescriptorEquals_success() {
        String startTimeString = "2023-10-10 10:10";
        String endTimeString = "2023-11-11 11:11";
        EditContactEventCommand.EditEventDescriptor descriptor1 = new EditEventDescriptorBuilder()
                .withEventDescription("Sleep").withEventStartTime(startTimeString)
                .withEventEndTime(endTimeString).build();
        EditContactEventCommand.EditEventDescriptor descriptor2 = new EditEventDescriptorBuilder()
                .withEventDescription("Sleep").withEventStartTime(startTimeString)
                .withEventEndTime(endTimeString).build();
        assertEquals(descriptor1, descriptor2);
        assertTrue(descriptor1.equals(descriptor1));
    }

    @Test
    public void execute_editEventDescriptorNotEquals_failure() {
        String startTimeString = "2023-10-10 10:10";
        String endTimeString = "2023-11-11 11:11";
        EditContactEventCommand.EditEventDescriptor descriptor1 = new EditEventDescriptorBuilder()
                .withEventDescription("Nap").withEventStartTime(startTimeString)
                .withEventEndTime(endTimeString).build();
        EditContactEventCommand.EditEventDescriptor descriptor2 = new EditEventDescriptorBuilder()
                .withEventDescription("Sleep").withEventStartTime(startTimeString)
                .withEventEndTime(endTimeString).build();
        assertFalse(descriptor1.equals(descriptor2));
        assertFalse(descriptor1.equals(null));
    }

    @Test
    public void execute_editEventDescriptorNotEquals_failure2() {
        String desc = "Nap";
        String startTimeString1 = "2023-10-10 10:10";
        String endTimeString1 = "2023-11-11 11:11";
        String startTimeString2 = "2023-10-11 10:10";
        EditContactEventCommand.EditEventDescriptor descriptor1 = new EditEventDescriptorBuilder()
                .withEventDescription(desc).withEventStartTime(startTimeString1)
                .withEventEndTime(endTimeString1).build();
        EditContactEventCommand.EditEventDescriptor descriptor2 = new EditEventDescriptorBuilder()
                .withEventDescription(desc).withEventStartTime(startTimeString2)
                .withEventEndTime(endTimeString1).build();
        assertFalse(descriptor1.equals(descriptor2));
    }

    @Test
    public void execute_editEventDescriptorToString_success() {
        String desc = "Nap";
        String startTimeString1 = "2023-10-10 10:10";
        String endTimeString1 = "2023-11-11 11:11";
        EditContactEventCommand.EditEventDescriptor descriptor1 = new EditContactEventCommand.EditEventDescriptor();
        descriptor1.setEventDescription(new EventDescription(desc));
        descriptor1.setEventPeriod(new EventPeriod(startTimeString1, endTimeString1));
        String expected = "{" + "eventDescription=" + desc + ", start=" + startTimeString1
                + ", end=" + endTimeString1 + "}";
        assertEquals(descriptor1.toString(), expected);
    }

    @Test
    public void execute_equalTo_success() {
        EventDescription expectedEventDescription = new EventDescription("Eat Tacos");
        EditContactEventCommand.EditEventDescriptor descriptor = new EditContactEventCommand.EditEventDescriptor();
        descriptor.setEventDescription(expectedEventDescription);
        String startTime = "2023-10-10 10:00";
        String endTime = "2023-10-10 12:00";
        descriptor.setStart(startTime);
        descriptor.setEnd(endTime);
        ArrayList<Index> indexArrayList = new ArrayList<>();
        indexArrayList.add(INDEX_FIRST_PERSON);
        indexArrayList.add(INDEX_FIRST_EVENT);
        ArrayList<Index> indexArrayList2 = new ArrayList<>();
        indexArrayList2.add(INDEX_SECOND_PERSON);
        indexArrayList2.add(INDEX_FIRST_EVENT);
        EditContactEventCommand editContactEventCommand1 = new EditContactEventCommand(indexArrayList, descriptor);
        EditContactEventCommand editContactEventCommand2 = new EditContactEventCommand(indexArrayList2, descriptor);

        assertTrue(editContactEventCommand1.equals(editContactEventCommand1));
        assertFalse(editContactEventCommand1.equals(null));
        assertFalse(editContactEventCommand1.equals(editContactEventCommand2));
    }

}
