package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PatientBuilder;

public class BloodTypePredicateTest {
    @Test
    public void equals() {
        String firstPredicateKeyword = "Blood Type A+";
        String secondPredicateKeyword = "Blood Type B-";

        BloodTypePredicate firstPredicate = new BloodTypePredicate(firstPredicateKeyword);
        BloodTypePredicate secondPredicate = new BloodTypePredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BloodTypePredicate firstPredicateCopy = new BloodTypePredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void testCorrectBloodTypePredicate() {
        BloodTypePredicate aPlusPredicate = new BloodTypePredicate("Blood Type A+");
        BloodTypePredicate aMinusPredicate = new BloodTypePredicate("Blood Type A-");
        BloodTypePredicate bPlusPredicate = new BloodTypePredicate("Blood Type B+");
        BloodTypePredicate bMinusPredicate = new BloodTypePredicate("Blood Type B-");
        BloodTypePredicate abPlusPredicate = new BloodTypePredicate("Blood Type AB+");
        BloodTypePredicate abMinusPredicate = new BloodTypePredicate("Blood Type AB-");
        BloodTypePredicate oPlusPredicate = new BloodTypePredicate("Blood Type O+");
        BloodTypePredicate oMinusPredicate = new BloodTypePredicate("Blood Type O-");
        // Test with a person having A+ blood type.
        assertTrue(aPlusPredicate.test(new PatientBuilder().withBloodType("A+").build()));

        // Test with a person having A- blood type.
        assertTrue(aMinusPredicate.test(new PatientBuilder().withBloodType("A-").build()));

        // Test with a person having B+ blood type.
        assertTrue(bPlusPredicate.test(new PatientBuilder().withBloodType("B+").build()));

        // Test with a person having B- blood type.
        assertTrue(bMinusPredicate.test(new PatientBuilder().withBloodType("B-").build()));

        // Test with a person having AB+ blood type.
        assertTrue(abPlusPredicate.test(new PatientBuilder().withBloodType("AB+").build()));

        // Test with a person having AB- blood type.
        assertTrue(abMinusPredicate.test(new PatientBuilder().withBloodType("AB-").build()));

        // Test with a person having O+ blood type.
        assertTrue(oPlusPredicate.test(new PatientBuilder().withBloodType("O+").build()));

        // Test with a person having O- blood type.
        assertTrue(oMinusPredicate.test(new PatientBuilder().withBloodType("O-").build()));
    }

    @Test
    public void testWrongBloodTypePredicate() {
        BloodTypePredicate aPlusPredicate = new BloodTypePredicate("Blood Type A+");
        BloodTypePredicate aMinusPredicate = new BloodTypePredicate("Blood Type A-");
        BloodTypePredicate bPlusPredicate = new BloodTypePredicate("Blood Type B+");
        BloodTypePredicate bMinusPredicate = new BloodTypePredicate("Blood Type B-");
        BloodTypePredicate abPlusPredicate = new BloodTypePredicate("Blood Type AB+");
        BloodTypePredicate abMinusPredicate = new BloodTypePredicate("Blood Type AB-");

        assertFalse(aPlusPredicate.test(new PatientBuilder().withBloodType("A-").build()));
        assertFalse(aMinusPredicate.test(new PatientBuilder().withBloodType("AB+").build()));
        assertFalse(bPlusPredicate.test(new PatientBuilder().withBloodType("B-").build()));
        assertFalse(bMinusPredicate.test(new PatientBuilder().withBloodType("A-").build()));
        assertFalse(abPlusPredicate.test(new PatientBuilder().withBloodType("A+").build()));
        assertFalse(abMinusPredicate.test(new PatientBuilder().withBloodType("B+").build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "keyword1";
        BloodTypePredicate predicate = new BloodTypePredicate("Blood type keyword1");

        String expected = BloodTypePredicate.class.getCanonicalName() + "{keywords=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
}
