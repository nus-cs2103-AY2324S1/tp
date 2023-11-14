package seedu.address.model.prescription;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_PROPRANOLOL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_PROPRANOLOL;
import static seedu.address.model.prescription.Prescription.EXPIRE_PREDICATE;
import static seedu.address.model.prescription.Prescription.STOCK_PREDICATE;
import static seedu.address.testutil.CompletedPrescriptions.ERGOTAMINE;
import static seedu.address.testutil.TypicalPrescriptions.ASPIRIN;
import static seedu.address.testutil.TypicalPrescriptions.EMPTY_PRESCRIPTION;
import static seedu.address.testutil.TypicalPrescriptions.PROPRANOLOL;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PrescriptionBuilder;

public class PrescriptionTest {

    @Test
    public void isEnded() {
        // completed prescription
        assertTrue(ERGOTAMINE.isEnded());

        // incomplete prescription
        assertFalse(ASPIRIN.isEnded());

        // prescription with no end date
        Prescription prescriptionWithNoEnd = new Prescription(new Name("Aspirin"), null, null, null,
                null, null, null, null, new HashSet<>());
        assertFalse(prescriptionWithNoEnd.isEnded());
    }

    @Test
    public void isAboutToExpire() {

        Prescription expiringPrescription = new PrescriptionBuilder(EMPTY_PRESCRIPTION)
            .withExpiryDate(LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
            .build();

        Prescription notExpiringPrescription = new PrescriptionBuilder(EMPTY_PRESCRIPTION)
            .withExpiryDate(LocalDate.now().plusDays(8).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
            .build();

        // expiring
        assertTrue(EXPIRE_PREDICATE.test(expiringPrescription));

        // not expiring
        assertFalse(EXPIRE_PREDICATE.test(notExpiringPrescription));

        // no expiry date
        assertFalse(EXPIRE_PREDICATE.test(EMPTY_PRESCRIPTION));
    }

    @Test
    public void isLowInStock() {
        // low stock
        assertTrue(STOCK_PREDICATE.test(new PrescriptionBuilder(EMPTY_PRESCRIPTION)
                .withDosage("1").withStock("10").build()));

        // enough stock
        assertFalse(STOCK_PREDICATE.test(new PrescriptionBuilder(EMPTY_PRESCRIPTION)
                .withDosage("1").withStock("11").build()));

        // dosage not inputted
        assertFalse(STOCK_PREDICATE.test(new PrescriptionBuilder(EMPTY_PRESCRIPTION)
                .withStock("10").build()));

        // stock not inputted
        assertFalse(STOCK_PREDICATE.test(new PrescriptionBuilder(EMPTY_PRESCRIPTION)
                .withDosage("5").build()));

        // dosage and stock not inputted
        assertFalse(STOCK_PREDICATE.test(EMPTY_PRESCRIPTION));
    }

    @Test
    public void isSamePrescription() {
        // same object -> returns true
        assertTrue(ASPIRIN.isSamePrescription(ASPIRIN));

        // null -> returns false
        assertFalse(ASPIRIN.isSamePrescription(null));

        // same name and start date, all other attributes different -> returns true
        Prescription editedAspirin = new PrescriptionBuilder(ASPIRIN).withDosage("1").withEndDate("01/01/2027").build();
        assertTrue(ASPIRIN.isSamePrescription(editedAspirin));

        // different name, all other attributes same -> returns false
        editedAspirin = new PrescriptionBuilder(ASPIRIN).withName(VALID_NAME_PROPRANOLOL).build();
        assertFalse(ASPIRIN.isSamePrescription(editedAspirin));

        // name differs in case, all other attributes same -> returns false
        Prescription editedPropranolol = new PrescriptionBuilder(PROPRANOLOL)
            .withName(VALID_NAME_PROPRANOLOL.toLowerCase()).build();
        assertFalse(PROPRANOLOL.isSamePrescription(editedPropranolol));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_PROPRANOLOL + " ";
        editedPropranolol = new PrescriptionBuilder(PROPRANOLOL).withName(nameWithTrailingSpaces).build();
        assertFalse(PROPRANOLOL.isSamePrescription(editedPropranolol));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Prescription aspirinCopy = new PrescriptionBuilder(ASPIRIN).build();
        assertTrue(ASPIRIN.equals(aspirinCopy));

        // same object -> returns true
        assertTrue(ASPIRIN.equals(ASPIRIN));

        // null -> returns false
        assertFalse(ASPIRIN.equals(null));

        // different type -> returns false
        assertFalse(ASPIRIN.equals(5));

        // different prescription -> returns false
        assertFalse(ASPIRIN.equals(PROPRANOLOL));

        // different name -> returns false
        Prescription editedAspirin = new PrescriptionBuilder(ASPIRIN).withName(VALID_NAME_PROPRANOLOL).build();
        assertFalse(ASPIRIN.equals(editedAspirin));

        // different start date -> returns false
        editedAspirin = new PrescriptionBuilder(ASPIRIN).withStartDate(VALID_START_DATE_PROPRANOLOL).build();
        assertFalse(ASPIRIN.equals(editedAspirin));

    }

    @Test
    public void toStringMethod() {
        String expected = Prescription.class.getCanonicalName()
            + "{name=" + ASPIRIN.getName()
            + ", dosage=" + ASPIRIN.getDosage()
            + ", frequency=" + ASPIRIN.getFrequency()
            + ", startDate=" + ASPIRIN.getStartDate()
            + ", endDate=" + ASPIRIN.getEndDate()
            + ", expiryDate=" + ASPIRIN.getExpiryDate()
            + ", totalStock=" + ASPIRIN.getTotalStock()
            + ", consumptionCount=" + ASPIRIN.getConsumptionCount()
            + ", isCompleted=" + ASPIRIN.getIsCompleted()
            + ", note=" + ASPIRIN.getNote() + "}";
        assertEquals(expected, ASPIRIN.toString());
    }
}
