package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PatientBuilder;

public class BloodTypePredicateTest {
    @Test
    public void equals() {
        String firstPredicateKeyword = "A+";
        String secondPredicateKeyword = "B-";

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
        BloodTypePredicate aPlusPredicate = new BloodTypePredicate("A+");
        BloodTypePredicate aMinusPredicate = new BloodTypePredicate("A-");
        BloodTypePredicate bPlusPredicate = new BloodTypePredicate("B+");
        BloodTypePredicate bMinusPredicate = new BloodTypePredicate("B-");
        BloodTypePredicate abPlusPredicate = new BloodTypePredicate("AB+");
        BloodTypePredicate abMinusPredicate = new BloodTypePredicate("AB-");
        BloodTypePredicate oPlusPredicate = new BloodTypePredicate("O+");
        BloodTypePredicate oMinusPredicate = new BloodTypePredicate("O-");
        // Test with a person having A+ .
        assertTrue(aPlusPredicate.test(new PatientBuilder().withBloodType("A+").build()));

        // Test with a person having A- .
        assertTrue(aMinusPredicate.test(new PatientBuilder().withBloodType("A-").build()));

        // Test with a person having B+ .
        assertTrue(bPlusPredicate.test(new PatientBuilder().withBloodType("B+").build()));

        // Test with a person having B- .
        assertTrue(bMinusPredicate.test(new PatientBuilder().withBloodType("B-").build()));

        // Test with a person having AB+ .
        assertTrue(abPlusPredicate.test(new PatientBuilder().withBloodType("AB+").build()));

        // Test with a person having AB- .
        assertTrue(abMinusPredicate.test(new PatientBuilder().withBloodType("AB-").build()));

        // Test with a person having O+ .
        assertTrue(oPlusPredicate.test(new PatientBuilder().withBloodType("O+").build()));

        // Test with a person having O- .
        assertTrue(oMinusPredicate.test(new PatientBuilder().withBloodType("O-").build()));
    }

    @Test
    public void testWrongBloodTypePredicate() {
        BloodTypePredicate aPlusPredicate = new BloodTypePredicate(" A+");
        BloodTypePredicate aMinusPredicate = new BloodTypePredicate(" A-");
        BloodTypePredicate bPlusPredicate = new BloodTypePredicate(" B+");
        BloodTypePredicate bMinusPredicate = new BloodTypePredicate(" B-");
        BloodTypePredicate abPlusPredicate = new BloodTypePredicate(" AB+");
        BloodTypePredicate abMinusPredicate = new BloodTypePredicate(" AB-");

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
        BloodTypePredicate predicate = new BloodTypePredicate("keyword1");

        String expected = BloodTypePredicate.class.getCanonicalName() + "{keywords=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
}
