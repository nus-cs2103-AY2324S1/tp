package seedu.address.ui;

import static seedu.address.model.booking.Room.NUMBER_OF_DELUXE_ROOMS;
import static seedu.address.model.booking.Room.NUMBER_OF_NORMAL_ROOMS;
import static seedu.address.model.booking.Room.NUMBER_OF_PRESIDENTIAL_SUITES;
import static seedu.address.model.booking.Room.NUMBER_OF_STUDIO_ROOMS;
import static seedu.address.model.booking.Room.NUMBER_OF_SUITES;

import java.util.HashSet;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.Room;

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
        HashSet<Integer> roomNumbersInPieChart = new HashSet<>();
        int numOfOccupiedRooms = 0;
        int availableNormalRooms = NUMBER_OF_NORMAL_ROOMS;
        int availableStudioRooms = NUMBER_OF_STUDIO_ROOMS;
        int availableDeluxeRooms = NUMBER_OF_DELUXE_ROOMS;
        int availableSuites = NUMBER_OF_SUITES;
        int availablePresidentialSuites = NUMBER_OF_PRESIDENTIAL_SUITES;
        for (Booking booking : bookingList) {
            int roomNumber = booking.getRoom().getRoomNumber();
            if (roomNumbersInPieChart.contains(roomNumber)) {
                continue;
            }
            roomNumbersInPieChart.add(roomNumber);
            numOfOccupiedRooms++;
            Room.RoomType roomType = booking.getRoom().getType();
            switch (roomType) {
            case NORMAL:
                availableNormalRooms--;
                break;
            case STUDIO:
                availableStudioRooms--;
                break;
            case DELUXE:
                availableDeluxeRooms--;
                break;
            case SUITES:
                availableSuites--;
                break;
            default:
                availablePresidentialSuites--;
                break;
            }
        }

        int occupiedRooms = numOfOccupiedRooms;
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Occupied Rooms: " + occupiedRooms, occupiedRooms),
                        new PieChart.Data("Normal Rooms: " + availableNormalRooms,
                                availableNormalRooms),
                        new PieChart.Data("Studio Rooms: " + availableStudioRooms,
                                availableStudioRooms),
                        new PieChart.Data("Deluxe Rooms: " + availableDeluxeRooms,
                                availableDeluxeRooms),
                        new PieChart.Data("Suites: " + availableSuites,
                                availableSuites),
                        new PieChart.Data("Presidential Suites: " + availablePresidentialSuites,
                                availablePresidentialSuites));
        final PieChart roomPieChart = new DoughnutChart(pieChartData);
        roomPieChart.setTitle("Room Statistics");
        roomPieChart.setLabelsVisible(false);
        roomPieChartPane.getChildren().add(roomPieChart);
    }
}
