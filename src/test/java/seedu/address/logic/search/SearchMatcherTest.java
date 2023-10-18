package seedu.address.logic.search;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

class SearchMatcherTest {

    private static final Map<String, String> TEST_PERSON;

    static {
        TEST_PERSON = new HashMap<>();
        TEST_PERSON.put("field A", "Lorem ipsum, dolor sit amet.");
        TEST_PERSON.put("field B", "Ut enim; ad@minim veNiam!");
    }

    private static SearchMatcher generatePredicate(String str) {
        return new SingleTextSearchMatcher(str);
    }

    private static <T> T get(Supplier<T> supplier) {
        return supplier.get();
    }

    private static FieldRanges joinTest(
            String predicateA, String predicateB, String method
    ) {
        SearchMatcher a = generatePredicate(predicateA);
        SearchMatcher b = generatePredicate(predicateB);
        try {
            return ((SearchMatcher) SearchMatcher.class.getDeclaredMethod(method, SearchMatcher.class).invoke(a, b))
                    .test(TEST_PERSON);
        } catch (Exception ignored) {
            assert false : "Unexpected Exception";
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
