package seedu.address.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.model.item.exceptions.DuplicateItemException;
import seedu.address.model.item.exceptions.ItemNotFoundException;
import seedu.address.model.stall.AveragePrice;

/**
 * A list of items that enforces uniqueness between its elements and does not allow nulls.
 * An item is considered unique by comparing using {@code item#isSameItem(item)}. As such, adding and updating of
 * items uses item#isSameItems(item) for equality to ensure that the item being added or updated is
 * unique in terms of identity in the UniqueitemList. However, the removal of an item uses item#equals(Object)
 * to ensure that the item with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Item#isSameItem(Item)
 */
public class UniqueItemList implements Iterable<Item> {

    private final ObservableList<Item> internalList = FXCollections.observableArrayList();
    private final ObservableList<Item> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public AveragePrice getAveragePrice() {
        double averagePrice = internalList.stream()
                .mapToDouble(item -> item.getPrice().priceDouble).average().orElse(-1.0);
        if (averagePrice < 0) {
            // no items in list
            return null;
        } else {
            return new AveragePrice(averagePrice);
        }
    }

    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    /**
     * Returns size of list
     *
     * @return size of list
     */
    public int getSize() {
        return internalList.size();
    }

    /**
     * Returns Item at index
     * @param index index of item
     * @return item at index
     */
    public Item getItem(int index) {
        return internalList.get(index);
    }

    public Item getItem(Item item) {
        requireNonNull(item);
        return internalList.stream().filter(item::isSameItem).findFirst().get();
    }

    /**
     * Returns true if the list contains an equivalent item as the given argument.
     * @param toCheck item to check
     * @return true if list contains item
     */
    public boolean contains(Item toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameItem);
    }

    /**
     * Adds an item to the list.
     * The item must not already exist in the list.
     * @param toAdd item to add
     */
    public void add(Item toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateItemException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the item {@code target} in the list with {@code editeditem}.
     * {@code target} must exist in the list.
     * The item identity of {@code editeditem} must not be the same as another existing item in the list.
     * @param target item to replace
     * @param editeditem item to replace with
     */
    public void setItem(Item target, Item editeditem) {
        requireAllNonNull(target, editeditem);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ItemNotFoundException();
        }

        if (!target.isSameItem(editeditem) && contains(editeditem)) {
            throw new DuplicateItemException();
        }

        internalList.set(index, editeditem);
    }

    /**
     * Removes the equivalent item from the list.
     * The item must exist in the list.
     * @param itemIndex index of item to remove
     */
    public void remove(Index itemIndex) {
        Item toRemove = internalList.get(itemIndex.getZeroBased());
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ItemNotFoundException();
        }
    }

    public void setItems(UniqueItemList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code items}.
     * {@code items} must not contain duplicate items.
     * @param items items to replace with
     */
    public void setItems(List<Item> items) {
        requireAllNonNull(items);
        if (!itemsAreUnique(items)) {
            throw new DuplicateItemException();
        }

        internalList.setAll(items);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     * @return backing list
     */
    public ObservableList<Item> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Item> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueItemList)) {
            return false;
        }

        UniqueItemList otherUniqueitemList = (UniqueItemList) other;
        return internalList.equals(otherUniqueitemList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code items} contains only unique items.
     * @param items items to check
     * @return true if items are unique
     */
    private boolean itemsAreUnique(List<Item> items) {
        for (int i = 0; i < items.size() - 1; i++) {
            for (int j = i + 1; j < items.size(); j++) {
                if (items.get(i).isSameItem(items.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns true if any item in the list contains any of the keywords.
     * @param keywords keywords to check
     * @return true if any item contains any of the keywords
     */
    public boolean containsKeywords(List<String> keywords) {
        for (int i = 0; i < internalList.size(); i++) {
            Item item = this.getItem(i);
            if (keywords.stream()
                    .anyMatch(keyword -> StringUtil
                            .containsWordIgnoreCase(item.getName().fullName, keyword))) {
                return true;
            }
        }
        return false;
    }
}
