package seedu.address.model.mapping;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.model.partnercourse.PartnerCode;
import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.model.university.UniversityName;

/**
 * Compares two instances of Mapping based on given attribute.
 */
public class MappingComparatorByAttribute implements Comparator<Mapping> {

    /* Crucial that this is initialised with a correct attribute */
    private final MappingAttribute attribute;

    private Function<? super LocalCode, ? extends Optional<? extends LocalCourse>> getLocalCourseIfExists;
    private BiFunction<? super PartnerCode, ? super UniversityName, ? extends
            Optional<? extends PartnerCourse>> getPartnerCourseIfExists;

    private boolean isGetLocalPartnerCourseInitialised = false;

    public MappingComparatorByAttribute(MappingAttribute attribute) {
        this.attribute = attribute;
    }

    /**
     * Initialises the getLocalCourseIfExists and getPartnerCourseIfExists functions.
     */
    public void initialiseGetLocalPartnerCourse(Function<? super LocalCode, ? extends Optional<? extends LocalCourse>>
                                                        getLocalCourseIfExists,
                                                BiFunction<? super PartnerCode, ? super UniversityName, ? extends
                                                        Optional<? extends PartnerCourse>> getPartnerCourseIfExists) {
        this.getLocalCourseIfExists = getLocalCourseIfExists;
        this.getPartnerCourseIfExists = getPartnerCourseIfExists;
        isGetLocalPartnerCourseInitialised = true;
    }

    /**
     * Returns the mapping attribute.
     */
    public MappingAttribute getAttribute() {
        return attribute;
    }

    @Override
    public int compare(Mapping m1, Mapping m2) {
        switch (attribute) {
        case LOCALCODE:
            return compareBasedOnLocalCode(m1, m2);
        case LOCALNAME:
            if (!isGetLocalPartnerCourseInitialised) {
                break;
            }
            return compareBasedOnLocalName(m1, m2);
        case PARTNERCODE:
            return compareBasedOPartnerCode(m1, m2);
        case PARTNERNAME:
            if (!isGetLocalPartnerCourseInitialised) {
                break;
            }
            return compareBasedOnPartnerName(m1, m2);
        case UNIVERSITY:
            return compareBasedOnUniversityName(m1, m2);
        case INFORMATION:
            return compareBasedOnInformation(m1, m2);
        default:
            // This will not happen
            return 0;
        }
        return 0;
    }

    private int compareBasedOnLocalCode(Mapping m1, Mapping m2) {
        int diff = m1.getLocalCode().getValue().compareToIgnoreCase(
                m2.getLocalCode().getValue()
        );
        return Integer.compare(diff, 0);
    }

    private int compareBasedOPartnerCode(Mapping m1, Mapping m2) {
        int diff = m1.getPartnerCode().getValue().compareToIgnoreCase(
                m2.getPartnerCode().getValue()
        );
        return Integer.compare(diff, 0);
    }

    private int compareBasedOnUniversityName(Mapping m1, Mapping m2) {
        int diff = m1.getUniversityName().getValue().compareToIgnoreCase(
                m2.getUniversityName().getValue()
        );
        return Integer.compare(diff, 0);
    }

    private int compareBasedOnLocalName(Mapping m1, Mapping m2) {
        String localNameOfM1 = getLocalCourseIfExists
                .apply(m1.getLocalCode()).map(lc -> lc.getLocalName().getValue()).orElse("");
        String localNameOfM2 = getLocalCourseIfExists
                .apply(m2.getLocalCode()).map(lc -> lc.getLocalName().getValue()).orElse("");
        int diff = localNameOfM1.compareToIgnoreCase(localNameOfM2);
        return Integer.compare(diff, 0);
    }

    private int compareBasedOnPartnerName(Mapping m1, Mapping m2) {
        String partnerNameOfM1 = getPartnerCourseIfExists
                .apply(m1.getPartnerCode(), m1.getUniversityName())
                .map(pc -> pc.getPartnerName().getValue()).orElse("");
        String partnerNameOfM2 = getPartnerCourseIfExists
                .apply(m2.getPartnerCode(), m2.getUniversityName())
                .map(pc -> pc.getPartnerName().getValue()).orElse("");
        int diff = partnerNameOfM1.compareToIgnoreCase(partnerNameOfM2);
        return Integer.compare(diff, 0);
    }

    private int compareBasedOnInformation(Mapping m1, Mapping m2) {
        int diff = m1.getMappingMiscInformation().getValue().compareToIgnoreCase(
                m2.getMappingMiscInformation().getValue()
        );
        return Integer.compare(diff, 0);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MappingComparatorByAttribute)) {
            return false;
        }

        MappingComparatorByAttribute otherComparator = (MappingComparatorByAttribute) other;
        return attribute.equals(otherComparator.attribute);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("attribute", attribute).toString();
    }
}
