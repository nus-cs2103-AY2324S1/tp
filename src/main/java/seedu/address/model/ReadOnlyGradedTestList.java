package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.gradedtest.GradedTest;

/**
 * Unmodified view of a gradedTest List
 */
public interface ReadOnlyGradedTestList {
    /**
     * Returns an unmodifiable view of the task list.
     * This list will not contain any duplicate tasks.
     */
    ObservableList<GradedTest> getGradedTestList();
}
