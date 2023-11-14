package seedu.address.ui;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.util.Pair;
import seedu.address.model.Dashboard;
import seedu.address.model.reminder.Reminder;

/**
 * The Dashboard Display.
 */
public class DashboardDisplay extends UiPart<Region> {
    private static final String FXML = "DashboardDisplay.fxml";

    @FXML
    private Label interactionsPerClient;
    @FXML
    private Label uncontacted;
    @FXML
    private Label contacting;
    @FXML
    private Label closed;
    @FXML
    private PieChart pieChart;
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private ListView<Pair<LocalDate, ObservableList<Reminder>>> dateRemindersListView;

    /**
     * Creates a {@code DashboardDisplay} using data from the given {@code dashboard}.
     */
    public DashboardDisplay(Dashboard dashboard) {
        super(FXML);

        // stats
        interactionsPerClient.setText(String.valueOf(dashboard.averageInteractionsPerClient()));
        uncontacted.setText(String.valueOf(dashboard.getNumUncontactedClients()));
        contacting.setText(String.valueOf(dashboard.getNumContactingClients()));
        closed.setText(String.valueOf(dashboard.getNumClosedClients()));

        // pie chart
        double closed = dashboard.closedPercentage();
        double interested = dashboard.interestedPercentage();
        double notInterested = dashboard.notInterestedPercentage();
        double followUpRequired = dashboard.followUpRequiredPercentage();
        double unknown = dashboard.unknownPercentage();

        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
                new PieChart.Data("Interested", interested), new PieChart.Data("Not Interested", notInterested),
                new PieChart.Data("Follow Up Required", followUpRequired), new PieChart.Data("Closed", closed),
                new PieChart.Data("Unknown", unknown));

        pieChart.setData(data);

        // bar chart
        int totalHotLeads = dashboard.getTotalHotLeads();
        int totalWarmLeads = dashboard.getTotalWarmLeads();
        int totalColdLeads = dashboard.getTotalColdLeads();

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("HOT", totalHotLeads));
        series.getData().add(new XYChart.Data<>("WARM", totalWarmLeads));
        series.getData().add(new XYChart.Data<>("COLD", totalColdLeads));

        barChart.getData().add(series);

        // set axis
        double maxY = Math.ceil(1.25 * Math.max(totalHotLeads, Math.max(totalWarmLeads, totalColdLeads)));

        xAxis.setLabel("Leads");
        yAxis.setLabel("#Clients");
        yAxis.setMinorTickVisible(false);
        yAxis.setTickUnit(1);
        yAxis.setAutoRanging(false);
        yAxis.setUpperBound(maxY);

        dateRemindersListView.setItems(dashboard.getDateReminders());
        dateRemindersListView.setCellFactory(listView -> new DashboardDisplay.ReminderListViewCell());
    }

    static class ReminderListViewCell extends ListCell<Pair<LocalDate, ObservableList<Reminder>>> {
        @Override
        protected void updateItem(Pair<LocalDate, ObservableList<Reminder>> dateReminders, boolean empty) {
            super.updateItem(dateReminders, empty);

            if (empty || dateReminders == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ReminderDateCard(dateReminders.getKey(), dateReminders.getValue()).getRoot());
            }
        }
    }
}
