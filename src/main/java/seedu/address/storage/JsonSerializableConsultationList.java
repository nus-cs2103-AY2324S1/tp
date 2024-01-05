package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ConsultationListBook;
import seedu.address.model.ReadOnlyConsultationList;
import seedu.address.model.consultation.Consultation;


/**
 * An Immutable ConsultationList that is serializable to JSON format.
 */
@JsonRootName(value = "consultationlist")
public class JsonSerializableConsultationList {
    public static final String MESSAGE_DUPLICATE_CONSULTATION = "Consultation list contains duplicate consultation(s).";

    private final List<JsonAdaptedConsultation> consultations = new ArrayList<>();

    @JsonCreator
    public JsonSerializableConsultationList(@JsonProperty("consultations")
                                                List<JsonAdaptedConsultation> consultations) {
        this.consultations.addAll(consultations);
    }

    /**
     * Converts a given {@code ReadOnlyConsultationList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableConsultationList}.
     */
    public JsonSerializableConsultationList(ReadOnlyConsultationList source) {
        consultations.addAll(source.getConsultationList().stream().map(JsonAdaptedConsultation::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this consultation list into the model's {@code ConsultationListBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ConsultationListBook toModelType() throws IllegalValueException {
        ConsultationListBook consultationList = new ConsultationListBook();
        for (JsonAdaptedConsultation jsonAdaptedConsultation : consultations) {
            Consultation consultation = jsonAdaptedConsultation.toModelType();
            if (consultationList.hasConsultation(consultation)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CONSULTATION);
            }
            consultationList.addConsultation(consultation);
        }
        return consultationList;
    }
}
