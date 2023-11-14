package seedu.pharmhub.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.pharmhub.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.pharmhub.model.order.exceptions.DuplicateOrderException;
import seedu.pharmhub.model.order.exceptions.OrderNotFoundException;
import seedu.pharmhub.model.person.Person;


/**
 * A list of orders that enforces uniqueness between its elements and does not allow nulls.
 * An order is considered unique by comparing using {@code Order#isSameOrder(Order)}. As such, adding of
 * orders uses Order#isSameOrder(Order) for equality so as to ensure that the order being added or updated is
 * unique in terms of identity in the OrderList.
 *
 * Supports a minimal set of list operations.
 *
 * @see Order#isSameOrder(Order)
 */
public class OrderList implements Iterable<Order> {
    private final ObservableList<Order> internalList = FXCollections.observableArrayList();
    private final ObservableList<Order> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent order as the given argument.
     */
    public boolean contains(Order toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameOrder);
    }
    /**
     * Adds an order to the list.
     */
    public void add(Order toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateOrderException();
        }
        internalList.add(toAdd);
    }
    /**
     * Removes the equivalent order from the list.
     * The order must exist in the list.
     */
    public void remove(Order toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new OrderNotFoundException();
        }
    }


    /**
     * Deletes orders in this list which belong to given person.
     * @param person
     */
    public void removeOrdersWithPerson(Person person) {
        requireNonNull(person);
        List<Order> temp = internalList.stream().filter(x -> !x.getPerson().equals(person))
                .collect(Collectors.toList());
        this.setOrders(temp);
    }

    /**
     * Edits orders in this list which belong to given person.
     * @param person
     */
    public void editOrdersWithPerson(Person person, Person newPerson) {
        requireNonNull(person);
        List<Order> temp = internalList.stream().map(x -> x.getPerson().equals(person)
                ? new Order(x.getOrderNumber(), newPerson, x.getMedicines(), x.getStatus()) : x)
                .collect(Collectors.toList());
        this.setOrders(temp);
    }

    /**
     * Gets an order from the orderList based on its orderNumber.
     * @param orderNumber
     * @return Order with specified orderNumber, if any.
     */
    public Optional<Order> getOrder(String orderNumber) {
        Stream<Order> filtered = internalList.stream().filter(order ->
                order.getOrderNumber().toString().equals(orderNumber));
        return filtered.findFirst();
    }

    public void setOrders(OrderList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code orders}.
     */
    public void setOrders(List<Order> orders) {
        requireAllNonNull(orders);
        internalList.setAll(orders);
    }
    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */

    public void setOrder(Order target, Order editedOrder) {
        requireAllNonNull(target, editedOrder);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new OrderNotFoundException();
        }

        if (!target.isSameOrder(editedOrder) && contains(editedOrder)) {
            throw new DuplicateOrderException();
        }

        internalList.set(index, editedOrder);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Order> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Order> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }


        // instanceof handles nulls
        if (!(other instanceof OrderList)) {
            return false;
        }

        OrderList orderList = (OrderList) other;
        return internalList.equals(orderList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }
}
