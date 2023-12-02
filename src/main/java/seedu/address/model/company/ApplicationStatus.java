package seedu.address.model.company;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Company's application status  in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidApplicationStatus(String)}
 */
public class ApplicationStatus {

    public static final String MESSAGE_CONSTRAINTS_NON_EMPTY =
            "Oops! Application status should not be blank! Please try again with a valid application status.";
    public static final String MESSAGE_CONSTRAINTS_VALID_STATUS =
            "Oops! You have entered an invalid application status! \n"
                    + "Valid statuses: PA (Pending Application), PI (Pending Interview), PO (Pending Outcome), "
                    + "A (Accepted), R (Rejected). \n"
                    + "Please try again with a valid application status.";

    /**
     * The application status of a company.
     */
    public enum ApplicationStatusEnum {
        PA("PA", "PENDING APPLICATION"),
        PI("PI", "PENDING INTERVIEW"),
        PO("PO", "PENDING OUTCOME"),
        A("A", "ACCEPTED"),
        R("R", "REJECTED");

        private final String code;
        private final String description;

        ApplicationStatusEnum(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }

    public final ApplicationStatusEnum status;

    /**
     * Constructs an {@code ApplicationStatus} based on the given status string.
     * The constructor supports a range of status inputs for flexibility, allowing
     * for both short codes and various longer forms. This includes allowing
     * infinite spaces between letters such as 'P' and 'O'. Refer to the class
     * documentation for a detailed list of supported status inputs.
     *
     * @param status A string representation of the application status.
     * @throws IllegalArgumentException If the provided status string is invalid.
     */
    public ApplicationStatus(String status) throws IllegalArgumentException {
        requireNonNull(status);
        status = status.toUpperCase().replaceAll("\\s+", " ").trim();

        if (status.matches("^(PA|PEND\\s*APP|PENDING\\s*APP|PENDING\\s*APPLICATION|P\\sA|PENDING\\sA)$")) {
            this.status = ApplicationStatusEnum.PA;
        } else if (status.matches("^(PI|PEND\\s*INT|PENDING\\s*INT|PENDING\\s*INTERVIEW|P\\sI|PENDING\\sI)$")) {
            this.status = ApplicationStatusEnum.PI;
        } else if (status.matches("^(PO|PEND\\s*OUT|PENDING\\s*OUT|PENDING\\s*OUTCOME|P\\sO|PENDING\\sO)$")) {
            this.status = ApplicationStatusEnum.PO;
        } else if (status.matches("^(A|ACC|ACCEPT|ACPT|ACCEPTED)$")) {
            this.status = ApplicationStatusEnum.A;
        } else if (status.matches("^(R|REJ|REJECT|REJECTED)$")) {
            this.status = ApplicationStatusEnum.R;
        } else {
            throw new IllegalArgumentException("Invalid application status");
        }
    }

    /**
     * Returns true if a given string is a valid application status.
     */
    public static boolean isValidApplicationStatus(String test) {
        test = test.toUpperCase().replaceAll("\\s+", " ").trim();
        return test.matches("^(PA|PEND\\s*APP|PENDING\\s*APP"
                + "|PENDING\\s*APPLICATION|P\\sA|PENDING\\sA|PI|PEND\\s*INT|"
                + "PENDING\\s*INT|PENDING\\s*INTERVIEW|P\\sI|PENDING\\sI|PO|PEND\\s*OUT|"
                + "PENDING\\s*OUT|PENDING\\s*OUTCOME|P\\sO|PENDING\\sO|A|ACC|ACCEPT|ACPT|"
                + "ACCEPTED|R|REJ|REJECT|REJECTED)$");
    }

    @Override
    public String toString() {
        return status.getCode();
    }

    public String getDescription() {
        return status.getDescription();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ApplicationStatus)) {
            return false;
        }

        ApplicationStatus otherApplicationStatus = (ApplicationStatus) other;
        return status.equals(otherApplicationStatus.status);
    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }

}

