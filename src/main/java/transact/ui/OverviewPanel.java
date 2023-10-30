package transact.ui;

import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.TreeMap;

import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import se.alipsa.ymp.YearMonthPickerCombo;
import transact.model.transaction.Transaction;
import transact.model.transaction.info.TransactionType;

/**
 * Overview Panel
 */
public class OverviewPanel extends UiPart<Region> {

    private static final String FXML = "Overview.fxml";

    private static final DateTimeFormatter yearMonthFormat = DateTimeFormatter.ofPattern("MMM yy");

    @FXML
    private StackPane pickerContainer;

    @FXML
    private Label revenueLabel;

    @FXML
    private Label expensesLabel;

    @FXML
    private VBox graphContainer;

    private LineChart<String, Number> profitGraph;

    private ObservableList<Transaction> transactionList;

    private TreeMap<YearMonth, MonthData> monthDataMap = new TreeMap<>();

    /**
     * Creates a {@code OverviewPanel} with the given
     * {@code transactionList}.
     *
     * @param transactionList
     */
    public OverviewPanel(ObservableList<Transaction> transactionList) {
        super(FXML);

        this.transactionList = transactionList;

        // Init UI elements
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setAutoRanging(true);
        yAxis.setForceZeroInRange(false);

        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.setAutoRanging(true);
        xAxis.setLabel("Date");

        profitGraph = new LineChart<String, Number>(xAxis, yAxis);
        profitGraph.setTitle("Profit Graph");
        profitGraph.setLegendVisible(false);

        graphContainer.getChildren().add(profitGraph);

        updateMonthData();
        updateGraph();
        changeMonthView(YearMonth.now());

        this.transactionList.addListener((Change<? extends Transaction> change) -> {
            updateMonthData();
            updateGraph();
        });

        YearMonthPickerCombo ymp = new YearMonthPickerCombo(
                monthDataMap.firstKey(),
                monthDataMap.lastKey(),
                monthDataMap.lastKey(),
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

    /**
     * Updates the {@code monthDataMap} with information from the
     * {@code transactionList}.
     */
    private void updateMonthData() {
        transactionList.stream().forEach((Transaction t) -> {
            YearMonth yearMonth = YearMonth.from(t.getDate().getDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate());
            if (monthDataMap.get(yearMonth) == null) {
                monthDataMap.put(yearMonth, new MonthData(0, 0));
            }
            if (t.getTransactionType() == TransactionType.EXPENSE) {
                // Increase Expense
                monthDataMap.get(yearMonth).increaseExpense(t.getAmount().getValue().doubleValue());
            } else {
                // Increase Revenue
                monthDataMap.get(YearMonth.from(t.getDate().getDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate())).increaseRevenue(t.getAmount().getValue().doubleValue());
            }
        });
    }

    /**
     * Creates an ArrayList of monthly profit data for each month in the year,
     * starting from the first available data to the last
     *
     * @return An ArrayList of Data objects, each representing monthly profit data.
     */
    private ArrayList<Data<String, Number>> getProfitGraphData() {
        ArrayList<Data<String, Number>> data = new ArrayList<>();

        YearMonth startMonth = monthDataMap.firstKey();
        YearMonth lastMonth = monthDataMap.lastKey().plusMonths(1);

        while (startMonth.isBefore(lastMonth)) {
            String displayDate = startMonth.format(yearMonthFormat);
            MonthData monthData = monthDataMap.get(startMonth);
            XYChart.Data<String, Number> d = new XYChart.Data<>(displayDate,
                    monthData == null ? 0 : monthData.getProfit());

            data.add(d);

            startMonth = startMonth.plusMonths(1);
        }
        return data;
    }

    private void updateGraph() {
        profitGraph.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        series.getData().addAll(getProfitGraphData());

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

        /**
         * Increases each month's revenue by a certain amount.
         *
         * @param revenue
         */
        public void increaseRevenue(double revenue) {
            this.revenue += revenue;
        }

        /**
         * Increases each month's expense by a certain amount.
         *
         * @param expense
         */
        public void increaseExpense(double expense) {
            this.expense += expense;
        }
    }
}
