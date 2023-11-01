package networkbook.logic.commands.filter;

import networkbook.commons.util.ToStringBuilder;
import networkbook.logic.commands.CommandResult;
import networkbook.model.Model;
import networkbook.model.person.filter.CourseContainsKeyTermsPredicate;
import networkbook.model.person.filter.CourseIsStillBeingTakenPredicate;

/**
 * Filters the list of contacts to contacts that have courses that contain
 * at least one course that contains some specified key terms.
 *
 * Additionally, we can further specify whether all courses should be counted,
 * or only contacts that are currently taking the courses are counted.
 */
public class FilterCourseCommand extends FilterCommand {

    private final CourseContainsKeyTermsPredicate keyTermsPredicate;
    private final CourseIsStillBeingTakenPredicate takenPredicate;
    private final boolean checkFin;

    /**
     * Creates a FilterCommand object that searches using a list of key terms,
     * a specified date to check whether a course is being taken, and a
     * boolean that specifies whether the date check needs to be done.
     */
    public FilterCourseCommand(CourseContainsKeyTermsPredicate keyTermsPredicate,
                         CourseIsStillBeingTakenPredicate takenPredicate,
                         boolean checkFin) {
        this.keyTermsPredicate = keyTermsPredicate;
        this.takenPredicate = takenPredicate;
        this.checkFin = checkFin;
    }

    /**
     * Executes the FilterCommand object and returns a message to the user.
     *
     * @param model {@code Model} which the command should operate on.
     */
    public CommandResult execute(Model model) {
        assert model != null : "Model should not be null";
        model.updateFilteredPersonList(keyTermsPredicate);
        String feedback = String.format(MESSAGE_SUCCESS, keyTermsPredicate.getKeyTerms()
                .stream()
                .reduce("", (acc, term) -> acc + " \"" + term + "\"")
                .trim()
                .replace(" ", ", "));
        if (checkFin) {
            model.updateFilteredPersonList(person -> keyTermsPredicate.getCourses(person)
                    .stream()
                    .anyMatch(course -> takenPredicate.test(course)));
            feedback += MESSAGE_EXCL_FIN;
        }
        return new CommandResult(feedback
                + String.format(MESSAGE_PERSONS_FOUND_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCourseCommand)) {
            return false;
        }

        FilterCourseCommand otherFilterCommand = (FilterCourseCommand) other;
        return takenPredicate.equals(otherFilterCommand.takenPredicate)
                && keyTermsPredicate.equals(otherFilterCommand.keyTermsPredicate)
                && checkFin == otherFilterCommand.checkFin;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", keyTermsPredicate)
                .add("time", takenPredicate)
                .add("taken", checkFin)
                .toString();
    }
}
