package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.item.Item;
import seedu.address.model.item.review.ItemReview;
import seedu.address.model.stall.Menu;
import seedu.address.model.stall.Stall;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Stall> filteredStalls;
    private final FilteredList<Stall> tempFilteredStalls;
    private ObservableList<Item> filteredItemList;
    private Item filteredItem;
    private Stall filteredStall;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStalls = new FilteredList<>(this.addressBook.getStallList());
        tempFilteredStalls = new FilteredList<>(this.addressBook.getStallList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasStall(Stall stall) {
        requireNonNull(stall);
        return addressBook.hasStall(stall);
    }

    @Override
    public void showStall(Stall stall) {
        requireNonNull(stall);
        Predicate<Stall> predicate = stallll -> stallll.equals(stall);
        tempFilteredStalls.setPredicate(predicate);
    }

    @Override
    public void deleteStall(Stall target) {
        addressBook.removeStall(target);
    }

    @Override
    public void addStall(Stall stall) {
        addressBook.addStall(stall);
        updateFilteredStallList(PREDICATE_SHOW_ALL_STALLS);
    }

    @Override
    public void setStall(Stall target, Stall editedStall) {
        requireAllNonNull(target, editedStall);
        addressBook.setStall(target, editedStall);
    }

    //=========== Filtered Stall List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Stall} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Stall> getFilteredStallList() {
        return filteredStalls;
    }

    @Override
    public ObservableList<Stall> getTempFilteredStallList() {
        return tempFilteredStalls;
    }

    @Override
    public void updateFilteredStallList(Predicate<Stall> predicate) {
        requireNonNull(predicate);
        filteredStalls.setPredicate(predicate);
    }
    @Override
    public Stall getFilteredStall() {
        return this.filteredStall;
    }

    @Override
    public Stall getFilteredStall(Index stallIndex) {
        return filteredStalls.get(stallIndex.getZeroBased());
    }

    @Override
    public void sortStallRating() {
        addressBook.sortStallRating();
    }

    @Override
    public void sortStallLocation() {
        addressBook.sortStallLocation();
    }

    @Override
    public void sortStallPrice() {
        addressBook.sortStallPrice();
    }

    @Override
    public int getFilteredStallIndex() {
        return filteredStalls.indexOf(filteredStall) + 1;
    }


    //=========== Filtered Item List Accessors =============================================================

    @Override
    public boolean hasItem(Stall stall, Item item) {
        requireAllNonNull(stall, item);
        return stall.hasItem(item);
    }

    @Override
    public boolean hasItemReview(Item item) {
        requireNonNull(item);
        return item.hasItemReview();
    }

    @Override
    public void addItem(Index stallIndex, Item item) {
        requireNonNull(stallIndex);
        requireNonNull(item);
        this.getFilteredStall(stallIndex).addItem(item);
    }

    @Override
    public void setItem(Index stallIndex, Index itemIndex, Item editedItem) {
        requireNonNull(stallIndex);
        requireNonNull(itemIndex);
        requireNonNull(editedItem);
        Stall stallToEdit = this.getFilteredStall(stallIndex);
        Menu menuToEdit = stallToEdit.getMenu();
        Item itemToEdit = menuToEdit.getItem(itemIndex);
        menuToEdit.setItem(itemToEdit, editedItem);
    }

    @Override
    public void deleteItem(Index stallIndex, Index itemIndex) {
        requireNonNull(stallIndex);
        requireNonNull(itemIndex);
        this.getFilteredStall(stallIndex).deleteItem(itemIndex);
    }

    @Override
    public void setItemReview(Item item, ItemReview itemReview) {
        requireNonNull(item);
        requireNonNull(itemReview);
        item.setItemReview(itemReview);
    }

    @Override
    public void deleteItemReview(Item item) {
        requireNonNull(item);
        item.deleteItemReview();
    }

    @Override
    public Item getFilteredItem() {
        return filteredItem;
    }

    @Override
    public Item getFilteredItem(Index stallIndex, Index itemIndex) {
        requireAllNonNull(stallIndex, itemIndex);
        setFilteredStall(stallIndex);
        return filteredStall.getItem(itemIndex);
    }

    @Override
    public void setFilteredItem(Item item) {
        requireNonNull(item);
        this.filteredItem = item;
    }

    @Override
    public void setFilteredItemList(Index stallIndex) {
        requireNonNull(stallIndex);
        setFilteredStall(stallIndex);
        filteredItemList = filteredStall.getMenuList();
    }

    @Override
    public void setFilteredStall(Index stallIndex) {
        filteredStall = filteredStalls.get(stallIndex.getZeroBased());
    }


    @Override
    public ObservableList<Item> getFilteredItemList() {
        return filteredItemList;
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredStalls.equals(otherModelManager.filteredStalls);
    }

}
