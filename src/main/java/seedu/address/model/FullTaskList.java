package seedu.address.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.lessons.Task;

/**
 * Wraps all tasks
 */
public class FullTaskList implements ReadOnlyFullTaskList {
    private ObservableList<Lesson> lessonList;
    private ObservableList<Task> fullTaskList = FXCollections.observableArrayList();
    private final ObservableList<Task> internalUnmodifiableFullTaskList =
            FXCollections.unmodifiableObservableList(fullTaskList);


    public FullTaskList() {
    }


    public void setFullTaskList(ReadOnlySchedule scheduleList) {
        this.lessonList = scheduleList.getLessonList();
        refreshFullTaskList();
    }

    /**
     * Refreshes the Full Task List.
     */
    public void refreshFullTaskList() {
        fullTaskList.clear();
        for (Lesson lesson : lessonList) {
            ObservableList<Task> tasks = lesson.getTaskList().asUnmodifiableObservableList();
            fullTaskList.addAll(tasks);
        }
    }

    public ObservableList<Task> getFullTaskList() {
        return fullTaskList;
    }


}
