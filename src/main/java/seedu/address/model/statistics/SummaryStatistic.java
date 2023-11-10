package seedu.address.model.statistics;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.person.Score;
import seedu.address.model.person.ScoreList;
import seedu.address.model.tag.Tag;

/**
 * Represents the summary statistic of the address book.
 * Using terminal operations on Stream like count() consumes the stream and closes it.
 * This can throw an IllegalStateException if you try to use the stream after it is closed.
 */
public class SummaryStatistic implements ReadOnlySummaryStatistic {
    private static final Logger logger = LogsCenter.getLogger(SummaryStatistic.class);
    private ObservableList<Person> personData;

    /**
     * Initializes a SummaryStatistic with the observable list of given person data.
     * @param persons person data
     */
    public SummaryStatistic(ObservableList<Person> persons) {
        requireAllNonNull(persons);
        personData = persons;
    }

    /**
     * Updates the person data in the summary statistic.
     * @param persons person data
     */
    public void updatePersonData(ObservableList<Person> persons) {
        personData = persons;
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
        Stream<Person> filteredStream = filteredPersonsWithScoreTag(tag);
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
        Stream<Person> filteredStreamWithScoreValue = filteredPersonsWithScoreTag(tag)
                .filter(personInList -> personInList.getScoreForTag(tag).compareTo(person.getScoreForTag(tag)) < 0);
        List<Person> filteredListWithScoreValue = filteredStreamWithScoreValue.collect(Collectors.toList());
        double percentile = 0;

        int sameScoreIndividuals = filteredPersonsWithScoreTag(tag)
                .filter(personInList -> personInList.getScoreForTag(tag).compareTo(person.getScoreForTag(tag)) == 0)
                .collect(Collectors.toList()).size();
        int trueListSize = filteredList.size() - sameScoreIndividuals;
        if (filteredListWithScoreValue.size() <= 0) {
            logger.warning("No people in the list, percentile will be left as default of 0");
            return percentile;
        }
        percentile = Math.ceil((double) filteredListWithScoreValue.size() / trueListSize * 100);
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

    /**
     * Returns the list of people in the address book associated with that tag and has a value greater than
     * or equal to the metric and value provided by the user.
     * @param tag tag to be associated with
     * @param metric metric to be associated with
     * @param value value to be associated with
     * @return
     */
    public List<Person> filteredPersonList(Tag tag, StatisticMetric metric, int value) {
        switch (metric) {
        case SCORE:
            Stream<Integer> scoreValueStream = getSortedScoreValueStream(tag);
            Stream<Person> filteredPersonStream = scoreValueStream.filter(scoreValue -> scoreValue >= value)
                    .flatMap(scoreValue -> personData.stream()
                    .filter(person -> person.getTags().contains(tag)
                            && person.getScoreList().hasTag(tag))
                    .filter(person -> person.getScoreForTag(tag).value >= scoreValue)).distinct();
            List<Person> filteredPersonList = filteredPersonStream.collect(Collectors.toList());
            return filteredPersonList;
        case MEAN:
            Stream<Integer> sortedScoreValueStream = getSortedScoreValueStream(tag);
            int mean = calculateMean(sortedScoreValueStream);
            Stream<Person> filteredPersonStreamWithMean = filteredPersonsWithScoreTag(tag)
                    .filter(person -> person.getScoreForTag(tag).value >= mean);
            List<Person> filteredPersonListWithMean = filteredPersonStreamWithMean.collect(Collectors.toList());
            return filteredPersonListWithMean;
        case MEDIAN:
            Stream<Integer> sortedScoreValueStreamWithMedian = getSortedScoreValueStream(tag);
            int median = calculateMedian(sortedScoreValueStreamWithMedian);
            Stream<Person> filteredPersonStreamWithMedian = filteredPersonsWithScoreTag(tag)
                    .filter(person -> person.getScoreForTag(tag).value >= median);
            List<Person> filteredPersonListWithMedian = filteredPersonStreamWithMedian.collect(Collectors.toList());
            return filteredPersonListWithMedian;
        case PERCENTILE:
            Stream<Person> filteredStreamWithPercentile = filteredPersonsWithScoreTag(tag)
                    .filter(person -> generatePercentileWithTag(person, tag) >= value);
            List<Person> filteredPersonListWithPercentile = filteredStreamWithPercentile.collect(Collectors.toList());
            return filteredPersonListWithPercentile;
        default:
            assert false : "Invalid metric";
            return List.of();
        }
    }

    /**
     * Returns the stream of people in JABPro associated with that tag and has a value greater than
     * @param tag tag to be associated with
     * @return stream of people in JABPro associated with that tag and has a value greater than
     */
    public Stream<Person> filteredPersonsWithScoreTag(Tag tag) {
        Stream<Person> filteredStream = personData.stream().filter(person -> person.getTags().contains(tag)
                && person.getScoreList().hasTag(tag));
        return filteredStream;
    }


}

