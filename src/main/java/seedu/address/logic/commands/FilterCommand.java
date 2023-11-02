package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.statistics.StatisticMetric;
import seedu.address.model.statistics.SummaryStatistic;
import seedu.address.model.tag.Tag;

/**
 * Filters the list of users by the given metric and value.
 */
public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filter";
    public static final String MESSAGE_INVALID_TAG = "Invalid tag provided. Needs to be non-empty name";
    public static final String MESSAGE_INVALID_METRIC = "Invalid metric provided. Needs to be one of: "
            + "score, mean, median, percentile";
    public static final String MESSAGE_INVALID_VALUE = "Invalid value provided. Needs to be a non negative integer "
            + "that is more than or equal to 0";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters the list of users whose value is greater "
            + "or equal to the value provided for the given metric and tag. \n"
            + "Parameters: "
            + "t/TAG  (Tag must be a non-empty name of type assessment), "
            + "met/METRIC  (Metric must be one of: score, mean, median, percentile), "
            + "val/VALUE (Value must be a non-negative integer, OPTIONAL for mean and median \n"
            + "Example: " + COMMAND_WORD + " " + "t/Interview met/SCORE val/50"
            + "Example: " + COMMAND_WORD + " " + "t/Interview met/MEAN";

    private final StatisticMetric metric;
    private final int value;
    private final Tag tag;

    /**
     * Creates a FilterCommand to filter the list of users by the given metric and value.
     * @param tag
     * @param metric
     * @param value
     */
    public FilterCommand(Tag tag, StatisticMetric metric, int value) {
        requireAllNonNull(tag, metric, value);
        this.tag = tag;
        this.metric = metric;
        this.value = value;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        SummaryStatistic summaryStatistic = new SummaryStatistic(model.getFilteredPersonList());
        if (summaryStatistic.getNumOfPeopleAssociatedWithTag(tag) == 0) {
            throw new CommandException(Messages.MESSAGE_TAG_DOES_NOT_EXIST);
        }

        List<Person> filteredPersonWithGreaterVal = summaryStatistic.filteredPersonList(tag, metric, value);
        model.updateFilteredPersonList(person -> isFilteredPerson(filteredPersonWithGreaterVal, person));
        return new CommandResult(String.format(successMessage(model.getFilteredPersonList().size())));
    }

    /**
     * Checks if the person is in the filtered list.
     * @param filteredList
     * @param person
     * @return true if the person is in the filtered list, false otherwise.
     */
    public boolean isFilteredPerson(List<Person> filteredList, Person person) {
        if (filteredList.contains(person)) {
            return true;
        }
        return false;
    }

    /**
     * Returns the success message for the filter command.
     * @param numberOfPeople number of people in the filtered list
     * @return success message for the filter command
     */
    public String successMessage(int numberOfPeople) {
        String message = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, numberOfPeople)
                + "\n"
                + String.format("There are %d people with a value more than the value provided for the given metric %s",
                numberOfPeople, metric);
        return message;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FilterCommand)) {
            return false;
        }
        FilterCommand otherFilterCommand = (FilterCommand) other;
        return this.tag.equals(otherFilterCommand.tag)
                && this.metric.equals(otherFilterCommand.metric)
                && this.value == otherFilterCommand.value;
    }
}
