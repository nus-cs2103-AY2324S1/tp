package seedu.cc.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.cc.commons.exceptions.IllegalValueException;
import seedu.cc.model.medicalhistory.MedicalCondition;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;
import seedu.cc.model.medicalhistory.Treatment;
import seedu.cc.model.util.Date;

/**
 * Jackson-friendly version of {@link MedicalHistoryEvent}.
 */
public class JsonAdaptedMedicalHistoryEvent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Medical history event's %s field is missing!";

    private final String medicalCondition;
    private final String treatment;
    private final String date;

    /**
     * Constructs a {@code JsonAdaptedMedicalHistoryEvent} with the given medical history event details.
     */
    @JsonCreator
    public JsonAdaptedMedicalHistoryEvent(@JsonProperty("medicalCondition") String medicalCondition,
                                          @JsonProperty("treatment") String treatment,
                                          @JsonProperty("date") String date) {
        this.medicalCondition = medicalCondition;
        this.treatment = treatment;
        this.date = date;
    }

    /**
     * Converts a given {@code MedicalHistoryEvent} into this class for Jackson use.
     */
    public JsonAdaptedMedicalHistoryEvent(MedicalHistoryEvent source) {
        medicalCondition = source.getMedicalCondition().value;
        treatment = source.getTreatment().value;
        date = source.getDate().toString(); // Convert LocalDateTime to String
    }

    /**
     * Converts this Jackson-friendly adapted medical history event object into the model's {@code MedicalHistoryEvent}
     * object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted medical history event.
     */
    public MedicalHistoryEvent toModelType() throws IllegalValueException {
        if (medicalCondition == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Medical Condition"));
        }

        MedicalCondition modelMedicalCondition = new MedicalCondition(this.medicalCondition);

        if (treatment == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Treatment"));
        }

        Treatment modelTreatment = new Treatment(this.treatment);
        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Date"));
        }

        Date modelDate = new Date(this.date);

        return new MedicalHistoryEvent(modelMedicalCondition, modelTreatment, modelDate);
    }
}
