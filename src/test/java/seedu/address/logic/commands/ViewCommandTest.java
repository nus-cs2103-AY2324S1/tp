package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class ViewCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void executeValidIndex_success() {
        Person personToView = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_PERSON_SUCCESS, Messages.format(personToView));
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        expectedModel.setPerson(model.getFilteredPersonList().get(0), personToView);
        expectedModel.updateRecordList(personToView);
        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeInvalidIndex_throwsCommandException() {
        Index indexOutOfBounds = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ViewCommand viewCommand = new ViewCommand(indexOutOfBounds);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexRecordList_success() {

        Person personToView = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_PERSON_SUCCESS,
                Messages.format(personToView));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        try {
            viewCommand.execute(model);
            viewCommand.execute(expectedModel);
        } catch (Exception ignore) {
            return;
        }

        sameRecordList(model, expectedModel);
        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexSamePerson_success() {

        Person personToView = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_PERSON_SUCCESS,
                Messages.format(personToView));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        try {
            viewCommand.execute(model);
            viewCommand.execute(expectedModel);
        } catch (Exception ignore) {
            return;
        }

        samePersonViewed(model, expectedModel);
        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {

        ViewCommand viewCommand1 = new ViewCommand(INDEX_FIRST_PERSON);
        ViewCommand viewCommand2 = new ViewCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(viewCommand1.equals(viewCommand1));

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(INDEX_FIRST_PERSON);
        assertTrue(viewCommand1.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewCommand1.equals(1));

        // null -> returns false
        assertFalse(viewCommand1.equals(null));

        // different person -> returns false
        assertFalse(viewCommand1.equals(viewCommand2));
    }

    private void sameRecordList(Model model1, Model model2) {
        assertTrue(model1.getRecordList().equals(model2.getRecordList()));
    }

    private void samePersonViewed(Model model1, Model model2) {
        assertTrue(model1.getPersonBeingViewed().equals(model2.getPersonBeingViewed()));
    }
}
