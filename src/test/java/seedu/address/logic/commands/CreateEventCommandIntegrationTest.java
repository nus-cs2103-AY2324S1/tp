package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.testutil.EventBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code CreateEventCommand}.
 */
public class CreateEventCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newEvent_success() {
        Event validEvent = new EventBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.createEvent(validEvent);

        assertCommandSuccess(new CreateEventCommand(validEvent), model,
                String.format(CreateEventCommand.MESSAGE_SUCCESS, Messages.format(validEvent)),
                expectedModel);
    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() {
        Event memberInList = model.getAddressBook().getEventList().get(0);
        assertCommandFailure(new CreateEventCommand(memberInList), model,
                CreateEventCommand.MESSAGE_DUPLICATE_EVENT);
    }

}
