package seedu.address.ui;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 * The UI component that represents a column in the event space background, displaying time intervals.
 */
public class EventSpaceBackgroundColumn extends UiPart<Region> {
    private static final String FXML = "EventSpaceBackgroundColumn.fxml";
    private static final String DIVIDER_LINE_STROKE_COLOUR_STRING = "#86826a";
    private static final String RECTANGLE_STROKE_COLOUR_STRING = "#dbc3ae";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final int NUMBER_OF_DAY_CELLS = 7;
    private static final int RECTANGLE_ARC_VALUE = 5;
    private static final int CELL_HEIGHT = 30;
    private static final int CELL_WIDTH = 50;
    private static final int RECTANGLE_WIDTH = 25;
    private static final int DIVIDER_LINE_LENGTH = 28;
    private static final int DIVIDER_LINE_STROKE_WIDTH = 2;
    private static final double DIVIDER_LINE_OPACITY = 0.5;
    private final LocalTime startTime;

    @FXML
    private VBox columnContainer;

    @FXML
    private Label startTimeLabel;

    /**
     * Constructs an EventSpaceBackgroundColumn with the specified start time.
     *
     * @param startTime The start time represented by this column.
     */
    public EventSpaceBackgroundColumn(LocalTime startTime) {
        super(FXML);
        this.startTime = startTime;

        setLabel();
        setColumnRectangles();
    }

    /**
     * Checks if this column's time interval is within the specified time range.
     *
     * @param start The start time of the range (inclusive).
     * @param end The end time of the range (exclusive).
     * @return True if the column's time interval is within the specified range; otherwise, false.
     */
    public boolean isWithin(LocalTime start, LocalTime end) {
        return (startTime.equals(start) || (startTime.isAfter(start) && startTime.isBefore(end)))
                && !startTime.equals(end);
    }

    /**
     * Sets the label to display the start time formatted as "HH:mm".
     */
    private void setLabel() {
        startTimeLabel.setText(startTime.format(TIME_FORMATTER));
    }

    /**
     * Sets up the rectangles and divider line for each day cell in the column.
     */
    private void setColumnRectangles() {
        Stream.<StackPane>generate(this::constructDayColumnCell).limit(NUMBER_OF_DAY_CELLS)
                .forEachOrdered(cell -> columnContainer.getChildren().add(cell));
    }

    /**
     * Constructs a day column cell with rectangles and a divider line.
     *
     * @return StackPane representing a day column cell.
     */
    private StackPane constructDayColumnCell() {
        StackPane cellContainer = new StackPane();
        VBox.setVgrow(cellContainer, Priority.NEVER);
        HBox.setHgrow(cellContainer, Priority.NEVER);
        cellContainer.setPrefHeight(CELL_HEIGHT);
        cellContainer.setPrefWidth(CELL_WIDTH);
        HBox rectangleContainer = new HBox();
        Rectangle leftRectangle = new Rectangle(RECTANGLE_WIDTH, CELL_HEIGHT);
        Rectangle rightRectangle = new Rectangle(RECTANGLE_WIDTH, CELL_HEIGHT);
        Line dividerLine = new Line();

        leftRectangle.setFill(Color.ANTIQUEWHITE);
        rightRectangle.setFill(Color.BISQUE);
        leftRectangle.setArcHeight(RECTANGLE_ARC_VALUE);
        leftRectangle.setArcWidth(RECTANGLE_ARC_VALUE);
        rightRectangle.setArcHeight(RECTANGLE_ARC_VALUE);
        rightRectangle.setArcWidth(RECTANGLE_ARC_VALUE);
        leftRectangle.setStroke(Paint.valueOf(RECTANGLE_STROKE_COLOUR_STRING));
        leftRectangle.setStrokeType(StrokeType.INSIDE);
        rightRectangle.setStroke(Paint.valueOf(RECTANGLE_STROKE_COLOUR_STRING));
        rightRectangle.setStrokeType(StrokeType.INSIDE);

        dividerLine.setStroke(Paint.valueOf(DIVIDER_LINE_STROKE_COLOUR_STRING));
        dividerLine.setEndY(DIVIDER_LINE_LENGTH);
        dividerLine.setStrokeWidth(DIVIDER_LINE_STROKE_WIDTH);
        dividerLine.setOpacity(DIVIDER_LINE_OPACITY);
        dividerLine.setFill(Color.GAINSBORO);
        StackPane.setAlignment(dividerLine, Pos.CENTER);

        rectangleContainer.getChildren().addAll(leftRectangle, rightRectangle);
        cellContainer.getChildren().addAll(rectangleContainer, dividerLine);
        return cellContainer;
    }
}
