package seedu.address.testutil;

import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.mapping.Mapping;
import seedu.address.model.mapping.MappingMiscInformation;
import seedu.address.model.partnercourse.PartnerCode;
import seedu.address.model.university.UniversityName;

/**
 * A utility class to help with building Mapping objects.
 */
public class MappingBuilder {

    public static final String DEFAULT_LOCAL_CODE = "CS2103T";
    public static final String DEFAULT_PARTNER_CODE = "COMP2020T";
    public static final String DEFAULT_UNIVERSITY_NAME = "Stockholm University";
    public static final String DEFAULT_MAPPING_MISC_INFORMATION = "Semester 1 only.";

    private LocalCode localCode;
    private PartnerCode partnerCode;
    private UniversityName universityName;
    private MappingMiscInformation mappingMiscInformation;

    /**
     * Creates a {@code MappingBuilder} with the default details.
     */
    public MappingBuilder() {
        localCode = new LocalCode(DEFAULT_LOCAL_CODE);
        partnerCode = new PartnerCode(DEFAULT_PARTNER_CODE);
        universityName = new UniversityName(DEFAULT_UNIVERSITY_NAME);
        mappingMiscInformation = new MappingMiscInformation(DEFAULT_MAPPING_MISC_INFORMATION);
    }

    /**
     * Initializes the MappingBuilder with the data of {@code mappingToCopy}.
     */
    public MappingBuilder(Mapping mappingToCopy) {
        localCode = mappingToCopy.getLocalCode();
        partnerCode = mappingToCopy.getPartnerCode();
        universityName = mappingToCopy.getUniversityName();
        mappingMiscInformation = mappingToCopy.getMappingMiscInformation();
    }

    /**
     * Sets the {@code LocalCode} of the {@code Mapping} that we are building.
     */
    public MappingBuilder withLocalCode(String localCode) {
        this.localCode = new LocalCode(localCode);
        return this;
    }

    /**
     * Sets the {@code PartnerCode} of the {@code Mapping} that we are building.
     */
    public MappingBuilder withPartnerCode(String partnerCode) {
        this.partnerCode = new PartnerCode(partnerCode);
        return this;
    }

    /**
     * Sets the {@code UniversityName} of the {@code Mapping} that we are building.
     */
    public MappingBuilder withUniversityName(String universityName) {
        this.universityName = new UniversityName(universityName);
        return this;
    }

    /**
     * Sets the {@code MappingMiscInformation} of the {@code Mapping} that we are building.
     */
    public MappingBuilder withMappingMiscInformation(String mappingMiscInformation) {
        this.mappingMiscInformation = new MappingMiscInformation(mappingMiscInformation);
        return this;
    }

    public Mapping build() {
        return new Mapping(localCode, universityName, partnerCode, mappingMiscInformation);
    }

}
