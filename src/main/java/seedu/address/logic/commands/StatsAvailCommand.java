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
    public static final String MESSAGE_AVAIL_SUCCESS = "%1$d out of %2$d listed fosterers"
            + " are available (%3$.1f%%)!\n"
            + "Out of those available, \n"
            + "- %4$d can foster dogs (%5$.1f%%)\n"
            + "- %6$d can foster cats (%7$.1f%%)";

    public List<Person> getAvailableFosterers(List<Person> fosterers) {
        return fosterers.stream()
                .filter(fosterer ->
                        fosterer.getAvailability()
                                .equals(Availability.AVAILABLE))
                .collect(Collectors.toList());
    }

    public int getAbleDogCount(List<Person> fosterers) {
        return (int) fosterers.stream()
                .filter(fosterer ->
                        fosterer.getAnimalType()
                                .equals(AnimalType.ABLE_DOG))
                .count();
    }

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

        List<Person> availableFosterers = getAvailableFosterers(lastShownList);
        int availableCount = availableFosterers.size();
        int canFosterDogsCount = getAbleDogCount(availableFosterers);
        int canFosterCatsCount = getAbleCatCount(availableFosterers);

        float availPercent = calculatePercentage(availableCount, total);
        float canFosterDogsPercent = calculatePercentage(canFosterDogsCount, availableCount);
        float canFosterCatsPercent = calculatePercentage(canFosterCatsCount, availableCount);

        String result = String.format(MESSAGE_AVAIL_SUCCESS, availableCount, total, availPercent,
                canFosterDogsCount, canFosterDogsPercent, canFosterCatsCount, canFosterCatsPercent);

        return new CommandResult(result);
    }
}
