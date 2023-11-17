package seedu.address.model.animal;

/**
 * Represents the valid Sexes.
 */
public enum SexEnum {
    MALE("M"),
    FEMALE("F");

    private final String shortForm;

    SexEnum(String shortForm) {
        this.shortForm = shortForm;
    }

    public String getShortForm() {
        return shortForm;
    }

    /**
     * Returns true if the string supplied is equal to "MALE" or "FEMALE"/ part of SexEnum.
     * Compares case-insensitively.
     *
     * @param test the string to be checked for equality with any of the SexEnum.
     * @return true if the string is a SexEnum.
     */
    public static boolean isValid(String test) {
        for (SexEnum value : SexEnum.values()) {
            if (value.name().equalsIgnoreCase(test)) {
                return true;
            }
        }
        return false;
    }
}
