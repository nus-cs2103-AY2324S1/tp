package seedu.address.model.person;

import static java.util.Map.entry;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Map;

/**
 * Represents the previous grade an applicant obtained in the module i.e. when they were
 * a student of the module.
 */
public class PreviousGrade implements Comparable<PreviousGrade> {

    public static final String MESSAGE_CONSTRAINTS =
            "Grades must be one of the following: A+, A, A-, B+, B, B-, C+, C, D+, D, F";

    public final Grade grade;

    /**
     * Creates a new "previous grade" that the TA applicant has obtained in the same module.
     *
     * @param grade The grade the applicant previously obtained as a participant of the module.
     */
    public PreviousGrade(String grade) {
        requireNonNull(grade);
        checkArgument(isValidGrade(grade), MESSAGE_CONSTRAINTS);
        this.grade = Grade.parse(grade);
    }

    public static boolean isValidGrade(String test) {
        return Grade.parse(test) != null;
    }

    @Override
    public int compareTo(PreviousGrade other) {
        return grade.compareTo(other.grade);
    }

    @Override
    public String toString() {
        return grade.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PreviousGrade)) {
            return false;
        }

        PreviousGrade otherPrevousGrade = (PreviousGrade) other;
        return grade == otherPrevousGrade.grade;
    }

    @Override
    public int hashCode() {
        return grade.hashCode();
    }

    /**
     * Represents a grade that an NUS student can obtain under the GPA system.
     */
    public static enum Grade {
        A_PLUS,
        A,
        A_MINUS,
        B_PLUS,
        B,
        B_MINUS,
        C_PLUS,
        C,
        D_PLUS,
        D,
        F;

        private static final Map<String, Grade> GRADES = Map.ofEntries(
                entry("A+", Grade.A_PLUS),
                entry("A", Grade.A),
                entry("A-", Grade.A_MINUS),
                entry("B+", Grade.B_PLUS),
                entry("B", Grade.B),
                entry("B-", Grade.B_MINUS),
                entry("C+", Grade.C_PLUS),
                entry("C", Grade.C),
                entry("D+", Grade.D_PLUS),
                entry("D", Grade.D),
                entry("F", Grade.F));

        public static Grade parse(String grade) {
            return GRADES.get(grade);
        }

        @Override
        public String toString() {
            switch (this) {
            case A_PLUS:
                return "A+";
            case A:
                return "A";
            case A_MINUS:
                return "A-";
            case B_PLUS:
                return "B+";
            case B:
                return "B";
            case B_MINUS:
                return "B-";
            case C_PLUS:
                return "C+";
            case C:
                return "C";
            case D_PLUS:
                return "D+";
            case D:
                return "D";
            case F:
                return "F";
            default:
                throw new Error("Unreachable");
            }
        }
    }
}
