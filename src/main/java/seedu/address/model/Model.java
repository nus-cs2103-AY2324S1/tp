package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.displayable.Person;
import seedu.address.model.displayable.buyer.Buyer;
import seedu.address.model.displayable.seller.Seller;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that evaluates to true only for sellers */
    Predicate<Seller> PREDICATE_SHOW_SELLERS = unused -> true;

    /** {@code Predicate} that evaluates to true only for buyers */
    Predicate<Buyer> PREDICATE_SHOW_BUYERS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path filePath);
    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a buyer with the same identity as {@code buyer} exists in the address book's buyer list.
     */
    boolean hasBuyer(Buyer buyer);
    /**
     * Returns true if a buyer with similar identity as {@code buyer} exists in the address book's buyer list.
     */
    boolean hasSimilarBuyer(Buyer buyer);

    /**
     * Returns true if a seller with the same identity as {@code seller} exists in the address book's seller list.
     */
    boolean hasSeller(Seller seller);
    /**
     * Returns true if a seller with similar identity as {@code seller} exists in the address book's seller list.
     */
    boolean hasSimilarSeller(Seller seller);


    boolean buyerHasSameSellerName(Buyer buyer);

    boolean sellerHasSameBuyerName(Seller seller);

    /**
     * Deletes the given buyer.
     * The buyer must exist in the address book's buyer list.
     */
    void deleteBuyer(Buyer targetBuyer);

    /**
     * Deletes the given seller.
     * The seller must exist in the address book's seller list.
     */
    void deleteSeller(Seller targetSeller);

    /**
     * Adds the given buyer.
     * {@code buyer} must not already exist in the address book's buyer list.
     */
    void addBuyer(Buyer buyer);

    /**
     * Adds the given seller.
     * {@code seller} must not already exist in the address book's seller list.
     */
    void addSeller(Seller seller);

    /**
     * Replaces the given buyer {@code targetBuyer} with {@code editedBuyer}.
     * {@code targetBuyer} must exist in the address book's buyer list.
     * The displayable identity of {@code editedBuyer} must not be the same as another existing buyer in the buyer list.
     */
    void setBuyer(Buyer targetBuyer, Buyer editedBuyer);

    /**
     * Replaces the given seller {@code targetSeller} with {@code editedSeller}.
     * {@code targetSeller} must exist in the address book's seller list.
     * The displayable identity of {@code editedSeller} must not be the same
     * as another existing seller in the seller list.
     */
    void setSeller(Seller targetSeller, Seller editedSeller);

    /** Returns an unmodifiable view of the filtered buyer list */
    ObservableList<Buyer> getFilteredBuyerList();

    /** Returns an unmodifiable view of the filtered seller list */
    ObservableList<Seller> getFilteredSellerList();

    /**
     * Updates the filter of the filtered buyer list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredBuyerList(Predicate<? super Buyer> predicate);

    /**
     * Updates the filter of the filtered seller list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredSellerList(Predicate<? super Seller> predicate);

    void commitAddressBook();

    void undoAddressBook();

    void redoAddressBook();

    boolean canUndoAddressBook();

    boolean canRedoAddressBook();

    void updateFilteredSortedBuyerList(Comparator<Buyer> comparator);

    void updateFilteredSortedSellerList(Comparator<Seller> comparator);
}
