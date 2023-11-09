package seedu.address.model.statistics;

import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;


/**
 * Represents a ReadOnlySummaryStatistic, that is no update can be done to the summary statistic.
 */
public interface ReadOnlySummaryStatistic {
    /**
     * Returns the number of people in the address book associated with that tag.
     * @return number of people in the address book associated with that tag.
     */
    public int getNumOfPeopleAssociatedWithTag(Tag tag);

    /**
     * Generate the Median of the score of the people associated with that tag.
     * @param tag tag to be associated with
     * @return median of the score of the people associated with that tag.
     */
    public int generateMedianWithTag(Tag tag);

    /**
     * Generate the Mean of the score of the people associated with that tag.
     * @param tag tag to be associated with
     * @return mean of the score of the people associated with that tag.
     */
    public int generateMeanWithTag(Tag tag);

    /**
     * Generate the Max score of the people associated with that tag.
     * @param person person to be associated with
     * @param tag tag to be associated with
     * @return max score of the people associated with that tag.
     */
    public double generatePercentileWithTag(Person person, Tag tag);

    /**
     * Generate the Min score of the people associated with that tag.
     * @param tag tag to be associated with
     * @return min score of the people associated with that tag.
     */
    public int generateMaxScoreValueWithTag(Tag tag);

    /**
     * Generate the Min score of the people associated with that tag.
     * @param tag tag to be associated with
     * @return min score of the people associated with that tag.
     */
    public int generateMinScoreValueWithTag(Tag tag);

}
