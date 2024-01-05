package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.GradedTestListBook;
import seedu.address.model.gradedtest.GradedTest;

/**
 * A utility class containing a list of {@code GradedTest} objects to be used in tests.
 */
public class TypicalGradedTest {

    public static final GradedTest GT1 = new GradedTestBuilder()
            .withReadingAssessment1("10")
            .withReadingAssessment2("20")
            .withMidTerms("30")
            .withFinals("40")
            .withPe("50")
            .build();

    public static final GradedTest GT2 = new GradedTestBuilder()
            .withReadingAssessment1("1")
            .withReadingAssessment2("2")
            .withMidTerms("3")
            .withFinals("4")
            .withPe("5")
            .build();

    // floats allowed
    public static final GradedTest GT3 = new GradedTestBuilder()
            .withReadingAssessment1("5.0")
            .withReadingAssessment2("4.0")
            .withMidTerms("3.0")
            .withFinals("2.0")
            .withPe("1.0")
            .build();

    // 0 allowed
    public static final GradedTest GT4 = new GradedTestBuilder()
            .withReadingAssessment1("0")
            .withReadingAssessment2("0")
            .withMidTerms("0")
            .withFinals("0")
            .withPe("0")
            .build();

    public static final GradedTest GT5 = new GradedTestBuilder()
            .withReadingAssessment1("100")
            .withReadingAssessment2("50")
            .withMidTerms("25")
            .withFinals("12.5")
            .withPe("6.25")
            .build();

    public static final GradedTest GT6 = new GradedTestBuilder()
            .withReadingAssessment1("1.1")
            .withReadingAssessment2("2.22")
            .withMidTerms("3.333")
            .withFinals("4.4444")
            .withPe("5.55555")
            .build();

    public static final GradedTest GT7 = new GradedTestBuilder()
            .withReadingAssessment1("100")
            .withReadingAssessment2("100")
            .withMidTerms("100")
            .withFinals("100")
            .withPe("100")
            .build();

    // - allowed
    public static final GradedTest GT8 = new GradedTestBuilder()
            .withReadingAssessment1("-")
            .withReadingAssessment2("-")
            .withMidTerms("-")
            .withFinals("-")
            .withPe("-")
            .build();

    public static final GradedTest GT9 = new GradedTestBuilder()
            .withReadingAssessment1("123")
            .withReadingAssessment2("321")
            .withMidTerms("456")
            .withFinals("654")
            .withPe("999")
            .build();

    public static final GradedTest GT10 = new GradedTestBuilder()
            .withReadingAssessment1("1.23")
            .withReadingAssessment2("32.1")
            .withMidTerms("45.6")
            .withFinals("6.54")
            .withPe("9.99")
            .build();

    private TypicalGradedTest() {} // prevents instantiation

    /**
     * Returns an {@code GradedTestBook} with all the typical gradedTest.
     */
    public static GradedTestListBook getTypicalGradedTestList() {
        GradedTestListBook ab = new GradedTestListBook();
        for (GradedTest gt : getTypicalGradedTest()) {
            ab.addGradedTest(gt);
        }
        return ab;
    }

    public static List<GradedTest> getTypicalGradedTest() {
        return new ArrayList<>(Arrays.asList(GT1, GT2, GT3, GT4, GT5, GT6, GT7, GT8));
    }

}
