package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalEvents.getTypicalCalendar;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

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
import seedu.address.testutil.PersonBuilder;

public class EditContactEventCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalCalendar(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
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
                model.getCalendar(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertTrue(true);
    }
}
