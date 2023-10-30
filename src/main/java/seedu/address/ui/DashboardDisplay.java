package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;
import seedu.address.model.Dashboard;

public class DashboardDisplay extends UiPart<Region> {
    private static final String FXML = "DashboardDisplay.fxml";

    @FXML
    PieChart pieChart;

    /**
     * Creates a {@code DashboardDisplay} using data from the given {@code dashboard}.
     */
    public DashboardDisplay(Dashboard dashboard) {
        super(FXML);

        double interested = dashboard.interestedPercentage();
        double notInterested = dashboard.notInterestedPercentage();

        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
                new PieChart.Data("Interested", interested),
                new PieChart.Data("Not Interested", notInterested)
        );

        pieChart.setData(data);
    }
}
