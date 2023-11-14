package seedu.address.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import seedu.address.MainApp;
import seedu.address.model.person.Gpa;
import seedu.address.model.person.InterviewScore;
import seedu.address.model.person.Person;
import seedu.address.model.person.PreviousGrade;

/**
 * The Compare Pop-up Window. Provides a separate JavaFX stage when a "compare" command is inputted.
 * This window is used to display and compare the details of two applicants,
 * including student number, name, and their relevant achievements.
 */
public class CompareWindow extends Stage {
    @FXML
    private Label person1StuNum;
    @FXML
    private Label person1Name;
    @FXML
    private Label person1Gpa;
    @FXML
    private Rectangle gpa1Highlight;
    @FXML
    private Label person1IScore;
    @FXML
    private Rectangle iScore1Highlight;
    @FXML
    private Label person1MGrade;
    @FXML
    private Rectangle mGrade1Highlight;
    @FXML
    private Label person1Comment;
    @FXML
    private Label person2StuNum;
    @FXML
    private Label person2Name;
    @FXML
    private Label person2Gpa;
    @FXML
    private Rectangle gpa2Highlight;
    @FXML
    private Label person2IScore;
    @FXML
    private Rectangle iScore2Highlight;
    @FXML
    private Label person2MGrade;
    @FXML
    private Rectangle mGrade2Highlight;
    @FXML
    private Label person2Comment;

    /**
     * Creates a new instance of the CompareWindow with information about two persons to be compared.
     *
     * @param person1 The first person for comparison.
     * @param person2 The second person for comparison.
     */
    public CompareWindow(Person person1, Person person2) {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/view/CompareWindow.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setTitle("Comparison Result");

        //person1 stuff
        Gpa gpa1 = person1.getGpa();
        PreviousGrade mGrade1 = person1.getPreviousGrade();
        InterviewScore interviewScore1 = null;
        String interviewScoreString1;

        if (person1.getInterviewScore().isPresent()) {
            interviewScore1 = person1.getInterviewScore().get();
            interviewScoreString1 = interviewScore1.toString();
        } else {
            interviewScoreString1 = "-";
        }

        String comment1;
        if (person1.getComment().isPresent()) {
            String commentString1 = person1.getComment().toString();
            comment1 = commentString1.substring("Optional[".length(),
                    commentString1.length() - 1);
        } else {
            comment1 = "-";
        }

        //person2 stuff
        Gpa gpa2 = person2.getGpa();
        PreviousGrade mGrade2 = person2.getPreviousGrade();
        InterviewScore interviewScore2 = null;
        String interviewScoreString2;

        if (person2.getInterviewScore().isPresent()) {
            interviewScore2 = person2.getInterviewScore().get();
            interviewScoreString2 = interviewScore2.toString();
        } else {
            interviewScoreString2 = "-";
        }

        String comment2;
        if (person2.getComment().isPresent()) {
            String commentString2 = person2.getComment().toString();
            comment2 = commentString2.substring("Optional[".length(),
                    commentString2.length() - 1);
        } else {
            comment2 = "-";
        }

        person1StuNum.setText(person1.getStudentNumber().toString());
        person1Name.setText(person1.getName().toString());
        person1Gpa.setText(gpa1.toString());
        person1IScore.setText(interviewScoreString1);
        person1MGrade.setText(mGrade1.toString());
        person1Comment.setText(comment1);

        person2StuNum.setText(person2.getStudentNumber().toString());
        person2Name.setText(person2.getName().toString());
        person2Gpa.setText(gpa2.toString());
        person2IScore.setText(interviewScoreString2);
        person2MGrade.setText(mGrade2.toString());
        person2Comment.setText(comment2);

        if (gpa1.compareTo(gpa2) > 0) {
            gpa1Highlight.setOpacity(0.33);
        } else if (gpa1.compareTo(gpa2) < 0) {
            gpa2Highlight.setOpacity(0.33);
        }

        if (interviewScoreString1 != "-" && interviewScoreString2 != "-") {
            if (interviewScore1.compareTo(interviewScore2) > 0) {
                iScore1Highlight.setOpacity(0.33);
            } else if (interviewScore1.compareTo(interviewScore2) < 0) {
                iScore2Highlight.setOpacity(0.33);
            }
        }

        if (mGrade1.compareTo(mGrade2) < 0) {
            mGrade1Highlight.setOpacity(0.33);
        } else if (mGrade1.compareTo(mGrade2) > 0) {
            mGrade2Highlight.setOpacity(0.33);
        }

        Scene scene = new Scene(fxmlLoader.getRoot());
        setWidth(900);
        setHeight(600);
        setScene(scene);
    }
}
