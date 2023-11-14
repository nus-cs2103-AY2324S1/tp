package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandPropertyTestUtil.DESC_AQUAVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.DESC_SKYVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_NAME_SKYVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_PHONE_SKYVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_TAG_BIG;
import static seedu.address.logic.commands.CommandPropertyTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandPropertyTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandPropertyTestUtil.showPropertyAtIndex;
import static seedu.address.testutil.TypicalCustomers.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROPERTY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PROPERTY;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditPropertyCommand.EditPropertyDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PropertyBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.property.Property;
import seedu.address.testutil.EditPropertyDescriptorBuilder;
import seedu.address.testutil.PropertyBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditPropertyCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalPropertyBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Property editedProperty = new PropertyBuilder().build();
        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder(editedProperty).build();
        EditPropertyCommand editCommand = new EditPropertyCommand(INDEX_FIRST_PROPERTY, descriptor);

        String expectedMessage = String.format(EditPropertyCommand.MESSAGE_EDIT_PROPERTY_SUCCESS,
                Messages.format(editedProperty));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new PropertyBook(model.getPropertyBook()), new UserPrefs());
        expectedModel.setProperty(model.getFilteredPropertyList().get(0), editedProperty);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastProperty = Index.fromOneBased(model.getFilteredPropertyList().size());
        Property lastProperty = model.getFilteredPropertyList().get(indexLastProperty.getZeroBased());

        PropertyBuilder propertyInList = new PropertyBuilder(lastProperty);
        Property editedProperty = propertyInList.withName(VALID_NAME_SKYVIEW).withPhone(VALID_PHONE_SKYVIEW)
                .withTags(VALID_TAG_BIG).build();

        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder().withName(VALID_NAME_SKYVIEW)
                .withPhone(VALID_PHONE_SKYVIEW).withTags(VALID_TAG_BIG).build();
        EditPropertyCommand editCommand = new EditPropertyCommand(indexLastProperty, descriptor);

        String expectedMessage = String.format(EditPropertyCommand.MESSAGE_EDIT_PROPERTY_SUCCESS,
                Messages.format(editedProperty));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new PropertyBook(model.getPropertyBook()), new UserPrefs());
        expectedModel.setProperty(lastProperty, editedProperty);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditPropertyCommand editCommand = new EditPropertyCommand(INDEX_FIRST_PROPERTY, new EditPropertyDescriptor());
        Property editedProperty = model.getFilteredPropertyList().get(INDEX_FIRST_PROPERTY.getZeroBased());

        String expectedMessage = String.format(EditPropertyCommand.MESSAGE_EDIT_PROPERTY_SUCCESS,
                Messages.format(editedProperty));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new PropertyBook(model.getPropertyBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPropertyAtIndex(model, INDEX_FIRST_PROPERTY);

        Property propertyInFilteredList = model.getFilteredPropertyList().get(INDEX_FIRST_PROPERTY.getZeroBased());
        Property editedProperty = new PropertyBuilder(propertyInFilteredList).withName(VALID_NAME_SKYVIEW).build();
        EditPropertyCommand editCommand = new EditPropertyCommand(INDEX_FIRST_PROPERTY,
                new EditPropertyDescriptorBuilder().withName(VALID_NAME_SKYVIEW).build());

        String expectedMessage = String.format(EditPropertyCommand.MESSAGE_EDIT_PROPERTY_SUCCESS,
                Messages.format(editedProperty));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new PropertyBook(model.getPropertyBook()), new UserPrefs());
        expectedModel.setProperty(model.getFilteredPropertyList().get(0), editedProperty);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePropertyUnfilteredList_failure() {
        Property firstProperty = model.getFilteredPropertyList().get(INDEX_FIRST_PROPERTY.getZeroBased());
        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder(firstProperty).build();
        EditPropertyCommand editCommand = new EditPropertyCommand(INDEX_SECOND_PROPERTY, descriptor);

        assertCommandFailure(editCommand, model, EditPropertyCommand.MESSAGE_DUPLICATE_PROPERTY);
    }

    @Test
    public void execute_duplicatePropertyFilteredList_failure() {
        showPropertyAtIndex(model, INDEX_FIRST_PROPERTY);

        // edit property in filtered list into a duplicate in budget book
        Property propertyInList = model.getPropertyBook().getPropertyList().get(INDEX_SECOND_PROPERTY.getZeroBased());
        EditPropertyCommand editCommand = new EditPropertyCommand(INDEX_FIRST_PROPERTY,
                new EditPropertyDescriptorBuilder(propertyInList).build());

        assertCommandFailure(editCommand, model, EditPropertyCommand.MESSAGE_DUPLICATE_PROPERTY);
    }

    @Test
    public void execute_invalidPropertyIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPropertyList().size() + 1);
        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder().withName(VALID_NAME_SKYVIEW).build();
        EditPropertyCommand editCommand = new EditPropertyCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of budget book
     */
    @Test
    public void execute_invalidPropertyIndexFilteredList_failure() {
        showPropertyAtIndex(model, INDEX_FIRST_PROPERTY);
        Index outOfBoundIndex = INDEX_SECOND_PROPERTY;
        // ensures that outOfBoundIndex is still in bounds of budget book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPropertyBook().getPropertyList().size());

        EditPropertyCommand editCommand = new EditPropertyCommand(outOfBoundIndex,
                new EditPropertyDescriptorBuilder().withName(VALID_NAME_SKYVIEW).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditPropertyCommand standardCommand = new EditPropertyCommand(INDEX_FIRST_PROPERTY, DESC_AQUAVIEW);

        // same values -> returns true
        EditPropertyDescriptor copyDescriptor = new EditPropertyDescriptor(DESC_AQUAVIEW);
        EditPropertyCommand commandWithSameValues = new EditPropertyCommand(INDEX_FIRST_PROPERTY, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditPropertyCommand(INDEX_SECOND_PROPERTY, DESC_AQUAVIEW)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditPropertyCommand(INDEX_FIRST_PROPERTY, DESC_SKYVIEW)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditPropertyDescriptor editPropertyDescriptor = new EditPropertyDescriptor();
        EditPropertyCommand editCommand = new EditPropertyCommand(index, editPropertyDescriptor);
        String expected = EditPropertyCommand.class.getCanonicalName() + "{index=" + index + ", editPropertyDescriptor="
                + editPropertyDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}
