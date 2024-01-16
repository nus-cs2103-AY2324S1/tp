package seedu.address.ui;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.statistics.ReadOnlySummaryStatistic;
import seedu.address.model.tag.Tag;

/**
 * Controller for a Summary Statistic page
 */
public class SummaryStatisticScreen extends UiPart<Region> {
    private static final String FXML = "SummaryStatisticScreen.fxml";
    private final ReadOnlySummaryStatistic summaryStatistic;
    private final Person person;

    @FXML
    private TableView<StatisticData> tableView;

    /**
     * Constructor for summary statistic screen.
     * @param summaryStatistic summary statistic
     * @param person person
     */
    public SummaryStatisticScreen(ReadOnlySummaryStatistic summaryStatistic, Person person) {
        super(FXML);
        this.summaryStatistic = summaryStatistic;
        this.person = person;
        updateTableView();
    }

    /**
     * Updates the table view with the statistic data.
     */
    public void updateTableView() {
        ObservableList<StatisticData> statisticData = FXCollections.observableArrayList();
        statisticData = fillStatisticDataForPerson(statisticData);
        tableView.setItems(statisticData);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    /**
     * Fills the statistic data for the person.
     * @return statistic data
     */
    public ObservableList<StatisticData> fillStatisticDataForPerson(ObservableList<StatisticData> statisticData) {


        List<Tag> tags = person.getScoreList().getTagsWithScore();

        for (Tag tag : tags) {
            int mean = summaryStatistic.generateMeanWithTag(tag);
            int median = summaryStatistic.generateMedianWithTag(tag);
            int min = summaryStatistic.generateMinScoreValueWithTag(tag);
            int max = summaryStatistic.generateMaxScoreValueWithTag(tag);
            double percentile = summaryStatistic.generatePercentileWithTag(person, tag);
            int currentScore = person.getScoreList().getScore(tag).value;

            statisticData.add(new StatisticData(tag.tagName, mean, median, min, max, percentile, currentScore));

        }
        return statisticData;
    }


    /**
     * Represents the statistic data.
     */
    public static class StatisticData {
        private final String tag;
        private final int mean;
        private final int median;
        private final int max;
        private final int min;
        private final double percentile;

        private final int currentScore;

        /**
         * Constructor for statistic data.
         * @param tag tag
         * @param mean mean
         * @param median median
         * @param max max
         * @param min min
         * @param percentile percentile
         */
        public StatisticData(String tag, int mean, int median, int min, int max, double percentile, int currentScore) {
            this.tag = tag;
            this.mean = mean;
            this.median = median;
            this.min = min;
            this.max = max;
            this.percentile = percentile;
            this.currentScore = currentScore;
        }
        public String getTag() {
            return tag;
        }

        public int getMean() {
            return mean;
        }

        public int getMedian() {
            return median;
        }

        public int getMin() {
            return min;
        }

        public int getMax() {
            return max;
        }

        public double getPercentile() {
            return percentile;
        }

        public int getCurrentScore() {
            return currentScore;
        }
    }
}
