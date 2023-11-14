package seedu.pharmhub.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pharmhub.testutil.TypicalOrders.PANADOL_MEDICINE;
import static seedu.pharmhub.testutil.TypicalPersons.ALICE;
import static seedu.pharmhub.testutil.TypicalPersons.getTypicalPharmHub;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.pharmhub.model.medicine.Medicine;
import seedu.pharmhub.model.order.Order;
import seedu.pharmhub.model.order.OrderNumber;
import seedu.pharmhub.model.order.Status;

public class VersionedPharmHubTest {
    @Test
    public void canUndo_noActionDone_returnsFalse() {
        VersionedPharmHub newVersionedPharmHub = new VersionedPharmHub();
        assertFalse(newVersionedPharmHub.canUndo());
    }

    @Test
    public void canRedo_noActionDone_returnsFalse() {
        VersionedPharmHub newVersionedPharmHub = new VersionedPharmHub();
        assertFalse(newVersionedPharmHub.canUndo());
    }

    @Test
    public void canUndo_afterAddingOrder_returnsTrue() {
        VersionedPharmHub versionedPharmHub = new VersionedPharmHub(getTypicalPharmHub());
        versionedPharmHub.addOrder(new Order(
                new OrderNumber("1000"), ALICE, new HashSet<>(List.of(PANADOL_MEDICINE)),
                new Status(Status.OrderStatus.PENDING)));
        assertTrue(versionedPharmHub.canUndo());
    }

    @Test
    public void canRedo_afterUndo_returnsTrue() {
        VersionedPharmHub versionedPharmHub = new VersionedPharmHub(getTypicalPharmHub());
        versionedPharmHub.addOrder(new Order(
                new OrderNumber("1000"), ALICE, new HashSet<>(List.of(PANADOL_MEDICINE)),
                new Status(Status.OrderStatus.PENDING)));
        versionedPharmHub.undo();
        assertTrue(versionedPharmHub.canRedo());
    }

    @Test
    public void undo_afterDataModifyingCommand_success() {
        VersionedPharmHub versionedPharmHub = new VersionedPharmHub();
        PharmHub basePharmHub = new PharmHub();

        versionedPharmHub.addPerson(ALICE);
        versionedPharmHub.undo();
        assertEquals(basePharmHub, new PharmHub(versionedPharmHub));

        versionedPharmHub.addOrder(new Order(
                new OrderNumber("1000"), ALICE, new HashSet<>(),
                new Status(Status.OrderStatus.PENDING)));
        versionedPharmHub.undo();
        assertEquals(basePharmHub, new PharmHub(versionedPharmHub));

        versionedPharmHub.addMedicine(new Medicine("Panadol_2"));
        versionedPharmHub.undo();
        assertEquals(basePharmHub, new PharmHub(versionedPharmHub));
    }

    @Test
    public void redo_afterUndo_success() {
        VersionedPharmHub versionedPharmHub = new VersionedPharmHub();
        PharmHub basePharmHub = new PharmHub();

        basePharmHub.addPerson(ALICE);
        versionedPharmHub.addPerson(ALICE);
        versionedPharmHub.undo();
        versionedPharmHub.redo();
        assertEquals(basePharmHub, new PharmHub(versionedPharmHub));

        Order orderToAdd = new Order(
                new OrderNumber("1000"), ALICE, new HashSet<>(),
                new Status(Status.OrderStatus.PENDING));
        basePharmHub.addOrder(orderToAdd);
        versionedPharmHub.addOrder(orderToAdd);
        versionedPharmHub.undo();
        versionedPharmHub.redo();
        assertEquals(basePharmHub, new PharmHub(versionedPharmHub));

        Medicine medicineToAdd = new Medicine("Panadol_2");
        basePharmHub.addMedicine(medicineToAdd);
        versionedPharmHub.addMedicine(medicineToAdd);
        versionedPharmHub.undo();
        versionedPharmHub.redo();
        assertEquals(basePharmHub, new PharmHub(versionedPharmHub));
    }
}
