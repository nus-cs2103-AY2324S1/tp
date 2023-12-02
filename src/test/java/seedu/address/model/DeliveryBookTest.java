package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_JAMES_MILK;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDeliveries.GABRIELS_MILK;
import static seedu.address.testutil.TypicalDeliveries.getTypicalDeliveryBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.delivery.Delivery;
import seedu.address.model.delivery.exceptions.DuplicateDeliveryException;
import seedu.address.testutil.DeliveryBuilder;

public class DeliveryBookTest {

    private final DeliveryBook deliveryBook = new DeliveryBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), deliveryBook.getList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> deliveryBook.resetData(null));
    }

    @Test public void resetData_withValidReadOnlyDeliveryBook_replaceData() {
        DeliveryBook newData = getTypicalDeliveryBook();
        deliveryBook.resetData(newData);
        assertEquals(newData, deliveryBook);
    }

    @Test
    public void resetData_withDuplicateDeliveries_throwsDuplicateDeliveryException() {
        Delivery editedGabriel = new DeliveryBuilder(GABRIELS_MILK).withName(VALID_NAME_JAMES_MILK).build();
        List<Delivery> newDeliveries = Arrays.asList(GABRIELS_MILK, editedGabriel);
        DeliveryBookStub newData = new DeliveryBookStub(newDeliveries);

        assertThrows(DuplicateDeliveryException.class, () -> deliveryBook.resetData(newData));
    }

    @Test
    public void hasDelivery_nullDelivery_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> deliveryBook.hasDelivery(null));
    }

    @Test
    public void hasDelivery_deliveryNotInDeliveryBook_returnsFalse() {
        assertFalse(deliveryBook.hasDelivery(GABRIELS_MILK));
    }

    @Test
    public void hasDelivery_deliveryInDeliveryBook_returnsTrue() {
        deliveryBook.addDelivery(GABRIELS_MILK);
        assertTrue(deliveryBook.hasDelivery(GABRIELS_MILK));
    }

    @Test
    public void hasDelivery_deliveryWithSameIdentityFieldsInDeliveryBook_returnsTrue() {
        deliveryBook.addDelivery(GABRIELS_MILK);
        Delivery editedGabriel = new DeliveryBuilder(GABRIELS_MILK).withName(VALID_NAME_JAMES_MILK).build();
        assertTrue(deliveryBook.hasDelivery(editedGabriel));
    }

    @Test
    public void getDeliveryList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> deliveryBook.getList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = DeliveryBook.class.getCanonicalName() + "{deliveries=" + deliveryBook.getList() + "}";
        assertEquals(expected, deliveryBook.toString());
    }

    /**
     * A stub ReadOnlyBook whose delivery list can violate interface constraints.
     */
    private static class DeliveryBookStub implements ReadOnlyBook<Delivery> {
        private final ObservableList<Delivery> deliveries = FXCollections.observableArrayList();

        DeliveryBookStub(Collection<Delivery> deliveries) {
            this.deliveries.setAll(deliveries);
        }

        @Override
        public ObservableList<Delivery> getList() {
            return deliveries;
        }

        @Override
        public Optional<Delivery> getById(int id) {
            return Optional.empty();
        }
    }
}

