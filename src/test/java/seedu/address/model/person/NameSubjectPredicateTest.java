package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NameSubjectPredicateTest {

    @Test
    public void equals() {
        List<String> nameKeywords = Collections.singletonList("Alice");
        List<String> subjectKeywords = Arrays.asList("Math", "Science");

        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(nameKeywords);
        SubjectContainsKeywordsPredicate subjectPredicate = new SubjectContainsKeywordsPredicate(subjectKeywords);

        NameSubjectPredicate firstPredicate = new NameSubjectPredicate(namePredicate, subjectPredicate);

        assertTrue(firstPredicate.equals(firstPredicate));

        NameSubjectPredicate firstPredicateCopy = new NameSubjectPredicate(namePredicate, subjectPredicate);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        assertFalse(firstPredicate.equals(1));

        assertFalse(firstPredicate.equals(null));

        NameSubjectPredicate secondPredicate = new NameSubjectPredicate(
                new NameContainsKeywordsPredicate(Collections.singletonList("Bob")),
                new SubjectContainsKeywordsPredicate(Collections.singletonList("English"))
        );
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameSubjectPredicate_returnsTrue() {
        //Both name and subject keywords match
        NameContainsKeywordsPredicate name =
                new NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        SubjectContainsKeywordsPredicate subject =
                new SubjectContainsKeywordsPredicate(Collections.singletonList("Maths"));
        NameSubjectPredicate predicate = new NameSubjectPredicate(name, subject);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withSubject("Maths").build()));

    }

    @Test
    public void test_nameSubjectPredicate_returnsFalse() {
        // Neither name nor subject match
        NameContainsKeywordsPredicate name =
                new NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        SubjectContainsKeywordsPredicate subject =
                new SubjectContainsKeywordsPredicate(Collections.singletonList("Math"));
        NameSubjectPredicate predicate = new NameSubjectPredicate(name, subject);
        assertFalse(predicate.test(new PersonBuilder().withName("Bob").withSubject("English").build()));

        //Only name keyword matches
        name = new NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        subject = new SubjectContainsKeywordsPredicate(Collections.singletonList("Biology"));
        predicate = new NameSubjectPredicate(name, subject);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withSubject("Maths").build()));

        //Only subject keyword matches
        name = new NameContainsKeywordsPredicate(Collections.singletonList("Bob"));
        subject = new SubjectContainsKeywordsPredicate(Collections.singletonList("Math"));
        predicate = new NameSubjectPredicate(name, subject);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withSubject("Math").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> nameKeywords = Collections.singletonList("Alice");
        List<String> subjectKeywords = Arrays.asList("Math", "Science");
        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(nameKeywords);
        SubjectContainsKeywordsPredicate subjectPredicate = new SubjectContainsKeywordsPredicate(subjectKeywords);
        NameSubjectPredicate predicate = new NameSubjectPredicate(namePredicate, subjectPredicate);

        String expected = NameSubjectPredicate.class.getCanonicalName() + "{name=" + namePredicate + ", subject="
                + subjectPredicate + "}";
        assertEquals(expected, predicate.toString());
    }
}
