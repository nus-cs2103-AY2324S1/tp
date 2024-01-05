package seedu.address.model.gradedtest;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.parser.exceptions.ParseException;

public class GradedTest {
    public static final String MESSAGE_CONSTRAINTS =
            "GradedTest Names should only contain alphanumeric characters and spaces, and it should not be blank \n"
            + "Format: RA1:<Score> | RA2:<Score> | MidTerms:<Score> | Finals:<Score> | PE:<Score>";

    public static final String VALIDATION_REGEX =
            "RA1:[-\\d]+ \\| RA2:[-\\d]+ \\| MidTerms:[-\\d]+ \\| Finals:[-\\d]+ \\| PE:[-\\d]+";
    public static final String VALIDATION_REGEX_DEFAULT = "(?i)default";

    public static final String DEFAULT_VALUE = "-";
    // Identity fields
    public final String gradedTestsIndv;

    // Data fields
    private final ReadingAssessment1 readingAssessment1;
    private final ReadingAssessment2 readingAssessment2;
    private final MidTerms midTerms;
    private final Finals finals;
    private final PracticalExam practicalExam;

    /**
     * Constructs a {@code GradedTest}.
     *
     * @param readingAssessment1 The first reading assessment.
     * @param readingAssessment2 The second reading assessment.
     * @param midTerms The mid-terms.
     * @param finals The finals.
     * @param practicalExam The practical exam.
     */
    public GradedTest(ReadingAssessment1 readingAssessment1, ReadingAssessment2 readingAssessment2,
                      MidTerms midTerms, Finals finals, PracticalExam practicalExam) {
        requireAllNonNull(readingAssessment1, readingAssessment2, midTerms, finals, practicalExam);
        this.readingAssessment1 = readingAssessment1;
        this.readingAssessment2 = readingAssessment2;
        this.midTerms = midTerms;
        this.finals = finals;
        this.practicalExam = practicalExam;
        this.gradedTestsIndv =
                "RA1:" + readingAssessment1.toString() + " | "
                + "RA2:" + readingAssessment2.toString() + " | "
                + "MidTerms:" + midTerms.toString() + " | "
                + "Finals:" + finals.toString() + " | "
                + "PE:" + practicalExam.toString();
    }

    /**
     * Constructs a {@code GradedTest}.
     *
     * @param gradedTestsIndv A valid gradedTest name.
     */
    public GradedTest(String gradedTestsIndv) {
        requireNonNull(gradedTestsIndv);
        if ("default".equalsIgnoreCase(gradedTestsIndv)) {
            this.readingAssessment1 = new ReadingAssessment1(DEFAULT_VALUE);
            this.readingAssessment2 = new ReadingAssessment2(DEFAULT_VALUE);
            this.midTerms = new MidTerms(DEFAULT_VALUE);
            this.finals = new Finals(DEFAULT_VALUE);
            this.practicalExam = new PracticalExam(DEFAULT_VALUE);
            this.gradedTestsIndv = "RA1:" + DEFAULT_VALUE + " | RA2:" + DEFAULT_VALUE
                    + " | MidTerms:" + DEFAULT_VALUE + " | Finals:" + DEFAULT_VALUE + " | PE:" + DEFAULT_VALUE;
        } else if (isValidGradeTestName(gradedTestsIndv)) {
            try {
                this.gradedTestsIndv = gradedTestsIndv;
                this.readingAssessment1 = new ReadingAssessment1(parseGradedTest(gradedTestsIndv)[0]);
                this.readingAssessment2 = new ReadingAssessment2(parseGradedTest(gradedTestsIndv)[1]);
                this.midTerms = new MidTerms(parseGradedTest(gradedTestsIndv)[2]);
                this.finals = new Finals(parseGradedTest(gradedTestsIndv)[3]);
                this.practicalExam = new PracticalExam(parseGradedTest(gradedTestsIndv)[4]);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
    }

    public static String[] parseGradedTest(String gradedTestsIndv) throws ParseException {
        String[] components = gradedTestsIndv.split("\\|");

        if (components.length != 5) {
            throw new ParseException("Invalid GradedTest format. Expected 5 components.");
        }

        String[] scores = new String[5];

        for (int i = 0; i < components.length; i++) {
            String[] fieldParts = components[i].split(":");
            if (fieldParts.length != 2) {
                throw new ParseException("Invalid GradedTest format for field " + i);
            }
            String fieldName = fieldParts[0].trim();
            String fieldValue = fieldParts[1].trim();
            String errorMessage = validateField(fieldName, fieldValue);
            if (errorMessage != null) {
                throw new ParseException(errorMessage);
            }
            scores[i] = fieldValue;
        }

        return scores;
    }

    public static String validateField(String fieldName, String fieldValue) {
        switch (fieldName) {
        case "RA1":
            if (!fieldValue.matches(ReadingAssessment1.VALIDATION_REGEX)) {
                return ReadingAssessment1.MESSAGE_CONSTRAINTS;
            }
            break;
        case "RA2":
            if (!fieldValue.matches(ReadingAssessment2.VALIDATION_REGEX)) {
                return ReadingAssessment2.MESSAGE_CONSTRAINTS;
            }
            break;
        case "MidTerms":
            if (!fieldValue.matches(MidTerms.VALIDATION_REGEX)) {
                return MidTerms.MESSAGE_CONSTRAINTS;
            }
            break;
        case "Finals":
            if (!fieldValue.matches(Finals.VALIDATION_REGEX)) {
                return Finals.MESSAGE_CONSTRAINTS;
            }
            break;
        case "PE":
            if (!fieldValue.matches(PracticalExam.VALIDATION_REGEX)) {
                return PracticalExam.MESSAGE_CONSTRAINTS;
            }
            break;
        default:
        }
        return null;
    }

    public ReadingAssessment1 getRA1() {
        return readingAssessment1;
    }

    public ReadingAssessment2 getRA2() {
        return readingAssessment2;
    }

    public MidTerms getMidTerms() {
        return midTerms;
    }

    public Finals getFinals() {
        return finals;
    }

    public PracticalExam getPracticalExam() {
        return practicalExam;
    }

    public String getGradedTests() {
        return gradedTestsIndv;
    }

    /**
     * Returns true if a given string is a valid gradedTest name.
     */
    public static boolean isValidGradeTestName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid gradedTest name.
     */
    public static boolean isValidGradeTestNameDefault(String test) {
        test.toLowerCase();
        return test.matches(VALIDATION_REGEX_DEFAULT);
    }

    /**
     * Returns true if both gradedtest have the same name and description.
     */
    public boolean isSameGradedTest(GradedTest otherGradedTest) {
        if (otherGradedTest == this) {
            return true;
        }

        return otherGradedTest != null
                && otherGradedTest.getGradedTests().equals(gradedTestsIndv)
                && otherGradedTest.getRA1().equals(readingAssessment1)
                && otherGradedTest.getRA2().equals(readingAssessment2)
                && otherGradedTest.getMidTerms().equals(midTerms)
                && otherGradedTest.getFinals().equals(finals)
                && otherGradedTest.getPracticalExam().equals(practicalExam);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GradedTest)) {
            return false;
        }

        GradedTest otherTest = (GradedTest) other;
        return readingAssessment1.equals(otherTest.readingAssessment1)
                && readingAssessment2.equals(otherTest.readingAssessment2)
                && midTerms.equals(otherTest.midTerms)
                && finals.equals(otherTest.finals)
                && practicalExam.equals(otherTest.practicalExam)
                && gradedTestsIndv.equals(otherTest.gradedTestsIndv);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(readingAssessment1, readingAssessment2, midTerms, finals, practicalExam);
    }

    @Override
    public String toString() {
        return new ToStringBuilder("")
                .add("RA1", readingAssessment1)
                .add("RA2", readingAssessment2)
                .add("MidTerms", midTerms)
                .add("Finals", finals)
                .add("PE", practicalExam)
                .toString();
    }
}
