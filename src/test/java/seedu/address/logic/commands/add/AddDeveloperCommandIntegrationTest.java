package seedu.address.logic.commands.add;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDevelopers.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.developer.Developer;
import seedu.address.testutil.DeveloperBuilder;


/**
 * Contains integration tests (interaction with the Model) for {@code AddDeveloperCommand}.
 */
public class AddDeveloperCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Developer validDeveloper = new DeveloperBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addDeveloper(validDeveloper);

        assertCommandSuccess(new AddDeveloperCommand(validDeveloper), model,
                String.format(AddDeveloperCommand.MESSAGE_SUCCESS, Messages.format(validDeveloper)),
                expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Developer developerInList = model.getAddressBook().getDeveloperList().get(0);
        assertCommandFailure(new AddDeveloperCommand(developerInList), model,
                AddDeveloperCommand.MESSAGE_DUPLICATE_DEVELOPER);
    }

}
