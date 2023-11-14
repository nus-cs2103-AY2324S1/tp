package seedu.address.model.partnercourse;

import java.util.function.Predicate;

/**
 * Tests that a {@code PartnerCourse}'s {@code PartnerAttribute} matches any of the keywords given.
 */
public class PartnerCourseContainsKeywordsPredicate implements Predicate<PartnerCourse> {
    private final String keyword;
    private final PartnerCourseAttribute attribute;

    /**
     * Creates a PartnerCourseContainsKeywordsPredicate to initialise the keyword and attribute.
     * @param keywords
     * @param attribute
     */
    public PartnerCourseContainsKeywordsPredicate(String keywords, PartnerCourseAttribute attribute) {
        this.keyword = keywords;
        this.attribute = attribute;
    }

    @Override
    public boolean test(PartnerCourse partnerCourse) {
        switch (attribute) {
        case PARTNERNAME:
            return partnerCourse.getPartnerName().getValue().toLowerCase().contains(keyword.toLowerCase());
        case PARTNERCODE:
            return partnerCourse.getPartnerCode().getValue().toLowerCase().contains(keyword.toLowerCase());
        case PARTNERDESCRIPTION:
            return partnerCourse.getPartnerDescription().getValue().toLowerCase().contains(keyword.toLowerCase());
        case UNIVERSITY:
            return partnerCourse.getPartnerUniversity().getUniversityName().getName().toLowerCase()
                    .contains(keyword.toLowerCase());
        default:
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PartnerCourseContainsKeywordsPredicate)) {
            return false;
        }

        PartnerCourseContainsKeywordsPredicate otherPredicate = (PartnerCourseContainsKeywordsPredicate) other;
        return keyword.equals(otherPredicate.keyword)
                && attribute.equals(otherPredicate.attribute);
    }
}
