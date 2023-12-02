package seedu.address.model.delivery;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.delivery.exceptions.DeliveryNotFoundException;
import seedu.address.model.delivery.exceptions.DuplicateDeliveryException;

/**
 * A list of deliveries that enforces uniqueness between its elements and does not allow nulls.
 * A delivery is considered unique by comparing using {@code Delivery#isSameDelivery(Delivery)}.
 * As such, adding and updating of deliveries uses Delivery#isSameDelivery(Delivery) for equality so as to ensure that
 * the Delivery being added or updated is unique in terms of identity in the UniqueDeliveryList. However, the removal
 * of a delivery uses Delivery#equals(Object) so as to ensure that the person with exactly the same fields
 * will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Delivery#isSameDelivery(Delivery)
 */
public class UniqueDeliveryList implements Iterable<Delivery> {

    private final ObservableList<Delivery> internalList = FXCollections.observableArrayList();
    private final ObservableList<Delivery> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Delivery toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameDelivery);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Delivery toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateDeliveryException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the delivery {@code target} in the list with {@code editedDelivery}.
     * {@code target} must exist in the list.
     * The delivery identity of {@code editedDelivery} must not be the same as another existing Delivery in the list.
     */
    public void setDelivery(Delivery target, Delivery editedDelivery) {
        requireAllNonNull(target, editedDelivery);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new DeliveryNotFoundException();
        }

        if (!target.isSameDelivery(editedDelivery) && contains(editedDelivery)) {
            throw new DuplicateDeliveryException();
        }

        internalList.set(index, editedDelivery);
    }

    /**
     * Removes the equivalent delivery from the list.
     * The person must exist in the list.
     */
    public void remove(Delivery toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new DeliveryNotFoundException();
        }
    }

    public void setDeliveries(UniqueDeliveryList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code deliveries}.
     * {@code persons} must not contain duplicate deliveries.
     */
    public void setDeliveries(List<Delivery> deliveries) {
        requireAllNonNull(deliveries);
        if (!deliveriesAreUnique(deliveries)) {
            throw new DuplicateDeliveryException();
        }

        internalList.setAll(deliveries);
    }

    /**
     * Retrieves delivery by its id
     *
     * @param id The id of the delivery to be retrieved
     * @return Optional containing the delivery if it exists
     */
    public Optional<Delivery> getById(int id) {

        for (Delivery d : internalList) {
            if (d.getDeliveryId() == id) {
                return Optional.of(d);
            }
        }
        return Optional.empty();
    }

    /**
     * Retrieves delivery by its id
     *
     * @param id The id of the delivery to be retrieved
     * @return Optional containing the delivery if it exists
     */
    public Stream<Delivery> getByCustomerId(int id) {
        return internalList.stream().filter(d -> d.getCustomerId() == id);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Delivery> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    public void removeIf(Predicate<Delivery> predicate) {
        internalList.removeIf(predicate);
    }

    @Override
    public Iterator<Delivery> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueDeliveryList)) {
            return false;
        }

        UniqueDeliveryList otherUniquePersonList = (UniqueDeliveryList) other;
        return internalList.equals(otherUniquePersonList.internalList);
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
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean deliveriesAreUnique(List<Delivery> deliveries) {
        for (int i = 0; i < deliveries.size() - 1; i++) {
            for (int j = i + 1; j < deliveries.size(); j++) {
                if (deliveries.get(i).isSameDelivery(deliveries.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
