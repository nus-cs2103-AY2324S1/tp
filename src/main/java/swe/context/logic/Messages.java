package swe.context.logic;

/**
 * Holds message strings used by the logic for display to the user.
 *
 * Public messages do not need formatting and can be used directly after import.
 *
 * Private messages need formatting and have the prefix {@code UNFORMATTED_}.
 * These contain raw format specifiers (e.g. {@code %s}) that should be
 * populated by calling their associated methods.
 */
public final class Messages {
    // Generic commands
    public static final String COMMAND_UNKNOWN = "Unknown command.";
    public static final String DUPLICATE_FIELDS =
        "Multiple values specified for the following single-valued field(s): ";
    public static final String EMPTY_WORD_PARAMETER = "Word parameter cannot be empty";
    public static final String SINGLE_WORD_EXPECTED = "Word parameter should be a single word";
    public static final String COMMAND_DUPLICATE_CONTACT = "This contact is already in your contact list.";
    public static final String EDIT_COMMAND_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String HELP_COMMAND_SHOW_HELP = "Opened help window.";
    public static final String LIST_COMMAND_SUCCESS = "Listed all contacts";
    private static final String UNFORMATTED_COMMAND_INVALID_FORMAT = "Invalid command format.\n%s";
    private static final String UNFORMATTED_CONTACTS_LISTED_OVERVIEW = "%d contacts listed!";
    private static final String UNFORMATTED_ADD_COMMAND_SUCCESS = "New contact added: %1$s";
    private static final String UNFORMATTED_DELETE_COMMAND_SUCCESS = "Deleted Contact(s): \n%1$s";
    private static final String UNFORMATTED_EDIT_COMMAND_SUCCESS = "Edited Contact: %1$s";

    // Specific commands
    public static final String COMMAND_CLEAR_SUCCESS = "All contacts have been removed!";
    public static final String COMMAND_EXIT_SUCCESS = "Exiting app...";

    // Contacts
    // Messages associated with Attributes constraints
    public static final String INVALID_CONTACT_DISPLAYED_INDEX =
        "One or more of the contact indices provided are invalid";
    public static final String NAME_INVALID =
            "Names must be alphanumeric (spaces allowed).";
    public static final String PHONE_INVALID =
            "Phone numbers must start with at least 3 digits.";
    public static final String EMAIL_INVALID =
            "Emails must roughly be of the form \"example_email@foo-domain.sg.\"";
    private static final String UNFORMATTED_TAG_INVALID =
            "\"%s\" is not a valid tag. Tags must be alphanumeric (spaces allowed).";

    // JSON
    public static final String CONVERT_CONTACTS_DUPLICATE = "Encountered duplicate while converting contacts.";

    // Exceptions
    public static final String DUPLICATE_CONTACT_EXCEPTION = "Operation would result in duplicate contacts";
    private static final String UNFORMATTED_FILE_OPS_ERROR_FORMAT =
            "Could not save data due to the following error: %s";
    private static final String UNFORMATTED_FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";

    private static final String UNFORMATTED_FIELD_MISSING = "Contact's %s field is missing.";

    /**
     * Returns a formatted message about the command format being invalid, with
     * the specified help text.
     */
    public static String commandInvalidFormat(String helpText) {
        return String.format(
            Messages.UNFORMATTED_COMMAND_INVALID_FORMAT,
            helpText
        );
    }

    /**
     * Returns a formatted message about the specified tag name being invalid.
     */
    public static String tagInvalid(String invalidName) {
        return String.format(
            Messages.UNFORMATTED_TAG_INVALID,
            invalidName
        );
    }

    /**
     * Returns a formatted message showing the number of contacts listed.
     */
    public static String contactsListedOverview(int count) {
        return String.format(UNFORMATTED_CONTACTS_LISTED_OVERVIEW, count);
    }

    /**
     * Returns a formatted message indicating a successful addition of a contact.
     */
    public static String addCommandSuccess(String contactDetails) {
        return String.format(UNFORMATTED_ADD_COMMAND_SUCCESS, contactDetails);
    }

    /**
     * Returns a formatted message indicating successful deletion of contact(s).
     */
    public static String deleteCommandSuccess(String contactDetails) {
        return String.format(UNFORMATTED_DELETE_COMMAND_SUCCESS, contactDetails);
    }

    /**
     * Returns a formatted message indicating successful editing of a contact.
     */
    public static String editCommandSuccess(String contactDetails) {
        return String.format(UNFORMATTED_EDIT_COMMAND_SUCCESS, contactDetails);
    }

    /**
     * Returns a formatted message indicating a file operation error.
     */
    public static String fileOpsErrorFormat(String errorDetails) {
        return String.format(UNFORMATTED_FILE_OPS_ERROR_FORMAT, errorDetails);
    }

    /**
     * Returns a formatted message indicating a file permission error.
     */
    public static String fileOpsPermissionErrorFormat(String filePath) {
        return String.format(UNFORMATTED_FILE_OPS_PERMISSION_ERROR_FORMAT, filePath);
    }

    /**
     * Returns a formatted message indicating a missing field in a contact.
     */
    public static String fieldMissing(String fieldName) {
        return String.format(UNFORMATTED_FIELD_MISSING, fieldName);
    }

    private Messages() {
        // No instantiation
    }
}
