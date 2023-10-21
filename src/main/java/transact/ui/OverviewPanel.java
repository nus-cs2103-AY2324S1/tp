package transact.ui;

import java.time.YearMonth;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import se.alipsa.ymp.YearMonthPickerCombo;

public class OverviewPanel extends UiPart<Region> {

    private static final String FXML = "Overview.fxml";

    @FXML
    private StackPane pickerContainer;

    @FXML
    private Label revenueLabel;

    @FXML
    private Label expensesLabel;

    @FXML
    private LineChart<YearMonth, Double> profitGraph;

    @FXML
    private PieChart breakdownPieChart;

    public OverviewPanel() {
        super(FXML);

        YearMonthPickerCombo ymp = new YearMonthPickerCombo(
                YearMonth.now().minusYears(1),
                YearMonth.now(),
                YearMonth.now(),
                Locale.ENGLISH,
                "MMM yyyy");

        ymp.setOnAction(a -> System.out.println("Default YearMonthPicker, Value picked was " + ymp.getValue()));
        pickerContainer.getChildren().add(ymp);

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Grapefruit", 13),
                new PieChart.Data("Oranges", 25),
                new PieChart.Data("Plums", 10),
                new PieChart.Data("Pears", 22),
                new PieChart.Data("Apples", 30));

        breakdownPieChart.setData(pieChartData);
    }
}
