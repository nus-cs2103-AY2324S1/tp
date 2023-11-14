package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's major in StudentConnect.
 * Guarantees: immutable; is valid as declared in {@link #isValidMajor(String)}
 */
public class Major {

    public static final String MESSAGE_CONSTRAINTS = "Majors should not be blank and must be a valid"
            + " major offered at NUS.\n"
            + "Valid major list can be found in the user guide.\n"
            + "The user guide can be found by using the 'help' command.";

    private static final String[] VALID_NUS_MAJORS = {
        "Accountancy",
        "Actuarial Studies",
        "Anthropology",
        "Architecture",
        "Biological Sciences",
        "Biomedical Engineering",
        "Business Administration",
        "Business Administration (Accountancy)",
        "Business Analytics",
        "Chemical Engineering",
        "Chemistry",
        "Chinese Language",
        "Chinese Studies",
        "Communications and New Media",
        "Civil Engineering",
        "Computer Engineering",
        "Computer Science",
        "Data Science and Analytics",
        "Data Science and Economics",
        "Dentistry",
        "Economics",
        "Electrical Engineering",
        "English Language",
        "English Literature",
        "Environmental Engineering",
        "Environmental Studies",
        "Food Science and Technology",
        "Geography",
        "Global Studies",
        "History",
        "Japanese Studies",
        "Industrial Design",
        "Industrial Engineering",
        "Industrial and Systems Engineering",
        "Information Systems",
        "Information Security",
        "Infrastructure and Project Management",
        "Landscape Architecture",
        "Law",
        "Life Sciences",
        "Malay Studies",
        "Management",
        "Marketing",
        "Materials Science and Engineering",
        "Mathematics",
        "Mechanical Engineering",
        "Medicine",
        "Pharmacy",
        "Pharmaceutical Science",
        "Philosophy",
        "Philosophy, Politics, and Economics",
        "Physics",
        "Political Science",
        "Psychology",
        "Quantitative Finance",
        "Real Estate",
        "Social Work",
        "Sociology",
        "South Asian Studies",
        "Southeast Asian Studies",
        "Statistics",
        "Systems Engineering",
        "Theatre Studies",
        "Urban Studies",
        "Visual Communications",
        "Others"
    };

    public final String value;

    /**
     * Constructs a {@code Major} with the specified major value.
     *
     * @param major The major value. Must not be null.
     */
    public Major(String major) {
        requireNonNull(major);
        checkArgument(isValidMajor(major), MESSAGE_CONSTRAINTS);
        value = capitaliseFirstLetterOfEachWord(major);
    }

    private String capitaliseFirstLetterOfEachWord(String text) {
        String[] words = text.split("\\s");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase());
                result.append(" "); // Add a space between words
            }
        }
        if (result.length() > 0) {
            result.setLength(result.length() - 1);
        }
        return result.toString();
    }

    /**
     * Returns if a given string is a valid major offered at NUS.
     */
    public static boolean isValidMajor(String major) {
        for (String validMajor : VALID_NUS_MAJORS) {
            if (major.equalsIgnoreCase(validMajor)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Major // instanceof handles nulls
                && value.equals(((Major) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
