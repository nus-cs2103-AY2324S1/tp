package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.item.Item;
import seedu.address.model.item.review.ItemReview;
import seedu.address.model.stall.Stall;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Stall> PREDICATE_SHOW_ALL_STALLS = unused -> true;

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
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a stall with the same identity as {@code stall} exists in the address book.
     */
    boolean hasStall(Stall stall);

    /**
     * Deletes the given stall.
     * The stall must exist in the address book.
     */
    void deleteStall(Stall target);

    void showStall(Stall stall);

    /**
     * Adds the given stall.
     * {@code stall} must not already exist in the address book.
     */
    void addStall(Stall stall);

    /**
     * Get stall that is filtered out
     * @return filtered stall
     */
    Stall getFilteredStall();

    /**
     * Returns the filtered stall.
     * @param stallIndex index of the stall
     */
    Stall getFilteredStall(Index stallIndex);

    /**
     * Returns the filtered stall index.
     */
    int getFilteredStallIndex();


    /**
     * Replaces the given stall {@code target} with {@code editedStall}.
     * {@code target} must exist in the address book.
     * The stall identity of {@code editedStall} must not be the same as another existing stall in the address book.
     */
    void setStall(Stall target, Stall editedStall);

    /** Returns an unmodifiable view of the filtered stall list */
    ObservableList<Stall> getFilteredStallList();

    ObservableList<Stall> getTempFilteredStallList();
    /**
     * Returns the filtered item.
     */
    Item getFilteredItem(Index stallIndex, Index itemIndex);

    /**
     * Returns the desired filtered item
     * @return filtered item
     */
    Item getFilteredItem();

    /**
     * Sets the item.
     * @param item item to be set
     */
    void setFilteredItem(Item item);

    /**
     * Updates the filter of the filtered stall list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStallList(Predicate<Stall> predicate);

    /**
     * Returns true if an item with the same identity as {@code item} exists in the menu.
     * @param stallIndex index of the stall
     * @param item item to be checked
     * @return true if an item with the same identity as {@code item} exists in the menu.
     */
    boolean hasItem(Stall stallIndex, Item item);

    /**
     * Returns true if an item review exists in the item.
     */
    boolean hasItemReview(Item item);

    /**
     * Adds the given item.
     * {@code item} must not already exist in the menu.
     * @param stallIndex index of the stall
     * @param item item to be added
     */
    void addItem(Index stallIndex, Item item);

    /**
     * Replaces the given item {@code target} with {@code editedItem}.
     * {@code target} must exist in the menu.
     * The item identity of {@code editedItem} must not be the same as another existing item in the menu.
     */
    void setItem(Index stallIndex, Index itemIndex, Item editedItem);

    /**
     * Deletes the given item.
     * The item must exist in the stall menu.
     * @param stallIndex index of the stall
     * @param itemIndex index of the item
     */
    void deleteItem(Index stallIndex, Index itemIndex);

    /**
     * Adds the given item review.
     *
     * {@code itemReview} must not already exist in the item.
     * @param itemIndex index of the item
     * @param itemReview item review to be added
     */
    void setItemReview(Item itemIndex, ItemReview itemReview);

    /**
     * Deletes the given item review.
     *
     * {@code itemReview} must exist in the item.
     * @param itemIndex index of the item
     */
    void deleteItemReview(Item itemIndex);

    /**
     * Sets the item list to be that of the chosen stall
     * @param stallIndex index of the chosen stall
     */
    void setFilteredItemList(Index stallIndex);

    /**
     * Sets filtered stall to be the chosen stall
     * @param stallIndex index of the chosen stall
     */
    void setFilteredStall(Index stallIndex);


    /**
     * Sorts the stall list by rating in descending order.
     */
    void sortStallRating();

    /**
     * Sorts the stall list by location in alphabetical order.
     */
    void sortStallLocation();

    /**
     * Get the item list that is filtered by stall.
     * @return filtered item list.
     * @throws NullPointerException if {@code predicate} is null.
     */
    ObservableList<Item> getFilteredItemList();

    /**
     * Sorts the stall list by price in ascending order.
     */
    void sortStallPrice();

}
