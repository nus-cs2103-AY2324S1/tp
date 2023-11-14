package seedu.address.testutil;

import java.util.Comparator;

import seedu.address.logic.commands.localcourse.LocalCourseAddCommand;
import seedu.address.logic.commands.localcourse.LocalCourseDeleteCommand;
import seedu.address.logic.commands.localcourse.LocalCourseSortCommand;
import seedu.address.model.localcourse.LocalCourse;

/**
 * A utility class for LocalCourse.
 */
public class LocalCourseUtil {

    /**
     * Returns an add command string for adding the {@code LocalCourse}.
     */
    public static String getLocalCourseAddCommandFrom(LocalCourse localCourse) {
        return String.format("%s %s %s",
                LocalCourseAddCommand.COMMAND_WORD,
                LocalCourseAddCommand.ACTION_WORD,
                getLocalCourseArgumentsForAddCommand(localCourse));
    }

    /**
     * Returns the part of the add command string for the given {@code localCourse}'s details.
     */
    public static String getLocalCourseArgumentsForAddCommand(LocalCourse localCourse) {
        return String.format("[%s] [%s] [%s] [%s]",
                localCourse.getLocalCode(),
                localCourse.getLocalName(),
                localCourse.getLocalUnit(),
                localCourse.getLocalDescription());
    }

    /**
     * Returns a delete command string for deleting the {@code LocalCourse}.
     */
    public static String getLocalCourseDeleteCommandFrom(LocalCourse localCourse) {
        return String.format("%s %s %s",
                LocalCourseDeleteCommand.COMMAND_WORD,
                LocalCourseDeleteCommand.ACTION_WORD,
                getLocalCourseArgumentsForDeleteCommand(localCourse));
    }

    /**
     * Returns the part of delete command string for the given {@code LocalCourse}'s details.
     */
    public static String getLocalCourseArgumentsForDeleteCommand(LocalCourse localCourse) {
        return String.format("[%s]", localCourse.getLocalCode());
    }

    /**
     * Returns a sort command string for sorting the localcourse list.
     */
    public static String getLocalCourseSortCommandFrom(Comparator<LocalCourse> localCourseComparator) {
        return String.format("%s %s %s",
                LocalCourseSortCommand.COMMAND_WORD,
                LocalCourseSortCommand.ACTION_WORD,
                getLocalCourseArgumentsForSortCommand(localCourseComparator));
    }

    /**
     * Returns the part of sort command string for the given comparator's details.
     */
    public static String getLocalCourseArgumentsForSortCommand(Comparator<LocalCourse> comparator) {
        return String.format("[%s]", comparator.toString());
    }
}
