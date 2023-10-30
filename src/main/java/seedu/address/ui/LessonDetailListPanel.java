package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.lessons.Task;
import seedu.address.model.lessons.TaskList;


/**
 * Panel containing a student's details.
 */
public class LessonDetailListPanel extends UiPart<Region> {
    private static final String FXML = "LessonDetailListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(LessonDetailListPanel.class);

    private Logic logic;

    @FXML
    private TextField date;

    @FXML
    private TextField startTime;

    @FXML
    private TextField endTime;

    @FXML
    private TextField students;

    @FXML
    private TextField subject;

    @FXML
    private ListView<Task> taskListView;
    @FXML
    private VBox taskListContainer;


    /**
     * Creates a {@code LessonDetailListPanel} with the given {@code ObservableList}.
     */
    public LessonDetailListPanel(Logic logic) {
        super(FXML);
        this.logic = logic;
    }


    /**
     * Sets the Details of the lesson to be shown.
     *
     * @param lesson The lesson whose details are to be shown.
     */
    public void setLessonDetails(Lesson lesson) {
        date.setText(lesson.getLessonDateStr());
        startTime.setText(lesson.getStart().toString());
        endTime.setText(lesson.getEnd().toString());
        students.setText(lesson.getStudentsStr());
        subject.setText(lesson.getSubject().toString());
        //taskListView.setItems("to be implemented");
        taskListView.setCellFactory(listView -> new LessonDetailListPanel.TaskListViewCell());
        subject.setText(lesson.getSubject().toString());

        taskListContainer.getChildren().clear();
        // temporary code, to show ui only

        ObservableList<Task> taskList = lesson.getTaskList().asUnmodifiableObservableList();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            taskListContainer.getChildren().add(new TaskCard(task, i + 1).getRoot());
        }
    }

    class TaskListViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TaskCard(task, getIndex() + 1).getRoot());
            }
        }
    }

}
