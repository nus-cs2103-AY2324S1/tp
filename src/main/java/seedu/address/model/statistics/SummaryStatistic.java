package seedu.address.model.statistics;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.person.Score;
import seedu.address.model.person.ScoreList;
import seedu.address.model.tag.Tag;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents the summary statistic of the address book.
 * Using terminal operations on Stream like count() consumes the stream and closes it.
 * This can throw an IllegalStateException if you try to use the stream after it is closed.
 */
public class SummaryStatistic implements ReadOnlySummaryStatistic {
    ObservableList<Person> personData;
    private List<Person> personList;
    private static final Logger logger = LogsCenter.getLogger(SummaryStatistic.class);

    /**
     * Initializes a SummaryStatistic with the observable list of given person data.
     * @param persons person data
     */
    public SummaryStatistic(ObservableList<Person> persons) {
        requireAllNonNull(persons);
        personData = persons;
        personList = personData.stream().collect(Collectors.toList());
    }

    /**
     * Updates the person data in the summary statistic.
     * @param persons person data
     */
    public void updatePersonData(ObservableList<Person> persons) {
        personData = persons;
    }

    /**
     * Returns the number of people in the address book.
     * @return number of people in the address book.
     */
    public int getNumOfPeople() {
        return personList.size();
    }

    /**
     * Returns the number of people in the address book associated with that tag.
     * @param tag tag to be associated with
     * @return number of people in the address book associated with that tag.
     */
    public int getNumOfPeopleAssociatedWithTag(Tag tag) {
        Stream<Person> filteredStream = personData.stream().filter(person -> person.getTags().contains(tag));
        return (int) filteredStream.count();
    }

    /**
     * Returns the sorted stream of score value in the address book associated with that tag.
     * @param tag tag to be associated with
     * @return sorted stream of score value
     */
    private Stream<Integer> getSortedScoreValueStream(Tag tag) {
        Stream<Person> filteredStream = personData.stream().filter(person -> person.getTags().contains(tag));
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
        List<Integer> sortedValueList = sortedScoreValueStream.collect(Collectors.toList());
        int numOfPeople = sortedValueList.size();
        int median = 0;
        if (numOfPeople <= 0) {
            logger.warning("No people in the list, median will be left as default of 0");
            return median;
        }
        if (numOfPeople == 1) {
            median = sortedValueList.get(0);
            return median;
        }

        if (numOfPeople % 2 == 0) {
            int medianIndex = numOfPeople / 2;
            median = (sortedValueList.get(medianIndex - 1) + sortedValueList.get(medianIndex)) / 2;
            return median;
        } else {
            int medianIndex = (numOfPeople + 1) / 2;
            median = sortedValueList.get(medianIndex - 1);
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
        List<Integer> sortedValueList = sortedScoreValueStream.collect(Collectors.toList());
        int numOfPeople = sortedValueList.size();
        int mean = 0;
        if (numOfPeople <= 0) {
            logger.warning("No people in the list, mean will be left as default of 0");
            return mean;
        }
        if (numOfPeople == 1) {
            Integer ans = sortedValueList.get(0);
            return ans;
        }
        for (Integer value : sortedValueList) {
            mean += value;
        }
        mean /= numOfPeople;
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
        List<Integer> filteredList = getSortedScoreValueStream(tag).collect(Collectors.toList());
        if (filteredList.size() <= 0) {
            logger.warning("No people in the list, percentile will be left as default of 0");
            return 0;
        } else if (filteredList.size() == 1) {
            return 100;
        }
        Stream<Person> filteredStreamWithScoreValue = personData.stream()
                .filter(personInList -> personInList.getTags().contains(tag))
                .filter(personInList -> personInList.getScoreForTag(tag).compareTo(person.getScoreForTag(tag)) <= 0);
        List<Person> filteredListWithScoreValue = filteredStreamWithScoreValue.collect(Collectors.toList());
        double percentile = 0;
        if (filteredListWithScoreValue.size() <= 0) {
            logger.warning("No people in the list, percentile will be left as default of 0");
            return percentile;
        }
        percentile =  Math.ceil((double) filteredListWithScoreValue.size() / filteredList.size() * 100);
        return percentile;
    }

    /**
     * Generate the Max score of the people associated with that tag.
     * @param tag tag to be associated with
     * @return max score of the people associated with that tag.
     */
    public int generateMaxScoreValueWithTag(Tag tag) {
        Stream<Integer> sortedScoreValueStream = getSortedScoreValueStream(tag);
        List<Integer> sortedList = sortedScoreValueStream.collect(Collectors.toList());
        if (sortedList.size() <= 0) {
            logger.warning("No people in the list, max score will be left as default of 0");
            return 0;
        }
        if (sortedList.size() == 1) {
            return sortedList.get(0);
        }
        int maxScore = sortedList.get(sortedList.size() - 1);
        return maxScore;
    }

    /**
     * Generate the Min score of the people associated with that tag.
     * @param tag tag to be associated with
     * @return min score of the people associated with that tag.
     */
    public int generateMinScoreValueWithTag(Tag tag) {
        Stream<Integer> sortedScoreValueStream = getSortedScoreValueStream(tag);
        List<Integer> sortedList = sortedScoreValueStream.collect(Collectors.toList());
        if (sortedList.size() <= 0) {
            logger.warning("No people in the list, min score will be left as default of 0");
            return 0;
        }
        if (sortedList.size() == 1) {
            return sortedList.get(0);
        }
        int minScore = sortedList.get(0);
        return minScore;
    }


}

