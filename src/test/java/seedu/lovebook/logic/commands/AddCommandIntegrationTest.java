package seedu.lovebook.logic.commands;

import static seedu.lovebook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.lovebook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.lovebook.testutil.TypicalDatePrefs.getTypicalDatePrefs;
import static seedu.lovebook.testutil.TypicalPersons.getTypicalLoveBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.lovebook.logic.Messages;
import seedu.lovebook.model.Model;
import seedu.lovebook.model.ModelManager;
import seedu.lovebook.model.UserPrefs;
import seedu.lovebook.model.person.Date;
import seedu.lovebook.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalLoveBook(), new UserPrefs(), getTypicalDatePrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Date validDate = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getLoveBook(), new UserPrefs(), model.getDatePrefs());
        expectedModel.addPerson(validDate);

        assertCommandSuccess(new AddCommand(validDate), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validDate)),
                expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Date dateInList = model.getLoveBook().getPersonList().get(0);
        assertCommandFailure(new AddCommand(dateInList), model,
                AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
