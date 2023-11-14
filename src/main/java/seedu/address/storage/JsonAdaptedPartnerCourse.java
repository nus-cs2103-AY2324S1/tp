package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.messages.ConstraintMessage;
import seedu.address.model.partnercourse.PartnerCode;
import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.model.partnercourse.PartnerDescription;
import seedu.address.model.partnercourse.PartnerName;
import seedu.address.model.partnercourse.PartnerUnit;
import seedu.address.model.university.University;
import seedu.address.model.university.UniversityName;

/**
 * Jackson-friendly version of {@link PartnerCourse}.
 */
public class JsonAdaptedPartnerCourse {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "PartnerCourse's %s field is missing!";

    private final String universityName;
    private final String partnerCode;
    private final String partnerName;
    private final Double partnerUnit;
    private final String partnerDescription;

    /**
     * Constructs a {@code JsonAdaptedPartnerCourse} with the given partnerCourse details.
     */
    @JsonCreator
    public JsonAdaptedPartnerCourse(
        @JsonProperty("universityName") String universityName,
        @JsonProperty("partnerCode") String partnerCode,
        @JsonProperty("partnerName") String partnerName,
        @JsonProperty("partnerUnit") Double partnerUnit,
        @JsonProperty("partnerDescription") String partnerDescription) {
        this.universityName = universityName;
        this.partnerCode = partnerCode;
        this.partnerName = partnerName;
        this.partnerUnit = partnerUnit;
        this.partnerDescription = partnerDescription;
    }

    /**
     * Converts a given {@code PartnerCourse} into this class for Jackson use.
     */
    public JsonAdaptedPartnerCourse(PartnerCourse source) {
        universityName = source.getPartnerUniversity().getUniversityName().getValue();
        partnerCode = source.getPartnerCode().getValue();
        partnerName = source.getPartnerName().getValue();
        partnerUnit = source.getPartnerUnit().getValue();
        partnerDescription = source.getPartnerDescription().getValue();
    }

    /**
     * Converts this Jackson-friendly adapted partnerCourse object into the model's {@code PartnerCourse} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted partnerCourse.
     */
    public PartnerCourse toModelType() throws IllegalValueException {

        if (universityName == null) {
            throw new IllegalValueException(String.format(
                MISSING_FIELD_MESSAGE_FORMAT,
                UniversityName.class.getSimpleName()));
        }
        if (!UniversityName.isValidUniversityName(universityName)) {
            throw new IllegalValueException(ConstraintMessage.UNIVERSITY_NAME.toString());
        }
        final University modelUniversity = new University(new UniversityName(universityName));

        if (partnerCode == null) {
            throw new IllegalValueException(String.format(
                MISSING_FIELD_MESSAGE_FORMAT,
                PartnerCode.class.getSimpleName()));
        }
        if (!PartnerCode.isValidPartnerCode(partnerCode)) {
            throw new IllegalValueException(ConstraintMessage.PARTNERCOURSE_CODE.toString());
        }
        final PartnerCode modelPartnerCode = new PartnerCode(partnerCode);

        if (partnerName == null) {
            throw new IllegalValueException(String.format(
                MISSING_FIELD_MESSAGE_FORMAT,
                PartnerName.class.getSimpleName()));
        }
        if (!PartnerName.isValidPartnerName(partnerName)) {
            throw new IllegalValueException(ConstraintMessage.PARTNERCOURSE_NAME.toString());
        }
        final PartnerName modelPartnerName = new PartnerName(partnerName);

        if (partnerUnit == null) {
            throw new IllegalValueException(String.format(
                MISSING_FIELD_MESSAGE_FORMAT,
                PartnerUnit.class.getSimpleName()));
        }
        if (!PartnerUnit.isValidPartnerUnit(partnerUnit)) {
            throw new IllegalValueException(ConstraintMessage.PARTNERCOURSE_UNIT.toString());
        }
        final PartnerUnit modelPartnerUnit = new PartnerUnit(partnerUnit);

        if (partnerDescription == null) {
            throw new IllegalValueException(String.format(
                MISSING_FIELD_MESSAGE_FORMAT,
                PartnerDescription.class.getSimpleName()));
        }
        if (!PartnerDescription.isValidPartnerDescription(partnerDescription)) {
            throw new IllegalValueException(ConstraintMessage.PARTNERCOURSE_UNIT.toString());
        }
        final PartnerDescription modelPartnerDescription = new PartnerDescription(partnerDescription);

        return new PartnerCourse(
            modelUniversity,
            modelPartnerCode,
            modelPartnerName,
            modelPartnerUnit,
            modelPartnerDescription);
    }
}
