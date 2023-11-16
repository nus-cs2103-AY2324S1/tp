package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CALEB;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CALEB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_2_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_3_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertClientCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showClientAtIndex;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBookWithProjects;
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
import seedu.address.model.client.Client;
import seedu.address.testutil.ClientBuilder;
import seedu.address.testutil.EditClientDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditClientCommand.
 */
public class EditClientCommandTest {

    private final Model modelNoProjects = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model modelWithProjects = new ModelManager(getTypicalAddressBookWithProjects(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedNoProjectEditUnfilteredListNoProjects_success() {
        Client editedClient = new ClientBuilder().build();
        EditClientCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder(editedClient)
                .build();
        EditClientCommand editCommand = new EditClientCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS,
                Messages.format(editedClient));

        Model expectedModel = new ModelManager(new AddressBook(modelNoProjects.getAddressBook()), new UserPrefs());
        expectedModel.setClient(modelNoProjects.getFilteredClientList().get(0), editedClient);

        assertClientCommandSuccess(editCommand, modelNoProjects, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allFieldsSpecifiedNoProjectEditUnfilteredListWithProjects_success() {
        Client editedClient = new ClientBuilder().build();
        EditClientCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder(editedClient)
                .build();
        EditClientCommand editCommand = new EditClientCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS,
                Messages.format(editedClient));

        Model expectedModel = new ModelManager(new AddressBook(modelWithProjects.getAddressBook()), new UserPrefs());
        expectedModel.setClient(modelWithProjects.getFilteredClientList().get(0), editedClient);

        assertClientCommandSuccess(editCommand, modelWithProjects, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedValidProjectEditUnfilteredListWithProjects_success() {
        Index indexLastClient = Index.fromOneBased(modelWithProjects.getFilteredClientList().size());
        Client lastClient = modelWithProjects.getFilteredClientList().get(indexLastClient.getZeroBased());

        ClientBuilder clientInList = new ClientBuilder(lastClient);
        Client editedClient = clientInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withProjects(VALID_PROJECT_3_BOB).build();

        EditClientCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder()
                .withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withProjects(VALID_PROJECT_3_BOB).build();
        EditClientCommand editCommand = new EditClientCommand(indexLastClient, descriptor);

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS,
                Messages.format(editedClient));

        Model expectedModel = new ModelManager(new AddressBook(modelWithProjects.getAddressBook()), new UserPrefs());
        expectedModel.setClient(lastClient, editedClient);

        assertClientCommandSuccess(editCommand, modelWithProjects, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedInvalidProjectEditUnfilteredListWithProjects_failure() {
        Index indexLastClient = Index.fromOneBased(modelWithProjects.getFilteredClientList().size());

        EditClientCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder()
                .withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withProjects(VALID_PROJECT_2_BOB).build();
        EditClientCommand editCommand = new EditClientCommand(indexLastClient, descriptor);

        String expectedMessage = String.format(Messages.MESSAGE_NONEXISTENT_PROJECT, VALID_PROJECT_2_BOB);

        assertCommandFailure(editCommand, modelWithProjects, expectedMessage);
    }

    @Test
    public void execute_allFieldsSpecifiedValidProjectEditUnfilteredListWithProjects_success() {
        Client editedClient = new ClientBuilder().withProjects(VALID_PROJECT_3_BOB).build();
        EditClientCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder(editedClient)
                .build();
        EditClientCommand editCommand = new EditClientCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS,
                Messages.format(editedClient));

        Model expectedModel = new ModelManager(new AddressBook(modelWithProjects.getAddressBook()), new UserPrefs());
        expectedModel.setClient(modelWithProjects.getFilteredClientList().get(0), editedClient);

        assertClientCommandSuccess(editCommand, modelWithProjects, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredListValidProjectEditWithProjects_success() {
        showClientAtIndex(modelWithProjects, INDEX_FIRST_PERSON);

        Client clientInFilteredList = modelWithProjects.getFilteredClientList()
                .get(INDEX_FIRST_PERSON.getZeroBased());
        Client editedClient = new ClientBuilder(clientInFilteredList).withName(VALID_NAME_BOB)
                .withProjects(VALID_PROJECT_3_BOB).build();
        EditClientCommand editCommand = new EditClientCommand(INDEX_FIRST_PERSON,
                new EditClientDescriptorBuilder().withName(VALID_NAME_BOB).withProjects(VALID_PROJECT_3_BOB)
                        .build());

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS,
                Messages.format(editedClient));

        Model expectedModel = new ModelManager(new AddressBook(modelWithProjects.getAddressBook()), new UserPrefs());
        expectedModel.setClient(modelWithProjects.getFilteredClientList().get(0), editedClient);

        assertClientCommandSuccess(editCommand, modelWithProjects, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredListInvalidProjectEditWithProjects_failure() {
        showClientAtIndex(modelWithProjects, INDEX_FIRST_PERSON);

        EditClientCommand editCommand = new EditClientCommand(INDEX_FIRST_PERSON,
                new EditClientDescriptorBuilder().withName(VALID_NAME_BOB).withProjects(VALID_PROJECT_2_BOB)
                        .build());

        String expectedMessage = String.format(Messages.MESSAGE_NONEXISTENT_PROJECT, VALID_PROJECT_2_BOB);

        assertCommandFailure(editCommand, modelWithProjects, expectedMessage);
    }

    @Test
    public void execute_duplicateClientUnfilteredListNoProjects_failure() {
        Client firstClient = modelNoProjects.getFilteredClientList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditClientCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder(firstClient)
                .build();
        EditClientCommand editCommand = new EditClientCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, modelNoProjects, EditClientCommand.MESSAGE_DUPLICATE_CLIENT);
    }

    @Test
    public void execute_duplicateClientUnfilteredListWithProjects_failure() {
        Client firstClient = modelWithProjects.getFilteredClientList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditClientCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder(firstClient)
                .build();
        EditClientCommand editCommand = new EditClientCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, modelWithProjects, EditClientCommand.MESSAGE_DUPLICATE_CLIENT);
    }

    @Test
    public void execute_duplicateClientFilteredListNoProjects_failure() {
        showClientAtIndex(modelNoProjects, INDEX_FIRST_PERSON);

        // edit client in filtered list into a duplicate in address book
        Client clientInList = modelNoProjects.getAddressBook().getClientList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditClientCommand editCommand = new EditClientCommand(INDEX_FIRST_PERSON,
                new EditClientDescriptorBuilder(clientInList).build());

        assertCommandFailure(editCommand, modelNoProjects, EditClientCommand.MESSAGE_DUPLICATE_CLIENT);
    }

    @Test
    public void execute_duplicateClientFilteredListWithProjects_failure() {
        showClientAtIndex(modelWithProjects, INDEX_FIRST_PERSON);

        // edit client in filtered list into a duplicate in address book
        Client clientInList = modelWithProjects.getAddressBook().getClientList()
                .get(INDEX_SECOND_PERSON.getZeroBased());
        EditClientCommand editCommand = new EditClientCommand(INDEX_FIRST_PERSON,
                new EditClientDescriptorBuilder(clientInList).build());

        assertCommandFailure(editCommand, modelWithProjects, EditClientCommand.MESSAGE_DUPLICATE_CLIENT);
    }

    @Test
    public void execute_invalidClientIndexUnfilteredListNoProjects_failure() {
        Index outOfBoundIndex = Index.fromOneBased(modelNoProjects.getFilteredClientList().size() + 1);
        EditClientCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder()
                .withName(VALID_NAME_CALEB).build();
        EditClientCommand editCommand = new EditClientCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, modelNoProjects, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidClientIndexUnfilteredListWithProjects_failure() {
        Index outOfBoundIndex = Index.fromOneBased(modelWithProjects.getFilteredClientList().size() + 1);
        EditClientCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder()
                .withName(VALID_NAME_CALEB).build();
        EditClientCommand editCommand = new EditClientCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, modelWithProjects, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidClientIndexFilteredListNoProjects_failure() {
        showClientAtIndex(modelNoProjects, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < modelNoProjects.getAddressBook().getClientList().size());

        EditClientCommand editCommand = new EditClientCommand(outOfBoundIndex,
                new EditClientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, modelNoProjects, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidClientIndexFilteredListWithProjects_failure() {
        showClientAtIndex(modelWithProjects, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < modelWithProjects.getAddressBook()
                .getClientList().size());

        EditClientCommand editCommand = new EditClientCommand(outOfBoundIndex,
                new EditClientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, modelWithProjects, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditClientCommand standardCommand = new EditClientCommand(INDEX_FIRST_PERSON, DESC_CALEB);

        // same values -> returns true
        EditClientCommand.EditClientDescriptor copyDescriptor = new EditClientCommand.EditClientDescriptor(DESC_CALEB);
        EditClientCommand commandWithSameValues = new EditClientCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        Assertions.assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new EditClientCommand(INDEX_SECOND_PERSON, DESC_CALEB));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditClientCommand(INDEX_FIRST_PERSON, DESC_DAN));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditClientCommand.EditClientDescriptor editClientDescriptor = new EditClientCommand
                .EditClientDescriptor();
        EditClientCommand editCommand = new EditClientCommand(index, editClientDescriptor);
        String expected = EditClientCommand.class.getCanonicalName() + "{index=" + index
                + ", editClientDescriptor="
                + editClientDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }
}
