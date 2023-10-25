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
 * Calculates the statistics of fosterers who are currently fostering from the currently displayed list.
 */
public class StatsCurrentCommand extends StatsCommand {
    public static final String COMMAND_WORD = "current";
    public static final String MESSAGE_CURRENT_SUMMARY = "%1$d out of %2$d listed fosterers are "
            + "currently fostering (%3$.1f%%)!";

    public static final String MESSAGE_CURRENT_DETAILS = "- %1$d fostering dogs (%2$.1f%%)\n"
            + "- %3$d fostering cats (%4$.1f%%)";

    /**
     * Returns a list of fosterers who are currently fostering.
     */
    public List<Person> getCurrentFosterers(List<Person> fosterers) {
        return fosterers.stream()
                .filter(fosterer ->
                        fosterer.getAvailability()
                                .equals(Availability.NOT_AVAILABLE))
                .collect(Collectors.toList());
    }

    /**
     * Returns the number of fosterers from the given list who are fostering dogs.
     */
    public int getCurrentDogCount(List<Person> fosterers) {
        return (int) fosterers.stream()
                .filter(fosterer ->
                        fosterer.getAnimalType()
                                .equals(AnimalType.CURRENT_DOG))
                .count();
    }

    /**
     * Returns the number of fosterers from the given list who are fostering cats.
     */
    public int getCurrentCatCount(List<Person> fosterers) {
        return (int) fosterers.stream()
                .filter(fosterer ->
                        fosterer.getAnimalType()
                                .equals(AnimalType.CURRENT_CAT))
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

        List<Person> currentFosterers = getCurrentFosterers(lastShownList);
        int currentCount = currentFosterers.size();
        float currentPercent = calculatePercentage(currentCount, total);
        String resultSummary = String.format(MESSAGE_CURRENT_SUMMARY, currentCount, total, currentPercent);

        if (currentCount == 0) {
            return new CommandResult(resultSummary);
        }

        int fosteringDogsCount = getCurrentDogCount(currentFosterers);
        int fosteringCatsCount = getCurrentCatCount(currentFosterers);

        float fosteringDogsPercent = calculatePercentage(fosteringDogsCount, currentCount);
        float fosteringCatsPercent = calculatePercentage(fosteringCatsCount, currentCount);

        String resultDetails = String.format(MESSAGE_CURRENT_DETAILS, fosteringDogsCount,
                fosteringDogsPercent, fosteringCatsCount, fosteringCatsPercent);

        return new CommandResult(resultSummary + "\n" + resultDetails);
    }
}
