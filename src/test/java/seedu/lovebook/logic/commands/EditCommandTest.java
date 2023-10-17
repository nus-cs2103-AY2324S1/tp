package seedu.lovebook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.lovebook.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.lovebook.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_AGE_BOB;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.lovebook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.lovebook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.lovebook.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.lovebook.testutil.TypicalDatePrefs.getTypicalDatePrefs;
import static seedu.lovebook.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.lovebook.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.lovebook.testutil.TypicalPersons.getTypicalLoveBook;

import org.junit.jupiter.api.Test;

import seedu.lovebook.commons.core.index.Index;
import seedu.lovebook.logic.Messages;
import seedu.lovebook.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.lovebook.model.LoveBook;
import seedu.lovebook.model.Model;
import seedu.lovebook.model.ModelManager;
import seedu.lovebook.model.UserPrefs;
import seedu.lovebook.model.person.Date;
import seedu.lovebook.testutil.EditPersonDescriptorBuilder;
import seedu.lovebook.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalLoveBook(), new UserPrefs(), getTypicalDatePrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Date editedDate = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedDate).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedDate));

        Model expectedModel = new ModelManager(new LoveBook(model.getLoveBook()), new UserPrefs(),
                model.getDatePrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedDate);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Date lastDate = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastDate);
        Date editedDate = personInList.withName(VALID_NAME_BOB).withAge(VALID_AGE_BOB).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withAge(VALID_AGE_BOB).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedDate));

        Model expectedModel = new ModelManager(new LoveBook(model.getLoveBook()), new UserPrefs(),
                model.getDatePrefs());
        expectedModel.setPerson(lastDate, editedDate);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditPersonDescriptor());
        Date editedDate = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedDate));

        Model expectedModel = new ModelManager(new LoveBook(model.getLoveBook()), new UserPrefs(),
                model.getDatePrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Date dateInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Date editedDate = new PersonBuilder(dateInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedDate));

        Model expectedModel = new ModelManager(new LoveBook(model.getLoveBook()), new UserPrefs(),
                model.getDatePrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedDate);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Date firstDate = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstDate).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit date in filtered list into a duplicate in lovebook book
        Date dateInList = model.getLoveBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder(dateInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of lovebook book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of lovebook book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLoveBook().getPersonList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_BOB)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        EditCommand editCommand = new EditCommand(index, editPersonDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{index=" + index + ", editPersonDescriptor="
                + editPersonDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}
