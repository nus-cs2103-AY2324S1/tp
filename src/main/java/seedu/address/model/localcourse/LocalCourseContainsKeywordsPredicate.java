package seedu.address.model.localcourse;

import java.util.function.Predicate;

/**
 * Tests that a {@code LocalCourse}'s {@code LocalAttribute} matches any of the keywords given.
 */
public class LocalCourseContainsKeywordsPredicate implements Predicate<LocalCourse> {
    private final String keyword;
    private final LocalCourseAttribute attribute;

    /**
     * Creates a LocalCourseContainsKeywordsPredicate to initialise the keyword and attribute.
     * @param keywords
     * @param attribute
     */
    public LocalCourseContainsKeywordsPredicate(String keywords, LocalCourseAttribute attribute) {
        this.keyword = keywords;
        this.attribute = attribute;
    }

    @Override
    public boolean test(LocalCourse localCourse) {
        switch (attribute) {
        case LOCALNAME:
            return localCourse.getLocalName().getValue().toLowerCase().contains(keyword.toLowerCase());
        case LOCALCODE:
            return localCourse.getLocalCode().getValue().toLowerCase().contains(keyword.toLowerCase());
        case LOCALDESCRIPTION:
            return localCourse.getLocalDescription().getValue().toLowerCase().contains(keyword.toLowerCase());
        default:
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof LocalCourseContainsKeywordsPredicate)) {
            return false;
        }

        LocalCourseContainsKeywordsPredicate otherPredicate = (LocalCourseContainsKeywordsPredicate) other;
        return keyword.equals(otherPredicate.keyword)
                && attribute.equals(otherPredicate.attribute);
    }
}
