package seedu.address.logic.sort;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

import seedu.address.logic.sort.enums.SortOrderEnum;
import seedu.address.model.task.Task;

/**
 * Class that contains utility methods that help to sort the Tasks in the Task List.
 */
public abstract class SortUtil {
    private static int sortTaskNameAscending(Task task1, Task task2) {
        assertAllTasksNotNull(task1, task2);

        return task1.getDescription().compareTo(task2.getDescription());
    }

    private static int sortTaskNameDescending(Task task1, Task task2) {
        assertAllTasksNotNull(task1, task2);

        // invert the conditions from ascending
        return -1 * SortUtil.sortTaskNameAscending(task1, task2);
    }

    private static int sortPriorityAscending(Task task1, Task task2) {
        assertAllTasksNotNull(task1, task2);

        return task1.getPriority().compareTo(task2.getPriority());
    }

    private static int sortPriorityDescending(Task task1, Task task2) {
        assertAllTasksNotNull(task1, task2);

        return -1 * SortUtil.sortPriorityAscending(task1, task2);
    }

    private static int sortDeadlineAscending(Task task1, Task task2) {
        assertAllTasksNotNull(task1, task2);

        return task1.getDeadline().compareTo(task2.getDeadline());
    }

    private static int sortDeadlineDescending(Task task1, Task task2) {
        assertAllTasksNotNull(task1, task2);

        return -1 * SortUtil.sortDeadlineAscending(task1, task2);
    }

    private static int sortStatusAscending(Task task1, Task task2) {
        assertAllTasksNotNull(task1, task2);

        return task1.getStatus().compareTo(task2.getStatus());
    }

    private static int sortStatusDescending(Task task1, Task task2) {
        assertAllTasksNotNull(task1, task2);

        return -1 * SortUtil.sortStatusAscending(task1, task2);
    }

    private static void assertAllTasksNotNull(Task... tasks) {
        if (Stream.of(tasks).anyMatch(Objects::isNull)) {
            throw new AssertionError("Task List should not have null items");
        }
    }

    /**
     * Returns a {@code Comparator<Task>}, which compares two Tasks and returns an integer
     * indicating if the first object is less than, equal to or larger than the second object.
     * <p>
     * This function is used to generate the comparator function for sorting by Task Description.
     * </p>
     *
     * @param sortOrderEnum The order to sort the task by, represented by a {@code SortOrderEnum}
     * @return {@code Comparator<Task>} object
     */
    public static Comparator<Task> ofTaskName(SortOrderEnum sortOrderEnum) {
        assert (sortOrderEnum != null);
        switch (sortOrderEnum) {
        case ASCENDING:
            return SortUtil::sortTaskNameAscending;
        case DESCENDING:
            return SortUtil::sortTaskNameDescending;
        default:
            throw new IllegalStateException("No such Enum state!");
        }
    }

    /**
     * Returns a {@code Comparator<Task>}, which compares two Tasks and returns an integer
     * indicating if the first object is less than, equal to or larger than the second object.
     * <p>
     * This function is used to generate the comparator function for sorting by Task Priority.
     * </p>
     *
     * @param sortOrderEnum The order to sort the task by, represented by a {@code SortOrderEnum}
     * @return {@code Comparator<Task>} object
     */
    public static Comparator<Task> ofPriority(SortOrderEnum sortOrderEnum) {
        assert (sortOrderEnum != null);
        switch (sortOrderEnum) {
        case ASCENDING:
            return SortUtil::sortPriorityAscending;
        case DESCENDING:
            return SortUtil::sortPriorityDescending;
        default:
            throw new IllegalStateException("No such Enum state!");
        }
    }

    /**
     * Returns a {@code Comparator<Task>}, which compares two Tasks and returns an integer
     * indicating if the first object is less than, equal to or larger than the second object.
     * <p>
     * This function is used to generate the comparator function for sorting by Task Deadline.
     * </p>
     *
     * @param sortOrderEnum The order to sort the task by, represented by a {@code SortOrderEnum}
     * @return {@code Comparator<Task>} object
     */
    public static Comparator<Task> ofDeadline(SortOrderEnum sortOrderEnum) {
        assert (sortOrderEnum != null);
        switch (sortOrderEnum) {
        case ASCENDING:
            return SortUtil::sortDeadlineAscending;
        case DESCENDING:
            return SortUtil::sortDeadlineDescending;
        default:
            throw new IllegalStateException("No such Enum state!");
        }
    }

    /**
     * Returns a {@code Comparator<Task>}, which compares two Tasks and returns an integer
     * indicating if the first object is less than, equal to or larger than the second object.
     * <p>
     * This function is used to generate the comparator function for sorting by Task Status.
     * </p>
     *
     * @param sortOrderEnum The order to sort the task by, represented by a {@code SortOrderEnum}
     * @return {@code Comparator<Task>} object
     */
    public static Comparator<Task> ofStatus(SortOrderEnum sortOrderEnum) {
        assert (sortOrderEnum != null);

        switch (sortOrderEnum) {
        case ASCENDING:
            return SortUtil::sortStatusAscending;
        case DESCENDING:
            return SortUtil::sortStatusDescending;
        default:
            throw new IllegalStateException("No such Enum state!");
        }
    }
}
