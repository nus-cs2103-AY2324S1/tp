package seedu.address.model.mapping;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.model.partnercourse.PartnerCode;
import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.model.university.UniversityName;

/**
 * Tests that a {@code University}'s {@code UniversityName} matches any of the keywords given.
 */
public class MappingAttributeContainsKeywordPredicate implements Predicate<Mapping> {
    private final String keyword;

    /* Crucial that this is initialised with a correct attribute */
    private final MappingAttribute attribute;

    private Function<? super LocalCode, ? extends Optional<? extends LocalCourse>> getLocalCourseIfExists;
    private BiFunction<? super PartnerCode, ? super UniversityName, ? extends
            Optional<? extends PartnerCourse>> getPartnerCourseIfExists;

    private boolean isGetLocalPartnerCourseInitialised = false;

    /**
     * Constructor for MappingAttributeContainsKeywordPredicate.
     *
     * @param keyword   This is the query.
     * @param attribute This is the attribute to be searched.
     */
    public MappingAttributeContainsKeywordPredicate(String keyword, MappingAttribute attribute) {
        this.keyword = keyword;
        this.attribute = attribute;
    }

    /**
     * Initialises the getLocalCourseIfExists and getPartnerCourseIfExists functions.
     */
    public void initialiseGetLocalPartnercourse(Function<? super LocalCode, ? extends Optional<? extends LocalCourse>>
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

    /**
     * Returns the queried keyword.
     */
    public String getKeyword() {
        return keyword;
    }

    @Override
    public boolean test(Mapping mapping) {
        switch (attribute) {
        case LOCALCODE:
            return isQueryContainedInLocalCode(keyword, mapping);
        case LOCALNAME:
            if (!isGetLocalPartnerCourseInitialised) {
                break;
            }
            return isQueryContainedInLocalName(keyword, mapping, getLocalCourseIfExists);
        case PARTNERCODE:
            return isQueryContainedInPartnerCode(keyword, mapping);
        case PARTNERNAME:
            if (!isGetLocalPartnerCourseInitialised) {
                break;
            }
            return isQueryContainedInPartnerName(keyword, mapping, getPartnerCourseIfExists);
        case UNIVERSITY:
            return isQueryContainedInUniversityName(keyword, mapping);
        case INFORMATION:
            return isQueryContainedInMappingMiscInformation(keyword, mapping);
        default:
            return false;
        }
        return false;
    }


    private boolean isQueryContainedInLocalCode(String query,
                                                Mapping mapping) {
        return mapping.getLocalCode().getValue().toLowerCase().contains(query.toLowerCase());
    }


    private boolean isQueryContainedInPartnerName(String query,
                                                  Mapping mapping,
                                                  BiFunction<? super PartnerCode, ? super UniversityName, ? extends
                                                          Optional<? extends PartnerCourse>>
                                                          getPartnerCourseIfExists) {
        String partnerName = getPartnerCourseIfExists.apply(mapping.getPartnerCode(), mapping.getUniversityName())
                .map(pc -> pc.getPartnerName().getValue()).orElse("");
        return partnerName.toLowerCase().contains(query.toLowerCase());
    }


    private boolean isQueryContainedInPartnerCode(String query,
                                                  Mapping mapping) {
        return mapping.getPartnerCode().getValue().toLowerCase().contains(query.toLowerCase());
    }


    private boolean isQueryContainedInLocalName(String query,
                                                Mapping mapping,
                                                Function<? super LocalCode, ? extends
                                                        Optional<? extends LocalCourse>> getLocalCourseIfExists) {
        String localName = getLocalCourseIfExists.apply(mapping.getLocalCode())
                .map(lc -> lc.getLocalName().getValue()).orElse("");
        return localName.toLowerCase().contains(query.toLowerCase());
    }


    public boolean isQueryContainedInUniversityName(String query,
                                                    Mapping mapping) {
        return mapping.getUniversityName().getValue().toLowerCase().contains(query.toLowerCase());
    }


    public boolean isQueryContainedInMappingMiscInformation(String query,
                                                            Mapping mapping) {
        return mapping.getMappingMiscInformation().getValue().toLowerCase().contains(query.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MappingAttributeContainsKeywordPredicate)) {
            return false;
        }

        MappingAttributeContainsKeywordPredicate otherContainsKeywordsPredicate =
                (MappingAttributeContainsKeywordPredicate) other;
        return keyword.equals(otherContainsKeywordsPredicate.keyword)
                && attribute.equals(otherContainsKeywordsPredicate.attribute);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("attribute", attribute)
                .add("keyword", keyword).toString();
    }
}



