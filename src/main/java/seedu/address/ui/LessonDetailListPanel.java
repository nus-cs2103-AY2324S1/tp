package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.lessons.Lesson;


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
        date.setText(lesson.getLessonDate());
        startTime.setText(lesson.getStartTime());
        endTime.setText(lesson.getEndTime());
        students.setText(lesson.getStudents());
        subject.setText(lesson.getSubject());
    }

}
