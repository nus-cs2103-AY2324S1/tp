package seedu.address.testutil;

import seedu.address.commons.core.index.Index;

/**
 * A utility class containing a list of {@code Index} and ID objects to be used in tests.
 */
public class TypicalIndexes {
    public static final Index INDEX_FIRST_PERSON = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_PERSON = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_PERSON = Index.fromOneBased(3);

    public static final long FIRST_DENTIST_ID = 1;
    public static final long SECOND_DENTIST_ID = 2;
    public static final long THIRD_DENTIST_ID = 3;

    public static final long FIRST_PATIENT_ID = 1;
    public static final long SECOND_PATIENT_ID = 2;
    public static final long FIRST_APPOINTMENT_ID = 1;
    public static final String FIRST_TREATMENT_NAME = "Cleaning";
}
