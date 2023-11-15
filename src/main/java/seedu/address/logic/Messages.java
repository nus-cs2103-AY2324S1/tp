package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_NOT_FOUND_INDEX = "Error: Invalid Index";
    public static final String MESSAGE_MISSING_INDEX = "Error: Missing Index";
    public static final String MESSAGE_IMPOSSIBLE_INDEX = "Error: The parameter is not of the type positive integer";
    public static final String MESSAGE_PREAMBLE_DETECTED = "Error: Preamble Detected";
    public static final String MESSAGE_USED_POLICY_NUMBER = "Error: The policy number is already in use";

    public static final String MESSAGE_DATES_NOT_COMPATIBLE = "Error: "
            + "The Policy Issue Date falls after Policy Expiry Date";

    public static final String MESSAGE_INCOMPLETE_POLICY_EDIT = "Error: "
            + "You must provide edits for all policy parameters";
    public static final String MESSAGE_MISSING_FIELDS_FOR_ADD_COMMAND = "Error: "
            + "Some of the required fields are missing. "
            + "\n"
            + "Please include the following: ";

    public static final String MESSAGE_MISSING_FIELDS_POLICY_FOR_EDIT_COMMAND = "Error: "
            + "Please provide all the policy parameters when editing a policy with no existing policy data. "
            + "\n"
            + "Please include the following: ";
    public static final String MESSAGE_MISSING_FIELDS_POLICY_FOR_ADD_COMMAND = "Error: "
            + "Please include either all or none of the policy variables. "
            + "\n"
            + "You are missing the following: ";
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_NOT_NUMBERS = "Error: The value is not a number";
    public static final String MESSAGE_NOT_IN_RANGE =
            "Error: The value has to be between %1$d and %2$d (both inclusive)";
    public static final String MESSAGE_INVALID_TWO_FIELD = "Error: Please contain only either "
            + "one field of %1$s or %2$s.";
    public static final String MESSAGE_EMPTY_FIELDS = "Error: No value detected for the following field(s): %1$s";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Address: ")
                .append(person.getAddress())
                .append("; NRIC: ")
                .append(person.getNric())
                .append("; Licence Plate: ")
                .append(person.getLicencePlate())
                .append("\nPolicy Details (Company, Policy Number, Policy Issue Date and Policy Expiry Date): ")
                .append(person.getPolicy().toDisplay(false))
                .append("\nTags: ");
        person.getTags().forEach(builder::append);
        builder.append("\nRemark: ").append(person.getRemark());
        return builder.toString();
    }

}
