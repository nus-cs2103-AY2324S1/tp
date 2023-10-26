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
public class StarCommandTest {

    private Model model = new ModelManager(getTypicalLoveBook(), new UserPrefs(), getTypicalDatePrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Date dateToStar = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        StarCommand starCommand = new StarCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(StarCommand.MESSAGE_STAR_PERSON_SUCCESS,
                Messages.format(dateToStar));

        ModelManager expectedModel = new ModelManager(model.getLoveBook(), new UserPrefs(), model.getDatePrefs());
        Star star = new Star("true");
        Date starredDate = new Date(dateToStar.getName(), dateToStar.getAge(), dateToStar.getGender(),
                dateToStar.getHeight(), dateToStar.getIncome(), dateToStar.getHoroscope(), star);
        expectedModel.setPerson(dateToStar, starredDate);
        assertCommandSuccess(starCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        StarCommand starCommand = new StarCommand(outOfBoundIndex);

        assertCommandFailure(starCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Date dateToStar = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        StarCommand starCommand = new StarCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(StarCommand.MESSAGE_STAR_PERSON_SUCCESS,
                Messages.format(dateToStar));

        Model expectedModel = new ModelManager(model.getLoveBook(), new UserPrefs(), model.getDatePrefs());
        Star star = new Star("true");
        Date starredDate = new Date(dateToStar.getName(), dateToStar.getAge(), dateToStar.getGender(),
                dateToStar.getHeight(), dateToStar.getIncome(), dateToStar.getHoroscope(), star);
        expectedModel.setPerson(dateToStar, starredDate);

        assertCommandSuccess(starCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of lovebook book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLoveBook().getPersonList().size());

        StarCommand starCommand = new StarCommand(outOfBoundIndex);

        assertCommandFailure(starCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test public void execute_dateIsAlreadyStarred_throwsCommandException() {
        StarCommand starCommand = new StarCommand(INDEX_FIRST_PERSON);
        try {
            starCommand.execute(model);
        } catch (CommandException e) {
            throw new RuntimeException(e);
        }
        assertThrows(CommandException.class, "Date has already been starred", ()
                -> starCommand.execute(model));
    }
}
