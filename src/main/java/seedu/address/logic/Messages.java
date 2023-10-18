package seedu.address.logic;

import seedu.address.model.contact.Email;



//TODO we could avoid static imports, and instead refer to strings as
// Messages.FOO. Then we can remove the MESSAGE_ prefix for everything
//TODO prefix messages containing format specifiers with UNFORMATTED_
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
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format!\n%s";
    public static final String MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX = "The contact index provided is invalid";
    public static final String MESSAGE_CONTACTS_LISTED_OVERVIEW = "%d contacts listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
            "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_EMPTY_WORD_PARAMETER = "Word parameter cannot be empty";
    public static final String MESSAGE_SINGLE_WORD_EXPECTED = "Word parameter should be a single word";

    // Messages associated with various Commands
    public static final String MESSAGE_ADD_COMMAND_SUCCESS = "New contact added: %1$s";
    public static final String MESSAGE_COMMAND_DUPLICATE_CONTACT = "This contact is already in your contact list.";
    public static final String MESSAGE_DELETE_COMMAND_SUCCESS = "Deleted Contact: %1$s";
    public static final String MESSAGE_EDIT_COMMAND_SUCCESS = "Edited Contact: %1$s";
    public static final String MESSAGE_EDIT_COMMAND_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_HELP_COMMAND_SHOW_HELP = "Opened help window.";
    public static final String MESSAGE_LIST_COMMAND_SUCCESS = "Listed all contacts";

    // Exception message
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";
    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";
    public static final String MESSAGE_DUPLICATE_CONTACT_EXCEPTION = "Operation would result in duplicate contacts";

    // Messages associated with Attributes constraints
    public static final String MESSAGE_NAME_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String MESSAGE_PHONE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should be at least 3 digits long";
    public static final String MESSAGE_EMAIL_CONSTRAINTS = "Emails should be of the format local-part@domain "
            + "and adhere to the following constraints:\n"
            + "1. The local-part should only contain alphanumeric characters and these special characters, excluding "
            + "the parentheses, (" + Email.SPECIAL_CHARACTERS
            + "). The local-part may not start or end with any special "
            + "characters.\n"
            + "2. This is followed by a '@' and then a domain name. The domain name is made up of domain labels "
            + "separated by periods.\n"
            + "The domain name must:\n"
            + "    - end with a domain label at least 2 characters long\n"
            + "    - have each domain label start and end with alphanumeric characters\n"
            + "    - have each domain label consist of alphanumeric characters, separated only by hyphens, if any.";

    // Messages associated with Storage
    public static final String MESSAGE_FIELD_MISSING = "Contact's %s field is missing.";
    public static final String MESSAGE_CONTAIN_DUPLICATE_CONTACT = "Contact list contains duplicate contact(s).";

    //TODO refine the messages above this line

    // Commands
    public static final String CLEAR_COMMAND_SUCCESS = "All contacts have been removed!";
    public static final String EXIT_COMMAND_SUCCESS = "Exiting app...";

    // Tag
    private static final String UNFORMATTED_TAG_INVALID =
            "\"%s\" is not a valid tag. Tags must be alphanumeric (spaces allowed).";

    private Messages() {
        // No instantiation
    }

    /**
     * Returns a formatted message for the specified invalid tag name.
     *
     * @param invalidName Invalid name.
     */
    public static String tagInvalid(String invalidName) {
        return String.format(
            UNFORMATTED_TAG_INVALID,
            invalidName
        );
    }
}
