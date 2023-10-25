package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.AnimalType;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Person;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * Calculates the statistics of available fosterers from the currently displayed list.
 */
public class StatsAvailCommand extends StatsCommand {
    public static final String COMMAND_WORD = "avail";
    public static final String MESSAGE_AVAIL_SUMMARY = "%1$d out of %2$d listed fosterers are available (%3$.1f%%)!";

    public static final String MESSAGE_AVAIL_DETAILS = "Out of those available, \n"
            + "- %1$d can foster dogs (%2$.1f%%)\n"
            + "- %3$d can foster cats (%4$.1f%%)";

    /**
     * Returns a list of available fosterers.
     */
    public List<Person> getAvailableFosterers(List<Person> fosterers) {
        return fosterers.stream()
                .filter(fosterer ->
                        fosterer.getAvailability()
                                .equals(Availability.AVAILABLE))
                .collect(Collectors.toList());
    }

    /**
     * Returns the number of fosterers from the given list who can foster dogs.
     */
    public int getAbleDogCount(List<Person> fosterers) {
        return (int) fosterers.stream()
                .filter(fosterer ->
                        fosterer.getAnimalType()
                                .equals(AnimalType.ABLE_DOG))
                .count();
    }
    /**
     * Returns the number of fosterers from the given list who can foster cats.
     */
    public int getAbleCatCount(List<Person> fosterers) {
        return (int) fosterers.stream()
                .filter(fosterer ->
                        fosterer.getAnimalType()
                                .equals(AnimalType.ABLE_CAT))
                .count();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        int total = lastShownList.size();

        if (total == 0) {
            throw new CommandException(StatsCommand.MESSAGE_NO_FOSTERERS);
        }

        List<Person> availableFosterers = getAvailableFosterers(lastShownList);
        int availableCount = availableFosterers.size();
        float availPercent = calculatePercentage(availableCount, total);
        String resultSummary = String.format(MESSAGE_AVAIL_SUMMARY, availableCount, total, availPercent);

        if (availableCount == 0) {
            return new CommandResult(resultSummary);
        }

        int canFosterDogsCount = getAbleDogCount(availableFosterers);
        int canFosterCatsCount = getAbleCatCount(availableFosterers);

        float canFosterDogsPercent = calculatePercentage(canFosterDogsCount, availableCount);
        float canFosterCatsPercent = calculatePercentage(canFosterCatsCount, availableCount);

        String resultDetails = String.format(MESSAGE_AVAIL_DETAILS, canFosterDogsCount, canFosterDogsPercent,
                canFosterCatsCount, canFosterCatsPercent);

        return new CommandResult(resultSummary + "\n" + resultDetails);
    }
}
