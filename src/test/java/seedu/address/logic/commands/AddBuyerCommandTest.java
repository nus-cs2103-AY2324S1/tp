package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBuyers.ALICE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.displayable.buyer.Buyer;
import seedu.address.model.displayable.seller.Seller;
import seedu.address.testutil.BuyerBuilder;

public class AddBuyerCommandTest {

    @Test
    public void constructor_nullBuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddBuyerCommand(null));
    }

    @Test
    public void execute_buyerAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingBuyerAdded modelStub = new ModelStubAcceptingBuyerAdded();
        Buyer validBuyer = new BuyerBuilder().build();

        CommandResult commandResult = new AddBuyerCommand(validBuyer).execute(modelStub);

        assertEquals(String.format(AddBuyerCommand.MESSAGE_SUCCESS, Messages.format(validBuyer)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validBuyer), modelStub.buyersAdded);
    }

    @Test
    public void execute_duplicateBuyer_throwsCommandException() {
        Buyer validBuyer = new BuyerBuilder().build();
        AddBuyerCommand addBuyerCommand = new AddBuyerCommand(validBuyer);
        ModelStub modelStub = new ModelStubWithBuyer(validBuyer);

        assertThrows(CommandException.class,
                AddBuyerCommand.MESSAGE_DUPLICATE_BUYER, () -> addBuyerCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Buyer alice = new BuyerBuilder().withName("Alice").build();
        Buyer bob = new BuyerBuilder().withName("Bob").build();
        AddBuyerCommand addAliceCommand = new AddBuyerCommand(alice);
        AddBuyerCommand addBobCommand = new AddBuyerCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddBuyerCommand addAliceCommandCopy = new AddBuyerCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different buyer -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddBuyerCommand addBuyerCommand = new AddBuyerCommand(ALICE);
        String expected = AddBuyerCommand.class.getCanonicalName() + "{toAdd=" + ALICE + "}";
        assertEquals(expected, addBuyerCommand.toString());
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
        public Path getFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addBuyer(Buyer buyer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addSeller(Seller seller) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasBuyer(Buyer buyer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSimilarBuyer(Buyer buyer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSeller(Seller seller) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean buyerHasSameSellerName(Buyer buyer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean sellerHasSameBuyerName(Seller seller) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSimilarSeller(Seller seller) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteBuyer(Buyer target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteSeller(Seller target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBuyer(Buyer target, Buyer editedBuyer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSeller(Seller target, Seller editedBuyer) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public ObservableList<Buyer> getFilteredBuyerList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Seller> getFilteredSellerList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredBuyerList(Predicate<? super Buyer> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredSellerList(Predicate<? super Seller> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitAddressBook() {}

        @Override
        public void undoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredSortedBuyerList(Comparator<Buyer> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredSortedSellerList(Comparator<Seller> comparator) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single buyer.
     */
    private class ModelStubWithBuyer extends ModelStub {
        private final Buyer buyer;

        ModelStubWithBuyer(Buyer buyer) {
            requireNonNull(buyer);
            this.buyer = buyer;
        }

        @Override
        public boolean hasBuyer(Buyer buyer) {
            requireNonNull(buyer);
            return this.buyer.isSamePerson(buyer);
        }
    }

    /**
     * A Model stub that always accept the buyer being added.
     */
    private class ModelStubAcceptingBuyerAdded extends ModelStub {
        final ArrayList<Buyer> buyersAdded = new ArrayList<>();

        @Override
        public boolean hasBuyer(Buyer buyer) {
            requireNonNull(buyer);
            return buyersAdded.stream().anyMatch(buyer::isSamePerson);
        }

        @Override
        public void addBuyer(Buyer buyer) {
            requireNonNull(buyer);
            buyersAdded.add(buyer);
        }

        @Override
        public boolean hasSimilarBuyer(Buyer buyer) {
            requireNonNull(buyer);
            return buyersAdded.stream().anyMatch(buyer::isSimilarDisplayable);
        }

        @Override
        public boolean buyerHasSameSellerName(Buyer buyer) {
            return false;
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
