package seedu.lovebook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.lovebook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.lovebook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.lovebook.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.lovebook.testutil.Assert.assertThrows;
import static seedu.lovebook.testutil.TypicalDatePrefs.getTypicalDatePrefs;
import static seedu.lovebook.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.lovebook.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.lovebook.testutil.TypicalPersons.getTypicalLoveBook;

import org.junit.jupiter.api.Test;

import seedu.lovebook.commons.core.index.Index;
import seedu.lovebook.logic.Messages;
import seedu.lovebook.logic.commands.exceptions.CommandException;
import seedu.lovebook.model.Model;
import seedu.lovebook.model.ModelManager;
import seedu.lovebook.model.UserPrefs;
import seedu.lovebook.model.date.Date;
import seedu.lovebook.model.date.Star;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code StarCommand}.
 */
public class UnstarCommandTest {

    private Model model = new ModelManager(getTypicalLoveBook(), new UserPrefs(), getTypicalDatePrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        //Select date from model
        Date dateToUnstar = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        //set up expected model
        String expectedMessage = String.format(UnstarCommand.MESSAGE_STAR_PERSON_SUCCESS,
                Messages.format(dateToUnstar));
        ModelManager expectedModel = new ModelManager(model.getLoveBook(), new UserPrefs(), model.getDatePrefs());
        //initialise model
        Star star = new Star("true");
        Date starredDate = new Date(dateToUnstar.getName(), dateToUnstar.getAge(), dateToUnstar.getGender(),
                dateToUnstar.getHeight(), dateToUnstar.getIncome(), dateToUnstar.getHoroscope(), star);
        model.setPerson(dateToUnstar, starredDate);
        UnstarCommand unstarCommand = new UnstarCommand(INDEX_FIRST_PERSON);
        assertCommandSuccess(unstarCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        UnstarCommand unstarCommand = new UnstarCommand(outOfBoundIndex);

        assertCommandFailure(unstarCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Date dateToUnstar = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String expectedMessage = String.format(UnstarCommand.MESSAGE_STAR_PERSON_SUCCESS,
                Messages.format(dateToUnstar));

        Model expectedModel = new ModelManager(model.getLoveBook(), new UserPrefs(), model.getDatePrefs());
        Star star = new Star("true");
        Date unstarredDate = new Date(dateToUnstar.getName(), dateToUnstar.getAge(), dateToUnstar.getGender(),
                dateToUnstar.getHeight(), dateToUnstar.getIncome(), dateToUnstar.getHoroscope(), star);
        model.setPerson(dateToUnstar, unstarredDate);
        UnstarCommand unstarCommand = new UnstarCommand(INDEX_FIRST_PERSON);
        assertCommandSuccess(unstarCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of lovebook book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLoveBook().getPersonList().size());

        UnstarCommand unstarCommand = new UnstarCommand(outOfBoundIndex);

        assertCommandFailure(unstarCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test public void execute_dateIsAlreadyUnstarred_throwsCommandException() {
        UnstarCommand unstarCommand = new UnstarCommand(INDEX_FIRST_PERSON);
        assertThrows(CommandException.class, "Date has already been unstarred", ()
                -> unstarCommand.execute(model));
    }
}
