package seedu.address.model.animal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AnimalBuilder;


public class KeywordPredicateTest {

    private List<String> firstPredicateKeywordList = List.of("first");
    private List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
    private KeywordPredicate firstPredicate = new KeywordPredicate(firstPredicateKeywordList);
    private KeywordPredicate secondPredicate = new KeywordPredicate(secondPredicateKeywordList);

    @Test
    public void equals_samePredicate_returnsTrue() {
        assertTrue(firstPredicate.equals(firstPredicate));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        KeywordPredicate firstPredicateCopy = new KeywordPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));
    }

    @Test
    public void equals_nullOtherPredicate_returnsFalse() {
        assertFalse(firstPredicate.equals(null));
    }

    @Test
    public void equals_null_returnsFalse() {
        assertThrows(NullPointerException.class, () -> ((KeywordPredicate) null).equals(firstPredicate));
    }

    @Test
    public void equals_differentTypes_returnsFalse() {
        assertFalse(firstPredicate.equals(1));
    }

    @Test
    public void equals_differentPredicate_returnsFalse() {
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One word
        KeywordPredicate predicate = new KeywordPredicate(Arrays.asList("Tofu", "", "", "", "", "", ""));
        assertTrue(predicate.test(new AnimalBuilder().withName("Tofu").build()));

        // Multiple words
        predicate = new KeywordPredicate(Arrays.asList("Tofu Muffin", "", "", "", "", "", ""));
        assertTrue(predicate.test(new AnimalBuilder().withName("Tofu Muffin").build()));

        // Mixed-case keywords
        predicate = new KeywordPredicate(Arrays.asList("tOfU mUfFin", "", "", "", "", "", ""));
        assertTrue(predicate.test(new AnimalBuilder().withName("Tofu Muffin").build()));

        // Trailing spaces in keywords
        predicate = new KeywordPredicate(Arrays.asList("Tofu  Muffin", "", "", "", "", "", ""));
        assertTrue(predicate.test(new AnimalBuilder().withName("Tofu Muffin").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Partial keywords
        KeywordPredicate predicate = new KeywordPredicate(Arrays.asList("Tofu Muffin", "", "", "", "", "", ""));
        assertFalse(predicate.test(new AnimalBuilder().withName("Tofu Muff").build()));

        // Only one matching keyword
        predicate = new KeywordPredicate(Arrays.asList("Tofu Muffin", "", "", "", "", "", ""));
        assertFalse(predicate.test(new AnimalBuilder().withName("Tofu").build()));

        // Non-matching keywords
        predicate = new KeywordPredicate(Arrays.asList("Tofu", "", "", "", "", "", ""));
        assertFalse(predicate.test(new AnimalBuilder().withName("Muffin").build()));
    }

    @Test
    public void test_petIdContainsKeywords_returnTrue() {
        KeywordPredicate predicate = new KeywordPredicate(Arrays.asList("", "1234", "", "", "", "", ""));
        assertTrue(predicate.test(new AnimalBuilder().withPetId("1234").build()));
    }

    @Test
    public void test_petIdDoesNotContainKeywords_returnFalse() {
        // Partial keywords
        KeywordPredicate predicate = new KeywordPredicate(Arrays.asList("", "123", "", "", "", "", ""));
        assertFalse(predicate.test(new AnimalBuilder().withPetId("1234").build()));

        // Non-matching keywords
        predicate = new KeywordPredicate(Arrays.asList("", "9876", "", "", "", "", ""));
        assertFalse(predicate.test(new AnimalBuilder().withPetId("1234").build()));

        predicate = new KeywordPredicate(Arrays.asList("", "nonInteger", "", "", "", "", ""));
        assertFalse(predicate.test(new AnimalBuilder().withPetId("1234").build()));

        // Longer keyword
        predicate = new KeywordPredicate(Arrays.asList("", "12345", "", "", "", "", ""));
        assertFalse(predicate.test(new AnimalBuilder().withPetId("1234").build()));

        // Spaces in keyword
        predicate = new KeywordPredicate(Arrays.asList("", "1 2 3 4", "", "", "", "", ""));
        assertFalse(predicate.test(new AnimalBuilder().withPetId("1234").build()));
    }

    @Test
    public void test_dateContainsKeywords_returnTrue() {
        // Matching date
        KeywordPredicate predicate = new KeywordPredicate(
                Arrays.asList("", "", "2020-01-01", "", "", "", ""));
        assertTrue(predicate.test(new AnimalBuilder().withDateOfBirth("2020-01-01").build()));

        predicate = new KeywordPredicate(
                Arrays.asList("", "", "", "2020-01-01", "", "", ""));
        assertTrue(predicate.test(new AnimalBuilder().withAdmissionDate("2020-01-01").build()));
    }

    @Test
    public void test_dateDoesNotContainKeywords_returnFalse() {
        // Non-matching date
        KeywordPredicate predicate = new KeywordPredicate(
                Arrays.asList("", "", "2020-01-01", "", "", "", ""));
        assertFalse(predicate.test(new AnimalBuilder().withDateOfBirth("2020-01-02").build()));
        predicate = new KeywordPredicate(
                Arrays.asList("", "", "", "2020-01-01", "", "", ""));
        assertFalse(predicate.test(new AnimalBuilder().withAdmissionDate("2020-01-02").build()));

        // Partial keywords
        predicate = new KeywordPredicate(
                Arrays.asList("", "", "2020-01", "", "", "", ""));
        assertFalse(predicate.test(new AnimalBuilder().withDateOfBirth("2020-01-02").build()));
        predicate = new KeywordPredicate(
                Arrays.asList("", "", "", "2020-01", "", "", ""));
        assertFalse(predicate.test(new AnimalBuilder().withAdmissionDate("2020-01-02").build()));

        // Longer keyword
        predicate = new KeywordPredicate(
                Arrays.asList("", "", "2020-01-01-01", "", "", "", ""));
        assertFalse(predicate.test(new AnimalBuilder().withDateOfBirth("2020-01-01").build()));
        predicate = new KeywordPredicate(
                Arrays.asList("", "", "", "2020-01-01-01", "", "", ""));
        assertFalse(predicate.test(new AnimalBuilder().withAdmissionDate("2020-01-01").build()));

        // Spaces in keyword
        predicate = new KeywordPredicate(
                Arrays.asList("", "", "2020 -01 -01", "", "", "", ""));
        assertFalse(predicate.test(new AnimalBuilder().withDateOfBirth("2020-01-01").build()));
        predicate = new KeywordPredicate(
                Arrays.asList("", "", "", "2020 -01 -01", "", "", ""));
        assertFalse(predicate.test(new AnimalBuilder().withAdmissionDate("2020-01-01").build()));

        // Non-date keyword
        predicate = new KeywordPredicate(
                Arrays.asList("", "", "nonDate", "", "", "", ""));
        assertFalse(predicate.test(new AnimalBuilder().withDateOfBirth("2020-01-01").build()));
        predicate = new KeywordPredicate(
                Arrays.asList("", "", "", "nonDate", "", "", ""));
        assertFalse(predicate.test(new AnimalBuilder().withAdmissionDate("2020-01-01").build()));
    }

    @Test
    public void test_speciesContainsKeywords_returnTrue() {
        // Matching species
        KeywordPredicate predicate = new KeywordPredicate(
                Arrays.asList("", "", "", "", "cat", "", ""));
        assertTrue(predicate.test(new AnimalBuilder().withSpecies("cat").build()));
    }

    @Test
    public void test_speciesDoesNotContainKeyword_returnFalse() {
        // Non-matching species
        KeywordPredicate predicate = new KeywordPredicate(
                Arrays.asList("", "", "", "", "cat", "", ""));
        assertFalse(predicate.test(new AnimalBuilder().withSpecies("dog").build()));

        // Partial keywords
        predicate = new KeywordPredicate(
                Arrays.asList("", "", "", "", "ca", "", ""));
        assertFalse(predicate.test(new AnimalBuilder().withSpecies("cat").build()));

        // Longer keyword
        predicate = new KeywordPredicate(
                Arrays.asList("", "", "", "", "cats", "", ""));
        assertFalse(predicate.test(new AnimalBuilder().withSpecies("cat").build()));

        // Spaces in keyword
        predicate = new KeywordPredicate(
                Arrays.asList("", "", "", "", "c a t", "", ""));
        assertFalse(predicate.test(new AnimalBuilder().withSpecies("cat").build()));
    }

    @Test
    public void test_breedContainsKeywords_returnTrue() {
        // Matching breed
        KeywordPredicate predicate = new KeywordPredicate(Arrays.asList("", "", "", "", "", "", "persian"));
        assertTrue(predicate.test(new AnimalBuilder().withBreed("persian").build()));

        // Matching breed with multiple words
        predicate = new KeywordPredicate(Arrays.asList("", "", "", "", "", "", "golden retriever"));
        assertTrue(predicate.test(new AnimalBuilder().withBreed("golden retriever").build()));
    }

    @Test
    public void test_breedDoesNotContainKeywords_returnFalse() {
        // Non-matching breed
        KeywordPredicate predicate = new KeywordPredicate(Arrays.asList("", "", "", "", "", "", "persian"));
        assertFalse(predicate.test(new AnimalBuilder().withBreed("ragdoll").build()));

        // Partial keywords
        predicate = new KeywordPredicate(Arrays.asList("", "", "", "", "", "", "persi"));
        assertFalse(predicate.test(new AnimalBuilder().withBreed("persian").build()));

        // Longer keyword
        predicate = new KeywordPredicate(Arrays.asList("", "", "", "", "", "", "persians"));
        assertFalse(predicate.test(new AnimalBuilder().withBreed("persian").build()));

        // Spaces in keyword
        predicate = new KeywordPredicate(Arrays.asList("", "", "", "", "", "", "p e r s i a n"));
        assertFalse(predicate.test(new AnimalBuilder().withBreed("persian").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        KeywordPredicate predicate = new KeywordPredicate(keywords);

        String expectedString = KeywordPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expectedString, predicate.toString());
    }
}
