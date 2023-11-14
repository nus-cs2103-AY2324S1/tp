package seedu.address.messages;

/**
 * Enum for model constraint messages.
 */
public enum ConstraintMessage {
    // LocalCourse messages
    LOCALCOURSE_CODE(
        "localcode:\n"
        + "Must be non-empty\n"
        + "Must start with an alphabet\n"
        + "Be no more than 10 alphanumeric characters\n"),

    LOCALCOURSE_NAME(
       "localname:\n"
        + "Must be non-empty\n"
        + "Must be between 1-76 characters long\n"),

    LOCALCOURSE_UNIT(
        "unit:\n"
        + "Must be non-empty\n"
        + "Must be a numeric value between 0 and 10000\n"),

    LOCALCOURSE_DESCRIPTION("localdescription must:\n"
                            + "Must be non-empty\n"),

    LOCALCOURSE_ATTRIBUTE_SEARCH(
        "There are only 3 attributes available for localcourse search:\n"
        + "localcode\n"
        + "localname\n"
        + "localdescription\n"),

    LOCALCOURSE_ATTRIBUTE_SORT(
        "There are only 2 attributes available for localcourse sort:\n"
        + "localcode\n"
        + "localname\n"),

    LOCALCOURSE_ATTRIBUTE_UPDATE(
        "There are only 4 attributes available for localcourse update:\n"
        + "localcode\n"
        + "localname\n"
        + "unit\n"
        + "localdescription\n"),

    // PartnerCourse messages
    PARTNERCOURSE_CODE(
        "partnercode:\n"
        + "Must be non-empty\n"
        + "Must start with an alphabet\n"
        + "Be no more than 10 alphanumeric characters\n"),

    PARTNERCOURSE_NAME(
        "partnername:\n"
        + "Must not be blank\n"),

    PARTNERCOURSE_UNIT(
        "unit:\n"
        + "Must be non-empty\n"
        + "Must be a non-negative numeric value\n"),

    PARTNERCOURSE_DESCRIPTION(
        "description must:\n"
        + "Must be non-empty\n"),

    PARTNERCOURSE_ATTRIBUTE_SEARCH(
        "There are only 4 attributes available for partnercourse search:\n"
        + "partnercode\n"
        + "partnername\n"
        + "description\n"
        + "university\n"),

    PARTNERCOURSE_ATTRIBUTE_SORT(
        "There are only 3 attributes available for partnercourse sort:\n"
        + "partnercode\n"
        + "partnername\n"
        + "university\n"),

    PARTNERCOURSE_ATTRIBUTE_UPDATE(
        "There are only 4 attributes available for partnercourse update:\n"
        + "partnercode\n"
        + "partnername\n"
        + "unit\n"
        + "description\n"),

    // Mapping messages
    MAPPING_ATTRIBUTE_SEARCH(
        "There are only 6 available for mapping search:\n"
        + "localcode\n"
        + "localname\n"
        + "partnercode\n"
        + "partnername\n"
        + "university\n"
        + "information\n"),

    MAPPING_ATTRIBUTE_SORT(
        "There are only 6 available for mapping sort:\n"
        + "localcode\n"
        + "localname\n"
        + "partnercode\n"
        + "partnername\n"
        + "university\n"
        + "information\n"),

    // University messages
    UNIVERSITY_NAME(
        "university:"
        + "Must be non-empty"),

    UNIVERSITY_ATTRIBUTE_SEARCH(
        "There is only 1 available for university search:\n"
        + "university\n"),

    UNIVERSITY_ATTRIBUTE_SORT(
        "There is only 1 available for university sort:\n"
        + "university\n"),
    // Note messages
    NOTE_CONTENT(
        "content:\n"
        + "Must only contain alphanumeric characters or spaces\n"
        + "Must be non-empty"),

    // Tag messages
    TAG_NAME(
        "tag:\n"
        + "Must only contain alphanumeric characters\n"
        + "Must not contain whitespaces");

    private final String value;

    /**
     * Constructs a ConstraintMessage with the specified value.
     *
     * @param description The usage message value.
     */
    ConstraintMessage(String description) {
        this.value = description;
    }

    /**
     * Gets the string representation of the constraint message.
     *
     * @return The usage message string.
     */
    @Override
    public String toString() {
        return value;
    }
}
