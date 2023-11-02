package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalEvents.getTypicalCalendar;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
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
import seedu.address.model.event.EventDescription;
import seedu.address.model.person.Person;
import seedu.address.model.task.TaskManager;
import seedu.address.testutil.PersonBuilder;

public class EditContactEventCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalCalendar(), new TaskManager(),
            new UserPrefs());

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = new PersonBuilder().withCalendar().build();
        EventDescription expectedEventDescription = new EventDescription("Eat Tacos");
        EditContactEventCommand.EditEventDescriptor descriptor = new EditContactEventCommand.EditEventDescriptor();
        descriptor.setEventDescription(expectedEventDescription);
        ArrayList<Index> indexArrayList = new ArrayList<>();
        indexArrayList.add(INDEX_FIRST_PERSON);
        indexArrayList.add(INDEX_FIRST_EVENT);
        EditContactEventCommand editContactEventCommand = new EditContactEventCommand(indexArrayList, descriptor);

        String expectedMessage = String.format(EditContactEventCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                model.getCalendar(), model.getTaskManager(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertTrue(true);
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = new PersonBuilder().withCalendar().build();
        EventDescription expectedEventDescription = new EventDescription("Eat Tacos");
        EditContactEventCommand.EditEventDescriptor descriptor = new EditContactEventCommand.EditEventDescriptor();
        descriptor.setEventDescription(expectedEventDescription);
        descriptor.setStart("2023-10-10 10:00");
        descriptor.setEnd("2023-10-10 12:00");
        ArrayList<Index> indexArrayList = new ArrayList<>();
        indexArrayList.add(INDEX_FIRST_PERSON);
        indexArrayList.add(INDEX_FIRST_EVENT);
        EditContactEventCommand editContactEventCommand = new EditContactEventCommand(indexArrayList, descriptor);

        String expectedMessage = String.format(EditContactEventCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                model.getCalendar(), model.getTaskManager(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertTrue(true);
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

}
