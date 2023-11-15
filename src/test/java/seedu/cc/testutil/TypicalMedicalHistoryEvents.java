package seedu.cc.testutil;

import seedu.cc.model.medicalhistory.MedicalHistoryEvent;

/**
 * A utility class containing {@code MedicalHistoryEvent} objects to be used in tests.
 */
public class TypicalMedicalHistoryEvents {
    public static final MedicalHistoryEvent CANCER = new MedicalHistoryEventBuilder().withMedicalCondition("Cancer")
            .withTreatment("Chemotherapy").withDate("2020-10-10").build();

    public static final MedicalHistoryEvent DIABETES = new MedicalHistoryEventBuilder().withMedicalCondition("Diabetes")
            .withTreatment("Insulin").withDate("2020-10-11").build();
}
