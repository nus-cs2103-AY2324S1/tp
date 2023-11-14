package seedu.address.logic.commands.viewcommands;

import static seedu.address.logic.commands.CommandTestUtil.assertPersonCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code ViewPersonCommand}.
 */
public class ViewPersonCommandIntegrationTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_viewPersonAtIndex1_success() {
        List<Person> listToCheck = expectedModel.getFilteredPersonList();
        Person personToView = listToCheck.get(0);
        CommandTestUtil.assertDisplayableCommandSuccess(new ViewPersonCommand(Index.fromOneBased(1)), model,
                String.format(ViewPersonCommand.MESSAGE_SUCCESS, 1), expectedModel, personToView);
    }

    @Test
    public void execute_viewPersonAtIndexExceedsListSize_exceptionThrown() {
        assertPersonCommandFailure(new ViewPersonCommand(Index.fromOneBased(99)), model,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
