package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PatientBuilder;

public class BloodTypePredicateTest {
    @Test
    public void correctBloodTypePredicate_returnsTrue() {
        BloodTypePredicate aPositivePredicate = new BloodTypePredicate("A+");
        BloodTypePredicate bPositivePredicate = new BloodTypePredicate("B+");
        BloodTypePredicate oPositivePredicate = new BloodTypePredicate("O+");
        BloodTypePredicate abPositivePredicate = new BloodTypePredicate("AB+");
        BloodTypePredicate aNegativePredicate = new BloodTypePredicate("A-");
        BloodTypePredicate bNegativePredicate = new BloodTypePredicate("B-");
        BloodTypePredicate oNegativePredicate = new BloodTypePredicate("O-");
        BloodTypePredicate abNegativePredicate = new BloodTypePredicate("AB-");
        assertTrue(aPositivePredicate.test(new PatientBuilder().withBloodType("A+").build()));
        assertTrue(bPositivePredicate.test(new PatientBuilder().withBloodType("B+").build()));
        assertTrue(oPositivePredicate.test(new PatientBuilder().withBloodType("O+").build()));
        assertTrue(abPositivePredicate.test(new PatientBuilder().withBloodType("AB+").build()));
        assertTrue(aNegativePredicate.test(new PatientBuilder().withBloodType("A-").build()));
        assertTrue(bNegativePredicate.test(new PatientBuilder().withBloodType("B-").build()));
        assertTrue(oNegativePredicate.test(new PatientBuilder().withBloodType("O-").build()));
        assertTrue(abNegativePredicate.test(new PatientBuilder().withBloodType("AB-").build()));
    }

    @Test
    public void wrongBloodTypePredicate_returnsFalse() {
        BloodTypePredicate bPositivePredicate = new BloodTypePredicate("B+");
        BloodTypePredicate aNegativePredicate = new BloodTypePredicate("A-");
        // Test with a person having a different gender
        assertFalse(bPositivePredicate.test(new PatientBuilder().withBloodType("O+").build()));
        assertFalse(aNegativePredicate.test(new PatientBuilder().withBloodType("B-").build()));
    }

    @Test
    public void equals() {
        String firstPredicateKeyword = "B+";
        String secondPredicateKeyword = "A-";

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
    public void toStringMethod() {
        String keyword = "keyword1";
        BloodTypePredicate predicate = new BloodTypePredicate(keyword);

        String expected = BloodTypePredicate.class.getCanonicalName() + "{keywords=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
}
