package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Date;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Date validDate = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validDate);

        assertCommandSuccess(new AddCommand(validDate), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validDate)),
                expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Date dateInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddCommand(dateInList), model,
                AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
