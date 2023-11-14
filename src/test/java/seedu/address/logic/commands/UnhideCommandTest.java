package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;


public class UnhideCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_personUnhidden_unhideSuccessful() throws CommandException {
        Person hiddenPerson = new PersonBuilder().withHidden(true).build();
        model.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()), hiddenPerson);
        CommandResult commandResult = new UnhideCommand(INDEX_FIRST_PERSON).execute(model);

        String expectedMessage = String.format(UnhideCommand.MESSAGE_UNHIDE_APPLICANT_SUCCESS,
                Messages.format(hiddenPerson));

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertFalse(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()).getIsHidden().value);
    }

    @Test
    public void execute_indexOutOfRange_throwsCommandException() {
        Index outOfRangeIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        UnhideCommand unhideCommand = new UnhideCommand(outOfRangeIndex);
        assertCommandFailure(unhideCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        HideCommand hideFirstCommand = new HideCommand(INDEX_FIRST_PERSON);
        HideCommand hideSecondCommand = new HideCommand(INDEX_SECOND_PERSON);

        assertTrue(hideFirstCommand.equals(hideFirstCommand));

        HideCommand hideFirstCommandCopy = new HideCommand(INDEX_FIRST_PERSON);
        assertTrue(hideFirstCommand.equals(hideFirstCommandCopy));

        assertFalse(hideFirstCommand.equals(1));

        assertFalse(hideFirstCommand.equals(null));

        assertFalse(hideFirstCommand.equals(hideSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        HideCommand hideCommand = new HideCommand(targetIndex);
        String expected = HideCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, hideCommand.toString());
    }
}
