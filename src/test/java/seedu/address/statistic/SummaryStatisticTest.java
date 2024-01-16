package seedu.address.statistic;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookWithTagScores;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.model.AddressBook;
import seedu.address.model.EventBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Score;
import seedu.address.model.person.ScoreList;
import seedu.address.model.statistics.StatisticMetric;
import seedu.address.model.statistics.SummaryStatistic;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TypicalPersons;


public class SummaryStatisticTest {
    private static final Tag VALID_SCORE_TAG = new Tag("Interview", "assessment");

    private static final Person VALID_LOWEST_SCORE_PERSON = TypicalPersons.ALPHA;
    private static final Person VALID_PERSON_WITH_SCORE = TypicalPersons.BETA;

    private Model model = new ModelManager(getTypicalAddressBookWithTagScores(), new EventBook(), new UserPrefs());

    private Model emptyModel = new ModelManager(new AddressBook(), new EventBook(), new UserPrefs());


    // Check if the mean is calculated correctly
    @Test
    public void execute_calculateMean_success() {
        SummaryStatistic summaryStatistic = new SummaryStatistic(model.getFilteredPersonList());
        int expectedMean = prepareAverageScore();
        int actualMean = summaryStatistic.calculateMean(generateSortedStream(model));
        assertTrue(expectedMean == actualMean);
    }

    // Check if the median is calculated correctly
    @Test
    public void execute_calculateMedian_success() {
        SummaryStatistic summaryStatistic = new SummaryStatistic(model.getFilteredPersonList());
        int expectedMedian = prepareMedianScore();
        int actualMedian = summaryStatistic.calculateMedian(generateSortedStream(model));
        System.out.println(expectedMedian);
        System.out.println(actualMedian);
        assertTrue(expectedMedian == actualMedian);
    }

    // Check if we are able to generate Mean value with tag
    @Test
    public void execute_generateMeanValueWithTag_success() {
        SummaryStatistic summaryStatistic = new SummaryStatistic(model.getFilteredPersonList());
        int expectedMean = prepareAverageScore();
        int actualMean = summaryStatistic.generateMeanWithTag(VALID_SCORE_TAG);
        assertTrue(expectedMean == actualMean);
    }

    // Check if we are able to generate Median value with tag
    @Test
    public void execute_generateMedianValueWithTag_success() {
        SummaryStatistic summaryStatistic = new SummaryStatistic(model.getFilteredPersonList());
        int expectedMedian = prepareMedianScore();
        int actualMedian = summaryStatistic.generateMedianWithTag(VALID_SCORE_TAG);
        assertTrue(expectedMedian == actualMedian);
    }

    // Check if we are able to generate Max value with tag
    @Test
    public void execute_generateMaxValueWithTag_success() {
        SummaryStatistic summaryStatistic = new SummaryStatistic(model.getFilteredPersonList());
        int expectedMax = prepareMaxScore();
        int actualMax = summaryStatistic.generateMaxScoreValueWithTag(VALID_SCORE_TAG);
        assertTrue(expectedMax == actualMax);
    }

    // Check if we are able to generate Min value with tag
    @Test
    public void execute_generateMinValueWithTag_success() {
        SummaryStatistic summaryStatistic = new SummaryStatistic(model.getFilteredPersonList());
        int expectedMin = prepareMinScore();
        int actualMin = summaryStatistic.generateMinScoreValueWithTag(VALID_SCORE_TAG);
        assertTrue(expectedMin == actualMin);
    }

    // Check if we are able to generate percentile value with tag
    @Test
    public void execute_generatePercentileValueWithTag_success() {
        SummaryStatistic summaryStatistic = new SummaryStatistic(model.getFilteredPersonList());
        double expectedPercentile = preparePercentile();
        double actualPercentile = summaryStatistic.generatePercentileWithTag(VALID_PERSON_WITH_SCORE, VALID_SCORE_TAG);
        assertTrue(expectedPercentile == actualPercentile);
    }

    @Test
    public void execute_filteredPersonList_success() {
        SummaryStatistic summaryStatistic = new SummaryStatistic(model.getFilteredPersonList());

        // Test case 1: Lowest scorer being filtered out (Score metric)
        // Lowest score of 50
        int lowestScore = VALID_LOWEST_SCORE_PERSON.getScoreForTag(VALID_SCORE_TAG).value;

        // Filtering a score above the lowest scorer, take note that BETA, the next lowest scorer, has a score of 60
        List<Person> filteredList = summaryStatistic.filteredPersonList(
                VALID_SCORE_TAG, StatisticMetric.SCORE, lowestScore + 1);


        // Checking if the filtered list has one less person than the original list
        assertTrue(filteredList.size() == model.getFilteredPersonList().size() - 1);
        assertTrue(!filteredList.contains(VALID_LOWEST_SCORE_PERSON));

        // Test case 2: All scorers being filtered out (Score metric)
        List<Person> filteredList2 = summaryStatistic.filteredPersonList(
                VALID_SCORE_TAG, StatisticMetric.SCORE, 1000);
        assertTrue(filteredList2.size() == 0);

        // Test case 3: Filtering out less than median scorers (Median metric)
        List<Person> filteredList3 = summaryStatistic.filteredPersonList(
                VALID_SCORE_TAG, StatisticMetric.MEDIAN, 0);
        assertTrue(filteredList3.size() == countOfMoreThanEqualToMedian());

        // Test case 4: Filtering out less than mean scorers (Median mean)
        List<Person> filteredList4 = summaryStatistic.filteredPersonList(
                VALID_SCORE_TAG, StatisticMetric.MEAN, 0);
        assertTrue(filteredList4.size() == countOfMoreThanEqualToMean());

        // Test case 5: Filtering out lowest percentile 0.0 scorers (Percentile metric)
        List<Person> filteredList5 = summaryStatistic.filteredPersonList(
                VALID_SCORE_TAG, StatisticMetric.PERCENTILE, 0);
        assertTrue(filteredList5.size() == model.getFilteredPersonList().size());

        // Test case 6: Filtering out highest percentile 100.0 scorers (Percentile metric)
        List<Person> filteredList6 = summaryStatistic.filteredPersonList(
                VALID_SCORE_TAG, StatisticMetric.PERCENTILE, 100);
        assertTrue(filteredList6.size() == countOfHundredPercentile());

        // Test case 7: Filtering out highest percentile 50.0 scorers (Percentile metric)
        List<Person> filteredList7 = summaryStatistic.filteredPersonList(
                VALID_SCORE_TAG, StatisticMetric.PERCENTILE, 50);
        assertTrue(filteredList7.size() == countOfMoreThanEqualToMedian());
    }

    @Test
    public void execute_filteredPersonsWithTag_success() {
        SummaryStatistic summaryStatistic = new SummaryStatistic(model.getFilteredPersonList());
        // The model contains everyone who contains an "Interview" Tag
        int expectedNumOfPeople = model.getFilteredPersonList().size();
        int actualNumOfPeople = summaryStatistic.getNumOfPeopleAssociatedWithTag(VALID_SCORE_TAG);

        assertTrue(expectedNumOfPeople == actualNumOfPeople);
    }

    @Test
    public void execute_filteredPersonsWithScoreTag_success() {
        SummaryStatistic summaryStatistic = new SummaryStatistic(model.getFilteredPersonList());

        Stream<Person> personWithValidScoreTags = summaryStatistic.filteredPersonsWithScoreTag(VALID_SCORE_TAG);
        assertTrue(personWithValidScoreTags.count() == model.getFilteredPersonList().size());
    }

    // Check for all statistic being 0 for empty list of people
    @Test
    public void execute_emptyList_success() {
        SummaryStatistic summaryStatistic = new SummaryStatistic(emptyModel.getFilteredPersonList());
        int expectedMean = 0;
        int actualMean = summaryStatistic.calculateMean(generateSortedStream(emptyModel));
        assertTrue(expectedMean == actualMean);

        int expectedMedian = 0;
        int actualMedian = summaryStatistic.calculateMedian(generateSortedStream(emptyModel));
        System.out.println(actualMedian);
        assertTrue(expectedMedian == actualMedian);

        int expectedMax = 0;
        int actualMax = summaryStatistic.generateMaxScoreValueWithTag(VALID_SCORE_TAG);
        assertTrue(expectedMax == actualMax);

        int expectedMin = 0;
        int actualMin = summaryStatistic.generateMinScoreValueWithTag(VALID_SCORE_TAG);
        assertTrue(expectedMin == actualMin);

        double expectedPercentile = 0;
        double actualPercentile = summaryStatistic.generatePercentileWithTag(VALID_PERSON_WITH_SCORE, VALID_SCORE_TAG);
        assertTrue(expectedPercentile == actualPercentile);

        List<Person> filteredList = summaryStatistic.filteredPersonList(
                VALID_SCORE_TAG, StatisticMetric.SCORE, 0);
        assertTrue(filteredList.size() == 0);


    }

    // 1 person in list of people
    @Test
    public void execute_onePerson_success() {
        emptyModel.addPerson(VALID_PERSON_WITH_SCORE);
        SummaryStatistic summaryStatistic = new SummaryStatistic(emptyModel.getFilteredPersonList());
        int expectedMean = VALID_PERSON_WITH_SCORE.getScoreForTag(VALID_SCORE_TAG).value;
        int actualMean = summaryStatistic.calculateMean(generateSortedStream(emptyModel));
        assertTrue(expectedMean == actualMean);

        int expectedMedian = VALID_PERSON_WITH_SCORE.getScoreForTag(VALID_SCORE_TAG).value;
        int actualMedian = summaryStatistic.calculateMedian(generateSortedStream(emptyModel));
        assertTrue(expectedMedian == actualMedian);

        int expectedMax = VALID_PERSON_WITH_SCORE.getScoreForTag(VALID_SCORE_TAG).value;
        int actualMax = summaryStatistic.generateMaxScoreValueWithTag(VALID_SCORE_TAG);
        assertTrue(expectedMax == actualMax);

        int expectedMin = VALID_PERSON_WITH_SCORE.getScoreForTag(VALID_SCORE_TAG).value;
        int actualMin = summaryStatistic.generateMinScoreValueWithTag(VALID_SCORE_TAG);
        assertTrue(expectedMin == actualMin);

        double expectedPercentile = 100;
        double actualPercentile = summaryStatistic.generatePercentileWithTag(VALID_PERSON_WITH_SCORE, VALID_SCORE_TAG);
        assertTrue(expectedPercentile == actualPercentile);

    }



    /**
     * Helper method to prepare the average score
     * @return average score
     */
    private int prepareAverageScore() {
        int totalScore = 0;
        int totalPersons = model.getFilteredPersonList().size();
        for (int i = 0; i < totalPersons; i++) {
            totalScore += model.getFilteredPersonList().get(i).getScoreForTag(VALID_SCORE_TAG).value;

        }
        return totalScore / totalPersons;
    }

    /**
     * Helper method to prepare the median score
     * @return median score
     */
    private int prepareMedianScore() {
        int totalPersons = model.getFilteredPersonList().size();
        int[] scores = new int[totalPersons];
        for (int i = 0; i < totalPersons; i++) {
            scores[i] = model.getFilteredPersonList().get(i).getScoreForTag(VALID_SCORE_TAG).value;
        }

        Arrays.sort(scores);
        if (totalPersons % 2 == 0) {
            return (scores[totalPersons / 2 - 1] + scores[totalPersons / 2]) / 2;
        } else {
            return scores[totalPersons / 2];
        }
    }

    /**
     * Helper method to count the number of persons with score more than or equal to the median
     * @return count of persons with score more than or equal to the median
     */
    private int countOfMoreThanEqualToMedian() {
        int totalPersons = model.getFilteredPersonList().size();
        int[] scores = new int[totalPersons];
        for (int i = 0; i < totalPersons; i++) {
            scores[i] = model.getFilteredPersonList().get(i).getScoreForTag(VALID_SCORE_TAG).value;
        }
        int median = scores[totalPersons / 2];
        int count = 0;
        for (int i = 0; i < totalPersons; i++) {
            if (scores[i] >= median) {
                count++;
            }
        }
        return count;
    }

    /**
     * Helper method to count the number of persons with score more than or equal to the mean
     * @return count of persons with score more than or equal to the mean
     */
    private int countOfMoreThanEqualToMean() {
        int totalPersons = model.getFilteredPersonList().size();
        int[] scores = new int[totalPersons];
        for (int i = 0; i < totalPersons; i++) {
            scores[i] = model.getFilteredPersonList().get(i).getScoreForTag(VALID_SCORE_TAG).value;
        }
        int mean = prepareAverageScore();
        int count = 0;
        for (int i = 0; i < totalPersons; i++) {
            if (scores[i] >= mean) {
                count++;
            }
        }
        return count;
    }

    /**
     * Helper method to count the number of persons with score equal to 100.0 percentile
     * @return count of persons with score equal to 100.0 percentile
     */
    private int countOfHundredPercentile() {
        int totalPersons = model.getFilteredPersonList().size();
        int count = 0;
        for (int i = 0; i < totalPersons; i++) {
            int score = model.getFilteredPersonList().get(i).getScoreForTag(VALID_SCORE_TAG).value;
            if (score >= prepareMaxScore()) {
                count++;
            }
        }
        return count;
    }



    /**
     * Helper method to prepare the minimum score
     * @return minimum score
     */
    private int prepareMinScore() {
        int totalPersons = model.getFilteredPersonList().size();
        int[] scores = new int[totalPersons];
        for (int i = 0; i < totalPersons; i++) {
            scores[i] = model.getFilteredPersonList().get(i).getScoreForTag(VALID_SCORE_TAG).value;
        }
        int min = scores[0];
        for (int i = 1; i < totalPersons; i++) {
            if (scores[i] < min) {
                min = scores[i];
            }
        }
        return min;
    }

    /**
     * Helper method to prepare the maximum score
     * @return maximum score
     */
    private int prepareMaxScore() {
        int totalPersons = model.getFilteredPersonList().size();
        int[] scores = new int[totalPersons];
        for (int i = 0; i < totalPersons; i++) {
            scores[i] = model.getFilteredPersonList().get(i).getScoreForTag(VALID_SCORE_TAG).value;
        }
        int max = scores[0];
        for (int i = 1; i < totalPersons; i++) {
            if (scores[i] > max) {
                max = scores[i];
            }
        }
        return max;
    }

    /**
     * Helper method to prepare the percentile
     * @return percentile
     */
    private double preparePercentile() {
        int totalPersons = model.getFilteredPersonList().size();
        int[] scores = new int[totalPersons];
        for (int i = 0; i < totalPersons; i++) {
            scores[i] = model.getFilteredPersonList().get(i).getScoreForTag(VALID_SCORE_TAG).value;
        }
        Arrays.sort(scores);
        int targetScore = VALID_PERSON_WITH_SCORE.getScoreForTag(VALID_SCORE_TAG).value;
        int numberOfScoresLessThanTarget = 0;
        int numberOfSameScore = 0;
        for (int i = 0; i < totalPersons; i++) {
            if (scores[i] < targetScore) {
                numberOfScoresLessThanTarget++;
            }
            if (scores[i] == targetScore) {
                numberOfSameScore++;
            }
        }
        return Math.ceil((double) numberOfScoresLessThanTarget / (totalPersons - numberOfSameScore) * 100);

    }



    /**
     * Helper method to generate a sorted stream of scores
     * @return sorted stream of scores
     */
    private Stream<Integer> generateSortedStream(Model modelUsed) {
        ObservableList<Person> personList = modelUsed.getFilteredPersonList();
        Stream<Person> filteredStream = personList.stream().filter(person -> person.getTags().contains(VALID_SCORE_TAG)
                && person.getScoreList().hasTag(VALID_SCORE_TAG));
        Stream<ScoreList> scoreListStream = filteredStream.map(person -> person.getScoreList());
        Stream<Score> scoreStream = scoreListStream.map(scoreList -> scoreList.getScore(VALID_SCORE_TAG));
        Stream<Integer> scoreValueStream = scoreStream.map(score -> score.value);
        Stream<Integer> sortedScoreValueStream = scoreValueStream.sorted();
        return sortedScoreValueStream;
    }


}
