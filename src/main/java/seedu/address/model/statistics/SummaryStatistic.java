package seedu.address.model.statistics;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.person.Score;
import seedu.address.model.person.ScoreList;
import seedu.address.model.tag.Tag;

import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * Represents the summary statistic of the address book.
 */
public class SummaryStatistic implements ReadOnlySummaryStatistic {
    private Stream<Person> personData;
    private static final Logger logger = LogsCenter.getLogger(SummaryStatistic.class);

    /**
     * Initializes a SummaryStatistic with the observable list of given person data.
     * @param persons person data
     */
    public SummaryStatistic(ObservableList<Person> persons) {
        requireAllNonNull(persons);
        personData = persons.stream();
    }

    /**
     * Updates the person data in the summary statistic.
     * @param persons person data
     */
    public void updatePersonData(ObservableList<Person> persons) {
        personData = persons.stream();
    }

    /**
     * Returns the number of people in the address book.
     * @return number of people in the address book.
     */
    public int getNumOfPeople() {
        return (int) personData.count();
    }

    /**
     * Returns the number of people in the address book associated with that tag.
     * @param tag tag to be associated with
     * @return number of people in the address book associated with that tag.
     */
    public int getNumOfPeopleAssociatedWithTag(Tag tag) {
        Stream<Person> filteredStream = personData.filter(person -> person.getTags().contains(tag));
        return (int) filteredStream.count();
    }

    /**
     * Returns the sorted stream of score value in the address book associated with that tag.
     * @param tag tag to be associated with
     * @return sorted stream of score value
     */
    private Stream<Integer> getSortedScoreValueStream(Tag tag) {
        Stream<Person> filteredStream = personData.filter(person -> person.getTags().contains(tag));
        Stream<ScoreList> scoreListStream = filteredStream.map(person -> person.getScoreList());
        Stream<Score> scoreStream = scoreListStream.map(scoreList -> scoreList.getScore(tag));
        Stream<Integer> scoreValueStream = scoreStream.map(score -> score.value);
        Stream<Integer> sortedScoreValueStream = scoreValueStream.sorted();
        return sortedScoreValueStream;
    }

    /**
     * Calculate the median of the score value stream.
     * @param sortedScoreValueStream sorted score value stream
     * @return median of the score value stream
     */
    public int calculateMedian(Stream<Integer> sortedScoreValueStream) {
        int numOfPeople = (int) sortedScoreValueStream.count();
        int median = 0;
        if (numOfPeople <= 0) {
            logger.warning("No people in the list, median will be left as default of 0");
            return median;
        }

        if (numOfPeople % 2 == 0) {
            int medianIndex = numOfPeople / 2;
            median = sortedScoreValueStream
                    .skip(medianIndex - 1)
                    .limit(2)
                    .reduce((a, b) -> a + b).get() / 2;
            return median;
        } else {
            int medianIndex = (numOfPeople + 1) / 2;
            median = sortedScoreValueStream.skip(medianIndex - 1).findFirst().orElse(0);
            return median;
        }
    }

    /**
     * Generate the Median of the score of the people associated with that tag.
     * @param tag tag to be associated with
     * @return median of the score of the people associated with that tag.
     */
    public int generateMedianWithTag(Tag tag) {
        Stream<Integer> sortedScoreValueStream = getSortedScoreValueStream(tag);
        int median = calculateMedian(sortedScoreValueStream);
        return median;
    }

    /**
     * Calculate the mean of the score value stream.
     * @param sortedScoreValueStream sorted score value stream
     * @return mean of the score value stream
     */
    public int calculateMean(Stream<Integer> sortedScoreValueStream) {
        int numOfPeople = (int) sortedScoreValueStream.count();
        int mean = 0;
        if (numOfPeople <= 0) {
            logger.warning("No people in the list, mean will be left as default of 0");
            return mean;
        }
        mean = sortedScoreValueStream.reduce((a, b) -> a + b).get() / numOfPeople;
        return mean;
    }

    /**
     * Generate the Mean of the score of the people associated with that tag.
     * @param tag tag to be associated with
     * @return mean of the score of the people associated with that tag.
     */
    public int generateMeanWithTag(Tag tag) {
        Stream<Integer> sortedScoreValueStream = getSortedScoreValueStream(tag);
        int mean = calculateMean(sortedScoreValueStream);
        return mean;
    }

    /**
     * Generate the percentile of the score of the person associated with that tag.
     * @param person person to be associated with
     * @param tag tag to be associated with
     * @return percentile of the score of the person associated with that tag.
     */
    public double generatePercentileWithTag(Person person, Tag tag) {
        Stream<Person> filteredStreamWithTags = personData.filter(personInList -> personInList.getTags().contains(tag));
        Stream<Person> filteredStreamWithScoreValue = filteredStreamWithTags
                .filter(personInList -> personInList.getScore().value <= person.getScore().value);
        int numOfPeopleBefore = (int) filteredStreamWithScoreValue.count();
        double percentile = 0;
        if (numOfPeopleBefore <= 0) {
            logger.warning("No people in the list, percentile will be left as default of 0");
            return percentile;
        }
        percentile =  Math.ceil((double) numOfPeopleBefore / getNumOfPeople() * 100);
        return percentile;
    }

    /**
     * Generate the Max score of the people associated with that tag.
     * @param tag tag to be associated with
     * @return max score of the people associated with that tag.
     */
    public int generateMaxScoreValueWithTag(Tag tag) {
        Stream<Integer> sortedScoreValueStream = getSortedScoreValueStream(tag);
        int maxScore = sortedScoreValueStream.reduce((a, b) -> b).orElse(0);
        return maxScore;
    }

    /**
     * Generate the Min score of the people associated with that tag.
     * @param tag tag to be associated with
     * @return min score of the people associated with that tag.
     */
    public int generateMinScoreValueWithTag(Tag tag) {
        Stream<Integer> sortedScoreValueStream = getSortedScoreValueStream(tag);
        int minScore = sortedScoreValueStream.reduce((a, b) -> a).orElse(0);
        return minScore;
    }

}

