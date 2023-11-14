package seedu.address.model.person;

/**
 * Represents reasons for deductions or benefits.
 */
public enum Reason {
    ABSENCE,
    TRANSPORT_ALLOWANCE,
    NO_PAY_LEAVE,
    ANNUAL_BONUS,
    EMPLOYEE_CPF_DEDUCTION;

    public static final String MISSING_ALERT = "Please specify your reason with /r prefix.\n";

    public static final String MESSAGE_CONSTRAINTS =
        "Reasons should only be one of the following:\n"
        + "1. " + ABSENCE.toString() + "\n"
        + "2. " + TRANSPORT_ALLOWANCE.toString() + "\n"
        + "3. " + NO_PAY_LEAVE.toString() + "\n"
        + "4. " + ANNUAL_BONUS.toString() + "\n"
        + "5. " + EMPLOYEE_CPF_DEDUCTION.toString() + "\n"
        + "Please specify your reason.\n";
}
