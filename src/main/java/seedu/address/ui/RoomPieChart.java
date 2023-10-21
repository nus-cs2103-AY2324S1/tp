package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.booking.Booking;

/**
 * Create a pie chart for available and unavailable rooms.
 */
public class RoomPieChart extends UiPart<Region> {

    private static final String FXML = "RoomPieChart.fxml";
    private final Logger logger = LogsCenter.getLogger(RoomPieChart.class);

    @FXML
    private StackPane roomPieChartPane;

    /**
     * Constructor for room pie chart.
     * @param bookingList the list of bookings
     */
    public RoomPieChart(ObservableList<Booking> bookingList) {
        super(FXML);

        int numOfOccupiedRooms = 0;
        for (Booking booking : bookingList) {
            numOfOccupiedRooms++;
        }

        int occupiedRooms = numOfOccupiedRooms;
        int availableRooms = 500 - occupiedRooms;
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Occupied Rooms", occupiedRooms),
                        new PieChart.Data("Available Rooms", availableRooms));
        final PieChart roomPieChart = new PieChart(pieChartData);
        roomPieChartPane.getChildren().add(roomPieChart);
    }
}
