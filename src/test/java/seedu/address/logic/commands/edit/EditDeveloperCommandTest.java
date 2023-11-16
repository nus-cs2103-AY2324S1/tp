package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CALEB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_2_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_3_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertDeveloperCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showDeveloperAtIndex;
import static seedu.address.testutil.TypicalDevelopers.getTypicalAddressBook;
import static seedu.address.testutil.TypicalDevelopers.getTypicalAddressBookWithProjects;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.developer.Developer;
import seedu.address.testutil.DeveloperBuilder;
import seedu.address.testutil.EditDeveloperDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditDeveloperCommand.
 */
public class EditDeveloperCommandTest {

    private final Model modelNoProjects = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model modelWithProjects = new ModelManager(getTypicalAddressBookWithProjects(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedNoProjectEditUnfilteredListNoProjects_success() {
        Developer editedDeveloper = new DeveloperBuilder().build();
        EditDeveloperCommand.EditDeveloperDescriptor descriptor = new EditDeveloperDescriptorBuilder(editedDeveloper)
                .build();
        EditDeveloperCommand editCommand = new EditDeveloperCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditDeveloperCommand.MESSAGE_EDIT_DEVELOPER_SUCCESS,
                Messages.format(editedDeveloper));

        Model expectedModel = new ModelManager(new AddressBook(modelNoProjects.getAddressBook()), new UserPrefs());
        expectedModel.setDeveloper(modelNoProjects.getFilteredDeveloperList().get(0), editedDeveloper);

        assertDeveloperCommandSuccess(editCommand, modelNoProjects, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allFieldsSpecifiedNoProjectEditUnfilteredListWithProjects_success() {
        Developer editedDeveloper = new DeveloperBuilder().build();
        EditDeveloperCommand.EditDeveloperDescriptor descriptor = new EditDeveloperDescriptorBuilder(editedDeveloper)
                .build();
        EditDeveloperCommand editCommand = new EditDeveloperCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditDeveloperCommand.MESSAGE_EDIT_DEVELOPER_SUCCESS,
                Messages.format(editedDeveloper));

        Model expectedModel = new ModelManager(new AddressBook(modelWithProjects.getAddressBook()), new UserPrefs());
        expectedModel.setDeveloper(modelWithProjects.getFilteredDeveloperList().get(0), editedDeveloper);

        assertDeveloperCommandSuccess(editCommand, modelWithProjects, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedValidProjectEditUnfilteredListWithProjects_success() {
        Index indexLastDeveloper = Index.fromOneBased(modelWithProjects.getFilteredDeveloperList().size());
        Developer lastDeveloper = modelWithProjects.getFilteredDeveloperList().get(indexLastDeveloper.getZeroBased());

        DeveloperBuilder developerInList = new DeveloperBuilder(lastDeveloper);
        Developer editedDeveloper = developerInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withProjects(VALID_PROJECT_3_BOB).build();

        EditDeveloperCommand.EditDeveloperDescriptor descriptor = new EditDeveloperDescriptorBuilder()
                .withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withProjects(VALID_PROJECT_3_BOB).build();
        EditDeveloperCommand editCommand = new EditDeveloperCommand(indexLastDeveloper, descriptor);

        String expectedMessage = String.format(EditDeveloperCommand.MESSAGE_EDIT_DEVELOPER_SUCCESS,
                Messages.format(editedDeveloper));

        Model expectedModel = new ModelManager(new AddressBook(modelWithProjects.getAddressBook()), new UserPrefs());
        expectedModel.setDeveloper(lastDeveloper, editedDeveloper);

        assertDeveloperCommandSuccess(editCommand, modelWithProjects, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedInvalidProjectEditUnfilteredListWithProjects_failure() {
        Index indexLastDeveloper = Index.fromOneBased(modelWithProjects.getFilteredDeveloperList().size());

        EditDeveloperCommand.EditDeveloperDescriptor descriptor = new EditDeveloperDescriptorBuilder()
                .withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withProjects(VALID_PROJECT_2_BOB).build();
        EditDeveloperCommand editCommand = new EditDeveloperCommand(indexLastDeveloper, descriptor);

        String expectedMessage = String.format(Messages.MESSAGE_NONEXISTENT_PROJECT, VALID_PROJECT_2_BOB);

        assertCommandFailure(editCommand, modelWithProjects, expectedMessage);
    }

    @Test
    public void execute_allFieldsSpecifiedValidProjectEditUnfilteredListWithProjects_success() {
        Developer editedDeveloper = new DeveloperBuilder().withProjects(VALID_PROJECT_3_BOB).build();
        EditDeveloperCommand.EditDeveloperDescriptor descriptor = new EditDeveloperDescriptorBuilder(editedDeveloper)
                .build();
        EditDeveloperCommand editCommand = new EditDeveloperCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditDeveloperCommand.MESSAGE_EDIT_DEVELOPER_SUCCESS,
                Messages.format(editedDeveloper));

        Model expectedModel = new ModelManager(new AddressBook(modelWithProjects.getAddressBook()), new UserPrefs());
        expectedModel.setDeveloper(modelWithProjects.getFilteredDeveloperList().get(0), editedDeveloper);

        assertDeveloperCommandSuccess(editCommand, modelWithProjects, expectedMessage, expectedModel);
    }

    // Project needs to be created
    /*
    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditDeveloperCommand editCommand = new EditDeveloperCommand(INDEX_FIRST_PERSON,
                new EditDeveloperCommand.EditDeveloperDescriptor());
        Developer editedDeveloper = model.getFilteredDeveloperList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditDeveloperCommand.MESSAGE_EDIT_DEVELOPER_SUCCESS,
                Messages.format(editedDeveloper));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }*/

    @Test
    public void execute_filteredListValidProjectEditWithProjects_success() {
        showDeveloperAtIndex(modelWithProjects, INDEX_FIRST_PERSON);

        Developer developerInFilteredList = modelWithProjects.getFilteredDeveloperList()
                .get(INDEX_FIRST_PERSON.getZeroBased());
        Developer editedDeveloper = new DeveloperBuilder(developerInFilteredList).withName(VALID_NAME_BOB)
                .withProjects(VALID_PROJECT_3_BOB).build();
        EditDeveloperCommand editCommand = new EditDeveloperCommand(INDEX_FIRST_PERSON,
                new EditDeveloperDescriptorBuilder().withName(VALID_NAME_BOB).withProjects(VALID_PROJECT_3_BOB)
                        .build());

        String expectedMessage = String.format(EditDeveloperCommand.MESSAGE_EDIT_DEVELOPER_SUCCESS,
                Messages.format(editedDeveloper));

        Model expectedModel = new ModelManager(new AddressBook(modelWithProjects.getAddressBook()), new UserPrefs());
        expectedModel.setDeveloper(modelWithProjects.getFilteredDeveloperList().get(0), editedDeveloper);

        assertDeveloperCommandSuccess(editCommand, modelWithProjects, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredListInvalidProjectEditWithProjects_failure() {
        showDeveloperAtIndex(modelWithProjects, INDEX_FIRST_PERSON);

        EditDeveloperCommand editCommand = new EditDeveloperCommand(INDEX_FIRST_PERSON,
                new EditDeveloperDescriptorBuilder().withName(VALID_NAME_BOB).withProjects(VALID_PROJECT_2_BOB)
                        .build());

        String expectedMessage = String.format(Messages.MESSAGE_NONEXISTENT_PROJECT, VALID_PROJECT_2_BOB);

        assertCommandFailure(editCommand, modelWithProjects, expectedMessage);
    }

    @Test
    public void execute_duplicateDeveloperUnfilteredListNoProjects_failure() {
        Developer firstDeveloper = modelNoProjects.getFilteredDeveloperList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditDeveloperCommand.EditDeveloperDescriptor descriptor = new EditDeveloperDescriptorBuilder(firstDeveloper)
                .build();
        EditDeveloperCommand editCommand = new EditDeveloperCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, modelNoProjects, EditDeveloperCommand.MESSAGE_DUPLICATE_DEVELOPER);
    }

    @Test
    public void execute_duplicateDeveloperUnfilteredListWithProjects_failure() {
        Developer firstDeveloper = modelWithProjects.getFilteredDeveloperList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditDeveloperCommand.EditDeveloperDescriptor descriptor = new EditDeveloperDescriptorBuilder(firstDeveloper)
                .build();
        EditDeveloperCommand editCommand = new EditDeveloperCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, modelWithProjects, EditDeveloperCommand.MESSAGE_DUPLICATE_DEVELOPER);
    }

    @Test
    public void execute_duplicateDeveloperFilteredListNoProjects_failure() {
        showDeveloperAtIndex(modelNoProjects, INDEX_FIRST_PERSON);

        // edit developer in filtered list into a duplicate in address book
        Developer developerInList = modelNoProjects.getAddressBook().getDeveloperList()
                .get(INDEX_SECOND_PERSON.getZeroBased());
        EditDeveloperCommand editCommand = new EditDeveloperCommand(INDEX_FIRST_PERSON,
                new EditDeveloperDescriptorBuilder(developerInList).build());

        assertCommandFailure(editCommand, modelNoProjects, EditDeveloperCommand.MESSAGE_DUPLICATE_DEVELOPER);
    }

    @Test
    public void execute_duplicateDeveloperFilteredListWithProjects_failure() {
        showDeveloperAtIndex(modelWithProjects, INDEX_FIRST_PERSON);

        // edit developer in filtered list into a duplicate in address book
        Developer developerInList = modelWithProjects.getAddressBook().getDeveloperList()
                .get(INDEX_SECOND_PERSON.getZeroBased());
        EditDeveloperCommand editCommand = new EditDeveloperCommand(INDEX_FIRST_PERSON,
                new EditDeveloperDescriptorBuilder(developerInList).build());

        assertCommandFailure(editCommand, modelWithProjects, EditDeveloperCommand.MESSAGE_DUPLICATE_DEVELOPER);
    }

    @Test
    public void execute_invalidDeveloperIndexUnfilteredListNoProjects_failure() {
        Index outOfBoundIndex = Index.fromOneBased(modelNoProjects.getFilteredDeveloperList().size() + 1);
        EditDeveloperCommand.EditDeveloperDescriptor descriptor = new EditDeveloperDescriptorBuilder()
                .withName(VALID_NAME_CALEB).build();
        EditDeveloperCommand editCommand = new EditDeveloperCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, modelNoProjects, Messages.MESSAGE_INVALID_DEVELOPER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidDeveloperIndexUnfilteredListWithProjects_failure() {
        Index outOfBoundIndex = Index.fromOneBased(modelWithProjects.getFilteredDeveloperList().size() + 1);
        EditDeveloperCommand.EditDeveloperDescriptor descriptor = new EditDeveloperDescriptorBuilder()
                .withName(VALID_NAME_CALEB).build();
        EditDeveloperCommand editCommand = new EditDeveloperCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, modelWithProjects, Messages.MESSAGE_INVALID_DEVELOPER_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidDeveloperIndexFilteredListNoProjects_failure() {
        showDeveloperAtIndex(modelNoProjects, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < modelNoProjects.getAddressBook().getDeveloperList().size());

        EditDeveloperCommand editCommand = new EditDeveloperCommand(outOfBoundIndex,
                new EditDeveloperDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, modelNoProjects, Messages.MESSAGE_INVALID_DEVELOPER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidDeveloperIndexFilteredListWithProjects_failure() {
        showDeveloperAtIndex(modelWithProjects, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < modelWithProjects.getAddressBook()
                .getDeveloperList().size());

        EditDeveloperCommand editCommand = new EditDeveloperCommand(outOfBoundIndex,
                new EditDeveloperDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, modelWithProjects, Messages.MESSAGE_INVALID_DEVELOPER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditDeveloperCommand standardCommand = new EditDeveloperCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditDeveloperCommand.EditDeveloperDescriptor copyDescriptor =
                new EditDeveloperCommand.EditDeveloperDescriptor(DESC_AMY);
        EditDeveloperCommand commandWithSameValues = new EditDeveloperCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        Assertions.assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new EditDeveloperCommand(INDEX_SECOND_PERSON, DESC_AMY));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditDeveloperCommand(INDEX_FIRST_PERSON, DESC_BOB));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditDeveloperCommand.EditDeveloperDescriptor editDeveloperDescriptor = new EditDeveloperCommand
                .EditDeveloperDescriptor();
        EditDeveloperCommand editCommand = new EditDeveloperCommand(index, editDeveloperDescriptor);
        String expected = EditDeveloperCommand.class.getCanonicalName() + "{index=" + index
                + ", editDeveloperDescriptor="
                + editDeveloperDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }
}
