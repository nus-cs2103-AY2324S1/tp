package seedu.address.ui;

import java.util.Arrays;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.trendresults.TrendCommandResult;
import seedu.address.model.tag.EnrolDate;

/**
 * Controller of a trend page.
 */
public class TrendWindow extends UiPart<Stage> {
    public static final String FXML = "TrendWindow.fxml";
    private static final Logger logger = LogsCenter.getLogger(TrendWindow.class);
    private static final String[] months = new String[]{
        EnrolDate.JAN, EnrolDate.FEB, EnrolDate.MAR, EnrolDate.APR, EnrolDate.MAY,
        EnrolDate.JUN, EnrolDate.JUL, EnrolDate.AUG, EnrolDate.SEP, EnrolDate.OCT, EnrolDate.NOV, EnrolDate.DEC
    };
    private static final int NUMBER_OF_MONTHS = 12;
    private static final String X_AXIS_LABEL = "Month";
    private static final String Y_AXIS_LABEL = "Number of Enrolments";
    private static final String SHOW_MESSAGE = "Showing trend in another window.";

    @FXML
    private LineChart<String, Number> lineChart;

    /**
     * Constructor for creating LineChart instance.
     * @param commandResult TrendCommandResult instance.
     */
    public TrendWindow(CommandResult commandResult) {
        super(FXML, new Stage());

        assert commandResult instanceof TrendCommandResult;

        TrendCommandResult trendCommandResult = (TrendCommandResult) commandResult;
        lineChart = createLineChart(trendCommandResult);
        initialize();
    }

    /**
     * Creates a LineChart with TrendCommandResult instance.
     * @param commandResult TrendCommandResult instance.
     * @return a TrendCommandResult instance.
     */
    public static LineChart<String, Number> createLineChart(TrendCommandResult commandResult) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(months)));
        xAxis.setLabel(X_AXIS_LABEL);

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(Y_AXIS_LABEL);

        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Trend");

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        series.getData().add(new XYChart.Data<>(months[0], commandResult.getJanCount()));
        series.getData().add(new XYChart.Data<>(months[1], commandResult.getFebCount()));
        series.getData().add(new XYChart.Data<>(months[2], commandResult.getMarCount()));
        series.getData().add(new XYChart.Data<>(months[3], commandResult.getAprCount()));
        series.getData().add(new XYChart.Data<>(months[4], commandResult.getMayCount()));
        series.getData().add(new XYChart.Data<>(months[5], commandResult.getJunCount()));
        series.getData().add(new XYChart.Data<>(months[6], commandResult.getJulCount()));
        series.getData().add(new XYChart.Data<>(months[7], commandResult.getAugCount()));
        series.getData().add(new XYChart.Data<>(months[8], commandResult.getSepCount()));
        series.getData().add(new XYChart.Data<>(months[9], commandResult.getOctCount()));
        series.getData().add(new XYChart.Data<>(months[10], commandResult.getNovCount()));
        series.getData().add(new XYChart.Data<>(months[11], commandResult.getDecCount()));

        lineChart.getData().add(series);

        return lineChart;

    }

    /**
     * Sets up the root control, scene and css for the line chart window.
     */
    public void initialize() {
        BorderPane root = new BorderPane();
        root.setCenter(lineChart);

        Scene scene = new Scene(root, 500, 300);
        scene.getStylesheets().add("resources/view/TrendWindow.css");
        super.getRoot().setScene(scene);
    }

    /**
     * Shows the line chart window.
     */
    public void show() {
        logger.fine(SHOW_MESSAGE);
        super.getRoot().show();
        super.getRoot().centerOnScreen();
    }

    /**
     * Checks is the line chart window is showing or not.
     * @return true if the line chart window is currently showing.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Focuses on the line chart window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

}
