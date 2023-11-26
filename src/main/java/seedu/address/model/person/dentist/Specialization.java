package seedu.address.model.person.dentist;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Dentist's specialization in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSpecialization(String)}
 */

// Solution of Specialization as a custom class inspired and adapted from
// https://github.com/AY2223S2-CS2103T-F12-1/tp/tree/master/src/main/java/seedu/address/model/person/doctor
public class Specialization {

    /**
     * Represents the specializations recognized in ToothTracker.
     * Source: <a href="https://www.healthprofessionals.gov.sg/dsab/specialist-training/list-of-recognised-specialties">
     *     Dental Specialties in Singapore from MOH</a>
     */
    public enum RecognizedSpecialization {
        ENDODONTICS,
        DENTAL_PUBLIC_HEALTH,
        ORAL_AND_MAXILLOFACIAL_SURGERY,
        ORTHODONTICS,
        PAEDIATRIC_DENTISTRY,
        PERIODONTICS,
        PROSTHODONTICS,
    }

    public static final String MESSAGE_CONSTRAINTS = getMessageConstraints();

    /*
     * The first character of the specialization must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    private static final String VALIDATION_REGEX =
            "ENDODONTICS|DENTAL_PUBLIC_HEALTH|ORAL_AND_MAXILLOFACIAL_SURGERY|ORTHODONTICS"
            + "|PAEDIATRIC_DENTISTRY|PERIODONTICS|PROSTHODONTICS";

    private final String specialization;

    /**
     * Constructs a {@code Specialization}.
     *
     * @param specialization A valid specialization.
     */
    public Specialization(String specialization) {
        requireNonNull(specialization);
        checkArgument(isValidSpecialization(specialization), MESSAGE_CONSTRAINTS);
        this.specialization = specialization.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid specialization.
     */
    public static boolean isValidSpecialization(String test) {
        String formattedTest = test.toUpperCase().replace(" ", "_"); // Convert test to uppercase for comparison

        for (RecognizedSpecialization specs : RecognizedSpecialization.values()) {
            if (specs.name().equals(formattedTest)) {
                return formattedTest.matches(VALIDATION_REGEX);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return specialization;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Specialization // instanceof handles nulls
                && specialization.equals(((Specialization) other).specialization)); // state check
    }

    @Override
    public int hashCode() {
        return specialization.hashCode();
    }

    public String getValue() {
        return specialization;
    }

    public static String getMessageConstraints() {
        StringBuilder sb = new StringBuilder();
        sb.append("Unrecognized Specialization entered! "
                + "Currently the following are recognized dental specialization in Singapore:\n");
        for (RecognizedSpecialization specs : RecognizedSpecialization.values()) {
            sb.append(specs).append("\n");
        }
        sb.append("\nPlease contact ToothTracker developers if you need to modify the recognized specializations!");
        return sb.toString();
    }
}
