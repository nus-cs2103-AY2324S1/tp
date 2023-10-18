package seedu.address.logic.search;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
        return new SingleTextSearchPredicate(str);
    }

    private static <T> T get(Supplier<T> supplier) {
        return supplier.get();
    }

    private static FieldRanges joinTest(
            String predicateA, String predicateB, String method
    ) {
        SearchPredicate a = generatePredicate(predicateA);
        SearchPredicate b = generatePredicate(predicateB);
        try {
            return ((SearchPredicate) SearchPredicate.class.getDeclaredMethod(method, SearchPredicate.class).invoke(a, b)).test(TEST_PERSON);
        } catch (Exception ignored) {
            assert false: "Unexpected Exception";
            return null;
        }
    }

    @Test
    public void test_andJoiner_success() {
        FieldRanges expected = get(() -> {
            FieldRanges fr = new FieldRanges();
            fr.put("field A", new Range(0, 4));
            fr.put("field B", new Range(0, 1));
            return fr;
        });
        FieldRanges actual = joinTest("lorem", "ut", "and");
        assertEquals(expected, actual);
    }

    @Test
    public void test_orJoiner_success() {
        FieldRanges expected = get(() -> {
            FieldRanges fr = new FieldRanges();
            fr.put("field A", new Range(0, 4));
            return fr;
        });
        FieldRanges actual = joinTest("abcdef", "lorem", "or");
        assertEquals(expected, actual);
    }

    // further testing included in FindCommandArgumentParserTest

}