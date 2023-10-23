package transact.ui;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import se.alipsa.ymp.YearMonthPickerCombo;
import transact.model.transaction.Transaction;

public class OverviewPanel extends UiPart<Region> {

    private static final String FXML = "Overview.fxml";

    @FXML
    private StackPane pickerContainer;

    @FXML
    private Label revenueLabel;

    @FXML
    private Label expensesLabel;

    @FXML
    private LineChart<String, Number> profitGraph;

    @FXML
    private PieChart breakdownPieChart;

    private ObservableList<Transaction> transactionList;

    private Map<YearMonth, MonthData> monthDataMap = new TreeMap<>();

    public OverviewPanel(ObservableList<Transaction> transactionList) {
        super(FXML);

        this.transactionList = transactionList;

        // Init view
        updateMonthData();
        updateGraph();
        changeMonthView(YearMonth.now());

        this.transactionList.addListener((Change<? extends Transaction> change) -> {
            updateMonthData();
            updateGraph();
        });

        YearMonthPickerCombo ymp = new YearMonthPickerCombo(
                YearMonth.now().minusYears(1),
                YearMonth.now(),
                YearMonth.now(),
                Locale.ENGLISH,
                "MMM yyyy");

        ymp.setOnAction(a -> {
            changeMonthView(ymp.getValue());
        });

        pickerContainer.getChildren().add(ymp);
    }

    private void changeMonthView(YearMonth month) {
        MonthData selectedMonthData = monthDataMap.get(month);
        if (selectedMonthData == null) {
            revenueLabel.setText(String.format("$%,.2f", 0.0));
            expensesLabel.setText(String.format("$%,.2f", 0.0));
        } else {
            revenueLabel.setText(String.format("$%,.2f", selectedMonthData.getRevenue()));
            expensesLabel.setText(String.format("$%,.2f", selectedMonthData.getExpense()));
        }
    }

    private void updateMonthData() {
        // TODO Implement this method
        transactionList.stream().forEach((Transaction t) -> {
            // TODO Do Calculations to populate monthDataMap
        });
        // Mock data
        monthDataMap.put(YearMonth.of(2023, 1), new MonthData(1000, 1000));
        monthDataMap.put(YearMonth.of(2023, 2), new MonthData(2000, 2000));
        monthDataMap.put(YearMonth.of(2023, 3), new MonthData(3000, 3000));
        monthDataMap.put(YearMonth.of(2023, 4), new MonthData(4000, 4000));
        monthDataMap.put(YearMonth.of(2023, 5), new MonthData(5000, 5000));

        // This month
        monthDataMap.put(YearMonth.of(2023, 10), new MonthData(1234, 1234));
    }

    private CategoryAxis generateMonthAxis() {
        // TODO Implement this method
        /*
         * Axis should show 12 months, ending at the month of the latest transaction
         */
        ObservableList<String> months = FXCollections.observableArrayList();
        months.addAll("Jan", "Feb", "Mar");
        CategoryAxis xAxis = new CategoryAxis(months);
        xAxis.setLabel("Month");
        return xAxis;
    }

    private ArrayList<Data<String, Number>> getProfitGraphData() {
        // TODO Do calculations
        ArrayList<Data<String, Number>> data = new ArrayList<>();
        data.add(new Data<String, Number>("Jan", 100));
        return data;
    }

    private void updateGraph() {
        final NumberAxis yAxis = new NumberAxis();

        yAxis.setLabel("Profit/$");

        profitGraph = new LineChart<String, Number>(generateMonthAxis(), yAxis);

        profitGraph.setTitle("Profit Graph");

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        series.getData().addAll(getProfitGraphData());

        profitGraph.getData().clear();
        profitGraph.getData().add(series);
    }

    class MonthData {
        private double revenue;
        private double expense;

        public MonthData(double revenue, double expense) {
            this.revenue = revenue;
            this.expense = expense;
        }

        public double getProfit() {
            return revenue - expense;
        }

        public double getRevenue() {
            return revenue;
        }

        public double getExpense() {
            return expense;
        }
    }
}
