package seedu.address.logic.commands.events;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonsBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.EventsBook;
import seedu.address.model.FinancesBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddEventCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPersonsBook(), new EventsBook(), new FinancesBook(), new UserPrefs());
    }

    @Test
    public void execute_newEvent_success() throws ParseException {
        Event validEvent = new EventBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new EventsBook(), new FinancesBook(),
                new UserPrefs());
        expectedModel.addEvent(validEvent);

        assertCommandSuccess(new AddEventCommand(validEvent), model,
                String.format(AddEventCommand.MESSAGE_SUCCESS, Messages.format(validEvent)),
                expectedModel);
    }

    @Test
    public void execute_personDoesNotExist_throwsCommandException() throws ParseException {
        Person invalidClient = new PersonBuilder().withName("Daniel").build();
        Event eventWithInvalidClients = new EventBuilder().withClient(invalidClient).build();
        assertCommandFailure(new AddEventCommand(eventWithInvalidClients), model,
                Messages.MESSAGE_CLIENT_DOES_NOT_EXIST);
    }
}
