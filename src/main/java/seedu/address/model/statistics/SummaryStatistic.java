package seedu.address.model.statistics;

import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.person.ScoreList;
import seedu.address.model.tag.Tag;

import java.util.logging.Logger;
import java.util.stream.Stream;

public class SummaryStatistic {
    private final Stream<Person> personData;
    private static final Logger logger = LogsCenter.getLogger(SummaryStatistic.class);

    public SummaryStatistic(ObservableList<Person> persons) {
        personData = persons.stream();
    }

    public int getNumOfPeople() {
        return (int) personData.count();
    }

    public boolean containTag(Tag tag) {
        return personData.anyMatch(person -> person.getTags().contains(tag));
    }

    public void generateMedianWithTag(Tag tag) {
        Stream<Person> filteredStream = personData.filter(person -> person.getTags().contains(tag));
        //Stream<ScoreList> scoreListStream = filteredStream.map(person -> person.getScoreList());
        //Stream<Score> scoreStream = scoreListStream.map(scoreList -> scoreList.getScore(tag));
        //Stream<Integer> scoreValueStream = scoreStream.map(score -> score.getScoreValue());
        //Stream<Integer> sortedScoreValueStream = scoreValueStream.sorted();
    }

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

}
