package seedu.address.model;

import static java.util.Objects.requireNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.lessons.Task;

/**
 * Wraps all tasks
 */
public class FullTaskList implements ReadOnlyFullTaskList {
    private ReadOnlySchedule scheduleList;
    private ObservableList<Task> fullTaskList = FXCollections.observableArrayList();
    private final ObservableList<Task> internalUnmodifiableFullTaskList =
            FXCollections.unmodifiableObservableList(fullTaskList);


    public FullTaskList() {
    }

    /**
     * Creates a FullTaskList using the taskList in the {@code toBeCopied}
     */
    public FullTaskList(ReadOnlyFullTaskList toBeCopied) {
        this();
        resetData(toBeCopied);
    }


    /**
     * Resets the existing data of this {@code FullTaskList} with {@code newData}.
     */
    public void resetData(ReadOnlyFullTaskList newData) {
        requireNonNull(newData);

        copyFullTaskList(newData.getFullTaskList());
    }

    public void copyFullTaskList(ObservableList<Task> fullTaskList) {
        this.fullTaskList = fullTaskList;
    }

    public void setFullTaskList(ReadOnlySchedule scheduleList) {
        this.scheduleList = scheduleList;
        ObservableList<Lesson> lessonList = scheduleList.getLessonList();
        for (Lesson lesson : lessonList) {
            ObservableList<Task> tasks = lesson.getTaskList().asUnmodifiableObservableList();
            fullTaskList.addAll(tasks);
        }
    }

    public ObservableList<Task> getFullTaskList() {
        return fullTaskList;
    }


}
