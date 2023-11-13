package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.YearMonth;

/**
 * Represents a Subject in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidSubjectName(String)}
 */
public class Subject {

    /**
         * Represents all possible Subjects.
     */
    private enum Subjects {
        ENGLISH,
        CHINESE,
        ELEMENTARY_MATHEMATICS,
        ADDITIONAL_MATHEMATICS,
        PHYSICS,
        CHEMISTRY,
        BIOLOGY,
        GEOGRAPHY,
        HISTORY,
        SOCIAL_STUDIES,
        INVALID
    }

    public static final String ENG = "English";
    public static final String CHI = "Chinese";
    public static final String EMATH = "Elementary Mathematics";
    public static final String AMATH = "Additional Mathematics";
    public static final String PHY = "Physics";
    public static final String CHEMI = "Chemistry";
    public static final String BIO = "Biology";
    public static final String GEOG = "Geography";
    public static final String HIST = "History";
    public static final String SOC = "Social Studies";

    public static final String MESSAGE_CONSTRAINTS = "Please enter a valid subject name. Valid subject names include: "
            + ENG + ", "
            + CHI + ", "
            + EMATH + ", "
            + AMATH + ", "
            + PHY + ", "
            + CHEMI + ", "
            + BIO + ", "
            + GEOG + ", "
            + HIST + ", "
            + "and " + SOC + ".";

    private final String subjectName;
    private final EnrolDate enrolDate;

    /**
     * Constructs a {@code Subject}.
     *
     * @param subjectName A valid subject name.
     */
    public Subject(String subjectName) {
        this(subjectName, new EnrolDate());
    }

    /**
     * Constructs a {@code Subject}.
     *
     * @param subjectName A valid subject name.
     * @param enrolDate Subject enrol date.
     */
    public Subject(String subjectName, EnrolDate enrolDate) {
        requireNonNull(subjectName);
        requireNonNull(enrolDate);
        checkArgument(isValidSubjectName(subjectName), MESSAGE_CONSTRAINTS);
        this.subjectName = getCorrectedSubjectName(subjectName);
        this.enrolDate = enrolDate;
    }

    /**
     * Returns true if a given string is a valid subject name.
     */
    public static boolean isValidSubjectName(String subjectName) {
        Subject.Subjects subject = Subject.Subjects.INVALID;

        if (subjectName.equalsIgnoreCase(ENG)) {
            subject = Subjects.ENGLISH;
        } else if (subjectName.equalsIgnoreCase(CHI)) {
            subject = Subjects.CHINESE;
        } else if (subjectName.equalsIgnoreCase(EMATH)) {
            subject = Subjects.ELEMENTARY_MATHEMATICS;
        } else if (subjectName.equalsIgnoreCase(AMATH)) {
            subject = Subjects.ADDITIONAL_MATHEMATICS;
        } else if (subjectName.equalsIgnoreCase(PHY)) {
            subject = Subjects.PHYSICS;
        } else if (subjectName.equalsIgnoreCase(CHEMI)) {
            subject = Subjects.CHEMISTRY;
        } else if (subjectName.equalsIgnoreCase(BIO)) {
            subject = Subjects.BIOLOGY;
        } else if (subjectName.equalsIgnoreCase(GEOG)) {
            subject = Subjects.GEOGRAPHY;
        } else if (subjectName.equalsIgnoreCase(HIST)) {
            subject = Subjects.HISTORY;
        } else if (subjectName.equalsIgnoreCase(SOC)) {
            subject = Subjects.SOCIAL_STUDIES;
        }

        return subject != Subjects.INVALID;
    }

    /**
     * This method is used to make the subjectNames for the same subject to be consistent.
     * @param subjectName user input subject name
     * @return corrected subject name that follows our convention, return null for invalid user input subject name.
     */
    public static String getCorrectedSubjectName(String subjectName) {
        requireNonNull(subjectName);
        String[] subjectNames = new String[] {ENG, CHI, EMATH, AMATH, PHY, CHEMI, BIO, GEOG, HIST, SOC};

        for (String s : subjectNames) {
            if (subjectName.equalsIgnoreCase(s)) {
                return s;
            }
        }
        return null;
    }

    /**
     * Getter method for subjectName
     * @return a string representation of subject name
     */
    public String getSubjectName() {
        return this.subjectName;
    }

    /**
     * Getter method of enrolDate
     * @return enrolDate instance of this subject
     */
    public EnrolDate getEnrolDate() {
        return this.enrolDate;
    }

    /**
     * Getter method for enroldate's yearmonth
     * @return YearMonth instance of EnrolDate
     */
    public YearMonth getYearMonth() {
        return this.enrolDate.getEnrolDate();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Subject)) {
            return false;
        }

        Subject otherSubject = (Subject) other;
        return subjectName.equals(otherSubject.subjectName);
    }

    @Override
    public int hashCode() {
        return subjectName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + subjectName + " (enrolled in: " + enrolDate + ')' + ']';
    }

}
