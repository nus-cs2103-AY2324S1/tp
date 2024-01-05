package seedu.address.testutil;

import seedu.address.model.gradedtest.Finals;
import seedu.address.model.gradedtest.GradedTest;
import seedu.address.model.gradedtest.MidTerms;
import seedu.address.model.gradedtest.PracticalExam;
import seedu.address.model.gradedtest.ReadingAssessment1;
import seedu.address.model.gradedtest.ReadingAssessment2;

/**
 * A utility class for building GradedTest objects for testing.
 * It provides methods for setting individual components of a GradedTest object.
 * You can create GradedTest objects with custom attributes or use default values.
 */
public class GradedTestBuilder {
    public static final String DEFAULT_RA1 = "-";
    public static final String DEFAULT_RA2 = "-";
    public static final String DEFAULT_MIDTERMS = "-";
    public static final String DEFAULT_FINALS = "-";
    public static final String DEFAULT_PE = "-";


    private ReadingAssessment1 rA1;
    private ReadingAssessment2 rA2;
    private MidTerms midterms;
    private Finals finals;
    private PracticalExam pE;


    /**
     * Creates a {@code GradedTestBuilder} with the default details.
     */
    public GradedTestBuilder() {
        rA1 = new ReadingAssessment1(DEFAULT_RA1);
        rA2 = new ReadingAssessment2(DEFAULT_RA2);
        midterms = new MidTerms(DEFAULT_MIDTERMS);
        finals = new Finals(DEFAULT_FINALS);
        pE = new PracticalExam(DEFAULT_PE);
    }

    /**
     * Initializes the GradedTestBuilder with the data of {@code gradedTest}.
     */
    public GradedTestBuilder(GradedTest gradedTestToCopy) {
        rA1 = gradedTestToCopy.getRA1();
        rA2 = gradedTestToCopy.getRA2();
        midterms = gradedTestToCopy.getMidTerms();
        finals = gradedTestToCopy.getFinals();
        pE = gradedTestToCopy.getPracticalExam();

    }

    /**
     * Sets the {@code ReadingAssessment} of the {@code GradedTest} that we are building.
     */
    public GradedTestBuilder withReadingAssessment1(String rA1) {
        this.rA1 = new ReadingAssessment1(rA1);
        return this;
    }

    /**
     * Sets the {@code ReadingAssessment} of the {@code GradedTest} that we are building.
     */
    public GradedTestBuilder withReadingAssessment2(String rA2) {
        this.rA2 = new ReadingAssessment2(rA2);
        return this;
    }

    /**
     * Sets the {@code MidTerms} of the {@code GradedTest} that we are building.
     */
    public GradedTestBuilder withMidTerms(String midTerms) {
        this.midterms = new MidTerms(midTerms);
        return this;
    }

    /**
     * Sets the {@code Finals} of the {@code GradedTest} that we are building.
     */
    public GradedTestBuilder withFinals(String finals) {
        this.finals = new Finals(finals);
        return this;
    }

    /**
     * Sets the {@code PracticalExam} of the {@code GradedTest} that we are building.
     */
    public GradedTestBuilder withPe(String pE) {
        this.pE = new PracticalExam(pE);
        return this;
    }

    public GradedTest build() {
        return new GradedTest(rA1, rA2, midterms, finals, pE);
    }
}
