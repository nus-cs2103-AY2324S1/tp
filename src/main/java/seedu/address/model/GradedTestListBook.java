package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.gradedtest.GradedTest;
import seedu.address.model.gradedtest.GradedTestList;

/**
 * Wraps all data at the gradedTest-list level
 * Duplicates are not allowed (by .isSameGradedTest comparison)
 */
public class GradedTestListBook implements ReadOnlyGradedTestList {
    private final GradedTestList gradedTests;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        gradedTests = new GradedTestList();
    }

    public GradedTestListBook() {}

    /**
     * Creates an GradedTestList using the GradedTest in the {@code toBeCopied}
     */
    public GradedTestListBook(ReadOnlyGradedTestList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the GradedTest list with {@code gradedTest}.
     * {@code gradedTests} must not contain duplicate gradedTest.
     */
    public void setGradedTests(List<GradedTest> gradedTests) {
        this.gradedTests.setGradedTests(gradedTests);
    }

    /**
     * Replaces the given gradedTest {@code target} in the list with {@code editedGradedTest}.
     * {@code target} must exist in the gradedTest list.
     * The gradedTest identity of {@code editedGradedTest} must not be the same as another existing
     * gradedTest in the gradedTest list.
     */
    public void setGradedTests(GradedTest target, GradedTest editedGradedTest) {
        requireNonNull(editedGradedTest);
        gradedTests.editGradedTest(target, editedGradedTest);
    }

    /**
     * Resets the existing data of this {@code GradedTestList} with {@code newData}.
     */
    public void resetData(ReadOnlyGradedTestList newData) {
        requireNonNull(newData);

        setGradedTests(newData.getGradedTestList());
    }

    /**
     * Returns the gradedTest at the given index.
     */
    public GradedTest getGradedTest(int index) {
        return gradedTests.getGradedTest(index);
    }

    /**
     * Returns true if a gradedTest with the same identity as {@code gradedTest} exists in the gradedTest list.
     */
    public boolean hasGradedTest(GradedTest gradedTest) {
        requireNonNull(gradedTest);
        return gradedTests.contains(gradedTest);
    }

    /**
     * Adds a gradedTest to the gradedTest list.
     * The gradedTest must not already exist in the gradedTest list.
     */
    public void addGradedTest(GradedTest gt) {
        gradedTests.add(gt);
    }

    /**
     * Removes {@code key} from this {@code GradedTestListBook}.
     * {@code key} must exist in the gradedTest list book.
     */
    public void removeGradedTest(GradedTest key) {
        gradedTests.remove(key);
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("gradedTests", gradedTests)
                .toString();
    }

    @Override
    public ObservableList<GradedTest> getGradedTestList() {
        return gradedTests.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (other == null | getClass() != other.getClass()) {
            return false;
        }

        GradedTestListBook otherGradedTestList = (GradedTestListBook) other;
        return gradedTests.equals(otherGradedTestList.gradedTests);
    }

    @Override
    public int hashCode() {
        return gradedTests.hashCode();
    }
}
