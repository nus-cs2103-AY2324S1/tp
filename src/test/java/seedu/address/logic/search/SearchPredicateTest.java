package seedu.address.logic.search;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class SearchPredicateTest {

    private static Map<String, String> TEST_PERSON;

    @BeforeAll
    public static void setUp() {
        TEST_PERSON = new HashMap<>();
        TEST_PERSON.put("field A", "Lorem ipsum, dolor sit amet.");
        TEST_PERSON.put("field B", "Ut enim; ad@minim veNiam!");
    }

    private static SearchPredicate generatePredicate(String str) {
        return new SingleTextSearchPredicate(str, true);
    }

    private static <T> T get(Supplier<T> supplier) {
        return supplier.get();
    }

    private static FieldRanges joinTest(
            String predicateA, String predicateB, String method
    ) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        SearchPredicate a = generatePredicate(predicateA);
        SearchPredicate b = generatePredicate(predicateB);
        return ((SearchPredicate) a.getClass().getMethod(method, SearchPredicate.class)
                .invoke(a,b)).test(TEST_PERSON);
    }

    @Test
    public void test_andJoiner_success()
            throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        FieldRanges expected = get(() -> {
            FieldRanges fr = new FieldRanges();
            fr.put("field A", new Range(0, 4));
            fr.put("field B", new Range(0, 2));
            return fr;
        });
        FieldRanges actual = joinTest("lorem", "ut", "and");
        assertEquals(expected, actual);
    }

    @Test
    public void test_orJoiner_success() {
        SearchPredicate noMatch = generatePredicate("abcdef");
        SearchPredicate match = generatePredicate("lorem");
        SearchPredicate orPredicate = noMatch.or(match);
        FieldRanges actual = orPredicate.test(TEST_PERSON);
        FieldRanges expected = get(() -> {
            FieldRanges fr = new FieldRanges();
            fr.put("field A", new Range(0, 4));
            return fr;
        });
        assertEquals(expected, actual);
    }

    @Test
    public void test_andJoiner_fail() {

    }

    @Test public void test_orJoiner_fail() {

    }

    @Test
    public void test_negate() {}

    @Test
    public void test_lazyAndJoiner() {}

    @Test
    public void test_notLazyOrJoiner() {}

    @Test
    public void test_close_doNothing() {}

    @Test
    public void test_open_doNothing() {}


}