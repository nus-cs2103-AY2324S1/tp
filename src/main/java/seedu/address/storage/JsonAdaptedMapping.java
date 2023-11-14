package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.messages.ConstraintMessage;
import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.mapping.Mapping;
import seedu.address.model.mapping.MappingMiscInformation;
import seedu.address.model.partnercourse.PartnerCode;
import seedu.address.model.university.UniversityName;

/**
 * Jackson-friendly version of {@link seedu.address.model.mapping.Mapping}.
 */
class JsonAdaptedMapping {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Mapping's %s field is missing!";

    private final String localCode;
    private final String universityName;
    private final String partnerCode;
    private final String mappingMiscInformation;


    /**
     * Constructs a {@code JsonAdaptedMapping} with the given mapping details.
     */
    @JsonCreator
    public JsonAdaptedMapping(@JsonProperty("localCode") String localCode,
                              @JsonProperty("universityName") String universityName,
                              @JsonProperty("partnerCode") String partnerCode,
                              @JsonProperty("mappingMiscInformation") String mappingMiscInformation) {
        this.localCode = localCode;
        this.universityName = universityName;
        this.partnerCode = partnerCode;
        this.mappingMiscInformation = mappingMiscInformation;
    }

    /**
     * Converts a given {@code LocalCourse} into this class for Jackson use.
     */
    public JsonAdaptedMapping(Mapping source) {
        localCode = source.getLocalCode().getValue();
        universityName = source.getUniversityName().getValue();
        partnerCode = source.getPartnerCode().getValue();
        mappingMiscInformation = source.getMappingMiscInformation().getValue();
    }

    /**
     * Converts this Jackson-friendly adapted mapping object into the model's {@code Mapping} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted mapping.
     */
    public Mapping toModelType() throws IllegalValueException {

        if (localCode == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalCode.class.getSimpleName()));
        }
        if (!LocalCode.isValidLocalCode(localCode)) {
            throw new IllegalValueException(ConstraintMessage.LOCALCOURSE_CODE.toString());
        }
        final LocalCode modelLocalCode = new LocalCode(localCode);

        if (universityName == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, UniversityName.class.getSimpleName()));
        }
        if (!UniversityName.isValidUniversityName(universityName)) {
            throw new IllegalValueException(ConstraintMessage.UNIVERSITY_NAME.toString());
        }
        final UniversityName modelUniversityName = new UniversityName(universityName);

        if (partnerCode == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, PartnerCode.class.getSimpleName()));
        }
        if (!PartnerCode.isValidPartnerCode(partnerCode)) {
            throw new IllegalValueException(ConstraintMessage.PARTNERCOURSE_CODE.toString());
        }
        final PartnerCode modelPartnerCode = new PartnerCode(partnerCode);

        if (mappingMiscInformation == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, MappingMiscInformation.class.getSimpleName()));
        }
        if (!MappingMiscInformation.isValidMappingMiscInformation(mappingMiscInformation)) {
            throw new IllegalValueException(MappingMiscInformation.MESSAGE_CONSTRAINTS);
        }
        final MappingMiscInformation modelMappingMiscInformation = new MappingMiscInformation(mappingMiscInformation);

        return new Mapping(modelLocalCode, modelUniversityName, modelPartnerCode, modelMappingMiscInformation);
    }

}
