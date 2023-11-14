package seedu.address.model.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EmployeeBuilder;

public class EmployeeContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        EmployeeContainsKeywordsPredicate firstPredicate =
                new EmployeeContainsKeywordsPredicate(firstPredicateKeywordList);
        EmployeeContainsKeywordsPredicate secondPredicate =
                new EmployeeContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EmployeeContainsKeywordsPredicate firstPredicateCopy =
                new EmployeeContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different employee -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_employeeContainsKeywords_returnsTrue() {
        // One keyword - name
        EmployeeContainsKeywordsPredicate predicate =
                new EmployeeContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new EmployeeBuilder().withName("Alice Bob").build()));

        // One keyword - position
        predicate = new EmployeeContainsKeywordsPredicate(Collections.singletonList("Manager"));
        assertTrue(predicate.test(new EmployeeBuilder().withPosition("Manager").build()));

        // One keyword - department
        predicate = new EmployeeContainsKeywordsPredicate(Collections.singletonList("HR"));
        assertTrue(predicate.test(new EmployeeBuilder().withDepartments("HR").build()));

        // One keyword - phone
        predicate = new EmployeeContainsKeywordsPredicate(Collections.singletonList("91234567"));
        assertTrue(predicate.test(new EmployeeBuilder().withPhone("91234567").build()));

        // One keyword - email
        predicate = new EmployeeContainsKeywordsPredicate(Collections.singletonList("alice@example.com"));
        assertTrue(predicate.test(new EmployeeBuilder().withEmail("alice@example.com").build()));

        // One keyword - id
        predicate = new EmployeeContainsKeywordsPredicate(Collections.singletonList("EID1234-5678"));
        assertTrue(predicate.test(new EmployeeBuilder().withId("EID1234-5678").build()));

        // Multiple keywords - name
        predicate = new EmployeeContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new EmployeeBuilder().withName("Alice Bob").build()));

        // Multiple keywords - position
        predicate = new EmployeeContainsKeywordsPredicate(Arrays.asList("Manager", "Supervisor"));
        assertTrue(predicate.test(new EmployeeBuilder().withPosition("Manager").build()));

        // Multiple keywords - department
        predicate = new EmployeeContainsKeywordsPredicate(Arrays.asList("HR", "IT"));
        assertTrue(predicate.test(new EmployeeBuilder().withDepartments("HR").build()));

        // Multiple keywords - phone
        predicate = new EmployeeContainsKeywordsPredicate(Arrays.asList("91234567", "87654321"));
        assertTrue(predicate.test(new EmployeeBuilder().withPhone("91234567").build()));

        // Multiple keywords - email
        predicate = new EmployeeContainsKeywordsPredicate(Arrays.asList("abc@def.com", "ghi@jkl.com"));
        assertTrue(predicate.test(new EmployeeBuilder().withEmail("abc@def.com").build()));

        // Multiple keywords - id
        predicate = new EmployeeContainsKeywordsPredicate(Arrays.asList("EID1234-5678", "EID8765-4321"));
        assertTrue(predicate.test(new EmployeeBuilder().withId("EID1234-5678").build()));

        // Multiple keywords across different fields
        predicate = new EmployeeContainsKeywordsPredicate(Arrays.asList("Alice", "Manager"));
        assertTrue(predicate.test(new EmployeeBuilder().withName("Alice").withPosition("Manager").build()));

        // Only one matching keyword
        predicate = new EmployeeContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new EmployeeBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new EmployeeContainsKeywordsPredicate(Arrays.asList("aLIce", "mAnaGeR"));
        assertTrue(predicate.test(new EmployeeBuilder().withName("Alice Bob").withPosition("manager").build()));
    }

    @Test
    public void test_employeeDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        EmployeeContainsKeywordsPredicate predicate = new EmployeeContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EmployeeBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new EmployeeContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new EmployeeBuilder().withName("Alice Bob").build()));

        // Keywords match salary but not others
        predicate = new EmployeeContainsKeywordsPredicate(Arrays.asList("1234"));
        assertFalse(predicate.test(new EmployeeBuilder().withSalary("5678")
                .withEmail("alice@email.com").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        EmployeeContainsKeywordsPredicate predicate = new EmployeeContainsKeywordsPredicate(keywords);

        String expected = EmployeeContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
