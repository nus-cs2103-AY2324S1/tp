package networkbook.logic.commands.filter;

import networkbook.model.person.filter.GradEqualsOneOfPredicate;

/**
 * Filters the list of contacts to contacts that have grad years
 * equal to one of the given years.
 */
public class FilterGradCommand {
    public static final String FIELD_NAME = "course";
    private GradEqualsOneOfPredicate yearsPredicate;
    public FilterGradCommand(GradEqualsOneOfPredicate yearsPredicate) {
        this.yearsPredicate = yearsPredicate;
    }
}
