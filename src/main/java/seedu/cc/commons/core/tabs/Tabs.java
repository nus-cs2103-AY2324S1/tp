package seedu.cc.commons.core.tabs;

/**
 * Represents the tabs in the application.
 */
public enum Tabs {
    PATIENTS("Patients"),
    MEDICAL_HISTORY("Medical History"),
    APPOINTMENTS("Appointments");

    private final String displayName;

    Tabs(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
