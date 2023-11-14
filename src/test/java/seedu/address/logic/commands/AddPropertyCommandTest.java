package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProperties.AQUAVISTA;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.PropertyBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyPropertyBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.customer.Customer;
import seedu.address.model.property.Property;
import seedu.address.testutil.PropertyBuilder;

public class AddPropertyCommandTest {

    @Test
    public void constructor_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPropertyCommand(null));
    }

    @Test
    public void execute_propertyAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPropertyAdded modelStub = new ModelStubAcceptingPropertyAdded();
        Property validProperty = new PropertyBuilder().build();

        CommandResult commandResult = new AddPropertyCommand(validProperty).execute(modelStub);

        assertEquals(String.format(AddPropertyCommand.MESSAGE_SUCCESS, Messages.format(validProperty)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validProperty), modelStub.propertiesAdded);
    }

    @Test
    public void execute_duplicateProperty_throwsCommandException() {
        Property validProperty = new PropertyBuilder().build();
        AddPropertyCommand addPropertyCommand = new AddPropertyCommand(validProperty);
        ModelStub modelStub = new ModelStubWithProperty(validProperty);

        assertThrows(CommandException.class, AddPropertyCommand.MESSAGE_DUPLICATE_PROPERTY, ()
                -> addPropertyCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Property aquavista = new PropertyBuilder().withName("Aquavista").build();
        Property skyvista = new PropertyBuilder().withName("Skyvista").build();
        AddPropertyCommand addAquavistaCommand = new AddPropertyCommand(aquavista);
        AddPropertyCommand addSkyvistaCommand = new AddPropertyCommand(skyvista);

        // same object -> returns true
        assertTrue(addAquavistaCommand.equals(addAquavistaCommand));

        // same values -> returns true
        AddPropertyCommand addAquavistaCommandCopy = new AddPropertyCommand(aquavista);
        assertTrue(addAquavistaCommand.equals(addAquavistaCommandCopy));

        // different types -> returns false
        assertFalse(addAquavistaCommand.equals(1));

        // null -> returns false
        assertFalse(addAquavistaCommand.equals(null));

        // different property -> returns false
        assertFalse(addAquavistaCommand.equals(addSkyvistaCommand));
    }

    @Test
    public void toStringMethod() {
        AddPropertyCommand addPropertyCommand = new AddPropertyCommand(AQUAVISTA);
        String expected = AddPropertyCommand.class.getCanonicalName() + "{toAdd=" + AQUAVISTA + "}";
        assertEquals(expected, addPropertyCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        public Path getPropertyBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        public void setPropertyBookFilePath(Path propertyBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCustomer(Customer customer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addProperty(Property property) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPropertyBook(ReadOnlyPropertyBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyPropertyBook getPropertyBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCustomer(Customer customer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasProperty(Property property) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCustomer(Customer target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteProperty(Property target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCustomer(Customer target, Customer editedCustomer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProperty(Property target, Property editedProperty) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public ObservableList<Customer> getFilteredCustomerList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Property> getFilteredPropertyList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCustomerList(Predicate<Customer> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPropertyList(Predicate<Property> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateMatchedCustomerList(Customer targetCustomer, Predicate<Property> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateMatchedPropertyList(Property targetProperty, Predicate<Customer> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single Property.
     */
    private class ModelStubWithProperty extends ModelStub {
        private final Property property;

        ModelStubWithProperty(Property property) {
            requireNonNull(property);
            this.property = property;
        }

        @Override
        public boolean hasProperty(Property property) {
            requireNonNull(property);
            return this.property.isSameProperty(property);
        }
    }

    /**
     * A Model stub that always accept the Property being added.
     */
    private class ModelStubAcceptingPropertyAdded extends ModelStub {
        final ArrayList<Property> propertiesAdded = new ArrayList<>();

        @Override
        public boolean hasProperty(Property property) {
            requireNonNull(property);
            return propertiesAdded.stream().anyMatch(property::isSameProperty);
        }

        @Override
        public void addProperty(Property property) {
            requireNonNull(property);
            propertiesAdded.add(property);
        }

        @Override
        public ReadOnlyPropertyBook getPropertyBook() {
            return new PropertyBook();
        }
    }

}
