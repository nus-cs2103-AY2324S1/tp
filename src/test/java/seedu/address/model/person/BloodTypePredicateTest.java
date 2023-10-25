package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PatientBuilder;

public class BloodTypePredicateTest {
    BloodTypePredicate APlusPredicate = new BloodTypePredicate("A+");
    BloodTypePredicate AMinusPredicate = new BloodTypePredicate("A-");
    BloodTypePredicate BPlusPredicate = new BloodTypePredicate("B+");
    BloodTypePredicate BMinusPredicate = new BloodTypePredicate("B-");
    BloodTypePredicate ABPlusPredicate = new BloodTypePredicate("AB+");
    BloodTypePredicate ABMinusPredicate = new BloodTypePredicate("AB-");
    BloodTypePredicate OPlusPredicate = new BloodTypePredicate("O+");
    BloodTypePredicate OMinusPredicate = new BloodTypePredicate("O-");

    @Test
    public void testCorrectBloodTypePredicate() {
        // Test with a person having A+ blood type.
        assertTrue(APlusPredicate.test(new PatientBuilder().withBloodType("A+").build()));

        // Test with a person having A- blood type.
        assertTrue(AMinusPredicate.test(new PatientBuilder().withBloodType("A-").build()));

        // Test with a person having B+ blood type.
        assertTrue(BPlusPredicate.test(new PatientBuilder().withBloodType("B+").build()));

        // Test with a person having B- blood type.
        assertTrue(BMinusPredicate.test(new PatientBuilder().withBloodType("B-").build()));

        // Test with a person having AB+ blood type.
        assertTrue(ABPlusPredicate.test(new PatientBuilder().withBloodType("AB+").build()));

        // Test with a person having AB- blood type.
        assertTrue(ABMinusPredicate.test(new PatientBuilder().withBloodType("AB-").build()));

        // Test with a person having O+ blood type.
        assertTrue(OPlusPredicate.test(new PatientBuilder().withBloodType("O+").build()));

        // Test with a person having O- blood type.
        assertTrue(OMinusPredicate.test(new PatientBuilder().withBloodType("O-").build()));
    }

    @Test
    public void testWrongBloodTypePredicate() {

        assertFalse(APlusPredicate.test(new PatientBuilder().withBloodType("A-").build()));
        assertFalse(AMinusPredicate.test(new PatientBuilder().withBloodType("AB+").build()));
        assertFalse(BPlusPredicate.test(new PatientBuilder().withBloodType("B-").build()));
        assertFalse(BMinusPredicate.test(new PatientBuilder().withBloodType("A-").build()));
        assertFalse(ABPlusPredicate.test(new PatientBuilder().withBloodType("A+").build()));
        assertFalse(ABMinusPredicate.test(new PatientBuilder().withBloodType("B+").build()));
    }

}