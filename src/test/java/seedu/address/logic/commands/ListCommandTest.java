package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.parser.CliSyntax.FIELD_BOOKMARKED;
import static seedu.address.logic.parser.CliSyntax.FIELD_HIDDEN;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listHiddenPersons_listHiddenSuccessful() {
        Person hiddenPerson = new PersonBuilder().withHidden(true).build();
        model.setPerson(model.getFilteredPersonList().get(0), hiddenPerson);
        CommandResult commandResult = new ListCommand(FIELD_HIDDEN).execute(model);
        String expectedMessage = ListCommand.MESSAGE_SUCCESS;
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(model.getFilteredPersonList().get(0), hiddenPerson);
    }

    @Test
    public void execute_listBookmarkedPersons_listBookmarkedSuccessful() {
        Person bookmarkedPerson = new PersonBuilder().withBookmark(true).build();
        model.setPerson(model.getFilteredPersonList().get(0), bookmarkedPerson);
        CommandResult commandResult = new ListCommand(FIELD_BOOKMARKED).execute(model);
        String expectedMessage = ListCommand.MESSAGE_SUCCESS;
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(model.getFilteredPersonList().get(0), bookmarkedPerson);
    }

    @Test
    public void equals() {
        ListCommand listFirstCommand = new ListCommand();
        ListCommand listSecondCommand = new ListCommand(FIELD_HIDDEN);

        assertTrue(listFirstCommand.equals(listFirstCommand));

        ListCommand listFirstCommandCopy = new ListCommand();
        assertTrue(listFirstCommand.equals(listFirstCommandCopy));

        assertFalse(listFirstCommand.equals(1));

        assertFalse(listFirstCommand.equals(null));

        assertFalse(listFirstCommand.equals(listSecondCommand));
    }

    @Test
    public void toStringMethod() {
        ListCommand listCommand = new ListCommand();
        String expected = ListCommand.class.getCanonicalName() + "{fieldName=" + "}";
        assertEquals(expected, listCommand.toString());
    }
}
