package seedu.codesphere.testutil;

import seedu.codesphere.model.student.Field;
import seedu.codesphere.model.student.SortCriteria;

/**
 * A utility class to help with building SortCriteria objects.
 */
public class SortCriteriaBuilder {

    private Field sortField;

    /**
     * Initializes the SortCriteriaBuilder with the field specified.
     */
    public SortCriteriaBuilder(Field field) {
        sortField = field;
    }

    /**
     * Sets the sort field to be "NAME".
     */
    public SortCriteriaBuilder withNameSortField() {
        sortField = Field.NAME;
        return this;
    }

    /**
     * Sets the sort field to be "TAG".
     */
    public SortCriteriaBuilder withTagSortField() {
        sortField = Field.TAG;
        return this;
    }

    public SortCriteria build() {
        return new SortCriteria(sortField);
    }
}
