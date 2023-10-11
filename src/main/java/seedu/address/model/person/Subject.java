package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.HashMap;


/**
 * Represents a student's subject in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSubject(String)}
 */
public class Subject {
    public static final String MESSAGE_CONSTRAINTS =
            "Subject should only be Mathematics, Physics, Biology, Chemistry or English";

    public final Subjects subjectName;

    public final String colour;
    // useful resource: https://www.w3schools.com/tags/ref_colornames.asp for colours
    private HashMap<String, String> subjectToColourMap = new HashMap<String, String>() {{
            put("MATHEMATICS", "red");
            put("PHYSICS", "yellow");
            put("BIOLOGY", "green");
            put("CHEMISTRY", "lightBlue");
            put("ENGLISH", "orange");
        }};


    /**
     * Subjects that tutor teaches.
     */
    public enum Subjects {
        MATHEMATICS,
        PHYSICS,
        BIOLOGY,
        CHEMISTRY,
        ENGLISH,
        NONE
    }
    /**
     * Constructs a {@code Subject}.
     *
     * @param subject A valid subject.
     */
    public Subject(String subject) {
        requireNonNull(subject);
        checkArgument(isValidSubject(subject.toUpperCase()), MESSAGE_CONSTRAINTS);
        if (isValidSubject(subject.toUpperCase())) {
            subjectName = Subjects.valueOf(subject.toUpperCase());
            colour = subjectToColourMap.get(subject.toUpperCase());
        } else {
            subjectName = Subjects.NONE;
            colour = "invalid";
        }
    }

    /**
     * Returns true if a given string is a valid subject.
     */
    public static boolean isValidSubject(String test) {
        try {
            Subjects subject = Subjects.valueOf(test);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + subjectName.toString() + ']';
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


}
