package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;


public class UnhideAllCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void execute_unhideAll_unhideAllSuccessful() throws CommandException {
        Person hiddenPersonOne = new PersonBuilder(model.getFilteredPersonList().get(0))
                .withHidden(true).build();
        Person hiddenPersonTwo = new PersonBuilder(model.getFilteredPersonList().get(1))
                .withHidden(true).build();
        model.setPerson(model.getFilteredPersonList().get(0), hiddenPersonOne);
        model.setPerson(model.getFilteredPersonList().get(1), hiddenPersonTwo);

        CommandResult commandResult = new UnhideAllCommand().execute(model);

        String expectedMessage = UnhideAllCommand.MESSAGE_SUCCESS;

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertTrue(!model.getFilteredPersonList().get(0).getIsHidden().value
                && !model.getFilteredPersonList().get(1).getIsHidden().value);
    }

}
