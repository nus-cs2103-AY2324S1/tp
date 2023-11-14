package seedu.address.model.mapping;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.SeplendidDataType;
import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.partnercourse.PartnerCode;
import seedu.address.model.university.UniversityName;

/**
 * Represents a Course Mapping in SEPlendid.
 * Guarantees: details are present and not null, field values are validated and immutable.
 */
public class Mapping implements SeplendidDataType {

    // Identity fields
    private final LocalCode localCode;
    private final PartnerCode partnerCode;
    private final UniversityName universityName;
    private final MappingMiscInformation miscInformation;


    /**
     * Every field must be present and not null
     */
    public Mapping(LocalCode localCode, UniversityName universityName,
                   PartnerCode partnerCode, MappingMiscInformation miscInformation) {
        requireAllNonNull(localCode, partnerCode, universityName, miscInformation);
        this.localCode = localCode;
        this.partnerCode = partnerCode;
        this.universityName = universityName;
        this.miscInformation = miscInformation;
    }

    // Getters
    public LocalCode getLocalCode() {
        return localCode;
    }

    public PartnerCode getPartnerCode() {
        return partnerCode;
    }

    public UniversityName getUniversityName() {
        return universityName;
    }

    public MappingMiscInformation getMappingMiscInformation() {
        return miscInformation;
    }

    /**
     * Returns true if both Mappings have the same localCode, partnerCode and university.
     * Assumption: localCode, partnerCode and university is the primary key i.e. there exists
     * no two distinct Mappings with the same primary key.
     *
     * @param otherMapping This is the other mapping.
     */
    public boolean isSameMapping(Mapping otherMapping) {
        if (otherMapping == this) {
            return true;
        }

        return otherMapping != null
                && otherMapping.getLocalCode().equals(getLocalCode())
                && otherMapping.getPartnerCode().equals(getPartnerCode())
                && otherMapping.getUniversityName().equals(getUniversityName());
    }

    /**
     * Returns true if both Mappings have the same identity and data fields.
     * This defines a stronger notion of equality between two mappings.
     *
     * @param other The other presumably Mapping object to compare with.
     * @return true if both objects are equal.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof Mapping)) {
            return false;
        }

        Mapping otherMapping = (Mapping) other;
        return localCode.equals(otherMapping.localCode)
                && partnerCode.equals(otherMapping.partnerCode)
                && universityName.equals(otherMapping.universityName)
                && miscInformation.equals(otherMapping.miscInformation);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(localCode, partnerCode, universityName, miscInformation);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("localCode", localCode)
                .add("partnerCode", partnerCode)
                .add("universityName", universityName)
                .add("miscInformation", miscInformation)
                .toString();
    }
}
