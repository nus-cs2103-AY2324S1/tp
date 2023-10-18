package seedu.address.logic.search;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.address.model.person.Person;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class SingleTextSearchMatcherTest {

    private static Map<String, String> TEST_PERSON;

    @BeforeAll
    public static void setUp() {
        TEST_PERSON = new HashMap<>();
        TEST_PERSON.put("field A", "Lorem ipsum, dolor sit amet.");
        TEST_PERSON.put("field B", "Ut enim; ad@minim veNiam!");
        TEST_PERSON.put("tagABC", null);
        TEST_PERSON.put("tag123", null);
    }

    private static String testMatchString(String str, boolean isCaseIgnored) {
        SearchMatcher predicate =
                new SingleTextSearchMatcher(str);
        predicate.setFlag(SearchMatcher.Flag.CASE_SENSITIVITY, !isCaseIgnored);
        FieldRanges result = predicate.test(TEST_PERSON);
        if (result.isEmpty()) {
            return null;
        }
        Map.Entry<String, Range> match = result.entrySet().iterator().next();
        Range range = match.getValue();
        String strToMatch = TEST_PERSON.get(match.getKey());
        if (strToMatch == null) {
            strToMatch = match.getKey();
        }
        return range.getSubstring(strToMatch);
    }

    public static Predicate<Person> createPredicate(String s) {
        return new SearchPredicate(new SingleTextSearchMatcher(s));
    }

    @Test
    public void test_exactMatch() {
        assertEquals("sit", testMatchString("sit", true));
        assertEquals("Lorem", testMatchString("Lorem", true));
        assertEquals("enim;", testMatchString("enim;", true));
        assertEquals("ad@min", testMatchString("ad@min", true));
    }

    @Test
    public void test_exactNoMatch_unsuccessful() {
        assertNull(testMatchString("abcdef", true));
    }

    @Test
    public void test_matchSymbols_successful() {
        assertEquals(",", testMatchString(",", true));
        assertEquals(";", testMatchString(";", true));
        assertEquals("d@", testMatchString("d@", true));
    }

    @Test
    public void test_stringStartAndEnd() {
        assertEquals(".", testMatchString(".", true));
        assertEquals("!", testMatchString("!", false));
        assertEquals("L", testMatchString("L", true));
        assertEquals("U", testMatchString("U", false));
    }

    @Test
    public void test_fullString() {
        assertEquals("Lorem ipsum, dolor sit amet.",
                testMatchString("Lorem ipsum, dolor sit amet.", true));
        assertEquals("Ut enim; ad@minim veNiam!",
                testMatchString("Ut enim; ad@minim veNiam!", false));
    }

    @Test
    public void test_ignoreCaseMatch() {
        assertEquals("Lorem", testMatchString("lorem", true));
        assertEquals("veN", testMatchString("ven", true));
        assertEquals("ipsum", testMatchString("ipSum", true));
        assertEquals("enim", testMatchString("eniM", true));
    }

    @Test
    public void test_withCaseMatch() {
        assertEquals("Lorem", testMatchString("Lorem", true));
        assertEquals("veN", testMatchString("veN", true));
    }

    @Test
    public void test_withCaseNoMatch_unsuccessful() {
        assertNull(testMatchString("lorem", false));
        assertNull(testMatchString("ven", false));
        assertNull(testMatchString("ipSum", false));
        assertNull(testMatchString("eniM", false));
    }

    @Test
    public void test_withTagMatch() {
        assertEquals("ABC", testMatchString("ABC", true));
        assertNull(testMatchString("abc", false));
        assertEquals("12", testMatchString("12", true));
        assertEquals("12", testMatchString("12", false));
    }

}